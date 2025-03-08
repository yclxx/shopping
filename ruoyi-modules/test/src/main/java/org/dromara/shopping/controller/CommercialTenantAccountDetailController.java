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
import org.dromara.shopping.domain.bo.CommercialTenantAccountDetailBo;
import org.dromara.shopping.domain.vo.CommercialTenantAccountDetailVo;
import org.dromara.shopping.service.ICommercialTenantAccountDetailService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户账户明细控制器
 * 前端访问路由地址为:/zlyyh/commercialTenantAccountDetail
 *
 * @author yzg
 * @date 2024-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/commercialTenantAccountDetail")
public class CommercialTenantAccountDetailController extends BaseController {

    private final ICommercialTenantAccountDetailService iCommercialTenantAccountDetailService;

    /**
     * 查询商户账户明细列表
     */
    @SaCheckPermission("zlyyh:commercialTenantAccountDetail:list")
    @GetMapping("/list")
    public TableDataInfo<CommercialTenantAccountDetailVo> list(CommercialTenantAccountDetailBo bo, PageQuery pageQuery) {
        return iCommercialTenantAccountDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商户账户明细列表
     */
    @SaCheckPermission("zlyyh:commercialTenantAccountDetail:export")
    @Log(title = "商户账户明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommercialTenantAccountDetailBo bo, HttpServletResponse response) {
        List<CommercialTenantAccountDetailVo> list = iCommercialTenantAccountDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户账户明细", CommercialTenantAccountDetailVo.class, response);
    }

    /**
     * 获取商户账户明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:commercialTenantAccountDetail:query")
    @GetMapping("/{id}")
    public R<CommercialTenantAccountDetailVo> getInfo(@PathVariable Long id) {
        return R.ok(iCommercialTenantAccountDetailService.queryById(id));
    }

    /**
     * 新增商户账户明细
     */
    @SaCheckPermission("zlyyh:commercialTenantAccountDetail:add")
    @Log(title = "商户账户明细", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CommercialTenantAccountDetailBo bo) {
        return toAjax(iCommercialTenantAccountDetailService.insertByBo(bo));
    }

    /**
     * 修改商户账户明细
     */
    @SaCheckPermission("zlyyh:commercialTenantAccountDetail:edit")
    @Log(title = "商户账户明细", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CommercialTenantAccountDetailBo bo) {
        return toAjax(iCommercialTenantAccountDetailService.updateByBo(bo));
    }

    /**
     * 删除商户账户明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:commercialTenantAccountDetail:remove")
    @Log(title = "商户账户明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iCommercialTenantAccountDetailService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
