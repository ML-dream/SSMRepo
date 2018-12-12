
var MB = new function(){};
MB.form = new function(){};

MB.form.metaType = function(config){
	Ext.create("Ext.window.Window",{
		id: 'metaTypeWin',
		title: config.title,
		width: Ext.getBody().getWidth() * 0.5,
		height: Ext.getBody().getHeight()* 0.6,
		layout: 'fit',
		modal: true,
       	maximizable: true,
       	renderTo: Ext.getBody(),
       	items: {
       		xtype: 'form',
       		id: 'metaTypeForm',
			border: 1,
			layout: {
				type: 'vbox',
				align: 'stretch'
			},
			autoScroll: true,
			bodyPadding: '10 30 10 30',
			fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', labelWidth: 80 },
       		items: [
       			{ xtype: 'hiddenfield', id: 'metaTtypeId', name: 'bmetaType.metaTypeId', value: config.data.metaTypeId, hidden: true },
	            {
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                    { xtype: 'textfield', name: 'bmetaType.metaTypeNum', fieldLabel: '编码', allowBlank: false, blankText: '编码必填', value: config.data.metaTypeNum},
	                    { xtype: 'splitter', width: 40 },
	                    { xtype: 'textfield', name: 'bmetaType.metaTypeName', fieldLabel: '名称', allowBlank: false, blankText: '名称必填', value: config.data.metaTypeName }
	                ]
	            },
	            {
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldLabel : '类型',
	                defaultType: 'radiofield',
	                fieldDefaults: {labelWidth: 80 },
	                items: [
	                	{boxLabel: '基本类型', name: 'bmetaType.metaTypeClas',inputValue: '1', checked: config.baseRadio},
                        { xtype: 'splitter', width: 40 },
                        {boxLabel: '自定义类型', name: 'bmetaType.metaTypeClas',inputValue: '2', checked: config.ownRadio}
	                ]
	            },
	            { xtype: 'textareafield', name: 'bmetaType.metaTypeSql', fieldLabel: '缺省/SQL', flex: 1, value: config.data.metaTypeSql },
	            { xtype: 'textareafield', id: 'metaTypeConfig', name: 'bmetaType.metaTypeConfig', fieldLabel: '控件配置', flex: 2, value: config.data.metaTypeConfig}
       		]
       	},
       	buttons: [{
			text: '保存',
			handler: function(){
				var url = 'custom/BMetaType_add.action';
				if(config.data.metaTypeId) {
					url = 'custom/BMetaType_edit.action';
				}
				Ext.getCmp('metaTypeForm').getForm().submit({
					url: url,
					success: function(form, action) {
						var result = Ext.decode(action.response.responseText);
                       	Ext.Msg.alert('成功', result.data, function(){
                       		Ext.getCmp('metaTypeGrid').getStore().load();
							Ext.getCmp('metaTypeWin').close();
                       });
                    },
                    failure: function(form, action) {
                    	var result = Ext.decode(action.response.responseText);
                        Ext.Msg.alert('失败', result.data);
                    }
				});
			}
		}, {
			text: '取消',
			handler: function(){
				Ext.getCmp('metaTypeWin').close();
			}
		}]
	}).show();
};

