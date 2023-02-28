package com.stock.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description 多线程执行结果汇总工具
 * @Author makuo
 * @Date 2023/2/27 17:43
 **/

@Slf4j
public class MultiFutureThreadUtil<T> {

    private static final int corePoolSize = Runtime.getRuntime().availableProcessors();
    private static final int maxPoolSize = Runtime.getRuntime().availableProcessors() * 4 + 1;

    // 总线程数量
    private int threadSize;
    // 单次执行多少次线程
    private int singleSize;

    private List<Callable<T>> callableList = Lists.newArrayList();

    public MultiFutureThreadUtil() {
        super();
        this.threadSize = maxPoolSize;
        this.singleSize = corePoolSize;
    }

    /**
     * 多线程执行结果汇总工具 构造方法
     *
     * @param threadSize 总线程数量
     * @param singleSize 单次执行多少次线程
     */
    public MultiFutureThreadUtil(int threadSize, int singleSize) {
        super();
        this.threadSize = threadSize;
        this.singleSize = singleSize < 1 ? threadSize : singleSize;
    }

    /**
     * 设计要执行的程序段
     *
     * @param @param  callable
     * @param @return 参数
     * @return MultiFutureThread<T> 返回类型
     * @throws
     * @Title: setCallable
     */
    public MultiFutureThreadUtil<T> setCallable(Callable<T> callable) {
        if (callable != null) {
            callableList.add(callable);
        }
        return this;
    }

    /**
     * 运行线程
     *
     * @param @return 参数
     * @return List<T> 返回类型
     * @throws
     * @Title: exec
     */
    public List<T> exec() {

        // 如果开启的线程数量为1时，刚不开启线程
        List<T> list = Lists.newArrayList();
        if (singleSize <= 1) {
            callableList.forEach(e -> {
                try {
                    T dataList = e.call();
                    list.add(dataList);
                } catch (Exception e1) {
                }
            });
            return list;
        }

//        ExecutorService executor = Executors.newFixedThreadPool(singleSize);
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<T>> pointTaskFutureList = new ArrayList<>(singleSize);
        int total = threadSize; // 总计算结果
        int done = 0; //完成任务的数量
        try {
            int count = (total / singleSize) + 1;
            for (int j = 0; j < count; j++) {
                int index = j * singleSize;
                int endIndex = index + singleSize;
                int runSize = callableList.size() > endIndex ? endIndex : callableList.size();
                for (int i = index; i < runSize; i++) {
                    // 提交任务，任务的执行由线程池去调用执行并管理。
                    // 这里获取结果任务的Future，并放到list中，供所有任务提交完后，通过每个任务的Future判断执行状态和结果。
                    Future<T> future = executor.submit(callableList.get(i));
                    pointTaskFutureList.add(future);
                }

                while (!pointTaskFutureList.isEmpty()) {
                    Iterator<Future<T>> iter = pointTaskFutureList.iterator();
                    while (iter.hasNext()) {
                        Future<T> next = iter.next();
                        if (next.isDone()) {
                            done++;
                            T dataList = next.get();
                            list.add(dataList);
                            iter.remove();
                        }
                    }
                    log.info("总任务量：{},已完成任务量：{}", total, done);
                    // 停留一会，避免一直循环。
                    Thread.sleep(10);
                }
            }
            log.info("总任务量：{},完成任务量：{}", total, done);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                log.error("线程超时，中断异常{}", e);
            }
        }

        return list;
    }

    /**
     * 测试
     *
     * @param @param  args
     * @param @throws Exception 参数
     * @return void 返回类型
     * @throws
     * @Title: main
     */
    public static void main(String[] args) throws Exception {
        MultiFutureThreadUtil<Integer> thread = new MultiFutureThreadUtil<Integer>(10, 10);
        for (int i = 0; i < 10; i++) {
            thread.setCallable(() -> {
                for (int j = 0; j < 10000; j++) {
                    System.out.println(new Random().nextInt() + "----------");
                }
                return new Random().nextInt();
            });
        }
        List<Integer> list = thread.exec();
        list.forEach(System.out::println);
    }

}
