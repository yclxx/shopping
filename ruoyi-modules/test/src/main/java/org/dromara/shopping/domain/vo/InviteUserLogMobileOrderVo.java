package org.dromara.shopping.domain.vo;

import com.ruoyi.common.core.annotation.Sensitive;
import com.ruoyi.common.core.enums.SensitiveStrategy;
import lombok.Data;

/**
 * @author 25487
 */
@Data
public class InviteUserLogMobileOrderVo extends InviteUserLogVo {

    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String inviteUserMobile;

    private OrderVo orderVo;
}
