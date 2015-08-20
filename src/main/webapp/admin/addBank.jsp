<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

判断是否要给提示消息：如插入失败，或者插入成功
<c:if test="${!empty message}" >
<br/>提示：${message }
</c:if>

添加银行
获取银行信息同注册页面相同。
<form action="addBankCheck/zonghang" method="post">
添加总行:<br/>
*总行名称:<input name="name" type="text" /><br/>
*总行编码:<input name="id" type="text" /><br/>
地址:<input name="address" type="text" /><br/>
联系人:<input name="contactPeople" type="text" /><br/>
电话:<input name="phone" type="text" /><br/>
Email:<input name="email" type="text" /><br/>
<input type="submit" value="添加" id="submit" /><br/>
</form>
<br/>
<form action="addBankCheck/fenhang" method="post">
添加分行:<br/>
*总行编码（通过api取出所有的总行名称及id，显示给用户总行名称，传递总行id）:<input name="zonghangid" type="text" /><br/>
*分行名称:<input name="name" type="text" /><br/>
*分行编码:<input name="id" type="text" /><br/>
地址:<input name="address" type="text" /><br/>
联系人:<input name="contactPeople" type="text" /><br/>
电话:<input name="phone" type="text" /><br/>
Email:<input name="email" type="text" /><br/>
<input type="submit" value="添加" id="submit" /><br/>
</form>
<br/>
<form action="addBankCheck/zhihang" method="post">
添加分行:<br/>
*分行编码（通过api取出所有的分行名称及id，显示给用户总行名称，传递总行id）:<input name="fenhangid" type="text" /><br/>
*支行名称:<input name="name" type="text" /><br/>
*支行编码:<input name="id" type="text" /><br/>
地址:<input name="address" type="text" /><br/>
联系人:<input name="contactPeople" type="text" /><br/>
电话:<input name="phone" type="text" /><br/>
Email:<input name="email" type="text" /><br/>
<input type="submit" value="添加" id="submit" />
</form>
</body>
</html>