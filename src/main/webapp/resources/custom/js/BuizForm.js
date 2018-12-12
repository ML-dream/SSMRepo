function sleep(milliSeconds) {
    var startTime = new Date().getTime(); // get the current time
    while (new Date().getTime() < startTime + milliSeconds); // hog cpu
}



var MB = function () { };
MB.form = function () { };


MB.Checked = function () { alert("adsfasdf") };

/**用于存放表单的通用配置项
*/
MB.form.FormConfig = {
};


MB.form.WFNodeHandle = function (config) {
    this.name = "流程处理节点表单";
    this.form = new Ext.form.FormPanel({
        params: { sid: config.id, jid: 'ssss' },
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        bodyPadding: 10,
        //height: 400,
        fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', labelWidth: 60, margin: '2px 0' },
        items: [
            { xtype: 'hiddenfield', name: 'templateId', value: config.templateId, hidden: true },
            { xtype: 'hiddenfield', name: 'nodeId', value: config.id, hidden: true },
            { xtype: 'hiddenfield', name: 'positionX', value: config.x, hidden: true },
            { xtype: 'hiddenfield', name: 'positionY', value: config.y, hidden: true },
            { xtype: 'hiddenfield', name: 'nodeType', value: 'WF_NODE_HANDLE', hidden: true },
            {
                xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: { labelAlign: 'left', msgTarget: 'none' },
                items: [
                    { xtype: 'textfield', name: 'nodeName', fieldLabel: '处理节点名称', labelWidth: 80, margin: '0 20 10 0', flex: 1 },
                    {
                        xtype: 'combo',
                        name: 'viewFormCode',
                        id: 'WFNodeHandle_ViewCode',
                        fieldLabel: '视图表单',
                        labelWidth: 60,
                        store: {
                            fields: ['id','text'],
                            data:[{id:'AUTO_FORM',text:'autoForm'}, {id:'DATA_BASE',text:'database'}]
                        },
                        displayField: 'text',
                        valueField: 'id',
                        value: config.formView,
                        readOnly: true
                    },
                    { xtype: 'checkbox', name: 'readOnly', boxLabel: '是否只读', margin: '0 50 0 10' }
                ]
            },
            
        	{
            xtype: 'tabpanel',
            activeTab: 0,
            flex: 1,
            items: [
            {
                title: '处理人',
                xtype: 'grid',
                id: 'nodeHandlerGrid',
                tbar: [
                    {

                    	xtype: 'button',
                        iconCls: 'icon-addBig',
                        text: '用户',
                        tooltip: '增加用户',
                        handler: function (source, e) {
                            Ext.create('Ext.grid.Panel', {
                                title: '增加用户',
                                useArrows: true,
                                modal: true,
                                renderTo: Ext.getBody(),
                                autoShow: true,
                                floating: true,
                                frame: true,
                                closable: true,
                                width: 400,
                                height: 400,
                                buttons: [
                                    {
                                        text: '确定',
                                        width: 40,
                                        handler: function (btn) {
                                            var userIds = [];
                                            var userNames = [];
                                            var userCodes = [];
                                            Ext.Array.forEach(
                                                this.up('gridpanel').getSelectionModel().selected.items,
                                                function (item, index) {
                                                    userIds.push(item.data.USER_ID);
                                                    userNames.push(encodeURIComponent(encodeURIComponent(item.data.USER_NAME)));
                                                    userCodes.push(encodeURIComponent(encodeURIComponent(item.data.LOGIN_NAME)))
                                                }
                                            );
                                            Ext.Ajax.request({
                                                url: 'custom/BWorkFlowTemplate_saveNodeHandler.action',
                                                params: { userIds: Ext.JSON.encode(userIds), userNames: Ext.JSON.encode(userNames), userCodes: Ext.JSON.encode(userCodes), nodeId: config.id, templateId: config.templateId, userType:'1' },
                                                success: function (response, opts) {
                                                    this.up('gridpanel').close();
                                                  //  Ext.Msg.alert('提示','增加成功!', function(){
                                                    	Ext.getCmp('nodeHandlerGrid').getStore().load({ params: { nodeId: config.id, templateId: config.templateId} });
                                                    	//this.up('gridpanel').close();
                                                   // },this);
                                                },
                                                failure: function (response, opts) {
                                                    //console.log('server-side failure with status code ' + response.status);
                                                },
                                                scope: btn
                                            });
                                        }
                                    },
                                    {
                                        text: '取消',
                                        width: 40,
                                        handler: function (btn) {
                                            btn.up('gridpanel').close();
                                        }
                                    }
                                ],
                                selModel: new Ext.selection.CheckboxModel({
                                    checkOnly: true
                                }),
                                store: Ext.create('Ext.data.Store', {
                                    fields: ['USER_ID', 'LOGIN_NAME', 'USER_NAME'],
                                    proxy: {
                                        type: 'ajax',
                                        url: 'custom/BWorkFlowTemplate_getUserList.action'
                                    },
                                    autoLoad: true
                                }),
                                columns: [
                                    { xtype: 'rownumberer', width: 30 },
                                    {
                                        header: '名称',
                                        dataIndex: 'USER_NAME',
                                        flex: 1
                                    },
                                    {
                                        header: '编码',
                                        dataIndex: 'LOGIN_NAME',
                                        flex: 1
                                    }
                                ]
                            });

                        }
                    },
                    {
                        xtype: 'button',
                        iconCls: 'icon-addBig',
                        text: '用户组',
                        tooltip: '增加用户组',
                        handler: function (source, e) {
                            Ext.create('Ext.grid.Panel', {
                                title: '增加用户组',
                                useArrows: true,
                                modal: true,
                                renderTo: Ext.getBody(),
                                autoShow: true,
                                floating: true,
                                frame: true,
                                closable: true,
                                width: 400,
                                height: 400,
                                buttons: [
                                    {
                                        text: '确定',
                                        width: 40,
                                        handler: function (btn) {
                                            var IDs = [];
                                             var userIds = [];
                                            var userNames = [];
                                            var userCodes = [];
                                            Ext.Array.forEach(
                                                this.up('gridpanel').getSelectionModel().selected.items,
                                                function (item, index) {
                                                    userIds.push(item.data.ROLE_ID);
                                                    userNames.push(item.data.ROLE_NAME);
                                                    userCodes.push(item.data.ORG_CODE)
                                                }
                                            );
                                            Ext.Ajax.request({
                                                url: 'custom/BWorkFlowTemplate_saveNodeHandler.action',
                                                params: { userIds: Ext.JSON.encode(userIds), userNames: JSON.stringify(userNames), userCodes: JSON.stringify(userCodes), nodeId: config.id, templateId: config.templateId, userType:'2' },
                                                success: function (response, opts) {
                                                    Ext.getCmp('nodeHandlerGrid').getStore().load({ params: { nodeId: config.id, templateId: config.templateId} });
                                                    this.up('gridpanel').close();
                                                    Ext.Msg.alert("提示",'增加成功!');
                                                },
                                                failure: function (response, opts) {
                                                    //console.log('server-side failure with status code ' + response.status);
                                                },
                                                scope: btn
                                            });
                                        }
                                    },
                                    {
                                        text: '取消',
                                        width: 40,
                                        handler: function (btn) {
                                            btn.up('gridpanel').close();
                                        }
                                    }
                                ],
                                selModel: new Ext.selection.CheckboxModel({
                                    checkOnly: true
                                }),
                                store: Ext.create('Ext.data.Store', {
                                    fields: ['ROLE_ID', 'ORG_CODE', 'ROLE_NAME'],
                                    proxy: {
                                        type: 'ajax',
                                        url: 'custom/BWorkFlowTemplate_getUserRolesList.action'
                                    },
                                    autoLoad: true
                                }),
                                columns: [
                                    { xtype: 'rownumberer', width: 30 },
                                    {
                                        header: '名称',
                                        dataIndex: 'ROLE_NAME',
                                        flex: 1
                                    },
                                    {
                                        header: '编码',
                                        dataIndex: 'ORG_CODE',
                                        flex: 1
                                    }
                                ]
                            });

                        }
                    },
                    '-',
                    {
                        xtype: 'button',
                        iconCls: 'icon-deleteBig',
                        text: '删除',
                        tooltip: '删除',
                        handler: function (s, e) {
                            if (!confirm("确认删除吗?")) return;
                            var IDs = [];
                            Ext.Array.forEach(
                                Ext.getCmp('nodeHandlerGrid').getSelectionModel().selected.items,
                                function (item, index) {
                                    this.push(item.data.handlerId);
                                },
                                IDs
                            );
                            Ext.Ajax.request({
                                url: 'custom/BWorkFlowTemplate_deleteNodeHandler.action',
                                params: { userIds: Ext.JSON.encode(IDs), nodeId: config.id, templateId: config.templateId },
                                success: function (response, opts) {
                                    Ext.getCmp('nodeHandlerGrid').getStore().load({ params: { nodeId: config.id, templateId: config.templateId} });
                                    Ext.Msg.alert("提示",'删除成功!');
                                },
                                failure: function (response, opts) {
                                    //console.log('server-side failure with status code ' + response.status);
                                }
                            });
                        }
                    }
                   
                ],
                selModel: new Ext.selection.CheckboxModel({
                    checkOnly: true
                }),
                 store: {
                    fields: ['handlerId', 'userName', 'userCode', 'category'], //Category:'用户'/'组织'/'用户组'
                    proxy: {
                        type: 'ajax',
                        url: 'custom/BWorkFlowTemplate_getNodeHandler.action',
                        reader: {
                            type: 'json',
                            root: 'data'
                        }
                    },
                   groupField: 'category'
                },
                features: [{
                    ftype: 'grouping',
                    groupHeaderTpl: '类别: {name}' //API文档错误,groupHdTpl无效
                }],
                columns: [
                    { xtype: 'rownumberer', width: 30 },
                    {
                        header: '名称',
                        dataIndex: 'userName',
                        flex: 1
                    },
                    {
                        header: '编码',
                        dataIndex: 'userCode',
                        flex: 1
                    },
                    {
                        header: '类别',
                        dataIndex: 'category',
                        flex: 1
                    }
                ]
            },
            {
                title: '会签',
                layout: { type: 'vbox', align: 'stretch' },
                items: [
                    { xtype: 'checkboxfield', name: 'countersign', fieldLabel: '是否会签' },
                    { xtype: 'textareafield', name: 'countersignRules', flex: 1, fieldLabel: '会签规则', labelAlign: 'top' },
                    { xtype: 'displayfield', fieldLabel: '会签规则说明', labelAlign: 'top', value: '1.如果规则为空,表示采用默认规则,使用默认规则时动作必须只有一个,所有处理人处理完成,该节点完成<br/>2.{H}:当前已经处理的用户数;{A}:节点指定的处理用户数;{动作名称}:当前选择该动作的用户数;<br/>3.每条规则一行,条件和最终动作用分号隔开,如{H}={A} and {actionA}>{actionB} and {actionA}>{actionC} : actionA' }
                ]
            }, {
                title: '访问权限',
                layout: 'fit',
                items: [
                {
                    xtype: 'grid',
                    id: 'ACLGrid',
                    border: 0,
                    tbar: [
                        { xtype: 'button', iconCls: 'icon-addBig', tooltip: '新增', tooltipType: 'title', handler: function (source, e) {
                            var formACL = Ext.create('Ext.form.Panel', {
                                title: '处理节点字段访问权限设置',
                                url: 'custom/BWorkFlowTemplate_eidtNodeAccess.action',
                                modal: true,
                                renderTo: Ext.getBody(),
                                autoShow: true,
                                floating: true,
                                frame: true,
                                closable: true,
                                //closeAction: 'hide',
                                width: 400,
                                //height: 300,
                                layout: 'anchor',
                                draggable: true,
                                bodyPadding: 6,
                                fieldDefaults: { anchor: '100%', labelWidth: 70 },
                                items: [
                                	{ xtype: 'hidden', name: 'accessName' },
                                	{ xtype: 'hidden', name: 'accessCode' },
                                    {
                                        xtype: 'combo',
                                        name: 'dataItemId',
                                        fieldLabel: '字段',
                                        store: {
                                            fields: ['DATA_ITEM_ID', 'DATA_ITEM_NAME', 'DATA_ITEM_CODE'],
                                            proxy: {
                                                type: 'ajax',
                                                url: 'custom/BWorkFlowTemplate_getAccessDataItem.action',
                                                extraParams: { templateId: config.templateId },
                                                reader: {
                                                    type: 'json',
                                                    root: 'data'
                                                }
                                            },
                                            autoLoad: true
                                        },
                                        queryMode: 'local',
                                        editable: false,
                                        allowBlank: false,
                                        forceSelection: true,
                                        emptyText: '请选择',
                                        displayField: 'DATA_ITEM_NAME',
                                        valueField: 'DATA_ITEM_ID',
                                        listeners: {
                                        	 select: function(combo, record, index) {
                                        	 	formACL.getForm().findField('accessName').setValue(record[0].get("DATA_ITEM_NAME"));
                                        	 	formACL.getForm().findField('accessCode').setValue(record[0].get("DATA_ITEM_CODE"));
                                        	 }
                                        }
                                    },
                                    {
                                        xtype: 'combo',
                                        name: 'accessType',
                                        fieldLabel: '访问权限',
                                        store: {
                                            fields: ['ACL', 'CODE'],
                                            data: [
                                                { ACL: '可写', CODE: 'WRITER' },
                                                { ACL: '只读', CODE: 'READ' },
                                                { ACL: '隐藏', CODE: 'HIDDEN' }
                                            ]
                                        },
                                        queryMode: 'local',
                                        editable: false,
                                        allowBlank: false,
                                        forceSelection: true,
                                        emptyText: '请选择',
                                        displayField: 'ACL',
                                        valueField: 'CODE',
                                        value: '可改写'
                                    },
                                    {
                                        xtype: 'textarea',
                                        name: 'accessParams',
                                        fieldLabel: '控件属性',
                                        anchor: '100%',
                                        height: 300
                                    },
                                    {
                                        xtype: 'fieldcontainer',
                                        bodyPadding: 10,
                                        defaults: { width: 60, height: 26 },
                                        style: { 'text-align': 'right' },
                                        items: [
                                            { xtype: 'button', text: '确定', handler: function () {
                                                formACL.getForm().submit({
                                                    params: { nodeId: config.id, templateId: config.templateId },
                                                    success: function (f, a) { Ext.getCmp('ACLGrid').getStore().load(); formACL.close(); }
                                                });
                                            }
                                            },
                                            { xtype: 'button', margin: '0 0 0 20', text: '取消', handler: function () { formACL.close(); } }
                                            ]
                                    }
                                ]
                            });
                        }
                        },
                        { xtype: 'button', iconCls: 'icon-editBig', tooltip: '修改', tooltipType: 'title', handler: function (source, e) {
                            var formACL = Ext.create('Ext.form.Panel', {
                                title: '处理节点字段访问权限设置',
                                url: 'custom/BWorkFlowTemplate_eidtNodeAccess.action',
                                modal: true,
                                renderTo: Ext.getBody(),
                                autoShow: true,
                                floating: true,
                                frame: true,
                                closable: true,
                                //closeAction: 'hide',
                                width: 400,
                                //height: 300,
                                layout: 'anchor',
                                draggable: true,
                                bodyPadding: 6,
                                fieldDefaults: { anchor: '100%', labelWidth: 70 },
                                items: [
                                    { xtype: 'hidden', name: 'accessId' },
                                    { xtype: 'hidden', name: 'accessName' },
                                	{ xtype: 'hidden', name: 'accessCode' },
                                    {
                                        xtype: 'combo',
                                        name: 'dataItemId',
                                        fieldLabel: '字段',
                                        store: {
                                            fields: ['DATA_ITEM_ID', 'DATA_ITEM_NAME', 'DATA_ITEM_CODE'],
                                            proxy: {
                                                type: 'ajax',
                                                url: 'custom/BWorkFlowTemplate_getAccessDataItem.action',
                                                extraParams: { templateId: config.templateId},
                                                reader: {
                                                    type: 'json',
                                                    root: 'data'
                                                }
                                            },
                                            autoLoad: true
                                        },
                                        queryMode: 'local',
                                        editable: false,
                                        allowBlank: false,
                                        //forceSelection: true,
                                        emptyText: '请选择',
                                        displayField: 'DATA_ITEM_NAME',
                                        valueField: 'DATA_ITEM_ID',
                                        listeners: {
                                        	 select: function(combo, record, index) {
                                        	 	formACL.getForm().findField('accessName').setValue(record[0].get("DATA_ITEM_NAME"));
                                        	 	formACL.getForm().findField('accessCode').setValue(record[0].get("DATA_ITEM_CODE"));
                                        	 }
                                        }
                                    },
                                    {
                                        xtype: 'combo',
                                        name: 'accessType',
                                        fieldLabel: '访问权限',
                                        store: {
                                            fields: ['ACL','CODE'],
                                            data: [
                                                { ACL: '可写' ,CODE: 'WRITER'},
                                                { ACL: '只读' ,CODE: 'READ'},
                                                { ACL: '隐藏' ,CODE: 'HIDDEN'}
                                            ]
                                        },
                                        queryMode: 'local',
                                        editable: false,
                                        allowBlank: false,
                                        forceSelection: true,
                                        emptyText: '请选择',
                                        displayField: 'ACL',
                                        valueField: 'CODE',
                                        value: '可改写'
                                    },
                                    {
                                        xtype: 'textarea',
                                        name: 'accessParams',
                                        fieldLabel: '控件属性',
                                        anchor: '100%',
                                        height: 300
                                    },
                                    {
                                        xtype: 'fieldcontainer',
                                        bodyPadding: 10,
                                        defaults: { width: 60, height: 26 },
                                        style: { 'text-align': 'right' },
                                        items: [
                                            { xtype: 'button', text: '确定', handler: function () {
                                                formACL.getForm().submit({
                                                    params: { nodeId: config.id, templateId: config.templateId },
                                                    success: function (f, a) { Ext.getCmp('ACLGrid').getStore().load(); formACL.close();}
                                                });
                                            }
                                            },
                                            { xtype: 'button', margin: '0 0 0 20', text: '取消', handler: function () { formACL.close(); } }
                                            ]
                                    }
                                ]
                            });
                            var item = Ext.getCmp('ACLGrid').getSelectionModel().getSelection()[0];
                            //var data = item.data;
                            //item.data["DATA_ITEM_ID"] = item.data.dataItemId;alert(item.data.DATA_ITEM_ID);
                           // item.data.push({"DATA_ITEM_ID":item.data["dataItemId"]});
                          //  debugger;
                            formACL.getForm().setValues(item.data);
                        }
                        }/*,
                        { xtype: 'button', iconCls: 'iconDelete', tooltip: '删除', tooltipType: 'title', handler: function (source, e) { } }*/
                    ],
                    store: {
                        fields: ['accessId', 'accessCode', 'accessName', 'accessType', 'accessParams', 'dataItemId'],
                        proxy: {
                            type: 'ajax',
                            extraParams: { nodeId: config.id, templateId: config.templateId },
                            url: 'custom/BWorkFlowTemplate_getNodeAccess.action',
                            reader: {
                                type: 'json',
                                root: 'data'
                            }
                        },
                        autoLoad: true,
                        listeners: {
                            load: function (store, records, successful) {
                                if (!successful) {
                                   // alert('数据加载失败');
                                }
                                else {
                                }
                            }
                        }
                    },
                    
                    columns: [
                        { xtype: 'rownumberer', width: 30 },
                        {
                            header: '字段编码',
                            dataIndex: 'accessCode',
                            width: 100
                        }, {
                            header: '字段名称',
                            dataIndex: 'accessName',
                            width: 100
                        }, {
                            header: '访问权限',
                            dataIndex: 'accessType',
                            width: 70,
                            renderer: function(value){
                            	var accessTypeName = "可写";
                            	if(value == "HIDDEN") {
                            		accessTypeName = "隐藏";
                            	} else if(value == "READ") {
                            	
                            		accessTypeName = "只读";
                            	}
                            	return accessTypeName;
                            }
                        }, 
                        {
                            xtype: 'templatecolumn',
                            header: '控件配置',
                            tpl: '<tpl if="accessParams.length&gt;0">...</tpl>',
                            flex: 1
                        }, 
                        /**
                        {
                            header: '控件配置',
                            dataIndex: 'accessParams',
                            width: 200
                        }, **/
                        {
                            xtype: 'actioncolumn',
                            icon: '../images/deleteBig.png',
                            //iconCls: 'icon-deleteBig',
                            width: 30,
                            tooltip: '删除',
                            handler: function (grid, rowIndex, colIndex) {
                                var rec = grid.getStore().getAt(rowIndex);
                                //alert("Terminate " + rec.get('firstname'));
                                //lert(rec.data.ID);
                                Ext.Ajax.request({
                                    url: 'custom/BWorkFlowTemplate_deleteNodeAccess.action',
                                    params: {
                                    	
                                        accessId: rec.data.accessId,
                                        nodeId: config.id,
                                        templateId: config.templateId
                                    },
                                    success: function (response) {
                                        Ext.getCmp('ACLGrid').getStore().load();
                                        Ext.Msg.alert("提示","删除成功!");
                                    },
                                    failure: function (response) {
                                        Ext.Msg.alert("提示","删除失败!");
                                    }
                                });
                            }
                        }
                        ]
                }
               ]
            }]
        }],
        buttons: [
            { text: '保存', handler: function () {
                this.up('form').getForm().submit({
                    url: config.opt ? 'custom/BWorkFlowTemplate_updateWFNodes.action' : 'custom/BWorkFlowTemplate_addWFNodes.action',
                    success: function (form, action) {form.setValues({ nodeId: action.result.data }); if (config && config.submitSccess) config.submitSccess(form, action) },
                    failure: function (form, action) {if (config && config.submitFailure) config.submitFailure(form, action) }
                }
                );
            }
            },
            { text: '取消', handler: function () { if (config && config.close) config.close() } }
        ]
    })
    if (config.opt) {
        this.form.getForm().load({
            url: 'custom/BWorkFlowTemplate_getWFNodes.action',
            params: { nodeId: config.id, templateId: this.form.getValues()["templateId"] },
            success: function (response) { /*debugger; alert('success');*/ },
            failure: function (response) { /*debugger; alert('failure');*/ }
        });
        Ext.getCmp('nodeHandlerGrid').getStore().load({ params: { nodeId: config.id, templateId: config.templateId} });
    } 
    return this.form;
}

