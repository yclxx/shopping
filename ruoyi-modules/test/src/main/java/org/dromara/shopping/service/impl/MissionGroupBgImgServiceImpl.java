package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.MissionGroupBgImg;
import org.dromara.shopping.domain.bo.MissionGroupBgImgBo;
import org.dromara.shopping.domain.vo.MissionGroupBgImgVo;
import org.dromara.shopping.mapper.MissionGroupBgImgMapper;
import org.dromara.shopping.service.IMissionGroupBgImgService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 任务组背景图片配置Service业务层处理
 *
 * @author yzg
 * @date 2024-01-03
 */
@RequiredArgsConstructor
@Service
public class MissionGroupBgImgServiceImpl implements IMissionGroupBgImgService {

    private final MissionGroupBgImgMapper baseMapper;

    /**
     * 查询任务组背景图片配置
     */
    @Override
    public MissionGroupBgImgVo queryById(Long missionGroupId){
        return baseMapper.selectVoById(missionGroupId);
    }

    /**
     * 查询任务组背景图片配置列表
     */
    @Override
    public TableDataInfo<MissionGroupBgImgVo> queryPageList(MissionGroupBgImgBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MissionGroupBgImg> lqw = buildQueryWrapper(bo);
        Page<MissionGroupBgImgVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询任务组背景图片配置列表
     */
    @Override
    public List<MissionGroupBgImgVo> queryList(MissionGroupBgImgBo bo) {
        LambdaQueryWrapper<MissionGroupBgImg> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MissionGroupBgImg> buildQueryWrapper(MissionGroupBgImgBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MissionGroupBgImg> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMissionGroupId() != null, MissionGroupBgImg::getMissionGroupId, bo.getMissionGroupId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MissionGroupBgImg::getStatus, bo.getStatus());
        lqw.eq(bo.getStartDate() != null, MissionGroupBgImg::getStartDate, bo.getStartDate());
        lqw.eq(bo.getEndDate() != null, MissionGroupBgImg::getEndDate, bo.getEndDate());
        lqw.eq(StringUtils.isNotBlank(bo.getMissionBgImg()), MissionGroupBgImg::getMissionBgImg, bo.getMissionBgImg());
        lqw.eq(bo.getPlatformKey() != null, MissionGroupBgImg::getPlatformKey, bo.getPlatformKey());
        return lqw;
    }

    /**
     * 新增任务组背景图片配置
     */
    @Override
    public Boolean insertByBo(MissionGroupBgImgBo bo) {
        MissionGroupBgImg add = BeanUtil.toBean(bo, MissionGroupBgImg.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMissionGroupId(add.getMissionGroupId());
        }
        return flag;
    }

    /**
     * 修改任务组背景图片配置
     */
    @Override
    public Boolean updateByBo(MissionGroupBgImgBo bo) {
        MissionGroupBgImg update = BeanUtil.toBean(bo, MissionGroupBgImg.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MissionGroupBgImg entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除任务组背景图片配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
