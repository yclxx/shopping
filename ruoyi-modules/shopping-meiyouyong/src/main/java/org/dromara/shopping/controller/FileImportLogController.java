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
import org.dromara.shopping.domain.bo.FileImportLogBo;
import org.dromara.shopping.domain.vo.FileImportLogVo;
import org.dromara.shopping.service.IFileImportLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 文件导入记录控制器
 * 前端访问路由地址为:/zlyyh/fileImportLog
 *
 * @author yzg
 * @date 2024-01-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/fileImportLog")
public class FileImportLogController extends BaseController {

    private final IFileImportLogService iFileImportLogService;

    /**
     * 查询文件导入记录列表
     */
    @SaCheckPermission("zlyyh:fileImportLog:list")
    @GetMapping("/list")
    public TableDataInfo<FileImportLogVo> list(FileImportLogBo bo, PageQuery pageQuery) {
        return iFileImportLogService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出文件导入记录列表
     */
    @SaCheckPermission("zlyyh:fileImportLog:export")
    @Log(title = "文件导入记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(FileImportLogBo bo, HttpServletResponse response) {
        List<FileImportLogVo> list = iFileImportLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "文件导入记录", FileImportLogVo.class, response);
    }

    /**
     * 获取文件导入记录详细信息
     *
     * @param importLogId 主键
     */
    @SaCheckPermission("zlyyh:fileImportLog:query")
    @GetMapping("/{importLogId}")
    public R<FileImportLogVo> getInfo(@PathVariable Long importLogId) {
        return R.ok(iFileImportLogService.queryById(importLogId));
    }

    /**
     * 新增文件导入记录
     */
    @SaCheckPermission("zlyyh:fileImportLog:add")
    @Log(title = "文件导入记录", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody FileImportLogBo bo) {
        return toAjax(iFileImportLogService.insertByBo(bo));
    }

    /**
     * 修改文件导入记录
     */
    @SaCheckPermission("zlyyh:fileImportLog:edit")
    @Log(title = "文件导入记录", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody FileImportLogBo bo) {
        return toAjax(iFileImportLogService.updateByBo(bo));
    }

    /**
     * 删除文件导入记录
     *
     * @param importLogIds 主键串
     */
    @SaCheckPermission("zlyyh:fileImportLog:remove")
    @Log(title = "文件导入记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{importLogIds}")
    public R<Void> remove(@PathVariable Long[] importLogIds) {
        return toAjax(iFileImportLogService.deleteWithValidByIds(Arrays.asList(importLogIds), true));
    }
}
