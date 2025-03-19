package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ShopMerchant;
import org.dromara.shopping.domain.bo.ShopMerchantBo;
import org.dromara.shopping.domain.vo.ShopMerchantVo;
import org.dromara.shopping.mapper.ShopMerchantMapper;
import org.dromara.shopping.service.IShopMerchantService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 门店商户号Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class ShopMerchantServiceImpl implements IShopMerchantService {

    private final ShopMerchantMapper baseMapper;

    /**
     * 查询门店商户号
     */
    @Override
    public ShopMerchantVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询门店商户号列表
     */
    @Override
    public TableDataInfo<ShopMerchantVo> queryPageList(ShopMerchantBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ShopMerchant> lqw = buildQueryWrapper(bo);
        Page<ShopMerchantVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询门店商户号列表
     */
    @Override
    public List<ShopMerchantVo> queryList(ShopMerchantBo bo) {
        LambdaQueryWrapper<ShopMerchant> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ShopMerchant> buildQueryWrapper(ShopMerchantBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShopMerchant> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getShopId() != null, ShopMerchant::getShopId, bo.getShopId());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantNo()), ShopMerchant::getMerchantNo, bo.getMerchantNo());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantType()), ShopMerchant::getMerchantType, bo.getMerchantType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ShopMerchant::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增门店商户号
     */
    @Override
    public Boolean insertByBo(ShopMerchantBo bo) {
        ShopMerchant add = BeanUtil.toBean(bo, ShopMerchant.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改门店商户号
     */
    @Override
    public Boolean updateByBo(ShopMerchantBo bo) {
        ShopMerchant update = BeanUtil.toBean(bo, ShopMerchant.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ShopMerchant entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除门店商户号
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public List<ShopMerchantVo> queryByShopId(Long shopId, String merchantType) {
        return baseMapper.selectVoList(new LambdaQueryWrapper<ShopMerchant>().eq(ShopMerchant::getShopId,shopId).eq(ShopMerchant::getMerchantType,merchantType));
    }

    @Override
    public ShopMerchantVo queryByMerNo(String merchantNo) {
        return baseMapper.selectVoOne(new LambdaQueryWrapper<ShopMerchant>().eq(ShopMerchant::getMerchantNo,merchantNo));
    }
}
