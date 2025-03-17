package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExpressResultVo {
	/**
	 * 物流单号
	 */
	private String number;
	/**
	 * 快递公司名称
	 */
	private String expName;

	/**
	 * 快递公司logo
	 */
	private String logo;
	/**
	 * 快递公司电话
	 */
	private String expPhone;
	/**
	 * 快递员 或 快递站(没有则为空)
	 */
	private String courier;
	/**
	 * 快递员电话 (没有则为空)
	 */
	private String courierPhone;
	/**
	 * 快递轨迹信息最新时间
	 */
	private String updateTime;
	/**
	 * 发货到收货消耗时长 (截止最新轨迹)
	 */
	private String takeTime;

	/**
	 * 物流信息
	 */
	private List<ExpressResultListVo> list;

	/** 0：快递收件(揽件)1.在途中 2.正在派件 3.已签收 4.派送失败 5.疑难件 6.退件签收  */
	private String deliverystatus;
	/**  1.是否签收 */
	private String issign;
}
