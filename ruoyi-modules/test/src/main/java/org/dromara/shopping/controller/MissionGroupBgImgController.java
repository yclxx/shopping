package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.MissionGroupBgImgBo;
import org.dromara.shopping.domain.vo.MissionGroupBgImgVo;
import org.dromara.shopping.service.IMissionGroupBgImgService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.dromara.common.log.annotation.Log;

import java.util.Arrays;
import java.util.List;

/**
 * 任务组背景图片配置控制器
 * 前端访问路由地址为:/zlyyh/missionGroupBgImg
 *
 * @author yzg
 * @date 2024-01-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/missionGroupBgImg")
public class MissionGroupBgImgController extends BaseController {

    private final IMissionGroupBgImgService iMissionGroupBgImgService;

    /**
     * 查询任务组背景图片配置列表
     */
    @SaCheckPermission("zlyyh:missionGroupBgImg:list")
    @GetMapping("/list")
    public TableDataInfo<MissionGroupBgImgVo> list(MissionGroupBgImgBo bo, PageQuery pageQuery) {
        return iMissionGroupBgImgService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出任务组背景图片配置列表
     */
    @SaCheckPermission("zlyyh:missionGroupBgImg:export")
    @Log(title = "任务组背景图片配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MissionGroupBgImgBo bo, HttpServletResponse response) {
        List<MissionGroupBgImgVo> list = iMissionGroupBgImgService.queryList(bo);
        ExcelUtil.exportExcel(list, "任务组背景图片配置", MissionGroupBgImgVo.class, response);
    }

    /**
     * 获取任务组背景图片配置详细信息
     *
     * @param missionGroupId 主键
     */
    @SaCheckPermission("zlyyh:missionGroupBgImg:query")
    @GetMapping("/{missionGroupId}")
    public R<MissionGroupBgImgVo> getInfo(@PathVariable Long missionGroupId) {
        return R.ok(iMissionGroupBgImgService.queryById(missionGroupId));
    }

    /**
     * 新增任务组背景图片配置
     */
    @SaCheckPermission("zlyyh:missionGroupBgImg:add")
    @Log(title = "任务组背景图片配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MissionGroupBgImgBo bo) {
        return toAjax(iMissionGroupBgImgService.insertByBo(bo));
    }

    /**
     * 修改任务组背景图片配置
     */
    @SaCheckPermission("zlyyh:missionGroupBgImg:edit")
    @Log(title = "任务组背景图片配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MissionGroupBgImgBo bo) {
        return toAjax(iMissionGroupBgImgService.updateByBo(bo));
    }

    /**
     * 删除任务组背景图片配置
     *
     * @param missionGroupIds 主键串
     */
    @SaCheckPermission("zlyyh:missionGroupBgImg:remove")
    @Log(title = "任务组背景图片配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{missionGroupIds}")
    public R<Void> remove(@PathVariable Long[] missionGroupIds) {
        return toAjax(iMissionGroupBgImgService.deleteWithValidByIds(Arrays.asList(missionGroupIds), true));
    }
}
