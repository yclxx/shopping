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
import org.dromara.shopping.domain.bo.MissionGroupBo;
import org.dromara.shopping.domain.vo.MissionGroupVo;
import org.dromara.shopping.service.IMissionGroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 任务组控制器
 * 前端访问路由地址为:/zlyyh/missionGroup
 *
 * @author yzg
 * @date 2023-05-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/missionGroup")
public class MissionGroupController extends BaseController {

    private final IMissionGroupService iMissionGroupService;

    /**
     * 查询任务组列表
     */
    @SaCheckPermission("zlyyh:missionGroup:list")
    @GetMapping("/list")
    public TableDataInfo<MissionGroupVo> list(MissionGroupBo bo, PageQuery pageQuery) {
        return iMissionGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出任务组列表
     */
    @SaCheckPermission("zlyyh:missionGroup:export")
    @Log(title = "任务组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MissionGroupBo bo, HttpServletResponse response) {
        List<MissionGroupVo> list = iMissionGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "任务组", MissionGroupVo.class, response);
    }

    /**
     * 获取任务组详细信息
     *
     * @param missionGroupId 主键
     */
    @SaCheckPermission("zlyyh:missionGroup:query")
    @GetMapping("/{missionGroupId}")
    public R<MissionGroupVo> getInfo(@PathVariable Long missionGroupId) {
        return R.ok(iMissionGroupService.queryById(missionGroupId));
    }

    /**
     * 新增任务组
     */
    @SaCheckPermission("zlyyh:missionGroup:add")
    @Log(title = "任务组", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MissionGroupBo bo) {
        return toAjax(iMissionGroupService.insertByBo(bo));
    }

    /**
     * 修改任务组
     */
    @SaCheckPermission("zlyyh:missionGroup:edit")
    @Log(title = "任务组", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MissionGroupBo bo) {
        return toAjax(iMissionGroupService.updateByBo(bo));
    }

    /**
     * 删除任务组
     *
     * @param missionGroupIds 主键串
     */
    @SaCheckPermission("zlyyh:missionGroup:remove")
    @Log(title = "任务组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{missionGroupIds}")
    public R<Void> remove(@PathVariable Long[] missionGroupIds) {
        return toAjax(iMissionGroupService.deleteWithValidByIds(Arrays.asList(missionGroupIds), true));
    }
}
