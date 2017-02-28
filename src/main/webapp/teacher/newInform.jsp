<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新建通知</title>

<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/base.css" rel="stylesheet">

<script src="http://libs.useso.com/js/jquery/2.1.0/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="../umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="../umeditor/umeditor.min.js"></script>
<link href="../umeditor/themes/default/css/umeditor.min.css"
	type="text/css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style>
.cc_right .form {
	padding: 20px 50px 20px 30px;
}

.btn-getcontent {
	float: right;
}
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
				<button onclick="window.location='logout.do'"
					class="btn btn-primary">注销</button>
			</div>
			<div class="c_content">
				<div class="cc_left col-md-2">
					<ul>
						<li><a href="infolist.do">通知列表</a></li>
						<li class="active"><a href="newInform.jsp">新建通知</a></li>
						<li><a href="sum.jsp">数据统计</a></li>
					</ul>
				</div>
				<div class="cc_right col-md-10">
					<div class="form">
						<form class="form-horizontal" role="form" action="newInform.do"
							method="Post" onSubmit="return newInformCheck()">
							<div class="form-group">
								<label for="title" class="col-sm-2 control-label">标题</label>
								<div class="col-sm-10">
									<input name="title" type="text" class="form-control" id="title"
										placeholder="通知标题">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2"></div>
								<div class="col-md-5">
									通知类别： <select id="type" name="type" class="form-control"
										onchange="typeChange()">
										<option value="面试通知" selected="selected">面试通知</option>
										<option value="笔试通知">笔试通知</option>
										<option value="Offer发放">Offer发放</option>
									</select>
								</div>
								<div class="col-md-5">
									权限： <select id="right" name="isPrivate" class="form-control">
										<option value="1">私有</option>
										<option value="0" selected="selected">公开</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="place" class="col-sm-2 control-label">地点</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="place" name="place"
										placeholder="地点">
								</div>
							</div>
							<div id="deadlinediv" class="form-group">
								<label for="deadline" class="col-sm-2 control-label">截止时间</label>
								<div class="col-sm-10">
									<input type="datetime" class="form-control" id="deadline"
										name="deadline" placeholder="截止时间">
								</div>
							</div>


							<div class="form-group">
								<label for="info-content" class="col-sm-2 control-label">通知内容</label>
								<div class="col-sm-10">
									<script type="text/javascript">
										var um = UM.getEditor('myEditor');
									</script>
									<textarea id="myEditor" name="content"
										style="width: 100%; height: 240px;"> 
    					<p>&nbsp;&nbsp;</p>
					</textarea>
								</div>
							</div>
							<table class="table">
								<thead>
									<tr>
										<th>学号*</th>
										<th>姓名*</th>
										<th>Email</th>
										<th>岗位</th>
										<th>备注</th>
										<th>#</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="text" class="form-control"
											placeholder="学号"></td>
										<td><input type="text" class="form-control"
											placeholder="姓名"></td>
										<td><input type="email" class="form-control"
											placeholder="Email"></td>
										<td><input type="text" class="form-control"
											placeholder="岗位"></td>
										<td><input type="text" class="form-control"
											placeholder="备注"></td>
										<td>
											<button type="button"
												class="btn btn-warning btn-table-delete">删除</button>
										</td>
									</tr>
								</tbody>
							</table>

							<div class="form-group">
								<button type="button" class="btn btn-default col-sm-12"
									id="btn-table-add">点击增加 录入一个新同学</button>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-10 col-sm-2">
									<button type="submit" class="btn btn-primary">确定发布</button>
								</div>
							</div>

						</form>
					</div>
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
	<script src="../js/newInform.js"></script>


</body>
</html>
