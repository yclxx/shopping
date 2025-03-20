package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.UserBo;
import org.dromara.shopping.domain.vo.UserVo;

import java.util.Collection;
import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface IUserService {

    /**
     * 查询用户信息
     */
    UserVo queryById(Long userId);

    /**
     * 查询用户信息列表
     */
    TableDataInfo<UserVo> queryPageList(UserBo bo, PageQuery pageQuery);

    /**
     * 查询用户信息列表
     */
    List<UserVo> queryList(UserBo bo);

    /**
     * 修改用户信息
     */
    Boolean insertByBo(UserBo bo);

    /**
     * 修改用户信息
     */
    Boolean updateByBo(UserBo bo);

    /**
     * 校验并批量删除用户信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据平台id和手机号获取用户信息
     *
     * @param platformKey 平台id
     * @param mobile      手机号
     * @return 用户信息
     */
    UserVo getUserVoByMobile(Long platformKey, String mobile);

    /**
     * 查询用户ID
     *
     * @param mobile 用户手机号
     * @return 用户ID
     */
    List<Long> getUserIdsByMobile(String mobile);
}
