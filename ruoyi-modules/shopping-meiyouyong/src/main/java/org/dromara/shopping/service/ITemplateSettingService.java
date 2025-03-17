package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.TemplateSettingBo;
import org.dromara.shopping.domain.vo.TemplateSettingVo;

import java.util.Collection;
import java.util.List;

/**
 * 落地页配置Service接口
 *
 * @author yzg
 * @date 2023-06-09
 */
public interface ITemplateSettingService {

    /**
     * 查询落地页配置
     */
    TemplateSettingVo queryById(Long id);

    /**
     * 查询落地页配置列表
     */
    TableDataInfo<TemplateSettingVo> queryPageList(TemplateSettingBo bo, PageQuery pageQuery);

    /**
     * 查询落地页配置列表
     */
    List<TemplateSettingVo> queryList(TemplateSettingBo bo);

    /**
     * 修改落地页配置
     */
    Boolean insertByBo(TemplateSettingBo bo);

    /**
     * 修改落地页配置
     */
    Boolean updateByBo(TemplateSettingBo bo);

    /**
     * 校验并批量删除落地页配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
