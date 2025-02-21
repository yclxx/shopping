package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.DownloadLogBo;
import org.dromara.shopping.domain.vo.DownloadLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 下载记录Service接口
 *
 * @author yzg
 * @date 2024-08-15
 */
public interface IDownloadLogService {

    /**
     * 查询下载记录
     */
    DownloadLogVo queryById(Long downloadId);

    /**
     * 查询下载记录列表
     */
    TableDataInfo<DownloadLogVo> queryPageList(DownloadLogBo bo, PageQuery pageQuery);

    /**
     * 查询下载记录列表
     */
    List<DownloadLogVo> queryList(DownloadLogBo bo);

    /**
     * 修改下载记录
     */
    Boolean insertByBo(DownloadLogBo bo);

    /**
     * 修改下载记录
     */
    Boolean updateByBo(DownloadLogBo bo);

    /**
     * 校验并批量删除下载记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
