package com.me.tracking.identifying;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.tracking.HomeController;

@WebFilter(filterName = "loginFilter", 
urlPatterns = "/*")
public class SessionManager implements Filter{
	
	private String[] reqList= {"/UpdatePackage.htm","/myPackages.htm","/myAccount.htm","/myHome.htm"};
	private String characterParm= "|,&amp;,;,$,%,',&quot;,\\',\\&quot;,\\,&lt;,&gt;,(,),+,CR,LF,BS";
	private String[] characterParmLiStrings=null;
	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);
    public static boolean isContains(String container, String[] reqlist) {
        boolean result = false;
        for (int i = 0; i < reqlist.length; i++) {
            if (container.indexOf(reqlist[i]) != -1) {
                return true;
            }
        }
        return result;
    }
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		characterParmLiStrings = characterParm.split(",");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String reqUrl = req.getRequestURI();
		//logger.info(reqUrl);
		//if(isContains(reqUrl,characterParmLiStrings))
		//{
			//resp.sendRedirect(req.getContextPath()+"/LoginAction");
		//}
		if(!isContains(reqUrl,reqList))
		{
			chain.doFilter(request, response);
		}
		else {
			HttpSession session = req.getSession();
			//did not login
			if(session.getAttribute("user")==null)
			{
				resp.sendRedirect(req.getContextPath()+"/LoginAction");
			}
			else {
				chain.doFilter(request, response);
			}
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
