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
import org.dromara.shopping.domain.bo.CommercialActivityBo;
import org.dromara.shopping.domain.vo.CommercialActivityVo;
import org.dromara.shopping.service.ICommercialActivityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户活动控制器
 * 前端访问路由地址为:/zlyyh/commercialActivity
 *
 * @author yzg
 * @date 2024-03-26
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/commercialActivity")
public class CommercialActivityController extends BaseController {

    private final ICommercialActivityService iCommercialActivityService;

    /**
     * 查询商户活动列表
     */
    @SaCheckPermission("zlyyh:commercialActivity:list")
    @GetMapping("/list")
    public TableDataInfo<CommercialActivityVo> list(CommercialActivityBo bo, PageQuery pageQuery) {
        return iCommercialActivityService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商户活动列表
     */
    @SaCheckPermission("zlyyh:commercialActivity:export")
    @Log(title = "商户活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommercialActivityBo bo, HttpServletResponse response) {
        List<CommercialActivityVo> list = iCommercialActivityService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户活动", CommercialActivityVo.class, response);
    }

    /**
     * 获取商户活动详细信息
     *
     * @param activityId 主键
     */
    @SaCheckPermission("zlyyh:commercialActivity:query")
    @GetMapping("/{activityId}")
    public R<CommercialActivityVo> getInfo(@PathVariable Long activityId) {
        return R.ok(iCommercialActivityService.queryById(activityId));
    }

    /**
     * 新增商户活动
     */
    @SaCheckPermission("zlyyh:commercialActivity:add")
    @Log(title = "商户活动", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CommercialActivityBo bo) {
        return toAjax(iCommercialActivityService.insertByBo(bo));
    }

    /**
     * 修改商户活动
     */
    @SaCheckPermission("zlyyh:commercialActivity:edit")
    @Log(title = "商户活动", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CommercialActivityBo bo) {
        return toAjax(iCommercialActivityService.updateByBo(bo));
    }

    /**
     * 删除商户活动
     *
     * @param activityIds 主键串
     */
    @SaCheckPermission("zlyyh:commercialActivity:remove")
    @Log(title = "商户活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public R<Void> remove(@PathVariable Long[] activityIds) {
        return toAjax(iCommercialActivityService.deleteWithValidByIds(Arrays.asList(activityIds), true));
    }
}
