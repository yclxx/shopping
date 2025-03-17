package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Bank;
import org.dromara.shopping.domain.bo.BankBo;
import org.dromara.shopping.domain.vo.BankVo;
import org.dromara.shopping.mapper.BankMapper;
import org.dromara.shopping.service.IBankService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 银行Service业务层处理
 *
 * @author yzg
 * @date 2024-03-26
 */
@RequiredArgsConstructor
@Service
public class BankServiceImpl implements IBankService {

    private final BankMapper baseMapper;

    /**
     * 查询银行
     */
    @Override
    public BankVo queryById(Long bankId) {
        return baseMapper.selectVoById(bankId);
    }

    /**
     * 查询银行列表
     */
    @Override
    public TableDataInfo<BankVo> queryPageList(BankBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Bank> lqw = buildQueryWrapper(bo);
        Page<BankVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询银行列表
     */
    @Override
    public List<BankVo> queryList(BankBo bo) {
        LambdaQueryWrapper<Bank> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询银行列表
     */
    @Override
    public List<BankVo> selectInstallmentList() {
        LambdaQueryWrapper<Bank> lqw = Wrappers.lambdaQuery();
        lqw.eq(Bank::getStatus, "0");
        lqw.isNotNull(Bank::getEnglishAbbreviation);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Bank> buildQueryWrapper(BankBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Bank> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBankName()), Bank::getBankName, bo.getBankName());
        lqw.like(StringUtils.isNotBlank(bo.getBankShortName()), Bank::getBankShortName, bo.getBankShortName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Bank::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增银行
     */
    @Override
    public Boolean insertByBo(BankBo bo) {
        Bank add = BeanUtil.toBean(bo, Bank.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBankId(add.getBankId());
        }
        return flag;
    }

    /**
     * 修改银行
     */
    @Override
    public Boolean updateByBo(BankBo bo) {
        Bank update = BeanUtil.toBean(bo, Bank.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Bank entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除银行
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
