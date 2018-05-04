package com.smart.service;

import com.smart.bean.Message;
import com.smart.dao.DaoManager;
import com.smart.dao.MessageDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{mid}/{oid}")
public class WebSockect {

    private static final ConcurrentHashMap<String, WebSockect> socketSessionMap = new ConcurrentHashMap<String, WebSockect>();

    private Session session;
    private int mid;
    private int oid;
    private String key;

    @OnOpen
    public void onOpen(@PathParam("mid")int mid, @PathParam("oid")int oid, Session session) {
        this.session = session;
        this.mid = mid;
        this.oid = oid;
        //socket连接以两人id作为key，同时在聊天窗口
        this.key = mid + "/" +oid;
        if (!socketSessionMap.containsKey(key)) {
            socketSessionMap.put(key, this);
        }
        System.out.println("seesion onOpen !");
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("onMessage: " + key + message);
        Message msg = new Message();
        msg.setSendId(mid);
        msg.setReplyId(oid);
        WebSockect sockect = socketSessionMap.get(oid + "/" + mid);
        msg.setSendTime(System.currentTimeMillis());
        msg.setContent(message);
        if (sockect!= null && sockect.session.isOpen()) {
            msg.setIsRead(1);
            sockect.session.getBasicRemote().sendText(message);
        } else {
            msg.setIsRead(0);
        }
        DaoManager.getInstance().getMessageDao().insert(msg);
    }

    @OnClose
    public void onClose(Session session) {
        if (socketSessionMap.contains(this)) {
            socketSessionMap.remove(key);
        }
        System.out.println("session closed !");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("session error !" + error);
        error.printStackTrace();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebSockect sockect = (WebSockect) o;

        return key != null ? key.equals(sockect.key) : sockect.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
