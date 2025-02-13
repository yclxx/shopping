package org.dromara.boss.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.boss.domain.bo.ApplyJobBo;
import org.dromara.boss.domain.vo.ApplyJobVo;
import org.dromara.boss.service.IApplyExecuteService;
import org.dromara.boss.service.IApplyJobService;
import org.dromara.boss.utils.ZpUtils;
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
 * 沟通任务
 *
 * @author xx
 * @date 2024-11-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/boss/applyJob")
public class ApplyJobController extends BaseController {

    private final IApplyJobService applyJobService;
    private final IApplyExecuteService applyExecuteService;

    /**
     * 查询沟通任务列表
     */
    @SaCheckPermission("boss:applyJob:list")
    @GetMapping("/list")
    public TableDataInfo<ApplyJobVo> list(ApplyJobBo bo, PageQuery pageQuery) {
        return applyJobService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出沟通任务列表
     */
    @SaCheckPermission("boss:applyJob:export")
    @Log(title = "沟通任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ApplyJobBo bo, HttpServletResponse response) {
        List<ApplyJobVo> list = applyJobService.queryList(bo);
        ExcelUtil.exportExcel(list, "沟通任务", ApplyJobVo.class, response);
    }

    /**
     * 获取沟通任务详细信息
     *
     * @param applyJobId 主键
     */
    @SaCheckPermission("boss:applyJob:query")
    @GetMapping("/{applyJobId}")
    public R<ApplyJobVo> getInfo(@NotNull(message = "主键不能为空")
                                 @PathVariable Long applyJobId) {
        return R.ok(applyJobService.queryById(applyJobId));
    }

    /**
     * 新增沟通任务
     */
    @SaCheckPermission("boss:applyJob:add")
    @Log(title = "沟通任务", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ApplyJobBo bo) {
        // 校验是否初始化参数
        ZpUtils instance = ZpUtils.getInstance();
        if (instance.isInitHeaders() == false) {
            return R.fail("请先初始化参数");
        }
        Boolean result = applyJobService.insertByBo(bo);
        if (result) {
            // 异步执行任务
            applyExecuteService.execute(bo.getApplyJobId());
        }
        return toAjax(result);
    }

    /**
     * 修改沟通任务
     */
    @SaCheckPermission("boss:applyJob:edit")
    @Log(title = "沟通任务", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ApplyJobBo bo) {
        return toAjax(applyJobService.updateByBo(bo));
    }

    /**
     * 删除沟通任务
     *
     * @param applyJobIds 主键串
     */
    @SaCheckPermission("boss:applyJob:remove")
    @Log(title = "沟通任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyJobIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] applyJobIds) {
        return toAjax(applyJobService.deleteWithValidByIds(List.of(applyJobIds), true));
    }
}
