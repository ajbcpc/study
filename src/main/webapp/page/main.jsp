<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/kindeditor/themes/default/default.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js" type="text/javascript"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/js/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#main_tree').tree({    
	    url:'showMenu',
	    onClick:function(node){
	    	if($("#main_tabs").tabs("getTab",node.text)==null){
		    	//alert(node.text);
		    	$('#main_tabs').tabs('add',{
		    		title: node.text,
		    		selected: true,
		    		//content:'<b>adsfadsf</b>'
		    		//只能引进来<body>标签的内容
		    		href:node.attributes.filename,
		    		closable:true
		    	});
			}else{
				$("#main_tabs").tabs("select",node.text);
			}
	    }
	}); 
})
</script>
</head>
<body class="easyui-layout">   
    <div data-options="region:'north',title:'知茗管理系统'" style="height:100px;">
    	<div style="width:400px;height:70px; float:left;font-size:20px; font-weight:bold;line-height: 70px;padding-left:20px;">
    		知茗管理系统
    	</div>
    	<div style="width:200px;height:70px;float:right;line-height: 70px;">
    		您好,${user.name },欢迎登录!
    	</div>
    </div>   
    <div data-options="region:'south',title:'底部声明'" style="height:100px;">
    	<div style="height:70px; line-height: 70px; text-align:center;color:gray">
    		Copyright &copy; 知茗
    	</div>
    </div>   
    <div data-options="region:'west',title:'菜单'" style="width:200px;">
    	<ul id="main_tree"></ul> 
    </div>   
    <div data-options="region:'center',title:'内容'" style="padding:5px;background:#eee;">
    	<div id="main_tabs" class="easyui-tabs" style="width:500px;height:250px;" data-options="fit:true">   
		    <div title="首页" style="padding:20px;">   
		        正文...
		    </div>  
		     
		</div>
    </div>   
</body>
</html>