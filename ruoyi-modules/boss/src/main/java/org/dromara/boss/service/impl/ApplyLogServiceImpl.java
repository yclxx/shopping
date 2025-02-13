package org.dromara.boss.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import com.baomidou.lock.executor.RedissonLockExecutor;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.boss.domain.ApplyJob;
import org.dromara.boss.domain.ApplyLog;
import org.dromara.boss.domain.bo.ApplyLogBo;
import org.dromara.boss.domain.vo.*;
import org.dromara.boss.mapper.ApplyJobMapper;
import org.dromara.boss.mapper.ApplyLogMapper;
import org.dromara.boss.service.IApplyExecuteService;
import org.dromara.boss.service.IApplyLogService;
import org.dromara.boss.utils.ZpUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 沟通记录Service业务层处理
 *
 * @author xx
 * @date 2024-11-16
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ApplyLogServiceImpl implements IApplyLogService, IApplyExecuteService {

    private final ApplyLogMapper baseMapper;
    private final ApplyJobMapper applyJobMapper;
    private final LockTemplate lockTemplate;

    /**
     * 查询沟通记录
     *
     * @param applyLogId 主键
     * @return 沟通记录
     */
    @Override
    public ApplyLogVo queryById(Long applyLogId) {
        return baseMapper.selectVoById(applyLogId);
    }

    /**
     * 分页查询沟通记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 沟通记录分页列表
     */
    @Override
    public TableDataInfo<ApplyLogVo> queryPageList(ApplyLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ApplyLog> lqw = buildQueryWrapper(bo);
        Page<ApplyLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的沟通记录列表
     *
     * @param bo 查询条件
     * @return 沟通记录列表
     */
    @Override
    public List<ApplyLogVo> queryList(ApplyLogBo bo) {
        LambdaQueryWrapper<ApplyLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ApplyLog> buildQueryWrapper(ApplyLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ApplyLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(null != bo.getApplyJobId(), ApplyLog::getApplyJobId, bo.getApplyJobId());
        lqw.like(StringUtils.isNotBlank(bo.getJobName()), ApplyLog::getJobName, bo.getJobName());
        lqw.like(StringUtils.isNotBlank(bo.getLocationName()), ApplyLog::getLocationName, bo.getLocationName());
        lqw.like(StringUtils.isNotBlank(bo.getBossName()), ApplyLog::getBossName, bo.getBossName());
        lqw.like(StringUtils.isNotBlank(bo.getBrandName()), ApplyLog::getBrandName, bo.getBrandName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ApplyLog::getStatus, bo.getStatus());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null, ApplyLog::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增沟通记录
     *
     * @param bo 沟通记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ApplyLogBo bo) {
        ApplyLog add = MapstructUtils.convert(bo, ApplyLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setApplyLogId(add.getApplyLogId());
        }
        return flag;
    }

    /**
     * 修改沟通记录
     *
     * @param bo 沟通记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ApplyLogBo bo) {
        ApplyLog update = MapstructUtils.convert(bo, ApplyLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ApplyLog entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除沟通记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    @Async
    @Override
    public void execute(Long applyJobId) {
        // 加锁
        String lockKey = "applyJobExecute" + applyJobId;
        final LockInfo lock = lockTemplate.lock(lockKey, 30000L, 5000L, RedissonLockExecutor.class);
        if (null == lock) {
            return;
        }
        ApplyJob applyJob = new ApplyJob();
        try {
            // 查询待执行任务
            ApplyJobVo applyJobVo = applyJobMapper.selectVoById(applyJobId);
            if (null == applyJobVo) {
                log.info("待执行任务不存在：{}", applyJobId);
                return;
            }
            if (!"0".equals(applyJobVo.getStatus())) {
                log.info("任务状态不为待执行：{}", applyJobVo);
                return;
            }
            // 修改状态为执行中
            applyJob.setApplyJobId(applyJobId);
            applyJob.setStatus("1");
            applyJobMapper.updateById(applyJob);

            // 执行任务
            ZpUtils instance = ZpUtils.getInstance();

            long applyCount = 0;
            for (int i = 1; i <= applyJobVo.getPages(); i++) {
                ZpListDataVo zpData = instance.queryZpList(applyJobVo.getEncryptExpectId(), i, true);
                if (null == zpData || ObjectUtil.isEmpty(zpData.getJobList())) {
                    log.info("获取招聘列表失败，返回结果为空");
                    break;
                }
                List<ZpListJobInfoVo> jobList = zpData.getJobList();
                for (ZpListJobInfoVo zpJobInfoVo : jobList) {
                    // 跳过猎头 bossTitle
                    if ("Y".equals(applyJobVo.getSkipHeadhunting()) && StringUtils.isNotBlank(zpJobInfoVo.getBossTitle()) && zpJobInfoVo.getBossTitle().contains("猎头")) {
                        continue;
                    }

                    // 跳过兼职 jobName
                    if ("Y".equals(applyJobVo.getSkipPartTimeJob()) && StringUtils.isNotBlank(zpJobInfoVo.getJobName()) && zpJobInfoVo.getJobName().contains("兼职")) {
                        continue;
                    }

                    // 校验薪资
                    boolean checkSalary = instance.checkSalary(zpJobInfoVo.getSalaryDesc(), applyJobVo.getMinSalary(), applyJobVo.getMaxSalary());
                    if (checkSalary == false) {
                        continue;
                    }

                    // 休眠一下，防止被当成机器人
                    int sleepTime = (RandomUtil.getRandom().nextInt(10) + 10) * 1000;
                    ThreadUtil.sleep(sleepTime);

                    // 查询招聘详情
                    ZpDetailInfoVo infoData = instance.queryZpInfoVo(zpJobInfoVo.getSecurityId(), zpJobInfoVo.getLid(), true);
                    log.info("获取招聘详细信息成功：{}", infoData);

                    // 判断招聘岗位是否是JAVA
                    boolean checkPositionName = instance.checkPositionName(applyJobVo.getPositionName(), infoData.getJobInfo().getPositionName());
                    if (checkPositionName == false) {
                        continue;
                    }

                    // 跳过猎头
                    if ("Y".equals(applyJobVo.getSkipHeadhunting()) && StringUtils.isNotBlank(infoData.getBossInfo().getTitle()) && infoData.getBossInfo().getTitle().contains("猎头")) {
                        log.info("跳过猎头,{}", infoData.getBossInfo().getTitle());
                        continue;
                    }

                    // 判断boos 活跃时间
                    String activeTimeDesc = infoData.getBossInfo().getActiveTimeDesc();
                    // null 刚刚活跃 今日活跃 3日内活跃 本周活跃 2周内活跃 本月活跃 半年前活跃
                    if (null == activeTimeDesc || (StringUtils.isNotBlank(applyJobVo.getActiveTimeDesc()) && applyJobVo.getActiveTimeDesc().contains(activeTimeDesc) == false)) {
                        log.info("长时间不活跃的跳过,{}", activeTimeDesc);
                        continue;
                    }

                    // 休眠一下，防止被当成机器人
                    int sleepTime2 = (RandomUtil.getRandom().nextInt(10) + 20) * 1000;
                    ThreadUtil.sleep(sleepTime2);

                    // 投递
                    ZpAddResultDataVo zpAddResultDataVo = instance.addZp(infoData.getSecurityId(), infoData.getJobInfo().getEncryptId(), infoData.getLid(), true);
                    log.info("投递公司：{}，返回结果：{}", infoData.getBossInfo().getBrandName(), zpAddResultDataVo);

                    if (null != zpAddResultDataVo && zpAddResultDataVo.getShowGreeting()) {
                        // 记录沟通记录
                        ApplyLog applyLog = getApplyLog(applyJobId, infoData);
                        baseMapper.insert(applyLog);

                        applyCount++;
                    }
                }
            }
            log.info("投递完成，共沟通：{}家", applyCount);
            // 修改数量
            applyJob.setApplyCount(applyCount);
            applyJob.setStatus("2");

        } catch (Exception e) {
            applyJob.setStatus("3");
            log.error("执行异常：", e);
        } finally {
            // 释放锁
            lockTemplate.releaseLock(lock);

            applyJob.setFinishTime(new Date());
            applyJobMapper.updateById(applyJob);
        }
    }

    private static ApplyLog getApplyLog(Long applyJobId, ZpDetailInfoVo infoData) {
        ApplyLog applyLog = new ApplyLog();
        applyLog.setApplyJobId(applyJobId);
        applyLog.setJobName(infoData.getJobInfo().getJobName());
        applyLog.setSalaryDesc(infoData.getJobInfo().getSalaryDesc());
        applyLog.setLocationName(infoData.getJobInfo().getLocationName());
        applyLog.setAddress(infoData.getJobInfo().getAddress());
        applyLog.setBossName(infoData.getBossInfo().getName());
        applyLog.setBrandName(infoData.getBossInfo().getBrandName());
        applyLog.setPostDescription(infoData.getJobInfo().getPostDescription());
        return applyLog;
    }
}
