/**
 * Form表单验证常用函数。
 * Author YISHUNLI
 * Date 2011.11.01
 */

/** 常量（需在constants.jsp中同步定义） */
//空值。
//var UNDEFINED = 'undefined';
var UNDEFINED = "undefined";
var NVLL = null;
var EMPTY = "";
//控制文档控件的行为模式。
var MODE_FOCUS = "FOCUS";//聚焦模式。
var MODE_SELECT = "SELECT";//选中模式。
//控制前后日期输入框的清空模式。
var FLAG_BEGIN = "BEGIN";//前面的日期。
var FLAG_END = "END";//后面的日期。
//控制附件上传格式。
var EXTENSION_WORD = "DOC/DOCX";//WORD文档。
var EXTENSION_EXCEL = "XLS/XLSX";//EXCEL文档。
var EXTENSION_IMAGE = "BMP/GIF/JPG/JPEG/PNG";//图片文档。
var EXTENSION_TXT = "TXT";//文本文档。
var EXTENSION_PDF = "PDF";//PDF文档。
var EXTENSION_EXCEPT_IMAGE = "DOC/DOCX/XLS/XLSX/TXT/PDF";//图片文档除外。
var EXTENSION_DEFAULT = "DOC/DOCX/XLS/XLSX/BMP/GIF/JPG/JPEG/PNG/TXT/PDF";//默认文档后缀名。
//控制日期时间格式。
var PATTERN_yMd = "yyyy-MM-dd";//年月日。
var PATTERN_yMdHms = "yyyy-MM-dd HH:mm:ss";//年月日时分秒。
var PATTERN_yMdHm = "yyyy-MM-dd HH:mm";//年月日时分。
//手机号码和电话号码的验证模式。
var TYPE_PHONE_MOBILE = "PHONE_MOBILE";//手机号码（中国移动）。
var TYPE_PHONE_UNICOM = "PHONE_UNICOM";//手机号码（中国联通）。
var TYPE_PHONE_TELECO = "PHONE_TELECO";//手机号码（中国电信）。
var TYPE_PHONE_TELECO_CDMA = "PHONE_TELECO_CDMA";//手机号码（中国电信CDMA）。
var TYPE_TELEPHONE = "TELEPHONE";//电话号码。
var TYPE_PHONE_MOBILE_TELEPHONE = "PHONE_MOBILE_TELEPHONE";//手机号码（中国电信）或电话号码。
var TYPE_PHONE_UNICOM_TELEPHONE = "PHONE_UNICOM_TELEPHONE";//手机号码（中国联通）或电话号码。
var TYPE_PHONE_TELECO_TELEPHONE = "PHONE_TELECO_TELEPHONE";//手机号码（中国电信）或电话号码。
var TYPE_PHONE_TELECO_CDMA_TELEPHONE = "PHONE_TELECO_CDMA_TELEPHONE";//手机号码（中国电信CDMA）或电话号码。
var TYPE_PHONE = "PHONE";//手机号码（中国移动、中国联通、中国电信、中国电信CDMA）。
var TYPE_PHONE_TELEPHONE = "PHONE_TELEPHONE";//手机号码（中国移动、中国联通、中国电信、中国电信CDMA）或电话号码。

/** 常量（无需在constants.jsp中同步定义） */
//中文中括号。
var CHINESE_MIDDLE_BRACKETS_LEFT = "【";//中文左中括号。
var CHINESE_MIDDLE_BRACKETS_RIGHT = "】";//中文右中括号。
//换行符。
var LINE_SEPERATOR = "\r";//也可用"\n"。
//分隔符。
var SPLIT_COMMA = ",";//英文逗号分隔符。
var SPLIT_BLANK = " ";//空格分隔符。
var SPLIT_HORIZONTAL = "-";//水平线分隔符。
//（中国移动、中国联通、中国电信、中国电信CDMA、电话号码）正确格式消息提示。
var HINT_PHONE_MOBILE = "必须在规定的中国移动134、135、136、137、138、139、147、150、151、152、157、158、159、181、182、183、184、187、188号段范围内，其中147为上网卡";//中国移动。
var HINT_PHONE_UNICOM = "必须在规定的中国联通130、131、132、145、155、156、185、186号段范围内，其中145为上网卡";//中国联通。
var HINT_PHONE_TELECO = "必须在规定的中国电信133、153、180、189号段范围内";//中国电信。
var HINT_PHONE_TELECO_CDMA = "必须在规定的中国电信CDMA133、153号段范围内";//中国电信CDMA。
var HINT_TELEPHONE = "必须为0000-00000000格式";//电话号码。
//（中国移动、中国联通、中国电信、中国电信CDMA、电话号码）手机号码格式验证正则表达式。
var REGEXP_PHONE_MOBILE = /^1(3[4-9]|5[012789]|8[123478])\d{8}$/;//中国移动。
var REGEXP_PHONE_UNICOM = /^1(3[0-2]|4[5]|5[56]|8[56])\d{8}$/;//中国联通。
var REGEXP_PHONE_TELECO = /^1(3[3]|5[3]|8[09])\d{8}$/;//中国电信。
var REGEXP_PHONE_TELECO_CDMA = /^1[35]3\d{8}$/;//中国电信CDMA。
var REGEXP_TELEPHONE = /^[0-9]{3,4}\-[0-9]{7,8}$/;//电话号码。

/**
 * 函数功能：检查文本框或文本域输入的内容是否为空。
 * 参数说明：title 文本框或文本域输入内容的标题。
 *         id 接受输入的文本框或文本域对象ID。
 * 返回结果：false 文本框或文本域输入的内容为空。
 *         true 文本框或文本域输入的内容不为空。
 * 适用事件：onsubmit、onblur。
 */
function validateTextEmpty(title, id) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    if (isEmpty(value)) {
        alert("对不起，您输入的“" + title + "”不能为空，请输入！");
        //将去除空格后的值回填给对象。
        object.value = value;
        if ("hidden" != object.type) {
            object.focus();
        }
        return false;
    }
    return true;
}

/**
 * 函数功能：验证文档控件数组输入的内容是否为空。
 * 参数说明：titleArray 文档控件数组对象的标题。
 *         nameArray 文档控件数组对象的NAME。
 *         flag true正常情况处理、false针对DynameicTable.js组件特殊处理。
 * 返回结果：false 文档控件数组输入的内容为空。
 *         true 文档控件数组输入的内容不为空。
 * 适用事件：onsubmit、onblur。
 */
function validateTextArrayEmpty(titleArray, nameArray, flag) {
    var messages = EMPTY;
    var object;
    for (var i = 0; i < nameArray.length; i++) {
        var objects = document.getElementsByName(nameArray[i]);
        var message = EMPTY;
        var length = objects.length;
        if (!flag) {
            //针对DynameicTable.js使用。
            length = objects.length - 1;
        }
        for (var j = 0; j < length; j++) {
            var value = replaceAllBlank(objects[j].value);
            if (isEmpty(value)) {
                message += SPLIT_COMMA + (j + 1);
                objects[j].value = value;
                if (UNDEFINED == typeof(object)) {
                    object = objects[j];
                }
            }
        }
        if (!isEmpty(message)) {
            message = "“" + titleArray[i] + "”[" + message.substring(1) + "行]；";
            messages += message + LINE_SEPERATOR;
        }
    }
    if (!isEmpty(messages)) {
        alert("对不起，您输入的" + LINE_SEPERATOR + messages + "不能为空，请输入！");
        if (UNDEFINED != typeof(object)) {
            object.focus();
        }
        return false;
    }
    return true;
}

