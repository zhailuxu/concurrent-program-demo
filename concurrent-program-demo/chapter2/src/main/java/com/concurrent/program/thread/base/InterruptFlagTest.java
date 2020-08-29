package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class InterruptFlagTest {
    public static void main(String[] args) throws InterruptedException {

        //1.创建线程
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //2.启动线程
        threadOne.start();

        //3.设置中断标志
        threadOne.interrupt();

        //4.获取中断标志
        System.out.println("isInterrupted:" + threadOne.isInterrupted());
        //获取中断标志并重置
        System.out.println("isInterrupted:" + threadOne.interrupted());
        //获取中断标志并重置
        System.out.println("isInterrupted:" + Thread.interrupted());
        //获取中断标志
        System.out.println("isInterrupted:" + threadOne.isInterrupted());

        //5.等待执行完毕
        threadOne.join();
        System.out.println("main thread is over");

    }
}
