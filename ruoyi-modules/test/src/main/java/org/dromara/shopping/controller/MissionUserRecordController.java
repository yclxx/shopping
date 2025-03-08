package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.shopping.domain.bo.MissionUserRecordBo;
import org.dromara.shopping.domain.vo.MissionUserRecordVo;
import org.dromara.shopping.service.IMissionUserRecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 活动记录控制器
 * 前端访问路由地址为:/zlyyh/missionUserRecord
 *
 * @author yzg
 * @date 2023-05-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/missionUserRecord")
public class MissionUserRecordController extends BaseController {

    private final IMissionUserRecordService iMissionUserRecordService;

    /**
     * 查询活动记录列表
     */
    @SaCheckPermission("zlyyh:missionUserRecord:list")
    @GetMapping("/list")
    public TableDataInfo<MissionUserRecordVo> list(MissionUserRecordBo bo, PageQuery pageQuery) {
        return iMissionUserRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动记录列表
     */
    @SaCheckPermission("zlyyh:missionUserRecord:export")
    @Log(title = "活动记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MissionUserRecordBo bo, HttpServletResponse response) {
        List<MissionUserRecordVo> list = iMissionUserRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动记录", MissionUserRecordVo.class, response);
    }



    /**
     * 导出活动记录列表
     */
    @SaCheckPermission("zlyyh:missionUserRecord:export")
    @Log(title = "活动记录", businessType = BusinessType.EXPORT)
    @PostMapping("/exportMissionLog")
    public void exportMissionLog(@RequestBody MissionUserRecordBo bo) {
        if (ObjectUtil.isEmpty(bo.getLinkmanEmail())) {
            throw new ServiceException("邮箱不允许为空");
        }
    }

    /**
     * 获取活动记录详细信息
     *
     * @param missionUserRecordId 主键
     */
    @SaCheckPermission("zlyyh:missionUserRecord:query")
    @GetMapping("/{missionUserRecordId}")
    public R<MissionUserRecordVo> getInfo(@PathVariable Long missionUserRecordId) {
        return R.ok(iMissionUserRecordService.queryById(missionUserRecordId));
    }

    /**
     * 新增活动记录
     */
    @SaCheckPermission("zlyyh:missionUserRecord:add")
    @Log(title = "活动记录", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MissionUserRecordBo bo) {
        return toAjax(iMissionUserRecordService.insertByBo(bo));
    }

    /**
     * 作废抽奖机会
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:missionUserRecord:edit")
    @Log(title = "活动订单取码记录", businessType = BusinessType.UPDATE)
    @PutMapping("/expiry/{ids}")
    public R<Void> expiry(@PathVariable Long[] ids) {
        return toAjax(iMissionUserRecordService.expiry(Arrays.asList(ids)));
    }

}
