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
import org.dromara.shopping.domain.bo.AppointmentBo;
import org.dromara.shopping.domain.vo.AppointmentVo;
import org.dromara.shopping.service.IAppointmentService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * 预约信息控制器
 * 前端访问路由地址为:/zlyyh/appointment
 *
 * @author yzg
 * @date 2024-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/appointment")
public class AppointmentController extends BaseController {

    private final IAppointmentService iAppointmentService;

    /**
     * 查询预约信息列表
     */
    @SaCheckPermission("zlyyh:appointment:list")
    @GetMapping("/list")
    public TableDataInfo<AppointmentVo> list(AppointmentBo bo, PageQuery pageQuery) {
        return iAppointmentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出预约信息列表
     */
    @SaCheckPermission("zlyyh:appointment:export")
    @Log(title = "预约信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppointmentBo bo, HttpServletResponse response) {
        List<AppointmentVo> list = iAppointmentService.queryList(bo);
        ExcelUtil.exportExcel(list, "预约信息", AppointmentVo.class, response);
    }

    /**
     * 获取预约信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("zlyyh:appointment:query")
    @GetMapping("/{id}")
    public R<AppointmentVo> getInfo(@PathVariable Long id) {
        return R.ok(iAppointmentService.queryById(id));
    }

    /**
     * 新增预约信息
     */
    @SaCheckPermission("zlyyh:appointment:add")
    @Log(title = "预约信息", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppointmentBo bo) {
        return toAjax(iAppointmentService.insertByBo(bo));
    }

    /**
     * 修改预约信息
     */
    @SaCheckPermission("zlyyh:appointment:edit")
    @Log(title = "预约信息", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppointmentBo bo) {
        return toAjax(iAppointmentService.updateByBo(bo));
    }

    /**
     * 删除预约信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("zlyyh:appointment:remove")
    @Log(title = "预约信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(iAppointmentService.deleteWithValidByIds(Arrays.asList(ids), true));
    }

    /**
     * 导入数据
     */
    @Log(title = "预约信息",businessType = BusinessType.IMPORT)
    @PostMapping(value = "/importData",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Void> importData(@RequestPart("file")MultipartFile file) throws Exception{
        iAppointmentService.importData(file);
        return R.ok();
    }



}
