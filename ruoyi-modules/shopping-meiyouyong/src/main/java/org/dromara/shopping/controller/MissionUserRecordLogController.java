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
import org.dromara.shopping.domain.bo.MissionUserRecordLogBo;
import org.dromara.shopping.domain.vo.MissionUserRecordLogVo;
import org.dromara.shopping.service.IMissionUserRecordLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 活动订单取码记录控制器
 * 前端访问路由地址为:/zlyyh/missionUserRecordLog
 *
 * @author yzg
 * @date 2023-05-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/missionUserRecordLog")
public class MissionUserRecordLogController extends BaseController {

    private final IMissionUserRecordLogService iMissionUserRecordLogService;

    /**
     * 查询活动订单取码记录列表
     */
    @SaCheckPermission("zlyyh:missionUserRecordLog:list")
    @GetMapping("/list")
    public TableDataInfo<MissionUserRecordLogVo> list(MissionUserRecordLogBo bo, PageQuery pageQuery) {
        return iMissionUserRecordLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动订单取码记录列表
     */
    @SaCheckPermission("zlyyh:missionUserRecordLog:export")
    @Log(title = "活动订单取码记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MissionUserRecordLogBo bo, HttpServletResponse response) {
        List<MissionUserRecordLogVo> list = iMissionUserRecordLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动订单取码记录", MissionUserRecordLogVo.class, response);
    }

    /**
     * 获取活动订单取码记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:missionUserRecordLog:query")
    @GetMapping("/{id}")
    public R<MissionUserRecordLogVo> getInfo(@PathVariable Long id) {
        return R.ok(iMissionUserRecordLogService.queryById(id));
    }

    /**
     * 新增活动订单取码记录
     */
    @SaCheckPermission("zlyyh:missionUserRecordLog:add")
    @Log(title = "活动订单取码记录", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MissionUserRecordLogBo bo) {
        return toAjax(iMissionUserRecordLogService.insertByBo(bo));
    }

    /**
     * 修改活动订单取码记录
     */
    @SaCheckPermission("zlyyh:missionUserRecordLog:edit")
    @Log(title = "活动订单取码记录", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MissionUserRecordLogBo bo) {
        return toAjax(iMissionUserRecordLogService.updateByBo(bo));
    }

    /**
     * 删除活动订单取码记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:missionUserRecordLog:remove")
    @Log(title = "活动订单取码记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iMissionUserRecordLogService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
