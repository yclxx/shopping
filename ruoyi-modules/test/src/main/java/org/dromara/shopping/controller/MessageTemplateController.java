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
import org.dromara.shopping.domain.bo.MessageTemplateBo;
import org.dromara.shopping.domain.vo.MessageTemplateVo;
import org.dromara.shopping.service.IMessageTemplateService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 消息模板控制器
 * 前端访问路由地址为:/zlyyh/messageTemplate
 *
 * @author yzg
 * @date 2023-11-23
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/messageTemplate")
public class MessageTemplateController extends BaseController {

    private final IMessageTemplateService iMessageTemplateService;

    /**
     * 查询消息模板列表
     */
    @SaCheckPermission("zlyyh:messageTemplate:list")
    @GetMapping("/list")
    public TableDataInfo<MessageTemplateVo> list(MessageTemplateBo bo, PageQuery pageQuery) {
        return iMessageTemplateService.queryPageList(bo, pageQuery);
    }


    /**
     * 导入数据
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Void> importData(MessageTemplateBo bo) throws Exception {
        iMessageTemplateService.importData(bo);
        return R.ok();
    }

    /**
     * 导出消息模板列表
     */
    @SaCheckPermission("zlyyh:messageTemplate:export")
    @Log(title = "消息模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MessageTemplateBo bo, HttpServletResponse response) {
        List<MessageTemplateVo> list = iMessageTemplateService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息模板", MessageTemplateVo.class, response);
    }

    /**
     * 获取消息模板详细信息
     *
     * @param templateId 主键
     */
    @SaCheckPermission("zlyyh:messageTemplate:query")
    @GetMapping("/{templateId}")
    public R<MessageTemplateVo> getInfo(@PathVariable Long templateId) {
        return R.ok(iMessageTemplateService.queryById(templateId));
    }

    /**
     * 新增消息模板
     */
    @SaCheckPermission("zlyyh:messageTemplate:add")
    @Log(title = "消息模板", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MessageTemplateBo bo) {
        return toAjax(iMessageTemplateService.insertByBo(bo));
    }

    /**
     * 修改消息模板
     */
    @SaCheckPermission("zlyyh:messageTemplate:edit")
    @Log(title = "消息模板", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MessageTemplateBo bo) {
        return toAjax(iMessageTemplateService.updateByBo(bo));
    }

    /**
     * 删除消息模板
     *
     * @param templateIds 主键串
     */
    @SaCheckPermission("zlyyh:messageTemplate:remove")
    @Log(title = "消息模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{templateIds}")
    public R<Void> remove(@PathVariable Long[] templateIds) {
        return toAjax(iMessageTemplateService.deleteWithValidByIds(Arrays.asList(templateIds), true));
    }
}
