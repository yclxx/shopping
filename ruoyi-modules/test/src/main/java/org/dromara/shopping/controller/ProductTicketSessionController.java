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
import org.dromara.shopping.domain.bo.ProductTicketSessionBo;
import org.dromara.shopping.domain.vo.ProductTicketSessionVo;
import org.dromara.shopping.service.IProductTicketSessionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 演出场次控制器
 * 前端访问路由地址为:/zlyyh/productTicketSession
 *
 * @author yzg
 * @date 2023-09-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productTicketSession")
public class ProductTicketSessionController extends BaseController {

    private final IProductTicketSessionService iProductTicketSessionService;

    /**
     * 查询演出场次列表
     */
    @SaCheckPermission("zlyyh:productTicketSession:list")
    @GetMapping("/list")
    public TableDataInfo<ProductTicketSessionVo> list(ProductTicketSessionBo bo, PageQuery pageQuery) {
        return iProductTicketSessionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出演出场次列表
     */
    @SaCheckPermission("zlyyh:productTicketSession:export")
    @Log(title = "演出场次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductTicketSessionBo bo, HttpServletResponse response) {
        List<ProductTicketSessionVo> list = iProductTicketSessionService.queryList(bo);
        ExcelUtil.exportExcel(list, "演出场次", ProductTicketSessionVo.class, response);
    }

    /**
     * 获取演出场次详细信息
     * @param priceId 主键
     */
    @SaCheckPermission("zlyyh:productTicketSession:query")
    @GetMapping("/{priceId}")
    public R<ProductTicketSessionVo> getInfo(@PathVariable Long priceId) {
        return R.ok(iProductTicketSessionService.queryById(priceId));
    }

    /**
     * 新增演出场次
     */
    @SaCheckPermission("zlyyh:productTicketSession:add")
    @Log(title = "演出场次", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductTicketSessionBo bo) {
        return toAjax(iProductTicketSessionService.insertByBo(bo));
    }

    /**
     * 修改演出场次
     */
    @SaCheckPermission("zlyyh:productTicketSession:edit")
    @Log(title = "演出场次", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductTicketSessionBo bo) {
        return toAjax(iProductTicketSessionService.updateByBo(bo));
    }

    /**
     * 删除演出场次
     *
     * @param priceIds 主键串
     */
    @SaCheckPermission("zlyyh:productTicketSession:remove")
    @Log(title = "演出场次", businessType = BusinessType.DELETE)
    @DeleteMapping("/{priceIds}")
    public R<Void> remove(@PathVariable Long[] priceIds) {
        return toAjax(iProductTicketSessionService.deleteWithValidByIds(Arrays.asList(priceIds), true));
    }
}
