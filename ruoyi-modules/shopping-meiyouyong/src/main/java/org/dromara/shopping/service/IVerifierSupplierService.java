package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.VerifierSupplierBo;
import org.dromara.shopping.domain.vo.VerifierSupplierVo;

import java.util.Collection;
import java.util.List;

/**
 * 核销员供应商关联Service接口
 *
 * @author yzg
 * @date 2024-03-25
 */
public interface IVerifierSupplierService {

    /**
     * 查询核销员供应商关联
     */
    VerifierSupplierVo queryById(Long id);

    /**
     * 查询核销员供应商关联列表
     */
    TableDataInfo<VerifierSupplierVo> queryPageList(VerifierSupplierBo bo, PageQuery pageQuery);

    /**
     * 查询核销员供应商关联列表
     */
    List<VerifierSupplierVo> queryList(VerifierSupplierBo bo);

    /**
     * 修改核销员供应商关联
     */
    Boolean insertByBo(VerifierSupplierBo bo);

    /**
     * 修改核销员供应商关联
     */
    Boolean updateByBo(VerifierSupplierBo bo);

    /**
     * 校验并批量删除核销员供应商关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
