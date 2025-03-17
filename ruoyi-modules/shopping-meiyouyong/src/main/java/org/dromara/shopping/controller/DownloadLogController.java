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
import org.dromara.shopping.domain.bo.DownloadLogBo;
import org.dromara.shopping.domain.vo.DownloadLogVo;
import org.dromara.shopping.service.IDownloadLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 下载记录控制器
 * 前端访问路由地址为:/zlyyh/downloadLog
 *
 * @author yzg
 * @date 2024-08-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/downloadLog")
public class DownloadLogController extends BaseController {

    private final IDownloadLogService iDownloadLogService;

    /**
     * 查询下载记录列表
     */
    @SaCheckPermission("zlyyh:downloadLog:list")
    @GetMapping("/list")
    public TableDataInfo<DownloadLogVo> list(DownloadLogBo bo, PageQuery pageQuery) {
        return iDownloadLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出下载记录列表
     */
    @SaCheckPermission("zlyyh:downloadLog:export")
    @Log(title = "下载记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DownloadLogBo bo, HttpServletResponse response) {
        List<DownloadLogVo> list = iDownloadLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "下载记录", DownloadLogVo.class, response);
    }

    /**
     * 获取下载记录详细信息
     *
     * @param downloadId 主键
     */
    @SaCheckPermission("zlyyh:downloadLog:query")
    @GetMapping("/{downloadId}")
    public R<DownloadLogVo> getInfo(@PathVariable Long downloadId) {
        return R.ok(iDownloadLogService.queryById(downloadId));
    }

    /**
     * 新增下载记录
     */
    @SaCheckPermission("zlyyh:downloadLog:add")
    @Log(title = "下载记录", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DownloadLogBo bo) {
        return toAjax(iDownloadLogService.insertByBo(bo));
    }

    /**
     * 修改下载记录
     */
    @SaCheckPermission("zlyyh:downloadLog:edit")
    @Log(title = "下载记录", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DownloadLogBo bo) {
        return toAjax(iDownloadLogService.updateByBo(bo));
    }

    /**
     * 删除下载记录
     *
     * @param downloadIds 主键串
     */
    @SaCheckPermission("zlyyh:downloadLog:remove")
    @Log(title = "下载记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{downloadIds}")
    public R<Void> remove(@PathVariable Long[] downloadIds) {
        return toAjax(iDownloadLogService.deleteWithValidByIds(Arrays.asList(downloadIds), true));
    }
}
