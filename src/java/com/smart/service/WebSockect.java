package com.smart.service;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class WebSockect {

    private static final ConcurrentHashMap<Long, WebSockect> socketSessionMap = new ConcurrentHashMap<Long, WebSockect>();

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("seesion onOpen !");
    }

    public void onMessage(Session session) {
        System.out.println("session onMessage !");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("session closed !");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("session error !" + error);
    }


}
