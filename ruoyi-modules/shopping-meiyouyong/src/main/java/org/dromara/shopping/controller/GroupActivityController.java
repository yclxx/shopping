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
import org.dromara.shopping.domain.bo.GroupActivityBo;
import org.dromara.shopping.domain.vo.GroupActivityVo;
import org.dromara.shopping.service.IGroupActivityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 拼团活动控制器
 * 前端访问路由地址为:/zlyyh/groupActivity
 *
 * @author yzg
 * @date 2024-09-26
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/groupActivity")
public class GroupActivityController extends BaseController {

    private final IGroupActivityService iGroupActivityService;

    /**
     * 查询拼团活动列表
     */
    @SaCheckPermission("zlyyh:groupActivity:list")
    @GetMapping("/list")
    public TableDataInfo<GroupActivityVo> list(GroupActivityBo bo, PageQuery pageQuery) {
        return iGroupActivityService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出拼团活动列表
     */
    @SaCheckPermission("zlyyh:groupActivity:export")
    @Log(title = "拼团活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GroupActivityBo bo, HttpServletResponse response) {
        List<GroupActivityVo> list = iGroupActivityService.queryList(bo);
        ExcelUtil.exportExcel(list, "拼团活动", GroupActivityVo.class, response);
    }

    /**
     * 获取拼团活动详细信息
     *
     * @param activityId 主键
     */
    @SaCheckPermission("zlyyh:groupActivity:query")
    @GetMapping("/{activityId}")
    public R<GroupActivityVo> getInfo(@PathVariable Long activityId) {
        return R.ok(iGroupActivityService.queryById(activityId));
    }

    /**
     * 新增拼团活动
     */
    @SaCheckPermission("zlyyh:groupActivity:add")
    @Log(title = "拼团活动", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GroupActivityBo bo) {
        return toAjax(iGroupActivityService.insertByBo(bo));
    }

    /**
     * 修改拼团活动
     */
    @SaCheckPermission("zlyyh:groupActivity:edit")
    @Log(title = "拼团活动", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GroupActivityBo bo) {
        return toAjax(iGroupActivityService.updateByBo(bo));
    }

    /**
     * 删除拼团活动
     *
     * @param activityIds 主键串
     */
    @SaCheckPermission("zlyyh:groupActivity:remove")
    @Log(title = "拼团活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public R<Void> remove(@PathVariable Long[] activityIds) {
        return toAjax(iGroupActivityService.deleteWithValidByIds(Arrays.asList(activityIds), true));
    }

    /**
     * 改变活动状态
     */
    @PostMapping("/changeGroupActivity")
    public R<Void> changeGroupActivity(@RequestBody GroupActivityBo bo){
        return toAjax(iGroupActivityService.changeGroupActivity(bo));
    }
}
