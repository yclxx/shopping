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
import org.dromara.shopping.domain.bo.PlatformUserGroupBo;
import org.dromara.shopping.domain.vo.PlatformUserGroupVo;
import org.dromara.shopping.service.IPlatformUserGroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台城市企业微信用户来源控制器
 * 前端访问路由地址为:/zlyyh/platformUserGroup
 *
 * @author yzg
 * @date 2024-03-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformUserGroup")
public class PlatformUserGroupController extends BaseController {

    private final IPlatformUserGroupService iPlatformUserGroupService;

    /**
     * 查询平台城市企业微信用户来源列表
     */
    @SaCheckPermission("zlyyh:platformUserGroup:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformUserGroupVo> list(PlatformUserGroupBo bo, PageQuery pageQuery) {
        return iPlatformUserGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台城市企业微信用户来源列表
     */
    @SaCheckPermission("zlyyh:platformUserGroup:export")
    @Log(title = "平台城市企业微信用户来源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformUserGroupBo bo, HttpServletResponse response) {
        List<PlatformUserGroupVo> list = iPlatformUserGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台城市企业微信用户来源", PlatformUserGroupVo.class, response);
    }

    /**
     * 获取平台城市企业微信用户来源详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformUserGroup:query")
    @GetMapping("/{id}")
    public R<PlatformUserGroupVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformUserGroupService.queryById(id));
    }

    /**
     * 新增平台城市企业微信用户来源
     */
    @SaCheckPermission("zlyyh:platformUserGroup:add")
    @Log(title = "平台城市企业微信用户来源", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformUserGroupBo bo) {
        return toAjax(iPlatformUserGroupService.insertByBo(bo));
    }

    /**
     * 修改平台城市企业微信用户来源
     */
    @SaCheckPermission("zlyyh:platformUserGroup:edit")
    @Log(title = "平台城市企业微信用户来源", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformUserGroupBo bo) {
        return toAjax(iPlatformUserGroupService.updateByBo(bo));
    }

    /**
     * 删除平台城市企业微信用户来源
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformUserGroup:remove")
    @Log(title = "平台城市企业微信用户来源", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformUserGroupService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
