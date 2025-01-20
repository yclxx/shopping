package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author 25487
 */
@Data
public class BackendTokenEntity {
	private String resp;
	private String msg;

	private Map<String,String> params;
}
