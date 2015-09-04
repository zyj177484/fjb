<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
先不要做这个页面，还没完成
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
<hr>
开始时间 结束时间 科目 考区 考场 已报人数 考场大小 <br>
2015-09-05 13:00:00 2015-09-05 14:00:00 测试科目 测试考点 测试考场 0 20<input type="radio" value="1" name="examineId"> <br>

<input type="submit" value="确认付款" id="submit" />
</form> 

</body>
</html>