package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Distributor;
import org.dromara.shopping.domain.bo.DistributorBo;
import org.dromara.shopping.domain.vo.DistributorVo;
import org.dromara.shopping.mapper.DistributorMapper;
import org.dromara.shopping.service.IDistributorService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 分销商信息Service业务层处理
 *
 * @author yzg
 * @date 2023-09-15
 */
@RequiredArgsConstructor
@Service
public class DistributorServiceImpl implements IDistributorService {

    private final DistributorMapper baseMapper;

    /**
     * 查询分销商信息
     */
    @Override
    public DistributorVo queryById(String distributorId) {
        return baseMapper.selectVoById(distributorId);
    }

    /**
     * 查询分销商信息列表
     */
    @Override
    public TableDataInfo<DistributorVo> queryPageList(DistributorBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Distributor> lqw = buildQueryWrapper(bo);
        Page<DistributorVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询分销商信息列表
     */
    @Override
    public List<DistributorVo> queryList(DistributorBo bo) {
        LambdaQueryWrapper<Distributor> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Distributor> buildQueryWrapper(DistributorBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Distributor> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getDistributorName()), Distributor::getDistributorName, bo.getDistributorName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Distributor::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增分销商信息
     */
    @Override
    public Boolean insertByBo(DistributorBo bo) {
        Distributor add = BeanUtil.toBean(bo, Distributor.class);
        if (StringUtils.isBlank(add.getDistributorId())) {
            add.setDistributorId(IdUtil.getSnowflakeNextIdStr());
        } else {
            DistributorVo distributorVo = baseMapper.selectVoById(add.getDistributorId());
            if (null != distributorVo) {
                throw new ServiceException("分销商ID已存在");
            }
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDistributorId(add.getDistributorId());
        }
        return flag;
    }

    /**
     * 修改分销商信息
     */
    @Override
    public Boolean updateByBo(DistributorBo bo) {
        Distributor update = BeanUtil.toBean(bo, Distributor.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除分销商信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
