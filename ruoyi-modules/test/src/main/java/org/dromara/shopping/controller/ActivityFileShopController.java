package org.dromara.shopping.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
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
import org.dromara.shopping.domain.bo.ActivityFileShopBo;
import org.dromara.shopping.domain.bo.FileImportLogBo;
import org.dromara.shopping.domain.vo.ActivityFileShopVo;
import org.dromara.shopping.domain.vo.FileImportLogVo;
import org.dromara.shopping.service.IActivityFileShopService;
import org.dromara.shopping.service.IFileImportLogService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * 活动商户控制器
 * 前端访问路由地址为:/zlyyh/activityFileShop
 *
 * @author yzg
 * @date 2024-01-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/activityFileShop")
public class ActivityFileShopController extends BaseController {

    private final IActivityFileShopService iActivityFileShopService;
    private final IFileImportLogService fileImportLogService;

    /**
     * 查询活动商户列表
     */
    @SaCheckPermission("zlyyh:activityFileShop:list")
    @GetMapping("/list")
    public TableDataInfo<ActivityFileShopVo> list(ActivityFileShopBo bo, PageQuery pageQuery) {
        return iActivityFileShopService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动商户列表
     */
    @SaCheckPermission("zlyyh:activityFileShop:export")
    @Log(title = "活动商户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ActivityFileShopBo bo, HttpServletResponse response) {
        List<ActivityFileShopVo> list = iActivityFileShopService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动商户", ActivityFileShopVo.class, response);
    }

    /**
     * 获取活动商户详细信息
     *
     * @param activityShopId 主键
     */
    @SaCheckPermission("zlyyh:activityFileShop:query")
    @GetMapping("/{activityShopId}")
    public R<ActivityFileShopVo> getInfo(@PathVariable Long activityShopId) {
        return R.ok(iActivityFileShopService.queryById(activityShopId));
    }

    /**
     * 新增活动商户
     */
    @SaCheckPermission("zlyyh:activityFileShop:add")
    @Log(title = "活动商户", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ActivityFileShopBo bo) {
        return toAjax(iActivityFileShopService.insertByBo(bo));
    }

    /**
     * 修改活动商户
     */
    @SaCheckPermission("zlyyh:activityFileShop:edit")
    @Log(title = "活动商户", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ActivityFileShopBo bo) {
        return toAjax(iActivityFileShopService.updateByBo(bo));
    }

    /**
     * 删除活动商户
     *
     * @param activityShopIds 主键串
     */
    @SaCheckPermission("zlyyh:activityFileShop:remove")
    @Log(title = "活动商户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityShopIds}")
    public R<Void> remove(@PathVariable Long[] activityShopIds) {
        return toAjax(iActivityFileShopService.deleteWithValidByIds(Arrays.asList(activityShopIds), true));
    }

    /**
     * 导入商户
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Void> importData(@RequestPart("file") MultipartFile file, String pageTitle) throws Exception {
        FileImportLogBo logBo = new FileImportLogBo();
        logBo.setName(file.getOriginalFilename());
        List<FileImportLogVo> logVos = fileImportLogService.queryList(logBo);
        if (ObjectUtils.isNotEmpty(logVos)) {
            return R.fail("导入失败，该文件名已存在");
        }
        logBo.setPageTitle(pageTitle);
        fileImportLogService.insertByBo(logBo);
        return R.ok();
    }
}
