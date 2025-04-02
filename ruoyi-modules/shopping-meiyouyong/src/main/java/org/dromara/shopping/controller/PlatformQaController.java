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
import org.dromara.shopping.domain.bo.PlatformQaBo;
import org.dromara.shopping.domain.vo.PlatformQaVo;
import org.dromara.shopping.service.IPlatformQaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台常见问题控制器
 * 前端访问路由地址为:/zlyyh/platformQa
 *
 * @author yzg
 * @date 2025-04-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformQa")
public class PlatformQaController extends BaseController {

    private final IPlatformQaService iPlatformQaService;

    /**
     * 查询平台常见问题列表
     */
    @SaCheckPermission("zlyyh:platformQa:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformQaVo> list(PlatformQaBo bo, PageQuery pageQuery) {
        return iPlatformQaService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台常见问题列表
     */
    @SaCheckPermission("zlyyh:platformQa:export")
    @PostMapping("/export")
    public void export(PlatformQaBo bo, HttpServletResponse response) {
        List<PlatformQaVo> list = iPlatformQaService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台常见问题", PlatformQaVo.class, response);
    }

    /**
     * 获取平台常见问题详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformQa:query")
    @GetMapping("/{id}")
    public R<PlatformQaVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformQaService.queryById(id));
    }

    /**
     * 新增平台常见问题
     */
    @SaCheckPermission("zlyyh:platformQa:add")
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformQaBo bo) {
        return toAjax(iPlatformQaService.insertByBo(bo));
    }

    /**
     * 修改平台常见问题
     */
    @SaCheckPermission("zlyyh:platformQa:edit")
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformQaBo bo) {
        return toAjax(iPlatformQaService.updateByBo(bo));
    }

    /**
     * 删除平台常见问题
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformQa:remove")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformQaService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
