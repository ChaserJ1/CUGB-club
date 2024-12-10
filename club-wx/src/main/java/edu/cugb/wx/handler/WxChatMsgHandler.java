package edu.cugb.wx.handler;

import java.util.Map;

/**
 * @Author pengjia
 * @Data 2024/12/6 19:55
 * @Description:
 */
public interface WxChatMsgHandler {

    /**
     * 获得消息类型
     */
    WxChatMsgTypeEnum getMsgType();

    /**
     * 处理消息
     */
    String delMsg(Map<String, String> msgMap);
}
