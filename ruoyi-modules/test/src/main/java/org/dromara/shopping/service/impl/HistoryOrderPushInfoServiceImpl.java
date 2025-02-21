package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.HistoryOrderPushInfo;
import org.dromara.shopping.domain.bo.HistoryOrderPushInfoBo;
import org.dromara.shopping.domain.vo.HistoryOrderPushInfoVo;
import org.dromara.shopping.mapper.HistoryOrderPushInfoMapper;
import org.dromara.shopping.service.IHistoryOrderPushInfoService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 历史订单取码记录Service业务层处理
 *
 * @author yzg
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class HistoryOrderPushInfoServiceImpl implements IHistoryOrderPushInfoService {

    private final HistoryOrderPushInfoMapper baseMapper;

    /**
     * 查询历史订单取码记录
     */
    @Override
    public HistoryOrderPushInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询历史订单取码记录列表
     */
    @Override
    public TableDataInfo<HistoryOrderPushInfoVo> queryPageList(HistoryOrderPushInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HistoryOrderPushInfo> lqw = buildQueryWrapper(bo);
        Page<HistoryOrderPushInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询历史订单取码记录列表
     */
    @Override
    public List<HistoryOrderPushInfoVo> queryList(HistoryOrderPushInfoBo bo) {
        LambdaQueryWrapper<HistoryOrderPushInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HistoryOrderPushInfo> buildQueryWrapper(HistoryOrderPushInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HistoryOrderPushInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getNumber() != null, HistoryOrderPushInfo::getNumber, bo.getNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getPushNumber()), HistoryOrderPushInfo::getPushNumber, bo.getPushNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getExternalOrderNumber()), HistoryOrderPushInfo::getExternalOrderNumber, bo.getExternalOrderNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getExternalProductId()), HistoryOrderPushInfo::getExternalProductId, bo.getExternalProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), HistoryOrderPushInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增历史订单取码记录
     */
    @Override
    public Boolean insertByBo(HistoryOrderPushInfoBo bo) {
        HistoryOrderPushInfo add = BeanUtil.toBean(bo, HistoryOrderPushInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改历史订单取码记录
     */
    @Override
    public Boolean updateByBo(HistoryOrderPushInfoBo bo) {
        HistoryOrderPushInfo update = BeanUtil.toBean(bo, HistoryOrderPushInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HistoryOrderPushInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除历史订单取码记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
