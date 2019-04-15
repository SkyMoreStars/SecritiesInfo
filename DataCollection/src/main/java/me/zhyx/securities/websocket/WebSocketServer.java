package me.zhyx.securities.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther: yssq
 * @Date: 2019/4/15 11:22
 * @Description:
 */
@ServerEndpoint("/websocket/{sid}")
@Component
@Slf4j
public class WebSocketServer {
    private static int onlineCount=0;
    private static CopyOnWriteArrayList<WebSocketServer> webSocketServers=new CopyOnWriteArrayList<>();

    private Session session;
    private String sid="";
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        this.session=session;
        this.sid=sid;
        webSocketServers.add(this);
        addOnlineCount();
        log.info("有新窗口开始监听：{},当前在线数为：{}",sid,getOnlineCount());
        try {
            sendMessage("连接成功");
        }catch (IOException e){
            log.error("websocket 异常");
        }

    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session,@PathParam("sid") String sid){
        webSocketServers.remove(this);
        subOnlineCount();
        log.info("有一连接关闭,当前在线数为：{}",getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("收到来自窗口{}的消息：{}",sid,message);
        for (WebSocketServer item : webSocketServers) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebSocketServer item : webSocketServers) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    private void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    private synchronized void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    private synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    private synchronized int getOnlineCount(){
        return WebSocketServer.onlineCount;
    }
}
