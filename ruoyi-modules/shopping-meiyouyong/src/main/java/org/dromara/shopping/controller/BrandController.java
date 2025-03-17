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
import org.dromara.shopping.domain.bo.BrandBo;
import org.dromara.shopping.domain.vo.BrandVo;
import org.dromara.shopping.service.IBrandService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 品牌管理控制器
 * 前端访问路由地址为:/zlyyh/brand
 *
 * @author yzg
 * @date 2023-12-29
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController {

    private final IBrandService iBrandService;

    /**
     * 查询品牌管理列表
     */
    @SaCheckPermission("zlyyh:brand:list")
    @GetMapping("/list")
    public TableDataInfo<BrandVo> list(BrandBo bo, PageQuery pageQuery) {
        return iBrandService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出品牌管理列表
     */
    @SaCheckPermission("zlyyh:brand:export")
    @Log(title = "品牌管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BrandBo bo, HttpServletResponse response) {
        List<BrandVo> list = iBrandService.queryList(bo);
        ExcelUtil.exportExcel(list, "品牌管理", BrandVo.class, response);
    }

    /**
     * 获取品牌管理详细信息
     *
     * @param brandId 主键
     */
    @SaCheckPermission("zlyyh:brand:query")
    @GetMapping("/{brandId}")
    public R<BrandVo> getInfo(@PathVariable Long brandId) {
        return R.ok(iBrandService.queryById(brandId));
    }

    /**
     * 新增品牌管理
     */
    @SaCheckPermission("zlyyh:brand:add")
    @Log(title = "品牌管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BrandBo bo) {
        return toAjax(iBrandService.insertByBo(bo));
    }

    /**
     * 修改品牌管理
     */
    @SaCheckPermission("zlyyh:brand:edit")
    @Log(title = "品牌管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BrandBo bo) {
        return toAjax(iBrandService.updateByBo(bo));
    }

    /**
     * 删除品牌管理
     *
     * @param brandIds 主键串
     */
    @SaCheckPermission("zlyyh:brand:remove")
    @Log(title = "品牌管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{brandIds}")
    public R<Void> remove(@PathVariable Long[] brandIds) {
        return toAjax(iBrandService.deleteWithValidByIds(Arrays.asList(brandIds), true));
    }
}
