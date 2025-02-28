package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PageBlockColumn;
import org.dromara.shopping.domain.bo.PageBlockColumnBo;
import org.dromara.shopping.domain.vo.PageBlockColumnVo;
import org.dromara.shopping.mapper.PageBlockColumnMapper;
import org.dromara.shopping.service.IPageBlockColumnService;
import org.dromara.common.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 版块模板字段Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class PageBlockColumnServiceImpl implements IPageBlockColumnService {

    private final PageBlockColumnMapper baseMapper;

    /**
     * 查询版块模板字段
     */
    @Override
    public PageBlockColumnVo queryById(Long columnId){
        return baseMapper.selectVoById(columnId);
    }

    /**
     * 查询版块模板字段列表
     */
    @Override
    public TableDataInfo<PageBlockColumnVo> queryPageList(PageBlockColumnBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PageBlockColumn> lqw = buildQueryWrapper(bo);
        Page<PageBlockColumnVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询版块模板字段列表
     */
    @Override
    public List<PageBlockColumnVo> queryList(PageBlockColumnBo bo) {
        LambdaQueryWrapper<PageBlockColumn> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PageBlockColumn> buildQueryWrapper(PageBlockColumnBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PageBlockColumn> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getBlockId() != null, PageBlockColumn::getBlockId, bo.getBlockId());
        lqw.like(StringUtils.isNotBlank(bo.getColumnName()), PageBlockColumn::getColumnName, bo.getColumnName());
        lqw.eq(StringUtils.isNotBlank(bo.getColumnComment()), PageBlockColumn::getColumnComment, bo.getColumnComment());
        lqw.eq(StringUtils.isNotBlank(bo.getJavaField()), PageBlockColumn::getJavaField, bo.getJavaField());
        lqw.eq(StringUtils.isNotBlank(bo.getIsRequired()), PageBlockColumn::getIsRequired, bo.getIsRequired());
        lqw.eq(StringUtils.isNotBlank(bo.getHtmlType()), PageBlockColumn::getHtmlType, bo.getHtmlType());
        lqw.eq(StringUtils.isNotBlank(bo.getDictType()), PageBlockColumn::getDictType, bo.getDictType());
        lqw.eq(bo.getSort() != null, PageBlockColumn::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增版块模板字段
     */
    @Override
    public Boolean insertByBo(PageBlockColumnBo bo) {
        PageBlockColumn add = BeanUtil.toBean(bo, PageBlockColumn.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setColumnId(add.getColumnId());
        }
        return flag;
    }

    /**
     * 修改版块模板字段
     */
    @Override
    public Boolean updateByBo(PageBlockColumnBo bo) {
        PageBlockColumn update = BeanUtil.toBean(bo, PageBlockColumn.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除版块模板字段
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 查询模板字段
     * @param blockId 模板编号
     */
    @Override
    public List<PageBlockColumnVo> selectListByBlockId(Long blockId) {
        LambdaQueryWrapper<PageBlockColumn> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(PageBlockColumn::getBlockId,blockId);
        wrapper.orderByAsc(PageBlockColumn::getSort);
        return baseMapper.selectVoList(wrapper);
    }
}
