package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {

        //1.创建线程
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                //如果当前线程被中断则退出循环
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + " hello");
                }
            }
        });

        //2.启动子线程
        thread.start();
        //主线程休眠1s，以便中断前让子线程输出点东西
        Thread.sleep(1);

        //3.中断子线程
        System.out.println("main thread interrupt thread");
        thread.interrupt();

        //4.等待子线程执行完毕
        thread.join();
        System.out.println("main is over");

    }
}
