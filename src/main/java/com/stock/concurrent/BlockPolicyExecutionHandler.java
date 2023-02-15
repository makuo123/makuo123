package com.stock.concurrent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Block Policy.
 *
 * @author xuechao.sxc
 */
public class BlockPolicyExecutionHandler implements RejectedExecutionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockPolicyExecutionHandler.class);

    private String name;

    public BlockPolicyExecutionHandler(String name) {
        this.name = name;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            /**
             * 线程池需要执行的任务数
             */
            long taskCount = executor.getTaskCount();
            /**
             * 线程池在运行过程中已完成的任务数
             */
            long completedTaskCount = executor.getCompletedTaskCount();
            /**
             * 曾经创建过的最大线程数
             */
            long largestPoolSize = executor.getLargestPoolSize();
            /**
             * 线程池里的线程数量
             */
            long poolSize = executor.getPoolSize();
            /**
             * 线程池里活跃的线程数量
             */
            long activeCount = executor.getActiveCount();

            LOGGER.warn("thread pool {} is full. taskCount:{}, completedTaskCount:{}, largestPoolSize:{}, poolSize:{}, activeCount:{}",
                    name, taskCount, completedTaskCount, largestPoolSize, poolSize, activeCount);
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            //ExceptionUtils.throwException(e);
        }
    }
}
