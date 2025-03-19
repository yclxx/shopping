package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.SupplierUser;
import org.dromara.shopping.domain.bo.SupplierUserBo;
import org.dromara.shopping.domain.vo.SupplierUserVo;
import org.dromara.shopping.mapper.SupplierUserMapper;
import org.dromara.shopping.service.ISupplierUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 供应商用户关联Service业务层处理
 *
 * @author yzg
 * @date 2024-12-20
 */
@RequiredArgsConstructor
@Service
public class SupplierUserServiceImpl implements ISupplierUserService {

    private final SupplierUserMapper baseMapper;

    /**
     * 查询供应商用户关联
     */
    @Override
    public SupplierUserVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询供应商用户关联列表
     */
    @Override
    public TableDataInfo<SupplierUserVo> queryPageList(SupplierUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SupplierUser> lqw = buildQueryWrapper(bo);
        Page<SupplierUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商用户关联列表
     */
    @Override
    public List<SupplierUserVo> queryList(SupplierUserBo bo) {
        LambdaQueryWrapper<SupplierUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SupplierUser> buildQueryWrapper(SupplierUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SupplierUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSupplierId() != null, SupplierUser::getSupplierId, bo.getSupplierId());
        lqw.eq(bo.getUserId() != null, SupplierUser::getUserId, bo.getUserId());
        return lqw;
    }

    /**
     * 新增供应商用户关联
     */
    @Override
    public Boolean insertByBo(SupplierUserBo bo) {
        SupplierUser add = BeanUtil.toBean(bo, SupplierUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改供应商用户关联
     */
    @Override
    public Boolean updateByBo(SupplierUserBo bo) {
        SupplierUser update = BeanUtil.toBean(bo, SupplierUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SupplierUser entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商用户关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Long querySupplierId(Long userId) {
        //第一步，判断是否是供应商账号
        LambdaQueryWrapper<SupplierUser> supplierUserWrapper = Wrappers.lambdaQuery();
        supplierUserWrapper.eq(SupplierUser::getUserId, userId);
        supplierUserWrapper.last("LIMIT 1");
        SupplierUser supplierUser = baseMapper.selectOne(supplierUserWrapper);
        if (ObjectUtil.isNotEmpty(supplierUser)) {
            return supplierUser.getSupplierId();
        }
        return null;
    }
}
