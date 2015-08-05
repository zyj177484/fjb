<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="css/main.css" type=text/css rel=stylesheet>
<title>找回密码</title>
<style type="text/css">
<!--
body {
	background-image: url(images/mainbgimg.gif);
}
-->
</style></head>
<script language="javascript">
<!--
function form1_onsubmit() {
if (document.form1.idcard.value=="")
	{
	alert("身份证号不能为空。")
	document.form1.idcard.focus()
	return false
	}

if (document.form1.examineename.value=="")
	{
	alert("姓名不能为空。")
	document.form1.examineename.focus()
	return false
	}

if (document.form1.newpwd.value=="")
	{
	alert("密码不能为空。")
	document.form1.newpwd.focus()
	return false
	}

if (document.form1.newpwd2.value=="")
	{
	alert("密码确认不能为空。")
	document.form1.newpwd2.focus()
	return false
	}

if (document.form1.newpwd.value!=document.form1.newpwd2.value)
	{
	alert("2次输入的密码不相同。")
	document.form1.newpwd.focus()
	return false
	}
}
// --></script>

<body>

 <!--<input class="go-wenbenkuang" name="imageField" type="submit" value=" 下一步 " onFocus="this.blur()">-->
<form method="POST" name="form1" language="javascript" onSubmit="return form1_onsubmit()" action="forgetmm.asp?ml=mlma">
                    <table border="0" cellpadding="10" cellspacing="1" width="400" style="border-collapse: collapse" align="center">
                      <TR>
                        <TD height=1></TD>
                      </TR>
                      <TR>
                        <TD height=30 align=center bgcolor="#FFCE08">请正确填写您的身份证号码、姓名及新密码</TD>
                      </TR>
                      <tr>
                        <td height="100"><br />
                          <table border="0" cellpadding="0" width="240" cellspacing="0">
                            <tr>
                              <!--<td width="100%" height="22">忘记密码？请在这里添入您的身份证号码！ </td>-->
							  <!--<td colSpan=2 width="100%" height="22"><b>请正确填写您的身份证号码、姓名及新密码</b></td>-->
                            </tr>
                            <tr>
                              <td width="100">身份证：</td>
							  <td width="100"><input name="idcard" type="text" class="wenbenkuang" size="18" maxlength="18"></td>
                            </tr>
							<tr>
							  <td width="100">姓名：</td>
							  <td width="100"><input name="examineename" type="text" class="wenbenkuang"></td>
                            </tr>
							<tr>
							  <td width="100">新密码：</td>
							  <td width="100"><input name="newpwd" type="password" class="wenbenkuang"></td>
                            </tr>
							<tr>
							  <td width="100">确认新密码：</td>
							  <td width="100"><input name="newpwd2" type="password" class="wenbenkuang"></td>
                            </tr>
							<tr>
                              <td width="100%" colSpan=2 align="center"><input align="center" class="go-wenbenkuang" name="imageField" type="submit" value="确定" onFocus="this.blur()">
                              </td>
                            </tr>
                          </table>
                        <p align="center"><a href="javascript:window.close()" class="1">关闭窗口</a></p></td>
                      </tr>
  </table>
</form>

</body>
</html>

