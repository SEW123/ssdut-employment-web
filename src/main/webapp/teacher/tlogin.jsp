<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.recruit.data.StaticData" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>大连理工大学软件学院实习生就业服务系统登录页面</title>

<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/base.css" rel="stylesheet">
<link href="../css/tlogin.css" rel="stylesheet">

<!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->


</head>
<body>
	<div class="a_top">
		<div class="a_1949_bg"></div>
		<div class="a_logo">
			<a href="#"><img src="../images/a_logo.png" width="381"
				height="67"></a>
		</div>
	</div>
	<div class="content">
		<div class="loginbox">
			<form id="form1" class="form-horizontal" role="form"
				action="tlogin.do" method="post"
				onSubmit="return mycheck()">
				<div class="form-group">
					<label for="userName" class="col-sm-2 control-label">账号</label>
					<div class="col-sm-10">
						<input id="userName" name="teacherId" type="text"
							class="form-control" placeholder="请输入管理员账号">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input id="password" name="password" type="password"
							class="form-control" placeholder="请输入密码">
					</div>
				</div>
				<div class="divider"></div>

				<div class="form-group">
					<p class="col-sm-offset-2 col-sm-5">实习生就业服务系统</p>
					<div class="col-sm-offset-2 col-sm-3">
						<button type="submit" class="btn btn-default">登录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/tlogin.js"></script>
</body>
</html>