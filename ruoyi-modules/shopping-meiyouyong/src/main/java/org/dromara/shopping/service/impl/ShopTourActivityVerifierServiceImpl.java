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
import org.dromara.shopping.domain.ShopTourActivityVerifier;
import org.dromara.shopping.domain.Verifier;
import org.dromara.shopping.domain.bo.ShopTourActivityVerifierBo;
import org.dromara.shopping.domain.vo.ShopTourActivityVerifierVo;
import org.dromara.shopping.domain.vo.VerifierVo;
import org.dromara.shopping.mapper.ShopTourActivityVerifierMapper;
import org.dromara.shopping.mapper.VerifierMapper;
import org.dromara.shopping.service.IShopTourActivityVerifierService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 巡检活动白名单Service业务层处理
 *
 * @author yzg
 * @date 2024-04-17
 */
@RequiredArgsConstructor
@Service
public class ShopTourActivityVerifierServiceImpl implements IShopTourActivityVerifierService {

    private final ShopTourActivityVerifierMapper baseMapper;
    private final VerifierMapper verifierMapper;

    /**
     * 查询巡检活动白名单
     */
    @Override
    public ShopTourActivityVerifierVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询巡检活动白名单列表
     */
    @Override
    public TableDataInfo<ShopTourActivityVerifierVo> queryPageList(ShopTourActivityVerifierBo bo, PageQuery pageQuery) {
        if (StringUtils.isNotEmpty(bo.getUserName())) {
            if (StringUtils.isNumeric(bo.getUserName())) {
                List<VerifierVo> verifierVos = verifierMapper.selectVoList(new LambdaQueryWrapper<Verifier>().eq(Verifier::getMobile, bo.getUserName()));
                if (ObjectUtil.isNotEmpty(verifierVos)) {
                    List<Long> verifierIds = verifierVos.stream().map(VerifierVo::getId).collect(Collectors.toList());
                    bo.setVerifierIds(verifierIds.toArray(new Long[0]));
                } else {
                    return TableDataInfo.build(new ArrayList<>());
                }
            } else {
                List<VerifierVo> verifierVos = verifierMapper.selectVoList(new LambdaQueryWrapper<Verifier>().eq(Verifier::getUsername, bo.getUserName()));
                if (ObjectUtil.isNotEmpty(verifierVos)) {
                    List<Long> verifierIds = verifierVos.stream().map(VerifierVo::getId).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
                    bo.setVerifierIds(verifierIds.toArray(new Long[0]));
                } else {
                    return TableDataInfo.build(new ArrayList<>());
                }
            }
        }
        LambdaQueryWrapper<ShopTourActivityVerifier> lqw = buildQueryWrapper(bo);
        Page<ShopTourActivityVerifierVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<ShopTourActivityVerifierVo> dataInfo = TableDataInfo.build(result);
        for (ShopTourActivityVerifierVo row : dataInfo.getRows()) {
            row.setVerifierVo(verifierMapper.selectVoById(row.getVerifierId()));
        }
        return dataInfo;
    }

    /**
     * 查询巡检活动白名单列表
     */
    @Override
    public List<ShopTourActivityVerifierVo> queryList(ShopTourActivityVerifierBo bo) {
        LambdaQueryWrapper<ShopTourActivityVerifier> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ShopTourActivityVerifier> buildQueryWrapper(ShopTourActivityVerifierBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShopTourActivityVerifier> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTourActivityId() != null, ShopTourActivityVerifier::getTourActivityId, bo.getTourActivityId());
        lqw.eq(bo.getVerifierId() != null, ShopTourActivityVerifier::getVerifierId, bo.getVerifierId());
        lqw.in(ObjectUtil.isNotEmpty(bo.getVerifierIds()), ShopTourActivityVerifier::getVerifierId, bo.getVerifierIds());
        return lqw;
    }

    /**
     * 新增巡检活动白名单
     */
    @Override
    public Boolean insertByBo(ShopTourActivityVerifierBo bo) {
        if (ObjectUtil.isNotEmpty(bo.getVerifierIds())) {
            for (Long verifierId : bo.getVerifierIds()) {
                ShopTourActivityVerifier activityVerifier = new ShopTourActivityVerifier();
                activityVerifier.setTourActivityId(bo.getTourActivityId());
                activityVerifier.setVerifierId(verifierId);
                baseMapper.insert(activityVerifier);
            }
            return true;
        }
        ShopTourActivityVerifier add = BeanUtil.toBean(bo, ShopTourActivityVerifier.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改巡检活动白名单
     */
    @Override
    public Boolean updateByBo(ShopTourActivityVerifierBo bo) {
        ShopTourActivityVerifier update = BeanUtil.toBean(bo, ShopTourActivityVerifier.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ShopTourActivityVerifier entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除巡检活动白名单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
