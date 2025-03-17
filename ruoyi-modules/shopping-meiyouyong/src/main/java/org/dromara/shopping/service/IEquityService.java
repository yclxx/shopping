package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.EquityBo;
import org.dromara.shopping.domain.vo.EquityVo;

import java.util.Collection;
import java.util.List;

/**
 * 权益包Service接口
 *
 * @author yzg
 * @date 2023-06-06
 */
public interface IEquityService {

    /**
     * 查询权益包
     */
    EquityVo queryById(Long equityId);

    /**
     * 查询权益包列表
     */
    TableDataInfo<EquityVo> queryPageList(EquityBo bo, PageQuery pageQuery);

    /**
     * 查询权益包列表
     */
    List<EquityVo> queryList(EquityBo bo);

    /**
     * 修改权益包
     */
    Boolean insertByBo(EquityBo bo);

    /**
     * 修改权益包
     */
    Boolean updateByBo(EquityBo bo);

    /**
     * 校验并批量删除权益包信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
