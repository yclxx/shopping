package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.TemplatePageBo;
import org.dromara.shopping.domain.vo.TemplatePageVo;

import java.util.Collection;
import java.util.List;

/**
 * 落地页Service接口
 *
 * @author yzg
 * @date 2023-06-09
 */
public interface ITemplatePageService {

    /**
     * 查询落地页
     */
    TemplatePageVo queryById(Long templateId);

    /**
     * 查询落地页列表
     */
    TableDataInfo<TemplatePageVo> queryPageList(TemplatePageBo bo, PageQuery pageQuery);

    /**
     * 查询落地页列表
     */
    List<TemplatePageVo> queryList(TemplatePageBo bo);

    /**
     * 修改落地页
     */
    Boolean insertByBo(TemplatePageBo bo);

    /**
     * 修改落地页
     */
    Boolean updateByBo(TemplatePageBo bo);

    /**
     * 校验并批量删除落地页信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
