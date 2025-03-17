package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
import org.dromara.shopping.domain.bo.ActionBo;
import org.dromara.shopping.domain.vo.ActionVo;
import org.dromara.shopping.service.IActionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 优惠券批次控制器
 * 前端访问路由地址为:/zlyyh/action
 *
 * @author yzg
 * @date 2023-10-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/action")
public class ActionController extends BaseController {

    private final IActionService actionService;

    /**
     * 查询优惠券批次列表
     */
    @SaCheckPermission("zlyyh:action:list")
    @GetMapping("/list")
    public TableDataInfo<ActionVo> list(ActionBo bo, PageQuery pageQuery) {
        return actionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出优惠券批次列表
     */
    @SaCheckPermission("zlyyh:action:export")
    @Log(title = "优惠券批次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ActionBo bo, HttpServletResponse response) {
        List<ActionVo> list = actionService.queryList(bo);
        ExcelUtil.exportExcel(list, "优惠券批次", ActionVo.class, response);
    }

    /**
     * 获取优惠券批次详细信息
     *
     * @param actionId 主键
     */
    @SaCheckPermission("zlyyh:action:query")
    @GetMapping("/{actionId}")
    public R<ActionVo> getInfo(@PathVariable Long actionId) {
        return R.ok(actionService.queryById(actionId));
    }

    /**
     * 创建优惠券
     */
    @SaCheckPermission("zlyyh:action:create")
    @Log(title = "创建优惠券", businessType = BusinessType.INSERT)
    @PostMapping("/createCoupon")
    public R<Void> create(@RequestBody ActionBo bo) {
        return toAjax(actionService.createCoupon(bo.getActionId(), bo.getCouponCount()));
    }

    /**
     * 新增优惠券批次
     */
    @SaCheckPermission("zlyyh:action:add")
    @Log(title = "优惠券批次", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ActionBo bo) {
        return toAjax(actionService.insertByBo(bo));
    }


    /**
     * 修改优惠券批次
     */
    @SaCheckPermission("zlyyh:action:edit")
    @Log(title = "优惠券批次", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ActionBo bo) {
        return toAjax(actionService.updateByBo(bo));
    }

    /**
     * 删除优惠券批次
     *
     * @param actionIds 主键串
     */
    @SaCheckPermission("zlyyh:action:remove")
    @Log(title = "优惠券批次", businessType = BusinessType.DELETE)
    @DeleteMapping("/{actionIds}")
    public R<Void> remove(@PathVariable Long[] actionIds) {
        return toAjax(actionService.deleteWithValidByIds(Arrays.asList(actionIds), true));
    }
}
