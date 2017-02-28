<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>通知详情</title>
<link href="../css/sbase.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="../css/slogin.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/1.js"></script>
</head>
<body>
	<div class="c_warp_bor">
		<div class="c_warp">
			<c:if test="${has!=null}">
				<div class="selectbox">
					<input type="button" id="refuse" class="btn btn-success" value="拒绝"
						onclick="javascript:window.location.href='refuse.do?messageId=${message.messageId}'">
					<input type="button" id="accept" class="btn btn-danger" value="接受"
						onclick="javascript:window.location.href='accept.do?messageId=${message.messageId}'">
				</div>
			</c:if>

			<div class="subtitle">
				<span>${message.title}</span>
			</div>
			<div class="subtitle-info">
				<span class="subtitle-time btn-default btn disabled">截止确认时间:${message.deadline }</span>
				<span class="subtitle-type btn-default btn disabled">类别：${message.type}</span>
			</div>
			<div class="info">${message.content }</div>

			<div class="xs-info-list table-responsive">
				<table class="table table-hover">
					<tr>
						<td>时间:${message.releaseTime }</td>
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
						<c:forEach items="${list}" var="infoview">
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
	</div>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/1.js"></script>
</body>
</html>