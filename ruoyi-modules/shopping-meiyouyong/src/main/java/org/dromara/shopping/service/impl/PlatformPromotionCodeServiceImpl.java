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
import org.dromara.shopping.domain.PlatformPromotionCode;
import org.dromara.shopping.domain.bo.PlatformPromotionCodeBo;
import org.dromara.shopping.domain.vo.PlatformPromotionCodeVo;
import org.dromara.shopping.mapper.PlatformPromotionCodeMapper;
import org.dromara.shopping.service.IPlatformPromotionCodeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 平台用户推广码Service业务层处理
 *
 * @author yzg
 * @date 2024-05-27
 */
@RequiredArgsConstructor
@Service
public class PlatformPromotionCodeServiceImpl implements IPlatformPromotionCodeService {

    private final PlatformPromotionCodeMapper baseMapper;

    /**
     * 查询平台用户推广码
     */
    @Override
    public PlatformPromotionCodeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询平台用户推广码列表
     */
    @Override
    public TableDataInfo<PlatformPromotionCodeVo> queryPageList(PlatformPromotionCodeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformPromotionCode> lqw = buildQueryWrapper(bo);
        Page<PlatformPromotionCodeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台用户推广码列表
     */
    @Override
    public List<PlatformPromotionCodeVo> queryList(PlatformPromotionCodeBo bo) {
        LambdaQueryWrapper<PlatformPromotionCode> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformPromotionCode> buildQueryWrapper(PlatformPromotionCodeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformPromotionCode> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformPromotionCode::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getPromotionCode()), PlatformPromotionCode::getPromotionCode, bo.getPromotionCode());
        lqw.eq(StringUtils.isNotBlank(bo.getMobile()), PlatformPromotionCode::getMobile, bo.getMobile());
        lqw.like(StringUtils.isNotBlank(bo.getName()), PlatformPromotionCode::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getInstitution()), PlatformPromotionCode::getInstitution, bo.getInstitution());
        return lqw;
    }

    /**
     * 新增平台用户推广码
     */
    @Override
    public Boolean insertByBo(PlatformPromotionCodeBo bo) {
        PlatformPromotionCode add = BeanUtil.toBean(bo, PlatformPromotionCode.class);
        while (true){
            Random random = new Random();
            int rand = 1000 + random.nextInt(9000);
            //查询平台对应的推广码是否存在
            LambdaQueryWrapper<PlatformPromotionCode> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(PlatformPromotionCode::getPlatformKey,bo.getPlatformKey());
            wrapper.eq(PlatformPromotionCode::getPromotionCode,rand);
            wrapper.last("LIMIT 1");
            PlatformPromotionCodeVo platformPromotionCodeVo = baseMapper.selectVoOne(wrapper);
            if(ObjectUtil.isEmpty(platformPromotionCodeVo)){
                add.setPromotionCode(String.valueOf(rand));
                break;
            }
        }
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改平台用户推广码
     */
    @Override
    public Boolean updateByBo(PlatformPromotionCodeBo bo) {
        PlatformPromotionCode update = BeanUtil.toBean(bo, PlatformPromotionCode.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformPromotionCode entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除平台用户推广码
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}
