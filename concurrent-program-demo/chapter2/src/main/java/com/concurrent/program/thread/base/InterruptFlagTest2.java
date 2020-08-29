package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class InterruptFlagTest2 {
    public static void main(String[] args) throws InterruptedException {

        //1.创建线程
        Thread threadOne = new Thread(new Runnable() {
            public void run() {

                //中断标志为true时候会退出循环，并且清除中断标志
                while (!Thread.currentThread().interrupted()) {

                }

                System.out.println("threadOne isInterrupted:" + Thread.currentThread().isInterrupted());
            }
        });

        //2. 启动线程
        threadOne.start();

        //3. 设置中断标志
        threadOne.interrupt();

        //3.等待线程one退出
        threadOne.join();
        System.out.println("main thread is over");

    }
}
