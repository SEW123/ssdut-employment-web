$(function() {
	setName();
	
	if($('.form .table > tbody').children().length == 1){	
		console.log(111);
		$('.form .table > tbody .btn-table-delete').attr('disabled','true');
	}else {
		console.log(222);
		$('.form .table > tbody .btn-table-delete').attr('disabled','false');
	}
	
	$('#btn-table-add')
			.on(
					'click',
					function() {
						
						if($('.form .table > tbody').children().length == 1){	
							$('.form .table > tbody .btn-table-delete').attr('disabled','true');
						}else {
							$('.form .table > tbody .btn-table-delete').attr('disabled','false');
						}
						
						$('.form .table > tbody')
								.append(
										$('<tr>'
												+ '<td>'
												+ '<input type="text" class="form-control" placeholder="学号123">'
												+ '</td>'
												+ '<td>'
												+ '<input type="text" class="form-control" placeholder="姓名">'
												+ '</td>'
										 		+ '<td>'
												+ '<input type="email" class="form-control" placeholder="Email">'
												+ '</td>'
												+ '<td>'
												+ '<input type="text" class="form-control" placeholder="岗位">'
												+ '</td>'
												+ '<td>'
												+ '<input type="text" class="form-control" placeholder="备注">'
												+ '</td>'
												+ '<td>'
												+ '<button type="button" class="btn btn-warning btn-table-delete">删除</button>'
												+ '</td>' + '</tr>'));
						setName();
						$('.btn-table-delete').off('click');
						$('.btn-table-delete').on('click', function() {
							$($(this).parent().parent()).remove();
							setName();
						});
						
						
					});
	$('.btn-table-delete').on('click', function() {
		$($(this).parent().parent()).remove();
		setName();
		
		if($('.form .table > tbody').children().length == 1){	
			$('.form .table > tbody .btn-table-delete').attr('disabled','true');
		}else {
			$('.form .table > tbody .btn-table-delete').attr('disabled','false');
		}
	});

	function setName() {
		var i = 0;
		$('.form .table > tbody > tr').each(
				function(index, ele) {
					$ele = $(ele);
					var arr = $ele.children();
					if (arr.length > 0) {
						$(arr[0].children[0]).attr('name',
								'infoViewList[' + i + '].studentId');
						$(arr[1].children[0]).attr('name',
								'infoViewList[' + i + '].name');
						$(arr[2].children[0]).attr('name',
								'infoViewList[' + i + '].mail');
						$(arr[3].children[0]).attr('name',
								'infoViewList[' + i + '].job');
						$(arr[4].children[0]).attr('name',
								'infoViewList[' + i + '].note');
						i++;
					}
				});
	}
});
function newInformCheck() {
	var ui = document.getElementById("deadlinediv");
	if (document.getElementById("title").value == "") {
		alert("标题为空！");
		return false;
	} else if (document.getElementById("place").value == "") {
		alert("地点为空！");
		return false;
	} else if (ui.style.display != "none"
			&& document.getElementById("deadline").value == "") {
		alert("截止日期为空！");
		return false;
	} else {
		var len = $('.form .table > tbody > tr').length;
		for (var i = 0; i < len; i++) {
			var studentId = $(
					'.form .table > tbody > tr:eq(' + i + ') > td:eq(0) >input')
					.val();
			if (studentId == "") {
				var j = i;
				j++;
				alert("第" + j + "行学号为空！");
				return false;
			} else {
				var name = $(
						'.form .table > tbody > tr:eq(' + i
								+ ') > td:eq(1) >input').val();
				var j = i;
				j++;
				if (name == "") {
					alert("第" + j + "行姓名为空！");
					return false;
				}
			}

		}

	}

}
function typeChange() {
	var objS = document.getElementById("type");
	var value = objS.options[objS.selectedIndex].value;
	var ui = document.getElementById("deadlinediv");
	if (value == "Offer发放") {
		ui.style.display = "none";
	}

	else {
		ui.style.display = "";
	}

}

//function getPlainTxt() {
//	if (newInformCheck() == false)
//		return false;
//	var objS = document.getElementById("type");
//	var value = objS.options[objS.selectedIndex].value;
//	var arr = [];
//	arr.push("标题：" + document.getElementById("title").value);
//	var objT = document.getElementById("type");
//	var valueT = objT.options[objT.selectedIndex].value;
//	arr.push("通知类型：" + valueT);
//	var objR = document.getElementById("type");
//	var valueR = objR.options[objR.selectedIndex].value;
//	if (valueR == 1)
//		arr.push("私有");
//	else
//		arr.push("公开");
//	arr.push("地点：" + document.getElementById("place").value);
//	var ui = document.getElementById("deadlinediv");
//	if (ui.style.display != "none")
//		arr.push("截止时间：" + document.getElementById("deadline").value);
//	arr.push("通知内容：" + UM.getEditor('myEditor').getPlainTxt());
//
//	var len = $('.form .table > tbody > tr').length;
//	for (var i = 0; i < len; i++) {
//		var studentInfo = "学生信息：";
//		for (var j = 0; j < 5; j++) {
//			var curValue = $(
//					'.form .table > tbody > tr:eq(' + i + ') > td:eq(' + j
//							+ ') >input').val();
//			if (curValue != "") {
//				studentInfo += curValue + ' ';
//			}
//		}
//		arr.push(studentInfo);
//	}
//	arr = arr.join('\n');
//	var hide = document.getElementById("hide");
//	hide.style.display = "";
//	var clipboard = document.getElementById("clipboard");
//	clipboard.value = arr;
//	clipboard.select(); // 选择对象
//	document.execCommand("Copy"); // 执行浏览器复制命令
//	alert(arr + "\n\n通知已复制到剪贴板，可以直接粘贴！");
//}
//function hideDiv() {
//	var hide = document.getElementById("hide");
//	hide.style.display = "none";
//}