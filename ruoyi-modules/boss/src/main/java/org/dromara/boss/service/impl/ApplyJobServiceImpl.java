package org.dromara.boss.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.boss.domain.bo.ApplyJobBo;
import org.dromara.boss.domain.vo.ApplyJobVo;
import org.dromara.boss.domain.ApplyJob;
import org.dromara.boss.mapper.ApplyJobMapper;
import org.dromara.boss.service.IApplyJobService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 沟通任务Service业务层处理
 *
 * @author xx
 * @date 2024-11-16
 */
@RequiredArgsConstructor
@Service
public class ApplyJobServiceImpl implements IApplyJobService {

    private final ApplyJobMapper baseMapper;

    /**
     * 查询沟通任务
     *
     * @param applyJobId 主键
     * @return 沟通任务
     */
    @Override
    public ApplyJobVo queryById(Long applyJobId){
        return baseMapper.selectVoById(applyJobId);
    }

    /**
     * 分页查询沟通任务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 沟通任务分页列表
     */
    @Override
    public TableDataInfo<ApplyJobVo> queryPageList(ApplyJobBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ApplyJob> lqw = buildQueryWrapper(bo);
        Page<ApplyJobVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的沟通任务列表
     *
     * @param bo 查询条件
     * @return 沟通任务列表
     */
    @Override
    public List<ApplyJobVo> queryList(ApplyJobBo bo) {
        LambdaQueryWrapper<ApplyJob> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ApplyJob> buildQueryWrapper(ApplyJobBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ApplyJob> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getEncryptExpectId()), ApplyJob::getEncryptExpectId, bo.getEncryptExpectId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ApplyJob::getStatus, bo.getStatus());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ApplyJob::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增沟通任务
     *
     * @param bo 沟通任务
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ApplyJobBo bo) {
        ApplyJob add = MapstructUtils.convert(bo, ApplyJob.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setApplyJobId(add.getApplyJobId());
        }
        return flag;
    }

    /**
     * 修改沟通任务
     *
     * @param bo 沟通任务
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ApplyJobBo bo) {
        ApplyJob update = MapstructUtils.convert(bo, ApplyJob.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ApplyJob entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除沟通任务信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
