package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Action;
import org.dromara.shopping.domain.ProductAction;
import org.dromara.shopping.domain.bo.CouponBo;
import org.dromara.shopping.domain.vo.CouponVo;
import org.dromara.shopping.domain.vo.ProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 优惠券Service接口
 *
 * @author yzg
 * @date 2023-10-16
 */
public interface ICouponService {

    /**
     * 查询优惠券
     */
    CouponVo queryById(Long couponId);

    /**
     * 根据批次号查询数量
     *
     * @return
     */
    Long queryNumberByActionNo(String actionNo);

    /**
     * 查询优惠券列表
     */
    TableDataInfo<CouponVo> queryPageList(CouponBo bo, PageQuery pageQuery);

    /**
     * 查询优惠券对应商品列表
     */
    TableDataInfo<ProductVo> queryProductPageList(CouponBo bo, PageQuery pageQuery);

    /**
     * 查询优惠券列表
     */
    List<CouponVo> queryList(CouponBo bo);

    /**
     * 批量创建优惠券
     */
    void addCoupon(Action action, Long number, List<ProductAction> productActions);

    /**
     * 修改优惠券
     */
    Boolean insertByBo(CouponBo bo);

    /**
     * 修改优惠券
     */
    Boolean updateByBo(CouponBo bo);

    /**
     * 校验并批量删除优惠券信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
