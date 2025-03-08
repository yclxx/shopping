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
import org.dromara.shopping.domain.bo.CommercialActivityApplyBo;
import org.dromara.shopping.domain.vo.CommercialActivityApplyVo;
import org.dromara.shopping.service.ICommercialActivityApplyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户活动报名控制器
 * 前端访问路由地址为:/zlyyh/commercialActivityApply
 *
 * @author yzg
 * @date 2024-04-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/commercialActivityApply")
public class CommercialActivityApplyController extends BaseController {

    private final ICommercialActivityApplyService iCommercialActivityApplyService;

    /**
     * 查询商户活动报名列表
     */
    @SaCheckPermission("zlyyh:commercialActivityApply:list")
    @GetMapping("/list")
    public TableDataInfo<CommercialActivityApplyVo> list(CommercialActivityApplyBo bo, PageQuery pageQuery) {
        return iCommercialActivityApplyService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商户活动报名列表
     */
    @SaCheckPermission("zlyyh:commercialActivityApply:export")
    @Log(title = "商户活动报名", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommercialActivityApplyBo bo, HttpServletResponse response) {
        List<CommercialActivityApplyVo> list = iCommercialActivityApplyService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户活动报名", CommercialActivityApplyVo.class, response);
    }

    /**
     * 获取商户活动报名详细信息
     *
     * @param applyId 主键
     */
    @SaCheckPermission("zlyyh:commercialActivityApply:query")
    @GetMapping("/{applyId}")
    public R<CommercialActivityApplyVo> getInfo(@PathVariable Long applyId) {
        return R.ok(iCommercialActivityApplyService.queryById(applyId));
    }

    /**
     * 新增商户活动报名
     */
    @SaCheckPermission("zlyyh:commercialActivityApply:add")
    @Log(title = "商户活动报名", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CommercialActivityApplyBo bo) {
        return toAjax(iCommercialActivityApplyService.insertByBo(bo));
    }

    /**
     * 修改商户活动报名
     */
    @SaCheckPermission("zlyyh:commercialActivityApply:edit")
    @Log(title = "商户活动报名", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CommercialActivityApplyBo bo) {
        return toAjax(iCommercialActivityApplyService.updateByBo(bo));
    }

    /**
     * 删除商户活动报名
     *
     * @param applyIds 主键串
     */
    @SaCheckPermission("zlyyh:commercialActivityApply:remove")
    @Log(title = "商户活动报名", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyIds}")
    public R<Void> remove(@PathVariable Long[] applyIds) {
        return toAjax(iCommercialActivityApplyService.deleteWithValidByIds(Arrays.asList(applyIds), true));
    }
}
