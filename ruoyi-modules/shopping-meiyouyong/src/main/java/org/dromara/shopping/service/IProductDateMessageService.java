package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductDateMessageBo;
import org.dromara.shopping.domain.vo.ProductDateMessageVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品（旅游相关）每日价格库存Service接口
 *
 * @author yzg
 * @date 2024-06-19
 */
public interface IProductDateMessageService {

    /**
     * 查询商品（旅游相关）每日价格库存
     */
    ProductDateMessageVo queryById(Long id);

    /**
     * 查询商品（旅游相关）每日价格库存列表
     */
    TableDataInfo<ProductDateMessageVo> queryPageList(ProductDateMessageBo bo, PageQuery pageQuery);

    /**
     * 查询商品（旅游相关）每日价格库存列表
     */
    List<ProductDateMessageVo> queryList(ProductDateMessageBo bo);

    /**
     * 修改商品（旅游相关）每日价格库存
     */
    Boolean insertByBo(ProductDateMessageBo bo);

    /**
     * 修改商品（旅游相关）每日价格库存
     */
    Boolean updateByBo(ProductDateMessageBo bo);

    /**
     * 校验并批量删除商品（旅游相关）每日价格库存信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    ProductDateMessageVo queryByIdAndDate(String exProductId,String date);
}
