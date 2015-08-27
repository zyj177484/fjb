<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
本页面的action 未完成
<form action="addExamineCheck" method="post">
添加考试:<br/>
*科目名称:<select name="subject">
   <c:forEach items="${subjectList}" var="subjectEntity">  
        <option value="<c:out value="${subjectEntity.subject}"></c:out>" ><c:out value="${subjectEntity.subject}, 考试结果公布日期:${subjectEntity.resultTimeString} }"> </c:out></option>
   </c:forEach>
</select><br/>
*考区名称:<select name="distinct">
   <c:forEach items="${distinctList}" var="distinctEntity">  
        <option value="<c:out value="${distinctEntity.name}"></c:out>" ><c:out value="${distinctEntity.name}"></c:out></option>
   </c:forEach>
</select><br/>
根据api获取对应考区的所有的考场(todo):
*请选择考场：<input name="room" type="checkbox" value="测试考场" />测试考场<br/>
*考试日期(日期格式YYYY-MM-DD，例如2015-09-05):<input name="date" type="text" /><br/>
*开始时间(时间格式HH:mm:ss，例如13:05:00):<input name="startTime" type="text" /><br/>
*结束时间(时间格式HH:mm:ss，例如13:05:00):<input name="endTime" type="text" /><br/>
<input type="submit" value="添加" id="submit" /><br/>
</form>
</body>
</html>