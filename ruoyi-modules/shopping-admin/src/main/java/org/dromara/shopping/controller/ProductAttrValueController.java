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
import org.dromara.shopping.base.domain.bo.ProductAttrValueBo;
import org.dromara.shopping.base.domain.vo.ProductAttrValueVo;
import org.dromara.shopping.service.IProductAttrValueService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性值
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/productAttrValue")
public class ProductAttrValueController extends BaseController {

    private final IProductAttrValueService productAttrValueService;

    /**
     * 查询商品属性值列表
     */
    @SaCheckPermission("shopping:productAttrValue:list")
    @GetMapping("/list")
    public TableDataInfo<ProductAttrValueVo> list(ProductAttrValueBo bo, PageQuery pageQuery) {
        return productAttrValueService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品属性值列表
     */
    @SaCheckPermission("shopping:productAttrValue:export")
    @Log(title = "商品属性值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductAttrValueBo bo, HttpServletResponse response) {
        List<ProductAttrValueVo> list = productAttrValueService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品属性值", ProductAttrValueVo.class, response);
    }

    /**
     * 获取商品属性值详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("shopping:productAttrValue:query")
    @GetMapping("/{id}")
    public R<ProductAttrValueVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(productAttrValueService.queryById(id));
    }

    /**
     * 新增商品属性值
     */
    @SaCheckPermission("shopping:productAttrValue:add")
    @Log(title = "商品属性值", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductAttrValueBo bo) {
        return toAjax(productAttrValueService.insertByBo(bo));
    }

    /**
     * 修改商品属性值
     */
    @SaCheckPermission("shopping:productAttrValue:edit")
    @Log(title = "商品属性值", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductAttrValueBo bo) {
        return toAjax(productAttrValueService.updateByBo(bo));
    }

    /**
     * 删除商品属性值
     *
     * @param ids 主键串
     */
    @SaCheckPermission("shopping:productAttrValue:remove")
    @Log(title = "商品属性值", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(productAttrValueService.deleteWithValidByIds(List.of(ids), true));
    }
}
