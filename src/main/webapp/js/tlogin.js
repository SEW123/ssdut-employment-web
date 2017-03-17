function mycheck() {
	if (isNull(form1.teacherId.value)) {
		alert("请输入账号！");
		return false;
	}
	if (isNull(form1.password.value)) {
		alert("请输入密码！");
		return false;
	}
	if(!isNum(form1.teacherId.value)){
		form1.teacherId.focus();
		alert("账号只能由数字组成！");
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
function goSelf() {
	window.location.href = "getSelf.do";
}
function goAccept(){
	window.location.href="accept.do?messageId=${message.messageId}";
}

function exportExcel(){  
    window.location.href="export.do";  
}  
