package com.concurrent.program.thread.base;

import sun.misc.Unsafe;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class TestUnSafeWrong {

    //获取Unsafe的实例（2.2.1）
    static final Unsafe unsafe = Unsafe.getUnsafe();

    //记录变量state在类TestUnSafe中的偏移值（2.2.2）
    static final long stateOffset;

    //变量(2.2.3)
    private volatile long state = 0;

    static {

        try {
            //获取state变量在类TestUnSafe中的偏移值(2.2.4)
            stateOffset = unsafe.objectFieldOffset(TestUnSafeWrong.class.getDeclaredField("state"));

        } catch (Exception ex) {

            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }

    }

    public static void main(String[] args) {

        //创建实例，并且设置state值为1(2.2.5)
        TestUnSafeWrong test = new TestUnSafeWrong();
        //(2.2.6)
        Boolean sucess = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(sucess);

    }

}
