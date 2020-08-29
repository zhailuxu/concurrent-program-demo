package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class DeadLockBrokenTest {

    //1. 创建共享资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {

        //2. 创建线程A
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                //2.1获取资源A的锁
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get ResourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //2.1获取资源B的锁
                    System.out.println(Thread.currentThread() + "waiting get ResourceB");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + "get ResourceB");
                    }
                }
            }
        });

        //3. 创建线程B
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                //3.1获取资源A的锁
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get ResourceA");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //3.2获取资源B的锁
                    System.out.println(Thread.currentThread() + "waiting get ResourceB");
                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread() + "get ResourceB");
                    }
                }
                ;
            }
        });

        //3. 启动线程
        threadA.start();
        threadB.start();
    }
}
