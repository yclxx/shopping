package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.BankRateBo;
import org.dromara.shopping.domain.vo.BankRateVo;

import java.util.Collection;
import java.util.List;

/**
 * 银行费率Service接口
 *
 * @author yzg
 * @date 2024-05-29
 */
public interface IBankRateService {

    /**
     * 查询银行费率
     */
    BankRateVo queryById(Long bankRateId);

    /**
     * 查询银行费率列表
     */
    TableDataInfo<BankRateVo> queryPageList(BankRateBo bo, PageQuery pageQuery);

    /**
     * 查询银行费率列表
     */
    List<BankRateVo> queryList(BankRateBo bo);

    /**
     * 修改银行费率
     */
    Boolean insertByBo(BankRateBo bo);

    /**
     * 修改银行费率
     */
    Boolean updateByBo(BankRateBo bo);

    /**
     * 校验并批量删除银行费率信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
