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
import org.dromara.shopping.domain.bo.CityMerchantBo;
import org.dromara.shopping.domain.vo.CityMerchantVo;
import org.dromara.shopping.service.ICityMerchantService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 城市商户控制器
 * 前端访问路由地址为:/zlyyh/cityMerchant
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/cityMerchant")
public class CityMerchantController extends BaseController {

    private final ICityMerchantService iCityMerchantService;

    /**
     * 查询城市商户列表
     */
    @SaCheckPermission("zlyyh:cityMerchant:list")
    @GetMapping("/list")
    public TableDataInfo<CityMerchantVo> list(CityMerchantBo bo, PageQuery pageQuery) {
        return iCityMerchantService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出城市商户列表
     */
    @SaCheckPermission("zlyyh:cityMerchant:export")
    @Log(title = "城市商户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CityMerchantBo bo, HttpServletResponse response) {
        List<CityMerchantVo> list = iCityMerchantService.queryList(bo);
        ExcelUtil.exportExcel(list, "城市商户", CityMerchantVo.class, response);
    }

    /**
     * 获取城市商户详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:cityMerchant:query")
    @GetMapping("/{id}")
    public R<CityMerchantVo> getInfo(@PathVariable Long id) {
        return R.ok(iCityMerchantService.queryById(id));
    }

    /**
     * 新增城市商户
     */
    @SaCheckPermission("zlyyh:cityMerchant:add")
    @Log(title = "城市商户", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CityMerchantBo bo) {
        return toAjax(iCityMerchantService.insertByBo(bo));
    }

    /**
     * 修改城市商户
     */
    @SaCheckPermission("zlyyh:cityMerchant:edit")
    @Log(title = "城市商户", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CityMerchantBo bo) {
        return toAjax(iCityMerchantService.updateByBo(bo));
    }

    /**
     * 删除城市商户
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:cityMerchant:remove")
    @Log(title = "城市商户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iCityMerchantService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
