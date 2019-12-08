<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8"> 
<title>ServiceSupport</title> 
<script src="https://code.jquery.com/jquery-3.4.0.min.js">
</script>
<script>
$(document).ready(function(){


  
  websocket = new WebSocket("ws://localhost:8080/tracking/websocket");
  websocket.onmessage = function(event) {onMessage(event);};
  websocket.onerror = function(event) {onError(event);};
  
  function onMessage(event) { $("#messageBoard").append("<label>" + event.data + "</label>").append("</br>");
  	$("#messageBoard").scrollTop( $("#messageBoard")[0].scrollHeight); }
  
  function onError(event) {
      alert(event.data);
  }

  $("body").keydown(function() {
      if (event.keyCode == "13") {//keyCode=13 enter
          $('#submitBtn').click();
          window.event.returnValue = false; 
      }
  });
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
<div id="messageBoard"></div>
<p>
<form id="messageForm" >
<input type="text" name="message_text" id="message_text" />
<input type="button" id="submitBtn" value="Send"/>
</form>
</p>
</body>
</html>