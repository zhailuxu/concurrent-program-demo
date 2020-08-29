package com.concurrent.program.sync.tool;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class CycleBarrierTest2 {
    // 创建一个CyclicBarrier实例
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 加入线程A到线程池
        executorService.submit(new Runnable() {
            public void run() {
                try {

                    System.out.println(Thread.currentThread() +  " step1");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() +  " step2");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() +  " step3");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 加入线程B到线程池
        executorService.submit(new Runnable() {
            public void run() {
                try {
                    System.out.println(Thread.currentThread() +  " step1");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() +  " step2");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() +  " step3");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //关闭线程池
        executorService.shutdown();
    }
}
