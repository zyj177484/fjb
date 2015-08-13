<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
<title>上传照片</title>

<script>
$(document).ready(function(){

	var imageOk=false;

        function checkImgType(ths) {
            if (ths.value == "") {
                alert("请上传图片");
                return false;
            } else {
                if (!/\.(jpg|JPG)$/.test(ths.value)) {
                    alert("图片类型必须是jpg");
                    ths.value = "";
                    return false;
                }

                else {
                    while (true) {
                        if (ths.files[0].size > 50 * 1024) {
                            alert("图片不大于50KB。");
                            return false;
                        }
                        break;

                    }
                }
            }
            return true;
        }

	$("#photo").change(function(){

	    imageOk = false;
	    if(checkImgType(this))
		imageOk = true;
	    //checkImgPX(this,150,210);  //判断图片大小服务器后台做 
	});

	 function checkSubmit() {
            var result = false;
            if (!imageOk)   
               alert('请上传照片');
	    else
                result = true;
            return result;
    }
	$("#submit").click(function(){

		return checkSubmit();
	});

});
</script>

</head>
<body>
<!-- JS检查文件名 -->
${message }
<center>
	<h3>请上传照片</h3>

<form action="uploadPhotoCheck" method="post" enctype="multipart/form-data">  
<input type="file" name="photo" id="photo" /> 
<input type="submit" value="上传" id="submit" />
</form>  
</center>
<br>
<br>
<div align:left>
	<h4>照片要求：</h4>
	<h4>1.上传照片必须为考生本人近期1寸正面免冠彩色证件照(用于合格证书)；</h4>

	<h4>2.照片尺寸要求为2.5cm*3.5cm，格式为jpg，文件大小不超过50kb,清晰度为150分辨率（dpi）以上(照片像素为150像素*210像素，则分辨率为150dpi)；</h4>

	<h4>3.头部占照片尺寸的2/3，白色背景；无边框，无明显畸变；务必保证照片清晰、可辨认。 其他如生活照、视频捕捉、摄像头所摄等照片或扫描仪等处理的照片请勿上传。</h4>
<h4>示例图片:</h4><img src="../images/example.jpg">
</div>
</body>
</html>
