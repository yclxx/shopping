package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.YsfConfigBo;
import org.dromara.shopping.domain.vo.YsfConfigVo;

import java.util.Collection;
import java.util.List;

/**
 * 云闪付参数配置Service接口
 *
 * @author yzg
 * @date 2023-07-31
 */
public interface IYsfConfigService {

    String queryValueByKey(Long platformId, String key);

    /**
     * 获取全平台统一公共参数
     */
    String queryValueByKey(String key);
    /**
     * 查询云闪付参数配置
     */
    YsfConfigVo queryById(Long configId);

    /**
     * 查询云闪付参数配置列表
     */
    TableDataInfo<YsfConfigVo> queryPageList(YsfConfigBo bo, PageQuery pageQuery);

    /**
     * 查询云闪付参数配置列表
     */
    List<YsfConfigVo> queryList(YsfConfigBo bo);

    /**
     * 修改云闪付参数配置
     */
    Boolean insertByBo(YsfConfigBo bo);

    /**
     * 修改云闪付参数配置
     */
    Boolean updateByBo(YsfConfigBo bo);

    /**
     * 校验并批量删除云闪付参数配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
