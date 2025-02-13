package org.dromara.boss.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.boss.domain.bo.ApplyLogBo;
import org.dromara.boss.domain.vo.ApplyLogVo;
import org.dromara.boss.service.IApplyLogService;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 沟通记录
 *
 * @author xx
 * @date 2024-11-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/boss/applyLog")
public class ApplyLogController extends BaseController {

    private final IApplyLogService applyLogService;

    /**
     * 查询沟通记录列表
     */
    @SaCheckPermission("boss:applyLog:list")
    @GetMapping("/list")
    public TableDataInfo<ApplyLogVo> list(ApplyLogBo bo, PageQuery pageQuery) {
        return applyLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出沟通记录列表
     */
    @SaCheckPermission("boss:applyLog:export")
    @Log(title = "沟通记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ApplyLogBo bo, HttpServletResponse response) {
        List<ApplyLogVo> list = applyLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "沟通记录", ApplyLogVo.class, response);
    }

    /**
     * 获取沟通记录详细信息
     *
     * @param applyLogId 主键
     */
    @SaCheckPermission("boss:applyLog:query")
    @GetMapping("/{applyLogId}")
    public R<ApplyLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long applyLogId) {
        return R.ok(applyLogService.queryById(applyLogId));
    }

    /**
     * 新增沟通记录
     */
    @SaCheckPermission("boss:applyLog:add")
    @Log(title = "沟通记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ApplyLogBo bo) {
        return toAjax(applyLogService.insertByBo(bo));
    }

    /**
     * 修改沟通记录
     */
    @SaCheckPermission("boss:applyLog:edit")
    @Log(title = "沟通记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ApplyLogBo bo) {
        return toAjax(applyLogService.updateByBo(bo));
    }

    /**
     * 删除沟通记录
     *
     * @param applyLogIds 主键串
     */
    @SaCheckPermission("boss:applyLog:remove")
    @Log(title = "沟通记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyLogIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] applyLogIds) {
        return toAjax(applyLogService.deleteWithValidByIds(List.of(applyLogIds), true));
    }
}
