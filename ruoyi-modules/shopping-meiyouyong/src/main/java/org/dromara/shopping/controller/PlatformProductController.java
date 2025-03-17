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
import org.dromara.shopping.domain.bo.PlatformProductBo;
import org.dromara.shopping.domain.vo.PlatformProductVo;
import org.dromara.shopping.service.IPlatformProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台商品配置控制器
 * 前端访问路由地址为:/zlyyh/platformProduct
 *
 * @author yzg
 * @date 2024-05-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformProduct")
public class PlatformProductController extends BaseController {

    private final IPlatformProductService iPlatformProductService;

    /**
     * 查询平台商品配置列表
     */
    @SaCheckPermission("zlyyh:platformProduct:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformProductVo> list(PlatformProductBo bo, PageQuery pageQuery) {
        return iPlatformProductService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台商品配置列表
     */
    @SaCheckPermission("zlyyh:platformProduct:export")
    @Log(title = "平台商品配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformProductBo bo, HttpServletResponse response) {
        List<PlatformProductVo> list = iPlatformProductService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台商品配置", PlatformProductVo.class, response);
    }

    /**
     * 获取平台商品配置详细信息
     *
     * @param platformProductId 主键
     */
    @SaCheckPermission("zlyyh:platformProduct:query")
    @GetMapping("/{platformProductId}")
    public R<PlatformProductVo> getInfo(@PathVariable Long platformProductId) {
        return R.ok(iPlatformProductService.queryById(platformProductId));
    }

    /**
     * 新增平台商品配置
     */
    @SaCheckPermission("zlyyh:platformProduct:add")
    @Log(title = "平台商品配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformProductBo bo) {
        return toAjax(iPlatformProductService.insertByBo(bo));
    }

    /**
     * 修改平台商品配置
     */
    @SaCheckPermission("zlyyh:platformProduct:edit")
    @Log(title = "平台商品配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformProductBo bo) {
        return toAjax(iPlatformProductService.updateByBo(bo));
    }

    /**
     * 删除平台商品配置
     *
     * @param platformProductIds 主键串
     */
    @SaCheckPermission("zlyyh:platformProduct:remove")
    @Log(title = "平台商品配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{platformProductIds}")
    public R<Void> remove(@PathVariable Long[] platformProductIds) {
        return toAjax(iPlatformProductService.deleteWithValidByIds(Arrays.asList(platformProductIds), true));
    }

    /**
     * 配置商品
     *
     * @param platformProductIds 主键串
     */
    @GetMapping("/setProduct/{platformProductIds}")
    public R<Void> setProduct(@PathVariable Long[] platformProductIds) {
        iPlatformProductService.setProduct(Arrays.asList(platformProductIds));
        return R.ok();
    }

    /**
     * 取消配置商品
     *
     * @param platformProductIds 主键串
     */
    @GetMapping("/unsetProduct/{platformProductIds}")
    public R<Void> unsetProduct(@PathVariable Long[] platformProductIds) {
        iPlatformProductService.unsetProduct(Arrays.asList(platformProductIds));
        return R.ok();
    }

    /**
     * 平台商品调价
     */
    @PostMapping("/productAdjustPrice")
    public R<Void> productAdjustPrice(@RequestBody PlatformProductBo bo) {
        iPlatformProductService.productAdjustPrice(bo);
        return R.ok();
    }
}
