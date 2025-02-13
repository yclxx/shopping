package org.dromara.boss.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.boss.domain.bo.InitZpUtilsBo;
import org.dromara.boss.domain.vo.ZpRandKeyResultVo;
import org.dromara.boss.domain.vo.ZpResultVo;
import org.dromara.boss.utils.ZpUtils;
import org.dromara.common.core.domain.R;
import org.dromara.common.json.utils.JsonUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 扫码登录控制器
 *
 * @author xiexi
 * @description
 * @date 2024/11/10 17:57
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/boss")
public class LoginController {

    /**
     * 初始化参数
     */
    @PostMapping("/init")
    public R<Void> init(@RequestBody InitZpUtilsBo bo) {
        ZpUtils.getInstance(bo.getHeaders());
        return R.ok();
    }

    /**
     * 查询活动管理列表
     */
    @PostMapping("/randQrCode")
    public R<ZpRandKeyResultVo> randQrCode() {
        String randKeyUrl = "https://www.zhipin.com/wapi/zppassport/captcha/randkey";

        HttpRequest request = HttpUtil.createRequest(Method.POST, randKeyUrl);
        HttpResponse response = request.execute();

        String result = response.body();
        // {"code":0,"message":"Success","zpData":{"qrId":"bosszp-5feef88c-81c0-4682-957c-aee9eb785da7","randKey":"P5dGDVa2r5SL13B8reJvCvS9jUFni4O2","secretKey":"jevtQs8T","shortRandKey":"bosszp-jTu2HzPHH2UKbbqi"}}
        log.info("请求url：{}，返回结果：{}", randKeyUrl, result);

        ZpResultVo zpResult = JsonUtils.parseObject(result, ZpResultVo.class);
        if (null == zpResult) {
            return R.fail("生成登录二维码失败，返回结果为空");
        }
        ZpRandKeyResultVo randKeyResultVo = zpResult.getZpData(ZpRandKeyResultVo.class);

        // 获取二维码
        String getQrCodeUrl = "https://www.zhipin.com/wapi/zpweixin/qrcode/getqrcode?content=" + randKeyResultVo.getQrId() + "&w=200&h=200";

        HttpResponse qrCodeResponse = HttpUtil.createGet(getQrCodeUrl).execute();

        byte[] qrCodeBytes = qrCodeResponse.bodyBytes();
        // 转base64
        String imgBase64 = Base64.encode(qrCodeBytes);

        randKeyResultVo.setQrCodeImg(imgBase64);

        return R.ok(randKeyResultVo);
    }

    /**
     * 查询活动管理列表
     */
    @GetMapping("/scan")
    public R<Void> scan(@RequestParam("uuid") String uuid) {
        String randKeyUrl = "https://www.zhipin.com/wapi/zppassport/qrcode/scan?uuid=" + uuid;

        String scanResult = HttpUtil.get(randKeyUrl);

        // {"scaned":true,"allweb":true,"newScaned":true,"scanedV2":true}
        log.info("请求url：{}，返回结果：{}", randKeyUrl, scanResult);

        // https://www.zhipin.com/wapi/zppassport/qrcode/scanLogin?qrId=bosszp-9457f65e-1e5f-4b96-9d09-9d34d5f90b92
        String scanLoginUrl = "https://www.zhipin.com/wapi/zppassport/qrcode/scanLogin?qrId=" + uuid;

        HttpRequest request = HttpUtil.createRequest(Method.GET, scanLoginUrl);
        HttpResponse response = request.execute();

        String result = response.body();
        // {"scaned":true,"newScaned":true,"login":true}
        log.info("请求url：{}，返回结果：{}", scanLoginUrl, result);

        return R.ok("操作成功");
    }
}
