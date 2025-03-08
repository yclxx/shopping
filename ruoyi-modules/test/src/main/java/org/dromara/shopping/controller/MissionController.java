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
import org.dromara.shopping.domain.bo.MissionBo;
import org.dromara.shopping.domain.vo.MissionVo;
import org.dromara.shopping.service.IMissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 任务配置控制器
 * 前端访问路由地址为:/zlyyh/mission
 *
 * @author yzg
 * @date 2023-05-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/mission")
public class MissionController extends BaseController {

    private final IMissionService iMissionService;

    /**
     * 查询任务配置列表
     */
    @SaCheckPermission("zlyyh:mission:list")
    @GetMapping("/list")
    public TableDataInfo<MissionVo> list(MissionBo bo, PageQuery pageQuery) {
        return iMissionService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出任务配置列表
     */
    @SaCheckPermission("zlyyh:mission:export")
    @Log(title = "任务配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MissionBo bo, HttpServletResponse response) {
        List<MissionVo> list = iMissionService.queryList(bo);
        ExcelUtil.exportExcel(list, "任务配置", MissionVo.class, response);
    }

    /**
     * 获取任务配置详细信息
     *
     * @param missionId 主键
     */
    @SaCheckPermission("zlyyh:mission:query")
    @GetMapping("/{missionId}")
    public R<MissionVo> getInfo(@PathVariable Long missionId) {
        return R.ok(iMissionService.queryById(missionId));
    }

    /**
     * 新增任务配置
     */
    @SaCheckPermission("zlyyh:mission:add")
    @Log(title = "任务配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MissionBo bo) {
        return toAjax(iMissionService.insertByBo(bo));
    }

    /**
     * 修改任务配置
     */
    @SaCheckPermission("zlyyh:mission:edit")
    @Log(title = "任务配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MissionBo bo) {
        return toAjax(iMissionService.updateByBo(bo));
    }

    /**
     * 删除任务配置
     *
     * @param missionIds 主键串
     */
    @SaCheckPermission("zlyyh:mission:remove")
    @Log(title = "任务配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{missionIds}")
    public R<Void> remove(@PathVariable Long[] missionIds) {
        return toAjax(iMissionService.deleteWithValidByIds(Arrays.asList(missionIds), true));
    }
}