/**
 * 函数功能：检查Iframe内的文本框或文本域输入的内容是否为空。
 * 参数说明：title 文本框或文本域输入内容的标题。
 *         iframe iframe对象的ID或NAME。
 *         id 接受输入的文本框或文本域对象ID。
 * 返回结果：false 文本框或文本域输入的内容为空。
 *         true 文本框或文本域输入的内容不为空。
 * 适用事件：onsubmit、onblur。
 */
function validateIframeTextEmpty(title, iframe, id) {
    //var object = (document.frames(iframe)).document.getElementById(id);
    var iframeObject = document.frames(iframe);
    var object = iframeObject.document.getElementById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    if (isEmpty(value)) {
        alert("对不起，您输入的“" + title + "”不能为空，请输入！");
        //将去除空格后的值回填给对象。
        object.value = value;
        if ("hidden" != object.type) {
            object.focus();
        }
        return false;
    }
    return true;
}

/**
 * 函数功能：检查文本框或文本域输入的内容是否超过最大长度限制。
 * 参数说明：title 文本框或文本域输入内容的标题。
 *         hintId 用于显示计算后还可输入字节数的文本框对象ID。
 *         validateId 接受输入的文本框或文本域对象ID。
 *         maxLength 文本框或文本域允许输入的最大字节数。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框或文本域输入的内容超过最大长度限制。
 *         true 文本框或文本域输入的内容在最大长度限制范围内。
 * 适用事件：onsubmit、onkeyup、onblur。
 */
function validateTextLength(title, validateId, hintId, maxLength, mode) {
    var validateObject = getObjectById(validateId);
    var hintObject = getObjectById(hintId);
    var validateValue = validateObject.value;
    var length = 0, position = -1;
    for (var i = 0; i < validateValue.length; i++) {
        if (0 <= validateValue.charCodeAt(i) && 255 >= validateValue.charCodeAt(i)) {
            length++;
        } else {
            length += 2;
        }
        if (maxLength < length && -1 == position) {
            position = i;
            break;
        }
    }
    mode = changeUpperCase(mode);
    if (NVLL != hintObject) {
        if (maxLength >= length) {
            hintObject.value = maxLength - length;
        } else {
            alert("对不起，您输入的“" + title + "”超过" + maxLength + "最大字节数！（1个汉字=2个字节）");
            hintObject.value = 0;
            validateObject.value = validateValue.substring(0, position);
            if (MODE_FOCUS == mode) {
                validateObject.focus();
            } else if (MODE_SELECT == mode) {
                validateObject.select();
            }
            return false;
        }
    } else {
        if (maxLength < length) {
            alert("对不起，您输入的“" + title + "”超过" + maxLength + "最大字节数！（1个汉字=2个字节）");
            validateObject.value = validateValue.substring(0, position);
            if (MODE_FOCUS == mode) {
                validateObject.focus();
            } else if (MODE_SELECT == mode) {
                validateObject.select();
            }
            return false;
        }
    }
    return true;
}

/**
 * 函数功能：校验文本框或文本域进行人员选择输入或组织选择输入时是否超过最大长度限制。（可选择输入个数）
 * 参数说明：textTitle 文本框或文本域输入内容的标题。
 *         textId 文本框或文本域对象ID。
 *         hiddenId 文本框或文本域对应的隐藏域对象ID。
 *         maxLength 文本框或文本域允许输入的最大长度。（字节数）
 *         dight 文本框或文本域输入内容对应ID值的长度。（字节数）
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框或文本域输入的内容超过最大长度限制。
 *         true 文本框或文本域输入的内容在最大长度限制范围内。
 * 适用事件：onsubmit、onchange、onblur。
 */
function validateTextHiddenLength(textTitle, textId, hiddenId, maxLength, digit, mode) {
    var textObject = getObjectById(textId);
    var hiddenObject = getObjectById(hiddenId);
    var textValue = textObject.value;
    var hiddenValue = hiddenObject.value;
    var textNumber = textValue.split(SPLIT_COMMA).length;
    var hiddenNumber = Math.floor((maxLength - digit) / (digit + 1)) + 1;
    var differNumber = 0;
    mode = changeUpperCase(mode);
    if (textNumber <= hiddenNumber) {
        differNumber = hiddenNumber - textNumber;
        alert("您还可以输入[" + differNumber + "个]“" + textTitle + "”！");
        if (MODE_FOCUS == mode) {
            textObject.focus();
        } else if (MODE_SELECT == mode) {
            textObject.select();
        }
        return true;
    } else {
        differNumber = textNumber - hiddenNumber;
        alert("您输入的“" + textTitle + "”[" + textNumber + "个]，已超过[" + hiddenNumber + "个]最大允许数！");
        var textArray = textValue.split(SPLIT_COMMA);
        for (var i = 0; i < differNumber; i++) {
            textArray.pop();
        }
        textObject.value = convertArrayToStr(textArray);
        hiddenObject.value = hiddenValue.substring(0, (hiddenValue.length - (differNumber * (digit + 1))));
        if (MODE_FOCUS == mode) {
            textObject.focus();
        } else if (MODE_SELECT == mode) {
            textObject.select();
        }
        return false;
    }
}

/**
 * 函数功能：数值进度处理。
 * 参数说明：def 默认值。
 *         num 需处理的数值。
 *         n 四舍五入小数的位数。
 * 返回结果：指定四舍五入位数后的数值。
 */
function round(def, num, n) {
    var isN = isNaN(parseFloat(num));
    return isN ? def : parseFloat(num).toFixed(n);
}

//默认值为0.00，一般用于小数输入。
function roundFloat(num, n) {
    return round("0.00", num, n);
}

//默认值为0，一般用于整数输入。
function roundInt(num, n) {
    return round("0", num, n);
}

//格式化为正整数。
function roundPositiveInteger(num, n) {
    return replaceAllChar(roundInt(num, n), SPLIT_HORIZONTAL, EMPTY);
}

//格式化为正小数。
function roundPositiveFloat(num, n) {
    return replaceAllChar(roundFloat(num, n), SPLIT_HORIZONTAL, EMPTY);
}

/**
 * 函数功能：替换字符串中所有指定的字符。
 * 参数说明：text 需替换字符的字符串。
 *         oldChar 被替换的字符。
 *         newChar 替换的字符。
 * 返回结果：替换指定字符后的字符串。
 */
function replaceAllChar(text, oldChar, newChar) {
    text = changeUndefinedNullToEmpty(text);
    var regExp = new RegExp(oldChar, "gm");
    text = text.replace(regExp, newChar);
    return text;
}

//去除所有空格。
function replaceAllBlank(text) {
    text = changeUndefinedNullToEmpty(text);
    text = replaceAllChar(text, SPLIT_BLANK, EMPTY);
    return text;
}

//undefined转换为空值。
function changeUndefinedToEmpty(text) {
    return UNDEFINED == typeof(text) ? EMPTY : text;
}

//undefined、null转换为空值。
function changeUndefinedNullToEmpty(text) {
    text = changeUndefinedToEmpty(text);
    return NVLL == text ? EMPTY : text;
}

//undefined、null、空格转换为空值。
function changeUndefinedNullBlankToEmpty(text) {
    text = changeUndefinedNullToEmpty(text);
    text = replaceAllBlank(text);
    return text;
}

//判断文本字符串是否为空(true 空、false 非空)。
function isEmpty(text) {
    text = changeUndefinedNullBlankToEmpty(text);
    return EMPTY == text ? true : false;
}

