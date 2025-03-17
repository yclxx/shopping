package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.RecordLogBo;
import org.dromara.shopping.domain.vo.RecordLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 记录日志Service接口
 *
 * @author yzg
 * @date 2023-08-01
 */
public interface IRecordLogService {

    /**
     * 查询记录日志
     */
    RecordLogVo queryById(Long recordId);

    /**
     * 查询记录日志列表
     */
    TableDataInfo<RecordLogVo> queryPageList(RecordLogBo bo, PageQuery pageQuery);

    /**
     * 查询记录日志列表
     */
    List<RecordLogVo> queryList(RecordLogBo bo);

    /**
     * 修改记录日志
     */
    Boolean insertByBo(RecordLogBo bo);

    /**
     * 修改记录日志
     */
    Boolean updateByBo(RecordLogBo bo);

    /**
     * 校验并批量删除记录日志信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
