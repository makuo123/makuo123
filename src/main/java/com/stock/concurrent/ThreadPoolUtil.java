package com.stock.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author lingyi
 */
public class ThreadPoolUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolUtil.class);

    private ThreadPoolUtil() {
    }


    public static ListeningExecutorService createListeningExecutor(String name,
                                                                   int corePoolSize,
                                                                   int maxPoolSize,
                                                                   BlockingQueue<Runnable> workQueue) {
        ExecutorService executorService = createExecutor(name, corePoolSize, maxPoolSize, workQueue);
        ListeningExecutorService listeningExecutorService = new ListeningDecorator(executorService);
        return listeningExecutorService;
    }


    public static ExecutorService createExecutor(String name,
                                                 int corePoolSize,
                                                 int maxPoolSize,
                                                 BlockingQueue<Runnable> workQueue) {
        ThreadFactory threadFactory = createThreadFactory(name);

        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                10L,
                TimeUnit.SECONDS,
                workQueue,
                threadFactory,
                new BlockPolicyExecutionHandler(name)
        );
    }

    public static ScheduledExecutorService scheduledExecutorService() {
        return executor;
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
            2,
            createThreadFactory("ScheduledExecutor pool"),
            new BlockPolicyExecutionHandler("GEI-ScheduledExecutor pool")
    );

    private static ThreadFactory createThreadFactory(String name) {
        return new NamedThreadFactory("AGEI-" + name, false, (t, e) -> LOGGER.error(t.getName() + " e", e));
    }

}
