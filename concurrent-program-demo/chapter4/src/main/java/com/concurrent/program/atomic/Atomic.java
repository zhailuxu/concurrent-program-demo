package com.concurrent.program.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Hello world!
 */
public class Atomic {
    //(10)创建Long型原子计数器
    private static AtomicLong atomicLong = new AtomicLong();
    //(11)创建数据源
    private static Integer[] arrayOne = new Integer[] {0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[] {10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        //（12）线程one统计数组arrayOne中0的个数
        Thread threadOne = new Thread(new Runnable() {

            @Override
            public void run() {

                int size = arrayOne.length;
                for (int i = 0; i < size; ++i) {
                    if (arrayOne[i].intValue() == 0) {

                        atomicLong.incrementAndGet();
                    }
                }

            }
        });
        //（13）线程two统计数组arrayTwo中0的个数
        Thread threadTwo = new Thread(new Runnable() {

            @Override
            public void run() {

                int size = arrayTwo.length;
                for (int i = 0; i < size; ++i) {
                    if (arrayTwo[i].intValue() == 0) {

                        atomicLong.incrementAndGet();
                    }
                }

            }
        });

        //(14)启动子线程
        threadOne.start();
        threadTwo.start();

        //(15)等待线程执行完毕
        threadOne.join();
        threadTwo.join();

        System.out.println("count 0:" + atomicLong.get());

    }
}
