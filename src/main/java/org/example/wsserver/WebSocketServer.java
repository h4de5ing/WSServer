package org.example.wsserver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/ws/{userId}")
@Component
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    // 在线用户表
    public static ConcurrentHashMap<String, Session> userBeans = new ConcurrentHashMap<>();
    String userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.userId = userId;
        userBeans.put(userId, session);
        logger.info("新连接：sessionID:{},userID:{},总共:{}人在线", session.getId(), userId, userBeans.size());
    }

    // 用户下线
    @OnClose
    public void onClose(Session session) {
        String removeKey = null;
        for (String key : userBeans.keySet()) {
            Session one = userBeans.get(key);
            if (one.getId().equals(session.getId())) {
                removeKey = key;
            }
        }
        if (removeKey != null) {
            userBeans.remove(removeKey);
            logger.info("{},{} -->onClose......还剩余人数:{}", removeKey, session.getId(), userBeans.size());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        //handleMessage(message, session);
        logger.info("收到用户{}的消息{}", session.getId(), message);
        //sendMessage(session, "收到 " + session.getId() + " 的消息 " + message); //回复用户
        send2All(session, message);
    }

    public void send2All(Session session, String message) {
        for (String key : userBeans.keySet()) {
            Session one = userBeans.get(key);
            if (!one.getId().equals(session.getId())) {
                sendMessage(userBeans.get(key), message);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("用户id为：{}的连接发送错误", session.getId());
    }

    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}