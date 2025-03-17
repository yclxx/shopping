package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.BankBo;
import org.dromara.shopping.domain.vo.BankVo;

import java.util.Collection;
import java.util.List;

/**
 * 银行Service接口
 *
 * @author yzg
 * @date 2024-03-26
 */
public interface IBankService {

    /**
     * 查询银行
     */
    BankVo queryById(Long bankId);

    /**
     * 查询银行列表
     */
    TableDataInfo<BankVo> queryPageList(BankBo bo, PageQuery pageQuery);

    /**
     * 查询银行列表
     */
    List<BankVo> queryList(BankBo bo);

    /**
     * 查询银行列表
     */
    List<BankVo> selectInstallmentList();

    /**
     * 修改银行
     */
    Boolean insertByBo(BankBo bo);

    /**
     * 修改银行
     */
    Boolean updateByBo(BankBo bo);

    /**
     * 校验并批量删除银行信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
