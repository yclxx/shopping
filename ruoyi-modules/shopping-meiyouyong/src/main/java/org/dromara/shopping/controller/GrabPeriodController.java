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
import org.dromara.shopping.domain.bo.GrabPeriodBo;
import org.dromara.shopping.domain.vo.GrabPeriodVo;
import org.dromara.shopping.service.IGrabPeriodService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 秒杀配置控制器
 * 前端访问路由地址为:/zlyyh/grabPeriod
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/grabPeriod")
public class GrabPeriodController extends BaseController {

    private final IGrabPeriodService iGrabPeriodService;

    /**
     * 查询秒杀配置列表
     */
    @SaCheckPermission("zlyyh:grabPeriod:list")
    @GetMapping("/list")
    public TableDataInfo<GrabPeriodVo> list(GrabPeriodBo bo, PageQuery pageQuery) {
        return iGrabPeriodService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出秒杀配置列表
     */
    @SaCheckPermission("zlyyh:grabPeriod:export")
    @Log(title = "秒杀配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GrabPeriodBo bo, HttpServletResponse response) {
        List<GrabPeriodVo> list = iGrabPeriodService.queryList(bo);
        ExcelUtil.exportExcel(list, "秒杀配置", GrabPeriodVo.class, response);
    }

    /**
     * 获取秒杀配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:grabPeriod:query")
    @GetMapping("/{id}")
    public R<GrabPeriodVo> getInfo(@PathVariable Long id) {
        return R.ok(iGrabPeriodService.queryById(id));
    }

    /**
     * 新增秒杀配置
     */
    @SaCheckPermission("zlyyh:grabPeriod:add")
    @Log(title = "秒杀配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GrabPeriodBo bo) {
        return toAjax(iGrabPeriodService.insertByBo(bo));
    }

    /**
     * 修改秒杀配置
     */
    @SaCheckPermission("zlyyh:grabPeriod:edit")
    @Log(title = "秒杀配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GrabPeriodBo bo) {
        return toAjax(iGrabPeriodService.updateByBo(bo));
    }

    /**
     * 删除秒杀配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:grabPeriod:remove")
    @Log(title = "秒杀配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iGrabPeriodService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
