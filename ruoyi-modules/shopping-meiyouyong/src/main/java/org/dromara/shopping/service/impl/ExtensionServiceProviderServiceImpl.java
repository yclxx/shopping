package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ExtensionServiceProvider;
import org.dromara.shopping.domain.bo.ExtensionServiceProviderBo;
import org.dromara.shopping.domain.vo.ExtensionServiceProviderVo;
import org.dromara.shopping.mapper.ExtensionServiceProviderMapper;
import org.dromara.shopping.service.IExtensionServiceProviderService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户拓展服务商Service业务层处理
 *
 * @author yzg
 * @date 2023-09-15
 */
@RequiredArgsConstructor
@Service
public class ExtensionServiceProviderServiceImpl implements IExtensionServiceProviderService {

    private final ExtensionServiceProviderMapper baseMapper;

    /**
     * 查询商户拓展服务商
     */
    @Override
    public ExtensionServiceProviderVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商户拓展服务商列表
     */
    @Override
    public TableDataInfo<ExtensionServiceProviderVo> queryPageList(ExtensionServiceProviderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ExtensionServiceProvider> lqw = buildQueryWrapper(bo);
        Page<ExtensionServiceProviderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户拓展服务商列表
     */
    @Override
    public List<ExtensionServiceProviderVo> queryList(ExtensionServiceProviderBo bo) {
        LambdaQueryWrapper<ExtensionServiceProvider> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ExtensionServiceProvider> buildQueryWrapper(ExtensionServiceProviderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ExtensionServiceProvider> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getProviderName()), ExtensionServiceProvider::getProviderName, bo.getProviderName());
        lqw.like(StringUtils.isNotBlank(bo.getProviderUserName()), ExtensionServiceProvider::getProviderUserName, bo.getProviderUserName());
        lqw.eq(StringUtils.isNotBlank(bo.getProviderUserMobile()), ExtensionServiceProvider::getProviderUserMobile, bo.getProviderUserMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ExtensionServiceProvider::getStatus, bo.getStatus());
        lqw.eq(bo.getSysDeptId() != null, ExtensionServiceProvider::getSysDeptId, bo.getSysDeptId());
        lqw.eq(bo.getSysUserId() != null, ExtensionServiceProvider::getSysUserId, bo.getSysUserId());
        return lqw;
    }

    /**
     * 新增商户拓展服务商
     */
    @Override
    public Boolean insertByBo(ExtensionServiceProviderBo bo) {
        ExtensionServiceProvider add = BeanUtil.toBean(bo, ExtensionServiceProvider.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商户拓展服务商
     */
    @Override
    public Boolean updateByBo(ExtensionServiceProviderBo bo) {
        ExtensionServiceProvider update = BeanUtil.toBean(bo, ExtensionServiceProvider.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ExtensionServiceProvider entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商户拓展服务商
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
