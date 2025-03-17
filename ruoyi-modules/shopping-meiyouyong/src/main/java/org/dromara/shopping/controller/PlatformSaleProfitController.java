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
import org.dromara.shopping.domain.bo.PlatformSaleProfitBo;
import org.dromara.shopping.domain.vo.PlatformSaleProfitVo;
import org.dromara.shopping.service.IPlatformSaleProfitService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台销售利润控制器
 * 前端访问路由地址为:/zlyyh/platformSaleProfit
 *
 * @author yzg
 * @date 2024-09-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformSaleProfit")
public class PlatformSaleProfitController extends BaseController {

    private final IPlatformSaleProfitService iPlatformSaleProfitService;

    /**
     * 查询平台销售利润列表
     */
    @SaCheckPermission("zlyyh:platformSaleProfit:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformSaleProfitVo> list(PlatformSaleProfitBo bo, PageQuery pageQuery) {
        return iPlatformSaleProfitService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台销售利润列表
     */
    @SaCheckPermission("zlyyh:platformSaleProfit:export")
    @Log(title = "平台销售利润", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformSaleProfitBo bo, HttpServletResponse response) {
        List<PlatformSaleProfitVo> list = iPlatformSaleProfitService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台销售利润", PlatformSaleProfitVo.class, response);
    }

    /**
     * 获取平台销售利润详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformSaleProfit:query")
    @GetMapping("/{id}")
    public R<PlatformSaleProfitVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformSaleProfitService.queryById(id));
    }

    /**
     * 新增平台销售利润
     */
    @SaCheckPermission("zlyyh:platformSaleProfit:add")
    @Log(title = "平台销售利润", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformSaleProfitBo bo) {
        return toAjax(iPlatformSaleProfitService.insertByBo(bo));
    }

    /**
     * 修改平台销售利润
     */
    @SaCheckPermission("zlyyh:platformSaleProfit:edit")
    @Log(title = "平台销售利润", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformSaleProfitBo bo) {
        return toAjax(iPlatformSaleProfitService.updateByBo(bo));
    }

    /**
     * 删除平台销售利润
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformSaleProfit:remove")
    @Log(title = "平台销售利润", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformSaleProfitService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
