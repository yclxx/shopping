package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 银联分销商品详情视图对象
 *
 * @author yzg
 * @date 2023-08-07
 */
@Data
@ExcelIgnoreUnannotated
public class ProductUnionPayVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long productId;

    /**
     * 外部产品ID
     */
    @ExcelProperty(value = "外部产品ID")
    private String externalProductId;

    /**
     * 产品标题
     */
    @ExcelProperty(value = "产品标题")
    private String title;

    /**
     * 销售模式 1-代销 2B;2-代销;3-直销
     */
    @ExcelProperty(value = "销售模式 1-代销 2B;2-代销;3-直销")
    private String coopMd;

    /**
     * 核销方式 0-直接使用，1-非联登页面使用，2-联登页面使用，3-支付时使用，4-内容方客户端
     */
    @ExcelProperty(value = "核销方式 0-直接使用，1-非联登页面使用，2-联登页面使用，3-支付时使用，4-内容方客户端")
    private String cancelMethod;

    /**
     * 是否券包商品 Y-是券包商品 N-非券包商品
     */
    @ExcelProperty(value = "是否券包商品 Y-是券包商品 N-非券包商品")
    private String flgSubProd;

    /**
     * 核销方 0-商户;1-银联
     */
    @ExcelProperty(value = "核销方 0-商户;1-银联")
    private String flgUpBond;

    /**
     * 卡券类型 0-仅券码;1-券码+券密;2-短链;3-直充
     */
    @ExcelProperty(value = "卡券类型 0-仅券码;1-券码+券密;2-短链;3-直充")
    private String prodTp;

    /**
     * 商品图链接
     */
    @ExcelProperty(value = "商品图链接")
    private String dtlPgUrl;

    /**
     * 发券账号类型 0-手机号;1-qq 号;2-微信号;3-其他类型账号4-银行卡号5-渠道用户 ID6-云闪付用户 ID 支持返回多个值，用竖线|分隔
     */
    @ExcelProperty(value = "发券账号类型 0-手机号;1-qq 号;2-微信号;3-其他类型账号4-银行卡号5-渠道用户 ID6-云闪付用户 ID 支持返回多个值，用竖线|分隔")
    private String prodAstidTp;

    /**
     * 是否支持随时退券 0-不支持;1-支持
     */
    @ExcelProperty(value = "是否支持随时退券 0-不支持;1-支持")
    private String flgRfd;

    /**
     * 是否支持过期自动退券 0-不支持;1-支持
     */
    @ExcelProperty(value = "是否支持过期自动退券 0-不支持;1-支持")
    private String flgExprRfd;

    /**
     * 商品详情类型 0-文字 1-图片
     */
    @ExcelProperty(value = "商品详情类型 0-文字 1-图片")
    private String detailTp;

    /**
     * 购买须知
     */
    @ExcelProperty(value = "购买须知")
    private String cusIstr;

    /**
     * 客服电话
     */
    @ExcelProperty(value = "客服电话")
    private String cusSvcHtline;

    /**
     * 商户资质信息
     */
    @ExcelProperty(value = "商户资质信息")
    private String brandQual;

    /**
     * 适用门店类型为 1-页面;2-列表
     */
    @ExcelProperty(value = "适用门店类型为 1-页面;2-列表")
    private String storeLstTp;

    /**
     * 适用门店链接 适用门店类型为“页面”时必填
     */
    @ExcelProperty(value = "适用门店链接 适用门店类型为“页面”时必填")
    private String storeLstUrl;

    /**
     * 核销页面链接
     */
    @ExcelProperty(value = "核销页面链接")
    private String exchUrl;

    /**
     * 核销页面上送业务要素
     */
    @ExcelProperty(value = "核销页面上送业务要素")
    private String exchAstidTp;

    /**
     * 副标题
     */
    @ExcelProperty(value = "副标题")
    private String prodSubTitle;

    /**
     * 商品亮点，Base64 传输
     */
    @ExcelProperty(value = "商品亮点，Base64 传输")
    private String prodAdv;


}
