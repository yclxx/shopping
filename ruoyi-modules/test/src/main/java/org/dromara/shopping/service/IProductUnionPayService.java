package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductUnionPayBo;
import org.dromara.shopping.domain.vo.ProductUnionPayVo;

import java.util.Collection;
import java.util.List;

/**
 * 银联分销商品详情Service接口
 *
 * @author yzg
 * @date 2023-08-07
 */
public interface IProductUnionPayService {

    /**
     * 查询银联分销商品详情
     */
    ProductUnionPayVo queryById(Long productId);

    /**
     * 查询银联分销商品详情列表
     */
    TableDataInfo<ProductUnionPayVo> queryPageList(ProductUnionPayBo bo, PageQuery pageQuery);

    /**
     * 查询银联分销商品详情列表
     */
    List<ProductUnionPayVo> queryList(ProductUnionPayBo bo);

    /**
     * 修改银联分销商品详情
     */
    Boolean insertByBo(ProductUnionPayBo bo);

    /**
     * 修改银联分销商品详情
     */
    Boolean updateByBo(ProductUnionPayBo bo);

    /**
     * 校验并批量删除银联分销商品详情信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
