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
import org.dromara.shopping.domain.bo.PageBlockColumnBo;
import org.dromara.shopping.domain.vo.PageBlockColumnVo;
import org.dromara.shopping.service.IPageBlockColumnService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 版块模板字段控制器
 * 前端访问路由地址为:/zlyyh/pageBlockColumn
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pageBlockColumn")
public class PageBlockColumnController extends BaseController {

    private final IPageBlockColumnService iPageBlockColumnService;

    /**
     * 查询版块模板字段列表
     */
    @SaCheckPermission("zlyyh:pageBlockColumn:list")
    @GetMapping("/list")
    public TableDataInfo<PageBlockColumnVo> list(PageBlockColumnBo bo, PageQuery pageQuery) {
        return iPageBlockColumnService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出版块模板字段列表
     */
    @SaCheckPermission("zlyyh:pageBlockColumn:export")
    @Log(title = "版块模板字段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PageBlockColumnBo bo, HttpServletResponse response) {
        List<PageBlockColumnVo> list = iPageBlockColumnService.queryList(bo);
        ExcelUtil.exportExcel(list, "版块模板字段", PageBlockColumnVo.class, response);
    }

    /**
     * 获取版块模板字段详细信息
     *
     * @param columnId 主键
     */
    @SaCheckPermission("zlyyh:pageBlockColumn:query")
    @GetMapping("/{columnId}")
    public R<PageBlockColumnVo> getInfo(@PathVariable Long columnId) {
        return R.ok(iPageBlockColumnService.queryById(columnId));
    }

    /**
     * 查询模板字段
     * @param blockId 模板编号
     * @return
     */
    @GetMapping("/selectListByBlockId/{blockId}")
    public R<List<PageBlockColumnVo>> selectListByBlockId(@PathVariable Long blockId){
        return R.ok(iPageBlockColumnService.selectListByBlockId(blockId));
    }

    /**
     * 新增版块模板字段
     */
    @SaCheckPermission("zlyyh:pageBlockColumn:add")
    @Log(title = "版块模板字段", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PageBlockColumnBo bo) {
        return toAjax(iPageBlockColumnService.insertByBo(bo));
    }

    /**
     * 修改版块模板字段
     */
    @SaCheckPermission("zlyyh:pageBlockColumn:edit")
    @Log(title = "版块模板字段", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PageBlockColumnBo bo) {
        return toAjax(iPageBlockColumnService.updateByBo(bo));
    }

    /**
     * 删除版块模板字段
     *
     * @param columnIds 主键串
     */
    @SaCheckPermission("zlyyh:pageBlockColumn:remove")
    @Log(title = "版块模板字段", businessType = BusinessType.DELETE)
    @DeleteMapping("/{columnIds}")
    public R<Void> remove(@PathVariable Long[] columnIds) {
        return toAjax(iPageBlockColumnService.deleteWithValidByIds(Arrays.asList(columnIds), true));
    }
}
