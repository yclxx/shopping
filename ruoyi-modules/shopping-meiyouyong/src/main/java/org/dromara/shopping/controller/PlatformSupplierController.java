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
import org.dromara.shopping.domain.bo.PlatformSupplierBo;
import org.dromara.shopping.domain.vo.PlatformSupplierVo;
import org.dromara.shopping.service.IPlatformSupplierService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台供应商控制器
 * 前端访问路由地址为:/zlyyh/platformSupplier
 *
 * @author yzg
 * @date 2025-01-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformSupplier")
public class PlatformSupplierController extends BaseController {

    private final IPlatformSupplierService iPlatformSupplierService;

    /**
     * 查询平台供应商列表
     */
    @SaCheckPermission("zlyyh:platformSupplier:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformSupplierVo> list(PlatformSupplierBo bo, PageQuery pageQuery) {
        return iPlatformSupplierService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台供应商列表
     */
    @SaCheckPermission("zlyyh:platformSupplier:export")
    @Log(title = "平台供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformSupplierBo bo, HttpServletResponse response) {
        List<PlatformSupplierVo> list = iPlatformSupplierService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台供应商", PlatformSupplierVo.class, response);
    }

    /**
     * 获取平台供应商详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformSupplier:query")
    @GetMapping("/{id}")
    public R<PlatformSupplierVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformSupplierService.queryById(id));
    }

    /**
     * 新增平台供应商
     */
    @SaCheckPermission("zlyyh:platformSupplier:add")
    @Log(title = "平台供应商", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformSupplierBo bo) {
        return toAjax(iPlatformSupplierService.insertByBo(bo));
    }

    /**
     * 修改平台供应商
     */
    @SaCheckPermission("zlyyh:platformSupplier:edit")
    @Log(title = "平台供应商", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformSupplierBo bo) {
        return toAjax(iPlatformSupplierService.updateByBo(bo));
    }

    /**
     * 删除平台供应商
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformSupplier:remove")
    @Log(title = "平台供应商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformSupplierService.deleteWithValidByIds(Arrays.asList(ids), true));
    }

}
