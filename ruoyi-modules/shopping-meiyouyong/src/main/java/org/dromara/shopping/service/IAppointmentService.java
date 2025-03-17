package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.AppointmentBo;
import org.dromara.shopping.domain.vo.AppointmentVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * 预约信息Service接口
 *
 * @author yzg
 * @date 2024-06-28
 */
public interface IAppointmentService {

    /**
     * 查询预约信息
     */
    AppointmentVo queryById(Long id);

    /**
     * 查询预约信息列表
     */
    TableDataInfo<AppointmentVo> queryPageList(AppointmentBo bo, PageQuery pageQuery);

    /**
     * 查询预约信息列表
     */
    List<AppointmentVo> queryList(AppointmentBo bo);

    /**
     * 修改预约信息
     */
    Boolean insertByBo(AppointmentBo bo);

    /**
     * 修改预约信息
     */
    Boolean updateByBo(AppointmentBo bo);

    /**
     * 校验并批量删除预约信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void importData(MultipartFile file) throws IOException;
}
