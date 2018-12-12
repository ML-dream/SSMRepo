/**
 * Created by lwl on 2015/9/25.
 */

function passwordTest(e){
    if (e.isValid) {
        var reg_pass = /^\w{6,18}$/
        if (!reg_pass.test(e.value)) {
            e.isValid = false;
            e.errorText = "登录密码只能输入6~18位的数字、字母或下划线!";
        }
    }
}

function passwordTwo(e){
    if (e.isValid) {
        if($("input[name='password']").val() != e.value){
            e.isValid = false;
            e.errorText = "确认密码必须与登录密码相同!";
        }
    }
}

function phone(e){
    if (e.isValid) {
        var tel = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
        if (!tel.test(e.value)) {
            e.isValid = false;
            e.errorText = "固定电话格式错误! 正确的格式如: 025-12345678";
        }
    }
}

function mobile(e){
    if (e.isValid) {
        var reg_mobile = /^1[3|4|5|8|9][0-9]\d{8,8}$/
        if (!reg_mobile.test(e.value)) {
            e.isValid = false;
            e.errorText = "手机格式错误!"
        }
    }
}
