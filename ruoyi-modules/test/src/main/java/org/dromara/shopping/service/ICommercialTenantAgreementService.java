package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CommercialTenantAgreementBo;
import org.dromara.shopping.domain.vo.CommercialTenantAgreementVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户合同Service接口
 *
 * @author yzg
 * @date 2024-09-10
 */
public interface ICommercialTenantAgreementService {

    /**
     * 查询商户合同
     */
    CommercialTenantAgreementVo queryById(Long agreementId);

    /**
     * 查询商户合同列表
     */
    TableDataInfo<CommercialTenantAgreementVo> queryPageList(CommercialTenantAgreementBo bo, PageQuery pageQuery);

    /**
     * 查询商户合同列表
     */
    List<CommercialTenantAgreementVo> queryList(CommercialTenantAgreementBo bo);

    /**
     * 修改商户合同
     */
    Boolean insertByBo(CommercialTenantAgreementBo bo);

    /**
     * 修改商户合同
     */
    Boolean updateByBo(CommercialTenantAgreementBo bo);

    /**
     * 校验并批量删除商户合同信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
