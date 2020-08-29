package com.concurrent.program.in.action;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.alibaba.fastjson.JSON;

/**
 * Hello world!
 */
public class TestMap2 {//(1)创建map,key为topic,value为设备列表
    //(1)创建map,key为topic,value为设备列表
    static ConcurrentHashMap<String, CopyOnWriteArrayList<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        //(2)进入直播间topic1 线程one
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device1");
                list1.add("device2");
                //(2.1)
                CopyOnWriteArrayList<String> oldList = map.putIfAbsent("topic1", list1);
                if (null != oldList) {
                    oldList.addAll(list1);
                }
                System.out.println(JSON.toJSONString(map));
            }
        });
        //(3)进入直播间topic1 线程two
        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device11");
                list1.add("device22");

                CopyOnWriteArrayList<String> oldList = map.putIfAbsent("topic1", list1);
                if (null != oldList) {
                    oldList.addAll(list1);
                }

                System.out.println(JSON.toJSONString(map));
            }
        });

        //(4)进入直播间topic2 线程three
        Thread threadThree = new Thread(new Runnable() {
            public void run() {
                CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device111");
                list1.add("device222");

                CopyOnWriteArrayList<String> oldList = map.putIfAbsent("topic2", list1);
                if (null != oldList) {
                    oldList.addAll(list1);
                }
                System.out.println(JSON.toJSONString(map));
            }
        });

        //(5)启动线程
        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }
}
