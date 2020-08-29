package com.concurrent.program.in.action;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class TestShutDown {
    static void asynExecuteOne() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            public void run() {
                System.out.println("--async execute one ---");
            }
        });
        //executor.shutdown();

    }

    static void asynExecuteTwo() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            public void run() {
                System.out.println("--async execute two ---");
            }
        });
       // executor.shutdown();

    }


    public static void main(String[] args) {
        //(1)同步执行
        System.out.println("---sync execute---");
        //(2)异步执行操作one
        asynExecuteOne();
        //(3)异步执行操作two
        asynExecuteTwo();
        //(4)执行完毕
        System.out.println("---execute over---");
    }

}
