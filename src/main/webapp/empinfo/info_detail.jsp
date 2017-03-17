<%@ page language="java" import="com.recruit.pojo.SsdutEmpInfo"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="viewport"
	content="width = device-width,initial-scale=1.0,maximum-scale=1.0">
<link rel="shortcut icon" href="未标题-1.ico">
<script src="modernizr-1.7.min.js"></script>
<style>
body, #main ul, #main li, h1 {
	margin: 0;
	padding: 0;
}

body {
	
}

#container {
	font-family: Arial;
	margin: 0 10px;
}

header, footer {
	display: block;
}

#main li {
	list-style: none;
	height: 65px;
	margin-bottom: 0.5em;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	border-radius: 15px;
}

.table1 {
	height: 70px;
	width: 320px;
	border-bottom: 1px solid #fb966e;
}

#main {
	margin-top: 2em;
}

#main li a {
	color: white;
	text-decoration: none;
}

header {
	color: #003366;
}

img {
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	border-radius: 15px;
}

.STYLE2 {
	font-family: "新宋体";
	font-size: 16px;
	font-weight: bold;
}
</style>
<title>新闻详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<body>

	<%
		SsdutEmpInfo empInfo = (SsdutEmpInfo) request.getAttribute("emp_info");
	%>

	<div id="container">

		<div style="width: 100%; height: 5px; background: #0080c0"></div>
		<header align="center">
		<h1><%=empInfo.getTitle()%></h1>
		</header>
		<div style="width: 100%; height: 5px; background: #0080c0"></div>
		<nav id="main">
		<div class="STYLE2"
			style="text-indent: 2em; list-style: none; width: 320px; line-height: 1.8">
			<%=empInfo.getContent()%>
		</div>
		</nav>

	</div>


</body>
</html>