MB.form.AutoFormMeta = function(config){
	Ext.create("Ext.window.Window",{
		id: 'metaWin',
		title: config.title,
		width: Ext.getBody().getWidth() * 0.6,
		height: Ext.getBody().getHeight()* 0.7,
		layout: 'fit',
		modal: true,
       	maximizable: true,
       	renderTo: Ext.getBody(),
       	items: {
       		xtype: 'form',
			id: 'metaForm',
			border: 1,
			layout: {
				type: 'vbox',
				align: 'stretch'
			},
			autoScroll: true,
			bodyPadding: '10 30 10 30',
			fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', labelWidth: 80 },
			items: [
	            { xtype: 'hiddenfield', id: 'metaId', name: 'bautoForm.metaId', value: config.data.metaId, hidden: true },
	            { xtype: 'hiddenfield', id: 'metaPid', name: 'bautoForm.metaPid', value: config.metaPid, hidden: true },
	            { xtype: 'hiddenfield', id: 'metaNumOld', name: 'bautoForm.metaNumOld', value: config.data.metaNumOld, hidden: true },
	            {
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                    { xtype: 'textfield', id: 'metaNum', name: 'bautoForm.metaNum', fieldLabel: '编码', allowBlank: false, blankText: '编码必填', value: config.data.metaNum},
	                    { xtype: 'splitter', width: 40 },
	                    { xtype: 'textfield', id: 'metaName', name: 'bautoForm.metaName', fieldLabel: '名称', allowBlank: false, blankText: '名称必填', value: config.data.metaName }
	                ]
	            },
	            {
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                {
	                    xtype: 'combo',
	                    id: 'metaType',
	                    name: 'bautoForm.metaType',
	                    fieldLabel: '类型',
	                    queryMode: 'local',
	                    displayField: 'DATA_ITEM_TYPE_NAME',
	                    valueField: 'DATA_ITEM_TYPE_CODE',
	                    editable: false,
	                    allowBlank: false, blankText: '类型必填',
	                    forceSelection: true,
	                    emptyText: '请选择',
	                    store: Ext.create('Ext.data.Store', {
	                        fields: ['DATA_ITEM_TYPE_ID', 'DATA_ITEM_TYPE_NAME', 'DATA_ITEM_TYPE_CODE'],
	                        proxy: {
	                            type: 'ajax',
	                            url: 'custom/BAutoForm_getMetaTypeList.action',
	                            extraParams: {optType: config.optType},
	                            reader: {
	                                type: 'json',
	                                root: 'data'
	                            }
	                        },
	                        autoLoad: true,
	                        listeners: {
	                            load: function (store, records, successful) {
	                                if (!successful) {
	                                    Ext.Msg.alert('提示','数据加载失败！');
	                                }
	                                else {
	                                    var field = Ext.getCmp('metaType');
	                                    if (field) {
	                                        field.select(config.data.metaType);
	                                        
	                                    }
	                                }
	                            }
	                        }
	                    })
	            	},
	                { xtype: 'splitter', width: 40 },
	                { xtype: 'displayfield', id: 'metaPName', fieldLabel: '属于', value: config.metaPNum }
	                ]
	            },
	            {
	                xtype: 'fieldset',
	                title: '高级选项',
	                items: [
	                    {
	                        xtype: 'fieldcontainer',
	                        layout: 'hbox',
	                        fieldDefaults: { flex: 1, labelWidth: 80 },
	                        items: [
	                            { xtype: 'textfield', name: 'bautoForm.metaFieldType', fieldLabel: '控件类型', value: config.data.metaFieldType },
	                            { xtype: 'splitter', width: 40 },
	                            {
	                                xtype: 'fieldcontainer',
	                                layout: 'hbox',
	                                fieldDefaults: { flex: 1, labelWidth: 80 },
	                                items: [
	                                    { xtype: 'checkboxfield', id: 'metaForceLine', name: 'bautoForm.metaForceLine', uncheckedValue: '0', inputValue: '1', fieldLabel: '强制换行', checked: parseInt(config.data.metaForceLine)},
	                                    { xtype: 'splitter', width: 40 },
	                                    { xtype: 'checkboxfield', id: 'metaReadOnly', name: 'bautoForm.metaReadOnly', uncheckedValue: '0', inputValue: '1', fieldLabel: '只读', labelWidth: 40, checked: parseInt(config.data.metaReadOnly) }
	                                ]
	                            }
	                        ]
	                    },
	                    {
	                        xtype: 'fieldcontainer',
	                        layout: 'hbox',
	                        fieldDefaults: { flex: 1, labelWidth: 80 },
	                        items: [
	                            { xtype: 'textfield', name: 'bautoForm.metaFieldWidth', fieldLabel: '宽度(%)', value: config.data.metaFieldWidth },
	                            { xtype: 'splitter', width: 40 },
	                            { xtype: 'textfield', name: 'bautoForm.metaFieldHeight', fieldLabel: '高度', value: config.data.metaFieldHeight }
	                        ]
	                    },
	                    {
	                        xtype: 'fieldcontainer',
	                        layout: 'hbox',
	                        fieldDefaults: { flex: 1, labelWidth: 80 },
	                        items: [
	                            { xtype: 'textfield', name: 'bautoForm.metaFieldMinLen', fieldLabel: '最小(短)', value: config.data.metaFieldMinLen },
	                            { xtype: 'splitter', width: 40 },
	                            { xtype: 'textfield', name: 'bautoForm.metaFieldMaxLen', fieldLabel: '最大(长)', value: config.data.metaFieldMaxLen }
	                        ]
	                    }
	                ]
	            },
	            {
	                xtype: 'tabpanel',
	                flex: 1,
	                bodyPadding: 2,
	                items: [
	                    { xtype: 'textareafield', name: 'bautoForm.metaDefault', title: '缺省[F]', flex: 1, value: config.data.metaDefault },
	                    /**
	                    { xtype: 'textareafield', name: 'Rules', title: '计算[T]', flex: 1 },
	                    { xtype: 'textareafield', name: 'ViewSQL', title: 'SQL[V]', flex: 1 },
	                    { xtype: 'textareafield', name: 'PreSQL', title: 'SQL[P]', flex: 1 },
	                    { xtype: 'textareafield', name: 'FeedBackURL', title: '状态反馈{I]', flex: 1 },
	                    { xtype: 'textareafield', name: 'EAXSLT', title: '解析模板[I]', flex: 1 },
	                    { xtype: 'textareafield', name: 'Connstring', title: '连接串[V]', flex: 1 },
	                    { xtype: 'textareafield', name: 'BuizForms', title: '表单列表', flex: 1, value: 'autoform:保存(save)' },
	                    { xtype: 'textareafield', name: 'BuizSummary', title: '摘要模板', flex: 1 },
	                    { xtype: 'textareafield', name: 'BuizXSLT', title: '模板(移动终端)', flex: 1 },**/
	                    { xtype: 'textareafield', name: 'bautoForm.metaFieldConfig', title: '控件配置', flex: 1, value: config.data.metaFieldConfig }
	                    
	                ]
	            }
	        ]
       	},
       buttons: [{
			text: '保存',
			handler: function(){
				var url = 'custom/BAutoForm_add.action';
				if(config.data.metaId) {
					url = 'custom/BAutoForm_edit.action';
				}
				Ext.getCmp('metaForm').getForm().submit({
					url: url,
					params: {optType: Ext.getCmp('metaType').getValue(), optTypeSource: config.optType, metaPNum: config.metaPNum, metaPName: config.metaPName},
					success: function(form, action) {
						var result = Ext.decode(action.response.responseText);
                       	Ext.Msg.alert('成功', result.data, function(){
                       		if(config.optType){
                       			Ext.getCmp('autoFormGrid').getStore().load();
                       		} else {
                       			//清空所有选择
                       			Ext.getCmp("dataItemTree").getSelectionModel().clearSelections();
                       			Ext.getCmp("dataItemTree").getStore().load({params: {metaPid: Ext.getCmp('autoFormGrid').getSelectionModel().selected.items[0].data.metaId}});
                       		}
							Ext.getCmp('metaWin').close();
                       });
                    },
                    failure: function(form, action) {
                    	var result = Ext.decode(action.response.responseText);
                        Ext.Msg.alert('失败', result.data);
                    }
				});
			}
		}, {
			text: '取消',
			handler: function(){
				Ext.getCmp('metaWin').close();
			}
		}]
	}).show();
}
