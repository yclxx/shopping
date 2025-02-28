package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductAdjustRatioBo;
import org.dromara.shopping.domain.vo.ProductAdjustRatioVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品调价比例Service接口
 *
 * @author yzg
 * @date 2024-05-30
 */
public interface IProductAdjustRatioService {

    /**
     * 查询商品调价比例
     */
    ProductAdjustRatioVo queryById(Long ratioId);

    /**
     * 查询商品调价比例列表
     */
    TableDataInfo<ProductAdjustRatioVo> queryPageList(ProductAdjustRatioBo bo, PageQuery pageQuery);

    /**
     * 查询商品调价比例列表
     */
    List<ProductAdjustRatioVo> queryList(ProductAdjustRatioBo bo);

    /**
     * 修改商品调价比例
     */
    Boolean insertByBo(ProductAdjustRatioBo bo);

    /**
     * 修改商品调价比例
     */
    Boolean updateByBo(ProductAdjustRatioBo bo);

    /**
     * 校验并批量删除商品调价比例信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
