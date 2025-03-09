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
import org.dromara.shopping.domain.bo.PlatformSharePosterBo;
import org.dromara.shopping.domain.vo.PlatformSharePosterVo;
import org.dromara.shopping.service.IPlatformSharePosterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台分享海报控制器
 * 前端访问路由地址为:/zlyyh/platformSharePoster
 *
 * @author yzg
 * @date 2025-01-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformSharePoster")
public class PlatformSharePosterController extends BaseController {

    private final IPlatformSharePosterService iPlatformSharePosterService;

    /**
     * 查询平台分享海报列表
     */
    @SaCheckPermission("zlyyh:platformSharePoster:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformSharePosterVo> list(PlatformSharePosterBo bo, PageQuery pageQuery) {
        return iPlatformSharePosterService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台分享海报列表
     */
    @SaCheckPermission("zlyyh:platformSharePoster:export")
    @Log(title = "平台分享海报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformSharePosterBo bo, HttpServletResponse response) {
        List<PlatformSharePosterVo> list = iPlatformSharePosterService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台分享海报", PlatformSharePosterVo.class, response);
    }

    /**
     * 获取平台分享海报详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformSharePoster:query")
    @GetMapping("/{id}")
    public R<PlatformSharePosterVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformSharePosterService.queryById(id));
    }

    /**
     * 新增平台分享海报
     */
    @SaCheckPermission("zlyyh:platformSharePoster:add")
    @Log(title = "平台分享海报", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformSharePosterBo bo) {
        return toAjax(iPlatformSharePosterService.insertByBo(bo));
    }

    /**
     * 修改平台分享海报
     */
    @SaCheckPermission("zlyyh:platformSharePoster:edit")
    @Log(title = "平台分享海报", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformSharePosterBo bo) {
        return toAjax(iPlatformSharePosterService.updateByBo(bo));
    }

    /**
     * 删除平台分享海报
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformSharePoster:remove")
    @Log(title = "平台分享海报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformSharePosterService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
