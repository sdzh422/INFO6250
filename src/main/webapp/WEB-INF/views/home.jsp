<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<form action="TrackConfirmAction" method="post">
  <input type="search" name="tracking_num" placeholder="Sample Number 9400 1000 0000 0000 0000 00" />
  <input type="submit" value="Track" />
</form>
<a href="RegistrationAction.htm">Sign up:</a>
<p>
<form action="LoginAction" method="post">
username:<input type="text" name="username" required>
password:<input type="password" name="password" required>
<input type ="submit" name = "submit" value="Sign In"></p>
</form>


</body>
</html>
