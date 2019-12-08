package com.me.tracking;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.tracking.dao.PackageDAO;
import com.me.tracking.exception.PackageException;
import com.me.tracking.pojo.PackageInfo;
import com.me.tracking.pojo.User;

import java.util.List;

@Controller
public class TrackController {
	@Autowired
	PackageDAO packageDao;
	
	private static final Logger logger = LoggerFactory.getLogger(TrackController.class);
	@RequestMapping(value="/TrackConfirmAction", method = RequestMethod.POST)
	public ModelAndView trackResult(HttpServletRequest req) throws PackageException
	{
		//track package
		String number = req.getParameter("tracking_num");
		//validate
		PackageInfo packageInfo = packageDao.getPackage(number);
		if(packageInfo!=null)
			return new ModelAndView("trackResult","packageinfo",packageInfo) ;
		else {
			return new ModelAndView("trackResult_2","trackingnum",number);
		}


	}
	
	@RequestMapping(value="/myPackages.htm", method = RequestMethod.GET)
	public String getMyPackage(HttpSession session,Model model) throws PackageException
	{
		User user = (User)session.getAttribute("user");
		String phone = user.getPhone();
		List<PackageInfo> packageList = packageDao.getMyPackage(phone);
		logger.info(String.valueOf(packageList.size()));
		model.addAttribute("packageList",packageList);
		if(packageList.isEmpty())
		{
			return "myPackage_2";
		}
		else {
			return "myPackage";
		}
		
	}
	
	//delivery man part
	//jump to a new page to update
	@RequestMapping(value="/UpdatePackage.htm", method = RequestMethod.GET)
	public String UpdatePackageForm(HttpSession session)
	{
		String number = (String) session.getAttribute("trackingnum");
		return "UpdateForm";
	}
	
	@RequestMapping(value="/UpdatePackage.htm", method = RequestMethod.POST)
	public String UpdatePackage(HttpSession session)
	{
		return "Success";
	}
	
	@RequestMapping(value="/map.htm", method = RequestMethod.GET)
	public String map()
	{
		return "map";
	}
	

}
