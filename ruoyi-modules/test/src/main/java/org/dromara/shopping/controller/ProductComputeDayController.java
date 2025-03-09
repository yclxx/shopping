package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.ProductComputeDayBo;
import org.dromara.shopping.domain.vo.ProductComputeDayVo;
import org.dromara.shopping.service.IProductComputeDayService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单数据统计（每天）控制器
 * 前端访问路由地址为:/zlyyh/productComputeDay
 *
 * @author yzg
 * @date 2023-07-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productComputeDay")
public class ProductComputeDayController extends BaseController {

    private final IProductComputeDayService iProductComputeDayService;

    /**
     * 查询订单数据统计（每天）列表
     */
    @SaCheckPermission("zlyyh:productComputeDay:list")
    @GetMapping("/list")
    public TableDataInfo<ProductComputeDayVo> list(ProductComputeDayBo bo, PageQuery pageQuery) {
        return iProductComputeDayService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单数据统计（每天）列表
     */
    @SaCheckPermission("zlyyh:productComputeDay:export")
    @Log(title = "订单数据统计（每天）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductComputeDayBo bo, HttpServletResponse response) {
        List<ProductComputeDayVo> list = iProductComputeDayService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单数据统计（每天）", ProductComputeDayVo.class, response);
    }

    /**
     * 获取订单数据统计（每天）详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:productComputeDay:query")
    @GetMapping("/{id}")
    public R<ProductComputeDayVo> getInfo(@PathVariable Long id) {
        return R.ok(iProductComputeDayService.queryById(id));
    }

    ///**
    // * 新增订单数据统计（每天）
    // */
    //@SaCheckPermission("zlyyh:productComputeDay:add")
    //@Log(title = "订单数据统计（每天）", businessType = BusinessType.INSERT)
    //@PostMapping()
    //public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductComputeDayBo bo) {
    //    return toAjax(iProductComputeDayService.insertByBo(bo));
    //}

    ///**
    // * 修改订单数据统计（每天）
    // */
    //@SaCheckPermission("zlyyh:productComputeDay:edit")
    //@Log(title = "订单数据统计（每天）", businessType = BusinessType.UPDATE)
    //@PutMapping()
    //public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductComputeDayBo bo) {
    //    return toAjax(iProductComputeDayService.updateByBo(bo));
    //}

    ///**
    // * 删除订单数据统计（每天）
    // *
    // * @param ids 主键串
    // */
    //@SaCheckPermission("zlyyh:productComputeDay:remove")
    //@Log(title = "订单数据统计（每天）", businessType = BusinessType.DELETE)
    //@DeleteMapping("/{ids}")
    //public R<Void> remove(@PathVariable Long[] ids) {
    //    return toAjax(iProductComputeDayService.deleteWithValidByIds(Arrays.asList(ids), true));
    //}
}
