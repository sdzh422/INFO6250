package com.me.tracking.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.tracking.pojo.User;
import com.me.tracking.exception.UserException;
import com.me.tracking.pojo.PackageInfo;

public class UserDao extends DAO{
	public User authenticateLogin(String username,String password) throws UserException
	{
		try {
			begin();
			Query q = getSession().createQuery("from User u where username= :username AND password=:password");
			q.setString("username", username);
			q.setString("password", password);
			User user = (User)q.uniqueResult();
			commit();
			return user;
		}
		catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new UserException("Could not Login with username - " + username + "password -"+password);
		}
	}
	
	public void signup(User user) throws UserException
	{
		try {
			begin();
			getSession().save(user);
			commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new UserException("Could not add new user username - " + user.getUsername() + "password -"+ user.getPassword());
		}
	}
	
	public boolean validUnique(String username) throws UserException
	{
		User user =null;
		try {
			begin();
			Query q = getSession().createQuery("from User u where username= :username");
			q.setString("username", username);
			user = (User)q.uniqueResult();	
		} catch (Exception e) {
			// TODO: handle exception
			rollback();
			throw new UserException("Could not valid new user username - " + username );
		}
		if(user!=null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void updateUser(User user) throws UserException
	{
		try {
			begin();
			Query q = getSession().createQuery("update User set password=:password,"
					+ "address =:address, phone=:phone, email =:email where username= :username");
			q.setString("username", user.getUsername());
			q.setString("password", user.getPassword());
			q.setString("address", user.getAddress());
			q.setString("phone", user.getPhone());
			q.setString("email", user.getEmail());
			q.executeUpdate();
			commit();
			}
		catch (HibernateException e) {
				// TODO: handle exception
				rollback();
				throw new UserException("Could not add update user username - " + user.getUsername() + "password -"+ user.getPassword());
			}
	}
	
	public User validInfo(String username,String field,String value) throws UserException
	{
		User user = new User();
		try {
			begin();
			Query q = getSession().createQuery("from User u where username= :username and "+field
					+"=:value");
			q.setString("username",username);
			q.setString("value", value);
			user = (User)q.uniqueResult();	
			commit();
			}
		catch (HibernateException e) {
				// TODO: handle exception
				rollback();
				throw new UserException("Could not obtain user username - " + username + "password -"+ user.getPassword());
			}
		return user;
	}
	
	public void resetPassword(String username,String password ) throws UserException
	{
		try {
			begin();
			Query q = getSession().createQuery("update User set password=:password "
					+ "where username= :username");
			q.setString("username", username);
			q.setString("password", password);
			q.executeUpdate();
			commit();
			}
		catch (HibernateException e) {
				// TODO: handle exception
				rollback();
				throw new UserException("Could not reset password username - " + username + "password -"+ password);
			}
	}
	

}
