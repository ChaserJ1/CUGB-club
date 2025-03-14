package edu.cugb.wx.handler;

/**
 * @Author pengjia
 * @Data 2024/12/6 18:43
 * @Description:
 */
public enum WxChatMsgTypeEnum {

    SUBSCRIBE("event.subscribe", "用户关注事件"),
    TEXT_MSG("text", "接受用户文本消息");

    private String msgType;

    private String desc;

    WxChatMsgTypeEnum(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }

    public static WxChatMsgTypeEnum getByMsgType(String msgType) {
        for (WxChatMsgTypeEnum wxChatMsgTypeEnum : WxChatMsgTypeEnum.values()) {
            if (wxChatMsgTypeEnum.msgType.equals(msgType)) {
                return wxChatMsgTypeEnum;
            }
        }
        return null;
    }
}
