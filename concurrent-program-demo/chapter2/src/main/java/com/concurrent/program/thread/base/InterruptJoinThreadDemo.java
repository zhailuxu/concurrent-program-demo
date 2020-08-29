package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class InterruptJoinThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        //1.线程one,模拟任务
        Thread threadOne = new Thread(new Runnable() {

            @Override
            public void run() {

                System.out.println("threadOne begin run!");
                for (;;) {
                }

            }
        });

        //2.获取主线程
        final Thread mainThread = Thread.currentThread();

        //3.线程two 模拟中断主线程
        Thread threadTwo = new Thread(new Runnable() {

            @Override
            public void run() {
                //休眠1s
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //中断主线程
                mainThread.interrupt();

            }
        });

        //4. 启动子线程
        threadOne.start();
        //延迟1s启动线程
        threadTwo.start();

        //5. 等待线程one执行完毕
        try{
            threadOne.join();
        }catch(InterruptedException e){
            System.out.println("main thread:" + e);
        }
    }
}
