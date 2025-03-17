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
import org.dromara.shopping.domain.bo.PlatformProductRuleBo;
import org.dromara.shopping.domain.vo.PlatformProductRuleVo;
import org.dromara.shopping.service.IPlatformProductRuleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商品规则控制器
 * 前端访问路由地址为:/zlyyh/platformProductRule
 *
 * @author yzg
 * @date 2024-06-24
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformProductRule")
public class PlatformProductRuleController extends BaseController {

    private final IPlatformProductRuleService iPlatformProductRuleService;

    /**
     * 查询商品规则列表
     */
    @SaCheckPermission("zlyyh:platformProductRule:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformProductRuleVo> list(PlatformProductRuleBo bo, PageQuery pageQuery) {
        return iPlatformProductRuleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品规则列表
     */
    @SaCheckPermission("zlyyh:platformProductRule:export")
    @Log(title = "商品规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformProductRuleBo bo, HttpServletResponse response) {
        List<PlatformProductRuleVo> list = iPlatformProductRuleService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品规则", PlatformProductRuleVo.class, response);
    }

    /**
     * 获取商品规则详细信息
     *
     * @param platformKey 主键
     */
    @SaCheckPermission("zlyyh:platformProductRule:query")
    @GetMapping("/{platformKey}")
    public R<PlatformProductRuleVo> getInfo(@PathVariable Long platformKey) {
        return R.ok(iPlatformProductRuleService.queryById(platformKey));
    }

    /**
     * 新增商品规则
     */
    @SaCheckPermission("zlyyh:platformProductRule:add")
    @Log(title = "商品规则", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformProductRuleBo bo) {
        return toAjax(iPlatformProductRuleService.insertByBo(bo));
    }

    /**
     * 修改商品规则
     */
    @SaCheckPermission("zlyyh:platformProductRule:edit")
    @Log(title = "商品规则", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformProductRuleBo bo) {
        return toAjax(iPlatformProductRuleService.updateByBo(bo));
    }

    /**
     * 删除商品规则
     *
     * @param platformKeys 主键串
     */
    @SaCheckPermission("zlyyh:platformProductRule:remove")
    @Log(title = "商品规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{platformKeys}")
    public R<Void> remove(@PathVariable Long[] platformKeys) {
        return toAjax(iPlatformProductRuleService.deleteWithValidByIds(Arrays.asList(platformKeys), true));
    }
}
