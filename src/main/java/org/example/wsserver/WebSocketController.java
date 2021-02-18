package org.example.wsserver;

import org.example.wsserver.bean.MessageBean;
import org.example.wsserver.bean.RoomInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class WebSocketController {
    @RequestMapping("/")
    public String index() {
        return "welcome to my WebSocket server...";
    }

    @RequestMapping("/roomList")
    public List<RoomInfo> roomList() {
        ConcurrentHashMap<String, RoomInfo> rooms = WebSocketList.rooms;
        Collection<RoomInfo> values = rooms.values();
        return new ArrayList<>(values);
    }

    @RequestMapping("/userList")
    public List<String> userList() {
        Collection<String> values = WebSocketList.userBeans.keySet();
        return new ArrayList<>(values);
    }

    @RequestMapping("/messages")
    public List<MessageBean> messageBeanList() {
        ConcurrentHashMap<String, MessageBean> message = WebSocketList.messages;
        Collection<MessageBean> values = message.values();
        return new ArrayList<>(values);
    }

    @RequestMapping("/deleteMessage/{key}")
    public String deleteMessage(@PathVariable("key") String key) {
        String result = "";
        try {
            WebSocketList.deleteMessage(key);
            result = key + "->del success!";
        } catch (Exception e) {
            result = "del err:" + e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/updateMessage/{key}/{message}")
    public String updateMessage(@PathVariable("key") String key, @PathVariable("message") String message) {
        String result = "";
        try {
            WebSocketList.updateMessages(key, new MessageBean(key, message, System.currentTimeMillis()));
            result = key + "->update success!";
        } catch (Exception e) {
            result = "update err:" + e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/clear")
    public String clearMessage() {
        String result = "";
        try {
            WebSocketList.clearMessage();
            result = "clear success!";
        } catch (Exception e) {
            result = "clear err:" + e.getMessage();
            e.printStackTrace();
        }
        return result;
    }
}