MB.form.WFNodeXORSplit = function (config) {
    this.name = "流程分支节点表单";
    this.form = new Ext.form.FormPanel({
        params: { sid: config.id, jid: 'ssss' },
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        height: 400,
        width: '100%',
        autoHeight: true,
        stateful: false,
        fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', margin: 4, labelWidth: 80 },
        items: [
            { xtype: 'hiddenfield', name: 'templateId', value: config.templateId, hidden: true },
            { xtype: 'hiddenfield', name: 'nodeId', value: config.id, hidden: true },
            { xtype: 'hiddenfield', name: 'positionX', value: config.x, hidden: true },
            { xtype: 'hiddenfield', name: 'positionY', value: config.y, hidden: true },
            { xtype: 'hiddenfield', name: 'nodeType', value: 'WF_NODE_BRANCH', hidden: true },
            { xtype: 'textfield', name: 'nodeName', fieldLabel: '节点名称' }
        ],
        buttons: [
            { text: '保存', handler: function () {
                this.up('form').getForm().submit({
                    url: config.id ? 'custom/BWorkFlowTemplate_updateWFNodes.action' : 'custom/BWorkFlowTemplate_addWFNodes.action',
                    success: function (form, action) { form.setValues({ ID: action.result.data }); if (config && config.submitSccess) config.submitSccess(form, action) },
                    failure: function (form, action) { if (config && config.submitFailure) config.submitFailure(form, action) }
                }
                );
            }
            },
            { text: '取消', handler: function () { if (config && config.close) config.close() } }
        ]
    })

    if (config.id) {
        this.form.getForm().load({
            url: 'custom/BWorkFlowTemplate_getWFNodes.action',
            params: { nodeId: config.id, templateId: this.form.getValues()["templateId"] },
            success: function (response) { /*debugger; alert('success');*/ },
            failure: function (response) { /*debugger; alert('failure');*/ }
        });
    }

    return this.form;
}

