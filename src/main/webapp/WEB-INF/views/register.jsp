<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Register</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
    <!-- CSS Files -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" />
    <link href="resources/css/now-ui-kit.css?v=1.1.0" rel="stylesheet" />
   
    
    
    
</head>

<body class="login-page sidebar-collapse">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent " color-on-scroll="400">
        <div class="container">
            <div class="dropdown button-dropdown">
                <a href="#pablo" class="dropdown-toggle" id="navbarDropdown" data-toggle="dropdown">
                    <span class="button-bar"></span>
                    <span class="button-bar"></span>
                    <span class="button-bar"></span>
                </a>
            </div>
            <div class="navbar-translate">
                <a class="navbar-brand" href="#" rel="tooltip" data-placement="bottom">
                    Register
                </a>
            </div>
            <div class="collapse navbar-collapse justify-content-end" data-nav-image="resources/images/blurred-image-1.jpg">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/tracking/">Back to Index</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/tracking/ServiceSupport.htm">Have an issue?</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->
    <div class="page-header" filter-color="orange">
        <div class="page-header-image" style="background-image:url(resources/images/header.jpg)"></div>
        <div class="container">
            <div class="col-md-4 content-center">
                <div class="card card-login card-plain">
                    <form:form commandName="user" method="post">
                        <div class="header header-primary text-center">
                            <div class="logo-container">
                                <img src="resources/images/now-logo.png" alt="">
                            </div>
                        </div>
                        
                        <div class="content">
                        	${notUnique}<br>
                            <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons users_circle-08"></i>
                                </span>
                                
                                <form:input id="un" path="username" type="text" class="form-control" placeholder="Username"/>                              
                            </div><form:errors path="username"></form:errors>
                            <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons text_caps-small"></i>
                                </span>
                                <form:input path="password" type="password" placeholder="Password" class="form-control" />     	
                            </div><form:errors path="password"/>
                            <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                </span>
                                <span class="input-group-addon">
                                </span>
                                <form:input path="phone" type="text" placeholder="Phone Number" class="form-control" />                         	
                            </div><form:errors path="phone"/>
                            <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                </span>
                                <span class="input-group-addon">
                                </span>
                                <form:input path="address" type="text" placeholder="Address" class="form-control" />     	
                            </div><form:errors path="address"/>
                            <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                </span>
                                <span class="input-group-addon">
                                </span>
                                <form:input path="email" type="text" placeholder="Email" class="form-control" />       	
                            </div><form:errors path="email"/>
                        </div>
                        	<div class="footer text-center">
                            <input type="submit" name="submit" class="btn btn-primary btn-round btn-lg btn-block" value ="Sign Up"/>
                        	</div>              
                        <div class="pull-left">
                            <h6>
                                <a href="${contextPath}/tracking/RegistrationAction.htm" class="link">Login</a>
                            </h6>
                        </div>
                        <div class="pull-right">
                            <h6>
                                <a href="${contextPath}/tracking/passwordResetAction" class="link">Forget password?</a>
                            </h6>
                        </div>
                     </form:form>
                </div>
            </div>
        </div>
        
    </div>
</body>


</html>