<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>学生注册</title>
<link href="../css/sbase.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="../css/sregister.css" rel="stylesheet" type="text/css">
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
		<div class="registerbox">
			<form id="form1" class="form-horizontal" action="sregister.do"
				method="post" onSubmit="return mycheck()">
				<div class="form-group">
					<label for="name" class="col-xs-3 control-label">姓名</label>
					<div class="col-xs-9">
						<input id="name" name="name" type="text"
							class="form-control" placeholder="请输入姓名">
					</div>
				</div>
				<div class="form-group">
					<label for="userName" class="col-xs-3 control-label">学号</label>
					<div class="col-xs-9">
						<input id="userName" name="studentId" type="text"
							class="form-control" placeholder="请输入学号">
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-xs-3 control-label">邮箱</label>
					<div class="col-xs-9">
						<input id="email" name="email" type="email"
							class="form-control" placeholder="请输入邮箱">
					</div>
				</div>
				<div class="form-group">
					<label for="uid" class="col-xs-3 control-label">Uid</label>
					<div class="col-xs-9">
						<input id="uid" name="uid" type="text"
							class="form-control" placeholder="Uid测试">
					</div>
				</div>
				<div class="divider"></div>

				<div class="form-group">
					<p class="col-xs-offset-2 col-xs-7">大工软院就业预测平台</p>
					<div class="col-xs-3">
						<button type="submit" class="btn btn-default">注册</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/sregister.js"></script>
</body>
</html>