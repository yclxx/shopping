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
import org.dromara.shopping.domain.bo.CategoryPlatformBo;
import org.dromara.shopping.domain.vo.CategoryPlatformVo;
import org.dromara.shopping.service.ICategoryPlatformService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 多平台类别控制器
 * 前端访问路由地址为:/zlyyh/categoryPlatform
 *
 * @author yzg
 * @date 2024-02-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/categoryPlatform")
public class CategoryPlatformController extends BaseController {

    private final ICategoryPlatformService iCategoryPlatformService;

    /**
     * 查询多平台类别列表
     */
    @SaCheckPermission("zlyyh:categoryPlatform:list")
    @GetMapping("/list")
    public TableDataInfo<CategoryPlatformVo> list(CategoryPlatformBo bo, PageQuery pageQuery) {
        return iCategoryPlatformService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询多平台类别列表
     */
    @GetMapping("/listAll")
    public R<List<CategoryPlatformVo>> listAll(CategoryPlatformBo bo) {
        return R.ok(iCategoryPlatformService.queryList(bo));
    }

    /**
     * 导出多平台类别列表
     */
    @SaCheckPermission("zlyyh:categoryPlatform:export")
    @Log(title = "多平台类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CategoryPlatformBo bo, HttpServletResponse response) {
        List<CategoryPlatformVo> list = iCategoryPlatformService.queryList(bo);
        ExcelUtil.exportExcel(list, "多平台类别", CategoryPlatformVo.class, response);
    }

    /**
     * 获取多平台类别详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:categoryPlatform:query")
    @GetMapping("/{id}")
    public R<CategoryPlatformVo> getInfo(@PathVariable Long id) {
        return R.ok(iCategoryPlatformService.queryById(id));
    }

    /**
     * 新增多平台类别
     */
    @SaCheckPermission("zlyyh:categoryPlatform:add")
    @Log(title = "多平台类别", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CategoryPlatformBo bo) {
        return toAjax(iCategoryPlatformService.insertByBo(bo));
    }

    /**
     * 修改多平台类别
     */
    @SaCheckPermission("zlyyh:categoryPlatform:edit")
    @Log(title = "多平台类别", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CategoryPlatformBo bo) {
        return toAjax(iCategoryPlatformService.updateByBo(bo));
    }

    /**
     * 删除多平台类别
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:categoryPlatform:remove")
    @Log(title = "多平台类别", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iCategoryPlatformService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
