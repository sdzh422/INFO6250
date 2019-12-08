package com.me.tracking;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.tracking.dao.MessageDao;
import com.me.tracking.dao.RedisProvider;
import com.me.tracking.exception.MessageException;
import com.me.tracking.message.WebSocketHandler;
import com.me.tracking.pojo.Message;
import com.me.tracking.pojo.UserMessage;

import javassist.expr.NewArray;
import redis.clients.jedis.Jedis;




@Controller
public class MessageController {
	@Autowired
	MessageDao messageDao;
	private WebSocketHandler messageHandler = new WebSocketHandler();
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	@RequestMapping(value="/ChatAction.htm", method = RequestMethod.GET)
	public String redirect(HttpSession session,Model model) throws MessageException
	{
		String username = (String)session.getAttribute("username");
		//String username = "t1";
		model.addAttribute("messageList", messageDao.getMessage(username));
		model.addAttribute("message",new Message());
		return  "chat";
	}
	
	@RequestMapping(value="/ChatAction.htm", method = RequestMethod.POST)
	public String update(@ModelAttribute(value = "message") Message message,HttpSession session,Model model) throws MessageException
	{
		String username = (String)session.getAttribute("username");
		//String username = "t1";
		message.setTouser("Support");
		message.setFromuser(username);
		messageDao.sendMessage(message);
		redirect(session,model);
		return  "chat";
	}
	
	@RequestMapping(value="/ServiceSupport.htm", method = RequestMethod.GET)
	public String redirectToServiceSupport(HttpSession session)
	{
		if(session.getAttribute("userId")!=null)
			return "message";
		int num = WebSocketHandler.getConnectionNum();
		logger.info("Connectnum+"+num);
		session.setAttribute("userId", num);
		return "message";
	}
	
	@RequestMapping(value="/ServiceSupport.htm", method = RequestMethod.POST)
	public String sendToSupport(HttpSession session,HttpServletRequest req)
	{
		logger.info("message_text"+ req.getParameter("message_text"));
		//boolean isSuccess = messageHandler.sendMessageToUser((Integer)session.getAttribute("userId"), req.getParameter("message_text"));
		Integer fromuser = (Integer)session.getAttribute("userId");
		boolean isSuccess = messageHandler.sendMessageToUser(0, req.getParameter("message_text"),fromuser);
		return "message";
	}
	
	@RequestMapping(value="/TestMessage.htm", method = RequestMethod.GET)
	public String redirectTestMessage(HttpSession session,Model model)
	{
		//retrieve message
		Set<Integer> userlist = WebSocketHandler.getUsers();
		logger.info("Userlist size-"+userlist.size());
		Jedis jedis = RedisProvider.getJedis();
		ArrayList<UserMessage> usermessageList = new ArrayList<UserMessage>();
		for(Integer uid:userlist)
		{
			logger.info("uid-"+uid);
			String key = "messageText_"+uid;
			if(jedis.exists(key))
			{
				String message_user_all = jedis.get(key);
				logger.info("ori message-"+message_user_all);
				String[] message_list = message_user_all.split("-");
				UserMessage uMessage = new UserMessage();
				uMessage.setMessageLiSt(message_list);
				logger.info("message size-"+message_list.length);
				uMessage.setUid(uid);
				usermessageList.add(uMessage);
			}
		}
		model.addAttribute("usermessageList", usermessageList);
		logger.info("List size-"+usermessageList.size());
		return "ServerMessage";
	}
	
	@RequestMapping(value="/TestMessage.htm", method = RequestMethod.POST)
	public String sendtoUser(HttpSession session,HttpServletRequest req)
	{
		//logger.info("message_text"+ req.getParameter("message_text"));
		//boolean isSuccess = messageHandler.sendMessageToUser((Integer)session.getAttribute("userId"), req.getParameter("message_text"));
		String userid = req.getParameter("message_touserid");
		boolean isSuccess = messageHandler.sendMessageToUser(Integer.valueOf(userid),req.getParameter("message_text"),0);
		return "message";
	}
}
