function mycheck() {
	if (isNull(form1.name.value)) {
		alert("请输入姓名！");
		return false;
	}
	if (isNull(form1.studentId.value)) {
		alert("请输入学号！");
		return false;
	}
	if (isNull(form1.email.value)) {
		alert("请输入邮箱！");
		return false;
	}
	if(!isNum(form1.studentId.value)){
		form1.studentId.focus();
		alert("学号只能由数字组成！");
		return false;
	}
}
function isNum(x) {
	var re = /^[1-9]+[0-9]*]*$/; // 判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
	if (!re.test(x)) {
		return false;
	}
	return true;
}

function isNull(str) {
	if (str == "")
		return true;
}
