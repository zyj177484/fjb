<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World</title>
</head>
<body>
下面是从服务器传过来所有可用的内容，message是提示信息，例如从用户强行输入要去进行练习却没付费，message会给出提示信息。诸如此类。其他是用于展示的信息。
<br />${message}

<br />身份证：${user.id}
<br />姓名：${user.username}
<br />角色：${user.role}
<br />总行：${user.zonghang }
<br />分行：${user.fenhang }
<br />支行：${user.zhihang }
<br />分理处：${user.fenlichu }
<br />性别：${user.sex }
<br />手机：${user.mobile }
<br />邮箱：${user.mail }

<img src="showPhoto" />
如果需要进行判断之后再显示，请按如下格式。
<c:if test="${user.practice == 1}" >
<br/><a href="practice">开始新的练习题</a>
</c:if>

</body>
</html>