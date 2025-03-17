package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SupplierReconciliationBo;
import org.dromara.shopping.domain.vo.SupplierReconciliationDataVo;
import org.dromara.shopping.domain.vo.SupplierReconciliationVo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * 供应商结算Service接口
 *
 * @author yzg
 * @date 2024-12-24
 */
public interface ISupplierReconciliationService {

    /**
     * 查询供应商结算
     */
    SupplierReconciliationVo queryById(Long reconciliationId);

    /**
     * 查询供应商结算列表
     */
    TableDataInfo<SupplierReconciliationVo> queryPageList(SupplierReconciliationBo bo, PageQuery pageQuery);

    TableDataInfo<SupplierReconciliationDataVo> getSupplierOrderData(SupplierReconciliationBo bo, PageQuery pageQuery);

    BigDecimal getSupplierOrderAmount(SupplierReconciliationBo bo);

    /**
     * 查询供应商结算列表
     */
    List<SupplierReconciliationVo> queryList(SupplierReconciliationBo bo);

    /**
     * 修改供应商结算
     */
    Boolean insertByBo(SupplierReconciliationBo bo);

    /**
     * 修改供应商结算
     */
    Boolean updateByBo(SupplierReconciliationBo bo);

    /**
     * 校验并批量删除供应商结算信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
