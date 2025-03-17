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
import org.dromara.shopping.domain.bo.ProductAdjustPriceBo;
import org.dromara.shopping.domain.vo.ProductAdjustPriceVo;
import org.dromara.shopping.service.IProductAdjustPriceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商品调价控制器
 * 前端访问路由地址为:/zlyyh/productAdjustPrice
 *
 * @author yzg
 * @date 2024-05-29
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productAdjustPrice")
public class ProductAdjustPriceController extends BaseController {

    private final IProductAdjustPriceService iProductAdjustPriceService;

    /**
     * 查询商品调价列表
     */
    @SaCheckPermission("zlyyh:productAdjustPrice:list")
    @GetMapping("/list")
    public TableDataInfo<ProductAdjustPriceVo> list(ProductAdjustPriceBo bo, PageQuery pageQuery) {
        return iProductAdjustPriceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品调价列表
     */
    @SaCheckPermission("zlyyh:productAdjustPrice:export")
    @Log(title = "商品调价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductAdjustPriceBo bo, HttpServletResponse response) {
        List<ProductAdjustPriceVo> list = iProductAdjustPriceService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品调价", ProductAdjustPriceVo.class, response);
    }

    /**
     * 获取商品调价详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:productAdjustPrice:query")
    @GetMapping("/{id}")
    public R<ProductAdjustPriceVo> getInfo(@PathVariable Long id) {
        return R.ok(iProductAdjustPriceService.queryById(id));
    }

    /**
     * 新增商品调价
     */
    @SaCheckPermission("zlyyh:productAdjustPrice:add")
    @Log(title = "商品调价", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductAdjustPriceBo bo) {
        return toAjax(iProductAdjustPriceService.insertByBo(bo));
    }

    /**
     * 修改商品调价
     */
    @SaCheckPermission("zlyyh:productAdjustPrice:edit")
    @Log(title = "商品调价", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductAdjustPriceBo bo) {
        return toAjax(iProductAdjustPriceService.updateByBo(bo));
    }

    /**
     * 删除商品调价
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:productAdjustPrice:remove")
    @Log(title = "商品调价", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iProductAdjustPriceService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
