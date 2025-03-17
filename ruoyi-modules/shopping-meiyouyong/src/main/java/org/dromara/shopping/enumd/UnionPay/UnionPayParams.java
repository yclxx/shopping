package org.dromara.shopping.enumd.UnionPay;

/**
 * 银联通用参数
 */
public enum UnionPayParams {
    CodeSuccess("0000000000");// 接口响应成功

    String str;

    private UnionPayParams(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
