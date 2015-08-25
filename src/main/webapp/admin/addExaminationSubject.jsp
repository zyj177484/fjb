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

<form action="addExamineSubjectCheck" method="post">
添加科目：<br/>
*科目名称:<input name="subject" type="text" /><br/>
*费用:<input name="charge" type="text" /><br/>
*报考截止日期(日期格式YYYY-MM-DD，例如2015-09-05):<input name="regTime" type="text" /><br/>
*考试结果公布日期(日期格式YYYY-MM-DD，例如2015-09-05):<input name="resultTime" type="text" /><br/>
注意事项:<input name="note" type="text" /><br/>
<input type="submit" value="添加" id="submit" /><br/>
</form>
</body>
</html>