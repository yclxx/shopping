package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.RecordLog;
import org.dromara.shopping.domain.bo.RecordLogBo;
import org.dromara.shopping.domain.vo.RecordLogVo;
import org.dromara.shopping.mapper.RecordLogMapper;
import org.dromara.shopping.service.IRecordLogService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 记录日志Service业务层处理
 *
 * @author yzg
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class RecordLogServiceImpl implements IRecordLogService {

    private final RecordLogMapper baseMapper;

    /**
     * 查询记录日志
     */
    @Override
    public RecordLogVo queryById(Long recordId){
        return baseMapper.selectVoById(recordId);
    }

    /**
     * 查询记录日志列表
     */
    @Override
    public TableDataInfo<RecordLogVo> queryPageList(RecordLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<RecordLog> lqw = buildQueryWrapper(bo);
        Page<RecordLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询记录日志列表
     */
    @Override
    public List<RecordLogVo> queryList(RecordLogBo bo) {
        LambdaQueryWrapper<RecordLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<RecordLog> buildQueryWrapper(RecordLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<RecordLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, RecordLog::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getUserNumber() != null, RecordLog::getUserNumber, bo.getUserNumber());
        lqw.eq(bo.getUserPeople() != null, RecordLog::getUserPeople, bo.getUserPeople());
        lqw.eq(bo.getOrderBuyNumber() != null, RecordLog::getOrderBuyNumber, bo.getOrderBuyNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getRecordDate()), RecordLog::getRecordDate, bo.getRecordDate());
        lqw.eq(StringUtils.isNotBlank(bo.getSource()), RecordLog::getSource, bo.getSource());
        return lqw;
    }

    /**
     * 新增记录日志
     */
    @Override
    public Boolean insertByBo(RecordLogBo bo) {
        RecordLog add = BeanUtil.toBean(bo, RecordLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRecordId(add.getRecordId());
        }
        return flag;
    }

    /**
     * 修改记录日志
     */
    @Override
    public Boolean updateByBo(RecordLogBo bo) {
        RecordLog update = BeanUtil.toBean(bo, RecordLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(RecordLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除记录日志
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
