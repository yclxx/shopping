package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductAdjustPriceBo;
import org.dromara.shopping.domain.vo.ProductAdjustPriceVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品调价Service接口
 *
 * @author yzg
 * @date 2024-05-29
 */
public interface IProductAdjustPriceService {

    /**
     * 查询商品调价
     */
    ProductAdjustPriceVo queryById(Long id);

    /**
     * 查询商品调价列表
     */
    TableDataInfo<ProductAdjustPriceVo> queryPageList(ProductAdjustPriceBo bo, PageQuery pageQuery);

    /**
     * 查询商品调价列表
     */
    List<ProductAdjustPriceVo> queryList(ProductAdjustPriceBo bo);

    /**
     * 修改商品调价
     */
    Boolean insertByBo(ProductAdjustPriceBo bo);

    /**
     * 修改商品调价
     */
    Boolean updateByBo(ProductAdjustPriceBo bo);

    /**
     * 校验并批量删除商品调价信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
