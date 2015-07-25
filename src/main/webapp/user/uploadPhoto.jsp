<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传照片</title>
</head>
<body>
<!-- JS检查文件名 -->
${message }
<p>请上传照片，照片尺寸要求为114×156，JPG/JPEG格式，300KB以内大小。</p>
<form action="uploadPhotoCheck" method="post" enctype="multipart/form-data">  
<input type="file" name="photo" /> 
<input type="submit" value="Submit" />
</form>  
</body>
</html>