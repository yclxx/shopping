package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformUserGroupBo;
import org.dromara.shopping.domain.vo.PlatformUserGroupVo;

import java.util.Collection;
import java.util.List;

/**
 * 平台城市企业微信用户来源Service接口
 *
 * @author yzg
 * @date 2024-03-06
 */
public interface IPlatformUserGroupService {

    /**
     * 查询平台城市企业微信用户来源
     */
    PlatformUserGroupVo queryById(Long id);

    /**
     * 查询平台城市企业微信用户来源列表
     */
    TableDataInfo<PlatformUserGroupVo> queryPageList(PlatformUserGroupBo bo, PageQuery pageQuery);

    /**
     * 查询平台城市企业微信用户来源列表
     */
    List<PlatformUserGroupVo> queryList(PlatformUserGroupBo bo);

    /**
     * 修改平台城市企业微信用户来源
     */
    Boolean insertByBo(PlatformUserGroupBo bo);

    /**
     * 修改平台城市企业微信用户来源
     */
    Boolean updateByBo(PlatformUserGroupBo bo);

    /**
     * 校验并批量删除平台城市企业微信用户来源信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
