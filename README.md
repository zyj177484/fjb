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

maven-> update project



##########
hacklu test git push
