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
import org.dromara.shopping.domain.bo.HistoryOrderBo;
import org.dromara.shopping.domain.bo.OrderDownloadLogBo;
import org.dromara.shopping.domain.vo.HistoryOrderVo;
import org.dromara.shopping.service.IHistoryOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 历史订单控制器
 * 前端访问路由地址为:/zlyyh/historyOrder
 *
 * @author yzg
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/historyOrder")
public class HistoryOrderController extends BaseController {

    private final IHistoryOrderService iHistoryOrderService;


    /**
     * 查询历史订单列表
     */
    @SaCheckPermission("zlyyh:historyOrder:list")
    @GetMapping("/list")
    public TableDataInfo<HistoryOrderVo> list(HistoryOrderBo bo, PageQuery pageQuery) {
        return iHistoryOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出历史订单列表
     */
    @SaCheckPermission("zlyyh:historyOrder:export")
    @Log(title = "历史订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HistoryOrderBo bo, HttpServletResponse response) {
        List<HistoryOrderVo> list = iHistoryOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "历史订单", HistoryOrderVo.class, response);
    }

    /**
     * 获取历史订单详细信息
     *
     * @param number 主键
     */
    @SaCheckPermission("zlyyh:historyOrder:query")
    @GetMapping("/{number}")
    public R<HistoryOrderVo> getInfo(@PathVariable Long number) {
        return R.ok(iHistoryOrderService.queryById(number));
    }

    /**
     * 新增历史订单
     */
    @SaCheckPermission("zlyyh:historyOrder:add")
    @Log(title = "历史订单", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HistoryOrderBo bo) {
        return toAjax(iHistoryOrderService.insertByBo(bo));
    }

    /**
     * 修改历史订单
     */
    @SaCheckPermission("zlyyh:historyOrder:edit")
    @Log(title = "历史订单", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HistoryOrderBo bo) {
        return toAjax(iHistoryOrderService.updateByBo(bo));
    }


    /**
     * 删除历史订单
     *
     * @param numbers 主键串
     */
    @SaCheckPermission("zlyyh:historyOrder:remove")
    @Log(title = "历史订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{numbers}")
    public R<Void> remove(@PathVariable Long[] numbers) {
        return toAjax(iHistoryOrderService.deleteWithValidByIds(Arrays.asList(numbers), true));
    }

}
