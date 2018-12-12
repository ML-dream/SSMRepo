/**
 * 设置表单控件
 * @param {} formObject 表单主键
 * @param {} instanceId 业务数据主键
 * @param {} dataItemList 主表数据项列表
 * @param {} childDataItemList 子表数据项列表
 * @param {} childTableList 子表表名列表
 */
function setFormField(formObject, instanceId, dataItemList, childDataItemList, childTableList) {

    if (null != dataItemList && dataItemList.length > 0) {
        //遍历数据项数据，填充控件数据到表单中
        for (var i = 0; i < dataItemList.length; i++) {
            var dataItem = dataItemList[i];				//数据项
            var nextNewLine = 0;							//下一个控件是否换行标识
            var nextDataItem;								//下一个数据项控件
            if (dataItem.META_PRIMARY_KEY == 1 || dataItem.META_FOREIGN_KEY == 1) {    //主外键
                continue;
            }
            //当前控件不是最后一个控件
            if (i < dataItemList.length - 1) {
                nextDataItem = dataItemList[i + 1];

                var nextAccessType = nextDataItem.ACCESS_TYPE;		//下一个控件访问权限
                if (nextAccessType != "HIDDEN") {                    //控件访问权限类型不是“隐藏”类型
                    nextNewLine = nextDataItem.META_FORCE_LINE;
                    if (nextNewLine == 1) {    //换行
                        formObject.add(getFormField(dataItem));

                    } else {                //不换行，一行显示两个控件
                        formObject.add({
                            xtype: "fieldcontainer",
                            fieldDefaults: { labelAlign: 'left', labelWidth: 70, flex: 1},
                            layout: {
                                type: 'hbox'
                            },
                            items: [
                                getFormField(dataItem),
                                {
                                    xtype: 'splitter',
                                    width: "5%"
                                },
                                getFormField(nextDataItem)
                            ]
                        });
                        i++;
                    }
                } else {
                    formObject.add(getFormField(dataItem));
                }
            } else {
                formObject.add(getFormField(dataItem));
            }
        }

        //添加子表列表

        if (null != childTableList && 0 < childTableList.length) {
            formObject.add({
                id: 'childTab',
                xtype: 'tabpanel',
                //				activeTab: 0,
                flex: 1,
                margin: "10 20 0 10",
                listeners:{
                    tabchange: function(tabPanel, newCard, oldCard) {
                        Ext.getCmp(newCard.getItemId()).getStore().load();
                    }
                }
            });

            //子表面板对象
            var childTab = Ext.getCmp("childTab");

            for (var index = 0; index < childTableList.length; index++) {
                var childTable = childTableList[index];
                //子表数据项数据
                var childDataItem = childDataItemList[childTable.META_NUM];
                //访问权限为隐藏，不显示
                if (null != childTable.ACCESS_TYPE && childTable.ACCESS_TYPE == 'HIDDEN') {
                    continue;
                } else {
                    childTab.add(getFormGridField(instanceId, childTable, childDataItem));
                }
            }
            Ext.getCmp('childTab').setActiveTab(0);
        }
    }
}

/**
 * 获取表单控件对象
 * @param {} dataItem 数据项对象
 * @return {}
 */
function getFormField(dataItem) {
    var formObject;	//表单控件

    eval('formObject = ' + dataItem.META_TYPE_CONFIG);

    return formObject;
}

function openWin(url) {
    window.open(url);
}

/**
 * 获取表单Grid控件
 * @param {} instanceId 业务数据主键
 * @param {} childTable 子表对象
 * @param {} childDataItem 子表表数据项对象列表
 * @return {}
 */
