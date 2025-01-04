package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.base.domain.bo.ProductBo;
import org.dromara.shopping.base.domain.vo.ProductVo;
import org.dromara.shopping.service.IProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品信息
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/product")
public class ProductController extends BaseController {

    private final IProductService productService;

    /**
     * 查询商品信息列表
     */
    @SaCheckPermission("shopping:product:list")
    @GetMapping("/list")
    public TableDataInfo<ProductVo> list(ProductBo bo, PageQuery pageQuery) {
        return productService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品信息列表
     */
    @SaCheckPermission("shopping:product:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductBo bo, HttpServletResponse response) {
        List<ProductVo> list = productService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品信息", ProductVo.class, response);
    }

    /**
     * 获取商品信息详细信息
     *
     * @param productId 主键
     */
    @SaCheckPermission("shopping:product:query")
    @GetMapping("/{productId}")
    public R<ProductVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long productId) {
        return R.ok(productService.queryById(productId));
    }

    /**
     * 新增商品信息
     */
    @SaCheckPermission("shopping:product:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductBo bo) {
        return toAjax(productService.insertByBo(bo));
    }

    /**
     * 修改商品信息
     */
    @SaCheckPermission("shopping:product:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductBo bo) {
        return toAjax(productService.updateByBo(bo));
    }

    /**
     * 删除商品信息
     *
     * @param productIds 主键串
     */
    @SaCheckPermission("shopping:product:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] productIds) {
        return toAjax(productService.deleteWithValidByIds(List.of(productIds), true));
    }
}
