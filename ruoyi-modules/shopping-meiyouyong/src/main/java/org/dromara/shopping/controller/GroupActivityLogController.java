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
import org.dromara.shopping.domain.bo.GroupActivityLogBo;
import org.dromara.shopping.domain.vo.GroupActivityLogVo;
import org.dromara.shopping.service.IGroupActivityLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 拼团活动记录控制器
 * 前端访问路由地址为:/zlyyh/groupActivityLog
 *
 * @author yzg
 * @date 2024-10-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/groupActivityLog")
public class GroupActivityLogController extends BaseController {

    private final IGroupActivityLogService iGroupActivityLogService;

    /**
     * 查询拼团活动记录列表
     */
    @SaCheckPermission("zlyyh:groupActivityLog:list")
    @GetMapping("/list")
    public TableDataInfo<GroupActivityLogVo> list(GroupActivityLogBo bo, PageQuery pageQuery) {
        return iGroupActivityLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出拼团活动记录列表
     */
    @SaCheckPermission("zlyyh:groupActivityLog:export")
    @Log(title = "拼团活动记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GroupActivityLogBo bo, HttpServletResponse response) {
        List<GroupActivityLogVo> list = iGroupActivityLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "拼团活动记录", GroupActivityLogVo.class, response);
    }

    /**
     * 获取拼团活动记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:groupActivityLog:query")
    @GetMapping("/{id}")
    public R<GroupActivityLogVo> getInfo(@PathVariable Long id) {
        return R.ok(iGroupActivityLogService.queryById(id));
    }

    /**
     * 新增拼团活动记录
     */
    @SaCheckPermission("zlyyh:groupActivityLog:add")
    @Log(title = "拼团活动记录", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GroupActivityLogBo bo) {
        return toAjax(iGroupActivityLogService.insertByBo(bo));
    }

    /**
     * 修改拼团活动记录
     */
    @SaCheckPermission("zlyyh:groupActivityLog:edit")
    @Log(title = "拼团活动记录", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GroupActivityLogBo bo) {
        return toAjax(iGroupActivityLogService.updateByBo(bo));
    }

    /**
     * 删除拼团活动记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:groupActivityLog:remove")
    @Log(title = "拼团活动记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iGroupActivityLogService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
