<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上海地区反假币上岗证书考试系统</title>
<link href="css/main.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>

<script> 
	$(document).ready(function(){
			$(".forget_pass").click(function(){
				window.open('forget.jsp','找回密码','toolbars=no,location=no,scrollbars=no,status=no,resizable=no,width=600,height=210')
			});

			$(".login").click(function(){
				var idcard = $("#idcard").val();
				var pwd = $("#pwd").val();
				var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  

				if(reg.test(idcard) === false)  
				{  
					alert("身份证输入不合法!");  
					return  false;  
				}  

				if(!idcard)
				{  
					alert("身份证不可为空!");
					return  false;  
				}  

				if(!pwd)
				{  
					alert("密码不可为空!");
					return  false;  
				}  
				$("#login_form").submit();

			});

		})
</script>
</head>
<body style="background-image: url(images/mainbgimg.gif);background-repeat:repeat-x;margin-top: 0px;">
<c:if test="${message != null}" >
<br/>${message }</a>
</c:if>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="10">&nbsp;</td>
  </tr>
</table>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="800" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div align="center"><STRONG><FONT face=黑体 color=#fde404 size=6>反假货币上岗资格证书考试系统</FONT></STRONG></div></td>
        </tr>
      </table>
      <table width="800" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="30">
<p><strong><font size=5>请阅读报名须知</font></strong></p>
<p><font size=4>准考证请去总行网站下载，考试当天请携带：<font 
color=#ff0000><strong>总行应知准考证，身份证，</font>迟到缺考没有补考。</font></strong> 
</p>
          </td>
        </tr>
      </table>
	  <form id="login_form" name="login_form" method="post" action="loginCheck">
      <table width="800" border="0" cellspacing="0" cellpadding="3">
        <tr>
          <td width="264" valign="middle"><div align="right"><span class="STYLE4">身份证号</span>&nbsp;</div></td>
          <td width="133"><input name="id" type="text" id="idcard" size="18" /></td>
        </tr>
        <tr>
          <td width="264" valign="middle"><div align="right"><span class="STYLE4">密码</span>&nbsp;</div></td>
          <td width="133"><input name="password" type="password" id="pwd" size="18" /></td>
          <td width="345" class="login">
            <input type="image" class="login"  src="images/button_jr.gif" value="进入考试系统" /></td>
        </tr>
	<tr>
	<td width="284"></td>
	<td height="" width="146" class="register">
		<a href="register.jsp"><img class="register" src="images/button_zc.gif" width="56" height="24" border="0" /></a>
	</td>
	<td class="forget_pass">
		<img class="forget_pass" src="images/button_wjmm.gif" width="100" height="24">
		
	</td>
	<td></td>
	</tr>
      </table>
	  </form>
	  
<table border=0 cellSpacing=0 cellPadding=0 width=800>
  <tbody>
  <tr>
    <td>
      <div align=center><font color=#ff0000 
      size=4><strong>重要提示</strong></font></div></td></tr>
  <tr>
    <td>
      <p><font 
      size=4>1.每次报名请记录订单号备查：工行付款订单格式为15位，查询工行网银—&gt;电子回单—&gt;查询电子回单—&gt;订单明细查询：商户名称“上海银港德莫斯培训”中查询到。<br>&nbsp; 
      建行付款订单格式为报名考生身份证加字母sunroo加6位系统生成数字，查询建行网银—&gt;客户服务—&gt;日志查询—&gt;选择日期—&gt;操作类型“网上支付”查询，在交易内容中有订单号（注意格式）。<br>2.出现成功付款却没有生成准考证说明报名没有成功但是缴费成功，建议考生换游览器或电脑重新报名。每次报名记录订单号备查。<br>3.报名未成功多缴费的考生请根据要求填写退费模板<a 
      href="tuifeimoban.xls">(点击此处下载)</a>发送到shpbcvip@ygdemos.com，邮件标题注明退费。模板内容请认真填写，留下联系方式。订单查询请参考重要提示的第一项说明。<br>考试中心电话：65569524</p></font></td></tr></tbody></table>

      <table width="760" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table></td>
  </tr>
</table>

</body>
</html>

