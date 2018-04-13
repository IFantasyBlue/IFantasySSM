<%@ page language="java" import="java.util.*,com.cn.ssm.entity.*" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>imformation</title>  
  </head>  


  <body>  
    <h1><%=request.getAttribute("info") %>...</h1>
    <%List<User> list=(List<User>)request.getAttribute("list"); %>
    <%=list.get(0).getUsername() %><%=list.get(0).getPower() %> VS <%=list.get(1).getUsername() %><%=list.get(1).getPower() %>
    
   
  </body>  
</html> 