package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.UserIdcardBo;
import org.dromara.shopping.domain.vo.UserIdcardVo;

import java.util.Collection;
import java.util.List;

/**
 * 观影用户信息Service接口
 *
 * @author yzg
 * @date 2023-09-15
 */
public interface IUserIdcardService {

    /**
     * 查询观影用户信息
     */
    UserIdcardVo queryById(Long userIdcardId);

    /**
     * 查询观影用户信息列表
     */
    TableDataInfo<UserIdcardVo> queryPageList(UserIdcardBo bo, PageQuery pageQuery);

    /**
     * 查询观影用户信息列表
     */
    List<UserIdcardVo> queryList(UserIdcardBo bo);

    /**
     * 修改观影用户信息
     */
    Boolean insertByBo(UserIdcardBo bo);

    /**
     * 修改观影用户信息
     */
    Boolean updateByBo(UserIdcardBo bo);

    /**
     * 校验并批量删除观影用户信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
