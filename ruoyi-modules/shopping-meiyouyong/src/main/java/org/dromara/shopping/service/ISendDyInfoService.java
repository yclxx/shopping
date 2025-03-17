package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SendDyInfoBo;
import org.dromara.shopping.domain.vo.SendDyInfoVo;

import java.util.Collection;
import java.util.List;

/**
 * 用户订阅Service接口
 *
 * @author yzg
 * @date 2023-12-07
 */
public interface ISendDyInfoService {

    /**
     * 查询用户订阅
     */
    SendDyInfoVo queryById(Long id);

    /**
     * 查询用户订阅列表
     */
    TableDataInfo<SendDyInfoVo> queryPageList(SendDyInfoBo bo, PageQuery pageQuery);

    /**
     * 查询用户订阅列表
     */
    List<SendDyInfoVo> queryList(SendDyInfoBo bo);

    /**
     * 湖北订阅消息
     */
    void sendHuBeiDyInfo(String job);

    /**
     * 微信订阅消息
     */
    void sendWxMsg(String job);

    /**
     * 修改用户订阅
     */
    Boolean insertByBo(SendDyInfoBo bo);

    /**
     * 修改用户订阅
     */
    Boolean updateByBo(SendDyInfoBo bo);

    /**
     * 校验并批量删除用户订阅信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
