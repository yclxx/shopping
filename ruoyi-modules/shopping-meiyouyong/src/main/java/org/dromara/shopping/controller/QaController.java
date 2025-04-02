package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.QaBo;
import org.dromara.shopping.domain.vo.QaVo;
import org.dromara.shopping.service.IQaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 常见问题控制器
 * 前端访问路由地址为:/zlyyh/qa
 *
 * @author yzg
 * @date 2025-04-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/qa")
public class QaController extends BaseController {

    private final IQaService iQaService;

    /**
     * 查询常见问题列表
     */
    @SaCheckPermission("zlyyh:qa:list")
    @GetMapping("/list")
    public TableDataInfo<QaVo> list(QaBo bo, PageQuery pageQuery) {
        return iQaService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出常见问题列表
     */
    @SaCheckPermission("zlyyh:qa:export")
    @PostMapping("/export")
    public void export(QaBo bo, HttpServletResponse response) {
        List<QaVo> list = iQaService.queryList(bo);
        ExcelUtil.exportExcel(list, "常见问题", QaVo.class, response);
    }

    /**
     * 获取常见问题详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:qa:query")
    @GetMapping("/{id}")
    public R<QaVo> getInfo(@PathVariable Long id) {
        return R.ok(iQaService.queryById(id));
    }

    /**
     * 新增常见问题
     */
    @SaCheckPermission("zlyyh:qa:add")
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QaBo bo) {
        return toAjax(iQaService.insertByBo(bo));
    }

    /**
     * 修改常见问题
     */
    @SaCheckPermission("zlyyh:qa:edit")
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QaBo bo) {
        return toAjax(iQaService.updateByBo(bo));
    }

    /**
     * 删除常见问题
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:qa:remove")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iQaService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
