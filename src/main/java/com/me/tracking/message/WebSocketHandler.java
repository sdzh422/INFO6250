package com.me.tracking.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.me.tracking.HomeController;
import com.me.tracking.dao.RedisProvider;

import redis.clients.jedis.Jedis;

public class WebSocketHandler extends TextWebSocketHandler{
	//connected user list
    private static final Map<Integer, WebSocketSession> users = new HashMap<Integer, WebSocketSession>();
    private static int Numconut=0; //support =  0
    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    /**
     * 连接已关闭，移除在Map集合中的记录
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        users.remove(getUserId(session));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.out.println("Connect Error");
        users.remove(getUserId(session));
    }

    /**
     * build identifier
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //System.out.println("Connect Succeed");
        Integer userId = getUserId(session);
        logger.info("user id"+ userId);
        System.out.println(userId);
        if (userId != null) {
            users.put(userId, session);
            logger.info("user size"+String.valueOf(users.size()));
            session.sendMessage(new TextMessage("What can i do for you?"));
            //System.out.println(userId);
            //System.out.println(session);
        }
    }

    /**
     * recevie message method
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	logger.info(message.toString());
    }

     /**
     * send message
     * @param clientId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(Integer clientId, String message,int from) {
    	
        if (users.get(clientId) == null&&clientId!=0) {
            return false;
        }
        //store message
        //key - messageText_clientId
    	if(clientId == 0)
    	{
	    	Jedis jedis = RedisProvider.getJedis();
	    	String store_name="messageText_"+from;
	    	logger.info("store name: "+store_name);
	    	boolean isExist = jedis.exists(store_name);
	    	if(isExist)
	    	{
				jedis.append(store_name, "-"+message);
	    	}
	    	else {
	    		jedis.psetex(store_name, 600*1000, message); //ms
			}
	    	return true;
    	}
    	else {
	        WebSocketSession session = users.get(clientId);
	        //System.out.println("sendMessage:" + session);
	
	        if (!session.isOpen()) {
	            return false;
	        }
	        try {
	            TextMessage textMessage = null; 
	            // send message
	            textMessage = new TextMessage(message);
	            session.sendMessage(textMessage);
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
    	}
    }

    /**
     * broadcast method
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = false;
        Set<Integer> userIds = users.keySet();
        WebSocketSession session = null;
        for (Integer uid : userIds) {
            try {
                session = users.get(uid);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }
        allSendSuccess = true;
        return  allSendSuccess;
    }

     /**
     * get user id
     * @param session
     * @return
     */
    private Integer getUserId(WebSocketSession session) {
        try {
        	int UserId = (Integer)session.getAttributes().get("user");
            return UserId;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static int getConnectionNum()
    {
    	Numconut++;
    	return Numconut;
    }
    
    public static Set<Integer> getUsers()
    {
    	return users.keySet();
    }


}
