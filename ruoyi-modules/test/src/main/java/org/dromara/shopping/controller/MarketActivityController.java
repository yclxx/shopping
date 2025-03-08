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
import org.dromara.shopping.domain.bo.MarketActivityBo;
import org.dromara.shopping.domain.vo.MarketActivityVo;
import org.dromara.shopping.service.IMarketActivityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 营销活动控制器
 * 前端访问路由地址为:/zlyyh/marketActivity
 *
 * @author yzg
 * @date 2023-12-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/marketActivity")
public class MarketActivityController extends BaseController {

    private final IMarketActivityService iMarketActivityService;

    /**
     * 查询营销活动列表
     */
    @SaCheckPermission("zlyyh:marketActivity:list")
    @GetMapping("/list")
    public TableDataInfo<MarketActivityVo> list(MarketActivityBo bo, PageQuery pageQuery) {
        return iMarketActivityService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出营销活动列表
     */
    @SaCheckPermission("zlyyh:marketActivity:export")
    @Log(title = "营销活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MarketActivityBo bo, HttpServletResponse response) {
        List<MarketActivityVo> list = iMarketActivityService.queryList(bo);
        ExcelUtil.exportExcel(list, "营销活动", MarketActivityVo.class, response);
    }

    /**
     * 获取营销活动详细信息
     *
     * @param activityId 主键
     */
    @SaCheckPermission("zlyyh:marketActivity:query")
    @GetMapping("/{activityId}")
    public R<MarketActivityVo> getInfo(@PathVariable Long activityId) {
        return R.ok(iMarketActivityService.queryById(activityId));
    }

    /**
     * 新增营销活动
     */
    @SaCheckPermission("zlyyh:marketActivity:add")
    @Log(title = "营销活动", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MarketActivityBo bo) {
        return toAjax(iMarketActivityService.insertByBo(bo));
    }

    /**
     * 修改营销活动
     */
    @SaCheckPermission("zlyyh:marketActivity:edit")
    @Log(title = "营销活动", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MarketActivityBo bo) {
        return toAjax(iMarketActivityService.updateByBo(bo));
    }

    /**
     * 删除营销活动
     *
     * @param activityIds 主键串
     */
    @SaCheckPermission("zlyyh:marketActivity:remove")
    @Log(title = "营销活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public R<Void> remove(@PathVariable Long[] activityIds) {
        return toAjax(iMarketActivityService.deleteWithValidByIds(Arrays.asList(activityIds), true));
    }
}
