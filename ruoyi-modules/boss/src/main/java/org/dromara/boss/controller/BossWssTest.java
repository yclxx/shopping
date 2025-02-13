package org.dromara.boss.controller;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiexi
 * @description
 * @date 2024/11/24 14:52
 */
@Slf4j
public class BossWssTest {

    public static void main(String[] args) throws Exception {

        String wssUrl = "wss://ws.zhipin.com/chatws";

        URI uri = URI.create(wssUrl);
        // 设置请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("sec-websocket-extensions", "permessage-deflate; client_max_window_bits");
        headers.put("sec-websocket-key", "wIm27pe2++JSTtClxEwQRw==");
        headers.put("sec-websocket-protocol", "DoIsK_d5fdO0mA7sPeaHXs3MAsAy0DQE_D4g0qa5fhcgUXM-bzW4Z0pGjZYHPVCHUNgtzHj2gaml1KerkTb8iNA~~");
        headers.put("sec-websocket-version", "13");
        headers.put("upgrade", "websocket");

        WebSocketClient webSocketClient = new WebSocketClient(uri, headers) {
            @Override
            public void onOpen(ServerHandshake handshake) {
                log.info("新连接已打卡，{}", handshake);
            }

            @Override
            public void onMessage(String message) {
                log.info("接受到消息：{}", message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("连接已关闭");
                log.info("连接已关闭");
            }

            @Override
            public void onError(Exception ex) {
                log.error("连接异常：", ex);
            }
        };

        // 启动客户端
        webSocketClient.connect();

    }
}
