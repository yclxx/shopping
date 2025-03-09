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
import org.dromara.shopping.domain.bo.PresidentBo;
import org.dromara.shopping.domain.vo.PresidentVo;
import org.dromara.shopping.service.IPresidentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 支行长控制器
 * 前端访问路由地址为:/zlyyh/president
 *
 * @author yzg
 * @date 2024-04-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/president")
public class PresidentController extends BaseController {

    private final IPresidentService iPresidentService;

    /**
     * 查询支行长列表
     */
    @SaCheckPermission("zlyyh:president:list")
    @GetMapping("/list")
    public TableDataInfo<PresidentVo> list(PresidentBo bo, PageQuery pageQuery) {
        return iPresidentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出支行长列表
     */
    @SaCheckPermission("zlyyh:president:export")
    @Log(title = "支行长", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PresidentBo bo, HttpServletResponse response) {
        List<PresidentVo> list = iPresidentService.queryList(bo);
        ExcelUtil.exportExcel(list, "支行长", PresidentVo.class, response);
    }

    /**
     * 获取支行长详细信息
     *
     * @param presidentId 主键
     */
    @SaCheckPermission("zlyyh:president:query")
    @GetMapping("/{presidentId}")
    public R<PresidentVo> getInfo(@PathVariable Long presidentId) {
        return R.ok(iPresidentService.queryById(presidentId));
    }

    /**
     * 新增支行长
     */
    @SaCheckPermission("zlyyh:president:add")
    @Log(title = "支行长", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PresidentBo bo) {
        return toAjax(iPresidentService.insertByBo(bo));
    }

    /**
     * 修改支行长
     */
    @SaCheckPermission("zlyyh:president:edit")
    @Log(title = "支行长", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PresidentBo bo) {
        return toAjax(iPresidentService.updateByBo(bo));
    }

    /**
     * 删除支行长
     *
     * @param presidentIds 主键串
     */
    @SaCheckPermission("zlyyh:president:remove")
    @Log(title = "支行长", businessType = BusinessType.DELETE)
    @DeleteMapping("/{presidentIds}")
    public R<Void> remove(@PathVariable Long[] presidentIds) {
        return toAjax(iPresidentService.deleteWithValidByIds(Arrays.asList(presidentIds), true));
    }
}
