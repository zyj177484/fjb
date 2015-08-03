set eclipse maven version 3.2.5
new maven-archetypes-webapp
groupId: com.jeff
Artifact: fjb

.setting文件夹，修改其中的org.eclipse.wst.common.project.facet.core.xml
<?xml version="1.0" encoding="UTF-8"?>
<faceted-project>
<fixed facet="wst.jsdt.web"/>
<installed facet="java" version="1.7"/>
<installed facet="jst.web" version="3.0"/>
<installed facet="wst.jsdt.web" version="1.0"/>
</faceted-project>

build path: remove src/main/java and src/test/java

add new source folder: src/main/java

双击每个文件夹的Output folder，选择路径。

src/main/java，src/main/resources，选择target/classes;

Library-> add library-> server runtime -> tomcat 7

项目点击右键 点击 Properties 选择Deployment Assembly 再点击右边的Add按钮 选择Java Build Path Entries后点击Next按钮 然后选择你的Maven Dependencies

maven-> update project

getQuestion说明：
getQuestion?no=1 表示拿取本轮练习的第一题，返回结果type 0->4
表的主键由id和type组成。每次返回结果包括id及type。
type 0:表示出错，请弹出提示信息message，并跳转到view页面
{
"type":"0",
"message":"用户从其他处登录",
"view":"index"
}

type 1:单选题
{
"type":"1",
"question":"#2现金整点业务委托他行代理的金融机构，采取代理行后台清分与记录存储方式的，应当选择_____较为完备的金融机构作为代理行，签订协议，规定冠字号码记录、存储和查询各环节中双方的职责，明确在处理数据丢失或误读时双方应承担的责任。",
"no":"2",
"id":"10",
"answer":"B",
"photo_url":"images/lena.jpg",
"A":"财务结算制度",
"B":"内部控制制度",
"C":"内冠字号码记、存储条件"
}

type 2:多选题
{
"type":"2",
"question":"#0.金融机构柜台如使用通过两次进",
"no":"0",
"id":"11"
"answer":"BC",
"photo_url":"",
"A":"一种",
"B":"两种",
"C":"三种"
}

type 3:判断题
{
"type":"3",
"question":"机械清分指通过自动化清分机具进行现金清分的处理过程。",
"no":"0",
"id":"12",
"answer":"A",
"A":"正确",
"B":"错误"
}

type 4:案例题，类似多选题

getQuestionPhoto说明：输入参数为id及type
getQuestion?id=12&type=3
jsp页面中样例<img src="getQuestion?id=12&type=3" />
##########
hacklu test git push
hacklu test git push

