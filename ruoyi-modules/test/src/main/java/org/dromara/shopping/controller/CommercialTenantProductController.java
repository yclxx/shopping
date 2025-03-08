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
import org.dromara.shopping.domain.bo.CommercialTenantProductBo;
import org.dromara.shopping.domain.vo.CommercialTenantProductVo;
import org.dromara.shopping.service.ICommercialTenantProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户商品配置控制器
 * 前端访问路由地址为:/zlyyh/commercialTenantProduct
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/commercialTenantProduct")
public class CommercialTenantProductController extends BaseController {

    private final ICommercialTenantProductService iCommercialTenantProductService;

    /**
     * 查询商户商品配置列表
     */
    @SaCheckPermission("zlyyh:commercialTenantProduct:list")
    @GetMapping("/list")
    public TableDataInfo<CommercialTenantProductVo> list(CommercialTenantProductBo bo, PageQuery pageQuery) {
        return iCommercialTenantProductService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商户商品配置列表
     */
    @SaCheckPermission("zlyyh:commercialTenantProduct:export")
    @Log(title = "商户商品配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommercialTenantProductBo bo, HttpServletResponse response) {
        List<CommercialTenantProductVo> list = iCommercialTenantProductService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户商品配置", CommercialTenantProductVo.class, response);
    }

    /**
     * 获取商户商品配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:commercialTenantProduct:query")
    @GetMapping("/{id}")
    public R<CommercialTenantProductVo> getInfo(@PathVariable Long id) {
        return R.ok(iCommercialTenantProductService.queryById(id));
    }

    /**
     * 新增商户商品配置
     */
    @SaCheckPermission("zlyyh:commercialTenantProduct:add")
    @Log(title = "商户商品配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CommercialTenantProductBo bo) {
        return toAjax(iCommercialTenantProductService.insertByBo(bo));
    }

    /**
     * 修改商户商品配置
     */
    @SaCheckPermission("zlyyh:commercialTenantProduct:edit")
    @Log(title = "商户商品配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CommercialTenantProductBo bo) {
        return toAjax(iCommercialTenantProductService.updateByBo(bo));
    }

    /**
     * 删除商户商品配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:commercialTenantProduct:remove")
    @Log(title = "商户商品配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iCommercialTenantProductService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
