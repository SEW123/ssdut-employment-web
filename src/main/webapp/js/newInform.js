$(function() {
	setName();
	$('#btn-table-add')
			.on(
					'click',
					function() {
						$('.form .table > tbody')
								.append(
										$('<tr>'
												+ '<td>'
												+ '<input type="text" class="form-control" placeholder="学号">'
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

function submit(){
	//tijiao
	var data = new FormData();
	var title = $('form #title').val();
	var type = $('form #type').val();
	var isPrivate = $('form #right').val();
	var place = $('form #place').val();
	var deadline = $('form #deadline').val();
	var content = $('form #myEditor').val();
	data.append('title', title);
	data.append('type', type);
	data.append('isPrivate', isPrivate);
	data.append('place', place);
	data.append('deadline', deadline);
	data.append('content', content);
	$('#table tbody tr').each(function(index,ele){
		var studentId = $(this).children('td:eq(0) > input').val();
		var name = $(this).children('td:eq(1) > input').val();
		var mail = $(this).children('td:eq(2) > input').val();
		var job = $(this).children('td:eq(3) > input').val();
		var note = $(this).children('td:eq(4) > input').val();
		
		data.append('infoViewList['+index+'].studentId', studentId);
		data.append('infoViewList['+index+'].name', name);
		data.append('infoViewList['+index+'].mail', mail);
		data.append('infoViewList['+index+'].job', job);
		data.append('infoViewList['+index+'].note', note);
	});

	doPost({
	    url: "http://10.31.248.250:8080/ssdut-employment-web/teacher/newInform.do",
	    data: data
	}, function (data) {      
	    // 成功
		
		alert(data);
		console.log(data);
		
	}, function (error) {

	    if (error.serverError) {
	        alert('服务器错误，请稍候再试！');
	    } else {
	        switch(error.code){
	            default:
	                alert("未知错误!");
	                break;
	        }
	    }
	});
}



function doPost(options, callback1, callback2) {
    $.ajax({
        type: 'POST',
        url: options.url,
        data: options.data,
        dataType: 'text',
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.code != 0) {
                callback2(data);
                return;
            }
            callback1(data);
        },
        error: function (error) {
            callback2({serverError: true});
        }
    });
}