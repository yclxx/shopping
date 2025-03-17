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
import org.dromara.shopping.domain.bo.PageBo;
import org.dromara.shopping.domain.vo.PageVo;
import org.dromara.shopping.service.IPageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 页面控制器
 * 前端访问路由地址为:/zlyyh/page
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/page")
public class PageController extends BaseController {

    private final IPageService iPageService;

    /**
     * 查询页面列表
     */
    @SaCheckPermission("zlyyh:page:list")
    @GetMapping("/list")
    public TableDataInfo<PageVo> list(PageBo bo, PageQuery pageQuery) {
        return iPageService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出页面列表
     */
    @SaCheckPermission("zlyyh:page:export")
    @Log(title = "页面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PageBo bo, HttpServletResponse response) {
        List<PageVo> list = iPageService.queryList(bo);
        ExcelUtil.exportExcel(list, "页面", PageVo.class, response);
    }

    /**
     * 获取页面详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:page:query")
    @GetMapping("/{id}")
    public R<PageVo> getInfo(@PathVariable Long id) {
        return R.ok(iPageService.queryById(id));
    }

    /**
     * 新增页面
     */
    @SaCheckPermission("zlyyh:page:add")
    @Log(title = "页面", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PageBo bo) {
        return toAjax(iPageService.insertByBo(bo));
    }

    /**
     * 修改页面
     */
    @SaCheckPermission("zlyyh:page:edit")
    @Log(title = "页面", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PageBo bo) {
        return toAjax(iPageService.updateByBo(bo));
    }

    /**
     * 删除页面
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:page:remove")
    @Log(title = "页面", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPageService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
