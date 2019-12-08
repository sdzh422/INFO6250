<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>MyPackages</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href='http://fonts.googleapis.com/css?family=Arimo:400,700' rel='stylesheet' type='text/css'>
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="https://cdn.bootcss.com/jquery/1.2.3/jquery.min.js"></script>
		<script src="<c:url value="/resources/js/skel.min.js"/>"></script>
		<script src="<c:url value="/resources/js/skel-panels.min.js"/>"></script>
		<script src="<c:url value="/resources/js/init.js"/>"></script>
			<link rel="stylesheet" href="/resources/css/skel-noscript.css"/>
			<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
			<link rel="stylesheet" href="<c:url value="/resources/css/style-desktop.css" />">
		
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
	</head>
	<body class="no-sidebar">

		<!-- Header -->
		<div id="header">
			<div class="container"> 
				
				<!-- Logo -->
				<div id="logo">
					<h1><a href="#">Home</a></h1>
					<span>Demo</span>
				</div>
				
				<!-- Nav -->
				<nav id="nav">
					<ul>
						<li><a href="myHome.htm">Home</a></li>
						<li class="active"><a href="myPackages.htm">Packages</a></li>
						<li><a href="myAccount.htm">Account</a></li>
						<li><a href="logOutAction.htm">Logout</a></li>
					</ul>
				</nav>
			</div>
		</div>

		<!-- Main -->
		<div id="main">
			<div class="container">
				<div class="row"> 
					
					<!-- Content -->
					<div id="content" class="12u skel-cell-important">
						<section>
							<header>
								<h2>My Packages</h2>
							</header>
							<a href="#" class="image full"><img src="resources/images/pic07.jpg" alt="" /></a>
							<p>
								There is no record of your package information for the time being.
							</p>
						</section>
					</div>
					
				</div>
			</div>
		</div>

		<!-- Footer -->
		<div id="featured">
			<div class="container">
				<div class="row">
					<div class="4u">
						<h2>Aenean elementum facilisis</h2>
						<a href="#" class="image full"><img src="resources/images/pic01.jpg" alt="" /></a>
						<p>Nullam non wisi a sem semper eleifend. Donec mattis libero eget urna. Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum. Quisque dictum. Pellentesque viverra vulputate enim.</p>
						<p><a href="#" class="button">More Details</a></p>
					</div>
					<div class="4u">
						<h2>Fusce ultrices fringilla</h2>
						<a href="#" class="image full"><img src="resources/images/pic02.jpg" alt="" /></a>
						<p>Nullam non wisi a sem semper eleifend. Donec mattis libero eget urna. Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum. Quisque dictum. Pellentesque viverra vulputate enim.</p>
						<p><a href="#" class="button">More Details</a></p>
					</div>
					<div class="4u">
						<h2>Etiam rhoncus volutpat erat</h2>
						<a href="#" class="image full"><img src="resources/images/pic03.jpg" alt="" /></a>
						<p>Nullam non wisi a sem semper eleifend. Donec mattis libero eget urna. Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum. Quisque dictum. Pellentesque viverra vulputate enim.</p>
						<p><a href="#" class="button">More Details</a></p>
					</div>
				</div>
			</div>
		</div>


		<!-- Footer -->
		

		<!-- Copyright -->

		

</body>
</html>