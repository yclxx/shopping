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
import org.dromara.shopping.domain.bo.CommercialTenantBo;
import org.dromara.shopping.domain.vo.CommercialTenantAccountVo;
import org.dromara.shopping.domain.vo.CommercialTenantVo;
import org.dromara.shopping.service.ICommercialTenantAccountService;
import org.dromara.shopping.service.ICommercialTenantService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户控制器
 * 前端访问路由地址为:/zlyyh/commercialTenant
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/commercialTenant")
public class CommercialTenantController extends BaseController {

    private final ICommercialTenantService iCommercialTenantService;
    private final ICommercialTenantAccountService iCommercialTenantAccountService;

    /**
     * 查询商户列表
     */
    @SaCheckPermission("zlyyh:commercialTenant:list")
    @GetMapping("/list")
    public TableDataInfo<CommercialTenantVo> list(CommercialTenantBo bo, PageQuery pageQuery) {
        TableDataInfo<CommercialTenantVo> commercialTenantVoTableDataInfo = iCommercialTenantService.queryPageList(bo, pageQuery);
        for (CommercialTenantVo row : commercialTenantVoTableDataInfo.getRows()) {
            CommercialTenantAccountVo commercialTenantAccountVo = iCommercialTenantAccountService.queryById(row.getCommercialTenantId());

            row.setAccountVo(commercialTenantAccountVo);
        }
        return commercialTenantVoTableDataInfo;
    }

    /**
     * 查询商品列表
     */
    @SaCheckPermission("zlyyh:commercialTenant:list")
    @GetMapping("/categoryCommercialList")
    public TableDataInfo<CommercialTenantVo> categoryCommercialList(CommercialTenantBo bo, PageQuery pageQuery) {
        return iCommercialTenantService.queryPageCategoryCommercialList(bo, pageQuery);
    }


    /**
     * 导出商户列表
     */
    @SaCheckPermission("zlyyh:commercialTenant:export")
    @Log(title = "商户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommercialTenantBo bo, HttpServletResponse response) {
        List<CommercialTenantVo> list = iCommercialTenantService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户", CommercialTenantVo.class, response);
    }

    /**
     * 获取商户详细信息
     *
     * @param commercialTenantId 主键
     */
    @SaCheckPermission("zlyyh:commercialTenant:query")
    @GetMapping("/{commercialTenantId}")
    public R<CommercialTenantVo> getInfo(@PathVariable Long commercialTenantId) {
        return R.ok(iCommercialTenantService.queryById(commercialTenantId));
    }

    /**
     * 新增商户
     */
    @SaCheckPermission("zlyyh:commercialTenant:add")
    @Log(title = "商户", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CommercialTenantBo bo) {
        return toAjax(iCommercialTenantService.insertByBo(bo));
    }

    /**
     * 修改商户
     */
    @SaCheckPermission("zlyyh:commercialTenant:edit")
    @Log(title = "商户", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CommercialTenantBo bo) {
        return toAjax(iCommercialTenantService.updateByBo(bo));
    }

    /**
     * 删除商户
     *
     * @param commercialTenantIds 主键串
     */
    @SaCheckPermission("zlyyh:commercialTenant:remove")
    @Log(title = "商户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commercialTenantIds}")
    public R<Void> remove(@PathVariable Long[] commercialTenantIds) {
        return toAjax(iCommercialTenantService.deleteWithValidByIds(Arrays.asList(commercialTenantIds), true));
    }
}
