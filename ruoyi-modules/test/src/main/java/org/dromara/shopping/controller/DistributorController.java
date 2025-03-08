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
import org.dromara.shopping.domain.bo.DistributorBo;
import org.dromara.shopping.domain.vo.DistributorVo;
import org.dromara.shopping.service.IDistributorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 分销商信息控制器
 * 前端访问路由地址为:/zlyyh/distributor
 *
 * @author yzg
 * @date 2023-09-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/distributor")
public class DistributorController extends BaseController {

    private final IDistributorService iDistributorService;

    /**
     * 查询分销商信息列表
     */
    @SaCheckPermission("zlyyh:distributor:list")
    @GetMapping("/list")
    public TableDataInfo<DistributorVo> list(DistributorBo bo, PageQuery pageQuery) {
        return iDistributorService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出分销商信息列表
     */
    @SaCheckPermission("zlyyh:distributor:export")
    @Log(title = "分销商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DistributorBo bo, HttpServletResponse response) {
        List<DistributorVo> list = iDistributorService.queryList(bo);
        ExcelUtil.exportExcel(list, "分销商信息", DistributorVo.class, response);
    }

    /**
     * 获取分销商信息详细信息
     *
     * @param distributorId 主键
     */
    @SaCheckPermission("zlyyh:distributor:query")
    @GetMapping("/{distributorId}")
    public R<DistributorVo> getInfo(@PathVariable String distributorId) {
        return R.ok(iDistributorService.queryById(distributorId));
    }

    /**
     * 新增分销商信息
     */
    @SaCheckPermission("zlyyh:distributor:add")
    @Log(title = "分销商信息", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DistributorBo bo) {
        return toAjax(iDistributorService.insertByBo(bo));
    }

    /**
     * 修改分销商信息
     */
    @SaCheckPermission("zlyyh:distributor:edit")
    @Log(title = "分销商信息", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DistributorBo bo) {
        return toAjax(iDistributorService.updateByBo(bo));
    }

    /**
     * 删除分销商信息
     *
     * @param distributorIds 主键串
     */
    @SaCheckPermission("zlyyh:distributor:remove")
    @Log(title = "分销商信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{distributorIds}")
    public R<Void> remove(@PathVariable String[] distributorIds) {
        return toAjax(iDistributorService.deleteWithValidByIds(Arrays.asList(distributorIds), true));
    }
}
