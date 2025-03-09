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
import org.dromara.shopping.domain.bo.FileImportLogBo;
import org.dromara.shopping.domain.bo.ProductArrivalListBo;
import org.dromara.shopping.domain.vo.FileImportLogVo;
import org.dromara.shopping.domain.vo.ProductArrivalListVo;
import org.dromara.shopping.service.IFileImportLogService;
import org.dromara.shopping.service.IProductArrivalListService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 到货清单控制器
 * 前端访问路由地址为:/zlyyh/productArrivalList
 *
 * @author yzg
 * @date 2024-05-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/productArrivalList")
public class ProductArrivalListController extends BaseController {

    private final IProductArrivalListService iProductArrivalListService;
    private final IFileImportLogService fileImportLogService;

    /**
     * 查询到货清单列表
     */
    @SaCheckPermission("zlyyh:productArrivalList:list")
    @GetMapping("/list")
    public TableDataInfo<ProductArrivalListVo> list(ProductArrivalListBo bo, PageQuery pageQuery) {
        return iProductArrivalListService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出到货清单列表
     */
    @SaCheckPermission("zlyyh:productArrivalList:export")
    @Log(title = "到货清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ProductArrivalListBo bo, HttpServletResponse response) {
        List<ProductArrivalListVo> list = iProductArrivalListService.queryList(bo);
        ExcelUtil.exportExcel(list, "到货清单", ProductArrivalListVo.class, response);
    }

    /**
     * 获取到货清单详细信息
     *
     * @param arrivalListId 主键
     */
    @SaCheckPermission("zlyyh:productArrivalList:query")
    @GetMapping("/{arrivalListId}")
    public R<ProductArrivalListVo> getInfo(@PathVariable Long arrivalListId) {
        return R.ok(iProductArrivalListService.queryById(arrivalListId));
    }

    /**
     * 新增到货清单
     */
    @SaCheckPermission("zlyyh:productArrivalList:add")
    @Log(title = "到货清单", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ProductArrivalListBo bo) {
        return toAjax(iProductArrivalListService.insertByBo(bo));
    }

    /**
     * 修改到货清单
     */
    @SaCheckPermission("zlyyh:productArrivalList:edit")
    @Log(title = "到货清单", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ProductArrivalListBo bo) {
        return toAjax(iProductArrivalListService.updateByBo(bo));
    }

    /**
     * 删除到货清单
     *
     * @param arrivalListIds 主键串
     */
    @SaCheckPermission("zlyyh:productArrivalList:remove")
    @Log(title = "到货清单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{arrivalListIds}")
    public R<Void> remove(@PathVariable Long[] arrivalListIds) {
        return toAjax(iProductArrivalListService.deleteWithValidByIds(Arrays.asList(arrivalListIds), true));
    }

    /**
     * 获取导入模板
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel(new ArrayList<>(), "清单数据", ProductArrivalListVo.class, response);
    }

    /**
     * 导入
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Void> importData(@RequestPart("file") MultipartFile file, Long platformKey) throws Exception {
        FileImportLogBo logBo = new FileImportLogBo();
        logBo.setName(file.getOriginalFilename());
        List<FileImportLogVo> logVos = fileImportLogService.queryList(logBo);
        if (ObjectUtils.isNotEmpty(logVos)) {
            return R.fail("导入失败，该文件名已存在");
        }
        fileImportLogService.insertByBo(logBo);
        iProductArrivalListService.importMerchant(file,platformKey,logBo);
        return R.ok();
    }
}
