package org.dromara.shopping.domain.vo;

import lombok.Data;

/**
 * 物流接口返回信息
 * {"status":0,"msg":"ok","result":{"number":"557039567535302","type":"htky","typename":"百世快递","logo":"https:\/\/api.jisuapi.com\/express\/static\/images\/logo\/80\/htky.png","list":[{"time":"2021-01-08 11:56:24","status":"【清远市区一部005】，你的快递已签收，有问题请致电19876318629。求好坪 已签收"},{"time":"2021-01-08 09:58:45","status":"【沈文桦\/19876318629】正在派件，【清远市区一部005\/网点电话0763-3149089  13535990766】"},{"time":"2021-01-08 09:57:45","status":"到【清远市区一部005】"},{"time":"2021-01-08 09:09:20","status":"【清远市区一部】，正发往【清远市区一部005】"},{"time":"2021-01-08 02:17:40","status":"【佛山转运中心】，正发往【清远市区一部】"},{"time":"2021-01-08 01:22:08","status":"到【佛山转运中心】"},{"time":"2021-01-06 18:39:48","status":"【合肥转运中心】，正发往【佛山转运中心】"},{"time":"2021-01-06 18:36:54","status":"到【合肥转运中心】"},{"time":"2021-01-06 10:26:09","status":"【包河新区】，【曾紫萱\/18130098818】已揽收"}],"deliverystatus":3,"issign":1}}
 */
@Data
public class ExpressVo {
	/**
	 * 查询状态，0-成功
	 */
	private String status;
	/**
	 * 描述
	 */
	private String msg;

	/**
	 * 结果
	 */
	private ExpressResultVo result;
}
