package edu.cugb.auth.application.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author pengjia
 * @Date 2024/12/26 16:34
 * @Description:  登录上下文
 */
public class LoginContextHolder {

   private static final Logger logger = LoggerFactory.getLogger(LoginContextHolder.class);


   private static final InheritableThreadLocal<Map<String,Object>> THREAD_LOCAL =
           new InheritableThreadLocal<>();

   /**
    * 向该线程的ThreadLocalMap中放值
    * @param key 键
    * @param val 值
    */
   public static void set(String key, Object val) {
      Map<String, Object> map = getThreadLocalMap();
      map.put(key,val);
   }

   /**
    * 根据传入的 key 拿到对应的value
    * @param key 传入的键
    * @return 返回对应的value
    */
   public static Object get(String key) {
      Map<String, Object> map = getThreadLocalMap();
       return map.get(key);
   }

   /**
    *移除当前线程的所有本地变量
    */
   public static void remove() {
      THREAD_LOCAL.remove();
   }

   /**
    * 以loginId作为 key 从当前线程的map中拿到对应 value
    * @return value
    */
   public static String getLoginId(){
      return (String) getThreadLocalMap().get("loginId");
   }


   /**
    * 拿到与当前线程相关的map，存储了线程本地变量
    * @return map
    */
   public static Map<String, Object> getThreadLocalMap(){
      Map<String, Object> map = THREAD_LOCAL.get();
      if (map== null){
         logger.error("ThreadLocal map is null");
         map = new ConcurrentHashMap<>();
         THREAD_LOCAL.set(map);
      }
      return map;
   }
}
