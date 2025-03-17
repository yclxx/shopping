package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.CommercialTenantAccountBo;
import org.dromara.shopping.domain.vo.CommercialTenantAccountVo;
import org.dromara.shopping.service.ICommercialTenantAccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户账户控制器
 * 前端访问路由地址为:/zlyyh/commercialTenantAccount
 *
 * @author yzg
 * @date 2024-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/commercialTenantAccount")
public class CommercialTenantAccountController extends BaseController {

    private final ICommercialTenantAccountService iCommercialTenantAccountService;

    /**
     * 查询商户账户列表
     */
    @SaCheckPermission("zlyyh:commercialTenantAccount:list")
    @GetMapping("/list")
    public TableDataInfo<CommercialTenantAccountVo> list(CommercialTenantAccountBo bo, PageQuery pageQuery) {
        return iCommercialTenantAccountService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商户账户列表
     */
    @SaCheckPermission("zlyyh:commercialTenantAccount:export")
    @Log(title = "商户账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommercialTenantAccountBo bo, HttpServletResponse response) {
        List<CommercialTenantAccountVo> list = iCommercialTenantAccountService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户账户", CommercialTenantAccountVo.class, response);
    }

    /**
     * 获取商户账户详细信息
     *
     * @param commercialTenantId 主键
     */
    @SaCheckPermission("zlyyh:commercialTenantAccount:query")
    @GetMapping("/{commercialTenantId}")
    public R<CommercialTenantAccountVo> getInfo(@PathVariable Long commercialTenantId) {
        return R.ok(iCommercialTenantAccountService.queryById(commercialTenantId));
    }

    /**
     * 新增商户账户
     */
    @SaCheckPermission("zlyyh:commercialTenantAccount:add")
    @Log(title = "商户账户", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@RequestBody CommercialTenantAccountBo bo) {
        return toAjax(iCommercialTenantAccountService.insertByBo(bo));
    }

    /**
     * 修改商户账户
     */
    @SaCheckPermission("zlyyh:commercialTenantAccount:edit")
    @Log(title = "商户账户", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@RequestBody CommercialTenantAccountBo bo) {
        // 只能更改状态
        CommercialTenantAccountBo updateBo = new CommercialTenantAccountBo();
        updateBo.setCommercialTenantId(bo.getCommercialTenantId());
        updateBo.setStatus(bo.getStatus());
        return toAjax(iCommercialTenantAccountService.updateByBo(updateBo));
    }

    /**
     * 删除商户账户
     *
     * @param commercialTenantIds 主键串
     */
    @SaCheckPermission("zlyyh:commercialTenantAccount:remove")
    @Log(title = "商户账户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commercialTenantIds}")
    public R<Void> remove(@PathVariable Long[] commercialTenantIds) {
        return toAjax(iCommercialTenantAccountService.deleteWithValidByIds(Arrays.asList(commercialTenantIds), true));
    }
}
