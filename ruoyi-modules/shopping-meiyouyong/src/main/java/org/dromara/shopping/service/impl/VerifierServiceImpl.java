package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Verifier;
import org.dromara.shopping.domain.VerifierSupplier;
import org.dromara.shopping.domain.VerifierUserRole;
import org.dromara.shopping.domain.bo.VerifierBo;
import org.dromara.shopping.domain.vo.VerifierSupplierVo;
import org.dromara.shopping.domain.vo.VerifierVo;
import org.dromara.shopping.mapper.VerifierMapper;
import org.dromara.shopping.mapper.VerifierSupplierMapper;
import org.dromara.shopping.mapper.VerifierUserRoleMapper;
import org.dromara.shopping.service.IVerifierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 核销人员管理Service业务层处理
 *
 * @author yzg
 * @date 2023-11-06
 */
@RequiredArgsConstructor
@Service
public class VerifierServiceImpl implements IVerifierService {

    private final VerifierMapper baseMapper;
    private final VerifierSupplierMapper verifierSupplierMapper;
    private final VerifierUserRoleMapper userRoleMapper;

    /**
     * 查询核销人员管理
     */
    @Override
    public VerifierVo queryById(Long id) {
        VerifierVo verifierVo = baseMapper.selectVoById(id);
        if (ObjectUtil.isNotEmpty(verifierVo)) {
            List<VerifierSupplierVo> verifierSupplierVos = verifierSupplierMapper.selectVoList(new LambdaQueryWrapper<VerifierSupplier>().eq(VerifierSupplier::getVerifierId, id));
            if (ObjectUtil.isNotEmpty(verifierSupplierVos)) {
                verifierVo.setSupplierIds(verifierSupplierVos.stream().map(VerifierSupplierVo::getSupplierId).toArray(Long[]::new));
            }

            List<Long> roleIds = userRoleMapper.selectObjs(Wrappers.lambdaQuery(VerifierUserRole.class).select(VerifierUserRole::getRoleId).eq(VerifierUserRole::getUserId, id));
            verifierVo.setRoleIds(roleIds);
        }
        return verifierVo;
    }

    /**
     * 查询核销人员管理列表
     */
    @Override
    public TableDataInfo<VerifierVo> queryPageList(VerifierBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Verifier> lqw = buildQueryWrapper(bo);
        Page<VerifierVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询核销人员管理列表
     */
    @Override
    public List<VerifierVo> queryList(VerifierBo bo) {
        LambdaQueryWrapper<Verifier> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Verifier> buildQueryWrapper(VerifierBo bo) {
        //Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Verifier> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, Verifier::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getMobile()), Verifier::getMobile, bo.getMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getUsername()), Verifier::getUsername, bo.getUsername());
        //lqw.eq(StringUtils.isNotBlank(bo.getVerifierType()), Verifier::getVerifierType, bo.getVerifierType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Verifier::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getOpenId()), Verifier::getOpenId, bo.getOpenId());
        lqw.eq(bo.getExtensionServiceProviderId() != null, Verifier::getExtensionServiceProviderId, bo.getExtensionServiceProviderId());
        if (StringUtils.isNotBlank(bo.getCity())) {
            lqw.and(lq ->
                lq.like(Verifier::getProvince, bo.getCity())
                    .or().like(Verifier::getCity, bo.getCity())
                    .or().like(Verifier::getDistrict, bo.getCity())
            );
        }
        lqw.notIn(ObjectUtil.isNotEmpty(bo.getVerifierIds()), Verifier::getId, bo.getVerifierIds());
        return lqw;
    }

    /**
     * 新增核销人员管理
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByBo(VerifierBo bo) {
        Verifier add = BeanUtil.toBean(bo, Verifier.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
            if (ObjectUtil.isNotEmpty(bo.getSupplierIds())) {
                for (Long supplierId : bo.getSupplierIds()) {
                    VerifierSupplier verifierSupplier = new VerifierSupplier();
                    verifierSupplier.setVerifierId(bo.getId());
                    verifierSupplier.setSupplierId(supplierId);
                    verifierSupplierMapper.insert(verifierSupplier);
                }
            }
        }
        insertVerifierRole(bo.getId(), bo.getRoleIds());
        return flag;
    }

    /**
     * 修改核销人员管理
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateByBo(VerifierBo bo) {
        Verifier update = BeanUtil.toBean(bo, Verifier.class);
        boolean b = baseMapper.updateById(update) > 0;
        if (b) {
            verifierSupplierMapper.delete(new LambdaQueryWrapper<VerifierSupplier>().eq(VerifierSupplier::getVerifierId, bo.getId()));
            if (ObjectUtil.isNotEmpty(bo.getSupplierIds())) {
                for (Long supplierId : bo.getSupplierIds()) {
                    VerifierSupplier verifierSupplier = new VerifierSupplier();
                    verifierSupplier.setVerifierId(bo.getId());
                    verifierSupplier.setSupplierId(supplierId);
                    verifierSupplierMapper.insert(verifierSupplier);
                }
            }
        }
        // 删除关联
        userRoleMapper.delete(new LambdaQueryWrapper<VerifierUserRole>().eq(VerifierUserRole::getUserId, bo.getId()));
        insertVerifierRole(bo.getId(), bo.getRoleIds());
        return b;
    }

    private void insertVerifierRole(Long userId, List<Long> roleIds) {
        List<VerifierUserRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIds) {
            VerifierUserRole ur = new VerifierUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            userRoleList.add(ur);
        }
        if (!userRoleList.isEmpty()) {
            userRoleMapper.insertBatch(userRoleList);
        }
    }

    /**
     * 批量删除核销人员管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
