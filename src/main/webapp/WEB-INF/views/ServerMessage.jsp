<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8"> 
<title>ServerMessageManagement</title> 
<script src="https://code.jquery.com/jquery-3.4.0.min.js">
</script>
<script>
$(document).ready(function(){

	$("body").keydown(function() {
	      if (event.keyCode == "13") {//keyCode=13 enter
	          $('#submitBtn').click();
	          window.event.returnValue = false; 
	      }
	  });
  
  function onMessage(event) { $("#messageBoard").append("<label>" + event.data + "</label>").append("</br>");
  	$("#messageBoard").scrollTop( $("#messageBoard")[0].scrollHeight); }
  
  function onError(event) {
      alert(event.data);
  }
 
  $("#submitBtn").click(function() {
	  $.ajax({
    	  uri:"${contextPath}/tracking/ServiceSupport.htm",
    	  type:"POST",
    	  enctype:"application/x-www-form-urlencoded",
          data:$("#messageForm").serialize()
      })
      $("#messageBoard").append("<label>" + $("#message_text").val() + "</label>").append("</br>");
	  $("#messageBoard").scrollTop( $("#messageBoard")[0].scrollHeight);
  })
  
});
</script>
</head>
<body>
<p> Message List</p>
<div id="messageBoard">
	<c:forEach var="usermsg" items="${usermessageList}">
	<label>UserId: ${usermsg.uid}</label><br>
		<c:forEach var="message" items="${usermsg.messageLiSt}">
			<label>${message}</label><br>
		</c:forEach>
	</c:forEach>
</div>
<p>
<form id="messageForm" >
User id:
<input type="text" name="message_touserid"  /><br>
Message:
<input type="text" name="message_text" id="message_text" /><br>
<input type="button" id="submitBtn" value="Send"/>
</form>
</p>
</body>
</html>