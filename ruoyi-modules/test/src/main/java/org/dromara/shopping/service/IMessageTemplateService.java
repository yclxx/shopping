package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.MessageTemplateBo;
import org.dromara.shopping.domain.vo.MessageTemplateVo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * 消息模板Service接口
 *
 * @author yzg
 * @date 2023-11-23
 */
public interface IMessageTemplateService {

    /**
     * 查询消息模板
     */
    MessageTemplateVo queryById(Long templateId);

    /**
     * 查询消息模板列表
     */
    TableDataInfo<MessageTemplateVo> queryPageList(MessageTemplateBo bo, PageQuery pageQuery);

    /**
     * 查询消息模板列表
     */
    List<MessageTemplateVo> queryList(MessageTemplateBo bo);

    /**
     * 修改消息模板
     */
    Boolean insertByBo(MessageTemplateBo bo);

    /**
     * 修改消息模板
     */
    Boolean updateByBo(MessageTemplateBo bo);

    /**
     * 校验并批量删除消息模板信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void importData(MessageTemplateBo bo) throws IOException;
}
