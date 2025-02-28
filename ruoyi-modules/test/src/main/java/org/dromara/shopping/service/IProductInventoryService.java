package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductInventoryBo;
import org.dromara.shopping.domain.vo.ProductInventoryVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品库存信息Service接口
 *
 * @author yzg
 * @date 2024-05-10
 */
public interface IProductInventoryService {

    /**
     * 查询商品库存信息
     */
    ProductInventoryVo queryById(Long inventoryId);

    /**
     * 查询商品库存信息列表
     */
    TableDataInfo<ProductInventoryVo> queryPageList(ProductInventoryBo bo, PageQuery pageQuery);

    /**
     * 查询商品库存信息列表
     */
    List<ProductInventoryVo> queryList(ProductInventoryBo bo);

    /**
     * 修改商品库存信息
     */
    Boolean insertByBo(ProductInventoryBo bo);

    /**
     * 修改商品库存信息
     */
    Boolean updateByBo(ProductInventoryBo bo);

    /**
     * 校验并批量删除商品库存信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
