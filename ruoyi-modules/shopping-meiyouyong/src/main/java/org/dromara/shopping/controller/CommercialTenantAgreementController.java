package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.CommercialTenantAgreementBo;
import org.dromara.shopping.domain.vo.CommercialTenantAgreementVo;
import org.dromara.shopping.service.ICommercialTenantAgreementService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户合同控制器
 * 前端访问路由地址为:/zlyyh/commercialTenantAgreement
 *
 * @author yzg
 * @date 2024-09-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/commercialTenantAgreement")
public class CommercialTenantAgreementController extends BaseController {

    private final ICommercialTenantAgreementService iCommercialTenantAgreementService;

    /**
     * 查询商户合同列表
     */
    @SaCheckPermission("zlyyh:commercialTenantAgreement:list")
    @GetMapping("/list")
    public TableDataInfo<CommercialTenantAgreementVo> list(CommercialTenantAgreementBo bo, PageQuery pageQuery) {
        return iCommercialTenantAgreementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商户合同列表
     */
    @SaCheckPermission("zlyyh:commercialTenantAgreement:export")
    @Log(title = "商户合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommercialTenantAgreementBo bo, HttpServletResponse response) {
        List<CommercialTenantAgreementVo> list = iCommercialTenantAgreementService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户合同", CommercialTenantAgreementVo.class, response);
    }

    /**
     * 获取商户合同详细信息
     *
     * @param agreementId 主键
     */
    @SaCheckPermission("zlyyh:commercialTenantAgreement:query")
    @GetMapping("/{agreementId}")
    public R<CommercialTenantAgreementVo> getInfo(@PathVariable Long agreementId) {
        return R.ok(iCommercialTenantAgreementService.queryById(agreementId));
    }

    /**
     * 新增商户合同
     */
    @SaCheckPermission("zlyyh:commercialTenantAgreement:add")
    @Log(title = "商户合同", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CommercialTenantAgreementBo bo) {
        return toAjax(iCommercialTenantAgreementService.insertByBo(bo));
    }

    /**
     * 修改商户合同
     */
    @SaCheckPermission("zlyyh:commercialTenantAgreement:edit")
    @Log(title = "商户合同", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CommercialTenantAgreementBo bo) {
        return toAjax(iCommercialTenantAgreementService.updateByBo(bo));
    }

    /**
     * 删除商户合同
     *
     * @param agreementIds 主键串
     */
    @SaCheckPermission("zlyyh:commercialTenantAgreement:remove")
    @Log(title = "商户合同", businessType = BusinessType.DELETE)
    @DeleteMapping("/{agreementIds}")
    public R<Void> remove(@PathVariable Long[] agreementIds) {
        return toAjax(iCommercialTenantAgreementService.deleteWithValidByIds(Arrays.asList(agreementIds), true));
    }
}
