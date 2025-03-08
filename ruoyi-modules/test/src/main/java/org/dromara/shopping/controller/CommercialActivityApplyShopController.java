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
import org.dromara.shopping.domain.bo.CommercialActivityApplyShopBo;
import org.dromara.shopping.domain.vo.CommercialActivityApplyShopVo;
import org.dromara.shopping.service.ICommercialActivityApplyShopService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户活动报名门店控制器
 * 前端访问路由地址为:/zlyyh/commercialActivityApplyShop
 *
 * @author yzg
 * @date 2024-04-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/commercialActivityApplyShop")
public class CommercialActivityApplyShopController extends BaseController {

    private final ICommercialActivityApplyShopService iCommercialActivityApplyShopService;

    /**
     * 查询商户活动报名门店列表
     */
    @SaCheckPermission("zlyyh:commercialActivityApplyShop:list")
    @GetMapping("/list")
    public TableDataInfo<CommercialActivityApplyShopVo> list(CommercialActivityApplyShopBo bo, PageQuery pageQuery) {
        return iCommercialActivityApplyShopService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商户活动报名门店列表
     */
    @SaCheckPermission("zlyyh:commercialActivityApplyShop:export")
    @Log(title = "商户活动报名门店", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CommercialActivityApplyShopBo bo, HttpServletResponse response) {
        List<CommercialActivityApplyShopVo> list = iCommercialActivityApplyShopService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户活动报名门店", CommercialActivityApplyShopVo.class, response);
    }

    /**
     * 获取商户活动报名门店详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:commercialActivityApplyShop:query")
    @GetMapping("/{id}")
    public R<CommercialActivityApplyShopVo> getInfo(@PathVariable Long id) {
        return R.ok(iCommercialActivityApplyShopService.queryById(id));
    }

    /**
     * 新增商户活动报名门店
     */
    @SaCheckPermission("zlyyh:commercialActivityApplyShop:add")
    @Log(title = "商户活动报名门店", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CommercialActivityApplyShopBo bo) {
        return toAjax(iCommercialActivityApplyShopService.insertByBo(bo));
    }

    /**
     * 修改商户活动报名门店
     */
    @SaCheckPermission("zlyyh:commercialActivityApplyShop:edit")
    @Log(title = "商户活动报名门店", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CommercialActivityApplyShopBo bo) {
        return toAjax(iCommercialActivityApplyShopService.updateByBo(bo));
    }

    /**
     * 删除商户活动报名门店
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:commercialActivityApplyShop:remove")
    @Log(title = "商户活动报名门店", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iCommercialActivityApplyShopService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
