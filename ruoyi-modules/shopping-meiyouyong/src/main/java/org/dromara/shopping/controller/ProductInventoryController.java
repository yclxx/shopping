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
import org.dromara.shopping.domain.bo.ProductInventoryBo;
import org.dromara.shopping.domain.vo.ProductInventoryVo;
import org.dromara.shopping.service.IProductInventoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商品库存信息控制器
 * 前端访问路由地址为:/zlyyh/productInventory
 *
 * @author yzg
 * @date 2024-05-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productInventory")
public class ProductInventoryController extends BaseController {

    private final IProductInventoryService iProductInventoryService;

    /**
     * 查询商品库存信息列表
     */
    @SaCheckPermission("zlyyh:productInventory:list")
    @GetMapping("/list")
    public TableDataInfo<ProductInventoryVo> list(ProductInventoryBo bo, PageQuery pageQuery) {
        return iProductInventoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品库存信息列表
     */
    @SaCheckPermission("zlyyh:productInventory:export")
    @Log(title = "商品库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductInventoryBo bo, HttpServletResponse response) {
        List<ProductInventoryVo> list = iProductInventoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品库存信息", ProductInventoryVo.class, response);
    }

    /**
     * 获取商品库存信息详细信息
     *
     * @param inventoryId 主键
     */
    @SaCheckPermission("zlyyh:productInventory:query")
    @GetMapping("/{inventoryId}")
    public R<ProductInventoryVo> getInfo(@PathVariable Long inventoryId) {
        return R.ok(iProductInventoryService.queryById(inventoryId));
    }

    /**
     * 新增商品库存信息
     */
    @SaCheckPermission("zlyyh:productInventory:add")
    @Log(title = "商品库存信息", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductInventoryBo bo) {
        return toAjax(iProductInventoryService.insertByBo(bo));
    }

    /**
     * 修改商品库存信息
     */
    @SaCheckPermission("zlyyh:productInventory:edit")
    @Log(title = "商品库存信息", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductInventoryBo bo) {
        return toAjax(iProductInventoryService.updateByBo(bo));
    }

    /**
     * 删除商品库存信息
     *
     * @param inventoryIds 主键串
     */
    @SaCheckPermission("zlyyh:productInventory:remove")
    @Log(title = "商品库存信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inventoryIds}")
    public R<Void> remove(@PathVariable Long[] inventoryIds) {
        return toAjax(iProductInventoryService.deleteWithValidByIds(Arrays.asList(inventoryIds), true));
    }
}
