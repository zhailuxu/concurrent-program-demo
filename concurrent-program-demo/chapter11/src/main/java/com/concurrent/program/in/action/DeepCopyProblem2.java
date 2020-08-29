package com.concurrent.program.in.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
public class DeepCopyProblem2 {

    static class Msg {
        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        private String dataId;
        private String body;

    }

    public interface StrategyService {
        public void sendMsg(List<Msg> msgList, List<String> deviceIdList);
    }

    public static class StrategyOneService implements StrategyService {

        @Override
        public void sendMsg(List<Msg> msgList, List<String> deviceIdList) {
            for (Msg msg : msgList) {
                msg.setDataId("oneService_" + msg.getDataId());
                System.out.println(msg.getDataId() + " " + JSON.toJSONString(deviceIdList));
            }
        }
    }

    public static class StrategyTwoService implements StrategyService {

        @Override
        public void sendMsg(List<Msg> msgList, List<String> deviceIdList) {

            for (Msg msg : msgList) {
                msg.setDataId("TwoService_" + msg.getDataId());
                System.out.println(msg.getDataId() + " " + JSON.toJSONString(deviceIdList));
            }
        }
    }

    // (1)不同appkey注册不同的服务
    static Map<Integer, StrategyService> serviceMap = new HashMap<Integer, StrategyService>();

    public static void main(String[] args) {

        //注册
        serviceMap.put(111, new StrategyOneService());
        serviceMap.put(222, new StrategyTwoService());

        // (2)key为appkey,value为设备id列表
        Map<Integer, List<String>> appKeyMap = new HashMap<Integer, List<String>>();

        // (3)为appkey=111的添加设备列表
        List<String> oneList = new ArrayList<>();
        oneList.add("device_id1");
        appKeyMap.put(111, oneList);

        // 为appkey=222的添加设备列表
        List<String> twoList = new ArrayList<>();
        twoList.add("device_id2");
        appKeyMap.put(222, twoList);

        // (4)创建消息
        List<Msg> msgList = new ArrayList<>();
        Msg msg = new Msg();
        msg.setDataId("abc");
        msg.setBody("hello");
        msgList.add(msg);

        // (5)根据不同的appKey使用不同的策略进行处理
        Iterator<Integer> appKeyItr = appKeyMap.keySet().iterator();
        Map<Integer, List<Msg>> appKeyMsgMap = new HashMap<Integer, List<Msg>>();
        while (appKeyItr.hasNext()) {
            appKeyMsgMap.put(appKeyItr.next(), new ArrayList<>(msgList));
        }

        // (5)根据不同的appKey使用不同的策略进行处理
        appKeyItr = appKeyMap.keySet().iterator();
        while (appKeyItr.hasNext()) {
            int appKey = appKeyItr.next();
            // 这里从根据appkey获取自己的消息列表
            StrategyService strategyService = serviceMap.get(appKey);
            if (null != strategyService) {
                strategyService.sendMsg(appKeyMsgMap.get(appKey), appKeyMap.get(appKey));
            } else {
                System.out.println(String.format("appkey:%s, is not registerd service", appKey));
            }

        }
    }

}
