package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.MarketLogBo;
import org.dromara.shopping.domain.vo.MarketLogVo;
import org.dromara.shopping.service.IMarketLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 奖励发放记录控制器
 * 前端访问路由地址为:/zlyyh/marketLog
 *
 * @author yzg
 * @date 2023-10-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/marketLog")
public class MarketLogController extends BaseController {

    private final IMarketLogService iMarketLogService;

    /**
     * 查询奖励发放记录列表
     */
    @SaCheckPermission("zlyyh:marketLog:list")
    @GetMapping("/list")
    public TableDataInfo<MarketLogVo> list(MarketLogBo bo, PageQuery pageQuery) {
        return iMarketLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出奖励发放记录列表
     */
    @SaCheckPermission("zlyyh:marketLog:export")
    @Log(title = "奖励发放记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MarketLogBo bo, HttpServletResponse response) {
        List<MarketLogVo> list = iMarketLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "奖励发放记录", MarketLogVo.class, response);
    }

    /**
     * 获取奖励发放记录详细信息
     *
     * @param logId 主键
     */
    @SaCheckPermission("zlyyh:marketLog:query")
    @GetMapping("/{logId}")
    public R<MarketLogVo> getInfo(@PathVariable Long logId) {
        return R.ok(iMarketLogService.queryById(logId));
    }
}
