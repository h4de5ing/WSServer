package org.example.wsserver.bean;

import javax.websocket.Session;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoomInfo {
    public String roomId;//房间id
    public String userId;//创始人id
    //房间里面的人
    public CopyOnWriteArrayList<Session> userBeans = new CopyOnWriteArrayList<>();
}
