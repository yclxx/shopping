package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.PlatformShareConfigBo;
import org.dromara.shopping.domain.vo.PlatformShareConfigVo;
import org.dromara.shopping.service.IPlatformShareConfigService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 平台分享配置控制器
 * 前端访问路由地址为:/zlyyh/platformShareConfig
 *
 * @author yzg
 * @date 2025-01-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformShareConfig")
public class PlatformShareConfigController extends BaseController {

    private final IPlatformShareConfigService iPlatformShareConfigService;

    /**
     * 查询平台分享配置列表
     */
    @SaCheckPermission("zlyyh:platformShareConfig:list")
    @GetMapping("/list")
    public TableDataInfo<PlatformShareConfigVo> list(PlatformShareConfigBo bo, PageQuery pageQuery) {
        return iPlatformShareConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取平台分享配置详细信息
     *
     * @param platformKey 主键
     */
    @SaCheckPermission("zlyyh:platformShareConfig:query")
    @GetMapping("/{platformKey}")
    public R<PlatformShareConfigVo> getInfo(@PathVariable Long platformKey) {
        return R.ok(iPlatformShareConfigService.queryById(platformKey));
    }

    /**
     * 修改平台分享配置
     */
    @SaCheckPermission("zlyyh:platformShareConfig:edit")
    @Log(title = "平台分享配置", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PlatformShareConfigBo bo) {
        return toAjax(iPlatformShareConfigService.updateByBo(bo));
    }
}
