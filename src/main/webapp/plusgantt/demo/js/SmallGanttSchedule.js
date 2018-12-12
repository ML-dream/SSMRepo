/*
    GanttSchedule：甘特图功能逻辑模块
    描述：监听处理单元格编辑，以及条形图拖拽事件。
*/

function clearTime (date) {
    if (!date) return null;
    return new Date(date.getFullYear(), date.getMonth(), date.getDate());
}
function maxTime(date) {
    if (!date) return null;
    return new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59);
}

SmallGanttSchedule = function (gantt) {
    this.gantt = gantt;
    //处理单元格编辑，和条形图拖拽事件
    gantt.on("cellbeginedit", this.__OnCellBeginEdit, this);
    gantt.on("cellcommitedit", this.__OnCellCommitEdit, this);
    gantt.on("itemdragstart", this.__OnItemDragStart, this);
    gantt.on("itemdragcomplete", this.__OnItemDragComplete, this);
    gantt.on("taskdragdrop", this.__OnTaskDragDrop, this);
}
SmallGanttSchedule.prototype = {
    __OnTaskDragDrop: function (e) {
        e.cancel = true;
        var dragRecords = e.tasks, targetRecord = e.targetTask, action = e.action;

        this.gantt.moveTasks(dragRecords, targetRecord, action);
    },
    __OnCellBeginEdit: function (e) {
        var task = e.record, field = e.field;
        if (task.Summary) {
            if (field == 'Start' || field == 'Finish' || field == 'Duration') {
                e.cancel = true;
            }
        }
    },
    __OnCellCommitEdit: function (e) {
        e.cancel = true;
        var task = e.record, field = e.field, value = e.value, oldValue = task[field];

        if (mini.isEquals(oldValue, value)) return;

        try {

            //!!!处理单元格编辑提交
            var gantt = this.gantt;

            //标记任务的属性被修改
            gantt.setTaskModified(task, field);

            switch (field) {
                case "Duration":
                    task.Duration = value;

                    if (task.Start) {
                        task.Finish = new Date(task.Start.getTime() + task.Duration);

                        gantt.setTaskModified(task, "Finish");
                    }

                    break;
                case "Start":
                    task.Duration = task.Finish - task.Start;

                    task.Start = value;

                    if (task.Start) {
                        task.Finish = new Date(task.Start.getTime() + task.Duration);

                        gantt.setTaskModified(task, "Finish");
                    }

                    break;
                case "Finish":
                    task.Finish = value;

                    if (task.Finish && task.Start) {
                        
                        task.Duration = task.Finish - task.Start;

                        gantt.setTaskModified(task, "Duration");
                    }

                    break;
                case "PredecessorLink":
                    gantt.setLinks(task, value);
                    return;
                    break;
                default:
                    task[field] = value;

                    break;
            }

            gantt.refresh();

        } catch (ex) {
            alert(ex.message);
        }
    },
    __OnItemDragStart: function (e) {
        if (e.action == "start") {
            e.cancel = true;
        }
    },
    __OnItemDragComplete: function (e) {
        var action = e.action, value = e.value, task = e.item;

        //!!!处理条形图拖拽完成
        var gantt = this.gantt;


        if (action == "finish") {
            gantt.setTaskModified(task, "Finish");

            task.Finish = value;
            if (task.Finish && task.Start) {
                task.Duration = task.Finish - task.Start;

                gantt.setTaskModified(task, "Duration");
            }
        }
        if (action == "percentcomplete") {
            gantt.setTaskModified(task, "PercentComplete");

            task.PercentComplete = value;

        }
        if (action == "move") {
            gantt.setTaskModified(task, "Start");

            task.Duration = task.Finish - task.Start;

            task.Start =  value;

            if (task.Start) {
                task.Finish = new Date(task.Start.getTime() + task.Duration);

                gantt.setTaskModified(task, "Finish");
            }
        }

        gantt.refresh();
    }
};