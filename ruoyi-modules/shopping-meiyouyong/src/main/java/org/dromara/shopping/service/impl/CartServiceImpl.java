package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Cart;
import org.dromara.shopping.domain.bo.CartBo;
import org.dromara.shopping.domain.vo.CartVo;
import org.dromara.shopping.mapper.CartMapper;
import org.dromara.shopping.service.ICartService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 购物车Service业务层处理
 *
 * @author yzg
 * @date 2023-10-16
 */
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements ICartService {

    private final CartMapper baseMapper;

    /**
     * 查询购物车
     */
    @Override
    public CartVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询购物车列表
     */
    @Override
    public TableDataInfo<CartVo> queryPageList(CartBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Cart> lqw = buildQueryWrapper(bo);
        Page<CartVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询购物车列表
     */
    @Override
    public List<CartVo> queryList(CartBo bo) {
        LambdaQueryWrapper<Cart> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Cart> buildQueryWrapper(CartBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Cart> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, Cart::getUserId, bo.getUserId());
        lqw.eq(bo.getProductId() != null, Cart::getProductId, bo.getProductId());
        lqw.eq(bo.getCreateSellingPrice() != null, Cart::getCreateSellingPrice, bo.getCreateSellingPrice());
        lqw.eq(bo.getQuantity() != null, Cart::getQuantity, bo.getQuantity());
        return lqw;
    }

    /**
     * 新增购物车
     */
    @Override
    public Boolean insertByBo(CartBo bo) {
        Cart add = BeanUtil.toBean(bo, Cart.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改购物车
     */
    @Override
    public Boolean updateByBo(CartBo bo) {
        Cart update = BeanUtil.toBean(bo, Cart.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Cart entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除购物车
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
