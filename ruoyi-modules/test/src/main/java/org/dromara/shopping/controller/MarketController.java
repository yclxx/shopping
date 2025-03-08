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
import org.dromara.shopping.domain.bo.MarketBo;
import org.dromara.shopping.domain.vo.MarketVo;
import org.dromara.shopping.service.IMarketService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 新用户营销控制器
 * 前端访问路由地址为:/zlyyh/market
 *
 * @author yzg
 * @date 2023-10-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/market")
public class MarketController extends BaseController {

    private final IMarketService iMarketService;

    /**
     * 查询新用户营销列表
     */
    @SaCheckPermission("zlyyh:market:list")
    @GetMapping("/list")
    public TableDataInfo<MarketVo> list(MarketBo bo, PageQuery pageQuery) {
        return iMarketService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出新用户营销列表
     */
    @SaCheckPermission("zlyyh:market:export")
    @Log(title = "新用户营销", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MarketBo bo, HttpServletResponse response) {
        List<MarketVo> list = iMarketService.queryList(bo);
        ExcelUtil.exportExcel(list, "新用户营销", MarketVo.class, response);
    }

    /**
     * 获取新用户营销详细信息
     *
     * @param marketId 主键
     */
    @SaCheckPermission("zlyyh:market:query")
    @GetMapping("/{marketId}")
    public R<MarketVo> getInfo(@PathVariable Long marketId) {
        return R.ok(iMarketService.queryById(marketId));
    }

    @GetMapping("/prize/{marketId}")
    public R<Map<String, Object>> getInfos(@PathVariable Long marketId) {
        return R.ok(iMarketService.queryByIds(marketId));
    }

    /**
     * 新增新用户营销
     */
    @SaCheckPermission("zlyyh:market:add")
    @Log(title = "新用户营销", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MarketBo bo) {
        return toAjax(iMarketService.insertByBo(bo));
    }

    /**
     * 修改新用户营销
     */
    @SaCheckPermission("zlyyh:market:edit")
    @Log(title = "新用户营销", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MarketBo bo) {
        return toAjax(iMarketService.updateByBo(bo));
    }

    /**
     * 删除新用户营销
     *
     * @param marketIds 主键串
     */
    @SaCheckPermission("zlyyh:market:remove")
    @Log(title = "新用户营销", businessType = BusinessType.DELETE)
    @DeleteMapping("/{marketIds}")
    public R<Void> remove(@PathVariable Long[] marketIds) {
        return toAjax(iMarketService.deleteWithValidByIds(Arrays.asList(marketIds), true));
    }
}
