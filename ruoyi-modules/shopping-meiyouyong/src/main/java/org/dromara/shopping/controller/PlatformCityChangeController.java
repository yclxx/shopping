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
import org.dromara.shopping.domain.bo.PlatformCityChangeBo;
import org.dromara.shopping.domain.vo.PlatformCityChangeVo;
import org.dromara.shopping.service.IPlatformCityChangeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台切换控制器
 * 前端访问路由地址为:/zlyyh/platformCityChange
 *
 * @author yzg
 * @date 2024-03-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformCityChange")
public class PlatformCityChangeController extends BaseController {

    private final IPlatformCityChangeService iPlatformCityChangeService;

    /**
     * 查询平台切换列表
     */
    @SaCheckPermission("zlyyh:platformCityChange:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformCityChangeVo> list(PlatformCityChangeBo bo, PageQuery pageQuery) {
        return iPlatformCityChangeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台切换列表
     */
    @SaCheckPermission("zlyyh:platformCityChange:export")
    @Log(title = "平台切换", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformCityChangeBo bo, HttpServletResponse response) {
        List<PlatformCityChangeVo> list = iPlatformCityChangeService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台切换", PlatformCityChangeVo.class, response);
    }

    /**
     * 获取平台切换详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformCityChange:query")
    @GetMapping("/{id}")
    public R<PlatformCityChangeVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformCityChangeService.queryById(id));
    }

    /**
     * 新增平台切换
     */
    @SaCheckPermission("zlyyh:platformCityChange:add")
    @Log(title = "平台切换", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformCityChangeBo bo) {
        return toAjax(iPlatformCityChangeService.insertByBo(bo));
    }

    /**
     * 修改平台切换
     */
    @SaCheckPermission("zlyyh:platformCityChange:edit")
    @Log(title = "平台切换", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformCityChangeBo bo) {
        return toAjax(iPlatformCityChangeService.updateByBo(bo));
    }

    /**
     * 删除平台切换
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformCityChange:remove")
    @Log(title = "平台切换", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformCityChangeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
