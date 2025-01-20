package org.dromara.shopping.domain.bo;

import lombok.Data;

@Data
public class AppWxPayCallbackResource {
	/** 加密算法类型 */
	private String algorithm;
	/** 数据密文 */
	private String ciphertext;
	/** 附加数据 */
	private String associated_data;
	/** 原始类型 */
	private String original_type;
	/** 随机串 */
	private String nonce;
}
