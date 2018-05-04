<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>package</title>
</head>
<body>
<h1>package</h1>
<strong>${message}</strong><br/><br/>
${ goods }<br/><br/>
${ records }<br/><br/>
<c:forEach items="${list}" var="record" varStatus="vs">  
            <tr>  
                 <td align = "center">${record.packageId}</td>  
                 <td align = "center">${record.userId}</td>  
                 <td align = "center">${record.goodsId}</td>  
                 <td align = "center">${record.goodsNum}</td>  
          <br/>
             </tr>  
    </c:forEach>  
</body>
<a href="reOrder">重新排序</a>
<a href="apply">使用goods_id=1</a>
</html>