function getFormGridField(instanceId, childTable, childDataItem) {
    //主键编码
    var childPKNum;
    //父主键编码
    var masterPKNum;

    //控件高度
    if (null == childTable.META_FIELD_HEIGHT) {
        childTable.META_FIELD_HEIGHT = 250;
    }

    //编辑操作
    var editColumn = {
        icon: 'resources/custom/images/editBig.png', tooltip: "编辑",
        handler: function(grid, rowIndex, colIndex) {
            var rec = grid.getStore().getAt(rowIndex).data;
            new getChildForm(instanceId, childTable, childDataItem, rec);
        }
    };

    var columns = [
        { text: '序号', xtype: 'rownumberer', width: 40, sortable: false}
    ];
    var fields = ['ID'];

    Ext.Array.forEach(childDataItem, function(dataItem) {
        if (dataItem.META_PRIMARY_KEY == 1) {    //主键编码
            childPKNum = dataItem.META_NUM;
        } else if (dataItem.META_FOREIGN_KEY == 1) {    //父主键编码
            masterPKNum = dataItem.META_NUM;
        } else {
            if (null != dataItem.META_TYPE_NUM && 'ATTACH' == dataItem.META_TYPE_NUM) {
                //editColumn = {}; //设置编辑不显示
                columns.push({text: dataItem.META_NAME, dataIndex: dataItem.META_NUM, flex: 1,
                    renderer: function(value, cellmeta, record) {
                        if (value) {
                        	var url = beforeAttachUrl + value;	
                        	var str = "<a href=\"javascript:openWin('" + url + "')\">" + value + "</a>";
                            return str;
                        }
                    }
                });

            } else {
                columns.push({text: dataItem.META_NAME, dataIndex: dataItem.META_NUM, flex: 1});
            }
        }
        fields.push(dataItem.META_NUM);
    }, this);


    var gridTbar;
    //子表数据项数据
    var childData = '';
    if (childTable.READ_ONLY != true && (null == childTable.ACCESS_TYPE || childTable.ACCESS_TYPE == 'WRITTER')) {

        //操作列
        columns.push({
            text: '操作', width: 70,
            xtype: 'actioncolumn',
            align: "center",
            items: [editColumn,{
                icon: 'resources/custom/images/deleteBig.png', tooltip: "删除",
                iconCls: "margin15",
                handler: function(grid, rowIndex, colIndex) {
                    var rec = grid.getStore().getAt(rowIndex).data;
                    Ext.Ajax.request({
                        url: 'custom/BWorkFlowInstance_deleteChildById.action',
                        params: {
                            ID: rec[childPKNum],
                            childPKNum: childPKNum,
                            metaNum: childTable.META_NUM
                        },
                        success: function (response) {
                            Ext.Msg.alert("提示", response.responseText, function() {
                                Ext.getCmp(childTable.META_NUM).getStore().load({params: {metaNum: childTable.META_NUM, metaPid: instanceId, masterPKNum: masterPKNum}});
                            });
                        },
                        failure: function (response) {
                            Ext.Msg.alert("提示", response.responseText);
                        }
                    });
                }
            }]
        });

        //新增按钮
        gridTbar = Ext.create('Ext.toolbar.Toolbar', {
            xtype: "toolbar",
            items:{
                iconCls:'icon-addBig',
                tooltip:'新增',
                handler: function() {
                    //判断是否保存了主表
                    var parentId = Ext.getCmp("instanceId").getValue();
                    if (null == parentId || "" == parentId) {
                        Ext.Msg.alert('提示', '请先保存主表，再新增子表');
                        return;
                    } else {
                        new getChildForm(parentId, childTable, childDataItem, childData);
                    }

                }
            }
        });
    } else {    //“只读”状态，子表列表显示查看按钮
        	//操作列
            columns.push({
                text: '操作', width: 70,
                xtype: 'actioncolumn',
                align: "center",
                items: [
                    {
                        icon: 'resources/custom/images/query.png', tooltip: "查看",
                        handler: function(grid, rowIndex, colIndex) {
                            var rec = grid.getStore().getAt(rowIndex);
                            showFormView(rec.get(childPKNum), childTable.META_ID, childTable.META_NUM, null, 'AUTO_FORM', childTable.META_NAME, 'READ');
                        }
                    }
                ]
            });
    }

    var formGrid = Ext.create('Ext.grid.Panel', {
        id: childTable.META_NUM,
        title: childTable.META_NAME,
        height: parseInt(childTable.META_FIELD_HEIGHT),
        tbar: gridTbar,
        columns: columns,
        store: {
            fields: fields,
            proxy: {
                type: 'ajax',
                url: 'custom/BWorkFlowInstance_querychildList.action',
                extraParams: {metaNum: childTable.META_NUM, metaPid: instanceId, masterPKNum: masterPKNum},
                reader: {
                    type: 'json',
                    root: 'data'
                }
            }
        }

    });

    //判断主表数据是否存在，如果存在，则加载子表列表
    //	if(instanceId) {
    //		Ext.getCmp(childTable.META_NUM).getStore().load({params: {metaNum: childTable.META_NUM, metaPid: instanceId, masterPKNum: masterPKNum}});
    //	}
    return formGrid;
}
/**
 * 获取处理按钮
 */
