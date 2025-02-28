package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PresidentBo;
import org.dromara.shopping.domain.vo.PresidentVo;

import java.util.Collection;
import java.util.List;

/**
 * 支行长Service接口
 *
 * @author yzg
 * @date 2024-04-28
 */
public interface IPresidentService {

    /**
     * 查询支行长
     */
    PresidentVo queryById(Long presidentId);

    /**
     * 查询支行长列表
     */
    TableDataInfo<PresidentVo> queryPageList(PresidentBo bo, PageQuery pageQuery);

    /**
     * 查询支行长列表
     */
    List<PresidentVo> queryList(PresidentBo bo);

    /**
     * 修改支行长
     */
    Boolean insertByBo(PresidentBo bo);

    /**
     * 修改支行长
     */
    Boolean updateByBo(PresidentBo bo);

    /**
     * 校验并批量删除支行长信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
