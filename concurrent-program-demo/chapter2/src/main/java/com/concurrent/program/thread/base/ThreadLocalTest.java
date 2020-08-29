package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class ThreadLocalTest {

    //(1)打印函数
    static void print(String str) {
        //1.1  打印当前线程本地内存中localVariable变量的值
        System.out.println(str + ":" + localVariable.get());
        //1.2 清除当前线程本地内存中localVariable变量
        //localVariable.remove();
    }

    //(2) 创建ThreadLocal变量
    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {

        //(3) 创建线程one
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                //3.1 设置线程one中本地变量localVariable的值
                localVariable.set("threadOne local variable");
                //3.2 调用打印函数
                print("threadOne");
                //3.3打印本地变量值
                System.out.println("threadOne remove after" + ":" + localVariable.get());

            }
        });
        //(4) 创建线程two
        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                //4.1 设置线程one中本地变量localVariable的值
                localVariable.set("threadTwo local variable");
                //4.2 调用打印函数
                print("threadTwo");
                //4.3打印本地变量值
                System.out.println("threadTwo remove after" + ":" + localVariable.get());

            }
        });
        //(5)启动线程
        threadOne.start();
        threadTwo.start();
    }
}
