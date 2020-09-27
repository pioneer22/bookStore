package main.java.com.example.bookstore.AdminController;

import com.example.bookstore.utils.DateTimeUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收userId
    private String userId="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("连接成功！"+getOnlineCount());
        this.userId=userId;
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到来自用户"+userId+"的信息:"+message);
        //發送消息格式:接收者+"::"+發送內容
        //接收消息格式：发送者+"::"+發送內容+"::"+時間
        String[] msg = message.split("::");
        String addtime = DateTimeUtil.format(new Date());
        //发消息，0用户发给客服，1客服单发给用户，2客服群发给用户
        for (WebSocketServer item : webSocketSet) {
            try {
                if(msg[2].equals("2")) { // 群发
                    message = this.userId +"::" + msg[2] +"::"+ addtime;
                    System.out.println();
                    item.sendMessage(message);
                } else if (item.userId.equals(msg[1])){ // 单发，指定发送对象
                    message = this.userId +"::" + msg[2] +"::"+ addtime;
                    System.out.println();
                    item.sendMessage(message);
                }
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
        System.out.println("发生错误");
        error.printStackTrace();
    }
	/**
	 * 实现服务器主动推送
	 */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        System.out.println("推送消息到窗口"+userId+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet) {
            try {
            	//这里可以设定只推送给这个userId的，为null则全部推送
            	if(userId==null) {
            		item.sendMessage(message);
            	}else if(item.userId.equals(userId)){
            		item.sendMessage(message);
            	}
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}