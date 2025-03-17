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
import org.dromara.shopping.domain.bo.ProductGroupBo;
import org.dromara.shopping.domain.bo.ProductGroupConnectBo;
import org.dromara.shopping.domain.vo.ProductGroupVo;
import org.dromara.shopping.service.IProductGroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商品组规则配置控制器
 * 前端访问路由地址为:/zlyyh/productGroup
 *
 * @author yzg
 * @date 2024-01-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productGroup")
public class ProductGroupController extends BaseController {

    private final IProductGroupService iProductGroupService;

    /**
     * 查询商品组规则配置列表
     */
    @SaCheckPermission("zlyyh:productGroup:list")
    @GetMapping("/list")
    public TableDataInfo<ProductGroupVo> list(ProductGroupBo bo, PageQuery pageQuery) {
        return iProductGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品组规则配置列表
     */
    @SaCheckPermission("zlyyh:productGroup:export")
    @Log(title = "商品组规则配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductGroupBo bo, HttpServletResponse response) {
        List<ProductGroupVo> list = iProductGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品组规则配置", ProductGroupVo.class, response);
    }

    /**
     * 获取商品组规则配置详细信息
     *
     * @param productGroupId 主键
     */
    @SaCheckPermission("zlyyh:productGroup:query")
    @GetMapping("/{productGroupId}")
    public R<ProductGroupVo> getInfo(@PathVariable Long productGroupId) {
        return R.ok(iProductGroupService.queryById(productGroupId));
    }

    /**
     * 新增商品组规则配置
     */
    @SaCheckPermission("zlyyh:productGroup:add")
    @Log(title = "商品组规则配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductGroupBo bo) {
        return toAjax(iProductGroupService.insertByBo(bo));
    }

    /**
     * 修改商品组规则配置
     */
    @SaCheckPermission("zlyyh:productGroup:edit")
    @Log(title = "商品组规则配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductGroupBo bo) {
        return toAjax(iProductGroupService.updateByBo(bo));
    }

    /**
     * 修改优惠券批次商品
     */
    @PostMapping("/updateGroupProduct")
    public R<Void> updateActionProduct(@RequestBody ProductGroupConnectBo bo) {
        return toAjax(iProductGroupService.updateGroupProduct(bo.getProductIds(), bo.getProductGroupId(), bo.getType()));
    }


    /**
     * 删除商品组规则配置
     *
     * @param productGroupIds 主键串
     */
    @SaCheckPermission("zlyyh:productGroup:remove")
    @Log(title = "商品组规则配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productGroupIds}")
    public R<Void> remove(@PathVariable Long[] productGroupIds) {
        return toAjax(iProductGroupService.deleteWithValidByIds(Arrays.asList(productGroupIds), true));
    }
}
