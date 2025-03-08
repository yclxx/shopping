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
import org.dromara.shopping.domain.bo.EquityBo;
import org.dromara.shopping.domain.vo.EquityVo;
import org.dromara.shopping.service.IEquityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 权益包控制器
 * 前端访问路由地址为:/zlyyh/equity
 *
 * @author yzg
 * @date 2023-06-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/equity")
public class EquityController extends BaseController {

    private final IEquityService iEquityService;

    /**
     * 查询权益包列表
     */
    @SaCheckPermission("zlyyh:equity:list")
    @GetMapping("/list")
    public TableDataInfo<EquityVo> list(EquityBo bo, PageQuery pageQuery) {
        return iEquityService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出权益包列表
     */
    @SaCheckPermission("zlyyh:equity:export")
    @Log(title = "权益包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EquityBo bo, HttpServletResponse response) {
        List<EquityVo> list = iEquityService.queryList(bo);
        ExcelUtil.exportExcel(list, "权益包", EquityVo.class, response);
    }

    /**
     * 获取权益包详细信息
     *
     * @param equityId 主键
     */
    @SaCheckPermission("zlyyh:equity:query")
    @GetMapping("/{equityId}")
    public R<EquityVo> getInfo(@PathVariable Long equityId) {
        return R.ok(iEquityService.queryById(equityId));
    }

    /**
     * 新增权益包
     */
    @SaCheckPermission("zlyyh:equity:add")
    @Log(title = "权益包", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EquityBo bo) {
        return toAjax(iEquityService.insertByBo(bo));
    }

    /**
     * 修改权益包
     */
    @SaCheckPermission("zlyyh:equity:edit")
    @Log(title = "权益包", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EquityBo bo) {
        return toAjax(iEquityService.updateByBo(bo));
    }

    /**
     * 删除权益包
     *
     * @param equityIds 主键串
     */
    @SaCheckPermission("zlyyh:equity:remove")
    @Log(title = "权益包", businessType = BusinessType.DELETE)
    @DeleteMapping("/{equityIds}")
    public R<Void> remove(@PathVariable Long[] equityIds) {
        return toAjax(iEquityService.deleteWithValidByIds(Arrays.asList(equityIds), true));
    }
}
