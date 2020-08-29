package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        //1.创建线程1，模拟执行任务
        Thread threadOne = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("child threadOne over!");

            }
        });

        //2.创建线程2，模拟执行任务
        Thread threadTwo = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("child threadTwo over!");


            }
        });


        //3.两个线程执行
        threadOne.start();
        threadTwo.start();

        System.out.println("wait all child thread over!");

        //3.等待子线程执行完毕，返回
        threadOne.join();
        threadTwo.join();

        System.out.println("all child thread over!");

    }

}
