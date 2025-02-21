package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.LianlianCity;
import org.dromara.shopping.domain.bo.LianlianCityBo;
import org.dromara.shopping.domain.vo.LianlianCityVo;
import org.dromara.shopping.mapper.LianlianCityMapper;
import org.dromara.shopping.service.ILianlianCityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 联联市级城市Service业务层处理
 *
 * @author yzg
 * @date 2023-09-15
 */
@RequiredArgsConstructor
@Service
public class LianlianCityServiceImpl implements ILianlianCityService {

    private final LianlianCityMapper baseMapper;

    /**
     * 查询联联市级城市
     */
    @Override
    public LianlianCityVo queryById(Long cityId) {
        return baseMapper.selectVoById(cityId);
    }

    public Page<LianlianCity> selectLlianCityCodeList(String status, Integer pageNum, Integer pageSize) {
        Page<LianlianCity> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<LianlianCity> cityWrapper = new LambdaQueryWrapper<>();
        cityWrapper.eq(LianlianCity::getStatus, status);
        return baseMapper.selectPage(page, cityWrapper);
    }

    /**
     * 查询联联市级城市列表
     */
    @Override
    public TableDataInfo<LianlianCityVo> queryPageList(LianlianCityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<LianlianCity> lqw = buildQueryWrapper(bo);
        Page<LianlianCityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询联联市级城市列表
     */
    @Override
    public List<LianlianCityVo> queryList(LianlianCityBo bo) {
        LambdaQueryWrapper<LianlianCity> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<LianlianCity> buildQueryWrapper(LianlianCityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<LianlianCity> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCityName()), LianlianCity::getCityName, bo.getCityName());
        lqw.eq(StringUtils.isNotBlank(bo.getCityCode()), LianlianCity::getCityCode, bo.getCityCode());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), LianlianCity::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增联联市级城市
     */
    @Override
    public Boolean insertByBo(LianlianCityBo bo) {
        LianlianCity add = BeanUtil.toBean(bo, LianlianCity.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCityId(add.getCityId());
        }
        return flag;
    }

    /**
     * 修改联联市级城市
     */
    @Override
    public Boolean updateByBo(LianlianCityBo bo) {
        LianlianCity update = BeanUtil.toBean(bo, LianlianCity.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(LianlianCity entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除联联市级城市
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