MB.form.WFNodeAction = function (config) {
    this.name = "流程处理节点连接线表单";
    this.form = new Ext.form.FormPanel({
        params: { sid: config.id, jid: 'ssss' },
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        height: 400,
        width: '100%',
        autoHeight: true,
        stateful: false,
        trackResetOnLoad: true,
        fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', margin: 4, labelWidth: 80 },
        items: [
            { xtype: 'hiddenfield', name: 'templateId', value: config.templateId, hidden: true },
            { xtype: 'hiddenfield', name: 'actionId', value: config.actionId, hidden: true },
            { xtype: 'hiddenfield', name: 'actionStartNodeId', value: config.from, hidden: true },
            { xtype: 'hiddenfield', name: 'actionEndNodeId', value: config.to, hidden: true },
            { xtype: 'hiddenfield', name: 'lineType', value: config.type, hidden: true },
            {
                xtype: 'combo',
                name: 'formActionId',
                id: 'WFNodeAction_Code',
                fieldLabel: '动作代码',
                displayField: 'ACTION_NAME',
                valueField: 'ACTION_ID',
                forceSelection: false,
                editable: true,
                queryMode: 'local',
                store: {
                    fields: ['ACTION_ID','ACTION_NAME'],
                    proxy: {
                        type: 'ajax',
                        url: 'custom/BWorkFlowTemplate_getNodeFormAction.action',
                        extraParams: { templateId: config.templateId},
                        reader: {
                            type: 'json',
                            root: 'data'
                        }
                    },
                    autoLoad: true,
                    listeners: {
                        load: function (store, records, successful) {
                            if (!successful) {
                                Ext.Msg.alert('提示', '表单动作代码加载失败！');
                            }
                            else {
                                var field = Ext.getCmp('WFNodeAction_Code');
                                if (field) {
                                    field.select(field.getValue());
                                   
                                }
                               
                            }
                        }
                    }
                }
            },
        //{ xtype: 'textfield', name: 'Code', fieldLabel: '动作代码' },
            {xtype: 'textfield', name: 'actionName', fieldLabel: '动作名称' }
        ],
        buttons: [
            { text: '保存', handler: function () {
                this.up('form').getForm().submit({
                    url: config.actionId ? 'custom/BWorkFlowTemplate_updateWFNodesLine.action' : 'custom/BWorkFlowTemplate_addWFNodesLine.action',
                    success: function (form, action) { form.setValues({ actionId: action.result.data }); if (config && config.submitSccess) config.submitSccess(form, action) },
                    failure: function (form, action) { if (config && config.submitFailure) config.submitFailure(form, action) }
                }
                );
            }
            },
            { text: '取消', handler: function () { if (config && config.close) config.close() } }
        ]
    })

    if (config.actionId) {
        this.form.getForm().load({
            url: 'custom/BWorkFlowTemplate_getWFNodesLine.action',
            params: { lineId: config.actionId, lineType:config.type, templateId: this.form.getValues()["templateId"] }
        }
        );
    }

    return this.form;
}

