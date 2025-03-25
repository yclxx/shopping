package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.UserAddress;
import org.dromara.shopping.domain.bo.UserAddressBo;
import org.dromara.shopping.domain.vo.UserAddressVo;
import org.dromara.shopping.mapper.UserAddressMapper;
import org.dromara.shopping.service.IUserAddressService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户地址Service业务层处理
 *
 * @author yzg
 * @date 2023-09-15
 */
@RequiredArgsConstructor
@Service
public class UserAddressServiceImpl implements IUserAddressService {

    private final UserAddressMapper baseMapper;

    /**
     * 查询用户地址
     */
    @Override
    public UserAddressVo queryById(Long userAddressId){
        return baseMapper.selectVoById(userAddressId);
    }

    /**
     * 查询用户地址列表
     */
    @Override
    public TableDataInfo<UserAddressVo> queryPageList(UserAddressBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UserAddress> lqw = buildQueryWrapper(bo);
        Page<UserAddressVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户地址列表
     */
    @Override
    public List<UserAddressVo> queryList(UserAddressBo bo) {
        LambdaQueryWrapper<UserAddress> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UserAddress> buildQueryWrapper(UserAddressBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UserAddress> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, UserAddress::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), UserAddress::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getTel()), UserAddress::getTel, bo.getTel());
        lqw.eq(StringUtils.isNotBlank(bo.getIsDefault()), UserAddress::getIsDefault, bo.getIsDefault());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), UserAddress::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getAreaId()), UserAddress::getAreaId, bo.getAreaId());
        lqw.eq(StringUtils.isNotBlank(bo.getAddressInfo()), UserAddress::getAddressInfo, bo.getAddressInfo());
        return lqw;
    }

    /**
     * 新增用户地址
     */
    @Override
    public Boolean insertByBo(UserAddressBo bo) {
        UserAddress add = BeanUtil.toBean(bo, UserAddress.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserAddressId(add.getUserAddressId());
        }
        return flag;
    }

    /**
     * 修改用户地址
     */
    @Override
    public Boolean updateByBo(UserAddressBo bo) {
        UserAddress update = BeanUtil.toBean(bo, UserAddress.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(UserAddress entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户地址
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
