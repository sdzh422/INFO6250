<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Message History:
<c:forEach var= "m" items = "${messageList}">
<c:out value = "${m.message} "/><br>
</c:forEach>
<form:form commandName="message" method="POST">
<form:input type="text" id="message"
							path="message" placeholder="Message" />
<form:input type="hidden" id="message"	path="fromuser" value="${sessionScope.username}" />
<input type="submit" name="submit" class="btn btn-success" value="Send" />								
</form:form>
</body>
</html>