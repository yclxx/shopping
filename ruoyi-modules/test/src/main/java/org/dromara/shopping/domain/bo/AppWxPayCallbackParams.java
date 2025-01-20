package org.dromara.shopping.domain.bo;

import lombok.Data;

@Data
public class AppWxPayCallbackParams {
	/** 通知ID */
	private String id;
	/** 通知创建时间 */
	private String create_time;
	/** 通知类型 */
	private String event_type;
	/** 通知数据类型 */
	private String resource_type;
	/** 通知数据 */
	private AppWxPayCallbackResource resource;
	/** 回调摘要 */
	private String summary;
}
