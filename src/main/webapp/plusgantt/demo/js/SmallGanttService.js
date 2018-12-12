/* 定义一些常用、通用的方法，供外部调用
-----------------------------------------------------------------------------*/

function CreateGantt() {

    var gantt = new PlusGantt();

    //创建甘特图调度插件
    new SmallGanttSchedule(gantt);

    gantt.setStyle("width:800px;height:400px");

    gantt.setColumns([
        { header: "", field: "ID", width: 30, cellCls: "mini-indexcolumn", align: "center" },
        { header: mini.Gantt.Name_Text, field: "Name", width: 180, name: "taskname",
            editor: { type: "textbox" }
        },


        { header: mini.Gantt.Start_Text, field: "Start", dateFormat: "yyyy-MM-dd H:mm",
            editor: {
                type: "datepicker", format:"yyyy-MM-dd H:mm", showTime:"true"
            }
        },
        { header: mini.Gantt.Finish_Text, field: "Finish", dateFormat: "yyyy-MM-dd H:mm",
            editor: {
                type: "datepicker", format: "yyyy-MM-dd H:mm", showTime: "true"
            }
        },
        { header: mini.Gantt.Duration_Text, field: "Duration",
            editor: {
                type: "spinner", minValue: 0, maxValue: 1000
            }
        },
        { header: mini.Gantt.PredecessorLink_Text, field: "PredecessorLink",
            editor: {
                type: "textbox"
            },
            oncellbeginedit: function (e) {
                var table = e.source, gantt = table.owner;
                var links = e.value;
                e.value = gantt.getLinkString(links);
            }
        },
        { header: mini.Gantt.PercentComplete_Text, field: "PercentComplete",
            editor: {
                type: "spinner", minValue: 0, maxValue: 100
            }
        }
    ]);
    //设置节点列
    gantt.setTreeColumn("taskname");

    gantt.on("drawcell", function (e) {
        var task = e.task;
        if (e.column.field == "Name" && e.task.Summary) {
            e.cellHtml = '<b>' + e.cellHtml + '</b>';
        }

        if (e.column.field == "PredecessorLink") {
            e.cellHtml = gantt.getLinkString(e.value);
        }

        if (e.column.field == "Duration") {
            if (task.Duration) {
                e.cellHtml = parseInt(task.Duration / (3600 * 1000)) + "分钟";
            } else {
                e.cellHtml = 0;
            }
        }
    });

    return gantt;
}

