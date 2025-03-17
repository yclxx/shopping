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
import org.dromara.shopping.domain.bo.LianlianCityBo;
import org.dromara.shopping.domain.vo.LianlianCityVo;
import org.dromara.shopping.service.ILianlianCityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 联联市级城市控制器
 * 前端访问路由地址为:/zlyyh/lianlianCity
 *
 * @author yzg
 * @date 2023-09-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/lianlianCity")
public class LianlianCityController extends BaseController {

    private final ILianlianCityService iLianlianCityService;

    /**
     * 查询联联市级城市列表
     */
    @SaCheckPermission("zlyyh:lianlianCity:list")
    @GetMapping("/list")
    public TableDataInfo<LianlianCityVo> list(LianlianCityBo bo, PageQuery pageQuery) {
        return iLianlianCityService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出联联市级城市列表
     */
    @SaCheckPermission("zlyyh:lianlianCity:export")
    @Log(title = "联联市级城市", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(LianlianCityBo bo, HttpServletResponse response) {
        List<LianlianCityVo> list = iLianlianCityService.queryList(bo);
        ExcelUtil.exportExcel(list, "联联市级城市", LianlianCityVo.class, response);
    }

    /**
     * 获取联联市级城市详细信息
     *
     * @param cityId 主键
     */
    @SaCheckPermission("zlyyh:lianlianCity:query")
    @GetMapping("/{cityId}")
    public R<LianlianCityVo> getInfo(@PathVariable Long cityId) {
        return R.ok(iLianlianCityService.queryById(cityId));
    }

    /**
     * 新增联联市级城市
     */
    @SaCheckPermission("zlyyh:lianlianCity:add")
    @Log(title = "联联市级城市", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody LianlianCityBo bo) {
        return toAjax(iLianlianCityService.insertByBo(bo));
    }

    /**
     * 修改联联市级城市
     */
    @SaCheckPermission("zlyyh:lianlianCity:edit")
    @Log(title = "联联市级城市", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody LianlianCityBo bo) {
        return toAjax(iLianlianCityService.updateByBo(bo));
    }

    /**
     * 删除联联市级城市
     *
     * @param cityIds 主键串
     */
    @SaCheckPermission("zlyyh:lianlianCity:remove")
    @Log(title = "联联市级城市", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cityIds}")
    public R<Void> remove(@PathVariable Long[] cityIds) {
        return toAjax(iLianlianCityService.deleteWithValidByIds(Arrays.asList(cityIds), true));
    }
}
