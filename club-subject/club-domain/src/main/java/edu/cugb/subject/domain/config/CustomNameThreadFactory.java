package edu.cugb.subject.domain.config;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author pengjia
 * @Data 2024/12/25 15:58
 * @Description:
 */
public class CustomNameThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    CustomNameThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        if (StringUtils.isBlank(name)) {
            name = "pool";
        }
        namePrefix = name + "-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    @Override
    public Thread newThread(@NotNull Runnable r) {
        // 创建一个新的线程实例
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(), //线程名
                0); // stackSize 参数设置为 0 表示使用默认栈大小
        // 如果线程是守护线程，则将其设置为非守护线程
        if (t.isDaemon())
            t.setDaemon(false);
        // 如果线程是守护线程，则将其设置为非守护线程
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
