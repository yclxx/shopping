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
import org.dromara.shopping.domain.bo.PlatformCityGroupBo;
import org.dromara.shopping.domain.vo.PlatformCityGroupVo;
import org.dromara.shopping.service.IPlatformCityGroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台城市企业微信群控制器
 * 前端访问路由地址为:/zlyyh/platformCityGroup
 *
 * @author yzg
 * @date 2024-02-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformCityGroup")
public class PlatformCityGroupController extends BaseController {

    private final IPlatformCityGroupService iPlatformCityGroupService;

    /**
     * 查询平台城市企业微信群列表
     */
    @SaCheckPermission("zlyyh:platformCityGroup:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformCityGroupVo> list(PlatformCityGroupBo bo, PageQuery pageQuery) {
        return iPlatformCityGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台城市企业微信群列表
     */
    @SaCheckPermission("zlyyh:platformCityGroup:export")
    @Log(title = "平台城市企业微信群", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformCityGroupBo bo, HttpServletResponse response) {
        List<PlatformCityGroupVo> list = iPlatformCityGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台城市企业微信群", PlatformCityGroupVo.class, response);
    }

    /**
     * 获取平台城市企业微信群详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformCityGroup:query")
    @GetMapping("/{id}")
    public R<PlatformCityGroupVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformCityGroupService.queryById(id));
    }

    /**
     * 新增平台城市企业微信群
     */
    @SaCheckPermission("zlyyh:platformCityGroup:add")
    @Log(title = "平台城市企业微信群", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformCityGroupBo bo) {
        return toAjax(iPlatformCityGroupService.insertByBo(bo));
    }

    /**
     * 修改平台城市企业微信群
     */
    @SaCheckPermission("zlyyh:platformCityGroup:edit")
    @Log(title = "平台城市企业微信群", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformCityGroupBo bo) {
        return toAjax(iPlatformCityGroupService.updateByBo(bo));
    }

    /**
     * 删除平台城市企业微信群
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformCityGroup:remove")
    @Log(title = "平台城市企业微信群", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformCityGroupService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
