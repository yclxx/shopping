package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.MerchantApprovalBo;
import org.dromara.shopping.domain.vo.MerchantApprovalVo;
import org.dromara.shopping.service.IMerchantApprovalService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * 商户申请审批控制器
 * 前端访问路由地址为:/zlyyh/merchantApproval
 *
 * @author yzg
 * @date 2023-10-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/merchantApproval")
public class MerchantApprovalController extends BaseController {

    private final IMerchantApprovalService iMerchantApprovalService;

    /**
     * 查询商户申请审批列表
     */
    @SaCheckPermission("zlyyh:merchantApproval:list")
    @GetMapping("/list")
    public TableDataInfo<MerchantApprovalVo> list(MerchantApprovalBo bo, PageQuery pageQuery) {
        return iMerchantApprovalService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取商户申请审批详细信息
     *
     * @param approvalId 主键
     */
    @SaCheckPermission("zlyyh:merchantApproval:query")
    @GetMapping("/{approvalId}")
    public R<MerchantApprovalVo> getInfo(@PathVariable Long approvalId) {
        return R.ok(iMerchantApprovalService.queryById(approvalId));
    }

    /**
     * 修改商户申请审批
     */
    @SaCheckPermission("zlyyh:merchantApproval:edit")
    @Log(title = "商户申请审批", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@RequestBody MerchantApprovalBo bo) {
        return toAjax(iMerchantApprovalService.updateByBo(bo));
    }
}
