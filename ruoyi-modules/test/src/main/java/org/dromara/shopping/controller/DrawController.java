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
import org.dromara.shopping.domain.bo.DrawBo;
import org.dromara.shopping.domain.vo.DrawVo;
import org.dromara.shopping.service.IDrawService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 奖品管理控制器
 * 前端访问路由地址为:/zlyyh/draw
 *
 * @author yzg
 * @date 2023-05-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/draw")
public class DrawController extends BaseController {

    private final IDrawService iDrawService;

    /**
     * 查询奖品管理列表
     */
    @SaCheckPermission("zlyyh:draw:list")
    @GetMapping("/list")
    public TableDataInfo<DrawVo> list(DrawBo bo, PageQuery pageQuery) {
        return iDrawService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出奖品管理列表
     */
    @SaCheckPermission("zlyyh:draw:export")
    @Log(title = "奖品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DrawBo bo, HttpServletResponse response) {
        List<DrawVo> list = iDrawService.queryList(bo);
        ExcelUtil.exportExcel(list, "奖品管理", DrawVo.class, response);
    }

    /**
     * 获取奖品管理详细信息
     *
     * @param drawId 主键
     */
    @SaCheckPermission("zlyyh:draw:query")
    @GetMapping("/{drawId}")
    public R<DrawVo> getInfo(@PathVariable Long drawId) {
        return R.ok(iDrawService.queryById(drawId));
    }

    /**
     * 新增奖品管理
     */
    @SaCheckPermission("zlyyh:draw:add")
    @Log(title = "奖品管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DrawBo bo) {
        return toAjax(iDrawService.insertByBo(bo));
    }

    /**
     * 修改奖品管理
     */
    @SaCheckPermission("zlyyh:draw:edit")
    @Log(title = "奖品管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DrawBo bo) {
        return toAjax(iDrawService.updateByBo(bo));
    }

    /**
     * 删除奖品管理
     *
     * @param drawIds 主键串
     */
    @SaCheckPermission("zlyyh:draw:remove")
    @Log(title = "奖品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{drawIds}")
    public R<Void> remove(@PathVariable Long[] drawIds) {
        return toAjax(iDrawService.deleteWithValidByIds(Arrays.asList(drawIds), true));
    }
}
