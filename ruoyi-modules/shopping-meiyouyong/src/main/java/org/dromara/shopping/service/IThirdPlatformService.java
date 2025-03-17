package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ThirdPlatformBo;
import org.dromara.shopping.domain.vo.ThirdPlatformVo;

import java.util.Collection;
import java.util.List;

/**
 * 第三方平台信息配置Service接口
 *
 * @author yzg
 * @date 2024-03-08
 */
public interface IThirdPlatformService {

    /**
     * 查询第三方平台信息配置
     */
    ThirdPlatformVo queryById(Long id);

    /**
     * 查询第三方平台信息配置列表
     */
    TableDataInfo<ThirdPlatformVo> queryPageList(ThirdPlatformBo bo, PageQuery pageQuery);

    /**
     * 查询第三方平台信息配置列表
     */
    List<ThirdPlatformVo> queryList(ThirdPlatformBo bo);

    /**
     * 修改第三方平台信息配置
     */
    Boolean insertByBo(ThirdPlatformBo bo);

    /**
     * 修改第三方平台信息配置
     */
    Boolean updateByBo(ThirdPlatformBo bo);

    /**
     * 校验并批量删除第三方平台信息配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
