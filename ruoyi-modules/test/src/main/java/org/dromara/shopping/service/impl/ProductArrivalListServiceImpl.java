package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.DateUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductArrivalList;
import org.dromara.shopping.domain.bo.FileImportLogBo;
import org.dromara.shopping.domain.bo.ProductArrivalListBo;
import org.dromara.shopping.domain.vo.ProductArrivalListVo;
import org.dromara.shopping.mapper.ProductArrivalListMapper;
import org.dromara.shopping.service.IFileImportLogService;
import org.dromara.shopping.service.IProductArrivalListService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 到货清单Service业务层处理
 *
 * @author yzg
 * @date 2024-05-08
 */
@RequiredArgsConstructor
@Service
public class ProductArrivalListServiceImpl implements IProductArrivalListService {

    private final ProductArrivalListMapper baseMapper;
    private final IFileImportLogService fileImportLogService;

    /**
     * 查询到货清单
     */
    @Override
    public ProductArrivalListVo queryById(Long arrivalListId){
        return baseMapper.selectVoById(arrivalListId);
    }

    /**
     * 查询到货清单列表
     */
    @Override
    public TableDataInfo<ProductArrivalListVo> queryPageList(ProductArrivalListBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductArrivalList> lqw = buildQueryWrapper(bo);
        Page<ProductArrivalListVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询到货清单列表
     */
    @Override
    public List<ProductArrivalListVo> queryList(ProductArrivalListBo bo) {
        LambdaQueryWrapper<ProductArrivalList> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductArrivalList> buildQueryWrapper(ProductArrivalListBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductArrivalList> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), ProductArrivalList::getProductName, bo.getProductName());
        lqw.eq(bo.getListDate() != null, ProductArrivalList::getListDate, bo.getListDate());
        lqw.eq(bo.getQty() != null, ProductArrivalList::getQty, bo.getQty());
        lqw.eq(bo.getSendTime() != null, ProductArrivalList::getSendTime, bo.getSendTime());
        lqw.eq(bo.getArriveTime() != null, ProductArrivalList::getArriveTime, bo.getArriveTime());
        lqw.eq(StringUtils.isNotBlank(bo.getIsArrive()), ProductArrivalList::getIsArrive, bo.getIsArrive());
        return lqw;
    }

    /**
     * 新增到货清单
     */
    @Override
    public Boolean insertByBo(ProductArrivalListBo bo) {
        ProductArrivalList add = BeanUtil.toBean(bo, ProductArrivalList.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setArrivalListId(add.getArrivalListId());
        }
        return flag;
    }

    /**
     * 修改到货清单
     */
    @Override
    public Boolean updateByBo(ProductArrivalListBo bo) {
        ProductArrivalList update = BeanUtil.toBean(bo, ProductArrivalList.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductArrivalList entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除到货清单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 导入
     */
    @Override
    @Async
    public void importMerchant(MultipartFile file, Long platformKey, FileImportLogBo logBo) throws IOException {
        List<ProductArrivalListVo> productArrivalListVos = ExcelUtil.importExcel(file.getInputStream(), ProductArrivalListVo.class);
        if (ObjectUtil.isNotEmpty(productArrivalListVos)) {
            logBo.setCount((long) productArrivalListVos.size());
            Long importCount = 0L;
            for (ProductArrivalListVo arrivalListVo : productArrivalListVos) {
                ProductArrivalList productArrivalList = new ProductArrivalList();
                productArrivalList.setPlatformKey(platformKey);
                if (StringUtils.isNotEmpty(arrivalListVo.getListDateCopy())) {
                    productArrivalList.setListDate(DateUtils.parseDate(arrivalListVo.getListDateCopy()));
                }
                if (StringUtils.isNotEmpty(arrivalListVo.getSendTimeCopy())) {
                    productArrivalList.setSendTime(DateUtils.parseDate(arrivalListVo.getSendTimeCopy()));
                }
                if (StringUtils.isNotEmpty(arrivalListVo.getArriveTimeCopy())) {
                    productArrivalList.setArriveTime(DateUtils.parseDate(arrivalListVo.getArriveTimeCopy()));
                }
                productArrivalList.setQty(arrivalListVo.getQty());
                if (StringUtils.isNotEmpty(arrivalListVo.getIsArrive())) {
                    productArrivalList.setIsArrive(arrivalListVo.getIsArrive());
                }
                if (StringUtils.isNotEmpty(arrivalListVo.getProductName())) {
                    productArrivalList.setProductName(arrivalListVo.getProductName());
                }
                int i = baseMapper.insert(productArrivalList);
                if (i > 0) {
                    importCount++;
                }
            }
            logBo.setImportCount(importCount);
            fileImportLogService.updateByBo(logBo);
        }
    }
}
