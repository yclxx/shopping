package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductYcf;
import org.dromara.shopping.domain.bo.ProductYcfBo;
import org.dromara.shopping.domain.vo.ProductYcfVo;
import org.dromara.shopping.mapper.ProductYcfMapper;
import org.dromara.shopping.service.IProductYcfService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 要出发产品Service业务层处理
 *
 * @author yzg
 * @date 2024-06-19
 */
@RequiredArgsConstructor
@Service
public class ProductYcfServiceImpl implements IProductYcfService {

    private final ProductYcfMapper baseMapper;

    /**
     * 查询要出发产品
     */
    @Override
    public ProductYcfVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    @Override
    public ProductYcfVo queryByItemId(String itemId) {
        LambdaQueryWrapper<ProductYcf> lqw = Wrappers.lambdaQuery();
        lqw.eq(ProductYcf::getItemId, itemId);
        lqw.last("order by id desc limit 1");
        return baseMapper.selectVoOne(lqw);
    }

    /**
     * 查询要出发产品列表
     */
    @Override
    public TableDataInfo<ProductYcfVo> queryPageList(ProductYcfBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductYcf> lqw = buildQueryWrapper(bo);
        Page<ProductYcfVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询要出发产品列表
     */
    @Override
    public List<ProductYcfVo> queryList(ProductYcfBo bo) {
        LambdaQueryWrapper<ProductYcf> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductYcf> buildQueryWrapper(ProductYcfBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductYcf> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductYcf::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getItemId()), ProductYcf::getItemId, bo.getItemId());
        lqw.eq(StringUtils.isNotBlank(bo.getProductType()), ProductYcf::getProductType, bo.getProductType());
        lqw.eq(StringUtils.isNotBlank(bo.getIsTicketVoucher()), ProductYcf::getIsTicketVoucher, bo.getIsTicketVoucher());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketVoucherDateBegin()), ProductYcf::getTicketVoucherDateBegin, bo.getTicketVoucherDateBegin());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketVoucherDateEnd()), ProductYcf::getTicketVoucherDateEnd, bo.getTicketVoucherDateEnd());
        lqw.eq(StringUtils.isNotBlank(bo.getPoiId()), ProductYcf::getPoiId, bo.getPoiId());
        lqw.eq(bo.getBookAheadMin() != null, ProductYcf::getBookAheadMin, bo.getBookAheadMin());
        lqw.eq(bo.getMinNum() != null, ProductYcf::getMinNum, bo.getMinNum());
        lqw.eq(bo.getMaxNum() != null, ProductYcf::getMaxNum, bo.getMaxNum());
        lqw.eq(bo.getMinNight() != null, ProductYcf::getMinNight, bo.getMinNight());
        lqw.eq(bo.getMaxNight() != null, ProductYcf::getMaxNight, bo.getMaxNight());
        lqw.eq(bo.getMarketPrice() != null, ProductYcf::getMarketPrice, bo.getMarketPrice());
        lqw.eq(bo.getStartDate() != null, ProductYcf::getStartDate, bo.getStartDate());
        lqw.eq(bo.getEndDate() != null, ProductYcf::getEndDate, bo.getEndDate());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundType()), ProductYcf::getRefundType, bo.getRefundType());
        lqw.eq(StringUtils.isNotBlank(bo.getAdvanceOrDelayType()), ProductYcf::getAdvanceOrDelayType, bo.getAdvanceOrDelayType());
        lqw.eq(bo.getRefundPreminute() != null, ProductYcf::getRefundPreminute, bo.getRefundPreminute());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundNote()), ProductYcf::getRefundNote, bo.getRefundNote());
        lqw.eq(bo.getRoomChoiceNum() != null, ProductYcf::getRoomChoiceNum, bo.getRoomChoiceNum());
        lqw.eq(bo.getRoomOptionNum() != null, ProductYcf::getRoomOptionNum, bo.getRoomOptionNum());
        lqw.eq(bo.getTicketChoiceNum() != null, ProductYcf::getTicketChoiceNum, bo.getTicketChoiceNum());
        lqw.eq(bo.getTicketOptionNum() != null, ProductYcf::getTicketOptionNum, bo.getTicketOptionNum());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketType()), ProductYcf::getTicketType, bo.getTicketType());
        lqw.eq(StringUtils.isNotBlank(bo.getGetTicketMode()), ProductYcf::getGetTicketMode, bo.getGetTicketMode());
        lqw.eq(bo.getFoodChoiceNum() != null, ProductYcf::getFoodChoiceNum, bo.getFoodChoiceNum());
        lqw.eq(bo.getFoodOptionNum() != null, ProductYcf::getFoodOptionNum, bo.getFoodOptionNum());
        lqw.eq(StringUtils.isNotBlank(bo.getIsGlobalSale()), ProductYcf::getIsGlobalSale, bo.getIsGlobalSale());
        lqw.eq(StringUtils.isNotBlank(bo.getRoomMessage()), ProductYcf::getRoomMessage, bo.getRoomMessage());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketMessage()), ProductYcf::getTicketMessage, bo.getTicketMessage());
        lqw.eq(StringUtils.isNotBlank(bo.getFoodMessage()), ProductYcf::getFoodMessage, bo.getFoodMessage());
        lqw.eq(StringUtils.isNotBlank(bo.getBookRuleMessage()), ProductYcf::getBookRuleMessage, bo.getBookRuleMessage());
        lqw.eq(bo.getOrderDateBegin() != null, ProductYcf::getOrderDateBegin, bo.getOrderDateBegin());
        lqw.eq(bo.getOrderDateEnd() != null, ProductYcf::getOrderDateEnd, bo.getOrderDateEnd());
        lqw.eq(StringUtils.isNotBlank(bo.getPreSaleDescription()), ProductYcf::getPreSaleDescription, bo.getPreSaleDescription());
        return lqw;
    }

    /**
     * 新增要出发产品
     */
    @Override
    public Boolean insertByBo(ProductYcfBo bo) {
        ProductYcf add = BeanUtil.toBean(bo, ProductYcf.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改要出发产品
     */
    @Override
    public Boolean updateByBo(ProductYcfBo bo) {
        ProductYcf update = BeanUtil.toBean(bo, ProductYcf.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductYcf entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除要出发产品
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
