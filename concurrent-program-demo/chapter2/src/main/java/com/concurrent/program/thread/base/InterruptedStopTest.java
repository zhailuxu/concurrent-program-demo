package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class InterruptedStopTest {

    public static void main(String[] args) throws InterruptedException {

        //1.创建线程
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("threadOne begin sleep for 2000 seconds");
                    Thread.sleep(2000000);
                    System.out.println("threadOne awaking");

                } catch (InterruptedException e) {
                    System.out.println("threadOne is interrupted while sleeping");
                    return;
                }

                System.out.println("threadOne-leaving normally");
            }
        });

        //2.启动线程
        threadOne.start();
        //确保子线程进入了休眠
        Thread.sleep(1);

        //3.打断子线程的休眠，让子线程从sleep函数返回
        threadOne.interrupt();

        //4.等待子线程执行完毕
        threadOne.join();

        System.out.println("main thread is over");

    }
}
