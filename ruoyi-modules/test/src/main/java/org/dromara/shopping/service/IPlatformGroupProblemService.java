package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformGroupProblemBo;
import org.dromara.shopping.domain.vo.PlatformGroupProblemVo;

import java.util.Collection;
import java.util.List;

/**
 * 用户入群问题反馈Service接口
 *
 * @author yzg
 * @date 2024-02-22
 */
public interface IPlatformGroupProblemService {

    /**
     * 查询用户入群问题反馈
     */
    PlatformGroupProblemVo queryById(Long id);

    /**
     * 查询用户入群问题反馈列表
     */
    TableDataInfo<PlatformGroupProblemVo> queryPageList(PlatformGroupProblemBo bo, PageQuery pageQuery);

    /**
     * 查询用户入群问题反馈列表
     */
    List<PlatformGroupProblemVo> queryList(PlatformGroupProblemBo bo);

    /**
     * 修改用户入群问题反馈
     */
    Boolean insertByBo(PlatformGroupProblemBo bo);

    /**
     * 修改用户入群问题反馈
     */
    Boolean updateByBo(PlatformGroupProblemBo bo);

    /**
     * 校验并批量删除用户入群问题反馈信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
