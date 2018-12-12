$(function () {
    // $(".ibox-content").height($(window).height() - 80);
    // f_num();
    /*设施使用时间*/
    // f_del();
    /*订单内容删除*/
    // f_serve();
    /*服务设施*/
    // f_shooes();
    /*表格設置寬度*/
    // rightControl();
    // leftControl();
    /*rightControl();
    leftControl();
    $(".icon-serve .icon-yingyuanxinxizhoubiansheshi").hover(function () {
        $(this).addClass("hover");
    }, function () {
        $(this).removeClass("hover");
    });*/
});
function f_num() {
    //加号
    $(".add").click(function () {
        var $parent = $(this).parent(".num");
        var $num = window.Number($(".inputBorder", $parent).val());
        $(".inputBorder", $parent).val($num + 1);
    });

    //减号
    $(".minus").click(function () {
        var $parent = $(this).parent(".num");
        var $num = window.Number($(".inputBorder", $parent).val());
        if ($num > 2) {
            $(".inputBorder", $parent).val($num - 1);
        } else {
            $(".inputBorder", $parent).val(0);
            layer.open({
                content: '您确定要删除此服务设施吗？',
                btn: ['确定', '取消'],
                yes: function (index) {
                    location.reload();
                    layer.close(index);
                }
            });
        }
    });
};
function f_del() {
    $(".icon-shanchu").click(function () {
        $(this).parent().hide(500);
    })
};
$('.selectTrue').on('click', function () {
    $(this).addClass("selected");
    $(this).removeClass("selectTrue");
    $("#orderContent").append("<div class=\"col-xs-12 white-bg p-n m-t animated fadeInDown\">" +
        "<div class=\"col-xs-12 p-xxs\">" +
        "<label class=\"col-xs-3 p-n m-b-n text-right\">时间：</label>" +
        "<div class=\"col-xs-9 word-wrap\">2016-10-17 10:17 </div>" +
        "</div><div class=\"col-xs-12 p-xxs\">" +
        "<label class=\"col-xs-3 p-n m-b-n text-right\">场地：</label>" +
        "<div class=\"col-xs-9 word-wrap\">篮球场 </div>" +
        "</div><i class=\"iconfont icon-shanchu text-danger\"></i></div>")
    f_del();

})
$('dd').mouseover(function (event) {
    if ($(this).hasClass('selectFalse')) {
        $(this).css("cursor", 'not-allowed');
    }
});

// function f_searchUser(){
//     layer.confirm('您还不是我们的会员，是否添加为新会员？', {
//         btn: ['确定','取消'],
//         yes: function(index, layero){
//             //按钮【按钮一】的回调
//             $("#f_searchUser input").removeAttr("disabled");
//             $("#f_searchUser select").removeAttr("disabled");
//             $(".f_searchUser").text("添加会员");
//             layer.close(index);
//         },
//         btn2: function(index, layero){
//             layer.close(index);//按钮【按钮二】的回调
//         }
//     })
// }
function f_serve() {
    $(".icon-serve").mouseover(function () {
        $(this).addClass("serve-jia").parent().siblings().children().removeClass("serve-jia");
    });//这个是鼠标键，是你鼠标放上去的效果*/
    $(".icon-serve").mouseout(function () {
        $(this).removeClass("serve-jia").parent().siblings().children().removeClass("serve-jia");
    })
    $(".icon-serve").mousedown(function () {
        $(this).addClass("serve-shooes");
        if ($(this).hasClass("serve-shooes")) {
            $("#orderContent").append("<div class=\"col-xs-12 white-bg p-n m-t animated fadeInDown\">" +
                "<div class=\"col-xs-12 p-xxs\">" +
                "<label class=\"col-xs-3 p-n m-b-n text-right m-t-xs\">灯光：</label>" +
                "<div class=\"col-xs-9 word-wrap\">" +
                "<span class=\"num\">" +
                "<button type=\"button\" class=\"minus\" >-</button>" +
                "<input type=\"text\" class=\"inputBorder\" value=\"1\" />" +
                "<button type=\"button\" class=\"add\"  >+</button>" +
                "</span><span class=\"span-control m-l-sm\">小时</span>" +
                "</div></div><i class=\"iconfont icon-shanchu text-danger\"></i></div>")
        }
        f_del();
    })
    $(".icon-serve span").mouseup(function () {
        $(this).addClass("serve-shooes").parent().siblings().children().removeClass("serve-shooes");
    })//这个是鼠标键，是你鼠标左击放开后的效果*/
};
function f_shooes() {
    $(".grid dd").on("mouseover", function () {
        if ($(this).hasClass("shoose-dis")) {
            $(this).css("cursor", "not-allowed");
        }
    })
};


function rightControl() {
    //向右按钮点击事件
    var index = 0;
    var liLen;
    $('.control_right').on('click', function () {
        index++;
        liLen = $("ul.qg_icon li").length;//获取长度
        console.log(liLen);
        if (index >= liLen) {
            $("ul.qg_icon").stop();
            // alert("已经到达最后一页！");
        } else {
            $("ul.qg_icon").animate({left: -index * 90}, 700);
        }
    });
};
function leftControl() {

    //向左按钮点击事件
    var index = 0;
    var liLen;
    $(".control_left").on('click', function () {
        index--;
        liLen = $("ul.qg_icon li").length;//获取长度
        if (index == 0) {
            $("ul.qg_icon").stop();

        } else {
            $("ul.qg_icon").animate({right: index * 90}, 700);
        }
    });
}

