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
import org.dromara.shopping.constant.YsfUpConstants;
import org.dromara.shopping.domain.UnionpayProduct;
import org.dromara.shopping.domain.bo.UnionpayProductBo;
import org.dromara.shopping.domain.vo.UnionpayProductVo;
import org.dromara.shopping.mapper.UnionpayProductMapper;
import org.dromara.shopping.service.IUnionpayProductService;
import org.dromara.shopping.service.IYsfConfigService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 银联活动Service业务层处理
 *
 * @author yzg
 * @date 2023-12-08
 */
@RequiredArgsConstructor
@Service
public class UnionpayProductServiceImpl implements IUnionpayProductService {

    private final UnionpayProductMapper baseMapper;
    private final IYsfConfigService ysfConfigService;

    /**
     * 查询银联活动
     */
    @Override
    public UnionpayProductVo queryById(String activityNo) {
        return baseMapper.selectVoById(activityNo);
    }

    /**
     * 查询银联活动列表
     */
    @Override
    public TableDataInfo<UnionpayProductVo> queryPageList(UnionpayProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UnionpayProduct> lqw = buildQueryWrapper(bo);
        Page<UnionpayProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询银联活动列表
     */
    @Override
    public List<UnionpayProductVo> queryList(UnionpayProductBo bo) {
        LambdaQueryWrapper<UnionpayProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UnionpayProduct> buildQueryWrapper(UnionpayProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UnionpayProduct> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getActivityNo()), UnionpayProduct::getActivityNo, bo.getActivityNo());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityTp()), UnionpayProduct::getActivityTp, bo.getActivityTp());
        lqw.between(params.get("beginBeginTime") != null && params.get("endBeginTime") != null,
            UnionpayProduct::getBeginTime, params.get("beginBeginTime"), params.get("endBeginTime"));
        lqw.between(params.get("beginEndTime") != null && params.get("endEndTime") != null,
            UnionpayProduct::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getLimitTp()), UnionpayProduct::getLimitTp, bo.getLimitTp());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityMark()), UnionpayProduct::getActivityMark, bo.getActivityMark());
        return lqw;
    }

    /**
     * 新增银联活动
     */
    @Override
    public Boolean insertByBo(UnionpayProductBo bo) {
        UnionpayProduct add = BeanUtil.toBean(bo, UnionpayProduct.class);
        UnionpayProduct unionpayProduct = validEntityBeforeSave(add);
        if (null != unionpayProduct) {
            return baseMapper.insert(unionpayProduct) > 0;
        }
        return false;
    }

    /**
     * 修改银联活动
     */
    @Override
    public Boolean updateByBo(UnionpayProductBo bo) {
        UnionpayProduct update = BeanUtil.toBean(bo, UnionpayProduct.class);
        UnionpayProduct unionpayProduct = validEntityBeforeSave(update);
        if (null != unionpayProduct) {
            return baseMapper.updateById(unionpayProduct) > 0;
        }
        return false;
    }

    /**
     * 保存前的数据校验
     */
    private UnionpayProduct validEntityBeforeSave(UnionpayProduct entity) {
        if (StringUtils.isBlank(entity.getActivityNo())) {
            throw new ServiceException("缺少活动编号");
        }
        String chnlId = ysfConfigService.queryValueByKey(YsfUpConstants.up_chnlId);
        String appId = ysfConfigService.queryValueByKey(YsfUpConstants.up_appId);
        String rsaPrivateKey = ysfConfigService.queryValueByKey(YsfUpConstants.up_rsaPrivateKey);
        throw new ServiceException();
    }

    /**
     * 批量删除银联活动
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
