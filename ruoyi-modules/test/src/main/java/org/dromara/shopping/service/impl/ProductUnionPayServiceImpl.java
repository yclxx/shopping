package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductUnionPay;
import org.dromara.shopping.domain.bo.ProductUnionPayBo;
import org.dromara.shopping.domain.vo.ProductUnionPayVo;
import org.dromara.shopping.mapper.ProductUnionPayMapper;
import org.dromara.shopping.service.IProductUnionPayService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 银联分销商品详情Service业务层处理
 *
 * @author yzg
 * @date 2023-08-07
 */
@RequiredArgsConstructor
@Service
public class ProductUnionPayServiceImpl implements IProductUnionPayService {

    private final ProductUnionPayMapper baseMapper;

    /**
     * 查询银联分销商品详情
     */
    @Override
    public ProductUnionPayVo queryById(Long productId) {
        return baseMapper.selectVoById(productId);
    }

    /**
     * 查询银联分销商品详情列表
     */
    @Override
    public TableDataInfo<ProductUnionPayVo> queryPageList(ProductUnionPayBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductUnionPay> lqw = buildQueryWrapper(bo);
        Page<ProductUnionPayVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询银联分销商品详情列表
     */
    @Override
    public List<ProductUnionPayVo> queryList(ProductUnionPayBo bo) {
        LambdaQueryWrapper<ProductUnionPay> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductUnionPay> buildQueryWrapper(ProductUnionPayBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductUnionPay> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getExternalProductId()), ProductUnionPay::getExternalProductId, bo.getExternalProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), ProductUnionPay::getTitle, bo.getTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getCoopMd()), ProductUnionPay::getCoopMd, bo.getCoopMd());
        lqw.eq(StringUtils.isNotBlank(bo.getCancelMethod()), ProductUnionPay::getCancelMethod, bo.getCancelMethod());
        lqw.eq(StringUtils.isNotBlank(bo.getFlgSubProd()), ProductUnionPay::getFlgSubProd, bo.getFlgSubProd());
        lqw.eq(StringUtils.isNotBlank(bo.getFlgUpBond()), ProductUnionPay::getFlgUpBond, bo.getFlgUpBond());
        lqw.eq(StringUtils.isNotBlank(bo.getProdTp()), ProductUnionPay::getProdTp, bo.getProdTp());
        lqw.eq(StringUtils.isNotBlank(bo.getDtlPgUrl()), ProductUnionPay::getDtlPgUrl, bo.getDtlPgUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getProdAstidTp()), ProductUnionPay::getProdAstidTp, bo.getProdAstidTp());
        lqw.eq(StringUtils.isNotBlank(bo.getFlgRfd()), ProductUnionPay::getFlgRfd, bo.getFlgRfd());
        lqw.eq(StringUtils.isNotBlank(bo.getFlgExprRfd()), ProductUnionPay::getFlgExprRfd, bo.getFlgExprRfd());
        lqw.eq(StringUtils.isNotBlank(bo.getDetailTp()), ProductUnionPay::getDetailTp, bo.getDetailTp());
        lqw.eq(StringUtils.isNotBlank(bo.getCusIstr()), ProductUnionPay::getCusIstr, bo.getCusIstr());
        lqw.eq(StringUtils.isNotBlank(bo.getCusSvcHtline()), ProductUnionPay::getCusSvcHtline, bo.getCusSvcHtline());
        lqw.eq(StringUtils.isNotBlank(bo.getBrandQual()), ProductUnionPay::getBrandQual, bo.getBrandQual());
        lqw.eq(StringUtils.isNotBlank(bo.getStoreLstTp()), ProductUnionPay::getStoreLstTp, bo.getStoreLstTp());
        lqw.eq(StringUtils.isNotBlank(bo.getStoreLstUrl()), ProductUnionPay::getStoreLstUrl, bo.getStoreLstUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getExchUrl()), ProductUnionPay::getExchUrl, bo.getExchUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getExchAstidTp()), ProductUnionPay::getExchAstidTp, bo.getExchAstidTp());
        lqw.eq(StringUtils.isNotBlank(bo.getProdSubTitle()), ProductUnionPay::getProdSubTitle, bo.getProdSubTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getProdAdv()), ProductUnionPay::getProdAdv, bo.getProdAdv());
        return lqw;
    }

    /**
     * 新增银联分销商品详情
     */
    @Override
    public Boolean insertByBo(ProductUnionPayBo bo) {
        ProductUnionPay add = BeanUtil.toBean(bo, ProductUnionPay.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setProductId(add.getProductId());
        }
        return flag;
    }

    /**
     * 修改银联分销商品详情
     */
    @Override
    public Boolean updateByBo(ProductUnionPayBo bo) {
        ProductUnionPay update = BeanUtil.toBean(bo, ProductUnionPay.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductUnionPay entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除银联分销商品详情
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
