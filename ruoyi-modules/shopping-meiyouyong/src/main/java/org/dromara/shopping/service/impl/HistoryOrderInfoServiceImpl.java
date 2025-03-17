package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.HistoryOrderInfo;
import org.dromara.shopping.domain.bo.HistoryOrderInfoBo;
import org.dromara.shopping.domain.vo.HistoryOrderInfoVo;
import org.dromara.shopping.mapper.HistoryOrderInfoMapper;
import org.dromara.shopping.service.IHistoryOrderInfoService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 历史订单扩展信息Service业务层处理
 *
 * @author yzg
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class HistoryOrderInfoServiceImpl implements IHistoryOrderInfoService {

    private final HistoryOrderInfoMapper baseMapper;

    /**
     * 查询历史订单扩展信息
     */
    @Override
    public HistoryOrderInfoVo queryById(Long number){
        return baseMapper.selectVoById(number);
    }

    /**
     * 查询历史订单扩展信息列表
     */
    @Override
    public TableDataInfo<HistoryOrderInfoVo> queryPageList(HistoryOrderInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HistoryOrderInfo> lqw = buildQueryWrapper(bo);
        Page<HistoryOrderInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询历史订单扩展信息列表
     */
    @Override
    public List<HistoryOrderInfoVo> queryList(HistoryOrderInfoBo bo) {
        LambdaQueryWrapper<HistoryOrderInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HistoryOrderInfo> buildQueryWrapper(HistoryOrderInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HistoryOrderInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(null != bo.getNumber(), HistoryOrderInfo::getNumber, bo.getNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getVip62Status()), HistoryOrderInfo::getVip62Status, bo.getVip62Status());
        lqw.eq(StringUtils.isNotBlank(bo.getVip62MemberType()), HistoryOrderInfo::getVip62MemberType, bo.getVip62MemberType());
        lqw.eq(StringUtils.isNotBlank(bo.getVip62EndTime()), HistoryOrderInfo::getVip62EndTime, bo.getVip62EndTime());
        lqw.eq(StringUtils.isNotBlank(bo.getVip62BeginTime()), HistoryOrderInfo::getVip62BeginTime, bo.getVip62BeginTime());
        lqw.eq(StringUtils.isNotBlank(bo.getTxnTime()), HistoryOrderInfo::getTxnTime, bo.getTxnTime());
        lqw.eq(StringUtils.isNotBlank(bo.getQueryId()), HistoryOrderInfo::getQueryId, bo.getQueryId());
        lqw.eq(StringUtils.isNotBlank(bo.getTraceTime()), HistoryOrderInfo::getTraceTime, bo.getTraceTime());
        lqw.eq(StringUtils.isNotBlank(bo.getTraceNo()), HistoryOrderInfo::getTraceNo, bo.getTraceNo());
        lqw.eq(bo.getTxnAmt() != null, HistoryOrderInfo::getTxnAmt, bo.getTxnAmt());
        lqw.eq(StringUtils.isNotBlank(bo.getIssAddnData()), HistoryOrderInfo::getIssAddnData, bo.getIssAddnData());
        lqw.eq(StringUtils.isNotBlank(bo.getCommodityJson()), HistoryOrderInfo::getCommodityJson, bo.getCommodityJson());
        return lqw;
    }

    /**
     * 新增历史订单扩展信息
     */
    @Override
    public Boolean insertByBo(HistoryOrderInfoBo bo) {
        HistoryOrderInfo add = BeanUtil.toBean(bo, HistoryOrderInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setNumber(add.getNumber());
        }
        return flag;
    }

    /**
     * 修改历史订单扩展信息
     */
    @Override
    public Boolean updateByBo(HistoryOrderInfoBo bo) {
        HistoryOrderInfo update = BeanUtil.toBean(bo, HistoryOrderInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HistoryOrderInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除历史订单扩展信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
