package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductInfoBo;
import org.dromara.shopping.domain.vo.ProductInfoVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品拓展Service接口
 *
 * @author yzg
 * @date 2023-05-15
 */
public interface IProductInfoService {

    /**
     * 查询商品拓展
     */
    ProductInfoVo queryById(Long productId);

    /**
     * 查询产品扩展
     */
    ProductInfoVo queryByItemId(String itemId);

    /**
     * 查询商品拓展列表
     */
    TableDataInfo<ProductInfoVo> queryPageList(ProductInfoBo bo, PageQuery pageQuery);

    /**
     * 查询商品拓展列表
     */
    List<ProductInfoVo> queryList(ProductInfoBo bo);

    /**
     * 修改商品拓展
     */
    Boolean insertByBo(ProductInfoBo bo);

    /**
     * 修改商品拓展
     */
    Boolean updateByBo(ProductInfoBo bo);

    /**
     * 校验并批量删除商品拓展信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
