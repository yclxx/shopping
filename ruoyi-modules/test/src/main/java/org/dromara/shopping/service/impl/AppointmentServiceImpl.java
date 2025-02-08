package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Appointment;
import org.dromara.shopping.domain.Product;
import org.dromara.shopping.domain.bo.AppointmentBo;
import org.dromara.shopping.domain.vo.AppointmentVo;
import org.dromara.shopping.mapper.AppointmentMapper;
import org.dromara.shopping.mapper.ProductMapper;
import org.dromara.shopping.service.IAppointmentService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 预约信息Service业务层处理
 *
 * @author yzg
 * @date 2024-06-28
 */
@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private final AppointmentMapper baseMapper;
    private final ProductMapper productMapper;

    /**
     * 查询预约信息
     */
    @Override
    public AppointmentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询预约信息列表
     */
    @Override
    public TableDataInfo<AppointmentVo> queryPageList(AppointmentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Appointment> lqw = buildQueryWrapper(bo);
        Page<AppointmentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<AppointmentVo> build = TableDataInfo.build(result);
        List<AppointmentVo> rows = build.getRows();
        if(CollectionUtils.isNotEmpty(rows)){
            for (AppointmentVo row : rows) {
                Product product = productMapper.selectById(row.getProductId());
                if(null == product){
                    continue;
                }
                row.setProductName(product.getProductName());
            }
        }
        return build;
    }

    /**
     * 查询预约信息列表
     */
    @Override
    public List<AppointmentVo> queryList(AppointmentBo bo) {
        LambdaQueryWrapper<Appointment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Appointment> buildQueryWrapper(AppointmentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Appointment> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, Appointment::getProductId, bo.getProductId());
        lqw.eq(bo.getProductSkuId() != null, Appointment::getProductSkuId, bo.getProductSkuId());
        lqw.eq(bo.getAppointmentDate() != null, Appointment::getAppointmentDate, bo.getAppointmentDate());
        lqw.eq(bo.getAppointmentCount() != null, Appointment::getAppointmentCount, bo.getAppointmentCount());
        lqw.between(params.get("beginAppointmentDate") != null && params.get("endAppointmentDate") != null, Appointment::getAppointmentDate, params.get("beginAppointmentDate"), params.get("endAppointmentDate"));
        return lqw;
    }

    /**
     * 新增预约信息
     */
    @Override
    public Boolean insertByBo(AppointmentBo bo) {
        Appointment add = BeanUtil.toBean(bo, Appointment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改预约信息
     */
    @Override
    public Boolean updateByBo(AppointmentBo bo) {
        Appointment update = BeanUtil.toBean(bo, Appointment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Appointment entity){
        //TODO 做一些数据校验,如唯一约束
        //验证产品是否存在
        Product product = productMapper.selectById(entity.getProductId());
        if(null == product){
            throw new ServiceException("商品不存在");
        }
    }

    /**
     * 批量删除预约信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 导入数据
     */
    @Async
    @Override
    public void importData(MultipartFile file) throws IOException {
        List<AppointmentVo> appointmentVos = ExcelUtil.importExcel(file.getInputStream(), AppointmentVo.class);
        if(ObjectUtil.isNotEmpty(appointmentVos)){
            for (AppointmentVo appointmentVo : appointmentVos) {
                Product product = productMapper.selectById(appointmentVo.getProductId());
                if(ObjectUtil.isEmpty(product)){
                    continue;
                }
                Appointment appointment = BeanUtil.toBean(appointmentVo, Appointment.class);
                baseMapper.insert(appointment);
            }
        }
    }

}
