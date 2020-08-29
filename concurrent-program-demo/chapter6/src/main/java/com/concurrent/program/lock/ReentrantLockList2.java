package com.concurrent.program.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class ReentrantLockList2 {

    //线程不安全的list
    private ArrayList<String> array = new ArrayList<String>();
    //独占锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    //添加元素
    public void add(String e) {

        writeLock.lock();
        try {
            array.add(e);

        } finally {
            writeLock.unlock();

        }
    }

    //删元素
    public void remove(String e) {

        writeLock.lock();
        try {
            array.remove(e);

        } finally {
            writeLock.unlock();

        }
    }

    //获取数据
    public String get(int index) {

        readLock.lock();
        try {
            return array.get(index);

        } finally {
            readLock.unlock();

        }
    }

}
