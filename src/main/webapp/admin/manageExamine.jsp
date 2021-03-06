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
<hr/>
<c:if test="${!empty examineList}" >
已有考试:<br/>
<table>
<tr><td>科目</td><td>考点</td><td>考场</td><td>开始时间</td><td>结束时间</td><td>报名人数</td><td>考场容纳人数</td></tr>
   <c:forEach items="${examineList}" var="examineEntity">
   <tr>  
        <td><c:out value="${examineEntity.subject}"></c:out></td>
        <td><c:out value="${examineEntity.examineDistinct}"></c:out></td>
        <td><c:out value="${examineEntity.room}"></c:out></td>
        <td><c:out value="${examineEntity.startTimeString}"></c:out></td>
        <td><c:out value="${examineEntity.endTimeString}"></c:out></td>
        <td><c:out value="${examineEntity.signUp}"></c:out></td>
        <td><c:out value="${examineEntity.maxNum}"></c:out></td>
   </tr>
   </c:forEach>
</table>
</c:if>
<hr/>
<form action="addExamineCheck" method="post">
添加考试:<br/>
*科目名称:<select name="subjectId">
   <c:forEach items="${subjectList}" var="subjectEntity">  
        <option value="<c:out value="${subjectEntity.subjectId}"></c:out>" ><c:out value="${subjectEntity.subject}, 考试结果公布日期:${subjectEntity.resultTimeString} }"> </c:out></option>
   </c:forEach>
</select><br/>
*考区名称:<select name="distinct">
   <c:forEach items="${distinctList}" var="distinctEntity">  
        <option value="<c:out value="${distinctEntity.name}"></c:out>" ><c:out value="${distinctEntity.name}"></c:out></option>
   </c:forEach>
</select><br/>
根据api获取对应考区的所有的考场:<br/>
http://localhost:8080/fjb/getRooms?distinct=测试考点<br/>
[{"examineDistinct":"测试考点","name":"测试考场","num":20,"id":2},{"examineDistinct":"测试考点","name":"测试考场2","num":20,"id":3}]<br/>
*请选择考场：<br/>
value为id值，显示给用户name
<input name="roomId" type="checkbox" value="2" />测试考场<br/>
<input name="roomId" type="checkbox" value="3" />测试考场2<br/>
*考试日期(日期格式YYYY-MM-DD，例如2015-09-05):<input name="date" type="text" /><br/>
*开始时间(时间格式HH:mm:ss，例如13:05:00):<input name="startTime" type="text" /><br/>
*结束时间(时间格式HH:mm:ss，例如13:05:00):<input name="endTime" type="text" /><br/>
<input type="submit" value="添加" id="submit" /><br/>
</form>
</body>
</html>