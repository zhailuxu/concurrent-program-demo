package com.concurrent.program.thread.base;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 * 每个共享变量都有一个set集合,当线程调用该共享对象的wait()方法时候，会把当前线程放入该共享变量的等待集合里面，当前线程只会释放当前共享对象的锁，如果当前线程还持有其它共享对象的监视器锁，那么当前线程在synchronized块内等待时候还是持有这些共享对象的监视器锁的
 */
public class HoldMonitorLockTest {
    // 1.创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        //2. 创建线程A,获取两个资源的监视器锁
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                try {

                    //2.1 获取resourceA共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.println("threadA get resourceA lock");
                        //2.2 获取resourceB共享资源的监视器锁
                        synchronized (resourceB) {
                            System.out.println("threadA get resourceB lock");
                            //2.3 线程A阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadA");

        //3. 创建线程，
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                try {

                    //3.1休眠1s，让线程A获取到资源B的锁
                    Thread.sleep(1000);

                    // 3.2获取resourceA共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB try get resourceB lock...");
                        //3.3 获取resourceB共享资源的监视器锁
                        synchronized (resourceB) {
                            System.out.println("threadB get resourceB lock");
                            // 线程B阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();

                        }

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadB");

        //4. 启动线程
        threadA.start();
        threadB.start();

        //5. 等待两个线程结束
        threadA.join();
        threadB.join();

        System.out.println("main over");

    }
}