function getFormButton(nodeLine, readOnly) {
    var formButton = Ext.create("Ext.Button", {
        text: nodeLine.ACTION_NAME,
        handler: function() {
            Ext.getCmp("instanceForm").getForm().submit({
                waitMsg: '正在处理，请稍后...',
                url: "custom/BWorkFlowInstance_executeInstanceHandle.action",
                params: {
                    nextNodeId: nodeLine.ACTION_END_NODE_ID,
                    actionName: nodeLine.ACTION_NAME,
                    pageSource: pageSource,
                    readOnly: readOnly,
                    remark: Ext.getCmp('remark').getValue(),
                    sendMessage: nodeLine.SEND_MESSAGE,
                    sendEmail: nodeLine.SEND_EMAIL
                },
                success: function(form, action) {
                    var pageSource = action.result.data;
                    Ext.Msg.alert('成功', "操作成功！", function() {
                        //发起流程页面
                        var backUrl = "custom/BWorkFlowInstance_index.action";
                        if (pageSource == "TODO") {
                            //代办事项页面
                            backUrl = "custom/BWorkFlowInstance_todoIndex.action";
                        } else if (pageSource == "WF_MENU") {
                            backUrl = "custom/BWorkFlowMenu_menuList.action?templateId=" + templateId + "&menuId=" + menuId;
                        }
                        window.location.href = backUrl;
                    });

                },
                failure: function(form, action) {
                    Ext.Msg.alert('失败', action.result ? action.result.data : '操作失败！');
                }
            });
        }

    });
    return formButton;
}
/**
 *实例详情
 */
function showInstanceHistory(instanceId) {
    Ext.create("Ext.grid.Panel", {
        title: "流程历史",
        columnLines: true,
        width: Ext.getBody().getWidth() - 100,
        height: Ext.getBody().getHeight() - 50,
        modal: true,
        renderTo: Ext.getBody(),
        autoShow: true,
        floating: true,
        frame: true,
        closable: true,
        layout: 'border',
        store: {
            fields: [
                { name: 'INSTANCE_ID', type: 'string' },
                { name: 'NODE_ID', type: 'string' },
                { name: 'NODE_NAME', type: 'string' },
                { name: 'CREATE_TIME', type: 'string' },
                { name: 'STATE', type: 'string' },
                { name: 'CREATE_USER', type: 'string' },
                { name: 'ACTION_NAME', type: 'string' },
                { name: 'REMARK', type: 'string' }
            ],
            proxy: {
                type: 'ajax',
                url: 'custom/BWorkFlowInstance_instanceDetailJsonList.action',
                extraParams: { instanceId: instanceId }
            },
            listeners: {
                load: function (store, records, successful) {
                    if (!successful) {
                        Ext.Msg.alert("提示", "加载失败!");
                    }
                }
            },
            autoLoad: true
        },
        columns: [
            { text: '序号', xtype: 'rownumberer', width: 40 },
            { text: '节点名称', flex: 1, dataIndex: 'NODE_NAME' },
            { text: '处理时间', flex: 1, dataIndex: 'CREATE_TIME' },
            { text: '处理状态', flex: 1, dataIndex: 'STATE' },
            { text: '处理人', flex: 1, dataIndex: 'CREATE_USER' },
            { text: '处理意见', flex: 1, dataIndex: 'ACTION_NAME' },
            { text: '处理说明', xtype: 'templatecolumn', flex: 3, tpl: '<div style="width:100%; white-space: normal;">{REMARK}</div>' }
        ]
    });
}

