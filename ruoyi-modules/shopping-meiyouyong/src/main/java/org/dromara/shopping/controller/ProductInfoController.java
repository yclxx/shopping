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
import org.dromara.shopping.domain.bo.ProductInfoBo;
import org.dromara.shopping.domain.vo.ProductInfoVo;
import org.dromara.shopping.service.IProductInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商品拓展控制器
 * 前端访问路由地址为:/zlyyh/productInfo
 *
 * @author yzg
 * @date 2023-05-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController extends BaseController {

    private final IProductInfoService iProductInfoService;

    /**
     * 查询商品拓展列表
     */
    @SaCheckPermission("zlyyh:productInfo:list")
    @GetMapping("/list")
    public TableDataInfo<ProductInfoVo> list(ProductInfoBo bo, PageQuery pageQuery) {
        return iProductInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品拓展列表
     */
    @SaCheckPermission("zlyyh:productInfo:export")
    @Log(title = "商品拓展", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductInfoBo bo, HttpServletResponse response) {
        List<ProductInfoVo> list = iProductInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品拓展", ProductInfoVo.class, response);
    }

    /**
     * 获取商品拓展详细信息
     *
     * @param productId 主键
     */
    @SaCheckPermission("zlyyh:productInfo:query")
    @GetMapping("/{productId}")
    public R<ProductInfoVo> getInfo(@PathVariable Long productId) {
        return R.ok(iProductInfoService.queryById(productId));
    }

    /**
     * 新增商品拓展
     */
    @SaCheckPermission("zlyyh:productInfo:add")
    @Log(title = "商品拓展", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductInfoBo bo) {
        return toAjax(iProductInfoService.insertByBo(bo));
    }

    /**
     * 修改商品拓展
     */
    @SaCheckPermission("zlyyh:productInfo:edit")
    @Log(title = "商品拓展", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductInfoBo bo) {
        return toAjax(iProductInfoService.updateByBo(bo));
    }

    /**
     * 删除商品拓展
     *
     * @param productIds 主键串
     */
    @SaCheckPermission("zlyyh:productInfo:remove")
    @Log(title = "商品拓展", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productIds}")
    public R<Void> remove(@PathVariable Long[] productIds) {
        return toAjax(iProductInfoService.deleteWithValidByIds(Arrays.asList(productIds), true));
    }
}
