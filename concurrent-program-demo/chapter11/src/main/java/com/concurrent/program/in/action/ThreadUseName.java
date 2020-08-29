package com.concurrent.program.in.action;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class ThreadUseName {
    static final String THREAD_SAVE_ORDER = "THREAD_SAVE_ORDER";
    static final String THREAD_SAVE_ADDR = "THREAD_SAVE_ADDR";

    public static void main(String[] args) {

        // 订单模块
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                System.out.println("保存订单的线程");
                throw new NullPointerException();
            }
        }, THREAD_SAVE_ORDER);
        // 发货模块
        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                System.out.println("保存收货地址的线程");
            }
        }, THREAD_SAVE_ADDR);

        threadOne.start();
        threadTwo.start();

    }

}
