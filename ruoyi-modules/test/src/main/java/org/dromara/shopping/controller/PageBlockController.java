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
import org.dromara.shopping.domain.bo.PageBlockBo;
import org.dromara.shopping.domain.vo.PageBlockVo;
import org.dromara.shopping.service.IPageBlockService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 版块模板控制器
 * 前端访问路由地址为:/zlyyh/pageBlock
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pageBlock")
public class PageBlockController extends BaseController {

    private final IPageBlockService iPageBlockService;

    /**
     * 查询版块模板列表
     */
    @SaCheckPermission("zlyyh:pageBlock:list")
    @GetMapping("/list")
    public TableDataInfo<PageBlockVo> list(PageBlockBo bo, PageQuery pageQuery) {
        return iPageBlockService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出版块模板列表
     */
    @SaCheckPermission("zlyyh:pageBlock:export")
    @Log(title = "版块模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PageBlockBo bo, HttpServletResponse response) {
        List<PageBlockVo> list = iPageBlockService.queryList(bo);
        ExcelUtil.exportExcel(list, "版块模板", PageBlockVo.class, response);
    }

    /**
     * 获取版块模板详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:pageBlock:query")
    @GetMapping("/{id}")
    public R<PageBlockVo> getInfo(@PathVariable Long id) {
        return R.ok(iPageBlockService.queryById(id));
    }

    /**
     * 新增版块模板
     */
    @SaCheckPermission("zlyyh:pageBlock:add")
    @Log(title = "版块模板", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PageBlockBo bo) {
        return toAjax(iPageBlockService.insertByBo(bo));
    }

    /**
     * 修改版块模板
     */
    @SaCheckPermission("zlyyh:pageBlock:edit")
    @Log(title = "版块模板", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PageBlockBo bo) {
        return toAjax(iPageBlockService.updateByBo(bo));
    }

    /**
     * 删除版块模板
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:pageBlock:remove")
    @Log(title = "版块模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPageBlockService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
