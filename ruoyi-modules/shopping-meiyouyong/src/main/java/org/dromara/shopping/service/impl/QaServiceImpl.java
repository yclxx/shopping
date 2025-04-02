package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Qa;
import org.dromara.shopping.domain.bo.QaBo;
import org.dromara.shopping.domain.vo.QaVo;
import org.dromara.shopping.mapper.QaMapper;
import org.dromara.shopping.service.IQaService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 常见问题Service业务层处理
 *
 * @author yzg
 * @date 2025-04-02
 */
@RequiredArgsConstructor
@Service
public class QaServiceImpl implements IQaService {

    private final QaMapper baseMapper;

    /**
     * 查询常见问题
     */
    @Override
    public QaVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询常见问题列表
     */
    @Override
    public TableDataInfo<QaVo> queryPageList(QaBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Qa> lqw = buildQueryWrapper(bo);
        Page<QaVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询常见问题列表
     */
    @Override
    public List<QaVo> queryList(QaBo bo) {
        LambdaQueryWrapper<Qa> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Qa> buildQueryWrapper(QaBo bo) {
        LambdaQueryWrapper<Qa> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getIssue()), Qa::getIssue, bo.getIssue());
        return lqw;
    }

    /**
     * 新增常见问题
     */
    @Override
    public Boolean insertByBo(QaBo bo) {
        Qa add = BeanUtil.toBean(bo, Qa.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改常见问题
     */
    @Override
    public Boolean updateByBo(QaBo bo) {
        Qa update = BeanUtil.toBean(bo, Qa.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Qa entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除常见问题
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
