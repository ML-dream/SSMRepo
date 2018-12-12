/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
/**
* Simplified Chinese translation
* By DavidHu
* 09 April 2007
* 
* update by andy_ghg
* 2009-10-22 15:00:57
*/
Ext.onReady(function ()
{
    if (Ext.Updater)
    {
        Ext.Updater.defaults.indicatorText = '<div class="loading-indicator">加载中...</div>';
    }

    if (Ext.view.View)
    {
        Ext.view.View.prototype.emptyText = "";
    }

    if (Ext.grid.Panel)
    {
        Ext.grid.Panel.prototype.ddText = "选择了 {0} 行";
    }

    if (Ext.TabPanelItem)
    {
        Ext.TabPanelItem.prototype.closeText = "关闭此标签";
    }

    if (Ext.form.field.Base)
    {
        Ext.form.field.Base.prototype.invalidText = "输入值非法";
    }

    if (Ext.LoadMask)
    {
        Ext.LoadMask.prototype.msg = "读取中...";
    }

    if (Ext.Date)
    {
        Ext.Date.monthNames = [
           "一月",
           "二月",
           "三月",
           "四月",
           "五月",
           "六月",
           "七月",
           "八月",
           "九月",
           "十月",
           "十一月",
           "十二月"
        ];

        Ext.Date.dayNames = [
           "日",
           "一",
           "二",
           "三",
           "四",
           "五",
           "六"
        ];

        Ext.Date.formatCodes.a = "(this.getHours() < 12 ? '上午' : '下午')";
        Ext.Date.formatCodes.A = "(this.getHours() < 12 ? '上午' : '下午')";
    }

    if (Ext.MessageBox)
    {
        Ext.MessageBox.buttonText = {
            ok: "确定",
            cancel: "取消",
            yes: "是",
            no: "否"
        };
    }

    if (Ext.util.Format)
    {
        Ext.apply(Ext.util.Format, {
            thousandSeparator: '.',
            decimalSeparator: ',',
            currencySign: '\u00a5',  // Chinese Yuan
            dateFormat: 'y年m月d日'
        });
    }

    if (Ext.picker.Date)
    {
        Ext.apply(Ext.picker.Date.prototype, {
            todayText: "今天",
            minText: "日期必须大于最小允许日期", //update
            maxText: "日期必须小于最大允许日期", //update
            disabledDaysText: "",
            disabledDatesText: "",
            monthNames: Ext.Date.monthNames,
            dayNames: Ext.Date.dayNames,
            nextText: '下个月 (Ctrl+Right)',
            prevText: '上个月 (Ctrl+Left)',
            monthYearText: '选择一个月 (Control+Up/Down 来改变年份)', //update
            todayTip: "{0} (空格键选择)",
            format: "y年m月d日"
        });
    }

    if (Ext.picker.Month)
    {
        Ext.apply(Ext.picker.Month.prototype, {
            okText: "确定",
            cancelText: "取消"
        });
    }

    if (Ext.toolbar.Paging)
    {
        Ext.apply(Ext.PagingToolbar.prototype, {
            beforePageText: "第", //update
            afterPageText: "页,共 {0} 页", //update
            firstText: "第一页",
            prevText: "上一页", //update
            nextText: "下一页",
            lastText: "最后页",
            refreshText: "刷新",
            displayMsg: "显示 {0} - {1}条，共 {2} 条", //update
            emptyMsg: '没有数据'
        });
    }

    if (Ext.form.field.Text)
    {
        Ext.apply(Ext.form.field.Text.prototype, {
            minLengthText: "该输入项的最小长度是 {0} 个字符",
            maxLengthText: "该输入项的最大长度是 {0} 个字符",
            blankText: "该输入项为必输项",
            regexText: "",
            emptyText: null
        });
    }

    if (Ext.form.field.Number)
    {
        Ext.apply(Ext.form.field.Number.prototype, {
            minText: "该输入项的最小值是 {0}",
            maxText: "该输入项的最大值是 {0}",
            nanText: "{0} 不是有效数值"
        });
    }

    if (Ext.form.field.Date)
    {
        Ext.apply(Ext.form.field.Date.prototype, {
            disabledDaysText: "禁用",
            disabledDatesText: "禁用",
            minText: "该输入项的日期必须在 {0} 之后",
            maxText: "该输入项的日期必须在 {0} 之前",
            invalidText: "{0} 是无效的日期 - 必须符合格式： {1}",
            format: "y年m月d日"
        });
    }

    if (Ext.form.field.ComboBox)
    {
        Ext.apply(Ext.form.field.ComboBox.prototype, {
            loadingText: "加载中...",
            valueNotFoundText: undefined
        });
    }

    if (Ext.form.field.VTypes)
    {
        Ext.apply(Ext.form.field.VTypes, {
            emailText: '该输入项必须是电子邮件地址，格式如： "user@example.com"',
            urlText: '该输入项必须是URL地址，格式如： "http:/' + '/www.example.com"',
            alphaText: '该输入项只能包含半角字母和_', //update
            alphanumText: '该输入项只能包含半角字母,数字和_'//update
        });
    }
    //add HTMLEditor's tips by andy_ghg
    if (Ext.form.field.HtmlEditor)
    {
        Ext.apply(Ext.form.field.HtmlEditor.prototype, {
            createLinkText: '添加超级链接:',
            buttonTips: {
                bold: {
                    title: '粗体 (Ctrl+B)',
                    text: '将选中的文字设置为粗体',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                italic: {
                    title: '斜体 (Ctrl+I)',
                    text: '将选中的文字设置为斜体',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                underline: {
                    title: '下划线 (Ctrl+U)',
                    text: '给所选文字加下划线',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                increasefontsize: {
                    title: '增大字体',
                    text: '增大字号',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                decreasefontsize: {
                    title: '缩小字体',
                    text: '减小字号',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                backcolor: {
                    title: '以不同颜色突出显示文本',
                    text: '使文字看上去像是用荧光笔做了标记一样',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                forecolor: {
                    title: '字体颜色',
                    text: '更改字体颜色',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                justifyleft: {
                    title: '左对齐',
                    text: '将文字左对齐',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                justifycenter: {
                    title: '居中',
                    text: '将文字居中对齐',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                justifyright: {
                    title: '右对齐',
                    text: '将文字右对齐',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                insertunorderedlist: {
                    title: '项目符号',
                    text: '开始创建项目符号列表',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                insertorderedlist: {
                    title: '编号',
                    text: '开始创建编号列表',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                createlink: {
                    title: '转成超级链接',
                    text: '将所选文本转换成超级链接',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                },
                sourceedit: {
                    title: '代码视图',
                    text: '以代码的形式展现文本',
                    cls: Ext.baseCSSPrefix + 'html-editor-tip'
                }
            }
        });
    }


    if (Ext.grid.header.Container)
    {
        Ext.apply(Ext.grid.header.Container.prototype, {
            sortAscText: "正序", //update
            sortDescText: "倒序", //update
            lockText: "锁定列", //update
            unlockText: "解除锁定", //update
            columnsText: "列"
        });
    }

    if (Ext.grid.PropertyColumnModel)
    {
        Ext.apply(Ext.grid.PropertyColumnModel.prototype, {
            nameText: "名称",
            valueText: "值",
            dateFormat: "y年m月d日"
        });
    }

    if (Ext.layout.BorderLayout && Ext.layout.BorderLayout.SplitRegion)
    {
        Ext.apply(Ext.layout.BorderLayout.SplitRegion.prototype, {
            splitTip: "拖动来改变尺寸.",
            collapsibleSplitTip: "拖动来改变尺寸. 双击隐藏."
        });
    }
});

function getIFrameDOM(id) {//兼容IE、Firefox的iframe DOM获取函数
    return document.getElementById(id).contentDocument || document.frames[id].document;
}

//可考虑使用SimpleIFrame
function addTab(url, text) {
    if (Ext.isIE) {
        //document.getElementById('tabifr').src = 'about:blank';
        //getIFrameDOM('tabifr').close();
        //getIFrameDOM('tabifr').clear();
        //document.getElementById('tabifr').parentNode.removeChild(document.getElementById('tabifr'));
        Ext.get('tabifr').remove();
        Ext.getCmp('mainTabPanel').removeAll(true);
        //CollectGarbage();
        Ext.getCmp('mainTabPanel').update({ src: url }); 
        //window.setTimeout(CollectGarbage, 10000);
        return;
//        //iframe.removeChild(iframe.childNodes); 
//        iframe.src = 'about:blank'; //"javascript:false";
//        tabifr.close();
//        CollectGarbage(); 
//        //tabifr.writeln("<span style='font-size:12px;'>正在加载，请稍候...</span>");
//        tabifr.writeln("<img style='position:absolute; top:50%; left:50%; margin: -64px 0 0 -64px;' src='/content/images/loading.gif'/>");
//        tabifr = null;
//        //document.getElementById('tabifr').src = "javascript:false"; //'about:blank';//
//        //document.getElementById('tabifr').src='/simple.htm';
//        window.setTimeout(function () {  CollectGarbage(); iframe.src = url; }, 1000);
        
//        return;
    }
    else {
        var Id = "tab" + hashCode(url), mainTabPanel = Ext.getCmp('mainTabPanel'), tab = Ext.getCmp(Id);
        if (tab) {
            //mainTabPanel.remove(Id); // 如果该选项卡面板里已有选项卡，先将其移除
            mainTabPanel.setActiveTab(tab); //激活
        }
        else {
            tab = {
                xtype: 'container',
                id: Id, // 此id 不是html元素的ID
                closable: true,
                tabConfig: {
                    //title: 'Custom Title',
                    tooltip: text
                },
                //autoDestroy: true,
                //autoShow: true,
                title: text.length > 16 ? text.slice(0, 16) + '...' : text,
                //tooltip: text,
                //tooltipType: 'title',
                //hideMode: 'offsets', //在隐藏时保持面板尺寸
                html: Ext.String.format('<iframe id="frame_{0}" name="{0}" src="{1}" width="100%" height="100%" frameborder="0" ></iframe>', Id, url),
                listeners: {
                    beforeclose: function (ptab) {
                        //source.getEl().dom.getElementsByTagName("iframe")[0].src = "javascript:false";
                        //var iframe = source.getEl().dom.getElementsByTagName("iframe")[0];
                        //iframe.src = 'javascript:false';
                        //iframe.parentNode.removeChild(iframe);
                        debugger;
                        if (ptab.ownerCt.tabBacks) {
                            while (Ext.Array.contains(ptab.ownerCt.tabBacks, ptab.id)) {
                                Ext.Array.remove(ptab.ownerCt.tabBacks, ptab.id);
                            }
                            if (ptab.ownerCt.getActiveTab() == ptab) {
                                var tid = ptab.ownerCt.tabBacks.pop();
                                ptab.ownerCt.setActiveTab(Ext.getCmp(tid));
                            }
                        }
                    },
                    activate: function (p) {
                        debugger;
                        if (!mainTabPanel.tabBacks) {
                            mainTabPanel.tabBacks = [];
                        }
                        mainTabPanel.tabBacks.push(tab.id);
                        //Ext.Array.splice(mainTabPanel.tabBacks, 0, mainTabPanel.tabBacks.length - 5);
                        //this.doLayout();
                        var iframeDom = getIFrameDOM("frame_" + this.id);
                        if (iframeDom && iframeDom.tabCallback) {
                            iframeDom.tabCallback();  // 如果在激活时需要触发事件，在页内注册：document.tabCallback = function () {}
                        }
                    }
                    /*render: function(){ this.ownerCt.doLayout(); }*/
                }
            };
            mainTabPanel.add(tab);
            mainTabPanel.setActiveTab(Ext.getCmp(tab.id));

            //        tab = mainTabPanel.add(Ext.create('Ext.ux.SimpleIFrame', {
            //            id: Id, // 此id 不是html元素的ID
            //            closable: true,
            //            //autoDestroy: true,
            //            autoShow: true,
            //            title: text,
            //            border: false,
            //            src: url,
            //            listeners: {
            //                deactivate: function () { this.doLayout(); }
            //            }
            //        }));

            //mainTabPanel.doLayout();
            //        tab.on("beforeactivate", function (t)
            //        {
            //            debugger;
            //            tabActiveOrders.push(t.id);
            //        });
            //        tab.on("removed", function (t, ownerCt)
            //        {
            //            debugger;
            //            var tab = Ext.getCmp(tabActiveOrders.pop());
            //            while (!tab)
            //            {
            //                tab = Ext.getCmp(tabActiveOrders.pop());
            //            }
            //            if (tab)
            //                ownerCt.setActiveTab(tab);
            //        });
        }
    }
}

//<a href="javascript:this.parent.addTab('/system/auth/privilege','test')">parent.addTab</a>
//<a href="javascript:this.parent.closeMe(window)">parent.closeme()</a> 
function closeMe(iframe) {
    debugger;
    var ptab = Ext.getCmp(iframe.name);
    if (ptab.ownerCt.tabBacks) {
        while (Ext.Array.contains(ptab.ownerCt.tabBacks, ptab.id)) {
            Ext.Array.remove(ptab.ownerCt.tabBacks, ptab.id);
        }
        if (ptab.ownerCt.getActiveTab() == ptab) {
            var tid = ptab.ownerCt.tabBacks.pop();
            ptab.ownerCt.setActiveTab(Ext.getCmp(tid));
        }
    }
    ptab.ownerCt.remove(ptab, true);
}

function hashCode(str)
{
    //同一URL返回不同的值
    var now = new Date();
    //str = str + now.toTimeString() + now.getMilliseconds();

    var hash = 18951163698;

    for (var i = 0; i < str.length; i++)
    {
        hash ^= ((hash << 5) + str.charCodeAt(i) + (hash >> 2));
    }

    return (hash & 0x7FFFFFFF);
}

/*Ext.example = {};
Ext.example.msg = function (title, format)
{
    if (!this.msgCt)
    {
        this.msgCt = Ext.core.DomHelper.insertFirst(document.body, { 'class': 'msg-div' }, true);
    }

    var s = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1));
    var m = Ext.core.DomHelper.append(this.msgCt, { html: '<div class="msg"><h3>' + title + '</h3><a href=javascript:addTab("/office/myOffice/myMessage","我的消息")>' + s + '</a><img src="/content/images/cross.gif" alt="" style="position:absolute;top:6px;right:6px; cursor:pointer; border:none;" id="msgClose"/></div>' }, true);
    Ext.get('msgClose').on('click', function (img) { this.hide(); }, m);
    m.hide(false).slideIn('b');
    m.pause(3);
    m.ghost("b", { delay: 4000, remove: true });
};
*/

Ext.example = {};
Ext.example.msg = function (title, format) {
    //debugger;
    if (!this.content) {
        //debugger;
        var msgCt = Ext.core.DomHelper.insertFirst(document.body, { 'class': 'msg-div' }, true);
        this.content = Ext.core.DomHelper.append(msgCt, '<div class="msg"><h3>' + title + '</h3><a id="hrefMyMsg" href=javascript:{addTab("/office/myOffice/myMessage","我的消息")}>' + Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1)) + '</a><img src="/content/images/action_delete.gif" alt="" style="position:absolute;top:6px;right:6px; cursor:pointer; border:none;" id="msgClose"/></div>', true);
        Ext.get('msgClose').on('click', function (img) {
            //debugger;
            this.setY(Ext.getBody().getHeight() + 10);
        }, this.content);
    }
    else {
        Ext.get('hrefMyMsg').dom.innerText = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1));
    }
    //Ext.get('hrefMyMsg').dom.innerText = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1));
    //alert(Ext.get('hrefMyMsg').dom.innerText);
    //m.setVisible(false);m.hide();
    this.content.animate({
        duration: 10000,
        //        from: { opacity: 0.75 },
        //        to: {
        //            x: Ext.getBody().getWidth() - 310,
        //            y: Ext.getBody().getHeight() - this.content.getHeight() - 10,
        //            opacity: 1
        //        },
        keyframes: {
            0: { opacity: 0.75 },
            20: {
                x: Ext.getBody().getWidth() - 310,
                y: Ext.getBody().getHeight() - this.content.getHeight() - 10
            },
            80: {},
            100: {
                y: Ext.getBody().getHeight() + 10
            }
        }
    });
    //    this.msgCt.animate({
    //        duration: 3000
    //    });
    //    //m.slideIn('b');
    //    //m.pause(3);
    //    //m.ghost("b", { delay: 6000, remove: false });
    //    this.msgCt.animate({
    //        duration: 3000,
    //        to: {
    //            y: Ext.getBody().getHeight() + 10
    //        }
    //    });
};

function toLocalWeekDay(en)
{
    switch (en.toLowerCase())
    {
        case 'sunday':
            return '星期天';
            break;
        case 'monday':
            return '星期一';
            break;
        case 'tuesday':
            return '星期二';
            break;
        case 'wednesday':
            return '星期三';
            break;
        case 'thursday':
            return '星期四';
            break;
        case 'friday':
            return '星期五';
            break;
        case 'saturday':
            return '星期六';
            break;
        default: break;
    }
}

// 生成GUID
function GUID()
{
    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}
function S4()
{
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}

function myRound(num, digit) {
    debugger;
    var str = num.toString();
    var fix = 0;
    var strs = str.split(".");
    if (strs.length > 1) {
        fix = Math.pow(10, -(Math.max(strs[1].length, digit + 1)));
    }
    //alert(Number(parseFloat(num) + parseFloat(fix)).toFixed(digit));
    return Number(parseFloat(num) + parseFloat(fix)).toFixed(digit);
}

var MBDefault = {
    getValue: function (name) {
        if (Ext.util.Cookies.get(name)) {
            return Ext.util.Cookies.get(name);
        }
        var Nowdate = new Date(), currentDay = (Nowdate.getDay() == 0 ? 7 : Nowdate.getDay()) - 1, result, msInDay = 86400000/*24 * 60 * 60 * 1000*/;
        //if (currentDay == 0) { currentDay = 7 } //符合习惯,周日作为一周的最后一天,周一作为第一天
        switch (name) {
            case '@now':
                result = Nowdate;
                break;
            case '@YearFirstDay':
                result = new Date(Nowdate.getFullYear(), 1, 1);
                break;
            case '@YearLastDay':
                result = new Date(Nowdate.getFullYear(), 12, 31);
                break;
            case '@WeekFirstDay':
                debugger;
                result = new Date(Nowdate - currentDay * msInDay);
                break;
            case '@WeekLastDay':
                //result = new Date((new Date(Nowdate - (Nowdate.getDay() - 1) * 24 * 60 * 60 * 1000) / 1000 + 6 * 24 * 60 * 60 * 1000) * 1000);
                result = new Date(Nowdate - currentDay * msInDay + 6 * msInDay); // IE中必须先减再加!!!
                break;
            case '@MonthFirstDay':
                result = new Date(Nowdate.getFullYear(), Nowdate.getMonth(), 1);
                break;
            case '@MonthLastDay':
                result = new Date(new Date(Nowdate.getFullYear(), Nowdate.getMonth() + 1, 1) - msInDay);
                break;
            default:
                return null;
                break;
        }

        return result;
    }
};

function Html2Text(html) {
    if (html) {
        return html.replace(/<[^>].*?>/g, '');
    }
    else {
        return '';
    }
}