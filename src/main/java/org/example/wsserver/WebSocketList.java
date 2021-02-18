package org.example.wsserver;

import org.example.wsserver.bean.MessageBean;
import org.example.wsserver.bean.RoomInfo;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketList {
    //在线用户列表
    public static ConcurrentHashMap<String, Session> userBeans = new ConcurrentHashMap<>();
    //所有用户的消息缓存
    public static ConcurrentHashMap<String, MessageBean> messages = new ConcurrentHashMap<>();
    //房间列表
    public static ConcurrentHashMap<String, RoomInfo> rooms = new ConcurrentHashMap<>();

    public static void updateMessages(String key, MessageBean messageBean) {
        messages.put(key, messageBean);
    }


    public static void deleteMessage(String key) {
        System.out.println(messages);
        System.out.println(key);
        messages.remove(key);
    }

    public static void clearMessage() {
        messages.clear();
    }
}
