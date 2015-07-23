<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>首页</h2>
${message }
<form action="loginCheck" method="post">  
    身份证:<input type="text" name="id" />  
    <p>  
    密码:<input type="password" name="password"/>  
    <p>  
    <input type="submit" value="登录" /> <a href="register.jsp">注册</a>
</form>
</body>
</html>
