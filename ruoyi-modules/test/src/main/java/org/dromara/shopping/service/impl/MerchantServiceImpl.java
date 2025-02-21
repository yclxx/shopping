package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Merchant;
import org.dromara.shopping.domain.bo.MerchantBo;
import org.dromara.shopping.domain.vo.MerchantVo;
import org.dromara.shopping.mapper.MerchantMapper;
import org.dromara.shopping.service.IMerchantService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 商户号Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class MerchantServiceImpl implements IMerchantService {

    private final MerchantMapper baseMapper;

    /**
     * 查询商户号
     */
    @Override
    public MerchantVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商户号列表
     */
    @Override
    public TableDataInfo<MerchantVo> queryPageList(MerchantBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Merchant> lqw = buildQueryWrapper(bo);
        Page<MerchantVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户号列表
     */
    @Override
    public List<MerchantVo> queryList(MerchantBo bo) {
        LambdaQueryWrapper<Merchant> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Merchant> buildQueryWrapper(MerchantBo bo) {
        LambdaQueryWrapper<Merchant> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getMerchantName()), Merchant::getMerchantName, bo.getMerchantName());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantNo()), Merchant::getMerchantNo, bo.getMerchantNo());
        lqw.eq(StringUtils.isNotBlank(bo.getCertPath()), Merchant::getCertPath, bo.getCertPath());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantKey()), Merchant::getMerchantKey, bo.getMerchantKey());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Merchant::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getPayCallbackUrl()), Merchant::getPayCallbackUrl, bo.getPayCallbackUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundCallbackUrl()), Merchant::getRefundCallbackUrl, bo.getRefundCallbackUrl());
        return lqw;
    }

    /**
     * 新增商户号
     */
    @Override
    public Boolean insertByBo(MerchantBo bo) {
        Merchant add = BeanUtil.toBean(bo, Merchant.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商户号
     */
    @Override
    public Boolean updateByBo(MerchantBo bo) {
        Merchant update = BeanUtil.toBean(bo, Merchant.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除商户号
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
