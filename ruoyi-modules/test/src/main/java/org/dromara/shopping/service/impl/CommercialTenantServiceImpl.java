package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CategoryProduct;
import org.dromara.shopping.domain.CommercialTenant;
import org.dromara.shopping.domain.CommercialTenantProduct;
import org.dromara.shopping.domain.Verifier;
import org.dromara.shopping.domain.bo.CategoryProductBo;
import org.dromara.shopping.domain.bo.CommercialTenantBo;
import org.dromara.shopping.domain.bo.CommercialTenantProductBo;
import org.dromara.shopping.domain.vo.BrandVo;
import org.dromara.shopping.domain.vo.CategoryProductVo;
import org.dromara.shopping.domain.vo.CommercialTenantProductVo;
import org.dromara.shopping.domain.vo.CommercialTenantVo;
import org.dromara.shopping.mapper.BrandMapper;
import org.dromara.shopping.mapper.CommercialTenantMapper;
import org.dromara.shopping.mapper.VerifierMapper;
import org.dromara.shopping.service.ICategoryProductService;
import org.dromara.shopping.service.ICommercialTenantProductService;
import org.dromara.shopping.service.ICommercialTenantService;
import org.dromara.shopping.utils.MobileUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class CommercialTenantServiceImpl implements ICommercialTenantService {

    private final CommercialTenantMapper baseMapper;
    private final ICommercialTenantProductService commercialTenantProductService;
    private final ICategoryProductService categoryProductService;
    private final VerifierMapper verifierMapper;
    private final BrandMapper brandMapper;

    /**
     * 查询商户
     */
    @Override
    public CommercialTenantVo queryById(Long commercialTenantId) {
        CommercialTenantVo tenantVo = baseMapper.selectVoById(commercialTenantId);
        CommercialTenantProductBo categoryProductBo = new CommercialTenantProductBo();
        categoryProductBo.setCommercialTenantId(commercialTenantId);
        List<CommercialTenantProductVo> commercialTenantProductVos = commercialTenantProductService.queryList(categoryProductBo);
        if (ObjectUtil.isNotEmpty(commercialTenantProductVos)) {
            tenantVo.setProductIds(commercialTenantProductVos.stream().map(CommercialTenantProductVo::getProductId).toArray(Long[]::new));
        }
        CategoryProductBo productBo = new CategoryProductBo();
        productBo.setProductId(commercialTenantId);
        List<CategoryProductVo> categoryProductVos = categoryProductService.queryList(productBo);
        if (ObjectUtil.isNotEmpty(categoryProductVos)) {
            tenantVo.setCategoryIds(categoryProductVos.stream().map(CategoryProductVo::getCategoryId).toArray(Long[]::new));
        }
        return tenantVo;
    }

    /**
     * 查询商户
     */
    @Override
    public CommercialTenantVo queryByBrandId(Long brandId) {
        LambdaQueryWrapper<CommercialTenant> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CommercialTenant::getBrandId, brandId);
        wrapper.last("Limit 1");
        return baseMapper.selectVoOne(wrapper);
    }

    @Override
    public CommercialTenantVo queryByYlBrandId(String ylBrandId) {
        LambdaQueryWrapper<CommercialTenant> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CommercialTenant::getYlBrandId, ylBrandId);
        wrapper.last("Limit 1");
        return baseMapper.selectVoOne(wrapper);
    }

    @Override
    public CommercialTenantVo queryByCommercialTenantName(String tenantName) {
        LambdaQueryWrapper<CommercialTenant> lqw = Wrappers.lambdaQuery();
        lqw.eq(CommercialTenant::getCommercialTenantName, tenantName);
        lqw.last("Limit 1");
        return baseMapper.selectVoOne(lqw);
    }

    /**
     * 查询商户列表
     */
    @Override
    public TableDataInfo<CommercialTenantVo> queryPageList(CommercialTenantBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommercialTenant> lqw = buildQueryWrapper(bo);
        Page<CommercialTenantVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户列表
     */
    @Override
    public List<CommercialTenantVo> queryList(CommercialTenantBo bo) {
        LambdaQueryWrapper<CommercialTenant> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommercialTenant> buildQueryWrapper(CommercialTenantBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialTenant> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(bo.getCommercialTenantName())) {
            lqw.like(CommercialTenant::getCommercialTenantName, bo.getCommercialTenantName()).or().like(CommercialTenant::getCommercialTenantTitle, bo.getCommercialTenantName());
        }
        if (MobileUtils.isMobile(bo.getAdminMobile())) {
            lqw.eq(StringUtils.isNotBlank(bo.getAdminMobile()), CommercialTenant::getAdminMobile, bo.getAdminMobile());
        } else {
            lqw.like(StringUtils.isNotBlank(bo.getAdminMobile()), CommercialTenant::getAdminName, bo.getAdminName());
        }
        lqw.eq(StringUtils.isNotBlank(bo.getTags()), CommercialTenant::getTags, bo.getTags());
        lqw.eq(bo.getStartTime() != null, CommercialTenant::getStartTime, bo.getStartTime());
        lqw.eq(bo.getEndTime() != null, CommercialTenant::getEndTime, bo.getEndTime());
        lqw.eq(bo.getVerifierId() != null, CommercialTenant::getVerifierId, bo.getVerifierId());
        lqw.eq(StringUtils.isNotBlank(bo.getIndexShow()), CommercialTenant::getIndexShow, bo.getIndexShow());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CommercialTenant::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getSupplier()), CommercialTenant::getSupplier, bo.getSupplier());
        lqw.eq(bo.getSort() != null, CommercialTenant::getSort, bo.getSort());
        lqw.eq(StringUtils.isNotBlank(bo.getCommercialTenantType()), CommercialTenant::getCommercialTenantType, bo.getCommercialTenantType());
        lqw.eq(bo.getPlatformKey() != null, CommercialTenant::getPlatformKey, bo.getPlatformKey());
        lqw.between(params.get("beginStartTime") != null && params.get("endStartTime") != null,
            CommercialTenant::getStartTime, params.get("beginStartTime"), params.get("endStartTime"));
        lqw.between(params.get("beginEndTime") != null && params.get("endEndTime") != null,
            CommercialTenant::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));
        return lqw;
    }

    /**
     * 新增商户
     */
    @Override
    public Boolean insertByBo(CommercialTenantBo bo) {
        CommercialTenant add = BeanUtil.toBean(bo, CommercialTenant.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCommercialTenantId(add.getCommercialTenantId());
            processCategory(bo, false);
            addVerifier(bo.getAdminMobile());
        }
        return flag;
    }

    private void processCategory(CommercialTenantBo bo, boolean update) {
        if (null == bo.getCommercialTenantId()) {
            return;
        }
        if (update) {
            categoryProductService.remove(new LambdaQueryWrapper<CategoryProduct>().eq(CategoryProduct::getProductId, bo.getCommercialTenantId()));
            commercialTenantProductService.remove(new LambdaQueryWrapper<CommercialTenantProduct>().eq(CommercialTenantProduct::getCommercialTenantId, bo.getCommercialTenantId()));
        }
        if (ObjectUtil.isNotEmpty(bo.getCategoryIds())) {
            for (Long s : bo.getCategoryIds()) {
                CategoryProductBo categoryProductBo = new CategoryProductBo();
                categoryProductBo.setProductId(bo.getCommercialTenantId());
                categoryProductBo.setCategoryId(s);
                categoryProductService.insertByBo(categoryProductBo);
            }
        }
        if (ObjectUtil.isNotEmpty(bo.getProductIds())) {
            for (Long productId : bo.getProductIds()) {
                CommercialTenantProductBo tenantProductBo = new CommercialTenantProductBo();
                tenantProductBo.setCommercialTenantId(bo.getCommercialTenantId());
                tenantProductBo.setProductId(productId);
                commercialTenantProductService.insertByBo(tenantProductBo);
            }
        }
    }

    /**
     * 修改商户
     */
    @Override
    public Boolean updateByBo(CommercialTenantBo bo) {
        CommercialTenant update = BeanUtil.toBean(bo, CommercialTenant.class);
        validEntityBeforeSave(update);
        boolean flag = baseMapper.updateById(update) > 0;
        if (flag) {
            processCategory(bo, true);
            addVerifier(bo.getAdminMobile());
        }
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CommercialTenant entity) {
        if (null != entity.getBrandId()) {
            BrandVo brandVo = brandMapper.selectVoById(entity.getBrandId());
            if (null != brandVo) {
                if (StringUtils.isBlank(entity.getCommercialTenantImg())) {
                    entity.setCommercialTenantImg(brandVo.getBrandImg());
                }
                entity.setCommercialTenantName(brandVo.getBrandName());
            } else {
                if (StringUtils.isNotBlank(entity.getCommercialTenantTitle())) {
                    entity.setCommercialTenantName(entity.getCommercialTenantTitle());
                }
            }
        }
    }

    private void addVerifier(String adminMobile) {
        if (StringUtils.isNotBlank(adminMobile)) {
            Verifier newVerifier = new Verifier();
            newVerifier.setId(IdUtil.getSnowflakeNextId());
            newVerifier.setMobile(adminMobile);
            newVerifier.setReloadUser("0");
            newVerifier.setStatus("0");
            newVerifier.setIsVerifier(true);
            newVerifier.setIsAdmin(true);
            verifierMapper.insert(newVerifier);
        }
    }

    /**
     * 批量删除商户
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (ObjectUtil.isEmpty(ids)) {
            return false;
        }
        categoryProductService.remove(new LambdaQueryWrapper<CategoryProduct>().in(CategoryProduct::getProductId, ids));
        commercialTenantProductService.remove(new LambdaQueryWrapper<CommercialTenantProduct>().in(CommercialTenantProduct::getCommercialTenantId, ids));
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public TableDataInfo<CommercialTenantVo> queryPageCategoryCommercialList(CommercialTenantBo bo, PageQuery pageQuery) {

        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialTenant> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCommercialTenantName()), CommercialTenant::getCommercialTenantName, bo.getCommercialTenantName());

        lqw.eq(StringUtils.isNotBlank(bo.getIndexShow()), CommercialTenant::getIndexShow, bo.getIndexShow());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CommercialTenant::getStatus, bo.getStatus());

        lqw.eq(bo.getPlatformKey() != null, CommercialTenant::getPlatformKey, bo.getPlatformKey());
        lqw.between(params.get("beginStartTime") != null && params.get("endStartTime") != null,
            CommercialTenant::getStartTime, params.get("beginStartTime"), params.get("endStartTime"));
        lqw.between(params.get("beginEndTime") != null && params.get("endEndTime") != null,
            CommercialTenant::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));

        if (ObjectUtil.isNotEmpty(bo.getCategoryId()) && ObjectUtil.isNotEmpty(bo.getSort()) && bo.getSort().equals(0L)) {
            lqw.apply("commercial_tenant_id IN (select product_id from t_category_product where category_id = " + bo.getCategoryId() + ")");
        }
        if (ObjectUtil.isNotEmpty(bo.getCategoryId()) && ObjectUtil.isNotEmpty(bo.getSort()) && bo.getSort().equals(1L)) {
            lqw.apply("commercial_tenant_id NOT IN (select product_id from t_category_product where category_id = " + bo.getCategoryId() + ")");
        }

        Page<CommercialTenantVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);

    }
}
