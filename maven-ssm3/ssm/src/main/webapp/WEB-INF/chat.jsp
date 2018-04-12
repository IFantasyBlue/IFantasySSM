<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>chat</title>
</head>
<body>
	<h1>聊天</h1>
	${ world_records }<br/><hr><br/>
	${ friend_records }<br/><hr><br/>
     <c:forEach items="${list}" var="chat" varStatus="vs">  
            <tr>  
                 <td align = "center">${chat.id}</td>  
                 <td align = "center">${chat.sendId}</td>  
                 <td align = "center">${chat.receiveId}</td>  
                 <td align = "center">${chat.time}</td>  
                 <td align = "center">${chat.content}</td>
          <br/>
             </tr>  
    </c:forEach>  
    <hr>
    <br/><br/>
    <form action="send">
    	<input type="text" name="content">
    	<a href="chat/airing">发送公共聊天</a>
    	<a href="chat/whisper">发送私密聊天</a>
    </form>
</body>
</html>