package com.concurrent.program.in.action;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class ThreadNoName {
    public static void main(String[] args) {
        //订单模块
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                System.out.println("保存订单的线程");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new NullPointerException();
            }
        });
        //发货模块
        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                System.out.println("保存收获地址的线程");
            }
        });

        threadOne.start();
        threadTwo.start();

    }
}
