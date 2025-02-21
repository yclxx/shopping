package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.MissionUserRecordLog;
import org.dromara.shopping.domain.bo.MissionUserRecordLogBo;
import org.dromara.shopping.domain.vo.MissionUserRecordLogVo;
import org.dromara.shopping.mapper.MissionUserRecordLogMapper;
import org.dromara.shopping.service.IMissionUserRecordLogService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 活动订单取码记录Service业务层处理
 *
 * @author yzg
 * @date 2023-05-10
 */
@RequiredArgsConstructor
@Service
public class MissionUserRecordLogServiceImpl implements IMissionUserRecordLogService {

    private final MissionUserRecordLogMapper baseMapper;

    /**
     * 查询活动订单取码记录
     */
    @Override
    public MissionUserRecordLogVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询活动订单取码记录列表
     */
    @Override
    public TableDataInfo<MissionUserRecordLogVo> queryPageList(MissionUserRecordLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MissionUserRecordLog> lqw = buildQueryWrapper(bo);
        Page<MissionUserRecordLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询活动订单取码记录列表
     */
    @Override
    public List<MissionUserRecordLogVo> queryList(MissionUserRecordLogBo bo) {
        LambdaQueryWrapper<MissionUserRecordLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MissionUserRecordLog> buildQueryWrapper(MissionUserRecordLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MissionUserRecordLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMissionUserRecordId() != null, MissionUserRecordLog::getMissionUserRecordId, bo.getMissionUserRecordId());
        lqw.eq(StringUtils.isNotBlank(bo.getPushNumber()), MissionUserRecordLog::getPushNumber, bo.getPushNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getExternalOrderNumber()), MissionUserRecordLog::getExternalOrderNumber, bo.getExternalOrderNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getExternalProductId()), MissionUserRecordLog::getExternalProductId, bo.getExternalProductId());
        lqw.eq(bo.getSendValue() != null, MissionUserRecordLog::getSendValue, bo.getSendValue());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MissionUserRecordLog::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增活动订单取码记录
     */
    @Override
    public Boolean insertByBo(MissionUserRecordLogBo bo) {
        MissionUserRecordLog add = BeanUtil.toBean(bo, MissionUserRecordLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改活动订单取码记录
     */
    @Override
    public Boolean updateByBo(MissionUserRecordLogBo bo) {
        MissionUserRecordLog update = BeanUtil.toBean(bo, MissionUserRecordLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MissionUserRecordLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除活动订单取码记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
