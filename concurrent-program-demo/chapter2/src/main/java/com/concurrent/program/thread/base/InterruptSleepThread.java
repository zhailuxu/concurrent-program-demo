package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class InterruptSleepThread {
    public static void main(String[] args) throws InterruptedException {

        //1.创建子线程,并休眠10s
        Thread thread = new Thread(new Runnable() {
            public void run() {

                try {
                    System.out.println("child thread is in sleep");

                    Thread.sleep(10000);
                    System.out.println("child thread is in awaked");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //2.启动线程
        thread.start();

        //3.主线程休眠2s
        Thread.sleep(2000);

        //4.主线程中断子线程
        thread.interrupt();
    }

}