MB.form.WFNodeExpression = function (config) {
    this.name = "流程分支节点连接线表单";
    this.form = new Ext.form.FormPanel({
        params: { sid: config.id, jid: 'ssss' },
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        height: 400,
        width: '100%',
        autoHeight: true,
        stateful: false,
        fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', margin: 4, labelWidth: 80 },
        items: [
            { xtype: 'hiddenfield', name: 'templateId', value: config.templateId, hidden: true },
            { xtype: 'hiddenfield', name: 'branchId', value: config.branchId, hidden: true },
            { xtype: 'hiddenfield', name: 'branchStartNodeId', value: config.from, hidden: true },
            { xtype: 'hiddenfield', name: 'branchEndNodeId', value: config.to, hidden: true },
            { xtype: 'hiddenfield', name: 'lineType', value: config.type, hidden: true },
            { xtype: 'textfield', name: 'branchExpress', fieldLabel: '表达式' },
            { xtype: 'textfield', name: 'remark', fieldLabel: '说明' },
            { xtype: 'textfield', name: 'branchOrderNo', fieldLabel: '顺序号' }
        ],
        buttons: [
            { text: '保存', handler: function () {
                this.up('form').getForm().submit({
                    url: config.branchId ? 'custom/BWorkFlowTemplate_updateWFNodesLine.action' : 'custom/BWorkFlowTemplate_addWFNodesLine.action',
                    success: function (form, action) { form.setValues({ branchId: action.result.data }); if (config && config.submitSccess) config.submitSccess(form, action) },
                    failure: function (form, action) { if (config && config.submitFailure) config.submitFailure(form, action) }
                }
                );
            }
            },
            { text: '取消', handler: function () { if (config && config.close) config.close() } }
        ]
    })

    if (config.branchId) {
        this.form.getForm().load({
            url: 'custom/BWorkFlowTemplate_getWFNodesLine.action',
            params: { lineId: config.branchId, lineType:config.type, templateId: this.form.getValues()["templateId"] }
        }
        );
    }

    return this.form;
}
MB.form.WFTemplateEdit = function (config) {
    this.form = new Ext.form.FormPanel({
        params: { sid: config.id, jid: 'ssss' },
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        height: 300,
        autoHeight: true,
        width: '100%',
        stateful: false,
        fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', margin: 4, labelWidth: 80 },
        items: [
            { xtype: 'hiddenfield', name: 'bworkFlowTemplate.templateId', value: config.id, hidden: true },
            { xtype: 'hiddenfield', name: 'bworkFlowTemplate.menuId', hidden: true },
            { xtype: 'textfield', name: 'bworkFlowTemplate.templateName', fieldLabel: '模板名称' },
            {
                xtype: 'combo',
                id: "templateBusizId",
                name: 'bworkFlowTemplate.templateBusizId',
                fieldLabel: '业务模型',
                store: {
                    fields: ['ID', 'NAME'],
                    proxy: {
                        type: 'ajax',
                        url: 'custom/BWorkFlowTemplate_getBusizModelList.action'
                    },
                    autoLoad: true
                },
                displayField: 'NAME',
                valueField: 'ID',
                //forceSelection: true,
                blankText: '请选择业务模型',
                emptyText: '请选择业务模型',
                editable: false,
                queryMode: 'local',
                readOnly: config.id
            },
            {
                xtype: 'combo',
                name: 'bworkFlowTemplate.templateCategory',
                fieldLabel: '模板类别',
                store: {
                    fields: ['TEMPLATE_CATEGORY'],
                    proxy: {
                        type: 'ajax',
                        url: 'custom/BWorkFlowTemplate_getAllCategory.action',
                        reader: {
                            type: 'json',
                            root: 'data'
                        }
                    },
                    autoLoad: true
                },
                displayField: 'TEMPLATE_CATEGORY',
                valueField: 'TEMPLATE_CATEGORY',
                //forceSelection: true,
                blankText: '请选择模板类别',
                emptyText: '请选择模板类别',
                //editable: false,
                queryMode: 'local'
            },
            {
            	xtype: 'fieldcontainer',
            	fieldLabel: '审核提醒',
            	layout: 'hbox',
            	margin: '0 0 3 4',
            	items:[{
            		xtype: 'checkbox',
            		id: 'sendMessage',
            		name: 'bworkFlowTemplate.sendMessage',
            		boxLabel: '发送短信',
            		uncheckedValue: '0',
            		inputValue: '1',
            		checked: parseInt(config.sendMessage),
            		margin: '1 0 3 0'
            	},{
            		xtype: 'checkbox',
            		id: 'sendEmail',
            		name: 'bworkFlowTemplate.sendEmail',
            		boxLabel: '发送电子邮件',
            		uncheckedValue: '0',
            		inputValue: '1',
            		checked: parseInt(config.sendEmail),
            		margin: '1 0 3 20'
            	}]
            	
            },
            { xtype: 'textareafield', name: 'bworkFlowTemplate.remark', fieldLabel: '备注', flex: 1 }
        ],
        buttons: [
            { text: '保存', handler: function () {
            	if(Ext.getCmp("templateBusizId").getValue() == null) {
            		Ext.Msg.alert("提示", "请选择业务模型");
            		return;
            	}
                this.up('form').getForm().submit({
                    url: 'custom/BWorkFlowTemplate_doEdit.action',
                    success: function (form, action) { form.setValues({ templateId: action.result.data }); if (config && config.submitSccess) config.submitSccess(form, action) },
                    failure: function (form, action) { if (config && config.submitFailure) config.submitFailure(form, action) }
                });
            }
            },
            { text: '取消', handler: function () { if (config && config.close) config.close() } }
        ]
    })
	if (config.id) {
        this.form.getForm().load({
            url: 'custom/BWorkFlowTemplate_edit.action',
            params: { templateId: config.id }
        });
    }
    return this.form;
}



