package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShareUserRecordBo;
import org.dromara.shopping.domain.vo.ShareStatisticsVo;

import java.util.List;

/**
 * @author xiexi
 * @description
 * @date 2024/12/18 14:06
 */
public interface IShareStatisticsService {

    TableDataInfo<ShareStatisticsVo> queryPageList(ShareUserRecordBo bo, PageQuery pageQuery);

    List<ShareStatisticsVo> queryList(ShareUserRecordBo bo);
}
