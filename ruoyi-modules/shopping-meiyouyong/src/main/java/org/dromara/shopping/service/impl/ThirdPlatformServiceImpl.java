package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ThirdPlatform;
import org.dromara.shopping.domain.bo.ThirdPlatformBo;
import org.dromara.shopping.domain.vo.ThirdPlatformVo;
import org.dromara.shopping.mapper.ThirdPlatformMapper;
import org.dromara.shopping.service.IThirdPlatformService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 第三方平台信息配置Service业务层处理
 *
 * @author yzg
 * @date 2024-03-08
 */
@RequiredArgsConstructor
@Service
public class ThirdPlatformServiceImpl implements IThirdPlatformService {

    private final ThirdPlatformMapper baseMapper;

    /**
     * 查询第三方平台信息配置
     */
    @Override
    public ThirdPlatformVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询第三方平台信息配置列表
     */
    @Override
    public TableDataInfo<ThirdPlatformVo> queryPageList(ThirdPlatformBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThirdPlatform> lqw = buildQueryWrapper(bo);
        Page<ThirdPlatformVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询第三方平台信息配置列表
     */
    @Override
    public List<ThirdPlatformVo> queryList(ThirdPlatformBo bo) {
        LambdaQueryWrapper<ThirdPlatform> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ThirdPlatform> buildQueryWrapper(ThirdPlatformBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThirdPlatform> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThirdPlatform::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getSecret()), ThirdPlatform::getSecret, bo.getSecret());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), ThirdPlatform::getType, bo.getType());
        lqw.like(StringUtils.isNotBlank(bo.getAppName()), ThirdPlatform::getAppName, bo.getAppName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ThirdPlatform::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增第三方平台信息配置
     */
    @Override
    public Boolean insertByBo(ThirdPlatformBo bo) {
        ThirdPlatform add = BeanUtil.toBean(bo, ThirdPlatform.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改第三方平台信息配置
     */
    @Override
    public Boolean updateByBo(ThirdPlatformBo bo) {
        ThirdPlatform update = BeanUtil.toBean(bo, ThirdPlatform.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThirdPlatform entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除第三方平台信息配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
