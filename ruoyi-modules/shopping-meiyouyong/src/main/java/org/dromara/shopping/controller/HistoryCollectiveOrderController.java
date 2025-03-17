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
import org.dromara.shopping.domain.bo.HistoryCollectiveOrderBo;
import org.dromara.shopping.domain.vo.HistoryCollectiveOrderVo;
import org.dromara.shopping.service.IHistoryCollectiveOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 历史大订单控制器
 * 前端访问路由地址为:/zlyyh/historyCollectiveOrder
 *
 * @author yzg
 * @date 2023-11-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/historyCollectiveOrder")
public class HistoryCollectiveOrderController extends BaseController {

    private final IHistoryCollectiveOrderService iHistoryCollectiveOrderService;

    /**
     * 查询历史大订单列表
     */
    @SaCheckPermission("zlyyh:historyCollectiveOrder:list")
    @GetMapping("/list")
    public TableDataInfo<HistoryCollectiveOrderVo> list(HistoryCollectiveOrderBo bo, PageQuery pageQuery) {
        return iHistoryCollectiveOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出历史大订单列表
     */
    @SaCheckPermission("zlyyh:historyCollectiveOrder:export")
    @Log(title = "历史大订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HistoryCollectiveOrderBo bo, HttpServletResponse response) {
        List<HistoryCollectiveOrderVo> list = iHistoryCollectiveOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "历史大订单", HistoryCollectiveOrderVo.class, response);
    }

    /**
     * 获取历史大订单详细信息
     *
     * @param collectiveNumber 主键
     */
    @SaCheckPermission("zlyyh:historyCollectiveOrder:query")
    @GetMapping("/{collectiveNumber}")
    public R<HistoryCollectiveOrderVo> getInfo(@PathVariable Long collectiveNumber) {
        return R.ok(iHistoryCollectiveOrderService.queryById(collectiveNumber));
    }

    /**
     * 新增历史大订单
     */
    @SaCheckPermission("zlyyh:historyCollectiveOrder:add")
    @Log(title = "历史大订单", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HistoryCollectiveOrderBo bo) {
        return toAjax(iHistoryCollectiveOrderService.insertByBo(bo));
    }

    /**
     * 修改历史大订单
     */
    @SaCheckPermission("zlyyh:historyCollectiveOrder:edit")
    @Log(title = "历史大订单", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HistoryCollectiveOrderBo bo) {
        return toAjax(iHistoryCollectiveOrderService.updateByBo(bo));
    }

    /**
     * 删除历史大订单
     *
     * @param collectiveNumbers 主键串
     */
    @SaCheckPermission("zlyyh:historyCollectiveOrder:remove")
    @Log(title = "历史大订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collectiveNumbers}")
    public R<Void> remove(@PathVariable Long[] collectiveNumbers) {
        return toAjax(iHistoryCollectiveOrderService.deleteWithValidByIds(Arrays.asList(collectiveNumbers), true));
    }
}
