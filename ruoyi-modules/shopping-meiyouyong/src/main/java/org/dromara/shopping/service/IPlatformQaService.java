package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformQaBo;
import org.dromara.shopping.domain.vo.PlatformQaVo;

import java.util.Collection;
import java.util.List;

/**
 * 平台常见问题Service接口
 *
 * @author yzg
 * @date 2025-04-02
 */
public interface IPlatformQaService {

    /**
     * 查询平台常见问题
     */
    PlatformQaVo queryById(Long id);

    /**
     * 查询平台常见问题列表
     */
    TableDataInfo<PlatformQaVo> queryPageList(PlatformQaBo bo, PageQuery pageQuery);

    /**
     * 查询平台常见问题列表
     */
    List<PlatformQaVo> queryList(PlatformQaBo bo);

    /**
     * 修改平台常见问题
     */
    Boolean insertByBo(PlatformQaBo bo);

    /**
     * 修改平台常见问题
     */
    Boolean updateByBo(PlatformQaBo bo);

    /**
     * 校验并批量删除平台常见问题信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
