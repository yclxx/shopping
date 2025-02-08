package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Banner;
import org.dromara.shopping.domain.bo.BannerBo;
import org.dromara.shopping.domain.vo.BannerVo;
import org.dromara.shopping.mapper.BannerMapper;
import org.dromara.shopping.service.IBannerService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 广告管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements IBannerService {

    private final BannerMapper baseMapper;

    /**
     * 查询广告管理
     */
    @Override
    public BannerVo queryById(Long bannerId) {
        return baseMapper.selectVoById(bannerId);
    }

    /**
     * 查询广告管理列表
     */
    @Override
    public TableDataInfo<BannerVo> queryPageList(BannerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Banner> lqw = buildQueryWrapper(bo);
        Page<BannerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询广告管理列表
     */
    @Override
    public List<BannerVo> queryList(BannerBo bo) {
        LambdaQueryWrapper<Banner> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Banner> buildQueryWrapper(BannerBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Banner> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBannerName()), Banner::getBannerName, bo.getBannerName());
        lqw.eq(StringUtils.isNotBlank(bo.getBannerMark()), Banner::getBannerMark, bo.getBannerMark());
        lqw.eq(StringUtils.isNotBlank(bo.getBannerImage()), Banner::getBannerImage, bo.getBannerImage());
        lqw.eq(bo.getBannerRank() != null, Banner::getBannerRank, bo.getBannerRank());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Banner::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getBannerType()), Banner::getBannerType, bo.getBannerType());
        lqw.eq(StringUtils.isNotBlank(bo.getPagePath()), Banner::getPagePath, bo.getPagePath());
        lqw.eq(StringUtils.isNotBlank(bo.getToType()), Banner::getToType, bo.getToType());
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), Banner::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), Banner::getUrl, bo.getUrl());
        lqw.eq(bo.getStartTime() != null, Banner::getStartTime, bo.getStartTime());
        lqw.eq(bo.getEndTime() != null, Banner::getEndTime, bo.getEndTime());
        lqw.eq(StringUtils.isNotBlank(bo.getShowDimension()), Banner::getShowDimension, bo.getShowDimension());
        lqw.like(StringUtils.isNotBlank(bo.getShowCity()), Banner::getShowCity, bo.getShowCity());
        lqw.like(StringUtils.isNotBlank(bo.getSendProduct()), Banner::getSendProduct, bo.getSendProduct());
        lqw.eq(bo.getPlatformKey() != null, Banner::getPlatformKey, bo.getPlatformKey());
        lqw.between(params.get("beginStartTime") != null && params.get("endStartTime") != null,
            Banner::getStartTime, params.get("beginStartTime"), params.get("endStartTime"));
        lqw.between(params.get("beginEndTime") != null && params.get("endEndTime") != null,
            Banner::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));
        return lqw;
    }

    /**
     * 新增广告管理
     */
    @Override
    public Boolean insertByBo(BannerBo bo) {
        Banner add = BeanUtil.toBean(bo, Banner.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBannerId(add.getBannerId());
        }
        return flag;
    }

    /**
     * 修改广告管理
     */
    @Override
    public Boolean updateByBo(BannerBo bo) {
        Banner update = BeanUtil.toBean(bo, Banner.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Banner entity) {
        if (!"10".equals(entity.getBannerType())) {
            if (StringUtils.isEmpty(entity.getBannerImage())) {
                throw new ServiceException("banner图不能为空");
            }
        }
    }

    /**
     * 批量删除广告管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
