<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html class="no-js" lang="en"> <!--<![endif]-->

<head>

	<!--- Basic Page Needs
   ================================================== -->
   <meta charset="utf-8">
	<title>Tracking</title>
   <meta name="description" content="">  
   <meta name="author" content="">

   <!-- Mobile Specific Metas
   ================================================== -->
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

   <!-- CSS
   ================================================== -->
   <link rel="stylesheet" href="<c:url value="/resources/css/base.css" />" >
   <link rel="stylesheet" href="<c:url value="/resources/css/vendor.css"/>">
   <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">   

   <!-- Modernizr
   =================================================== -->
   <!-- script src="js/modernizr.js"></script> -->

   <!-- Favicons
   =================================================== -->
   <!-- <link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.png" >-->
    
</head>
    
<body>

   <!--[if lt IE 9]>
   	<p class="browserupgrade">You are using an <strong>outdated</strong> browser. 
   	Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
   <![endif]-->   

   <!-- content-wrap -->
   <div id="content-wrap">

      <!-- main  -->
      <main class="row">

            <header class="site-header">
               <div class="logo">
               	<a href="index.html">Tracking</a>
               </div> 
            </header>

            <div id="main-content" class="twelve columns">

               <h1>Trakcing Result</h1>

               <p align="left"> <b>Tracking number:</b>${packageinfo.trackingNumber}
               </p>

               <hr >
               <p align="left"> <b>Status </b><br></p>
               <div id="main-content" class="status">
					<p>${packageinfo.status}</p>
               </div>                  

               <!-- MailChimp Signup Form -->
               <div id="mc-signup">

                  <form id="mc-form" class="group" action="TrackConfirmAction" method="post">
                          
               		<input type="text" value="" name="tracking_num" id="mc-trackingnum" placeholder="Tracking Number" required>                                                           
                     <input type="submit" value="Track" name="subscribe" class="button">
                     <label for="mc-trackingnum" class="subscribe-message"></label>
                       
                  </form>

               </div> <!-- /sign-up form -->

            </div><!-- /main-content form -->

            <div class="modal-toggles">
               <ul>
                    <li class="about-us"><a href="${contextPath}/tracking/LoginAction">Log in</a></li>
                    <li class="location"><a href="${contextPath}/tracking/RegistrationAction.htm">Sign up</a></li>
               </ul>
            </div><!-- /modal-toggles --> 	
            
      </main>	      

   </div><!-- /content-wrap --> 
    	



 	<!-- footer
   =================================================== -->

   
        
</body>
</html>
