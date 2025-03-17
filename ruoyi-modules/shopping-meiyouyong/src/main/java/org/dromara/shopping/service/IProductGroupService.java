package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductGroupBo;
import org.dromara.shopping.domain.vo.ProductGroupVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品组规则配置Service接口
 *
 * @author yzg
 * @date 2024-01-16
 */
public interface IProductGroupService {

    /**
     * 查询商品组规则配置
     */
    ProductGroupVo queryById(Long productGroupId);

    /**
     * 查询商品组规则配置列表
     */
    TableDataInfo<ProductGroupVo> queryPageList(ProductGroupBo bo, PageQuery pageQuery);

    /**
     * 查询商品组规则配置列表
     */
    List<ProductGroupVo> queryList(ProductGroupBo bo);

    /**
     * 修改商品组规则配置
     */
    Boolean insertByBo(ProductGroupBo bo);

    /**
     * 修改商品组规则配置
     */
    Boolean updateByBo(ProductGroupBo bo);

    /**
     * 修改商品商品组关联表
     *
     * @return
     */
    int updateGroupProduct(List<Long> productIds, Long productGroupId,Integer type);

    /**
     * 校验并批量删除商品组规则配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
