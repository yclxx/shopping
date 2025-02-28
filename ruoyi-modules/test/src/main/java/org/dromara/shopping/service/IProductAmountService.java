package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductAmountBo;
import org.dromara.shopping.domain.vo.ProductAmountVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品价格配置Service接口
 *
 * @author yzg
 * @date 2023-07-24
 */
public interface IProductAmountService {

    /**
     * 查询商品价格配置
     */
    ProductAmountVo queryById(Long amountId);

    /**
     * 查询商品价格配置列表
     */
    TableDataInfo<ProductAmountVo> queryPageList(ProductAmountBo bo, PageQuery pageQuery);

    /**
     * 查询商品价格配置列表
     */
    List<ProductAmountVo> queryList(ProductAmountBo bo);

    /**
     * 修改商品价格配置
     */
    Boolean insertByBo(ProductAmountBo bo);

    /**
     * 修改商品价格配置
     */
    Boolean updateByBo(ProductAmountBo bo);

    /**
     * 校验并批量删除商品价格配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
