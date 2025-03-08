package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.BannerBo;
import org.dromara.shopping.domain.vo.BannerVo;
import org.dromara.shopping.service.IBannerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 广告管理控制器
 * 前端访问路由地址为:/zlyyh-admin/banner
 *
 * @author ruoyi
 * @date 2023-03-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/banner")
public class BannerController extends BaseController {

    private final IBannerService iBannerService;

    /**
     * 查询广告管理列表
     */
    @SaCheckPermission("zlyyh:banner:list")
    @GetMapping("/list")
    public TableDataInfo<BannerVo> list(BannerBo bo, PageQuery pageQuery) {
        return iBannerService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出广告管理列表
     */
    @SaCheckPermission("zlyyh:banner:export")
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BannerBo bo, HttpServletResponse response) {
        List<BannerVo> list = iBannerService.queryList(bo);
        ExcelUtil.exportExcel(list, "广告管理", BannerVo.class, response);
    }

    /**
     * 获取广告管理详细信息
     *
     * @param bannerId 主键
     */
    @SaCheckPermission("zlyyh:banner:query")
    @GetMapping("/{bannerId}")
    public R<BannerVo> getInfo(@PathVariable Long bannerId) {
        return R.ok(iBannerService.queryById(bannerId));
    }

    /**
     * 新增广告管理
     */
    @SaCheckPermission("zlyyh:banner:add")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BannerBo bo) {
        return toAjax(iBannerService.insertByBo(bo));
    }

    /**
     * 修改广告管理
     */
    @SaCheckPermission("zlyyh:banner:edit")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BannerBo bo) {
        return toAjax(iBannerService.updateByBo(bo));
    }

    /**
     * 删除广告管理
     *
     * @param bannerIds 主键串
     */
    @SaCheckPermission("zlyyh:banner:remove")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bannerIds}")
    public R<Void> remove(@PathVariable Long[] bannerIds) {
        return toAjax(iBannerService.deleteWithValidByIds(Arrays.asList(bannerIds), true));
    }
}
