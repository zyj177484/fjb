<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="signUpCheck" method="post">  
选择科目:<select name="subject">
<c:forEach items="${subjectList}" var="subjectEntity">  
        <option value="<c:out value="${subjectEntity.subject}"></c:out>" ><c:out value="${subjectEntity.subject}"></c:out></option>
</c:forEach><br/>
<input type="radio" value="工人" name="myrad">
</select>
<input type="submit" value="确认付款" id="submit" />
</form> 

</body>
</html>