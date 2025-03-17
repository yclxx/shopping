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
import org.dromara.shopping.domain.bo.PlatformCityIndexBo;
import org.dromara.shopping.domain.vo.PlatformCityIndexVo;
import org.dromara.shopping.service.IPlatformCityIndexService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义首页控制器
 * 前端访问路由地址为:/zlyyh/platformCityIndex
 *
 * @author yzg
 * @date 2023-08-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformCityIndex")
public class PlatformCityIndexController extends BaseController {

    private final IPlatformCityIndexService iPlatformCityIndexService;

    /**
     * 查询自定义首页列表
     */
    @SaCheckPermission("zlyyh:platformCityIndex:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformCityIndexVo> list(PlatformCityIndexBo bo, PageQuery pageQuery) {
        return iPlatformCityIndexService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出自定义首页列表
     */
    @SaCheckPermission("zlyyh:platformCityIndex:export")
    @Log(title = "自定义首页", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformCityIndexBo bo, HttpServletResponse response) {
//        JSONObject
        List<PlatformCityIndexVo> list = iPlatformCityIndexService.queryList(bo);
        ExcelUtil.exportExcel(list, "自定义首页", PlatformCityIndexVo.class, response);
    }

    /**
     * 获取自定义首页详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformCityIndex:query")
    @GetMapping("/{id}")
    public R<PlatformCityIndexVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformCityIndexService.queryById(id));
    }

    /**
     * 新增自定义首页
     */
    @SaCheckPermission("zlyyh:platformCityIndex:add")
    @Log(title = "自定义首页", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformCityIndexBo bo) {
        return toAjax(iPlatformCityIndexService.insertByBo(bo));
    }

    /**
     * 修改自定义首页
     */
    @SaCheckPermission("zlyyh:platformCityIndex:edit")
    @Log(title = "自定义首页", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformCityIndexBo bo) {
        return toAjax(iPlatformCityIndexService.updateByBo(bo));
    }

    /**
     * 删除自定义首页
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformCityIndex:remove")
    @Log(title = "自定义首页", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformCityIndexService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
