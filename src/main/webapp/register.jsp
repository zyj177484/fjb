<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${message }
*表示必填
<form action="registerCheck" method="post">  
    *身份证:<input type="text" name="id" />
    <p>  
    *密码:<input type="password" name="password"/>(密码中必须包含字母及数字)  
    <p>
    *确认密码:<input type="password" name="confirmPassword"/>
    <!-- 密码确认由JS做? -->
    <p>
    *姓名:<input type="text" name="username" />
    <p>
    *性别:<input type="radio" name="sex" value="男" checked>男
		<input type="radio" name="sex" value="女">女
    <p>
    *手机号:<input type="text" name="mobile" />
    <p>
    总行:<input type="text" name="zonghang" />
	<p>
    分行:<input type="text" name="fenhang" />
	<p>
    支行:<input type="text" name="zhihang" />
	<p>
	分理处:<input type="text" name="fenlichu" />
	<p>
	<input type="submit" value="注册" />
</form>
</body>
</html>