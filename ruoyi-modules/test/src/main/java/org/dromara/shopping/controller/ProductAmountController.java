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
import org.dromara.shopping.domain.bo.ProductAmountBo;
import org.dromara.shopping.domain.vo.ProductAmountVo;
import org.dromara.shopping.service.IProductAmountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商品价格配置控制器
 * 前端访问路由地址为:/zlyyh/productAmount
 *
 * @author yzg
 * @date 2023-07-24
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productAmount")
public class ProductAmountController extends BaseController {

    private final IProductAmountService iProductAmountService;

    /**
     * 查询商品价格配置列表
     */
    @SaCheckPermission("zlyyh:productAmount:list")
    @GetMapping("/list")
    public TableDataInfo<ProductAmountVo> list(ProductAmountBo bo, PageQuery pageQuery) {
        return iProductAmountService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品价格配置列表
     */
    @SaCheckPermission("zlyyh:productAmount:export")
    @Log(title = "商品价格配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductAmountBo bo, HttpServletResponse response) {
        List<ProductAmountVo> list = iProductAmountService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品价格配置", ProductAmountVo.class, response);
    }

    /**
     * 获取商品价格配置详细信息
     *
     * @param amountId 主键
     */
    @SaCheckPermission("zlyyh:productAmount:query")
    @GetMapping("/{amountId}")
    public R<ProductAmountVo> getInfo(@PathVariable Long amountId) {
        return R.ok(iProductAmountService.queryById(amountId));
    }

    /**
     * 新增商品价格配置
     */
    @SaCheckPermission("zlyyh:productAmount:add")
    @Log(title = "商品价格配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductAmountBo bo) {
        return toAjax(iProductAmountService.insertByBo(bo));
    }

    /**
     * 修改商品价格配置
     */
    @SaCheckPermission("zlyyh:productAmount:edit")
    @Log(title = "商品价格配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductAmountBo bo) {
        return toAjax(iProductAmountService.updateByBo(bo));
    }

    /**
     * 删除商品价格配置
     *
     * @param amountIds 主键串
     */
    @SaCheckPermission("zlyyh:productAmount:remove")
    @Log(title = "商品价格配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{amountIds}")
    public R<Void> remove(@PathVariable Long[] amountIds) {
        return toAjax(iProductAmountService.deleteWithValidByIds(Arrays.asList(amountIds), true));
    }
}
