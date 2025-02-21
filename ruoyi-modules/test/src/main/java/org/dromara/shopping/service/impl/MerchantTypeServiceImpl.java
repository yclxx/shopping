package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.MerchantType;
import org.dromara.shopping.domain.bo.MerchantTypeBo;
import org.dromara.shopping.domain.vo.MerchantTypeVo;
import org.dromara.shopping.mapper.MerchantTypeMapper;
import org.dromara.shopping.service.IMerchantTypeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户门店类别Service业务层处理
 *
 * @author yzg
 * @date 2024-01-04
 */
@RequiredArgsConstructor
@Service
public class MerchantTypeServiceImpl implements IMerchantTypeService {

    private final MerchantTypeMapper baseMapper;

    /**
     * 查询商户门店类别
     */
    @Override
    public MerchantTypeVo queryById(Long merchantTypeId){
        return baseMapper.selectVoById(merchantTypeId);
    }

    /**
     * 查询商户门店类别列表
     */
    @Override
    public TableDataInfo<MerchantTypeVo> queryPageList(MerchantTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MerchantType> lqw = buildQueryWrapper(bo);
        Page<MerchantTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户门店类别列表
     */
    @Override
    public List<MerchantTypeVo> queryList(MerchantTypeBo bo) {
        LambdaQueryWrapper<MerchantType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MerchantType> buildQueryWrapper(MerchantTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MerchantType> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTypeName()), MerchantType::getTypeName, bo.getTypeName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MerchantType::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商户门店类别
     */
    @Override
    public Boolean insertByBo(MerchantTypeBo bo) {
        MerchantType add = BeanUtil.toBean(bo, MerchantType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMerchantTypeId(add.getMerchantTypeId());
        }
        return flag;
    }

    /**
     * 修改商户门店类别
     */
    @Override
    public Boolean updateByBo(MerchantTypeBo bo) {
        MerchantType update = BeanUtil.toBean(bo, MerchantType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MerchantType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商户门店类别
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
