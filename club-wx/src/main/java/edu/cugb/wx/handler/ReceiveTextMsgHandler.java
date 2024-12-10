package edu.cugb.wx.handler;

import edu.cugb.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author pengjia
 * @Data 2024/12/6 19:57
 * @Description:
 */
@Component
@Slf4j
public class ReceiveTextMsgHandler implements WxChatMsgHandler {

    private static final String KEY_WORD = "验证码";

    private static final String LOGIN_PREFIX = "loginCode";

    @Resource
    private RedisUtil redisUtil;

    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String delMsg(Map<String, String> msgMap) {
        log.info("接收用户文本消息");
        String content = msgMap.get("Content");
        if (!KEY_WORD.equals(content)) {
            return "";
        }

        String fromUserName = msgMap.get("FromUserName");//openId
        String toUserName = msgMap.get("ToUserName");

        Random random = new Random();
        int num = random.nextInt(1000);
        String numKey = redisUtil.buildKey(LOGIN_PREFIX, String.valueOf(num));
        redisUtil.setNx(numKey, fromUserName, 5L, TimeUnit.MINUTES);
        String numContent = "您当前的验证码是：" + num + "！ 5分钟内有效";
        String replyContent = "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + numContent + "]]></Content>\n" +
                "</xml>";
        log.info("replyContent:{}", replyContent);
        return replyContent;
    }
}