/**
 * 获取子表页面
 * @param {} parentId    父节点主键
 * @param {} childTable    子表对象
 * @param {} childDataItem 子表数据项对象
 */
function getChildForm(parentId, childTable, childDataItem, childData) {

    var title = "新增" + childTable.META_NAME;
    if (childData) {
        var title = "编辑" + childTable.META_NAME;
    }

    //子表主键值
    var childPKValue;
    //主表主键编码
    var masterPKNum;

    //为数据项赋值
    for (var i = 0; i < childDataItem.length; i++) {
        if (childDataItem[i].META_PRIMARY_KEY == 1) {
            childPKValue = childData[childDataItem[i].META_NUM];
            continue;
        }
        if (childDataItem[i].META_FOREIGN_KEY == 1) {
            masterPKNum = childDataItem[i].META_NUM;
            continue;
        }
        childDataItem[i].FIELD_VALUE = childData[childDataItem[i].META_NUM];
    }


    var childWindow = Ext.create("Ext.window.Window", {
        id: 'childFormWin',
        title: title,
        width: Ext.getBody().getWidth() * 0.6,
        height: Ext.getBody().getHeight() * 0.8,
        layout: 'fit',
        modal: true,
        maximizable: true,
        renderTo: Ext.getBody(),
        items: {
            xtype: 'form',
            id: 'childForm',
            border: 1,
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            autoScroll: true,
            bodyPadding: 10,
            fieldDefaults: { labelAlign: 'left', margin: "5 20 0 10", labelWidth: 70},
            items: [
                {
                    //主表主键
                    xtype: 'hiddenfield', name: masterPKNum, id: masterPKNum, value: parentId
                }
            ]
        },
        buttons: [
            {
                text: '保存',
                handler: function() {
                    var url = 'custom/BWorkFlowInstance_editChild.action';
                    var waitMsg = "正在保存，请稍后...";

                    if (Ext.getCmp('childForm').getForm().isValid()) {
                        Ext.getCmp('childForm').getForm().submit({
                            url: url,
                            params: {
                                ID: childPKValue,                //子表主键值
                                metaNum: childTable.META_NUM,     //子表表名
                                metaId: childTable.META_ID        //子表主键
                            },
                            waitMsg: waitMsg,
                            success: function(form, action) {
                                var result = Ext.decode(action.response.responseText);
                                Ext.Msg.alert('成功', result.data, function() {
                                    var params = {metaNum: childTable.META_NUM, metaPid: parentId, masterPKNum: masterPKNum};
                                    Ext.getCmp(childTable.META_NUM).getStore().load({params: params});
                                    Ext.getCmp('childFormWin').close();
                                });
                            },
                            failure: function(form, action) {
                                var result = Ext.decode(action.response.responseText);
                                Ext.Msg.alert('失败', result.data);
                            }
                        });
                    }
                }
            },
            {
                text: '取消',
                handler: function() {
                    Ext.getCmp('childFormWin').close();
                }
            }
        ]
    });

    //子表表单对象
    var childForm = Ext.getCmp('childForm');

    //填充表单
    setFormField(childForm, null, childDataItem, null, null);

    childWindow.show();
}

/**
 *实例详情
 */
