package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.VerifierUserRoleBo;
import org.dromara.shopping.domain.vo.VerifierUserRoleVo;

import java.util.Collection;
import java.util.List;

/**
 * 用户和角色关联Service接口
 *
 * @author yzg
 * @date 2024-11-26
 */
public interface IVerifierUserRoleService {

    /**
     * 查询用户和角色关联
     */
    VerifierUserRoleVo queryById(Long userId);

    /**
     * 查询用户和角色关联列表
     */
    TableDataInfo<VerifierUserRoleVo> queryPageList(VerifierUserRoleBo bo, PageQuery pageQuery);

    /**
     * 查询用户和角色关联列表
     */
    List<VerifierUserRoleVo> queryList(VerifierUserRoleBo bo);

    /**
     * 修改用户和角色关联
     */
    Boolean insertByBo(VerifierUserRoleBo bo);

    /**
     * 修改用户和角色关联
     */
    Boolean updateByBo(VerifierUserRoleBo bo);

    /**
     * 校验并批量删除用户和角色关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
