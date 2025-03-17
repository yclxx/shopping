package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CommercialTenantAccountBo;
import org.dromara.shopping.domain.vo.CommercialTenantAccountVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户账户Service接口
 *
 * @author yzg
 * @date 2024-09-13
 */
public interface ICommercialTenantAccountService {

    /**
     * 查询商户账户
     */
    CommercialTenantAccountVo queryById(Long commercialTenantId);

    /**
     * 查询商户账户列表
     */
    TableDataInfo<CommercialTenantAccountVo> queryPageList(CommercialTenantAccountBo bo, PageQuery pageQuery);

    /**
     * 查询商户账户列表
     */
    List<CommercialTenantAccountVo> queryList(CommercialTenantAccountBo bo);

    /**
     * 修改商户账户
     */
    Boolean insertByBo(CommercialTenantAccountBo bo);

    /**
     * 修改商户账户
     */
    Boolean updateByBo(CommercialTenantAccountBo bo);

    /**
     * 校验并批量删除商户账户信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