function showInstanceDetail(instanceId, templateId) {
    Ext.create('Ext.panel.Panel', {
        title: '流程实例详细信息',
        width: Math.ceil(Ext.getBody().getWidth() * 0.8),
        height: Math.ceil(Ext.getBody().getHeight() * 0.9),
        modal: true,
        renderTo: Ext.getBody(),
        autoShow: true,
        floating: true,
        frame: true,
        closable: true,
        layout: 'border',
        items: [
            {
                region: 'north',
                id: 'instSummary',
                split: true,
                title: '流程图',
                height: "50%", collapsible: true, collapsed: false,
                html: '<div id="divCanvas" style="height: 100%;"><canvas id="myCanvas">您的浏览器不支持 HTML5 Canvas。</canvas></div>'
            },
            {
                region: 'center',
                xtype: 'grid',
                id: 'gridInstHistory',
                columnLines: true,
                title: '流程历史',
                tools: [
                    {
                        type: 'next',
                        tooltip: '自动播放',
                        handler: function () {
                            var index = 0;
                            var si = setInterval(
                                    function () {

                                        if (index < Ext.getCmp('gridInstHistory').getStore().data.length) {
                                            Ext.getCmp('gridInstHistory').getSelectionModel().select(index);
                                            index++;
                                        }
                                        else {
                                            index = 0;
                                            clearInterval(si);
                                        }
                                    },
                                    1500
                                    );
                        }
                    }
                ],
                store: {
                    fields: [
                        { name: 'INSTANCE_ID', type: 'string' },
                        { name: 'NODE_ID', type: 'string' },
                        { name: 'NODE_NAME', type: 'string' },
                        { name: 'CREATE_TIME', type: 'string' },
                        { name: 'STATE', type: 'string' },
                        { name: 'CREATE_USER', type: 'string' },
                        { name: 'ACTION_NAME', type: 'string' },
                        { name: 'REMARK', type: 'string' }
                    ],
                    proxy: {
                        type: 'ajax',
                        url: 'custom/BWorkFlowInstance_instanceDetailJsonList.action',
                        extraParams: { instanceId: instanceId }
                    },
                    listeners: {
                        load: function (store, records, successful) {
                            if (!successful) {
                                Ext.Msg.alert("提示", "加载失败!");
                            }
                            else {
                            }
                        }
                    },
                    autoLoad: true
                },
                columns: [
                    { text: '序号', xtype: 'rownumberer', width: 40 },
                    { text: '节点名称', flex: 1, dataIndex: 'NODE_NAME' },
                    { text: '处理时间', flex: 1.5, dataIndex: 'CREATE_TIME' },
                    { text: '处理状态', flex: 1, dataIndex: 'STATE' },
                    { text: '处理人', flex: 1, dataIndex: 'CREATE_USER' },
                    { text: '处理意见', flex: 1, dataIndex: 'ACTION_NAME' },
                    { text: '处理说明', xtype: 'templatecolumn', flex: 3, tpl: '<div style="width:100%; white-space: normal;">{REMARK}</div>' }
                ],
                listeners: {
                    selectionchange: function (view, selections, options, record, item, index, e) {
                        if (selections.length > 0) {
                            wfg.redrawAll();
                            wfg.setOperateState("move");
                            wfg.drawNodeRec(wfg.data.nodes[Ext.Array.pluck(wfg.data.nodes, "ID").indexOf(selections[0].data.NODE_ID)]);
                        }
                    }
                }
            }
        ]
    });

    /**
     * 请求数据，展示流程图
     */
    Ext.Ajax.request({
        url: 'custom/BWorkFlowTemplate_getTemplateData.action',
        params: {
            templateId: templateId
        },
        success: function (response) {
            var result = Ext.JSON.decode(response.responseText).data;
            wfg = new WFGraph({ id: "myCanvas", data: result});
            wfg.fullCanvas();
            wfg.setOperateState("move");
        },
        failure: function (response) {
            Ext.Msg.alert("提示", response.responseText);
        }
    });
}

/**
 * 展示表单
 * @param {} instanceId        业务数据主键
 * @param {} busizModelId    业务模版主键
 * @param {} dataSourceNum    表名
 * @param {} nodeId            节点主键
 * @param {} formDataSource    表单数据来源
 * @param {} templateName    模版名称
 * @param {} readOnly        是否只读
 */
