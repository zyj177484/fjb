<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>反假货币培训系统</title>
		<link rel="stylesheet" href="./css/style.css" type="text/css">
		<link rel="stylesheet" href="./css/ShowDialog.css" type="text/css">
		<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>

		<script>
			$(document).ready(function(){
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
						return true;
					$("#rightanswer").show();

				}
				//show next question 
				$("#next_q").click(function(){

					var id=$("#question_no").text();
					id++;
					id = id % 4;
					if(check_answer())
						update_question(id);
				});
				$("#pre_q").click(function(){

					var id=$("#question_no").text();
					id--;
					if(id<0)
						id=3;
					id = id % 4;
					if(check_answer())
						update_question(id);
				});

				//ajax get question by No.
				function update_question(id){
					$.getJSON(id+".txt", function(json){
						<!--alert("json");-->
						var type,question,no,answer,photo_url;
						type = json["type"];
						question = json["question"];
						no = json["no"];
						answer = json["answer"];
						photo_url = json["photo_url"];

						var myHtml="";
						myHtml='<div class="title"><span id="t">'+ question + '</span></div><div class="item">';

						if(photo_url)
						{
							myHtml+='<div class="right" id="question_pic" style="overflow: hidden;"><img class="bigPic" id="bigPic" style="position:relative" src="'+photo_url+'" height=150px; width=200px; alt="点击查看大图"> <div class="bigPic" style="color:#999;cursor:pointer; text-align:left; font-size:14px; height:18px; padding-left:24px; line-height:18px; margin-top:5px ">点击查看大图</div></div>'
						}

						myHtml += '<div style="display:none" id="question_no">'+ no +'</div><div class="left"><ul id="ul_answers">';

						if(type == 1) //1 单选; 2 多选
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
						else if(type == 2){
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
				<a href="#" target="_blank">章节练习</a>
			</li>
			<li>
				<a class="on">模拟考试</a>
			</li>
			<li>
				<a href="#" target="_blank">错题集</a>
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
		<div class="title">
			<span id="t">#2.现金整点业务委托他行代理的金融机构，采取代理行后台清分与记录存储方式的，应当选择_____较为完备的金融机构作为代理行，签订协议，规定冠字号码记录、存储和查询各环节中双方的职责，明确在处理数据丢失或误读时双方应承担的责任。</span>
		</div>
		<div class="item">
			<div class="right" id="question_pic" style="overflow: hidden;">
				<img class="bigPic" id="bigPic" style="position:relative" src="./images/lena.jpg" height=150px; width=200px; alt="点击查看大图"> 
				<div class="bigPic" style="color:#999;cursor:pointer; text-align:left; font-size:14px; height:18px; padding-left:24px; line-height:18px; margin-top:5px " onclick="./images/lena.jpg">点击查看大图</div>
			</div>

			<div style="display:none" id="question_no">2</div>
			<div class="left">
				<ul id="ul_answers">
					<li><input class="user_answer" type="radio" value="A" name="answer" id="answer1"><label for="answer1">A：财务结算制度</label></li>
					<li><input class="user_answer" type="radio" value="B" name="answer" id="answer2"><label for="answer2">B：内部控制制度</label></li>
					<li><input class="user_answer" type="radio" value="C" name="answer" id="answer3"><label for="answer3">C：内冠字号码记、存储条件</label></li>

				</ul>
				<span id="rightanswer" style="display: none" text-align: left; width: 100%;"><span id="question_answer" style="width:auto; display:inline-block; text-align:center; background:#f00" >您答错了！正确答案是：B</span></span>
			</div>

		</div>
	</div>
	<div class="foothidder"></div>
	<div class="tieba">
		<a href="javascript:void(0)" class="inp"  id="next_q" title="按键盘 → 进入下一题">下一题</a>
		<a href="javascript:void(0)" class="inp"  id="pre_q" title="按键盘 ← 进入上一题">上一题</a>
		<a href="javascript:void(0)" class="inp"  id="mark_q" title="按键盘 ← 进入上一题">标记该题</a>


	</div>
</div>			<div class="userinfo">
	<div class="left">
		答对：<span id="TrueNum">1</span> 题&nbsp;&nbsp; 答错：<span id="FalseNum">0</span> 题&nbsp;&nbsp;
		正确率：<span id="TruePre">100.00%</span>
	</div>
	<div class="right">
	</div>
	<div class="right" style="margin-left:34px">
	</div>
	
	<div class="rightr">
		共 <span id="quallcount">1229</span> 题  &nbsp;&nbsp; 转到
		<input type="text" id="tbtestindex" name="tbtestindex" onkeyup="userkeyup()" onkeydown="userkeydown()" onblur="userblur()" class="uan">
		题
	</div>
</div>			<div class="bottombar">
	</div>
 
		<div style="width:760px;height:90px ;margin:10px auto">
	
</body>
</html>