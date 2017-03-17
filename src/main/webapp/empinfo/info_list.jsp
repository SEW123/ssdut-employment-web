<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List,com.recruit.pojo.SsdutEmpInfo,com.recruit.data.StaticData"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>就业信息</title>
<link href="../css/sbase.css" rel="stylesheet" type="text/css">
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="../css/slogin.css" rel="stylesheet" type="text/css">
</head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<SsdutEmpInfo> list = (List<SsdutEmpInfo>) request.getAttribute("info_list");
%>
<body>
	<div class="c_warp_bor">
		<div class="c_warp">
			<div class="xs-info-list ">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>就业信息公告</th>
						</tr>
					</thead>
					<tbody>
						<%
							int size = 0;
							if (list != null) {
								size = list.size();
							}

							for (int i = 0; i < size; i++) {
								SsdutEmpInfo p = list.get(i);
						%>
						<%
							String uri = "empinfo_detail?id=" + p.getId();
						%>
						<tr>
							<td><a href="<%=uri%>"><%=p.getTitle()%></a></td>
						</tr>
						<%
							}
						%>
					
				</table>
			</div>

			<div class="control-page" style="text-align: center;">
				<%
					int paage = Integer.parseInt(request.getAttribute("page").toString());

					if (1 != paage && request.getParameter("page") != null) {

						int s = paage - 1;

						String uri = "empinfo_list?page=" + s;
				%>
				<a style="margin-right: 10%" id="one" class="btn btn-primary"
					href="<%=uri%>">上一页</a>
				<%
					}
				%>

				<%
					if (size == 10) {

						int s = paage + 1;

						String uri = "empinfo_list?page=" + s;
				%>
				<a id="one" class="btn btn-primary" href="<%=uri%>">下一页</a>
				<%
					}
				%>
			</div>
		</div>
	</div>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>