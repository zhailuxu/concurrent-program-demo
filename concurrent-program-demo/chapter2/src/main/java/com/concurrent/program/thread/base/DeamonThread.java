package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class DeamonThread {
    public static void main(String[] args) {
        //1.创建线程
        Thread daemonThread = new Thread(new Runnable() {
            public void run() {
                for (; ; ) {
                    System.out.println("hello,jiaduo");
                }
            }
        });

        //2.设置为守护线程，并启动
        //daemonThread.setDaemon(true);
        daemonThread.start();

    }
}
