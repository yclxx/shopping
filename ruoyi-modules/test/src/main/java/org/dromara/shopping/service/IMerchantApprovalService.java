package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MerchantApprovalBo;
import org.dromara.shopping.domain.vo.MerchantApprovalVo;

import java.util.Collection;
import java.util.List;

/**
 * 商户申请审批Service接口
 *
 * @author yzg
 * @date 2023-10-19
 */
public interface IMerchantApprovalService {

    /**
     * 查询商户申请审批
     */
    MerchantApprovalVo queryById(Long approvalId);

    /**
     * 查询商户申请审批列表
     */
    TableDataInfo<MerchantApprovalVo> queryPageList(MerchantApprovalBo bo, PageQuery pageQuery);

    /**
     * 查询商户申请审批列表
     */
    List<MerchantApprovalVo> queryList(MerchantApprovalBo bo);

    /**
     * 修改商户申请审批
     */
    Boolean insertByBo(MerchantApprovalBo bo);

    /**
     * 修改商户申请审批
     */
    Boolean updateByBo(MerchantApprovalBo bo);

    /**
     * 校验并批量删除商户申请审批信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
