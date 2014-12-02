package org.jewelsea.websocket.test;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/hello")
public class HelloEndpoint {
    @OnMessage
    public String onMessage(String message, Session session) {
        return "Hello " + message;
    }
}