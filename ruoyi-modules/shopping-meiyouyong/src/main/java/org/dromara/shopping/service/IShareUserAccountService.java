package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ShareUserAccountBo;
import org.dromara.shopping.domain.vo.ShareUserAccountVo;

import java.util.Collection;
import java.util.List;

/**
 * 分销用户账户Service接口
 *
 * @author yzg
 * @date 2023-11-09
 */
public interface IShareUserAccountService {

    /**
     * 查询分销用户账户
     */
    ShareUserAccountVo queryById(Long userId);

    /**
     * 查询分销用户账户列表
     */
    TableDataInfo<ShareUserAccountVo> queryPageList(ShareUserAccountBo bo, PageQuery pageQuery);

    /**
     * 查询分销用户账户列表
     */
    List<ShareUserAccountVo> queryList(ShareUserAccountBo bo);

    /**
     * 修改分销用户账户
     */
    Boolean insertByBo(ShareUserAccountBo bo);

    /**
     * 修改分销用户账户
     */
    Boolean updateByBo(ShareUserAccountBo bo);

    /**
     * 校验并批量删除分销用户账户信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
