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
import org.dromara.shopping.domain.bo.GrabPeriodProductBo;
import org.dromara.shopping.domain.vo.GrabPeriodProductVo;
import org.dromara.shopping.service.IGrabPeriodProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 秒杀商品配置控制器
 * 前端访问路由地址为:/zlyyh/grabPeriodProduct
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/grabPeriodProduct")
public class GrabPeriodProductController extends BaseController {

    private final IGrabPeriodProductService iGrabPeriodProductService;

    /**
     * 查询秒杀商品配置列表
     */
    @SaCheckPermission("zlyyh:grabPeriodProduct:list")
    @GetMapping("/list")
    public TableDataInfo<GrabPeriodProductVo> list(GrabPeriodProductBo bo, PageQuery pageQuery) {
        return iGrabPeriodProductService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出秒杀商品配置列表
     */
    @SaCheckPermission("zlyyh:grabPeriodProduct:export")
    @Log(title = "秒杀商品配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GrabPeriodProductBo bo, HttpServletResponse response) {
        List<GrabPeriodProductVo> list = iGrabPeriodProductService.queryList(bo);
        ExcelUtil.exportExcel(list, "秒杀商品配置", GrabPeriodProductVo.class, response);
    }

    /**
     * 获取秒杀商品配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:grabPeriodProduct:query")
    @GetMapping("/{id}")
    public R<GrabPeriodProductVo> getInfo(@PathVariable Long id) {
        return R.ok(iGrabPeriodProductService.queryById(id));
    }

    /**
     * 新增秒杀商品配置
     */
    @SaCheckPermission("zlyyh:grabPeriodProduct:add")
    @Log(title = "秒杀商品配置", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GrabPeriodProductBo bo) {
        return toAjax(iGrabPeriodProductService.insertByBo(bo));
    }

    /**
     * 修改秒杀商品配置
     */
    @SaCheckPermission("zlyyh:grabPeriodProduct:edit")
    @Log(title = "秒杀商品配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GrabPeriodProductBo bo) {
        return toAjax(iGrabPeriodProductService.updateByBo(bo));
    }

    /**
     * 删除秒杀商品配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:grabPeriodProduct:remove")
    @Log(title = "秒杀商品配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iGrabPeriodProductService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
