/**
 * Created by lwl on 2015/9/22.
 */


(function($){
    $.fn.serializeJson=function(text){
        var serializeObj={};
        $(this.serializeArray()).each(function(){
            serializeObj[this.name] = this.value;
        });
        return serializeObj;
    };

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
})(jQuery);

var Tool = {
    getObjArray :function (data,str){
        var arr = []
        if(data != null && data.length > 0){
            for(var i = 0; i < data.length;i++){
                arr[i] = data[i][str]
            }
        }
        return arr;
    },
    getPageStr: function (page,text) {
        var str = "";
        if (page == null)
            return str;
        else {
            return text + "pageIndex=" + page.pageIndex + "&pageSize=" + page.pageSize;
        }
    },
    getTableHeight : function(){
        var height = $(document).height() - 31;
        $(".mini-panel-viewport.mini-grid-viewport").height(height - $("fieldset").height() - $("#exp").height());
        $("#grid1").height(height - $("fieldset").height() - $("#exp").height());
    },
    getListTagHeight: function(){
        var height = $(document).height() - 7;
        $(".mini-panel-viewport.mini-grid-viewport").height(height - $(".mini-toolbar").height());
        $("#grid1").height(height - $("fieldset").height() - $(".mini-toolbar").height());

    }
}

