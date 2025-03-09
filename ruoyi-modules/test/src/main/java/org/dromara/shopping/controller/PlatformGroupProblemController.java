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
import org.dromara.shopping.domain.bo.PlatformGroupProblemBo;
import org.dromara.shopping.domain.vo.PlatformGroupProblemVo;
import org.dromara.shopping.service.IPlatformGroupProblemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 用户入群问题反馈控制器
 * 前端访问路由地址为:/zlyyh/platformGroupProblem
 *
 * @author yzg
 * @date 2024-02-22
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformGroupProblem")
public class PlatformGroupProblemController extends BaseController {

    private final IPlatformGroupProblemService iPlatformGroupProblemService;

    /**
     * 查询用户入群问题反馈列表
     */
    @SaCheckPermission("zlyyh:platformGroupProblem:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformGroupProblemVo> list(PlatformGroupProblemBo bo, PageQuery pageQuery) {
        return iPlatformGroupProblemService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户入群问题反馈列表
     */
    @SaCheckPermission("zlyyh:platformGroupProblem:export")
    @Log(title = "用户入群问题反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformGroupProblemBo bo, HttpServletResponse response) {
        List<PlatformGroupProblemVo> list = iPlatformGroupProblemService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户入群问题反馈", PlatformGroupProblemVo.class, response);
    }

    /**
     * 获取用户入群问题反馈详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformGroupProblem:query")
    @GetMapping("/{id}")
    public R<PlatformGroupProblemVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformGroupProblemService.queryById(id));
    }

    /**
     * 新增用户入群问题反馈
     */
    @SaCheckPermission("zlyyh:platformGroupProblem:add")
    @Log(title = "用户入群问题反馈", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformGroupProblemBo bo) {
        return toAjax(iPlatformGroupProblemService.insertByBo(bo));
    }

    /**
     * 修改用户入群问题反馈
     */
    @SaCheckPermission("zlyyh:platformGroupProblem:edit")
    @Log(title = "用户入群问题反馈", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformGroupProblemBo bo) {
        return toAjax(iPlatformGroupProblemService.updateByBo(bo));
    }

    /**
     * 删除用户入群问题反馈
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformGroupProblem:remove")
    @Log(title = "用户入群问题反馈", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformGroupProblemService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
