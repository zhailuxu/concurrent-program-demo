package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class InheritableThreadLocalTest {
    //(1) 创建线程变量
    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();

    public static void main(String[] args) {

        //(2)  设置线程变量
        threadLocal.set("hello world");
        //(3) 启动子线程
        Thread thread = new Thread(new Runnable() {
            public void run() {
                //(4)子线程输出线程变量的值
                System.out.println("thread:" + threadLocal.get());

            }
        });
        thread.start();

        //(5)主线程输出线程变量值
        System.out.println("main:" + threadLocal.get());

    }
}
