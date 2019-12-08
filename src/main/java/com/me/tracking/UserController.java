package com.me.tracking;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.validation.Valid;

import com.me.tracking.dao.UserDao;
import com.me.tracking.exception.UserException;
import com.me.tracking.identifying.EZgenerator;
import com.me.tracking.pojo.User;






@Controller
@SessionAttributes("username")
public class UserController {
	@Autowired
	UserDao userDao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/LoginAction", method = RequestMethod.POST)
	public String login(HttpServletRequest req,Model model,HttpSession session) throws UserException
	{
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = userDao.authenticateLogin(username, password);
		if(user==null)
		{
			logger.info(username);
			logger.info(password);
			return "login";
		}
		else {
			model.addAttribute("username", username);
			session.setAttribute("user", user);
			return "myHome";
		}
	}
	@RequestMapping(value="/LoginAction", method = RequestMethod.GET)
	public String loginform()
	{
		return "login";
	}
	
	@RequestMapping(value="/RegistrationAction.htm", method = RequestMethod.GET)
	public String redirect(HttpServletRequest req,Model model)
	{
		model.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value="/RegistrationAction.htm", method = RequestMethod.POST)
	public String signup(@ModelAttribute(value = "user") @Valid User user, BindingResult result, Model model,
			HttpSession session) throws UserException {
		if (result.hasErrors()) {
			return "register";
		} else {
			// check unique
			UserDao udDao = new UserDao();
			String username = user.getUsername();
			if (!udDao.validUnique(username)) {
				model.addAttribute("notUnique", "Please change another username.");
				return "register";
			}
			userDao.signup(user);
			session.setAttribute("user", user);
			model.addAttribute("username", user.getUsername());
			return "myHome";
		}
	}
	
	@RequestMapping(value="/myHome.htm", method = RequestMethod.GET)
	public String redirectMyHome()
	{
		return "myHome";
	}
	
	@RequestMapping(value="/myAccount.htm",method = RequestMethod.GET)
	public String redirectMyAccount( Model model)
	{
		model.addAttribute("user",new User());
		return "myAccount";
	}
	
	@RequestMapping(value="/myAccount.htm", method = RequestMethod.POST)
	public String updateMyAccount(@ModelAttribute(value = "user") @Valid User user, BindingResult result) throws UserException
	{
		if (result.hasErrors()) {
			logger.info("update error");
			return "myAccount";
		}
		else {
			userDao.updateUser(user);
			return "success";
		}

	}
	
	@RequestMapping(value="/passwordResetAction",method = RequestMethod.GET)
	public String redirectPasswordReset()
	{
		return "passwordReset";
	}
	
	// begin password reset action
	@RequestMapping(value="/passwordResetAction",method = RequestMethod.POST)
	public String beginPasswordReset(HttpServletRequest req,Model model,HttpSession session) throws UserException, EmailException
	{
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		//get user info 
		User user = userDao.validInfo(username, "email", email);
		if(user==null)
		{
			model.addAttribute("error","Username or email is invalid.");
			return "passwordReset";
		}
		else {
			String codeString = EZgenerator.generate_reset(username);
			logger.info(EZgenerator.generate_reset(username));
			SendEmail(email,codeString);
			session.setAttribute("username", username);
			return "verifyEmail";
		}
	}
	
	@RequestMapping(value="/verifyEmailAction",method = RequestMethod.GET)
	public String redirectVerifyEmail(HttpSession session)
	{
		String username = (String)session.getAttribute("username");
		if(username==null)
			return "passwordReset";
		else {
			return "verifyEmail";
		}	
	}
	
	@RequestMapping(value="/verifyEmailAction",method = RequestMethod.POST)
	public String verifyEmail(HttpSession session,HttpServletRequest req,Model model)
	{
		String username = (String)session.getAttribute("username");
		if(username==null)
			return "passwordReset";
		else {
			String secCode=EZgenerator.generate_reset(username);
			//compare to user input
			String inputString = req.getParameter("secCode");
			if(secCode.equals(inputString))
			{
				//build reset page
				return "verify_succeed";
			}
			else {
				model.addAttribute("error","Please check your security code");
				return "verifyEmail";
			}
		}	
	}
	
	@RequestMapping(value="/resetNext",method = RequestMethod.GET)
	public String redirectResetFinish(HttpSession session)
	{
		String username = (String)session.getAttribute("username");
		if(username==null)
			return "passwordReset";
		else {
			return "verify_succeed";
		}	
	}
	
	@RequestMapping(value="/resetNext",method = RequestMethod.POST)
	public String resetFinish(HttpSession session,HttpServletRequest req) throws UserException
	{
		String username = (String)session.getAttribute("username");
		if(username==null)
			return "passwordReset";
		else {
			String password= req.getParameter("password");
			userDao.resetPassword(username, password);
			session.invalidate();
			return "login";
		}	
	}
	
	public void SendEmail(String emailaddress,String securityNum) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("zh.oliver7@gmail.com", "zh19960422"));
		email.setSSLOnConnect(true);
		email.setFrom("no-reply@tracking.com");
		email.setSubject("Account Verification");
		email.setMsg("Here's your security code: "+securityNum);
		email.addTo(emailaddress);
		email.send();
	}
	
	
	@RequestMapping(value="/logOutAction.htm",method = RequestMethod.GET)
	public String Logout(HttpSession session)
	{
		session.invalidate();
		return "index";
	}
	


}
