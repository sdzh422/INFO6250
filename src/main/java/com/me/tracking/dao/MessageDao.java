package com.me.tracking.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.tracking.exception.MessageException;

import com.me.tracking.pojo.Message;
import com.me.tracking.pojo.User;

import java.util.List;


public class MessageDao extends DAO{
	public void sendMessage(Message message) throws MessageException
	{
		try {
			begin();
			getSession().save(message);
			commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new MessageException("Exception while sending message: " + e.getMessage());
		}
	}
	public List<Message> getMessage(String username) throws MessageException
	{
		try {
			begin();
			Query q = getSession().createQuery("from Message m where m.fromuser =:from or m.touser=:send");
			q.setString("from", username);
			q.setString("send", username);
			List<Message> result = q.list();
			return result;
		}catch (HibernateException e) {
			rollback();
			throw new MessageException("Exception while getting message: " + e.getMessage());
		}
	}

}
