package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SupplierBo;
import org.dromara.shopping.domain.vo.SupplierVo;

import java.util.Collection;
import java.util.List;

/**
 * 供应商Service接口
 *
 * @author yzg
 * @date 2023-10-11
 */
public interface ISupplierService {

    /**
     * 查询供应商
     */
    SupplierVo queryById(Long supplierId);

    /**
     * 查询供应商列表
     */
    TableDataInfo<SupplierVo> queryPageList(SupplierBo bo, PageQuery pageQuery);

    /**
     * 查询供应商列表
     */
    List<SupplierVo> queryList(SupplierBo bo);

    /**
     * 修改供应商
     */
    Boolean insertByBo(SupplierBo bo);

    /**
     * 修改供应商
     */
    Boolean updateByBo(SupplierBo bo);

    /**
     * 校验并批量删除供应商信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
