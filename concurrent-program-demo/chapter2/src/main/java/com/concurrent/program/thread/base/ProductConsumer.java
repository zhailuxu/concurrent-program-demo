package com.concurrent.program.thread.base;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class ProductConsumer {
    //共享队列
    private static Queue<Integer> queue = new LinkedList<>();
    private static final int MAX_SIZE = 10;

    private static void product() {
        synchronized (queue) {
            //1.消费队列满，则等待队列空闲
            while (queue.size() == MAX_SIZE) {
                try {
                    //挂起当前线程，并释放通过同步块获取的queue上面的锁，让消费线程可以获取该锁，然后获取队列里面元素
                    queue.wait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            //2.空闲则生成元素，并通知消费线程
            queue.add(new Random().nextInt());
            queue.notifyAll();
        }
    }

    private static void consumer() {
        synchronized (queue) {
            //1.消费队列为空,则等待有元素可消费
            while (queue.size() == 0) {
                try {
                    //挂起当前线程，并释放通过同步块获取的queue上面的锁，让生产线程可以获取该锁，生产元素放入队列
                    queue.wait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            //消费元素，并通知唤醒生产线程
            System.out.println(queue.poll());
            queue.notifyAll();

        }
    }

    public static void main(String[] args) {

        //1.生产线程，定时生产
        new Thread(() -> {
            for (; ; ) {
                //1.1 生产元素
                product();
                //1.2休眠1s
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        //2.消费线程
        new Thread(() -> {
            for (; ; ) {
                consumer();
            }

        }).start();
    }


}
