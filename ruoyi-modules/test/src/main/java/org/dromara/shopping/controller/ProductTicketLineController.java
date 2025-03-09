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
import org.dromara.shopping.domain.bo.ProductTicketLineBo;
import org.dromara.shopping.domain.vo.ProductTicketLineVo;
import org.dromara.shopping.service.IProductTicketLineService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 演出票种控制器
 * 前端访问路由地址为:/zlyyh/productTicketLine
 *
 * @author yzg
 * @date 2023-09-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productTicketLine")
public class ProductTicketLineController extends BaseController {

    private final IProductTicketLineService iProductTicketLineService;

    /**
     * 查询演出票种列表
     */
    @SaCheckPermission("zlyyh:productTicketLine:list")
    @GetMapping("/list")
    public TableDataInfo<ProductTicketLineVo> list(ProductTicketLineBo bo, PageQuery pageQuery) {
        return iProductTicketLineService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出演出票种列表
     */
    @SaCheckPermission("zlyyh:productTicketLine:export")
    @Log(title = "演出票种", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductTicketLineBo bo, HttpServletResponse response) {
        List<ProductTicketLineVo> list = iProductTicketLineService.queryList(bo);
        ExcelUtil.exportExcel(list, "演出票种", ProductTicketLineVo.class, response);
    }

    /**
     * 获取演出票种详细信息
     *
     * @param lineId 主键
     */
    @SaCheckPermission("zlyyh:productTicketLine:query")
    @GetMapping("/{lineId}")
    public R<ProductTicketLineVo> getInfo(@PathVariable Long lineId) {
        return R.ok(iProductTicketLineService.queryById(lineId));
    }

    /**
     * 新增演出票种
     */
    @SaCheckPermission("zlyyh:productTicketLine:add")
    @Log(title = "演出票种", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductTicketLineBo bo) {
        return toAjax(iProductTicketLineService.insertByBo(bo));
    }

    /**
     * 修改演出票种
     */
    @SaCheckPermission("zlyyh:productTicketLine:edit")
    @Log(title = "演出票种", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductTicketLineBo bo) {
        return toAjax(iProductTicketLineService.updateByBo(bo));
    }

    /**
     * 删除演出票种
     *
     * @param lineIds 主键串
     */
    @SaCheckPermission("zlyyh:productTicketLine:remove")
    @Log(title = "演出票种", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lineIds}")
    public R<Void> remove(@PathVariable Long[] lineIds) {
        return toAjax(iProductTicketLineService.deleteWithValidByIds(Arrays.asList(lineIds), true));
    }
}
