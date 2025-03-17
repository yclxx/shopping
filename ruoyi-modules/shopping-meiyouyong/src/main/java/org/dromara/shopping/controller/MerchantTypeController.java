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
import org.dromara.shopping.domain.bo.MerchantTypeBo;
import org.dromara.shopping.domain.vo.MerchantTypeVo;
import org.dromara.shopping.service.IMerchantTypeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商户门店类别控制器
 * 前端访问路由地址为:/zlyyh/merchantType
 *
 * @author yzg
 * @date 2024-01-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/merchantType")
public class MerchantTypeController extends BaseController {

    private final IMerchantTypeService iMerchantTypeService;

    /**
     * 查询商户门店类别列表
     */
    @SaCheckPermission("zlyyh:merchantType:list")
    @GetMapping("/list")
    public TableDataInfo<MerchantTypeVo> list(MerchantTypeBo bo, PageQuery pageQuery) {
        return iMerchantTypeService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出商户门店类别列表
     */
    @SaCheckPermission("zlyyh:merchantType:export")
    @Log(title = "商户门店类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MerchantTypeBo bo, HttpServletResponse response) {
        List<MerchantTypeVo> list = iMerchantTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "商户门店类别", MerchantTypeVo.class, response);
    }

    /**
     * 获取商户门店类别详细信息
     *
     * @param merchantTypeId 主键
     */
    @SaCheckPermission("zlyyh:merchantType:query")
    @GetMapping("/{merchantTypeId}")
    public R<MerchantTypeVo> getInfo(@PathVariable Long merchantTypeId) {
        return R.ok(iMerchantTypeService.queryById(merchantTypeId));
    }

    /**
     * 新增商户门店类别
     */
    @SaCheckPermission("zlyyh:merchantType:add")
    @Log(title = "商户门店类别", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MerchantTypeBo bo) {
        return toAjax(iMerchantTypeService.insertByBo(bo));
    }

    /**
     * 修改商户门店类别
     */
    @SaCheckPermission("zlyyh:merchantType:edit")
    @Log(title = "商户门店类别", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MerchantTypeBo bo) {
        return toAjax(iMerchantTypeService.updateByBo(bo));
    }

    /**
     * 删除商户门店类别
     *
     * @param merchantTypeIds 主键串
     */
    @SaCheckPermission("zlyyh:merchantType:remove")
    @Log(title = "商户门店类别", businessType = BusinessType.DELETE)
    @DeleteMapping("/{merchantTypeIds}")
    public R<Void> remove(@PathVariable Long[] merchantTypeIds) {
        return toAjax(iMerchantTypeService.deleteWithValidByIds(Arrays.asList(merchantTypeIds), true));
    }
}
