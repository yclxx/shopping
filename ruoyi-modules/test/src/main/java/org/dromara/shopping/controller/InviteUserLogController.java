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
import org.dromara.shopping.domain.bo.InviteUserLogBo;
import org.dromara.shopping.domain.vo.InviteUserLogVo;
import org.dromara.shopping.service.IInviteUserLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 邀请记录控制器
 * 前端访问路由地址为:/zlyyh/inviteUserLog
 *
 * @author yzg
 * @date 2023-08-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/inviteUserLog")
public class InviteUserLogController extends BaseController {

    private final IInviteUserLogService iInviteUserLogService;

    /**
     * 查询邀请记录列表
     */
    @SaCheckPermission("zlyyh:inviteUserLog:list")
    @GetMapping("/list")
    public TableDataInfo<InviteUserLogVo> list(InviteUserLogBo bo, PageQuery pageQuery) {
        return iInviteUserLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出邀请记录列表
     */
    @SaCheckPermission("zlyyh:inviteUserLog:export")
    @Log(title = "邀请记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(InviteUserLogBo bo, HttpServletResponse response) {
        List<InviteUserLogVo> list = iInviteUserLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "邀请记录", InviteUserLogVo.class, response);
    }

    /**
     * 获取邀请记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:inviteUserLog:query")
    @GetMapping("/{id}")
    public R<InviteUserLogVo> getInfo(@PathVariable Long id) {
        return R.ok(iInviteUserLogService.queryById(id));
    }

    /**
     * 新增邀请记录
     */
    @SaCheckPermission("zlyyh:inviteUserLog:add")
    @Log(title = "邀请记录", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody InviteUserLogBo bo) {
        return toAjax(iInviteUserLogService.insertByBo(bo));
    }

    /**
     * 修改邀请记录
     */
    @SaCheckPermission("zlyyh:inviteUserLog:edit")
    @Log(title = "邀请记录", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody InviteUserLogBo bo) {
        return toAjax(iInviteUserLogService.updateByBo(bo));
    }

    /**
     * 删除邀请记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:inviteUserLog:remove")
    @Log(title = "邀请记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iInviteUserLogService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
