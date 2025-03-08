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
import org.dromara.shopping.domain.bo.HotNewsBo;
import org.dromara.shopping.domain.vo.HotNewsVo;
import org.dromara.shopping.service.IHotNewsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 热门搜索配置控制器
 * 前端访问路由地址为:/zlyyh/hotNews
 *
 * @author yzg
 * @date 2023-07-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotNews")
public class HotNewsController extends BaseController {

    private final IHotNewsService iHotNewsService;

    /**
     * 查询热门搜索配置列表
     */
    @SaCheckPermission("zlyyh:hotNews:list")
    @GetMapping("/list")
    public TableDataInfo<HotNewsVo> list(HotNewsBo bo, PageQuery pageQuery) {
        return iHotNewsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出热门搜索配置列表
     */
    @SaCheckPermission("zlyyh:hotNews:export")
    @Log(title = "热门搜索配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HotNewsBo bo, HttpServletResponse response) {
        List<HotNewsVo> list = iHotNewsService.queryList(bo);
        ExcelUtil.exportExcel(list, "热门搜索配置", HotNewsVo.class, response);
    }

    /**
     * 获取热门搜索配置详细信息
     *
     * @param newsId 主键
     */
    @SaCheckPermission("zlyyh:hotNews:query")
    @GetMapping("/{newsId}")
    public R<HotNewsVo> getInfo(@PathVariable Long newsId) {
        return R.ok(iHotNewsService.queryById(newsId));
    }

    /**
     * 新增热门搜索配置
     */
    @SaCheckPermission("zlyyh:hotNews:add")
    @Log(title = "热门搜索配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HotNewsBo bo) {
        return toAjax(iHotNewsService.insertByBo(bo));
    }

    /**
     * 修改热门搜索配置
     */
    @SaCheckPermission("zlyyh:hotNews:edit")
    @Log(title = "热门搜索配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HotNewsBo bo) {
        return toAjax(iHotNewsService.updateByBo(bo));
    }

    /**
     * 删除热门搜索配置
     *
     * @param newsIds 主键串
     */
    @SaCheckPermission("zlyyh:hotNews:remove")
    @Log(title = "热门搜索配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{newsIds}")
    public R<Void> remove(@PathVariable Long[] newsIds) {
        return toAjax(iHotNewsService.deleteWithValidByIds(Arrays.asList(newsIds), true));
    }
}
