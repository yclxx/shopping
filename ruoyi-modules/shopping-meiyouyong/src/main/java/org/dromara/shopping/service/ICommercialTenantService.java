package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CommercialTenantBo;
import org.dromara.shopping.domain.vo.CommercialTenantVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface ICommercialTenantService {

    /**
     * 查询商户
     */
    CommercialTenantVo queryById(Long commercialTenantId);

    /**
     * 查询商户
     */
    CommercialTenantVo queryByYlBrandId(String ylBrandId);
    /**
     * 查询商户
     */
    CommercialTenantVo queryByCommercialTenantName(String tenantName);

    /**
     * 查询商户
     */
    CommercialTenantVo queryByBrandId(Long brandId);

    /**
     * 查询商户列表
     */
    TableDataInfo<CommercialTenantVo> queryPageList(CommercialTenantBo bo, PageQuery pageQuery);

    /**
     * 查询商户列表
     */
    List<CommercialTenantVo> queryList(CommercialTenantBo bo);

    /**
     * 修改商户
     */
    Boolean insertByBo(CommercialTenantBo bo);

    /**
     * 修改商户
     */
    Boolean updateByBo(CommercialTenantBo bo);

    /**
     * 校验并批量删除商户信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    TableDataInfo<CommercialTenantVo> queryPageCategoryCommercialList(CommercialTenantBo bo, PageQuery pageQuery);
}
