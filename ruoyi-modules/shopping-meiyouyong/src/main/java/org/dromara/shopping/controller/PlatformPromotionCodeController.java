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
import org.dromara.shopping.domain.bo.PlatformPromotionCodeBo;
import org.dromara.shopping.domain.vo.PlatformPromotionCodeVo;
import org.dromara.shopping.service.IPlatformPromotionCodeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 平台用户推广码控制器
 * 前端访问路由地址为:/zlyyh/platformPromotionCode
 *
 * @author yzg
 * @date 2024-05-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformPromotionCode")
public class PlatformPromotionCodeController extends BaseController {

    private final IPlatformPromotionCodeService iPlatformPromotionCodeService;

    /**
     * 查询平台用户推广码列表
     */
    @SaCheckPermission("zlyyh:platformPromotionCode:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformPromotionCodeVo> list(PlatformPromotionCodeBo bo, PageQuery pageQuery) {
        return iPlatformPromotionCodeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出平台用户推广码列表
     */
    @SaCheckPermission("zlyyh:platformPromotionCode:export")
    @Log(title = "平台用户推广码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PlatformPromotionCodeBo bo, HttpServletResponse response) {
        List<PlatformPromotionCodeVo> list = iPlatformPromotionCodeService.queryList(bo);
        ExcelUtil.exportExcel(list, "平台用户推广码", PlatformPromotionCodeVo.class, response);
    }

    /**
     * 获取平台用户推广码详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:platformPromotionCode:query")
    @GetMapping("/{id}")
    public R<PlatformPromotionCodeVo> getInfo(@PathVariable Long id) {
        return R.ok(iPlatformPromotionCodeService.queryById(id));
    }

    /**
     * 新增平台用户推广码
     */
    @SaCheckPermission("zlyyh:platformPromotionCode:add")
    @Log(title = "平台用户推广码", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PlatformPromotionCodeBo bo) {
        return toAjax(iPlatformPromotionCodeService.insertByBo(bo));
    }

    /**
     * 修改平台用户推广码
     */
    @SaCheckPermission("zlyyh:platformPromotionCode:edit")
    @Log(title = "平台用户推广码", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformPromotionCodeBo bo) {
        return toAjax(iPlatformPromotionCodeService.updateByBo(bo));
    }

    /**
     * 删除平台用户推广码
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:platformPromotionCode:remove")
    @Log(title = "平台用户推广码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iPlatformPromotionCodeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
