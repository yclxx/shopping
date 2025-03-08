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
import org.dromara.shopping.domain.bo.BankBo;
import org.dromara.shopping.domain.vo.BankVo;
import org.dromara.shopping.service.IBankService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 银行控制器
 * 前端访问路由地址为:/zlyyh/bank
 *
 * @author yzg
 * @date 2024-03-26
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/bank")
public class BankController extends BaseController {

    private final IBankService iBankService;

    /**
     * 查询银行列表
     */
    @SaCheckPermission("zlyyh:bank:list")
    @GetMapping("/list")
    public TableDataInfo<BankVo> list(BankBo bo, PageQuery pageQuery) {
        return iBankService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出银行列表
     */
    @SaCheckPermission("zlyyh:bank:export")
    @Log(title = "银行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BankBo bo, HttpServletResponse response) {
        List<BankVo> list = iBankService.queryList(bo);
        ExcelUtil.exportExcel(list, "银行", BankVo.class, response);
    }

    /**
     * 获取银行详细信息
     *
     * @param bankId 主键
     */
    @SaCheckPermission("zlyyh:bank:query")
    @GetMapping("/{bankId}")
    public R<BankVo> getInfo(@PathVariable Long bankId) {
        return R.ok(iBankService.queryById(bankId));
    }

    /**
     * 新增银行
     */
    @SaCheckPermission("zlyyh:bank:add")
    @Log(title = "银行", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BankBo bo) {
        return toAjax(iBankService.insertByBo(bo));
    }

    /**
     * 修改银行
     */
    @SaCheckPermission("zlyyh:bank:edit")
    @Log(title = "银行", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BankBo bo) {
        return toAjax(iBankService.updateByBo(bo));
    }

    /**
     * 删除银行
     *
     * @param bankIds 主键串
     */
    @SaCheckPermission("zlyyh:bank:remove")
    @Log(title = "银行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bankIds}")
    public R<Void> remove(@PathVariable Long[] bankIds) {
        return toAjax(iBankService.deleteWithValidByIds(Arrays.asList(bankIds), true));
    }
}
