package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductAmount;
import org.dromara.shopping.domain.bo.ProductAmountBo;
import org.dromara.shopping.domain.vo.ProductAmountVo;
import org.dromara.shopping.mapper.ProductAmountMapper;
import org.dromara.shopping.service.IProductAmountService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品价格配置Service业务层处理
 *
 * @author yzg
 * @date 2023-07-24
 */
@RequiredArgsConstructor
@Service
public class ProductAmountServiceImpl implements IProductAmountService {

    private final ProductAmountMapper baseMapper;

    /**
     * 查询商品价格配置
     */
    @Override
    public ProductAmountVo queryById(Long amountId){
        return baseMapper.selectVoById(amountId);
    }

    /**
     * 查询商品价格配置列表
     */
    @Override
    public TableDataInfo<ProductAmountVo> queryPageList(ProductAmountBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductAmount> lqw = buildQueryWrapper(bo);
        Page<ProductAmountVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品价格配置列表
     */
    @Override
    public List<ProductAmountVo> queryList(ProductAmountBo bo) {
        LambdaQueryWrapper<ProductAmount> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductAmount> buildQueryWrapper(ProductAmountBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductAmount> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductAmount::getProductId, bo.getProductId());
        lqw.eq(bo.getExternalProductSendValue() != null, ProductAmount::getExternalProductSendValue, bo.getExternalProductSendValue());
        lqw.eq(bo.getDrawProbability() != null, ProductAmount::getDrawProbability, bo.getDrawProbability());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ProductAmount::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商品价格配置
     */
    @Override
    public Boolean insertByBo(ProductAmountBo bo) {
        ProductAmount add = BeanUtil.toBean(bo, ProductAmount.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAmountId(add.getAmountId());
        }
        return flag;
    }

    /**
     * 修改商品价格配置
     */
    @Override
    public Boolean updateByBo(ProductAmountBo bo) {
        ProductAmount update = BeanUtil.toBean(bo, ProductAmount.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductAmount entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品价格配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
