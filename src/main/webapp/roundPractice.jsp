<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>反假货币培训系统</title>
		<link rel="stylesheet" href="./css/style.css" type="text/css">
		<link rel="stylesheet" href="./css/ShowDialog.css" type="text/css">
		<%--<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>--%>
		<script type="text/javascript" src="./js/jquery-1.4.min.js"></script>

		<script>
			$(document).ready(function(){

				var const_MaxQuestion = 10;
				$("#quallcount").html(const_MaxQuestion);

				update_question(1);

				function start_new()
				{
					location.href = "roundPractice";

				}

				$("#go_go").click(function(){
					var g_no = $("#goto").attr("value");
					if(!g_no)
						return;
					if(g_no<1 || g_no > const_MaxQuestion)
						{
							alert("输入的题号非法，只支持1-"+const_MaxQuestion+"题！");
							$("#goto").select();
							return;
						}

						update_question(g_no);

				});

				$("#new_start").click(function(){

					var r=confirm("重新开始一轮测试？");
					if (r==true)
						{
							start_new();
							return;
						}
					else
					{
						return;
					}

				});

				$(".bigPic").live("click",function(){   //点击图片放大效果
					$("#bigPic").animate({
//						left:'-=250px',
//						position:'fixed',
						top:'20%',
						left:'35%',
						opacity:'0.8',
						height:'+=250px',
						width:'+=250px'
					},"fast");
					$("#bigPic").css("position","fixed");
				}); 
				//禁止右键
				    $('body').bind('contextmenu', function() {
					          return false;
						      });
				//禁止复制
				      $('body').bind("selectstart",function(){return false;});

				function check_answer()
				{
					var right_answer,user_answer;
					user_answer = "";
					right_answer = $("#question_answer").text().slice(11);
					$("input[name='answer']:checked").each(function(){ 
						<!--alert($(this).val());-->
						user_answer += $(this).val(); 
					}); 

					if(!user_answer || (user_answer == right_answer))
					{
						if(user_answer == right_answer)
						{
							var v = $("#TrueNum").text();
							v = Number(v);
							v = v+1;
							$("#TrueNum").html(v);
						}
						return true;
					}

					var w = $("#FalseNum").text();
					w = Number(w);
					w = w+1;
					$("#FalseNum").html(w);
					$("#rightanswer").show();

				}
				//show next question 
				$("#next_q").click(function(){

					var result = check_answer();

					if(result != true)
						return;

					var id=$("#question_no").text();
					id++;
					<%--id = id % 4;--%>
					if(id > const_MaxQuestion){
						var r=confirm("当前测试已完成，是否重新开始一轮测试？");
						if (r==true)
							{
								start_new();
								return;
							}
						else
						{
							return;
						}
					}
					update_question(id);
				});
				$("#pre_q").click(function(){

					var result = check_answer();

					if(result != true)
						return;

					var id=$("#question_no").text();
					id--;
					if(id<=0){
						alert("已经是第一题了!");
						return;
						}
					update_question(id);
				});

				//ajax get question by No.
				function update_question(id){
					//$.getJSON(id+".txt", function(json){
					$.getJSON("getQuestion?no="+id, function(json){
						var type,question,no,answer,photo_url,view,id;
						type = json["type"];
						question = json["question"];
						no = json["no"];
						id = json["id"];
						answer = json["answer"];
						photo_url = json["photo_url"];
						view = json["view"];

						if(no==0)
							no=id; //按个getQuestion?id= 方式获取题目时，no为0，此时根据id来显示题号

						var myHtml="";
						myHtml='<div class="title"><span id="t">'+ '第'+ no+'题. '+question + '</span></div><div class="item">';

						if(photo_url)
						{
							myHtml+='<div class="right" id="question_pic" style="overflow: hidden;"><img class="bigPic" id="bigPic" style="position:relative" src="'+photo_url+'" height=150px; width=200px; alt="点击查看大图"> <div class="bigPic" style="color:#999;cursor:pointer; text-align:left; font-size:14px; height:18px; padding-left:24px; line-height:18px; margin-top:5px ">点击查看大图</div></div>'
						}

						myHtml += '<div style="display:none" id="question_no">'+ no +'</div><div class="left"><ul id="ul_answers">';

								if(type == 0)//something error. goback to index.jsp
								{
								<%--alert("用户从其他地方登陆或者登陆超时!");--%>
								alert(json["message"]);
								<%--location.href = "index.jsp";--%>
								location.href = view;

								}

						if(type == 1 || type ==3 ) //1 单选; 2 多选 ;3 判断 ;4案例题，类似多选
						{

							if(json["A"])
								myHtml += '<li><input type="radio" value="A" name="answer" id="answer1"><label for="answer1">A：'+ json["A"] +'</label></li>';
							if(json["B"])
								myHtml += '<li><input type="radio" value="B" name="answer" id="answer2"><label for="answer2">B：'+ json["B"] +'</label></li>';
							if(json["C"])
								myHtml += '<li><input type="radio" value="C" name="answer" id="answer3"><label for="answer3">C：'+ json["C"] +'</label></li>';
							if(json["D"])
								myHtml += '<li><input type="radio" value="D" name="answer" id="answer4"><label for="answer4">D：'+ json["D"] +'</label></li>';
							/////////////
						}
						else if(type == 2 || type ==4 ){
							if(json["A"])
								myHtml += '<li><input type="checkbox"" value="A" name="answer" id="answer1"><label for="answer1">A：'+ json["A"] +'</label></li>';
							if(json["B"])
								myHtml += '<li><input type="checkbox"" value="B" name="answer" id="answer2"><label for="answer2">B：'+ json["B"] +'</label></li>';
							if(json["C"])
								myHtml += '<li><input type="checkbox"" value="C" name="answer" id="answer3"><label for="answer3">C：'+ json["C"] +'</label></li>';
							if(json["D"])
								myHtml += '<li><input type="checkbox"" value="D" name="answer" id="answer4"><label for="answer4">D：'+ json["D"] +'</label></li>';

						}
						myHtml += '</ul>';
						myHtml += '<span id="rightanswer" style="display: none" text-align: left; width: 100%;"><span id="question_answer" style="width:auto; display:inline-block; text-align:center; background:#f00">您答错了！正确答案是：' + answer +'</span></span>';
						myHtml += '</div></div>';
						$("#frm_main").html(myHtml);

				});

		}//update_question()
				//show pre question

			});

		</script>
	</head>
	<body>
		<div style="width:760px;height:40px ;margin:10px auto">
		</div>
		<div class="ksmaindiv">
			<div class="topbar">
				反假货币培训 <span id="sjlxchapters_"></span><span id="sjlxchapters" style="display: none;"></span> 
			</div>
			<div class="top">
	<div class="logo">
		<a href="http://shpbc.com" target="_blank"> <img src="./images/demos_logo.jpg" alt="上海反假货币培训系统" height="47px" width="100px"></a>
	</div>
	<div class="select">
		<div class="selecttop"></div>
		<ul>
			<li>
				<a href="wholePractice" >章节练习</a>
			</li>
			<li>
				<a href="#" class="on">模拟考试</a>
			</li>
			<li>
				<a href="#" target="_blank" style="display:none">错题集</a>
			</li>
		</ul>
	</div>
	<div class="topbarbtns topbarbtnsl">
		<a href="http://shpbc.com/" target="_blank"><b></b>返回</a>
	</div>
		<div class="topbarbtns topbarbtnsr">
		<!--<a href="javascript:void(0)" style="background: none;" id="sjlx_setting">[ 设置 ]</a>-->
		</div>
	</div>			<div class="hidder"></div>
			<div class="examone" id="main_M">
	<div class="loading" id="wartingdiv" style="display: none;">
		<img src="./images/loading.gif">
		加载中……
	</div>
	<div class="ajshow" id="frm_main" style="display: block;">
	</div>
	<div class="foothidder"></div>
	<div class="tieba">
		<a href="javascript:void(0)" class="inp"  id="next_q" title="按键盘 → 进入下一题">下一题</a>
		<a href="javascript:void(0)" class="inp"  id="pre_q" title="按键盘 ← 进入上一题">上一题</a>
		<%--<a href="javascript:void(0)" class="inp"  id="mark_q" >标记该题</a>--%>
		<a href="javascript:void(0)" class="inp"  id="new_start" >重新开始测试</a>


	</div>
</div>			<div class="userinfo">
	<div class="left">
		答对：<span id="TrueNum">0</span> 题&nbsp;&nbsp; 答错：<span id="FalseNum">0</span> 题&nbsp;&nbsp;
	</div>
	<div class="right">
	</div>
	<div class="right" style="margin-left:34px">
	</div>
	
	<div class="rightr">
		共 <span id="quallcount">238</span> 题  &nbsp;&nbsp; 转到
		<input type="text" id="goto" name="tbtestindex" class="uan" value="1">
		题&nbsp;&nbsp;<a href="javascript:void(0)" ><span id="go_go">GO!</span></a>
	</div>
</div>			<div class="bottombar">
	</div>
 
		<div style="width:760px;height:90px ;margin:10px auto">
	
</body>
</html>
