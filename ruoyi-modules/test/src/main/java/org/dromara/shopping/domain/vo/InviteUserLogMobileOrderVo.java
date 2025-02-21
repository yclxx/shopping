package org.dromara.shopping.domain.vo;

import lombok.Data;
import org.dromara.common.sensitive.annotation.Sensitive;
import org.dromara.common.sensitive.core.SensitiveStrategy;

/**
 * @author 25487
 */
@Data
public class InviteUserLogMobileOrderVo extends InviteUserLogVo {

    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String inviteUserMobile;

    private OrderVo orderVo;
}
