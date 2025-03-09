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
import org.dromara.shopping.domain.bo.ProductComputeMonthBo;
import org.dromara.shopping.domain.vo.ProductComputeMonthVo;
import org.dromara.shopping.service.IProductComputeMonthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单数据统计（月份）控制器
 * 前端访问路由地址为:/zlyyh/productComputeMonth
 *
 * @author yzg
 * @date 2023-07-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productComputeMonth")
public class ProductComputeMonthController extends BaseController {

    private final IProductComputeMonthService iProductComputeMonthService;

    /**
     * 查询订单数据统计（月份）列表
     */
    @SaCheckPermission("zlyyh:productComputeMonth:list")
    @GetMapping("/list")
    public TableDataInfo<ProductComputeMonthVo> list(ProductComputeMonthBo bo, PageQuery pageQuery) {
        return iProductComputeMonthService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单数据统计（月份）列表
     */
    @SaCheckPermission("zlyyh:productComputeMonth:export")
    @Log(title = "订单数据统计（月份）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductComputeMonthBo bo, HttpServletResponse response) {
        List<ProductComputeMonthVo> list = iProductComputeMonthService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单数据统计（月份）", ProductComputeMonthVo.class, response);
    }

    /**
     * 获取订单数据统计（月份）详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:productComputeMonth:query")
    @GetMapping("/{id}")
    public R<ProductComputeMonthVo> getInfo(@PathVariable Long id) {
        return R.ok(iProductComputeMonthService.queryById(id));
    }
}
