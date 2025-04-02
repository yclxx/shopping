package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.QaBo;
import org.dromara.shopping.domain.vo.QaVo;

import java.util.Collection;
import java.util.List;

/**
 * 常见问题Service接口
 *
 * @author yzg
 * @date 2025-04-02
 */
public interface IQaService {

    /**
     * 查询常见问题
     */
    QaVo queryById(Long id);

    /**
     * 查询常见问题列表
     */
    TableDataInfo<QaVo> queryPageList(QaBo bo, PageQuery pageQuery);

    /**
     * 查询常见问题列表
     */
    List<QaVo> queryList(QaBo bo);

    /**
     * 修改常见问题
     */
    Boolean insertByBo(QaBo bo);

    /**
     * 修改常见问题
     */
    Boolean updateByBo(QaBo bo);

    /**
     * 校验并批量删除常见问题信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
