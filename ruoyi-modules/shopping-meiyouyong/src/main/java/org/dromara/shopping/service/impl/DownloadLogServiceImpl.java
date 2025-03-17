package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.shopping.domain.DownloadLog;
import org.dromara.shopping.domain.bo.DownloadLogBo;
import org.dromara.shopping.domain.vo.DownloadLogVo;
import org.dromara.shopping.mapper.DownloadLogMapper;
import org.dromara.shopping.service.IDownloadLogService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 下载记录Service业务层处理
 *
 * @author yzg
 * @date 2024-08-15
 */
@RequiredArgsConstructor
@Service
public class DownloadLogServiceImpl implements IDownloadLogService {

    private final DownloadLogMapper baseMapper;

    /**
     * 查询下载记录
     */
    @Override
    public DownloadLogVo queryById(Long downloadId){
        return baseMapper.selectVoById(downloadId);
    }

    /**
     * 查询下载记录列表
     */
    @Override
    public TableDataInfo<DownloadLogVo> queryPageList(DownloadLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DownloadLog> lqw = buildQueryWrapper(bo);
        Page<DownloadLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询下载记录列表
     */
    @Override
    public List<DownloadLogVo> queryList(DownloadLogBo bo) {
        LambdaQueryWrapper<DownloadLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DownloadLog> buildQueryWrapper(DownloadLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DownloadLog> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getDownloadRemark()), DownloadLog::getDownloadRemark, bo.getDownloadRemark());
        lqw.eq(StringUtils.isNotBlank(bo.getDownloadSource()), DownloadLog::getDownloadSource, bo.getDownloadSource());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), DownloadLog::getStatus, bo.getStatus());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            DownloadLog::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增下载记录
     */
    @Override
    public Boolean insertByBo(DownloadLogBo bo) {
        DownloadLog add = BeanUtil.toBean(bo, DownloadLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDownloadId(add.getDownloadId());
        }
        return flag;
    }

    /**
     * 修改下载记录
     */
    @Override
    public Boolean updateByBo(DownloadLogBo bo) {
        DownloadLog update = BeanUtil.toBean(bo, DownloadLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DownloadLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除下载记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
