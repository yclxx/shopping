package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CommercialTenantAccountDetailBo;
import org.dromara.shopping.domain.vo.CommercialTenantAccountDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户账户明细Service接口
 *
 * @author yzg
 * @date 2024-09-13
 */
public interface ICommercialTenantAccountDetailService {

    /**
     * 查询商户账户明细
     */
    CommercialTenantAccountDetailVo queryById(Long id);

    /**
     * 查询商户账户明细列表
     */
    TableDataInfo<CommercialTenantAccountDetailVo> queryPageList(CommercialTenantAccountDetailBo bo, PageQuery pageQuery);

    /**
     * 查询商户账户明细列表
     */
    List<CommercialTenantAccountDetailVo> queryList(CommercialTenantAccountDetailBo bo);

    /**
     * 修改商户账户明细
     */
    Boolean insertByBo(CommercialTenantAccountDetailBo bo);

    /**
     * 修改商户账户明细
     */
    Boolean updateByBo(CommercialTenantAccountDetailBo bo);

    /**
     * 校验并批量删除商户账户明细信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
