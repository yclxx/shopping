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
import org.dromara.shopping.base.domain.bo.ProductImgBo;
import org.dromara.shopping.base.domain.vo.ProductImgVo;
import org.dromara.shopping.service.IProductImgService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品图片
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/productImg")
public class ProductImgController extends BaseController {

    private final IProductImgService productImgService;

    /**
     * 查询商品图片列表
     */
    @SaCheckPermission("shopping:productImg:list")
    @GetMapping("/list")
    public TableDataInfo<ProductImgVo> list(ProductImgBo bo, PageQuery pageQuery) {
        return productImgService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品图片列表
     */
    @SaCheckPermission("shopping:productImg:export")
    @Log(title = "商品图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductImgBo bo, HttpServletResponse response) {
        List<ProductImgVo> list = productImgService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品图片", ProductImgVo.class, response);
    }

    /**
     * 获取商品图片详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("shopping:productImg:query")
    @GetMapping("/{id}")
    public R<ProductImgVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(productImgService.queryById(id));
    }

    /**
     * 新增商品图片
     */
    @SaCheckPermission("shopping:productImg:add")
    @Log(title = "商品图片", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductImgBo bo) {
        return toAjax(productImgService.insertByBo(bo));
    }

    /**
     * 修改商品图片
     */
    @SaCheckPermission("shopping:productImg:edit")
    @Log(title = "商品图片", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductImgBo bo) {
        return toAjax(productImgService.updateByBo(bo));
    }

    /**
     * 删除商品图片
     *
     * @param ids 主键串
     */
    @SaCheckPermission("shopping:productImg:remove")
    @Log(title = "商品图片", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(productImgService.deleteWithValidByIds(List.of(ids), true));
    }
}
