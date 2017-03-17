<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>大连理工大学软件学院毕业生就业服务系统</title>

<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/base.css" rel="stylesheet">
<link href="../css/infodetails.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style>
</style>
</head>
<body>
	<div class="a_top">
		<div class="a_1949_bg"></div>
		<div class="a_logo">
			<a href="#"><img src="../images/a_logo.png" width="381"
				height="67"></a>
		</div>
	</div>
	<div class="clearfix c_warp_bor">
		<div class="c_warp">
			<div class="titlebar">
				<span>实习生就业服务系统</span>
				<button onclick="window.location='logout.do'" class="btn btn-primary">注销</button>
			</div>
			<div class="c_content">
				<div class="cc_left col-md-2">
					<ul>
						<li class="active"><a href="infolist.do">通知列表</a></li>
						<li><a href="newInform.jsp">新建通知</a></li>
						<li><a href="sum.jsp">数据统计</a></li>
					</ul>
				</div>
				<div  class="cc_right col-md-10">
				<div id="copyDiv">
<style type="text/css">
body {
	background: url(../images/a_bg.png) repeat-x top #fff;
}

.table thead th {
	text-align: center;
}

.a_top {
	width: 1000px;
	margin: 0 auto;
	position: relative;
	height: 128px;
}

.a_logo {
	position: absolute;
	left: 33px;
	top: 38px;
}

.a_1949_bg {
	background: url(../images/a_bg_1.png) no-repeat top;
	position: absolute;
	right: 110px;
	top: 0px;
	width: 336px;
	height: 198px;
	z-index: -1;
}

.c_warp_bor {
	background: url(../images/c_warpbg.png) repeat-y;
	width: 1048px;
	margin: 0 auto;
	border-radius: 10px 10px 0 0;
	margin-bottom: 40px;
}

.c_warp {
	width: 1000px;
	margin: 0 auto;
	background: #fff;
}

.titlebar {
	height: 100px;
	position: relative;
	border-bottom: 2px solid #036eb8;
}

.titlebar>span {
	display: inline-block;
	width: 200px;
	height: 100%;
	position: absolute;
	left: 50%;
	margin-left: -100px;
	text-align: center;
	line-height: 100px;
	font-size: 20px;
	font-weight: 500;
}

.titlebar>button {
	position: absolute;
	right: 40px;
	top: 50%;
	margin-top: -20px;
}

.c_content {
	height: 700px;
	position: relative;
}

.subtitle {
	margin-top: 30px;
	height: 60px;
	padding: 10px 0;
	position: relative;
}

.subtitle>span:first-child {
	display: inline-block;
	height: 40px;
	width: 300px;
	border: 1px solid #036eb8;
	border-radius: 5px;
	text-align: center;
	line-height: 40px;
	position: absolute;
	left: 50%;
	margin-left: -150px;
}

.a_footer {
	width: 100%;
	height: 90px;
	background: #036eb8;
	clear: both;
	overflow: hidden;
}

.a_footer_p {
	width: 1000px;
	margin: 0 auto;
}

.a_footer_p .a_copyright {
	float: left;
	color: #fff;
	line-height: 28px;
	padding-top: 18px;
}

.a_footer_p .a_onsite {
	float: right;
	color: #fff;
	padding-top: 18px;
}

.a_footer_p .a_onsite a {
	color: #fff;
}

.placeholder {
	height: 10px;
	overflow: hidden;
	clear: both
}

.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.table tbody td {
	text-align: center;
}

.table thead th {
	text-align: center;
}

.subtitle-info {
	width: 100%;
	height: 50px;
	position: relative;
}

.subtitle-info .feedback {
	float: left;
	margin-left: 10px;
}

.subtitle-info .feedback a {
	width: 100%;
	height: 100%;
	display: inline-block;
}

.subtitle-info .toexcel {
	float: left;
	margin-left: 10px;
}

.subtitle-info .toexcel a {
	width: 100%;
	height: 100%;
	display: inline-block;
}

.subtitle-info>.subtitle-type, .subtitle-info>.subtitle-time {
	float: right;
	margin-right: 20px;
}

