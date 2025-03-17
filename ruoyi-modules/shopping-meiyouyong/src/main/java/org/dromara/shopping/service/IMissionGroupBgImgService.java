package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MissionGroupBgImgBo;
import org.dromara.shopping.domain.vo.MissionGroupBgImgVo;

import java.util.Collection;
import java.util.List;

/**
 * 任务组背景图片配置Service接口
 *
 * @author yzg
 * @date 2024-01-03
 */
public interface IMissionGroupBgImgService {

    /**
     * 查询任务组背景图片配置
     */
    MissionGroupBgImgVo queryById(Long missionGroupId);

    /**
     * 查询任务组背景图片配置列表
     */
    TableDataInfo<MissionGroupBgImgVo> queryPageList(MissionGroupBgImgBo bo, PageQuery pageQuery);

    /**
     * 查询任务组背景图片配置列表
     */
    List<MissionGroupBgImgVo> queryList(MissionGroupBgImgBo bo);

    /**
     * 修改任务组背景图片配置
     */
    Boolean insertByBo(MissionGroupBgImgBo bo);

    /**
     * 修改任务组背景图片配置
     */
    Boolean updateByBo(MissionGroupBgImgBo bo);

    /**
     * 校验并批量删除任务组背景图片配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
