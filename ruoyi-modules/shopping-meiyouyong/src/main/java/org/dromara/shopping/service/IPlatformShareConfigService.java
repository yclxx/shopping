package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.PlatformShareConfigBo;
import org.dromara.shopping.domain.vo.PlatformShareConfigVo;

import java.util.List;

/**
 * 平台分享配置Service接口
 *
 * @author yzg
 * @date 2025-01-14
 */
public interface IPlatformShareConfigService {

    /**
     * 查询平台分享配置
     */
    PlatformShareConfigVo queryById(Long platformKey);

    /**
     * 查询平台分享配置列表
     */
    TableDataInfo<PlatformShareConfigVo> queryPageList(PlatformShareConfigBo bo, PageQuery pageQuery);

    /**
     * 查询平台分享配置列表
     */
    List<PlatformShareConfigVo> queryList(PlatformShareConfigBo bo);

    /**
     * 修改平台分享配置
     */
    Boolean updateByBo(PlatformShareConfigBo bo);

}