function showFormView(instanceId, busizModelId, dataSourceNum, nodeId, formDataSource, templateName, readOnly) {
    var button = null;
    if (null == instanceId) {
        button = [
            {
                text: '保存',
                handler: function() {
                    if (Ext.getCmp("form" + dataSourceNum).getForm().isValid()) {

                        Ext.getCmp("form" + dataSourceNum).getForm().submit({
                            url: "custom/BWorkFlowInstance_saveMasterForm.action",
                            params:{instanceId: instanceId, saveFormSource: 'SaveAutoForm', busizModelId: busizModelId, dataSourceNum: dataSourceNum},
                            waitMsg: '正在保存，请稍后...',
                            success: function(form, action) {
                                Ext.Msg.alert('成功', "保存成功！", function() {
                                    instanceId = action.result.data.instanceId;
                                    this.instanceId = instanceId;
                                    Ext.getCmp("instanceId").setValue(instanceId);
                                });
                            },
                            failure: function(form, action) {
                                var result = Ext.decode(action.response.responseText);
                                Ext.Msg.alert('失败', result.data ? action.result.data : '操作失败！');
                            }
                        });
                    }
                }
            },
            {
                text: '取消',
                handler: function() {
                    Ext.getCmp('win' + dataSourceNum).close();
                }
            }
        ]
    }
    var detailWin = Ext.create('Ext.window.Window', {
        id: 'win' + dataSourceNum,
        layout: "fit",
        title: templateName,
        width: Math.ceil(Ext.getBody().getWidth() * 0.85),
        height: Math.ceil(Ext.getBody().getHeight() * 0.95),
        modal: true,
        maximizable: true,
        padding: 6,
        renderTo: Ext.getBody(),
        items: [
            {
                xtype: 'panel',
                autoScroll: true,
                border: 0,
                items:{
                    xtype: "form",
                    id: "form" + dataSourceNum,
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    border: 0,
                    bodyPadding: 10,
                    fieldDefaults: { labelAlign: 'left', margin: "5 20 0 10", labelWidth: 70},
                    items: [
                        {
                            //流程实例主键
                            xtype: 'hiddenfield', name: 'instanceId',  value: instanceId
                        }
                    ]
                }
            }
        ],
        buttons: button

    });

    /**
     * 请求数据，填充表单
     */
    Ext.Ajax.request({
        url: 'custom/BWorkFlowInstance_formDetail.action',
        params: {
            instanceId: instanceId,
            busizModelId: busizModelId,
            dataSourceNum: dataSourceNum,
            formDataSource: formDataSource,
            readOnly: readOnly,
            nodeId: nodeId
        },
        success: function (response) {
            var result = Ext.JSON.decode(response.responseText).data;
            //处理表单对象
            var formView = Ext.getCmp("form" + dataSourceNum);

            //主表数据项列表数据
            var WFDataItemList = result.WF_MASTER_DATA_ITEM;
            //子表数据项列表
            var childDataItemList = result.WF_DETAIL_DATA_ITEM;
            //子表表名列表
            var childTableList = result.WF_DETAIL_DATA_TABLE;

            //设置表单控件
            setFormField(formView, instanceId, WFDataItemList, childDataItemList, childTableList);
        },
        failure: function (response) {
            Ext.Msg.alert("提示", response.responseText);
        }
    });

    detailWin.show();
}

/**
 * 获取分页条
 * @param {} storeId 数据源ID
 * @return {}
 */
function getPagingtoolbar(storeId) {
    //分页条
    var bbar = Ext.create('Ext.PagingToolbar', {
        store: Ext.data.StoreManager.lookup(storeId),
        displayInfo: true,
        beforePageText: "第",
        afterPageText: "页, 共 {0} 页",
        displayMsg: '显示 {0} - {1} 条，共计 {2} 条',
        emptyMsg: "没有数据",
        items: [
            '-',
            {
                xtype: 'numberfield',
                labelWidth: 70,
                width: 140,
                fieldStyle: 'width:50px',
                id: storeId + '_' + pageSize,
                fieldLabel: '每页记录数',
                value: pageSize,
                minValue: 1,
                listeners: {
                    change: function (field, v) {
                        Ext.data.StoreManager.lookup(storeId).pageSize = v;
                    }
                }
            }
        ]
    });
    return bbar;
}