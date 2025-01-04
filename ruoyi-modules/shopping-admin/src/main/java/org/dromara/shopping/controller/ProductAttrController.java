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
import org.dromara.shopping.base.domain.bo.ProductAttrBo;
import org.dromara.shopping.base.domain.vo.ProductAttrVo;
import org.dromara.shopping.service.IProductAttrService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/productAttr")
public class ProductAttrController extends BaseController {

    private final IProductAttrService productAttrService;

    /**
     * 查询商品属性列表
     */
    @SaCheckPermission("shopping:productAttr:list")
    @GetMapping("/list")
    public TableDataInfo<ProductAttrVo> list(ProductAttrBo bo, PageQuery pageQuery) {
        return productAttrService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品属性列表
     */
    @SaCheckPermission("shopping:productAttr:export")
    @Log(title = "商品属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductAttrBo bo, HttpServletResponse response) {
        List<ProductAttrVo> list = productAttrService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品属性", ProductAttrVo.class, response);
    }

    /**
     * 获取商品属性详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("shopping:productAttr:query")
    @GetMapping("/{id}")
    public R<ProductAttrVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(productAttrService.queryById(id));
    }

    /**
     * 新增商品属性
     */
    @SaCheckPermission("shopping:productAttr:add")
    @Log(title = "商品属性", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductAttrBo bo) {
        return toAjax(productAttrService.insertByBo(bo));
    }

    /**
     * 修改商品属性
     */
    @SaCheckPermission("shopping:productAttr:edit")
    @Log(title = "商品属性", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductAttrBo bo) {
        return toAjax(productAttrService.updateByBo(bo));
    }

    /**
     * 删除商品属性
     *
     * @param ids 主键串
     */
    @SaCheckPermission("shopping:productAttr:remove")
    @Log(title = "商品属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(productAttrService.deleteWithValidByIds(List.of(ids), true));
    }
}
