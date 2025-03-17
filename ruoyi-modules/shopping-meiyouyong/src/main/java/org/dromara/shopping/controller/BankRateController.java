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
import org.dromara.shopping.domain.bo.BankRateBo;
import org.dromara.shopping.domain.vo.BankRateVo;
import org.dromara.shopping.service.IBankRateService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 银行费率控制器
 * 前端访问路由地址为:/zlyyh/bankRate
 *
 * @author yzg
 * @date 2024-05-29
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/bankRate")
public class BankRateController extends BaseController {

    private final IBankRateService iBankRateService;

    /**
     * 查询银行费率列表
     */
    @SaCheckPermission("zlyyh:bankRate:list")
    @GetMapping("/list")
    public TableDataInfo<BankRateVo> list(BankRateBo bo, PageQuery pageQuery) {
        return iBankRateService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出银行费率列表
     */
    @SaCheckPermission("zlyyh:bankRate:export")
    @Log(title = "银行费率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BankRateBo bo, HttpServletResponse response) {
        List<BankRateVo> list = iBankRateService.queryList(bo);
        ExcelUtil.exportExcel(list, "银行费率", BankRateVo.class, response);
    }

    /**
     * 获取银行费率详细信息
     *
     * @param bankRateId 主键
     */
    @SaCheckPermission("zlyyh:bankRate:query")
    @GetMapping("/{bankRateId}")
    public R<BankRateVo> getInfo(@PathVariable Long bankRateId) {
        return R.ok(iBankRateService.queryById(bankRateId));
    }

    /**
     * 新增银行费率
     */
    @SaCheckPermission("zlyyh:bankRate:add")
    @Log(title = "银行费率", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BankRateBo bo) {
        return toAjax(iBankRateService.insertByBo(bo));
    }

    /**
     * 修改银行费率
     */
    @SaCheckPermission("zlyyh:bankRate:edit")
    @Log(title = "银行费率", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BankRateBo bo) {
        return toAjax(iBankRateService.updateByBo(bo));
    }

    /**
     * 删除银行费率
     *
     * @param bankRateIds 主键串
     */
    @SaCheckPermission("zlyyh:bankRate:remove")
    @Log(title = "银行费率", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bankRateIds}")
    public R<Void> remove(@PathVariable Long[] bankRateIds) {
        return toAjax(iBankRateService.deleteWithValidByIds(Arrays.asList(bankRateIds), true));
    }
}
