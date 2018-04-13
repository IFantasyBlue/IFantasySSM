<%@ page language="java" import="java.util.*,com.cn.ssm.entity.*" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>matchingIn</title>  
  </head>  

<script language="javascript" type="text/javascript">
setTimeout(function () { this.location.href = "http://localhost:8080/ssm/matching/MatchingInfo?user_id="+<%=request.getAttribute("user_id") %>+"&user2_id="+<%=request.getAttribute("user2_id") %> }, 1000);
</script> 
  <body>  
    <h1><%=request.getAttribute("info") %></h1>
    <%List<User> list=(List<User>)request.getAttribute("list");
    for(int i=0;i<list.size();i++){
    	
    
    %>
    <%=list.get(i).getUsername() %>
    <%} %>
    
    
  </body>  
</html> 