<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>与我相关</title>
<link href="../css/sbase.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="../css/slogin.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<div class="c_warp_bor">
		<div class="c_warp">
			<div class="xs-info-list table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>通知</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" begin="${count-10>0? count-10:0}" end="${count-1>0? count-1:0}" var="message">
							<tr>
								<td><a href="godetails.do?messageId=${message.messageId}">${message.title}</a></td>
							</tr>
						</c:forEach>
				</table>
			</div>
			
			<div class="control-page" style="text-align: center;">
						<c:if test="${count<=10 && full != -1}">
							<a id="one" class="btn btn-primary" href="getSelf.do?count=${count}&type=1">下一页</a>
						</c:if>
						<c:if test="${full== -1 && count >10}">
							<a id="one" class="btn btn-primary" href="getSelf.do?count=${count}&type=-1">上一页</a>
						</c:if>
						<c:if test="${full!=-1&&count>10}">
							<a id="last" class="btn btn-primary" href="getSelf.do?count=${count}&type=-1">上一页</a>
                    		<a id="next" class="btn btn-primary" href="getSelf.do?count=${count}&type=1">下一页</a>
						</c:if>
			</div>
		</div>
	</div>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>