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
import org.dromara.shopping.domain.bo.ProductPackageBo;
import org.dromara.shopping.domain.vo.ProductPackageVo;
import org.dromara.shopping.service.IProductPackageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商品券包控制器
 * 前端访问路由地址为:/zlyyh/productPackage
 *
 * @author yzg
 * @date 2023-06-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productPackage")
public class ProductPackageController extends BaseController {

    private final IProductPackageService iProductPackageService;

    /**
     * 查询商品券包列表
     */
    @SaCheckPermission("zlyyh:productPackage:list")
    @GetMapping("/list")
    public TableDataInfo<ProductPackageVo> list(ProductPackageBo bo, PageQuery pageQuery) {
        return iProductPackageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品券包列表
     */
    @SaCheckPermission("zlyyh:productPackage:export")
    @Log(title = "商品券包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductPackageBo bo, HttpServletResponse response) {
        List<ProductPackageVo> list = iProductPackageService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品券包", ProductPackageVo.class, response);
    }

    /**
     * 获取商品券包详细信息
     *
     * @param packageId 主键
     */
    @SaCheckPermission("zlyyh:productPackage:query")
    @GetMapping("/{packageId}")
    public R<ProductPackageVo> getInfo(@PathVariable Long packageId) {
        return R.ok(iProductPackageService.queryById(packageId));
    }

    /**
     * 新增商品券包
     */
    @SaCheckPermission("zlyyh:productPackage:add")
    @Log(title = "商品券包", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductPackageBo bo) {
        return toAjax(iProductPackageService.insertByBo(bo));
    }

    /**
     * 修改商品券包
     */
    @SaCheckPermission("zlyyh:productPackage:edit")
    @Log(title = "商品券包", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductPackageBo bo) {
        return toAjax(iProductPackageService.updateByBo(bo));
    }

    /**
     * 删除商品券包
     *
     * @param packageIds 主键串
     */
    @SaCheckPermission("zlyyh:productPackage:remove")
    @Log(title = "商品券包", businessType = BusinessType.DELETE)
    @DeleteMapping("/{packageIds}")
    public R<Void> remove(@PathVariable Long[] packageIds) {
        return toAjax(iProductPackageService.deleteWithValidByIds(Arrays.asList(packageIds), true));
    }
}
