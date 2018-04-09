<%@ page language="java" import="java.util.*,com.cn.ssm.entity.*" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>测试</title>  
  </head>  

  <body>  
    ${user.username}  
    <%List list= (ArrayList)request.getAttribute("list");%>
    <%for(int i=0;i<list.size();i++){ %>
    <%TeamMembers t=(TeamMembers)list.get(i);%>
    <%=t.getPosition() %>
    <%} %>
    <%User_Info user_info = (User_Info)request.getAttribute("user_info"); %>
    <%=user_info.getBelongteam() %>
    <%=user_info.getLevel() %>
    <%=user_info.getUserId() %>
    <%=user_info.getVip() %>
  </body>  
</html> 