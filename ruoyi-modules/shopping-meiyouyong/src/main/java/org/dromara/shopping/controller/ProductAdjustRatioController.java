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
import org.dromara.shopping.domain.bo.ProductAdjustRatioBo;
import org.dromara.shopping.domain.vo.ProductAdjustRatioVo;
import org.dromara.shopping.service.IProductAdjustRatioService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商品调价比例控制器
 * 前端访问路由地址为:/zlyyh/productAdjustRatio
 *
 * @author yzg
 * @date 2024-05-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productAdjustRatio")
public class ProductAdjustRatioController extends BaseController {

    private final IProductAdjustRatioService iProductAdjustRatioService;

    /**
     * 查询商品调价比例列表
     */
    @SaCheckPermission("zlyyh:productAdjustRatio:list")
    @GetMapping("/list")
    public TableDataInfo<ProductAdjustRatioVo> list(ProductAdjustRatioBo bo, PageQuery pageQuery) {
        return iProductAdjustRatioService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品调价比例列表
     */
    @SaCheckPermission("zlyyh:productAdjustRatio:export")
    @Log(title = "商品调价比例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductAdjustRatioBo bo, HttpServletResponse response) {
        List<ProductAdjustRatioVo> list = iProductAdjustRatioService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品调价比例", ProductAdjustRatioVo.class, response);
    }

    /**
     * 获取商品调价比例详细信息
     *
     * @param ratioId 主键
     */
    @SaCheckPermission("zlyyh:productAdjustRatio:query")
    @GetMapping("/{ratioId}")
    public R<ProductAdjustRatioVo> getInfo(@PathVariable Long ratioId) {
        return R.ok(iProductAdjustRatioService.queryById(ratioId));
    }

    /**
     * 新增商品调价比例
     */
    @SaCheckPermission("zlyyh:productAdjustRatio:add")
    @Log(title = "商品调价比例", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductAdjustRatioBo bo) {
        return toAjax(iProductAdjustRatioService.insertByBo(bo));
    }

    /**
     * 修改商品调价比例
     */
    @SaCheckPermission("zlyyh:productAdjustRatio:edit")
    @Log(title = "商品调价比例", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductAdjustRatioBo bo) {
        return toAjax(iProductAdjustRatioService.updateByBo(bo));
    }

    /**
     * 删除商品调价比例
     *
     * @param ratioIds 主键串
     */
    @SaCheckPermission("zlyyh:productAdjustRatio:remove")
    @Log(title = "商品调价比例", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ratioIds}")
    public R<Void> remove(@PathVariable Long[] ratioIds) {
        return toAjax(iProductAdjustRatioService.deleteWithValidByIds(Arrays.asList(ratioIds), true));
    }
}
