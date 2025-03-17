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
import org.dromara.shopping.domain.bo.MerchantBo;
import org.dromara.shopping.domain.vo.MerchantVo;
import org.dromara.shopping.service.IMerchantService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户号控制器
 * 前端访问路由地址为:/zlyyh/merchant
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    private final IMerchantService iMerchantService;

    /**
     * 查询商户号列表
     */
    @SaCheckPermission("zlyyh:merchant:list")
    @GetMapping("/list")
    public TableDataInfo<MerchantVo> list(MerchantBo bo, PageQuery pageQuery) {
        return iMerchantService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出商户号列表
     */
    @SaCheckPermission("zlyyh:merchant:export")
    @Log(title = "商户号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MerchantBo bo, HttpServletResponse response) {
        List<MerchantVo> list = iMerchantService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户号", MerchantVo.class, response);
    }

    /**
     * 获取商户号详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:merchant:query")
    @GetMapping("/{id}")
    public R<MerchantVo> getInfo(@PathVariable Long id) {
        return R.ok(iMerchantService.queryById(id));
    }

    /**
     * 新增商户号
     */
    @SaCheckPermission("zlyyh:merchant:add")
    @Log(title = "商户号", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MerchantBo bo) {
        return toAjax(iMerchantService.insertByBo(bo));
    }

    /**
     * 修改商户号
     */
    @SaCheckPermission("zlyyh:merchant:edit")
    @Log(title = "商户号", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MerchantBo bo) {
        return toAjax(iMerchantService.updateByBo(bo));
    }

    /**
     * 删除商户号
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:merchant:remove")
    @Log(title = "商户号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iMerchantService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
