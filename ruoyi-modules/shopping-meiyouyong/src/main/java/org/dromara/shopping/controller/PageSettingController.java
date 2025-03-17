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
import org.dromara.shopping.domain.bo.PageSettingBo;
import org.dromara.shopping.domain.vo.PageSettingVo;
import org.dromara.shopping.service.IPageSettingService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 页面配置控制器
 * 前端访问路由地址为:/zlyyh/pageSetting
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pageSetting")
public class PageSettingController extends BaseController {

    private final IPageSettingService iPageSettingService;

    /**
     * 查询页面配置列表
     */
    @SaCheckPermission("zlyyh:pageSetting:list")
    @GetMapping("/list")
    public TableDataInfo<PageSettingVo> list(PageSettingBo bo, PageQuery pageQuery) {
        return iPageSettingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出页面配置列表
     */
    @SaCheckPermission("zlyyh:pageSetting:export")
    @Log(title = "页面配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PageSettingBo bo, HttpServletResponse response) {
        List<PageSettingVo> list = iPageSettingService.queryList(bo);
        ExcelUtil.exportExcel(list, "页面配置", PageSettingVo.class, response);
    }

    /**
     * 获取页面配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:pageSetting:query")
    @GetMapping("/{id}")
    public R<PageSettingVo> getInfo(@PathVariable Long id) {
        return R.ok(iPageSettingService.queryById(id));
    }

    /**
     * 新增页面配置
     */
    @SaCheckPermission("zlyyh:pageSetting:add")
    @Log(title = "页面配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PageSettingBo bo) {
        return toAjax(iPageSettingService.insertByBo(bo));
    }

    /**
     * 修改页面配置
     */
    @SaCheckPermission("zlyyh:pageSetting:edit")
    @Log(title = "页面配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PageSettingBo bo) {
        return toAjax(iPageSettingService.updateByBo(bo));
    }

    /**
     * 删除页面配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:pageSetting:remove")
    @Log(title = "页面配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPageSettingService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
