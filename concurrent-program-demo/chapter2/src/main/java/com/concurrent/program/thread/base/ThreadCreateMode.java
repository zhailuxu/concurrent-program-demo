package com.concurrent.program.thread.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方法
 *
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class ThreadCreateMode {

    //继承Thread类并重写run方法
    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("I am a child thread:" + Thread.currentThread().getName());
        }
    }

    public static class RunnableTask implements Runnable {
        @Override
        public void run() {
            System.out.println("I am a child thread:" + Thread.currentThread().getName());
        }
    }

    private static void threadMode() {
        // 创建线程
        MyThread thread = new MyThread();
        // 启动线程
        thread.start();
    }

    private static void runnableMode() {
        RunnableTask task = new RunnableTask();
        new Thread(task).start();
        new Thread(task).start();

    }

    public static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {

            return "hello";
        }

    }


    public static void callerTaskMode() {
        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        //启动线程
        new Thread(futureTask).start();
        try {
            //等待任务执行完毕，并返回结果
            String result = futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        //1.Thread方式
        threadMode();

        //2.Runnable方式
        runnableMode();

        //3.CallerTask
        callerTaskMode();

    }
}