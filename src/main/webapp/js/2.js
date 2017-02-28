/**
 * Created by yangzhaobin on 2016/11/24.
 */


$(function () {
    $('#userName').on('blur',function () {
        if($(this).val() == "") {
            $($(this).parent()).addClass('has-warning has-feedback');
            $(this).attr('placeholder','学号不能为空');
            $($(this).parent()).append($('<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>'));
        }else {
            $($(this).parent()).removeClass('has-warning has-feedback');
            $(this).attr('placeholder','');
            $($(this).parent()).remove($('<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>'));
        }
    });
    $('#password').on('blur',function () {
        if($(this).val() == "") {
            $($(this).parent()).addClass('has-warning has-feedback');
            $(this).attr('placeholder','密码不能为空');
            $($(this).parent()).append($('<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>'));
        }else {
            $($(this).parent()).removeClass('has-warning has-feedback');
            $(this).attr('placeholder','');
            $($(this).parent()).remove($('<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>'));
        }
    });
    
});