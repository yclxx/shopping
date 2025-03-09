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
import org.dromara.shopping.domain.bo.PlatformProductRuleListBo;
import org.dromara.shopping.domain.vo.PlatformProductRuleListVo;
import org.dromara.shopping.service.IPlatformProductRuleListService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 黑白名单控制器
 * 前端访问路由地址为:/zlyyh/platformProductRuleList
 *
 * @author yzg
 * @date 2024-06-24
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformProductRuleList")
public class PlatformProductRuleListController extends BaseController {

    private final IPlatformProductRuleListService iPlatformProductRuleListService;

    /**
     * 查询黑白名单列表
     */
    @SaCheckPermission("zlyyh:platformProductRuleList:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformProductRuleListVo> list(PlatformProductRuleListBo bo, PageQuery pageQuery) {
        TableDataInfo<PlatformProductRuleListVo> platformProductRuleListVoTableDataInfo = iPlatformProductRuleListService.queryPageList(bo, pageQuery);
        return platformProductRuleListVoTableDataInfo;
    }

    /**
     * 导出黑白名单列表
     */
    @SaCheckPermission("zlyyh:platformProductRuleList:export")
    @Log(title = "黑白名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformProductRuleListBo bo, HttpServletResponse response) {
        List<PlatformProductRuleListVo> list = iPlatformProductRuleListService.queryList(bo);
        ExcelUtil.exportExcel(list, "黑白名单", PlatformProductRuleListVo.class, response);
    }

    /**
     * 获取黑白名单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformProductRuleList:query")
    @GetMapping("/{id}")
    public R<PlatformProductRuleListVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformProductRuleListService.queryById(id));
    }

    /**
     * 新增黑白名单
     */
    @SaCheckPermission("zlyyh:platformProductRuleList:add")
    @Log(title = "黑白名单", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformProductRuleListBo bo) {
        return toAjax(iPlatformProductRuleListService.insertByBo(bo));
    }

    /**
     * 修改黑白名单
     */
    @SaCheckPermission("zlyyh:platformProductRuleList:edit")
    @Log(title = "黑白名单", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformProductRuleListBo bo) {
        return toAjax(iPlatformProductRuleListService.updateByBo(bo));
    }

    /**
     * 删除黑白名单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformProductRuleList:remove")
    @Log(title = "黑白名单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformProductRuleListService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