MB.form.metaEdit = function (config) {
    this.name = "表单模型编辑";
    this.form = new Ext.form.FormPanel({
        params: { sid: config.id, jid: 'ssss' },
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        height: 400,
        bodyPadding: 10,
        autoScroll: true,
        //autoHeight: true,
        width: '100%',
        //stateful: false,
        fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', labelWidth: 80 },
        items: [
            { xtype: 'hiddenfield', name: 'ID', value: config.id, hidden: true },
            { xtype: 'hiddenfield', name: 'Parent', value: config.metaparams.parent, hidden: true },
            {
                xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: { flex: 1, labelWidth: 80 },
                items: [
                    { xtype: 'textfield', id: 'BuizMeta$Code', name: 'Code', fieldLabel: '编码', allowBlank: false, blankText: '编码必填' },
                    { xtype: 'splitter', width: 40 },
                    { xtype: 'textfield', name: 'Name', fieldLabel: '名称', allowBlank: false, blankText: '名称必填' }
                ]
            },
            {
                xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: { flex: 1, labelWidth: 80 },
                items: [
                //{ xtype: 'textfield', name: 'Type', fieldLabel: '类型' },
                    {
                    xtype: 'combo',
                    id: 'combo_Type',
                    name: 'Type',
                    fieldLabel: '类型',
                    queryMode: 'local',
                    displayField: 'Name',
                    valueField: 'Code',
                    editable: false,
                    allowBlank: false, blankText: '类型必填',
                    forceSelection: true,
                    emptyText: '请选择',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['ID', 'Name', 'Code'],
                        proxy: {
                            type: 'ajax',
                            url: '/custom/autoform/getMetaViewList',
                            reader: {
                                type: 'json',
                                root: 'data'
                            }
                        },
                        autoLoad: true,
                        listeners: {
                            load: function (store, records, successful) {
                                if (!successful) {
                                    Ext.Msg.alert("提示",'数据加载失败');
                                }
                                else {
                                    var field = Ext.getCmp('combo_Type');
                                    if (field) {
                                        field.select(field.getValue());
                                        //field.setValue(field.getValue());
                                    }
                                    //alert('数据加载成功');
                                }
                            }
                        }
                        //data: [{ id: 'table', name: '表格' }, { id: 'string', name: '字符串' }, { id: 'numeric', name: '数值' }, { id: 'bool', name: '布尔值' }, { id: 'date', name: '日期时间' }, { id: 'User', name: '<系统用户>'}]
                    })
                },
                    { xtype: 'splitter', width: 40 },
                    { xtype: 'displayfield', name: 'ParentName', fieldLabel: '属于', value: config.metaparams.parentName }
                    ]
            },
            {
                xtype: 'fieldset',
                title: '高级选项',
                //collapsible: true,
                //collapsed: false,
                items: [
                    {
                        xtype: 'fieldcontainer',
                        layout: 'hbox',
                        fieldDefaults: { flex: 1, labelWidth: 80 },
                        items: [
                            { xtype: 'textfield', name: 'XType', fieldLabel: '控件类型' },
                            { xtype: 'splitter', width: 40 },
                            {
                                xtype: 'fieldcontainer',
                                layout: 'hbox',
                                fieldDefaults: { flex: 1, labelWidth: 80 },
                                items: [
                                    { xtype: 'checkboxfield', id: 'Clear', name: 'Clear', uncheckedValue: '0', inputValue: '1', fieldLabel: '强制换行' },
                                    { xtype: 'splitter', width: 40 },
                                    { xtype: 'checkboxfield', id: 'ReadOnly', name: 'ReadOnly', readOnly: true, uncheckedValue: '0', inputValue: '1', fieldLabel: '只读', labelWidth: 40, checked: config.readOnly }
                                ]
                            }
                        ]
                    },
                    {
                        xtype: 'fieldcontainer',
                        layout: 'hbox',
                        fieldDefaults: { flex: 1, labelWidth: 80 },
                        items: [
                            { xtype: 'textfield', name: 'Width', fieldLabel: '宽度(%)' },
                            { xtype: 'splitter', width: 40 },
                            { xtype: 'textfield', name: 'Height', fieldLabel: '高度' }
                        ]
                    },
                    {
                        xtype: 'fieldcontainer',
                        layout: 'hbox',
                        fieldDefaults: { flex: 1, labelWidth: 80 },
                        items: [
                            { xtype: 'textfield', name: 'MinLen', fieldLabel: '最小(短)' },
                            { xtype: 'splitter', width: 40 },
                            { xtype: 'textfield', name: 'MaxLen', fieldLabel: '最大(长)' }
                        ]
                    }
                ]
            },
            {
                xtype: 'tabpanel',
                flex: 1,
                bodyPadding: 2,
                items: [
                    { xtype: 'textareafield', name: 'Default', title: '缺省[F]', flex: 1 },
                    { xtype: 'textareafield', name: 'Rules', title: '计算[T]', flex: 1 },
                    { xtype: 'textareafield', name: 'ViewSQL', title: 'SQL[V]', flex: 1 },
                    { xtype: 'textareafield', name: 'PreSQL', title: 'SQL[P]', flex: 1 },
                    { xtype: 'textareafield', name: 'FeedBackURL', title: '状态反馈{I]', flex: 1 },
                    { xtype: 'textareafield', name: 'EAXSLT', title: '解析模板[I]', flex: 1 },
                    { xtype: 'textareafield', name: 'Connstring', title: '连接串[V]', flex: 1 },
                    { xtype: 'textareafield', name: 'BuizForms', title: '表单列表', flex: 1, value: 'autoform:保存(save)' },
                    { xtype: 'textareafield', name: 'BuizSummary', title: '摘要模板', flex: 1 },
                    { xtype: 'textareafield', name: 'BuizXSLT', title: '模板(移动终端)', flex: 1 },
                    { xtype: 'textareafield', name: 'FieldCfg', title: '控件配置', flex: 1 }
                ]
            }
        ],
        buttons: [
            { text: '保存', handler: function () {
                this.up('form').getForm().submit({
                    url: '/workflow/autoform/saveMeta',
                    success: function (form, action) { form.setValues({ ID: action.result.data }); if (config && config.submitSccess) config.submitSccess(form, action) },
                    failure: function (form, action) { if (config && config.submitFailure) config.submitFailure(form, action) }
                });
            }
            },
            { text: '取消', handler: function () { if (config && config.close) config.close() } }
        ]
    })
   
    if (config.id) {
        //Ext.getCmp('BuizMeta$Code').setReadOnly(true); 
        this.form.getForm().load({
            url: '/workflow/autoform/getMetaByID',
            params: { id: config.id }
        }
        );
    }

    return this.form;
}

