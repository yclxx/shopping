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
import org.dromara.shopping.domain.bo.BusinessDistrictBo;
import org.dromara.shopping.domain.vo.BusinessDistrictVo;
import org.dromara.shopping.service.IBusinessDistrictService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 商圈控制器
 * 前端访问路由地址为:/zlyyh/businessDistrict
 *
 * @author yzg
 * @date 2023-09-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/businessDistrict")
public class BusinessDistrictController extends BaseController {

    private final IBusinessDistrictService iBusinessDistrictService;

    /**
     * 查询商圈列表
     */
    @SaCheckPermission("zlyyh:businessDistrict:list")
    @GetMapping("/list")
    public TableDataInfo<BusinessDistrictVo> list(BusinessDistrictBo bo, PageQuery pageQuery) {
        return iBusinessDistrictService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出商圈列表
     */
    @SaCheckPermission("zlyyh:businessDistrict:export")
    @Log(title = "商圈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BusinessDistrictBo bo, HttpServletResponse response) {
        List<BusinessDistrictVo> list = iBusinessDistrictService.queryList(bo);
        ExcelUtil.exportExcel(list, "商圈", BusinessDistrictVo.class, response);
    }

    /**
     * 获取商圈详细信息
     *
     * @param businessDistrictId 主键
     */
    @SaCheckPermission("zlyyh:businessDistrict:query")
    @GetMapping("/{businessDistrictId}")
    public R<BusinessDistrictVo> getInfo(@PathVariable Long businessDistrictId) {
        return R.ok(iBusinessDistrictService.queryById(businessDistrictId));
    }

    /**
     * 新增商圈
     */
    @SaCheckPermission("zlyyh:businessDistrict:add")
    @Log(title = "商圈", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BusinessDistrictBo bo) {
        return toAjax(iBusinessDistrictService.insertByBo(bo));
    }

    /**
     * 修改商圈
     */
    @SaCheckPermission("zlyyh:businessDistrict:edit")
    @Log(title = "商圈", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BusinessDistrictBo bo) {
        return toAjax(iBusinessDistrictService.updateByBo(bo));
    }

    /**
     * 删除商圈
     *
     * @param businessDistrictIds 主键串
     */
    @SaCheckPermission("zlyyh:businessDistrict:remove")
    @Log(title = "商圈", businessType = BusinessType.DELETE)
    @DeleteMapping("/{businessDistrictIds}")
    public R<Void> remove(@PathVariable Long[] businessDistrictIds) {
        return toAjax(iBusinessDistrictService.deleteWithValidByIds(Arrays.asList(businessDistrictIds), true));
    }
}
