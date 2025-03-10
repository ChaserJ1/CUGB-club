package edu.cugb.subject.application.util;

import edu.cugb.subject.application.context.LoginContextHolder;

import java.util.Map;


/**
 * @Author pengjia
 * @Description:
 */
public class LoginUtil {

    /**
     * 以loginId作为 key 从当前线程的map中拿到对应 value
     * @return value
     */
    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }

    /**
     *移除当前线程的所有本地变量
     */
    public static void remove() {
        LoginContextHolder.remove();
    }

    /**
     * 根据传入的 key 拿到对应的value
     * @param key 传入的键
     * @return 返回对应的value
     */
    public static Object get(String key) {
        Map<String, Object> map = LoginContextHolder.getThreadLocalMap();
        return map.get(key);
    }

    /**
     * 向该线程的ThreadLocalMap中放值
     * @param key 键
     * @param val 值
     */
    public static void set(String key, Object val) {
        Map<String, Object> map = LoginContextHolder.getThreadLocalMap();
        map.put(key,val);
    }
}