//小写字母转换为大写字母。
function changeUpperCase(text) {
    if (!isEmpty(text)) {
        text = text.toUpperCase();
    }
    text = changeUndefinedNullBlankToEmpty(text);
    return text;
}

//大写字母转换为小写字母。
function changeLowerCase(text) {
    if (!isEmpty(text)) {
        text = text.toLowerCase();
    }
    text = changeUndefinedNullBlankToEmpty(text);
    return text;
}

/**
 //去除字符串首尾空格。
 String.prototype.trim = function() {
	var regExp = new RegExp(/(^\s*)|(\s*$)/g);
	return this.replace(regExp, EMPTY);
}
 */

//去除字符串首尾空格。
function trim(text) {
    text = changeUndefinedNullToEmpty(text);
    var regExp = new RegExp(/(^\s*)|(\s*$)/g);
    text = text.replace(regExp, EMPTY);
    return text;
}

//根据对象ID获取对象。
function getObjectById(id) {
    return document.getElementById(id);
}

/**
 * 函数功能：检查文本框输入的内容是否为数值。
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框输入的内容为空或输入的内容不是数值。
 *         true 文本框输入的内容为数值。
 * 适用事件：onsubmit、onblur。
 */
function validateIsNaN(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    mode = changeUpperCase(mode);
    /**
     //检查是否为空。
     if(!checkTextEmpty(object, title)) {
		object.value = roundFloat(value, 2);
		if (MODE_FOCUS == mode) {
			object.focus();
		} else if (MODE_SELECT == mode) {
			object.select();
		}
		return false;
	}
     */
    if (isNaN(value)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]不是数值，请重新输入！");
        object.value = roundFloat(value, 2);
        if (MODE_FOCUS == mode) {
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    //格式化数值并回填给对象。
    object.value = roundFloat(value, 2);
    if (MODE_FOCUS == mode) {
        object.focus();
    } else if (MODE_SELECT == mode) {
        object.select();
    }
    return true;
}

/**
 * 函数功能：检查文本框输入的内容是否为数字。
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框输入的内容为空或输入的内容不是数字。
 *         true 文本框输入的内容为数字。
 * 适用事件：onsubmit、onblur。
 */
function validateIsNumber(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    var regExp = new RegExp(/^[0-9]*$/);
    if (!regExp.exec(value)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]不是数字，请重新输入！");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：检查文本框输入的内容是否为正整数。
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框输入的内容为空或输入的内容不是正整数。
 *         true 文本框输入的内容为正整数。
 * 适用事件：onsubmit、onblur。
 */
function validateIsPositiveInteger(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    for (var i = 0; i < value.length; i++) {
        if (-1 == "0123456789".indexOf(value.charAt(i))) {
            alert("对不起，您输入的“" + title + "”[" + value + "]不是正整数，请重新输入！");
            mode = changeUpperCase(mode);
            if (MODE_FOCUS == mode) {
                object.value = EMPTY;
                object.focus();
            } else if (MODE_SELECT == mode) {
                object.select();
            }
            return false;
        }
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：验证是否从1开始的连续自然数。(1,2,3,4,5)
 * 参数说明：title 文档控件数组对象的标题。
 *         name 文档控件数组对象的NAME。
 *         flag true正常情况处理、false针对DynameicTable.js组件特殊处理。
 *         mode 文档控件数组对象的行为模式。
 * 返回结果：false 文档控件数组输入的值不是从1开始的连续自然数。
 *         true 文档控件数组输入的值是从1开始的连续自然数。
 * 适用事件：onsubmit、onblur。
 */
function validateIsContinuousNaturalNumber(title, name, flag, mode) {
    var objects = document.getElementsByName(name);
    var valueArray = new Array();
    var length = objects.length;
    if (!flag) {
        //使用动态添加行组件的特殊处理，去除模板中的对象。(objects.length - 1)
        length = objects.length - 1;
    }
    for (var i = 0; i < length; i++) {
        valueArray[i] = objects[i].value;
    }
    //升序排列。
    for (var i = 0; i < valueArray.length - 1; i++) {
        for (var j = i + 1; j < valueArray.length; j++) {
            if (valueArray[i] > valueArray[j]) {
                var temp = valueArray[i];
                valueArray[i] = valueArray[j];
                valueArray[j] = temp;
            }
        }
    }
    var index;
    var flag = true;
    for (var i = 0; i < valueArray.length; i++) {
        if (valueArray[i] != (i + 1)) {
            index = i;
            flag = false;
            break;
        }
    }
    if (!flag) {
        if ('function' == typeof(createIndex)) {
            if (confirm("对不起，您输入的“" + title + "”[" + valueArray.join() + "]不是从1开始的连续自然数，需要系统为其自动生成吗？")) {
                createIndex(name);
                return false;
            }
        }
        //alert("对不起，您输入的“" + title +"”[" + valueArray.join() + "]不是从1开始的连续自然数，请重新输入！");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            objects[index].value = EMPTY;
            objects[index].focus();
        } else if (MODE_SELECT == mode) {
            objects[index].select();
        }
        return false;
    }
    return true;
}

/**
 * 函数功能：验证身份证号码格式是否合法。(15位数字、18位数字或者17位数字加一个校验位字符'X')
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的身份证号码格式不合法。
 *         true 文本框接受输入的身份证号码格式合法。
 * 适用事件：onsubmit、onblur。
 */
function validateIsIDNumber(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    var regExp = new RegExp(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/);
    if (!value.match(regExp)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：15位数字、18位数字或者17位数字加一个校验位字符'X'）");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：主要用于校验IE、Firefox浏览器上传文件的格式（注：支持IE、Firefox各种版本）。
 * 参数说明：fileTitle 上传文件框标题。
 *         fileId 上传文件控件对象ID(*)。
 *         fileAllowExt 允许上传文件的后缀名（以"/"分隔）。
 *         mode 文档控件的行为模式。
 * 返回结果：false 上传文件的格式不合法。
 *         true 上传文件的格式合法。
 * 适用事件：onsubmit、onblur。
 */
function validateIsFileAllowExt(fileTitle, fileId, fileAllowExt, mode) {
    if (isEmpty(fileAllowExt)) {
        fileAllowExt = EXTENSION_DEFAULT;
    } else {
        fileAllowExt = changeUpperCase(fileAllowExt);
    }
    var fileObj = getObjectById(fileId);
    var fileVal = fileObj.value;
    var fileExt = fileVal.substr(fileVal.length - 3);
    fileExt = changeUpperCase(fileExt);
    if (-1 == fileAllowExt.indexOf(fileExt)) {
        alert("对不起，您选择的“" + fileTitle + "”文件格式[" + fileExt + "]不合法，要求是[" + fileAllowExt + "]中的任意一种格式，请重新选择文件！");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            /**
             //fileObj.value = EMPTY;//?X不支持
             fileObj.focus();
             */
            fileObj.select();
            //询问是否将当前选中区放入剪贴板后删除。
            //document.execCommand('cut');
            //删除当前选中区。
            document.execCommand('delete');
        } else if (MODE_SELECT == mode) {
            fileObj.select();
        }
        return false;
    }
    return true;
}

/**
 * 函数功能：根据文件路径获取文件大小（单位：字节Byte，简写B。1024B(Byte)=1KB、1024KB=1MB、1024MB=1GB、1024GB=1TB）
 * 参数说明：filePath 文件路径。
 * 返回结果：文件大小。
 */
function getFileSize(filePath) {
    //支持IE678
    //第一种实现方法：应用ActiveX控件。（唯一不足之处是有安全提示，当然把文件后缀名修改为.hta则会屏蔽安全提示，但很难被需求所取，故不推荐。）
    var fSObject = new ActiveXObject("Scripting.FileSystemObject");
    var fileObject = fSObject.GetFile(filePath);
    var fileSize = fileObject.size;
    /**
     //支持IE6
     //第二种实现方法：应用img标签dynsrc属性。（dynsrc可以用来插入各种多媒体，格式可以是wav、avi、aiff、au、mp3、ra、ram等等。url为音频或视频文件相对或绝对路径。）
     //故可以根据dynsrc动态赋值任何类型文件的路径，在javascript中根据Image对象本身的fileSize属性来获得文件的大小。
     var image = new Image();
     image.dynsrc = filePath;
     var fileSize = image.fileSize;
     */
    return fileSize;
}

/**
 * 函数功能：校验上传文件的大小是否超过最大允许值。（单位：MB）
 * 参数说明：title 上传文件框标题。
 *         id 上传文件控件对象ID。
 *         maxSize 上传文件大小的最大允许值。
 *         mode 文档控件的行为模式。
 * 返回结果：false 上传文件的大小超过最大允许值。
 *         true 上传文件的大小未超过最大允许值。
 * 适用事件：onsubmit、onblur。
 */
function validateFileSize(title, id, maxSize, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //var size = Number((getFileSize(value) / 1024) / 1024).toFixed(2);
    var size = ((getFileSize(value) / 1024) / 1024).toFixed(2);
    if (maxSize < size) {
        alert("对不起，您上传的文件“" + title + "”大小为[" + size + "MB]，超过最大允许值[" + maxSize + "MB]，请重新上传较小的文件！");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            /**
             //object.value = EMPTY;//?X不支持
             object.focus();
             */
            object.select();
            //询问是否将当前选中区放入剪贴板后删除。
            //document.execCommand('cut');
            //删除当前选中区。
            document.execCommand('delete');
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    return true;
}

/**
 * 函数功能：将附件上传控件对象的值赋值给图片控件对象。
 * 参数说明：fileId 附件上传控件对象ID。
 *         imgId 图片控件对象ID。
 * 返回结果：无。
 */
function changeSrc(fileId, imgId) {
    var fileObject = getObjectById(fileId);
    var imgObject = getObjectById(imgId);
    var fileValue = fileObject.value;
    imgObject.src = fileValue;
}

/**
 * 函数功能：校验上传图片文件的大小是否超过最大允许值。（单位：MB）
 * 参数功能：fileTitle 附件上传控件对象的标题。
 *         fileId 附件上传控件对象ID。
 *         imgId 图片控件对象ID。
 *         maxSize 图片文件上传大小的最大允许值。
 *         mode 文档控件的行为模式。
 * 返回结果：false 上传图片文件大小超过最大允许值或图片加载未完成。
 *         true 上传图片文件大小未超过最大允许值。
 */
function validateImageSize(fileTitle, fileId, imgId, maxSize, mode) {
    var fileObject = getObjectById(fileId);
    var imgObject = getObjectById(imgId);
    var imgSize = imgObject.fileSize;
    imgSize = (imgSize / 1024) / 1024;
    if ("complete" == imgObject.readyState) {
        if (maxSize < imgSize) {
            alert("对不起，您上传的图片“" + fileTitle + "”大小为[" + imgSize + "MB]，超过最大允许值[" + maxSize + "MB]，请重新上传较小的图片！");
            mode = changeUpperCase(mode);
            if (MODE_FOCUS == mode) {
                /**
                 //fileObject.value = EMPTY;//?X不支持
                 fileObject.focus();
                 */
                fileObject.select();
                //询问是否将当前选中区放入剪贴板后删除。
                //document.execCommand('cut');
                //删除当前选中区。
                document.execCommand('delete');
                imgObject.src = EMPTY;
            } else if (MODE_SELECT == mode) {
                fileObject.select();
            }
            return false;
        }
        return true;
    }
    return false;
}

/**
 * 函数功能：检查文件是否存在。
 * 参数说明：filePath 文件路径名。
 * 返回结果：false 文件不存在。
 *         true 文件存在。
 */
function fileIsExists(filePath) {
    var fSObject = new ActiveXObject("Scripting.FileSystemObject");
    if (!fSobject.FileExists(filePath)) {
        return false;
    }
    return true;
}

/**
 * 函数功能：检查上传的文件是否存在。
 * 参数说明：title 附件上传控件对象的标题。
 *         id 附件上传控件对象的ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 上传的文件不存在。
 *         true 上传的文件存在。
 */
function validateFileIsExists(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    if (!fileIsExists(value)) {
        alert("对不起，您选择的文件“" + title + "”[" + value + "]不存在或已被删除，请重新选择文件！");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            /**
             //fileObj.value = EMPTY;//?X不支持
             fileObj.focus();
             */
            fileObj.select();
            //询问是否将当前选中区放入剪贴板后删除。
            //document.execCommand('cut');
            //删除当前选中区。
            document.execCommand('delete');
        } else if (MODE_SELECT == mode) {
            fileObj.select();
        }
        return false;
    }
    return true;
}

/**
 * 函数功能：检查是否文件。
 * 参数说明：filePath 文件路径名。
 * 返回结果：false 不是文件。
 *         true 是文件。
 */
function isFile(filePath) {
    filePath = changeUpperCase(filePath);
    if (!isEmpty(filePath)) {
        var regExp = new RegExp(/.doc|.docx|.xls|.xlsx|.bmp|.gif|.jpg|.jpeg|.png|.txt|.pdf/i);
        if (!filePath.match(regExp)) {
            return false;
        }
        return true;
    }
    return false;
}

/**
 * 函数功能：检查上传的是否文件。
 * 参数说明：title 附件上传控件对象的标题。
 *         id 附件上传控件对象的ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 上传的不是文件。
 *         true 上传的是文件。
 */
function validateIsFile(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    if (!isFile(value)) {
        alert("对不起，您选择的“" + title + "”[" + value + "]不是文件，请重新选择！");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            /**
             //fileObj.value = EMPTY;//?X不支持
             fileObj.focus();
             */
            fileObj.select();
            //询问是否将当前选中区放入剪贴板后删除。
            //document.execCommand('cut');
            //删除当前选中区。
            document.execCommand('delete');
        } else if (MODE_SELECT == mode) {
            fileObj.select();
        }
        return false;
    }
    return true;
}

/**
 * 函数功能：验证日期格式是否合法。(yyyy-MM-dd)
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的日期格式不合法。
 *         true 文本框接受输入的日期格式合法。
 * 适用事件：onsubmit、onblur。
 */
function validateIsDate(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    //var regExp = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/;
    var regExp = new RegExp(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/);
    if (!regExp.test(value)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：yyyy-MM-dd）");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：验证日期时间格式是否合法。
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         pattern 日期时间格式。(目前支持yyyy-MM-dd、yyyy-MM-dd HH:mm:ss、yyyy-MM-dd HH:mm三种格式，pattern为null、空时默认为yyyy-MM-dd格式)
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的日期时间格式不合法。
 *         true 文本框接受输入的日期时间格式合法或内容为空。
 * 适用事件：onsubmit、onblur。
 */
function validateIsDateTime(title, id, pattern, mode) {
    var object = getObjectById(id);
    var flag = true;
    var value = object.value;
    /**
     if (isEmpty(value)) {
		return flag;
	}
     */
        //undefined、null、空格转换为空值。
    pattern = changeUndefinedNullBlankToEmpty(pattern);
    //年月日时分秒正则表达式。
    var regExp;
    var correctPattern;
    if (PATTERN_yMd == pattern) {
        //去除所有空格。
        value = replaceAllBlank(value);
        //年月日正则表达式。
        //regExp = /^(\d{1,4})\-(\d{1,2})\-(\d{1,2})$/;
        regExp = new RegExp(/^(\d{1,4})\-(\d{1,2})\-(\d{1,2})$/);
        correctPattern = PATTERN_yMd;
    } else if (PATTERN_yMdHms == pattern) {
        //去除字符串首尾空格。
        //value = value.trim();
        value = trim(value);
        //年月日时分秒正则表达式。
        regExp = new RegExp(/^(\d{1,4})\-(\d{1,2})\-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
        correctPattern = PATTERN_yMdHms;
    } else if (PATTERN_yMdHm == pattern) {
        //去除字符串首尾空格。
        value = trim(value);
        //年月日时分正则表达式。
        regExp = new RegExp(/^(\d{1,4})\-(\d{1,2})\-(\d{1,2}) (\d{1,2}):(\d{1,2})$/);
        correctPattern = PATTERN_yMdHm;
    } else {
        //去除所有空格。
        value = replaceAllBlank(value);
        //年月日正则表达式。（pattern为null、空时，默认为yyyy-MM-dd格式）
        regExp = new RegExp(/^(\d{1,4})\-(\d{1,2})\-(\d{1,2})$/);
        correctPattern = PATTERN_yMd;
    }
    var returnV = value.match(regExp);
    if (!isEmpty(returnV)) {
        var date;
        var num;
        if (PATTERN_yMd == pattern) {
            date = new Date(returnV[1], (returnV[2] - 1), returnV[3]);
            num = ((date.getFullYear() == returnV[1]) && ((date.getMonth() + 1) == returnV[2]) && (date.getDate() == returnV[3]));
        } else if (PATTERN_yMdHms == pattern) {
            date = new Date(returnV[1], (returnV[2] - 1), returnV[3], returnV[4], returnV[5], returnV[6]);
            num = ((date.getFullYear() == returnV[1]) && ((date.getMonth() + 1) == returnV[2]) && (date.getDate() == returnV[3])
                && (date.getHours() == returnV[4]) && (date.getMinutes() == returnV[5]) && (date.getSeconds() == returnV[6]));
        } else if (PATTERN_yMdHm == pattern) {
            date = new Date(returnV[1], (returnV[2] - 1), returnV[3], returnV[4], returnV[5]);
            num = ((date.getFullYear() == returnV[1]) && ((date.getMonth() + 1) == returnV[2]) && (date.getDate() == returnV[3])
                && (date.getHours() == returnV[4]) && (date.getMinutes() == returnV[5]));
        } else {
            date = new Date(returnV[1], (returnV[2] - 1), returnV[3]);
            num = ((date.getFullYear() == returnV[1]) && ((date.getMonth() + 1) == returnV[2]) && (date.getDate() == returnV[3]));
        }
        if (0 == num) {
            alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：" + correctPattern + "）");
            flag = false;
        }
    } else {
        alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：" + correctPattern + "）");
        flag = false;
    }
    if (!flag) {
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return flag;
    }
    object.value = value;
    return flag;
}

/**
 * 函数功能：检查前面的日期是否小于等于后面的日期且两个日期都不能大于指定的日期。
 * 参数说明：beginTitle 前面的日期标题。
 *         beginId 前面的日期对象ID。
 *         endTitle 后面的日期标题。
 *         endId 后面的日期对象ID。
 *         rangeValue 指定的日期，可以为null、空。
 *         flag 默认值为begin、end、null、空，前面的日期begin、后面的日期end、表单按钮null、空（具体参照ValidateForm.jsp页面应用示例）。
 *         mode 文档控件的行为模式。
 * 返回结果：false 前面的日期大于后面的日期、两个日期任一日期大于指定的日期（日期不为空）。
 *         true 前面的日期小于等于后面的日期、两个日期小于等于指定的日期（日期不为空）。
 * 适用事件：onsubmit、onblur。
 */
function validateDateRange(beginTitle, beginId, endTitle, endId, rangeValue, flag, mode) {
    var beginObject = getObjectById(beginId);
    var endObject = getObjectById(endId);
    var beginValue = beginObject.value;
    var endValue = endObject.value;
    //去除所有空格。
    beginValue = replaceAllBlank(beginValue);
    endValue = replaceAllBlank(endValue);
    var beginDateTime = 0, endDateTime = 0;
    if (!isEmpty(beginValue)) {
        //beginDateTime = new Date(replaceAllChar(beginValue, SPLIT_HORIZONTAL, SPLIT_COMMA)).getTime();//?X不支持
        var beginValueArray = beginValue.split(SPLIT_HORIZONTAL);
        beginDateTime = new Date(beginValueArray[0], beginValueArray[1], beginValueArray[2]).getTime();

        //beginDateTime = new Date(beginValue.replace(SPLIT_HORIZONTAL, SPLIT_COMMA)).getTime();
        //beginDateTime = eval(replaceAllChar(beginValue, SPLIT_HORIZONTAL, EMPTY));
    }
    if (!isEmpty(endValue)) {
        //endDateTime = new Date(replaceAllChar(endValue, SPLIT_HORIZONTAL, SPLIT_COMMA)).getTime();//?X不支持
        var endValueArray = endValue.split(SPLIT_HORIZONTAL);
        endDateTime = new Date(endValueArray[0], endValueArray[1], endValueArray[2]).getTime();

        //endDateTime = new Date(endValue.replace(SPLIT_HORIZONTAL, SPLIT_COMMA)).getTime();
        //endDateTime = eval(replaceAllChar(endValue, SPLIT_HORIZONTAL, EMPTY));
    }

    mode = changeUpperCase(mode);
    //检查日期是否小于等于指定的日期。
    if (!isEmpty(rangeValue)) {
        //var rangeDateTime = new Date(replaceAllChar(rangeValue, SPLIT_HORIZONTAL, SPLIT_COMMA)).getTime();//?X不支持
        var rangeValueArray = rangeValue.split(SPLIT_HORIZONTAL);
        endDateTime = new Date(rangeValueArray[0], rangeValueArray[1], rangeValueArray[2]).getTime();

        //var rangeDateTime = new Date(rangeValue.replace(SPLIT_HORIZONTAL, SPLIT_COMMA)).getTime();
        //var rangeDateTime = eval(replaceAllChar(rangeValue, SPLIT_HORIZONTAL, EMPTY));
        if (!isEmpty(beginValue)) {
            if (rangeDateTime < beginDateTime) {
                alert("对不起，您选择或输入的“" + beginTitle + "”[" + beginValue + "]不能大于最大日期[" + rangeValue + "]，请重新选择或输入！");
                if (MODE_FOCUS == mode) {
                    beginObject.value = EMPTY;
                    beginObject.focus();
                } else if (MODE_SELECT == mode) {
                    beginObject.select();
                }
                return false;
            }
        }
        if (!isEmpty(endValue)) {
            if (rangeDateTime < endDateTime) {
                alert("对不起，您选择或输入的“" + endTitle + "”[" + endValue + "]不能大于最大日期[" + rangeValue + "]，请重新选择或输入！");
                if (MODE_FOCUS == mode) {
                    endObject.value = EMPTY;
                    endObject.focus();
                } else if (MODE_SELECT == mode) {
                    endObject.select();
                }
                return false;
            }
        }
    }
    flag = changeUpperCase(flag);
    //检查前面的日期是否小于等于后面的日期。
    if ((!isEmpty(beginValue)) && (!isEmpty(endValue))) {
        if (!isEmpty(flag)) {
            if (FLAG_BEGIN == flag) {
                if (beginDateTime > endDateTime) {
                    alert("对不起，您选择或输入的“" + beginTitle + "”[" + beginValue + "]不能大于“" + endTitle + "”[" + endValue + "]，请重新选择或输入！");
                    if (MODE_FOCUS == mode) {
                        beginObject.value = EMPTY;
                        beginObject.focus();
                    } else if (MODE_SELECT == mode) {
                        beginObject.select();
                    }
                    return false;
                }
            } else if (FLAG_END == flag) {
                if (beginDateTime > endDateTime) {
                    alert("对不起，您选择或输入的“" + beginTitle + "”[" + beginValue + "]不能大于“" + endTitle + "”[" + endValue + "]，请重新选择或输入！");
                    if (MODE_FOCUS == mode) {
                        endObject.value = EMPTY;
                        endObject.focus();
                    } else if (MODE_SELECT == mode) {
                        endObject.select();
                    }
                    return false;
                }
            }
        } else {
            if (beginDateTime > endDateTime) {
                alert("对不起，您选择或输入的“" + beginTitle + "”[" + beginValue + "]不能大于“" + endTitle + "”[" + endValue + "]，请重新选择或输入！");
                if (MODE_FOCUS == mode) {
                    beginObject.value = EMPTY;
                    endObject.value = EMPTY;
                    beginObject.focus();
                } else if (MODE_SELECT == mode) {
                    //beginObject.select();
                    endObject.select();
                }
                return false;
            }
        }
    }
    beginObject.value = beginValue;
    endObject.value = endValue;
    return true;
}

/**
 * 函数功能：验证（中国移动）手机号码格式是否合法。(必须在规定的中国移动134、135、136、137、138、139、147、150、151、152、157、158、159、181、182、183、184、187、188号段范围内，其中147为上网卡)
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的（中国移动）手机号码格式不合法。
 *         true 文本框接受输入的（中国移动）手机号码格式合法。
 * 适用事件：onsubmit、onblur。
 */
function validateIsMobile(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    var regExp = new RegExp(REGEXP_MOBILE);
    if (!value.match(regExp)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：" + HINT_PHONE_MOBILE + "）");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：验证（中国联通）手机号码格式是否合法。(必须在规定的中国联通130、131、132、145、155、156、185、186号段范围内，其中145为上网卡)
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的（中国联通）手机号码格式不合法。
 *         true 文本框接受输入的（中国联通）手机号码格式合法。
 * 适用事件：onsubmit、onblur。
 */
function validateIsUnicom(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    var regExp = new RegExp(REGEXP_UNICOM);
    if (!value.match(regExp)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：" + HINT_PHONE_UNICOM + "）");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：验证（中国电信）手机号码格式是否合法。(必须在规定的中国电信133、153、180、189号段范围内)
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的（中国电信）手机号码格式不合法。
 *         true 文本框接受输入的（中国电信）手机号码格式合法。
 * 适用事件：onsubmit、onblur。
 */
function validateIsTeleco(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    var regExp = new RegExp(REGEXP_TELECO);
    if (!value.match(regExp)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：" + HINT_PHONE_TELECO + "）");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：验证（中国电信CDMA）手机号码格式是否合法。(必须在规定的中国电信CDMA133、153号段范围内)
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的（中国电信CDMA）手机号码格式不合法。
 *         true 文本框接受输入的（中国电信CDMA）手机号码格式合法。
 * 适用事件：onsubmit、onblur。
 */
function validateIsTelecoCDMA(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    var regExp = new RegExp(REGEXP_TELECO_CDMA);
    if (!value.match(regExp)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：" + HINT_PHONE_TELECO_CDMA + "）");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：验证电话号码（小灵通）格式是否合法。(0000-00000000)
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的电话号码格式不合法。
 *         true 文本框接受输入的电话号码格式合法。
 * 适用事件：onsubmit、onblur。
 */
function validateIsTelephone(title, id, mode) {
    var object = getObjectById(id);
    var value = object.value;
    //去除所有空格。
    value = replaceAllBlank(value);
    var regExp = new RegExp(REGEXP_TELEPHONE);
    if (!regExp.test(value)) {
        alert("对不起，您输入的“" + title + "”[" + value + "]格式不合法，请重新输入！" + LINE_SEPERATOR + "（正确格式：" + HINT_TELEPHONE + "）");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
        }
        return false;
    }
    object.value = value;
    return true;
}

/**
 * 函数功能：验证手机号码或电话号码（小灵通）格式是否合法。
 * 参数说明：title 文本框输入内容的标题。
 *         id 接受检查的文本框对象ID。
 *         type 手机号码和电话号码的验证模式。
 *         mode 文档控件的行为模式。
 * 返回结果：false 文本框接受输入的手机号码或电话号码格式不合法。
 *         true 文本框接受输入的手机号码或电话号码格式合法或内容为空。
 * 适用事件：onsubmit、onblur。
 */
function validateIsPhones(title, id, type, mode) {
    var object = getObjectById(id);
    var value = object.value;
    if (isEmpty(value)) {
        return true;
    }
    value = replaceAllChar(value, "，", ",");
    //有效元素 phoneArray[0]、重复元素 phoneArray[1]。
    var strArray = convertStrToArray(value);
    //手机号码 phoneArray[0]、电话号码 phoneArray[1]。
    var phoneArray = separateStrArray(strArray[0]);
    var illegalMobileArray, illegalUnicomArray, illegalTelecoArray, illegalTelecoCDMAArray, illegalTelephoneArray;
    type = changeUpperCase(type);
    //手机号码（中国移动）。
    if (TYPE_PHONE_MOBILE == type) {
        illegalMobileArray = getIllegalPhoneArray(REGEXP_PHONE_MOBILE, strArray[0]);
        //手机号码（中国联通）。
    } else if (TYPE_PHONE_UNICOM == type) {
        illegalUnicomArray = getIllegalPhoneArray(REGEXP_PHONE_UNICOM, strArray[0]);
        //手机号码（中国电信）。
    } else if (TYPE_PHONE_TELECO == type) {
        illegalTelecoArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO, strArray[0]);
        //手机号码（中国电信CDMA）。
    } else if (TYPE_PHONE_TELECO_CDMA == type) {
        illegalTelecoCDMAArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO_CDMA, strArray[0]);
        //电话号码。
    } else if (TYPE_TELEPHONE == type) {
        illegalTelephoneArray = getIllegalPhoneArray(REGEXP_TELEPHONE, phoneArray[1]);
        //手机号码（中国移动）或电话号码。
    } else if (TYPE_PHONE_MOBILE_TELEPHONE == type) {
        illegalMobileArray = getIllegalPhoneArray(REGEXP_PHONE_MOBILE, phoneArray[0]);
        illegalTelephoneArray = getIllegalPhoneArray(REGEXP_TELEPHONE, phoneArray[1]);
        //手机号码（中国联通）或电话号码。
    } else if (TYPE_PHONE_UNICOM_TELEPHONE == type) {
        illegalUnicomArray = getIllegalPhoneArray(REGEXP_PHONE_UNICOM, phoneArray[0]);
        illegalTelephoneArray = getIllegalPhoneArray(REGEXP_TELEPHONE, phoneArray[1]);
        //手机号码（中国电信）或电话号码。
    } else if (TYPE_PHONE_TELECO_TELEPHONE == type) {
        illegalTelecoArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO, phoneArray[0]);
        illegalTelephoneArray = getIllegalPhoneArray(REGEXP_TELEPHONE, phoneArray[1]);
        //手机号码（中国电信CDMA）或电话号码。
    } else if (TYPE_PHONE_TELECO_CDMA_TELEPHONE == type) {
        illegalTelecoCDMAArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO_CDMA, phoneArray[0]);
        illegalTelephoneArray = getIllegalPhoneArray(REGEXP_TELEPHONE, phoneArray[1]);
        //手机号码（中国移动、中国联通、中国电信、中国电信CDMA）。
    } else if (TYPE_PHONE == type) {
        illegalMobileArray = getIllegalPhoneArray(REGEXP_PHONE_MOBILE, strArray[0]);
        illegalUnicomArray = getIllegalPhoneArray(REGEXP_PHONE_UNICOM, illegalMobileArray);
        illegalTelecoArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO, illegalUnicomArray);
        illegalTelecoCDMAArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO_CDMA, illegalTelecoArray);
        illegalMobileArray = NVLL;
        illegalUnicomArray = NVLL;
        illegalTelecoArray = NVLL;
        //手机号码（中国移动、中国联通、中国电信、中国电信CDMA）或电话号码。
    } else if (TYPE_PHONE_TELEPHONE == type) {
        illegalMobileArray = getIllegalPhoneArray(REGEXP_PHONE_MOBILE, phoneArray[0]);
        illegalUnicomArray = getIllegalPhoneArray(REGEXP_PHONE_UNICOM, illegalMobileArray);
        illegalTelecoArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO, illegalUnicomArray);
        illegalTelecoCDMAArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO_CDMA, illegalTelecoArray);
        illegalTelephoneArray = getIllegalPhoneArray(REGEXP_TELEPHONE, phoneArray[1]);
        illegalMobileArray = NVLL;
        illegalUnicomArray = NVLL;
        illegalTelecoArray = NVLL;
        //type为null、空时，默认为手机号码（中国移动、中国联通、中国电信、中国电信CDMA）或电话号码。
    } else {
        illegalMobileArray = getIllegalPhoneArray(REGEXP_PHONE_MOBILE, phoneArray[0]);
        illegalUnicomArray = getIllegalPhoneArray(REGEXP_PHONE_UNICOM, illegalMobileArray);
        illegalTelecoArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO, illegalUnicomArray);
        illegalTelecoCDMAArray = getIllegalPhoneArray(REGEXP_PHONE_TELECO_CDMA, illegalTelecoArray);
        illegalTelephoneArray = getIllegalPhoneArray(REGEXP_TELEPHONE, phoneArray[1]);
        illegalMobileArray = NVLL;
        illegalUnicomArray = NVLL;
        illegalTelecoArray = NVLL;
    }
    var illegalELements = EMPTY;
    if (NVLL != illegalMobileArray && 0 != illegalMobileArray.length) {
        illegalELements += SPLIT_COMMA + convertArrayToStr(illegalMobileArray);
    }
    if (NVLL != illegalUnicomArray && 0 != illegalUnicomArray.length) {
        illegalELements += SPLIT_COMMA + convertArrayToStr(illegalUnicomArray);
    }
    if (NVLL != illegalTelecoArray && 0 != illegalTelecoArray.length) {
        illegalELements += SPLIT_COMMA + convertArrayToStr(illegalTelecoArray);
    }
    if (NVLL != illegalTelecoCDMAArray && 0 != illegalTelecoCDMAArray.length) {
        illegalELements += SPLIT_COMMA + convertArrayToStr(illegalTelecoCDMAArray);
    }
    if (NVLL != illegalTelephoneArray && 0 != illegalTelephoneArray.length) {
        illegalELements += SPLIT_COMMA + convertArrayToStr(illegalTelephoneArray);
    }
    //var repeatELements = convertArrayToStr(strArray[1]);
    var repeatELements = strArray[1].join();
    if (!isEmpty(repeatELements) || !isEmpty(illegalELements)) {
        var message = EMPTY;
        if (!isEmpty(repeatELements)) {
            message += "[" + repeatELements + "]重复";
            if (!isEmpty(illegalELements)) {
                illegalELements = illegalELements.substring(1);
                message += "、[" + illegalELements + "]格式不合法";
            }
        } else {
            if (!isEmpty(illegalELements)) {
                illegalELements = illegalELements.substring(1);
                message += "[" + illegalELements + "]格式不合法";
            }
        }
        if (!isEmpty(message)) {
            message = "对不起，您输入的“" + title + "”" + message
                + "，已过滤处理！" + LINE_SEPERATOR
                + "（中国移动手机号码：" + HINT_PHONE_MOBILE + "；"
                + "中国联通手机号码：" + HINT_PHONE_UNICOM + "；"
                + "中国电信手机号码：" + HINT_PHONE_TELECO + "；"
                + "中国电信CDMA手机号码：" + HINT_PHONE_TELECO_CDMA + "；"
                + "电话号码：" + HINT_TELEPHONE + "。）";
            alert(message);
        }
        /**
         alert("对不起，您输入的“" + title + "”[" + convertArrayToStr(strArray[1]) + "]重复、"
         + "[" + illegalMessage + "]格式不合法，已过滤处理！" + LINE_SEPERATOR
         + "（中国移动手机号码：" + HINT_PHONE_MOBILE + "；"
         + "中国联通手机号码：" + HINT_PHONE_UNICOM + "；"
         + "中国电信手机号码：" + HINT_PHONE_TELECO + "；"
         + "中国电信CDMA手机号码：" + HINT_PHONE_TELECO_CDMA + "；"
         + "电话号码：" + HINT_TELEPHONE + "。）");
         */
        if (!isEmpty(illegalELements)) {
            //将过滤后的值回填给对象。
            object.value = convertArrayToStr(removeElementFromArray((SPLIT_COMMA + illegalELements + SPLIT_COMMA), strArray[0]));
        }
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            //object.value = EMPTY;
            object.focus();
        } else if (MODE_SELECT == mode) {
            object.select();
            /**
             object.value = convertArrayToStr(strArray[0]);
             selectPhoneText(id, illegalELements);
             */
        }
        return false;
    }
    object.value = convertArrayToStr(strArray[0]);
    return true;
}

/**
 * 函数功能：用于分隔手机号码或电话号码组合的字符串，并返回数组。（可由英文状态逗号或空格分隔）
 * 参数说明：str 手机号码或电话号码组合的字符串。（如：13815428151 13815428152,025-52456321 13815428152 18912977541,025-52456321）
 * 返回结果：手机号码或电话号码组合的数组。（包括有效元素和重复元素数组）
 */
function convertStrToArray(str) {
    if (isEmpty(str)) {
        return null;
    }
    var strArray = new Array();
    strArray[0] = new Array();
    strArray[1] = new Array();
    var commaStrArray = str.split(SPLIT_COMMA);
    for (var i = 0; i < commaStrArray.length; i++) {
        if (!isEmpty(commaStrArray[i])) {
            if (-1 != commaStrArray[i].indexOf(SPLIT_BLANK)) {
                var blankStrArray = commaStrArray[i].split(SPLIT_BLANK);
                for (var j = 0; j < blankStrArray.length; j++) {
                    if (!isEmpty(blankStrArray[j])) {
                        //（有效元素）过滤重复的元素。
                        if (!isRepeat(trim(blankStrArray[j]), strArray[0])) {
                            strArray[0].push(trim(blankStrArray[j]));
                        } else {
                            //（重复元素）过滤重复的元素。
                            if (!isRepeat(trim(blankStrArray[j]), strArray[1])) {
                                strArray[1].push(trim(blankStrArray[j]));
                            }
                        }
                    }
                }
            } else {
                //（有效元素）过滤重复的元素。
                if (!isRepeat(trim(commaStrArray[i]), strArray[0])) {
                    strArray[0].push(trim(commaStrArray[i]));
                } else {
                    //（重复元素）过滤重复的元素。
                    if (!isRepeat(trim(commaStrArray[i]), strArray[1])) {
                        strArray[1].push(trim(commaStrArray[i]));
                    }
                }
            }
        }
    }
    return strArray;
}

/**
 * 函数功能：检查元素在数组中是否已存在。
 * 参数说明：element 需要查找的元素。
 *         array 需要查找的数组。
 * 返回结果：false 元素在数组中不存在。
 *         true 元素在数组中存在。
 */
function isRepeat(element, array) {
    var flag = false;
    if (NVLL == array) {
        return flag;
    }
    for (var i = 0; i < array.length; i++) {
        if (element == array[i]) {
            flag = true;
            break;
        }
    }
    return flag;
}

/**
 * 函数功能：用于分离手机号码和电话号码组合成的数组，并返回各自对应的二维数组。
 * 参数说明：strArray 手机号码和电话号码组合成的数组。
 * 返回结果：手机号码和电话号码各自对应的二维数组。
 */
function separateStrArray(strArray) {
    if (NVLL == strArray) {
        return NVLL;
    }
    var phoneArray = new Array();
    //手机号码。
    phoneArray[0] = new Array();
    //电话号码。
    phoneArray[1] = new Array();
    for (var i = 0; i < strArray.length; i++) {
        if (-1 != strArray[i].indexOf(SPLIT_HORIZONTAL)) {
            phoneArray[1].push(strArray[i]);
            continue;
        }
        phoneArray[0].push(strArray[i]);
    }
    return phoneArray;
}

/**
 * 函数功能：将不合法的手机号码或电话号码筛选出并保存至数组中。
 * 参数说明：regExpStr 筛选使用的正则表达式。
 *         phoneArray 需要进行筛选的由手机号码或电话号码组合成的数组。
 * 返回结果：不合法的手机号码或电话号码组成的数组。
 */
function getIllegalPhoneArray(regExpStr, phoneArray) {
    //if (isEmpty(regExpStr) || NVLL == phoneArray) {//isEmpty方法参数不能为正则表达式。
    if ((NVLL == regExpStr || EMPTY == regExpStr) || NVLL == phoneArray) {
        return NVLL;
    }
    var regExp = new RegExp(regExpStr);
    var illegalPhoneArray = new Array();
    for (var i = 0; i < phoneArray.length; i++) {
        if (!phoneArray[i].match(regExp)) {
            illegalPhoneArray.push(phoneArray[i]);
        }
    }
    return illegalPhoneArray;
}

/**
 * 函数功能：将手机号码或电话号码数组拼接成字符串。
 * 参数说明：array 需要拼接成字符串的手机号码或电话号码数组。
 * 返回结果：手机号码或电话号码数组拼接成的字符串。
 */
function convertArrayToStr(array) {
    var str = EMPTY;
    if (NVLL == array) {
        return str;
    }
    var length = array.length;
    for (var i = 0; i < length; i++) {
        str += array[i];
        if ((length - 1) != i) {
            str += SPLIT_COMMA;
        }
    }
    return str;
}

/**
 * 函数功能：移除数组元素。
 * 参数说明：elementStr 需要移除的数组元素字符串。（格式如：,025-52456320,18912977541,）
 *         array 需要移除元素的数组。
 * 返回结果：移除相应元素后的数组。
 */
function removeElementFromArray(elementStr, array) {
    if (!isEmpty(elementStr) && NVLL != array) {
        var length = array.length;
        for (var i = 0; i < length; i++) {
            if (-1 != elementStr.indexOf(SPLIT_COMMA + array[i] + SPLIT_COMMA)) {
                array.splice(i, 1);
                i--;
                length--;
            }
        }
    }
    return array;
}

/**
 * 函数功能：选中文本框或文本域中指定的手机号码或电话号码。
 * 参数说明：id 被选中的文本框或文本域对象ID。
 *         phones 需选中的手机号码或电话号码。
 * 返回结果：无。
 */
function selectPhoneText(id, phones) {
    if (isEmpty(phones)) {
        return;
    }
    var object = getObjectById(id);
    var value = object.value;
    var phoneArray = phones.split(SPLIT_COMMA);
    for (var i = 0; i < phoneArray.length; i++) {
        var begin = value.indexOf(phoneArray[i]);
        var end = phoneArray[i].length;
        selectText(id, begin, end);
        break;
    }
}

/**
 * 函数功能：选中文本框或文本域中指定的文本。（支持IE678）
 * 参数说明：id 文本框或文本域对象ID。
 *         begin 选中起始点。
 *         end 选中终止点。
 * 返回结果：无。
 */
function selectText(id, begin, end) {
    var object = getObjectById(id);
    //获取操作对象的TextRange。
    var textRange = object.createTextRange();
    //收缩，默认为true。
    textRange.collapse();
    //因为已收缩range，所以select后会将光标移到首部。
    textRange.select();
    //如果需要选中文本中的一部分，就需要创建Range。
    var range = document.selection.createRange();
    //移动起始点。
    //range.moveStart('character', begin);
    range.moveStart("character", begin);
    //移动终止点。
    //range.moveEnd('character', end);
    range.moveEnd("character", end);
    //选中起始点到终止点之间的字符。
    range.select();
    /**
     var object = getObjectById(id);
     var textRange = object.createTextRange();
     textRange.moveStart("character", begin);
     textRange.moveEnd("character", end);
     textRange.select();
     */
}

// 校验文本控件输入的值是否符合要求。
function validateTextValue(title, id, validateValue, mode) {
    var element = document.getElementById(id);
    var value = element.value;
    if (validateValue == value) {
        alert("对不起，您输入的“" + title + "”不能为：" + validateValue + "！");
        mode = changeUpperCase(mode);
        if (MODE_FOCUS == mode) {
            element.value = EMPTY;
            element.focus();
        } else if (MODE_SELECT == mode) {
            element.select();
        }
        return false;
    }
    return true;
}

function onValidateTextLength(event, title, validateId, hintId, maxLength, mode, flag) {
    //var validateObject = event.source;
    var validateObject = mini.get(validateId);
    var hintObject = mini.get(hintId);
    var validateValue = validateObject.getValue();
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
    mode = changeUpperCase(mode);
    if (NVLL != hintObject) {

        if (maxLength >= length) {
            hintObject.setValue(maxLength - length);
        } else {
            //mini.alert(errorText);
            event.errorText = errorText;
            event.isValid = false;
            //hintObject.setValue(0);
            validateObject.setValue(validateValue.substring(0, position));
            if (MODE_FOCUS == mode) {
                validateObject.focus();
            } else if (MODE_SELECT == mode) {
                validateObject.selectText();
            }
            return false;
        }
    } else {
        if (maxLength < length) {
            //mini.alert(errorText);
            event.errorText = errorText;
            event.isValid = false;
            //bug修改：取消自动截取功能，让用户自己截取
//            validateObject.setValue(validateValue.substring(0, position));
            if (MODE_FOCUS == mode) {
                validateObject.focus();
            } else if (MODE_SELECT == mode) {
                validateObject.selectText();
            }
            return false;
        }
    }
    return true;
}



/**
 *排序号验证
 */
function checkSort(e, value) {
    var strRegex = /^\d+$/;
    var re = new RegExp(strRegex);
    if (!re.test(e.value)) {
        mini.alert("排序号必须是正整数！", "提示", function() {
            e.value = value;
        });
    }
}