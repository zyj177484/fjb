<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

说明：通过select选择可选考试科目。通过api拿到该科目下的所有考场。
点击添加考试，将examinId存入session。在之后进入本页可以看到已经选择的考试。
在点击付费（尚未完成）后，将正式保留座位20分钟，并生成订单要求20分钟内付款，否则取消座位。

<c:if test="${!empty examineList}" >
已选择的考试:
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
(付费尚未完成，请不要点击)点击付费后，将为您保留座位20分钟。20分钟内付费未完成，自动取消本次考试。<br/>
<a href="user/pay?type=practice">考试付费</a>
<hr/>
</c:if>

<form action="signUpCheck" method="post">  
选择科目:<select name="subject">
<c:forEach items="${subjectList}" var="subjectEntity">  
        <option value="<c:out value="${subjectEntity.subjectId}"></c:out>" ><c:out value="${subjectEntity.subject}"></c:out></option>
</c:forEach><br/>
</select>
通过API获取该考试科目下开设的考试（尚未开考的）
http://localhost:8080/fjb/getToStartExamineBySubjectId?subjectId=1
[{"startTimeString":"2015-09-05 13:00:00","endTimeString":"2015-09-05 14:00:00","subject":"测试科目","room":"测试考场","examineDistinct":"测试考点","startTime":1441429200,"endTime":1441432800,"signUp":0,"maxNum":20,"examineId":1,"subjectId":1,"roomId":2},
{"startTimeString":"2015-09-05 13:00:00","endTimeString":"2015-09-05 14:00:00","subject":"测试科目","room":"测试考场2","examineDistinct":"测试考点","startTime":1441429200,"endTime":1441432800,"signUp":0,"maxNum":20,"examineId":2,"subjectId":1,"roomId":3}]
<hr/>
开始时间 结束时间 科目 考区 考场 已报人数 考场大小 <br>
2015-09-05 13:00:00 2015-09-05 14:00:00 测试科目 测试考点 测试考场 0 20<input type="radio" value="1" name="examineId"> <br>
2015-09-05 13:00:00 2015-09-05 14:00:00 测试科目 测试考点 测试考场2 0 20<input type="radio" value="2" name="examineId"> <br>
<input type="submit" value="添加考试" id="submit" />
</form> 

</body>
</html>