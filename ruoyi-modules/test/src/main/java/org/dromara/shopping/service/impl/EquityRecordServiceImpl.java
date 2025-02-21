package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.EquityRecord;
import org.dromara.shopping.domain.bo.EquityRecordBo;
import org.dromara.shopping.domain.vo.EquityRecordVo;
import org.dromara.shopping.mapper.EquityRecordMapper;
import org.dromara.shopping.service.IEquityRecordService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 领取记录Service业务层处理
 *
 * @author yzg
 * @date 2023-06-06
 */
@RequiredArgsConstructor
@Service
public class EquityRecordServiceImpl implements IEquityRecordService {

    private final EquityRecordMapper baseMapper;

    /**
     * 查询领取记录
     */
    @Override
    public EquityRecordVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询领取记录列表
     */
    @Override
    public TableDataInfo<EquityRecordVo> queryPageList(EquityRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EquityRecord> lqw = buildQueryWrapper(bo);
        Page<EquityRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询领取记录列表
     */
    @Override
    public List<EquityRecordVo> queryList(EquityRecordBo bo) {
        LambdaQueryWrapper<EquityRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EquityRecord> buildQueryWrapper(EquityRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EquityRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getEquityId() != null, EquityRecord::getEquityId, bo.getEquityId());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), EquityRecord::getProductName, bo.getProductName());
        lqw.eq(StringUtils.isNotBlank(bo.getEquityType()), EquityRecord::getEquityType, bo.getEquityType());
        lqw.eq(bo.getUserId() != null, EquityRecord::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EquityRecord::getStatus, bo.getStatus());
        lqw.between(params.get("beginReceiveDate") != null && params.get("endReceiveDate") != null,
            EquityRecord::getReceiveDate ,params.get("beginReceiveDate"), params.get("endReceiveDate"));
        lqw.eq(bo.getNumber() != null, EquityRecord::getNumber, bo.getNumber());
        return lqw;
    }

    /**
     * 新增领取记录
     */
    @Override
    public Boolean insertByBo(EquityRecordBo bo) {
        EquityRecord add = BeanUtil.toBean(bo, EquityRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改领取记录
     */
    @Override
    public Boolean updateByBo(EquityRecordBo bo) {
        EquityRecord update = BeanUtil.toBean(bo, EquityRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EquityRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除领取记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
