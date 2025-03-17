package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SupplierBrandBo;
import org.dromara.shopping.domain.vo.SupplierBrandVo;

import java.util.Collection;
import java.util.List;

/**
 * 供应商品牌Service接口
 *
 * @author yzg
 * @date 2024-12-26
 */
public interface ISupplierBrandService {

    /**
     * 查询供应商品牌
     */
    SupplierBrandVo queryById(Long brandId);

    /**
     * 查询供应商品牌列表
     */
    TableDataInfo<SupplierBrandVo> queryPageList(SupplierBrandBo bo, PageQuery pageQuery);

    /**
     * 查询供应商品牌列表
     */
    List<SupplierBrandVo> queryList(SupplierBrandBo bo);

    /**
     * 修改供应商品牌
     */
    Boolean insertByBo(SupplierBrandBo bo);

    /**
     * 修改供应商品牌
     */
    Boolean updateByBo(SupplierBrandBo bo);

    /**
     * 校验并批量删除供应商品牌信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
