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
<br/>提示：${message }<br />
</c:if>

可设置一个点选按钮，给用户选择每次要添加考区或者考场。
<form action="addExamineRoomCheck/distinct" method="post">
添加考区:<br/>
*考区名称:<input name="name" type="text" /><br/>
*地址:<input name="address" type="text" /><br/>
<input type="submit" value="添加" id="submit" /><br/>
</form>

<form action="addExamineRoomCheck/room" method="post">
添加考场:<br/>
*考区名称:<select name="distinct">
   <c:forEach items="${distinctList}" var="distinctEntity">  
        <option value="<c:out value="${distinctEntity.name}"></c:out>" ><c:out value="${distinctEntity.name}"></c:out></option>
   </c:forEach>

</select><br/>
*人数:<input name="num" type="text" /><br/>
*教室:<input name="name" type="text" /><br/>
<input type="submit" value="添加" id="submit" /><br/>
</form>


</body>
</html>