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
import org.dromara.shopping.domain.bo.CategoryPlatformProductBo;
import org.dromara.shopping.domain.vo.CategoryPlatformProductVo;
import org.dromara.shopping.service.ICategoryPlatformProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 多平台栏目商品关联控制器
 * 前端访问路由地址为:/zlyyh/categoryPlatformProduct
 *
 * @author yzg
 * @date 2024-02-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/categoryPlatformProduct")
public class CategoryPlatformProductController extends BaseController {

    private final ICategoryPlatformProductService iCategoryPlatformProductService;

    /**
     * 查询多平台栏目商品关联列表
     */
    @SaCheckPermission("zlyyh:categoryPlatformProduct:list")
    @GetMapping("/list")
    public TableDataInfo<CategoryPlatformProductVo> list(CategoryPlatformProductBo bo, PageQuery pageQuery) {
        return iCategoryPlatformProductService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出多平台栏目商品关联列表
     */
    @SaCheckPermission("zlyyh:categoryPlatformProduct:export")
    @Log(title = "多平台栏目商品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CategoryPlatformProductBo bo, HttpServletResponse response) {
        List<CategoryPlatformProductVo> list = iCategoryPlatformProductService.queryList(bo);
        ExcelUtil.exportExcel(list, "多平台栏目商品关联", CategoryPlatformProductVo.class, response);
    }

    /**
     * 获取多平台栏目商品关联详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:categoryPlatformProduct:query")
    @GetMapping("/{id}")
    public R<CategoryPlatformProductVo> getInfo(@PathVariable Long id) {
        return R.ok(iCategoryPlatformProductService.queryById(id));
    }

    /**
     * 新增多平台栏目商品关联
     */
    @SaCheckPermission("zlyyh:categoryPlatformProduct:add")
    @Log(title = "多平台栏目商品关联", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CategoryPlatformProductBo bo) {
        return toAjax(iCategoryPlatformProductService.insertByBo(bo));
    }

    /**
     * 修改多平台栏目商品关联
     */
    @SaCheckPermission("zlyyh:categoryPlatformProduct:edit")
    @Log(title = "多平台栏目商品关联", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CategoryPlatformProductBo bo) {
        return toAjax(iCategoryPlatformProductService.updateByBo(bo));
    }

    /**
     * 删除多平台栏目商品关联
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:categoryPlatformProduct:remove")
    @Log(title = "多平台栏目商品关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iCategoryPlatformProductService.deleteWithValidByIds(Arrays.asList(ids), true));
    }



    /**
     * 为栏目批量添加商品
     */
    @PostMapping("addProductByCategory")
    public R<Void> addProductByCategory(@RequestBody CategoryPlatformProductBo bo) {
        return toAjax(iCategoryPlatformProductService.addProductByCategoryPlatform(bo));
    }

    /**
     * 为栏目批量删除商品
     */
    @PostMapping("delProductByCategory")
    public R<Void> delProductByCategory(@RequestBody CategoryPlatformProductBo bo) {
        return toAjax(iCategoryPlatformProductService.delProductByCategoryPlatform(bo));
    }
}
