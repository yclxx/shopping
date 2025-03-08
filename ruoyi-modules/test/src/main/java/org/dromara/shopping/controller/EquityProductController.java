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
import org.dromara.shopping.domain.bo.EquityProductBo;
import org.dromara.shopping.domain.vo.EquityProductVo;
import org.dromara.shopping.service.IEquityProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 权益包商品控制器
 * 前端访问路由地址为:/zlyyh/equityProduct
 *
 * @author yzg
 * @date 2023-06-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/equityProduct")
public class EquityProductController extends BaseController {

    private final IEquityProductService iEquityProductService;

    /**
     * 查询权益包商品列表
     */
    @SaCheckPermission("zlyyh:equityProduct:list")
    @GetMapping("/list")
    public TableDataInfo<EquityProductVo> list(EquityProductBo bo, PageQuery pageQuery) {
        return iEquityProductService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出权益包商品列表
     */
    @SaCheckPermission("zlyyh:equityProduct:export")
    @Log(title = "权益包商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EquityProductBo bo, HttpServletResponse response) {
        List<EquityProductVo> list = iEquityProductService.queryList(bo);
        ExcelUtil.exportExcel(list, "权益包商品", EquityProductVo.class, response);
    }

    /**
     * 获取权益包商品详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:equityProduct:query")
    @GetMapping("/{id}")
    public R<EquityProductVo> getInfo(@PathVariable Long id) {
        return R.ok(iEquityProductService.queryById(id));
    }

    /**
     * 新增权益包商品
     */
    @SaCheckPermission("zlyyh:equityProduct:add")
    @Log(title = "权益包商品", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EquityProductBo bo) {
        return toAjax(iEquityProductService.insertByBo(bo));
    }

    /**
     * 修改权益包商品
     */
    @SaCheckPermission("zlyyh:equityProduct:edit")
    @Log(title = "权益包商品", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EquityProductBo bo) {
        return toAjax(iEquityProductService.updateByBo(bo));
    }

    /**
     * 删除权益包商品
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:equityProduct:remove")
    @Log(title = "权益包商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iEquityProductService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