.info {
	width: 100%;
	text-align: center;
	line-height: 25px;
	border: 1px solid #036eb8;
	margin-bottom: 30px;
}

.table {
	width: 100%;
	max-width: 100%;
	margin-bottom: 20px;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	padding: 8px;
	line-height: 1.42857143;
	vertical-align: top;
	border-top: 1px solid #ddd;
}

.table>thead>tr>th {
	vertical-align: bottom;
	border-bottom: 2px solid #ddd;
}

.table>caption+thead>tr:first-child>th, .table>colgroup+thead>tr:first-child>th,
	.table>thead:first-child>tr:first-child>th, .table>caption+thead>tr:first-child>td,
	.table>colgroup+thead>tr:first-child>td, .table>thead:first-child>tr:first-child>td
	{
	border-top: 0;
}

.table>tbody+tbody {
	border-top: 2px solid #ddd;
}

.table .table {
	background-color: #fff;
}
.btn-getcontent {
	float:right;
}

</style>
					<div class="subtitle">
						<span>${message.title }</span>
					</div>
					<div class="subtitle-info">
						<span class="feedback btn-default btn"><a href="infofeedback.do?messageId=${message.messageId }">反馈</a></span> <span
							class="subtitle-time btn-default btn disabled">${message.releaseTime
							}</span> <span class="subtitle-type btn-default btn disabled">类别:${message.type}</span>
					</div>
					<div class="info">${message.content }</div>

					<div class="ms-info-list table-responsive">
						<table class="table table-hover">
							<tr>
								<td>时间:${message.deadline }</td>
								<td>地点:${message.place}</td>
							</tr>
						</table>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>学号</th>
									<th>姓名</th>
									<th>Email</th>
									<th>岗位</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${viewlist}" var="infoview">
									<tr>
										<td>${infoview.studentId }</td>
										<td>${infoview.name }</td>
										<td>${infoview.mail }</td>
										<td>${infoview.job }</td>
										<td>${infoview.note }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					</div>
					<div id="hide" style="display: none;" class="col-sm-8">
									<div class="col-sm-10">
										<label for="clipboard" class="col-sm-3 control-label">剪贴板</label>
										<div class="col-sm-8">
											<textarea id="clipboard" class="form-control" rows="1"></textarea>
										</div>
									</div>
									<div>
										<button type="button" class="btn btn-primary"
											onclick="hideDiv()">隐藏</button>
									</div>
								</div>
								<button type="button" id="copy-dynamic" class="btn btn-primary btn-getcontent"
										>复制通知</button>
				</div>
				
			</div>
		</div>
	</div>


	<div class="a_footer">
		<div class="a_footer_p">
			<div class="a_copyright">
				<p>©版权所有：</p>
			</div>
		</div>
	</div>

	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script language="javascript" type="text/javascript"> 
    function load() 
    { 
        var test= document.getElementById('copyDiv').innerHTML ; 
		test=test.replace('<span class="feedback btn-default btn"><a href="infofeedback.do?messageId=${message.messageId }">反馈</a></span>','');
		var hide = document.getElementById("hide");
	hide.style.display = "";
	var clipboard = document.getElementById("clipboard");
	clipboard.value = test;
	clipboard.select(); // 选择对象
	document.execCommand("Copy"); // 执行浏览器复制命令
	alert("通知已复制到剪贴板，可以直接粘贴！\n\n"+test);
    } 
    $(document).ready(function(){
    	var copyContent= document.getElementById('copyDiv').innerHTML ; 
    	copyContent=copyContent.replace('<span class="feedback btn-default btn"><a href="infofeedback.do?messageId=${message.messageId }">反馈</a></span>','');

    	$('button#copy-dynamic').zclip({
    		path:'../js/ZeroClipboard.swf',
    		copy:function(){return copyContent;}
    	});

    	// The link with ID "copy-dynamic" will copy the current value
    	// of a dynamically changing input with the ID "dynamic"

    });
	

</script>

<script type="text/javascript" src="http://demo.jb51.net/jslib/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.zclip.js"></script>
</body>
</html>