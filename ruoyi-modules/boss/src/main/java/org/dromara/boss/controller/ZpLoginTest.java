package org.dromara.boss.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.dromara.boss.domain.vo.ZpRandKeyResultVo;
import org.dromara.boss.domain.vo.ZpResultVo;
import org.dromara.common.json.utils.JsonUtils;

/**
 * @author xiexi
 * @description
 * @date 2024/11/10 16:10
 */
@Slf4j
public class ZpLoginTest {
    public static void main(String[] args) {
        String randKeyUrl = "https://www.zhipin.com/wapi/zppassport/captcha/randkey";

        HttpRequest request = HttpUtil.createRequest(Method.POST, randKeyUrl);
        HttpResponse response = request.execute();
        // 获取cookie
        String cookieStr = response.getCookieStr();
        log.info("cookieStr：{}", cookieStr);

        String result = response.body();
        // {"code":0,"message":"Success","zpData":{"qrId":"bosszp-5feef88c-81c0-4682-957c-aee9eb785da7","randKey":"P5dGDVa2r5SL13B8reJvCvS9jUFni4O2","secretKey":"jevtQs8T","shortRandKey":"bosszp-jTu2HzPHH2UKbbqi"}}
        log.info("请求url：{}，返回结果：{}", randKeyUrl, result);

        ZpResultVo zpResult = JsonUtils.parseObject(result, ZpResultVo.class);
        if (null == zpResult) {
            return;
        }
        ZpRandKeyResultVo randKeyResultVo = zpResult.getZpData(ZpRandKeyResultVo.class);

        // 获取二维码
        String getQrCodeUrl = "https://www.zhipin.com/wapi/zpweixin/qrcode/getqrcode?content=" + randKeyResultVo.getQrId() + "&w=200&h=200";

        HttpResponse qrCodeResponse = HttpUtil.createGet(getQrCodeUrl).execute();

        byte[] qrCodeBytes = qrCodeResponse.bodyBytes();
        log.info("二维码图片大小：{}", qrCodeBytes.length);
    }
}
