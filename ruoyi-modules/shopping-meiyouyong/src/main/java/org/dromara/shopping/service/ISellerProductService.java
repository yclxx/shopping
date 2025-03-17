package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SellerProductBo;
import org.dromara.shopping.domain.vo.SellerProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 商品商铺关联Service接口
 *
 * @author yzg
 * @date 2024-10-21
 */
public interface ISellerProductService {

    /**
     * 查询商品商铺关联
     */
    SellerProductVo queryById(Long id);

    /**
     * 查询商品商铺关联列表
     */
    TableDataInfo<SellerProductVo> queryPageList(SellerProductBo bo, PageQuery pageQuery);

    /**
     * 查询商品商铺关联列表
     */
    List<SellerProductVo> queryList(SellerProductBo bo);

    /**
     * 修改商品商铺关联
     */
    Boolean insertByBo(SellerProductBo bo);

    /**
     * 修改商品商铺关联
     */
    Boolean updateByBo(SellerProductBo bo);

    /**
     * 校验并批量删除商品商铺关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
