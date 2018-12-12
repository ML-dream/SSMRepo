//js生成随机数    n表示生成几位的随机数
var jschars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
function generateMixed(n) {
    var res = "";
    for (var i = 0; i < n; i++) {
        var id = Math.ceil(Math.random() * 35);
        res += jschars[id];
    }
    return res;
}

function onDataGridValidateTextLength(event, title, maxLength, flag, validateValue) {
    var length = 0, position = -1;
    var errorText = "对不起，您输入的“" + title + "”超过" + maxLength + "个最大字节数！";
    if (flag) {
        errorText += "（1个汉字=3个字节）";
    }
    for (var i = 0; i < validateValue.length; i++) {
        if (0 <= validateValue.charCodeAt(i) && 255 >= validateValue.charCodeAt(i)) {
            length++;
        } else {
            length += 3;
        }
        if (maxLength < length && -1 == position) {
            position = i;
            break;
        }
    }
    if (maxLength < length) {
        event.errorText = errorText;
        event.isValid = false;
        //bug修改：取消自动截取功能，让用户自己截取
//            validateObject.setValue(validateValue.substring(0, position));
    }

}

function onNumber(e) {
    var number = e.value;
    if (number != null && number != undefined) {
        var reg_number = /^\+?[0-9][0-9]*$/;
        if (null != number && "" != number && !reg_number.test(number)) {
            e.errorText = "格式错误, 请填写正确的值";
            e.isValid = false;
        } else if (number.length > 9) {
            e.errorText = "最多只能输入9位, 请填写正确的值";
            e.isValid = false;
        }
    }
}

function onPrice(e) {
    var price = e.value;
    if (price != undefined && price != null) {
//        price = price.replace(/\s{1,}/g, ""); // 去除前后空格
        var reg_price = /^-?\d+\.{0,}\d{0,}$/;

        if (null != price && "" != price && !reg_price.test(price)) {
            e.errorText = "格式错误, 请填写正确的值";
            e.isValid = false;
        }
    }
}

//整数不超过8位，小数不超过2为的金额限制
function eightAndTwo(e) {
    var price = e.value;
    if (price != undefined && price != null) {
//        price = price.replace(/\s{1,}/g, ""); // 去除前后空格
        var reg_price = /^[+-]?\d{1,8}(?:\.\d{1,2})?$/;///^\d+(?:\.\d{1,2})?$/
       
        if (null != price && "" != price && !reg_price.test(price)) {
            e.errorText = "格式：整数8位，小数2位";
            e.isValid = false;
        }else if(parseInt(price) == 0){
        	e.errorText = "不能为0";
            e.isValid = false;
        }
    }
}
//正整数不超过10位,且大于0
function ten(e) {
    var price = e.value;
    if (price != undefined && price != null) {
//        price = price.replace(/\s{1,}/g, ""); // 去除前后空格
        var reg_price = /^[+]?\d{1,10}$/;       
        if (null != price && "" != price && !reg_price.test(price)) {
            e.errorText = "格式：正整数（不超过十位）";
            e.isValid = false;
        }else if(parseInt(price) == 0){
        	e.errorText = "不能为0";
            e.isValid = false;
        }
    }
}

function onScore(e) {
    var score = e.value;
    if (score != undefined && score != null) {
//        score = score.replace(/\s{1,}/g, ""); // 去除前后空格
        var reg_score = /^1?[0-9]?\d([.]\d)?$/;

        if (null != score && "" != score && !reg_score.test(score)) {
            e.errorText = "格式错误, 请填写正确的值";
            e.isValid = false;
        }
    }
}

/**
 * 验证字段值是否存在
 * @param {} inputName    输入框名称
 * @param {} inputValue    输入框修改之前的值
 * @param {} tableName    表名
 * @param {} fieldName    字段名
 * @param {} fieldValue 字段值
 * @param {} platId    平台主键
 */
function validateExists(e, inputName, inputValue, tableName, fieldName, platId, otherValue) {
    if (e.value == "" || e.value == null) {
        return;
    }
    var flag = true;

    if (inputValue != null && inputValue != "") {
        if (e.value == inputValue) {
            flag = false;
        }
    }
    if (flag) {
        var params = "tableName=" + tableName + "&fieldName= " + fieldName + "&fieldValue=" + e.value + "&platId=" + platId + "&otherValue=" + otherValue;
        $.ajax({
            url: "sysmag/BSysUtil_validateExists.action",
            type: 'POST',
            data: params,
            success: function (data) {
                if (null != data && data.opt == "error") {
                    mini.alert(inputName + "已存在，请重新输入！", "提示", function () {
                        mini.get(e.source.id).setValue("");
                    });
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                mini.alert(jqXHR.responseText);
            }
        });
    }
}
/**
 * 验证字段值是否存在
 * @param {} inputName    输入框名称
 * @param {} inputValue    输入框修改之前的值
 * @param {} tableName    表名
 * @param {} fieldName    字段名
 * @param {} fieldValue 字段值
 * @param {} platId    平台主键
 */
function validateExistsByOrgId(e, inputName, inputValue, tableName, fieldName, orgId, orgIdValue, platId, otherValue) {
    if (e.value == "" || e.value == null) {
        return;
    }
    var flag = true;

    if (inputValue != null && inputValue != "") {
        if (e.value == inputValue) {
            flag = false;
        }
    }
    if (flag) {
        var params = "tableName=" + tableName + "&fieldName= " + fieldName + "&fieldValue=" + e.value + "&orgId= " + orgId + "&orgIdValue=" + orgIdValue + "&platId=" + platId + "&otherValue=" + otherValue;
        $.ajax({
            url: "sysmag/BSysUtil_validateExistsByOrgId.action",
            type: 'POST',
            data: params,
            success: function (data) {
                if (null != data && data.opt == "error") {
                    mini.alert(inputName + "已存在，请重新输入！", "提示", function () {
                        mini.get(e.source.id).setValue("");
                    });
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                mini.alert(jqXHR.responseText);
            }
        });
    }
}
function validateGroupExists(e, inputName, inputValue, tableName, fieldName, verifyName, verifyValue, platId, otherValue) {
    if (e.value == "" || e.value == null) {
        return;
    }
    var flag = true;

    if (inputValue != null && inputValue != "") {
        if (e.value == inputValue) {
            flag = false;
        }
    }
    if (flag) {
        var params = "tableName=" + tableName + "&fieldName= " + fieldName + "&fieldValue=" + e.value + "&verifyName=" + verifyName + "&verifyValue=" + verifyValue + "&platId=" + platId + "&otherValue=" + otherValue;
        $.ajax({
            url: "sysmag/BSysUtil_validateGroupExists.action",
            type: 'POST',
            data: params,
            success: function (data) {
                if (null != data && data.opt == "error") {
                    mini.alert(inputName + "已存在，请重新输入！", "提示", function () {
                        mini.get(e.source.id).setValue("");
                    });
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                mini.alert(jqXHR.responseText);
            }
        });
    }
}