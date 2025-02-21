package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.*;
import org.dromara.shopping.domain.bo.MerchantApprovalBo;
import org.dromara.shopping.domain.vo.CommercialTenantVo;
import org.dromara.shopping.domain.vo.MerchantApprovalVo;
import org.dromara.shopping.mapper.*;
import org.dromara.shopping.service.IMerchantApprovalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 商户申请审批Service业务层处理
 *
 * @author yzg
 * @date 2023-10-19
 */
@RequiredArgsConstructor
@Service
public class MerchantApprovalServiceImpl implements IMerchantApprovalService {

    private final MerchantApprovalMapper baseMapper;
    private final ShopMapper shopMapper;
    private final CommercialTenantMapper commercialTenantMapper;
    private final ShopMerchantMapper shopMerchantMapper;
    private final VerifierMapper verifierMapper;
    private final VerifierShopMapper verifierShopMapper;

    /**
     * 查询商户申请审批
     */
    @Override
    public MerchantApprovalVo queryById(Long approvalId) {
        return baseMapper.selectVoById(approvalId);
    }

    /**
     * 查询商户申请审批列表
     */
    @Override
    public TableDataInfo<MerchantApprovalVo> queryPageList(MerchantApprovalBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MerchantApproval> lqw = buildQueryWrapper(bo);
        Page<MerchantApprovalVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户申请审批列表
     */
    @Override
    public List<MerchantApprovalVo> queryList(MerchantApprovalBo bo) {
        LambdaQueryWrapper<MerchantApproval> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MerchantApproval> buildQueryWrapper(MerchantApprovalBo bo) {
        //Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MerchantApproval> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, MerchantApproval::getPlatformKey, bo.getPlatformKey());
        lqw.like(StringUtils.isNotBlank(bo.getBrandName()), MerchantApproval::getBrandName, bo.getBrandName());
        lqw.like(StringUtils.isNotBlank(bo.getShopName()), MerchantApproval::getShopName, bo.getShopName());
        lqw.eq(StringUtils.isNotBlank(bo.getShopMobile()), MerchantApproval::getShopMobile, bo.getShopMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getNature()), MerchantApproval::getNature, bo.getNature());
        lqw.eq(StringUtils.isNotBlank(bo.getAccount()), MerchantApproval::getAccount, bo.getAccount());
        lqw.orderByDesc(MerchantApproval::getCreateTime);
        return lqw;
    }

    /**
     * 新增商户申请审批
     */
    @Override
    public Boolean insertByBo(MerchantApprovalBo bo) {
        MerchantApproval add = BeanUtil.toBean(bo, MerchantApproval.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setApprovalId(add.getApprovalId());
        }
        return flag;
    }

    /**
     * 修改商户申请审批
     */
    @Override
    public Boolean updateByBo(MerchantApprovalBo bo) {
        // 审批通过处理
        if (bo.getApprovalStatus().equals("1")) {
            // 查询申请信息
            MerchantApprovalVo vo = baseMapper.selectVoById(bo.getApprovalId());
            // 处理核销人员信息
            Verifier verifier = insertOrUpdateVerifier(vo);
            // 处理商户表
            CommercialTenantVo commercialTenantVo = insertCommercialTenantVo(vo, verifier);
            // 处理门店相关信息
            handleShop(vo, verifier, commercialTenantVo);
        }
        MerchantApproval update = BeanUtil.toBean(bo, MerchantApproval.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 处理核销人员信息
     */
    private Verifier insertOrUpdateVerifier(MerchantApprovalVo vo) {
        Verifier verifier = verifierMapper.selectOne(new LambdaQueryWrapper<Verifier>().eq(Verifier::getMobile, vo.getBrandMobile()).eq(Verifier::getPlatformKey, vo.getPlatformKey()));
        if (ObjectUtil.isNotEmpty(verifier)) {
            verifier.setPlatformKey(vo.getPlatformKey());
            verifier.setMobile(vo.getBrandMobile());
            //verifier.setVerifierType("admin");
            verifier.setIsVerifier(true);
            verifier.setIsAdmin(true);
            verifier.setIsBd(false);
            verifierMapper.updateById(verifier);
        } else {
            verifier = new Verifier();
            verifier.setId(IdUtil.getSnowflakeNextId());
            verifier.setPlatformKey(vo.getPlatformKey());
            verifier.setMobile(vo.getBrandMobile());
            verifier.setStatus("0");
            verifier.setIsVerifier(true);
            verifier.setIsAdmin(true);
            verifier.setIsBd(false);
            //verifier.setVerifierType("admin");
            verifierMapper.insert(verifier);
        }
        return verifier;
    }

    private CommercialTenantVo insertCommercialTenantVo(MerchantApprovalVo vo, Verifier verifier) {
        LambdaQueryWrapper<CommercialTenant> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(CommercialTenant::getCommercialTenantName, vo.getBrandName());
        CommercialTenantVo commercialTenantVo = commercialTenantMapper.selectVoOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(commercialTenantVo)) return commercialTenantVo;
        CommercialTenant commercialTenant = new CommercialTenant();
        commercialTenant.setCommercialTenantId(IdUtil.getSnowflakeNextId());
        commercialTenant.setCommercialTenantName(vo.getBrandName());
        commercialTenant.setVerifierId(verifier.getId());
        // 活动商户
        if (StringUtils.isNotEmpty(vo.getActivityNature())) {
            commercialTenant.setActivityNature(vo.getActivityNature());
        }
        // 商户Logo
        if (StringUtils.isNotEmpty(vo.getBrandLogo())) {
            commercialTenant.setCommercialTenantImg(vo.getBrandLogo());
        }
        // 负责人手机号
        if (StringUtils.isNotEmpty(vo.getBrandMobile())) {
            commercialTenant.setAdminMobile(vo.getBrandMobile());
        }
        // 商户简称
        if (StringUtils.isNotEmpty(vo.getBrandReferred())) {
            commercialTenant.setCommercialTenantTitle(vo.getBrandReferred());
        }
        // 性质
        if (StringUtils.isNotEmpty(vo.getNature())) {
            commercialTenant.setNature(vo.getNature());
        }
        // 发票类型
        if (StringUtils.isNotEmpty(vo.getInvoiceType())) {
            commercialTenant.setInvoice(vo.getInvoiceType());
        }
        // 营业执照
        if (StringUtils.isNotEmpty(vo.getBusinessLicense())) {
            commercialTenant.setLicense(vo.getBusinessLicense());
        }
        // 活动类型
        if (StringUtils.isNotEmpty(vo.getActivity())) {
            commercialTenant.setActivity(vo.getActivity());
        }
        commercialTenantMapper.insert(commercialTenant);
        return MapstructUtils.convert(commercialTenant, CommercialTenantVo.class);
    }

    private void handleShop(MerchantApprovalVo vo, Verifier verifier, CommercialTenantVo commercialTenantVo) {
        // 新增门店
        Shop shop = new Shop();
        shop.setShopId(IdUtil.getSnowflakeNextId());
        // 门店展示平台
        shop.setPlatformKey(vo.getMerchantPlatformKey());
        // 门店名称
        shop.setShopName(vo.getShopName());
        // 门店联系电话
        shop.setShopTel(vo.getShopMobile());
        shop.setStatus("0");
        shop.setShopType(vo.getShopType());
        shop.setAddress(vo.getShopAddressInfo());
        shop.setCommercialTenantId(commercialTenantVo.getCommercialTenantId());
        shop.setHoliday(vo.getBusinessHoliday());
        if (StringUtils.isNotEmpty(vo.getShopImage())) {
            shop.setShopImgs(vo.getShopImage());
        }
        if (StringUtils.isNotEmpty(vo.getBrandLogo())) {
            shop.setShopLogo(vo.getBrandLogo());
        }
        if (StringUtils.isNotEmpty(vo.getExtend())) { // 进件者
            shop.setExtensionServiceProviderId(Long.valueOf(vo.getExtend()));
        }
        // 营业执照
        if (StringUtils.isNotEmpty(vo.getBusinessLicense())) {
            shop.setLicense(vo.getBusinessLicense());
        }
        // 营业时间
        if (StringUtils.isNotEmpty(vo.getBusinessBegin()) && StringUtils.isNotEmpty(vo.getBusinessBegin())) {
            shop.setBusinessHours(vo.getBusinessBegin() + "-" + vo.getBusinessEnd());
        }
        // 收款账号
        if (StringUtils.isNotEmpty(vo.getAccount())) {
            shop.setAccount(vo.getAccount());
        }
        // 收款人
        if (StringUtils.isNotEmpty(vo.getAccountPayee())) {
            shop.setAccountPayee(vo.getAccountPayee());
        }
        // 开户行
        if (StringUtils.isNotEmpty(vo.getAccountBank())) {
            shop.setAccountBank(vo.getAccountBank());
        }
        // 性质
        if (StringUtils.isNotEmpty(vo.getNature())) {
            shop.setNature(vo.getNature());
        }
        // 发票
        if (StringUtils.isNotEmpty(vo.getInvoiceType())) {
            shop.setInvoice(vo.getInvoiceType());
        }
        // 活动类型
        if (StringUtils.isNotEmpty(vo.getActivity())) {
            shop.setActivity(vo.getActivity());
        }
        // 营业周
        if (StringUtils.isNotEmpty(vo.getBusinessWeek())) {
            shop.setAssignDate("1");
            shop.setWeekDate(vo.getBusinessWeek());
        }

        List<ShopMerchant> shopMerchants = new ArrayList<>();

        int insert = shopMapper.insert(shop);
        // 核销人员，门店表关联
        if (insert > 0) {
            VerifierShop verifierShop = new VerifierShop();
            verifierShop.setId(IdUtil.getSnowflakeNextId());
            verifierShop.setShopId(shop.getShopId());
            verifierShop.setVerifierId(verifier.getId());
            verifierShop.setSort(99L);
            verifierShopMapper.insert(verifierShop);
        }
        if (ObjectUtil.isNotEmpty(shopMerchants) && insert > 0) {
            shopMerchantMapper.insertBatch(shopMerchants);
        }
    }

    /**
     * 批量删除商户申请审批
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
