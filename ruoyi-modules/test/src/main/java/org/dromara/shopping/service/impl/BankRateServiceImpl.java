package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Bank;
import org.dromara.shopping.domain.BankRate;
import org.dromara.shopping.domain.bo.BankRateBo;
import org.dromara.shopping.domain.vo.BankRateVo;
import org.dromara.shopping.domain.vo.BankVo;
import org.dromara.shopping.mapper.BankMapper;
import org.dromara.shopping.mapper.BankRateMapper;
import org.dromara.shopping.service.IBankRateService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 银行费率Service业务层处理
 *
 * @author yzg
 * @date 2024-05-29
 */
@RequiredArgsConstructor
@Service
public class BankRateServiceImpl implements IBankRateService {

    private final BankRateMapper baseMapper;
    private final BankMapper bankMapper;

    /**
     * 查询银行费率
     */
    @Override
    public BankRateVo queryById(Long bankRateId){
        return baseMapper.selectVoById(bankRateId);
    }

    /**
     * 查询银行费率列表
     */
    @Override
    public TableDataInfo<BankRateVo> queryPageList(BankRateBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BankRate> lqw = buildQueryWrapper(bo);
        Page<BankRateVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<BankRateVo> build = TableDataInfo.build(result);
        List<BankRateVo> rows = build.getRows();
        if(CollectionUtils.isNotEmpty(rows)){
            for (BankRateVo row : rows) {
                BankVo bankVo = bankMapper.selectVoById(row.getBankId());
                if(ObjectUtils.isNotEmpty(bankVo)){
                    row.setBankName(bankVo.getBankName());
                }
            }
        }
        return build;
    }

    /**
     * 查询银行费率列表
     */
    @Override
    public List<BankRateVo> queryList(BankRateBo bo) {
        if(StringUtils.isNotEmpty(bo.getInstallmentBank())){
            LambdaQueryWrapper<Bank> bankWrapper = Wrappers.lambdaQuery();
            bankWrapper.eq(Bank::getEnglishAbbreviation,bo.getInstallmentBank());
            bankWrapper.last("LIMIT 1");
            BankVo bankVo = bankMapper.selectVoOne(bankWrapper);
            if(ObjectUtils.isNotEmpty(bankVo)){
                bo.setBankId(bankVo.getBankId());
            }
        }
        LambdaQueryWrapper<BankRate> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BankRate> buildQueryWrapper(BankRateBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BankRate> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getBankId() != null, BankRate::getBankId, bo.getBankId());
        lqw.eq(bo.getRate() != null, BankRate::getRate, bo.getRate());
        lqw.eq(StringUtils.isNotBlank(bo.getStagesNumber()), BankRate::getStagesNumber, bo.getStagesNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), BankRate::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增银行费率
     */
    @Override
    public Boolean insertByBo(BankRateBo bo) {
        BankRate add = BeanUtil.toBean(bo, BankRate.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBankRateId(add.getBankRateId());
        }
        return flag;
    }

    /**
     * 修改银行费率
     */
    @Override
    public Boolean updateByBo(BankRateBo bo) {
        BankRate update = BeanUtil.toBean(bo, BankRate.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BankRate entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除银行费率
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
