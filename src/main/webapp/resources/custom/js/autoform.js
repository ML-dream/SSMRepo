
var MB = new function(){};
MB.form = new function(){};

MB.form.AutoFormMeta = function(config){
	//根据表单视图类型判断是否只读
	var viewReadOnly = true;
	//表单视图类型
	var metaFormType = '0';
	if(config.viewReadOnly){
		viewReadOnly = false;
	}
	
	if(config.data.metaFormType){
		metaFormType = config.data.metaFormType;
	}
	
	Ext.create("Ext.window.Window",{
		id: 'metaWin',
		title: config.title,
		width: Math.ceil(Ext.getBody().getWidth() * 0.6),
		height: Math.ceil(Ext.getBody().getHeight()* 0.8),
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
	                    displayField: 'META_TYPE_NAME',
	                    valueField: 'META_TYPE_NUM',
	                    editable: false,
	                    allowBlank: false,
	                    blankText: '类型必填',
	                    forceSelection: true,
	                    emptyText: '请选择',
	                    store: Ext.create('Ext.data.Store', {
	                        fields: ['META_TYPE_ID', 'META_TYPE_NAME', 'META_TYPE_NUM'],
	                        proxy: {
	                            type: 'ajax',
	                            url: 'custom/BAutoForm_getMetaTypeList.action',
	                            //extraParams: {optType: config.optType},
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
	                {
	                    xtype: 'combo',
	                    id: 'metaFormType',
	                    name: 'bautoForm.metaFormType',
	                    fieldLabel: '视图',
	                    queryMode: 'local',
	                    displayField: 'name',
	                    valueField: 'id',
	                    editable: false,
	                    readOnly: viewReadOnly,
	                    store:{
	                    	fields: ['id', 'name'],
	                    	data:[{'name': 'autoform', 'id': '0'}, {'name': 'database', 'id': '1'}]
	                    },
	                    value: metaFormType
	            	}
	                ]
	            },
	            {
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                	{ xtype: 'displayfield', id: 'metaPName', fieldLabel: '属于', value: config.metaPNum },
	                    { xtype: 'splitter', width: 40 },
	                    {
                            xtype: 'fieldcontainer',
                            layout: 'hbox',
                            fieldDefaults: { flex: 1, labelWidth: 80 },
                            items: [
                                { xtype: 'checkboxfield', id: 'metaPrimaryKey', name: 'bautoForm.metaPrimaryKey', fieldLabel: '主键', uncheckedValue: '0', inputValue: '1', checked: parseInt(config.data.metaPrimaryKey)},
                                { xtype: 'splitter', width: 40 },
                                { xtype: 'checkboxfield', id: 'metaForeignKey', name: 'bautoForm.metaForeignKey', fieldLabel: '外键', uncheckedValue: '0', inputValue: '1', labelWidth: 60, checked: parseInt(config.data.metaForeignKey) }
                            ]
                        }
	                ]
	            },
	            {
	                xtype: 'fieldset',
	                title: '高级选项',
	                margin: '10 0 10 0',
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
	                                    { xtype: 'checkboxfield', id: 'metaNotNull', name: 'bautoForm.metaNotNull', uncheckedValue: '0', inputValue: '1', fieldLabel: '必填', labelWidth: 40, checked: parseInt(config.data.metaNotNull) }
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
	                    {
	                    	xtype: 'panel',
	                    	title: 'SQL[V]',
	                    	//border: 1,
	                    	layout: {
								type: 'vbox',
								align: 'stretch'
							},
	                    	bodyPadding: '5 10 5 10',
	                    	items: [{ 	
	                    			xtype: 'combo',
				                    id: 'handleType',
				                    fieldLabel: '类型',
				                    queryMode: 'local',
				                    displayField: 'name',
				                    valueField: 'id',
				                    editable: false,
				                    store:{
				                    	fields: ['id', 'name'],
				                    	data:[{'name': '未处理', 'id': '0'}, {'name': '已处理', 'id': '1'}]
				                    },
				                    value: '0',
				                    listeners: {
				                    	'select': function(combo, records, opts) {
				                    		if(records[0].data.id == '0') {
				                    			Ext.getCmp('metaUnhandleSql').setVisible(true);
				                    			Ext.getCmp('metaHandledSql').setVisible(false);
				                    		}else {
				                    			Ext.getCmp('metaUnhandleSql').hide();
				                    			Ext.getCmp('metaHandledSql').show();
				                    		}
				                    	}
				                    }
				                 },
	                    		{ xtype: 'textareafield', id: 'metaUnhandleSql', name: 'bautoForm.metaUnhandleSql', fieldLabel: 'SQL[V]', value: config.data.metaUnhandleSql, flex: 1 },
	                    		{ xtype: 'textareafield', id: 'metaHandledSql', name: 'bautoForm.metaHandledSql', fieldLabel: 'SQL[V]', value: config.data.metaHandledSql, flex: 1, hidden: true }
	                    	]
	                    },
	                    /**
	                    { xtype: 'textareafield', name: 'Rules', title: '计算[T]', flex: 1 },
	                    
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
				if(Ext.getCmp('metaForm').getForm().isValid()){
					Ext.getCmp('metaForm').getForm().submit({
						url: url,
						params: {optType: Ext.getCmp('metaType').getValue(), optTypeSource: config.optType, metaPNum: config.metaPNum, metaPName: config.metaPName},
						waitMsg: '正在保存，请稍后...',
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
			}
		}, {
			text: '取消',
			handler: function(){
				Ext.getCmp('metaWin').close();
			}
		}]
	}).show();
}

/**
 * 测试自动查询
 * @param {} config
 */
MB.form.AutoSearchText = function(config){
	//数据项列表
	var dataItemList = config.dataItemList;
	//主键编码
	var pkNum;
	for(var i = 0; i< dataItemList.length; i++){
		if(dataItemList[i].META_PRIMARY_KEY ==1){
			pkNum = dataItemList[i].META_NUM;
			break;
		}
	}
	
	var columns = [{ text: '序号', xtype: 'rownumberer', width: 40, sortable: false},{
	  		text: '操作' , xtype: 'actioncolumn',width:50, sortable: false, align: "center", 
			items: [{
	 	  		icon: 'resources/custom/images/query.png', tooltip: "查看",
			 	handler: function(grid, rowIndex, colIndex){
		 	  	   	   var rec = grid.getStore().getAt(rowIndex);
				  	   showFormView(rec.get(pkNum), config.metaId, config.metaNum, null, 'AUTO_FORM', config.metaName, 'READ');
			 	}
		 	  }] 
	}];
	var fields = [];
	
	Ext.Array.forEach(dataItemList, function(dataItem) {
		if(dataItem.META_PRIMARY_KEY == 1){
			fields.push(dataItem.META_NUM);
		}else {
			columns.push({text: dataItem.META_NAME, dataIndex: dataItem.META_NUM, flex: 1});
			fields.push(dataItem.META_NUM);
		}
	},this);
	
	var formStore = Ext.create("Ext.data.Store",{
		storeId: 'searchFormStore',
		fields: fields,
		pageSize: pageSize,
		proxy: {
			type: "ajax",
			url: "custom/BAutoForm_getFormDataList.action",
			extraParams: {busizTableName: config.metaNum},
			reader: {
				type: "json",
				root: "data",
				totalProperty: "total"
			}
		},
		autoLoad: true,
		listeners: {
			load: function (store, records, successful) {
                 if (!successful) {
                     Ext.Msg.alert("提示", '数据加载失败！');
                 }
             }
		}
	});
	
	Ext.create("Ext.window.Window",{
		id: 'searchListWin',
		title: config.metaName,
		width: Ext.getBody().getWidth() * 0.8,
		height: Ext.getBody().getHeight()* 0.9,
		layout: 'fit',
		modal: true,
       	maximizable: true,
       	renderTo: Ext.getBody(),
       	items: {
       		xtype: 'grid',
       		id: 'searchGrid',
       		columnLines: true,
       		rowexpander: 30,
       		store: formStore,
    		columns: columns, 
			bbar: getPagingtoolbar('searchFormStore')
       	}
       	
	}).show();
}


/**
 * 表单设置
 * @param {} config
 */
MB.form.setAutoFormMeta = function(config) {
	Ext.create("Ext.window.Window",{
		id: 'setAutoFormWin',
		title: '表单设置',
		width: Math.ceil(Ext.getBody().getWidth() * 0.7),
		height: Math.ceil(Ext.getBody().getHeight()* 0.85),
		layout: 'fit',
		modal: true,
       	maximizable: true,
       	renderTo: Ext.getBody(),
       	items: {
       		xtype: 'tabpanel',
			id: 'setAutoFormTab',
			title: '',
			flex: 1,
		    bodyPadding: 2,
       		items: [/**{
	       		xtype: 'tabpanel',
				id: 'queryParamsTab',
				title: '查询参数设置',
				//flex: 1,
			    //bodyPadding: 2,
			    items: [{
			    	xtype: 'grid',
			    	id: 'variableGrid',
			    	title: '变量设置',
			    	autoScroll: true,
			    	tbar: getToolBar('variableTbar', 'variableGrid', 'B_REPORT_VARIABLE', config.metaId),
			    	columns:[
			    		{ text: '序号', xtype: 'rownumberer', width: 40, sortable: false, height:25},
			    		{ text: '变量名称', dataIndex: 'VARIABLE_NAME', flex: 1},
			    		{ text: '变量编码', dataIndex: 'VARIABLE_CODE', flex: 1},
			    		{ text: '变量类型', dataIndex: 'META_TYPE_NAME', flex: 1},
			    		{ text: '变量表达式', dataIndex: 'VARIABLE_EXPRESS', flex: 1},
			    		{ text: '缺省值', dataIndex: 'VARIABLE_DEFAULT_VALUE', flex: 1},
			    		{ text: '是否显示', dataIndex: 'VARIABLE_IS_DISPLAY_TEXT', flex: 1}
			    	],
			    	store: Ext.create('Ext.data.Store',{
		        		proxy: {
		      				type: "ajax",
		      				url: "custom/BAutoForm_getFormQueryParamsList.action",
		      				extraParams: {metaId: config.metaId, source: 'B_REPORT_VARIABLE'},
		      				reader: {
		      					type: "json",
		      					root: "data"
		      				}
		      			},
		      			fields: ['VARIABLE_ID', 'DATA_ITEM_TYPE_ID', 'VARIABLE_CODE', 'VARIABLE_NAME', 'VARIABLE_DEFAULT_VALUE', 'VARIABLE_EXPRESS', 
		        			'VARIABLE_PARAMS', 'REMARK', 'VARIABLE_IS_DISPLAY', 'CREATE_TIME', 'META_TYPE_NAME', 'VARIABLE_IS_DISPLAY_TEXT'],
		      			autoLoad: true,
		      			listeners: {
		      				load: function (store, records, successful) {
		                         if (!successful) {
		                             Ext.Msg.alert("提示", '数据加载失败！');
		                         } 
		                     }
		      			}
		        	}),
			    	columnLines: true
			    	
			    },{
			    	xtype:"treepanel",
			    	id: 'filterCriteriaGrid',
			    	title: '筛选条件设置',
			    	autoScroll: true,
			    	tbar: getToolBar('filterCriteriaTbar', 'filterCriteriaGrid', 'B_REPORT_FILTER_CRITERIA', config.metaId),
			    	rootVisible: false,
			        columns: [
				        {
				        	xtype: 'treecolumn', 
				            text: '名称',
				            flex: 1,
				            menuDisabled: true,
				            sortable: false,
				            dataIndex: 'FILTER_CRITERIA_NAME'
				        },
				         {
				            text: '类型',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'META_TYPE_NAME'
				        },
				         {
				            text: '左表达式',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'META_NAME'
				        },
				         {
				            text: '运算符',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'FILTER_CRITERIA_OPERATOR_NAME'
				        },
				        {
				            text: '右表达式',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'VARIABLE_NAME'
				        },
				        {
				            text: '条件说明',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'REMARK'
				        }
			        ],
			    	store: {
			    		xtype: 'tree',
		        		fields: [
				            {name: 'FILTER_CRITERIA_ID',     type: 'string'},
				            {name: 'TEMPLATE_ID',     type: 'string'},
				            {name: 'DATA_ITEM_ID', type: 'string'},
				            {name: 'FILTER_CRITERIA_CODE',     type: 'string'},
				            {name: 'FILTER_CRITERIA_PCODE', type: 'string'},
				            {name: 'FILTER_CRITERIA_NAME', type: 'string'},
				            {name: 'FILTER_CRITERIA_OPERATOR', type: 'string'},
				            {name: 'FILTER_CRITERIA_OPERATOR_NAME', type: 'string'},
				            {name: 'FILTER_CRITERIA_REXPRESS', type: 'string'},
				            {name: 'REMARK', type: 'string'},
				            {name: 'META_NAME', type: 'string'},
				            {name: 'META_TYPE_NAME', type: 'string'},
				            {name: 'FILTER_CRITERIA_TYPE', type: 'string'},
				            {name: 'VARIABLE_NAME', type: 'string'}
				        ],
		        		proxy: {
		      				type: "ajax",
		      				url: "dreport/BReportFilterCriteria_listJson.action",
		      				extraParams: {modelSource: 'AUTO_FORM', templateId: config.metaId, source: 'B_REPORT_FILTER_CRITERIA', filterCriteriaType: 1}
		      			},
		      			listeners: {
		      				load: function (store, records, successful) {
		                         if (!successful) {
		                             Ext.Msg.alert("提示", '数据加载失败！');
		                         } 
		                     }
		      			}
		        	}
			    },{
			    	xtype: 'grid',
			    	id: 'groupFieldGrid',
			    	title: '分组设置',
			    	autoScroll: true,
			    	tbar: getToolBar('groupFieldTbar', 'groupFieldGrid', 'B_REPORT_GROUP_FIELD', config.metaId),
			    	columns:[
			    		{ text: '序号', xtype: 'rownumberer', width: 40, sortable: false, height:25},
			    		{ text: '分组字段名称', dataIndex: 'GROUP_FIELD_NAME', flex: 1},
			    		{ text: '分组字段', dataIndex: 'META_NAME', flex: 1},
			    		{ text: '创建时间', dataIndex: 'CREATE_TIME', flex: 1}
			    	],
			    	store: {
			    		xtype: 'store',
		        		proxy: {
		      				type: "ajax",
		      				url: "custom/BAutoForm_getFormQueryParamsList.action",
		      				extraParams: {metaId: config.metaId, source: 'B_REPORT_GROUP_FIELD'},
		      				reader: {
		      					type: "json",
		      					root: "data"
		      				}
		      			},
		      			fields: ['GROUP_FIELD_ID', 'GROUP_FIELD_NAME', 'TEMPLATE_ID', 'DATA_ITEM_ID', 'CREATE_TIME', 'META_NAME', 'DATA_ITEM_CODE', 'REMARK'],
		      			listeners: {
		      				load: function (store, records, successful) {
		                         if (!successful) {
		                             Ext.Msg.alert("提示", '数据加载失败！');
		                         } 
		                     }
		      			}
		        	},
			    	columnLines: true
			    },{
			    	xtype:"treepanel",
			    	id: 'groupCriteriaGrid',
			    	title: '分组筛选条件设置',
			    	autoScroll: true,
			    	tbar: getToolBar('groupCriteriaTbar', 'groupCriteriaGrid', 'B_REPORT_FILTER_CRITERIA', config.metaId),
			    	rootVisible: false,
			        columns: [
				        {
				        	xtype: 'treecolumn', 
				            text: '名称',
				            flex: 1,
				            menuDisabled: true,
				            sortable: false,
				            dataIndex: 'FILTER_CRITERIA_NAME'
				        },
				         {
				            text: '类型',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'META_TYPE_NAME'
				        },
				         {
				            text: '左表达式',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'META_NAME'
				        },
				         {
				            text: '运算符',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'FILTER_CRITERIA_OPERATOR_NAME'
				        },
				        {
				            text: '右表达式',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'FILTER_CRITERIA_REXPRESS'
				        },
				        {
				            text: '条件说明',
				            flex: 1,
				            sortable: false,
				            dataIndex: 'REMARK'
				        }
			        ],
			    	store: {
			    		xtype: 'tree',
		        		fields: [
				            {name: 'FILTER_CRITERIA_ID',     type: 'string'},
				            {name: 'TEMPLATE_ID',     type: 'string'},
				            {name: 'DATA_ITEM_ID', type: 'string'},
				            {name: 'FILTER_CRITERIA_CODE',     type: 'string'},
				            {name: 'FILTER_CRITERIA_PCODE', type: 'string'},
				            {name: 'FILTER_CRITERIA_NAME', type: 'string'},
				            {name: 'FILTER_CRITERIA_OPERATOR', type: 'string'},
				            {name: 'FILTER_CRITERIA_OPERATOR_NAME', type: 'string'},
				            {name: 'FILTER_CRITERIA_REXPRESS', type: 'string'},
				            {name: 'REMARK', type: 'string'},
				            {name: 'META_NAME', type: 'string'},
				            {name: 'META_TYPE_NAME', type: 'string'},
				            {name: 'FILTER_CRITERIA_TYPE', type: 'string'}
				        ],
		        		proxy: {
		      				type: "ajax",
		      				url: "dreport/BReportFilterCriteria_listJson.action",
		      				extraParams: {modelSource: 'AUTO_FORM', templateId: config.metaId, source: 'B_REPORT_FILTER_CRITERIA', filterCriteriaType: 2}
		      			},
		      			listeners: {
		      				load: function (store, records, successful) {
		                         if (!successful) {
		                             Ext.Msg.alert("提示", '数据加载失败！');
		                         } 
		                     }
		      			}
		        	}
			    },{
			    	xtype: 'grid',
			    	id: 'outputFieldGrid',
			    	title: '输出字段设置',
			    	autoScroll: true,
			    	tbar: getToolBar('outputFieldTbar', 'outputFieldGrid', 'B_REPORT_OUTPUT_FIELD', config.metaId),
			    	columns: [
			    		{text: '序号', xtype: 'rownumberer', width: 40, sortable: false, height: 25},
			    		{text: '输出字段名称', dataIndex: 'OUTPUT_FIELD_NAME', flex: 1},
			    		{text: '输出字段', dataIndex: 'DATA_ITEM_CODE', flex: 1},
			    		{text: '排序号', dataIndex: 'OUTPUT_FIELD_SORT_NO', flex: 1},
			    		{text: '创建时间', dataIndex: 'CREATE_TIME', flex: 1}
			    	],
			    	store: {
			    		xtype: 'store',
			    		fields: ['OUTPUT_FIELD_ID', 'OUTPUT_FIELD_SORT_NO', 'OUTPUT_FIELD_NAME', 'OUTPUT_FIELD_PARAMS', 'CREATE_TIME', 
			    			'DATA_ITEM_CODE', 'DATA_ITEM_ID', 'REMARK'],
			    		proxy: {
			    			type: 'ajax',
			    			url: 'custom/BAutoForm_getFormQueryParamsList.action',
			    			extraParams: {metaId: config.metaId, source: 'B_REPORT_OUTPUT_FIELD'},
			    			reader: {
			    				type: 'json',
			    				root: 'data'
			    			}
			    		},
			    		listeners: {
			    			load: function(store, records, successful) {
			    				if(!successful) {
			    					Ext.Msg.alert('提示', '数据加载失败！');
			    				}
			    				
			    			}
			    		}
			    	},
			    	columnLines: true
			    	
			    },{
			    	xtype: 'grid',
			    	id: 'sortFieldGrid',
			    	title: '排序设置',
			    	autoScroll: true,
			    	columnlines: true,
			    	tbar: getToolBar('sortFieldTbar', 'sortFieldGrid', 'B_REPORT_SORT_FIELD', config.metaId),
			    	columns: [
			    		{text: '序号', xtype: 'rownumberer', width: 40, sortable: false, height: 25},
			    		{text: '排序字段名称', dataIndex: 'SORT_FIELD_NAME', flex: 1},
			    		{text: '排序字段', dataIndex: 'DATA_ITEM_CODE', flex: 1},
			    		{text: '排序号', dataIndex: 'SORT_FIELD_SORT_NO', flex: 1},
			    		{text: '排列方式', dataIndex: 'SORT_FIELD_WAY_NAME', flex: 1},
			    		{text: '创建时间', dataIndex: 'CREATE_TIME', flex: 1}
			    	],
			    	store: {
			    		xtype: 'store',
			    		fields: ['SORT_FIELD_ID', 'SORT_FIELD_NAME', 'SORT_FIELD_SORT_NO', 'SORT_FIELD_WAY', 'CREATE_TIME', 
			    			'DATA_ITEM_CODE', 'DATA_ITEM_ID', 'REMARK', 'SORT_FIELD_WAY_NAME'],
			    		proxy: {
			    			type: 'ajax',
			    			url: 'custom/BAutoForm_getFormQueryParamsList.action',
			    			extraParams: {metaId: config.metaId, source: 'B_REPORT_SORT_FIELD'},
			    			reader: {
			    				type: 'json',
			    				root: 'data'
			    			}
			    		},
			    		listeners: {
			    			load: function(store, records, successful) {
			    				if(!successful) {
			    					Ext.Msg.alert('提示', '数据加载失败！');
			    				
			    				}
			    			}
			    		}
			    	}
			    }],
			    listeners:{
			    	tabchange: function(tabPanel, newCard, oldCard){
			    		Ext.getCmp(newCard.getItemId()).getStore().load();
			    	}
			    }
       		}, {
		    	xtype: 'grid',
		    	id: 'formPageGrid',
		    	title: '表单页面设置',
		    	autoScroll: true,
		    	columnLines: true,
	    		border: 1,
	    		rowexpander: 30,
	    		tbar: Ext.create('Ext.toolbar.Toolbar', {
	    			items: [{
	    				iconCls:'icon-addBig', 
						tooltip:'新增',
						handler: function(){
							
						}
	    			}, {
	    				iconCls:'icon-editBig', 
						tooltip:'编辑',
						handler: function(){
							
						}
	    			}, {
	    				iconCls:'icon-deleteBig', 
						tooltip:'删除',
						handler: function(){
							
						}
	    			}]
	    		}),
	    		store: {
	    			proxy: {
	    				type: 'ajax',
	    				url: '',
	    				render: {
							type: 'json',
							root: 'data'
						}
	    			},
	    			field: [
	    				{name: '', type: ''},
	    				{name: '', type: ''},
	    				{name: '', type: ''}
	    			],
	    			//autoLoad: true,
	    			listeners: {
	    				load: function(store, records, successful){
	    					if(!successful) {
	    						Ext.Msg.alert("提示", '数据加载失败');
	    					}
	    				}
	    			}
	    		},
	    		columns: [
	    			{text: '序号', xtype: 'rownumberer', width: 30, align: 'center'},
	    			{text: '名称', dataIndex: '', flex: 1, align: 'center'},
	    			{text: '类别', dataIndex: '', flex: 1, align: 'center'},
	    			{text: 'html', dataIndex: '', flex: 5, align: 'center'}
	    		]
		}, **/{
		    	xtype: 'grid',
		    	id: 'formActionGrid',
		    	title: '表单动作设置',
		    	autoScroll: true,
		    	columnLines: true,
	    		border: 1,
	    		rowexpander: 30,
	    		tbar: Ext.create('Ext.toolbar.Toolbar', {
	    			items: [{
	    				iconCls:'icon-addBig', 
						text:'新增',
						handler: function(){
							new MB.form.formActionForm({
								title: '新增',
								metaId: config.metaId,
								data: ''
							});
						}
	    			}, {
	    				iconCls:'icon-editBig', 
						text:'编辑',
						handler: function(){
							var actionGrid = Ext.getCmp('formActionGrid').getSelectionModel().selected;
							if (actionGrid.length == 1) {
								new MB.form.formActionForm({
									title: '编辑',
									metaId: config.metaId,
									data: actionGrid.items[0].data
								});
							} else {
								Ext.Msg.alert('提示', '请选择一条记录！');
							}
						}
	    			}, {
	    				iconCls:'icon-deleteBig', 
						text:'删除',
						handler: function(){
							var actionGrid = Ext.getCmp('formActionGrid').getSelectionModel().selected;
							if (actionGrid.length > 0) {
								
								var actionIds = [];
								//遍历获取选择的ID
								Ext.Array.forEach(actionGrid.items, function(item, index){
									actionIds.push(item.data.actionId);
								});
								
								Ext.Ajax.request({
                                url: 'custom/BAutoFormAction_delete.action',
                                params: { actionIds: Ext.JSON.encode(actionIds)},
                                success: function (response, opts) {
                                	var result = Ext.decode(response.responseText);
									Ext.Msg.alert('提示', result.data, function(){
										Ext.getCmp('formActionGrid').getStore().load();
									});
                                },
                                failure: function (response, opts) {
                                   Ext.Msg.alert('提示', result.data);
                                }
                            });
								
							}else {
								Ext.Msg.alert('提示', '请选择一条记录！');
							}
						}
	    			}]
	    		}),
	    		selModel: new Ext.selection.CheckboxModel({
                   // checkOnly: true
                }),
	    		store: {
	    			xtype: 'store',
	    			proxy: {
	    				type: 'ajax',
	    				url: 'custom/BAutoFormAction_list.action',
	    				extraParams: {metaId: config.metaId},
	    				render: {
							type: 'json',
							root: 'data'
						}
	    			},
	    			fields: [
	    				{name: 'actionId', type: 'string'},
	    				{name: 'actionName', type: 'string'},
	    				{name: 'actionCode', type: 'string'},
	    				{name: 'actionMethodName', type: 'string'},
	    				{name: 'actionMethodCode', type: 'string'},
	    				{name: 'autoFormId', type: 'string'},
	    				{name: 'createTime', type: 'string'}
	    			],
	    			autoLoad: true,
	    			listeners: {
	    				load: function(store, records, successful){
	    					if(!successful) {
	    						Ext.Msg.alert("提示", '数据加载失败');
	    					}
	    				}
	    			}
	    		},
	    		columns: [
	    			{text: '序号', xtype: 'rownumberer', width: 30, align: 'center'},
	    			{text: '动作名称', dataIndex: 'actionName', flex: 1, align: 'center'},
	    			{text: '动作代码', dataIndex: 'actionCode', flex: 1, align: 'center'},
	    			{text: '方法名称', dataIndex: 'actionMethodName', flex: 1, align: 'center'},
	    			{text: '方法代码', dataIndex: 'actionMethodCode', flex: 1, align: 'center'},
	    			{text: '创建时间', dataIndex: 'createTime', flex: 1, align: 'center'}
	    		]
	       	}],
		    listeners:{
		    	tabchange: function(tabPanel, newCard, oldCard){
		    		Ext.getCmp(newCard.getItemId()).getStore().load();
		    	}
		    }
       	}
	}).show();
	
}

/**
 * 表单动作设置新增/编辑页面
 * @param {} config
 */
MB.form.formActionForm = function(config){
	
	Ext.create("Ext.window.Window",{
		id: 'formActionWin',
		title: config.title,
		width: Math.ceil(Ext.getBody().getWidth() * 0.6),
		height: Math.ceil(Ext.getBody().getHeight()* 0.7),
		layout: 'fit',
		modal: true,
       	maximizable: true,
       	tbar: {
       		xtype: 'toolbar',
       		id: 'tbarFormAction',
       		items: [{
				iconCls:'icon-saveBus', 
				text:'保存',
				handler: function(){
					if(Ext.getCmp('formActionForm').getForm().isValid()){
						Ext.getCmp('formActionForm').getForm().submit({
							url: 'custom/BAutoFormAction_save.action',
							waitMsg: '正在保存，请稍后...',
							success: function(form, action) {
								var result = Ext.decode(action.response.responseText);
		                       	Ext.Msg.alert('成功', '操作成功！', function(){
		                       		Ext.getCmp('actionId').setValue(result.data);
		                       		Ext.getCmp('formActionGrid').getStore().load();
		                       });
		                    },
		                    failure: function(form, action) {
		                    	var result = Ext.decode(action.response.responseText);
		                        Ext.Msg.alert('失败', result.data);
		                    }
						});
					}
				}
       		},{
       			iconCls:'icon-returnBig', 
				text:'取消',
				handler: function(){
					Ext.getCmp('formActionWin').close();
				}
       		}]
				
       	},
       	items: {
       		xtype: 'form',
       		id: 'formActionForm',
			border: 1,
			layout: {
				type: 'vbox',
				align: 'stretch'
			},
			autoScroll: true,
			bodyPadding: '10 30 10 30',
			fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', labelWidth: 80 },
			items:[
				{ xtype: 'hiddenfield', id: 'actionId', name: 'autoFormAction.actionId', value: config.data.actionId, hidden: true  },
				{ xtype: 'hiddenfield', id: 'autoFormId', name: 'autoFormAction.autoFormId', value: config.metaId, hidden: true },
				{
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                    { xtype: 'textfield', id: 'actionName', name: 'autoFormAction.actionName', fieldLabel: '动作名称', allowBlank: false, blankText: '动作名称必填', value: config.data.actionName},
	                    { xtype: 'splitter', width: 40 },
	                    { xtype: 'textfield', id: 'actionCode', name: 'autoFormAction.actionCode', fieldLabel: '动作编码', allowBlank: false, blankText: '动作编码必填', value: config.data.actionCode }
	                ]
	            },{
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                    { xtype: 'textfield', id: 'actionMethodName', name: 'autoFormAction.actionMethodName', fieldLabel: '动作方法名称', allowBlank: false, blankText: '动作方法名称必填', value: config.data.actionMethodName},
	                    { xtype: 'splitter', width: 40 },
	                    { xtype: 'textfield', id: 'actionMethodCode', name: 'autoFormAction.actionMethodCode', fieldLabel: '动作方法编码', allowBlank: false, blankText: '动作方法编码必填', value: config.data.actionMethodCode }
	                ]
	            },
	            {
	            	xtype: 'grid',
	            	id: 'mtdParamsGrid',
			    	title: '动作方法参数',
			    	flex:1,
			    	autoScroll: true,
			    	columnLines: true,
		    		border: 1,
		    		rowexpander: 30,
		    		tbar: {
		    			xtype: 'toolbar',
		    			items: [{
		    				iconCls:'icon-addBig', 
							text:'新增',
							handler: function(){
								var actionId = Ext.getCmp('actionId').getValue();
								if(!actionId){
									Ext.Msg.alert('提示', '请先保存主表数据！');
									return;
								}
								var sortNo = Ext.getCmp('mtdParamsGrid').getStore().getCount();
								new MB.form.methodParamsForm({
									title: '新增',
									actionId: actionId,
									metaId: config.metaId,
									sortNo: sortNo,
									data: ''
									
								});
							}
		    			},{
		    				iconCls:'icon-editBig', 
							text:'编辑',
							handler: function(){
								
								var paramasGrid = Ext.getCmp('mtdParamsGrid').getSelectionModel().selected;
								if (paramasGrid.length == 1) {
									new MB.form.methodParamsForm({
										title: '编辑',
										actionId: config.data.actionId,
										metaId: config.metaId,
										sortNo: '',
										data: paramasGrid.items[0].data
									});
								} else {
									Ext.Msg.alert('提示', '请选择一条记录！');
								}
								
							}
		    			},{
		    				iconCls:'icon-deleteBig', 
							text:'删除',
							handler: function(){
								var paramasGrid = Ext.getCmp('mtdParamsGrid').getSelectionModel().selected;
								if (paramasGrid.length > 0) {
									
									var paramsIds = [];
									//遍历获取选择的ID
									Ext.Array.forEach(paramasGrid.items, function(item, index){
										paramsIds.push(item.data.methodParamsId);
									});
									
									Ext.Ajax.request({
		                                url: 'custom/BAutoFormMtdParams_delete.action',
		                                params: { paramsIds: Ext.JSON.encode(paramsIds)},
		                                success: function (response, opts) {
		                                	var result = Ext.decode(response.responseText);
											Ext.Msg.alert('提示', result.data, function(){
												Ext.getCmp('mtdParamsGrid').getStore().load();
											});
		                                },
		                                failure: function (response, opts) {
		                                   Ext.Msg.alert('提示', result.data);
		                                }
		                            });
									
								}else {
									Ext.Msg.alert('提示', '请选择一条记录！');
								}
							}
		    			},{
		    				iconCls:'icon-saveOrder', 
							text:'保存调整排序',
							handler: function(){
								var idAndSortNos = [];
								var mtdParamsStore = Ext.getCmp('mtdParamsGrid').getStore().getRange();
								
								for(var i = 0; i < mtdParamsStore.length; i++) {
									if(mtdParamsStore[i].data.methodParamsSortNo != i){
										idAndSortNos.push({methodParamsId: mtdParamsStore[i].data.methodParamsId, methodParamsSortNo: i});
									}
								}
								if(0 < idAndSortNos.length){
									Ext.Ajax.request({
		                                url: 'custom/BAutoFormMtdParams_updateSortNo.action',
		                                params: { idAndSortNos: Ext.JSON.encode(idAndSortNos)},
		                                success: function (response, opts) {
		                                	var result = Ext.decode(response.responseText);
											Ext.Msg.alert('提示', result.data);
		                                },
		                                failure: function (response, opts) {
		                                   Ext.Msg.alert('提示', result.data);
		                                }
		                            });
								}
							}
		    			}]
		    		},
		    		selModel: new Ext.selection.CheckboxModel({
	                    //checkOnly: true
	                }),
		    		store: {
		    			xtype: 'store',
		    			proxy: {
		    				type: 'ajax',
		    				url: 'custom/BAutoFormMtdParams_list.action',
		    				extraParams: {actionId: config.data.actionId},
		    				render: {
								type: 'json',
								root: 'data'
							}
		    			},
		    			fields: [
		    				{name: 'methodParamsId', type: 'string'},
		    				{name: 'methodParamsName', type: 'string'},
		    				{name: 'methodParamsCode', type: 'string'},
		    				{name: 'methodParamsType', type: 'string'},
		    				{name: 'methodParamsDefault', type: 'string'},
		    				{name: 'methodParamsSortNo', type: 'string'}
		    			],
		    			autoLoad: true,
		    			listeners: {
		    				load: function(store, records, successful){
		    					if(!successful) {
		    						Ext.Msg.alert("提示", '动作方法参数列表数据加载失败');
		    					}
		    				}
		    			}
		    		},
		    		columns: [
		    			{text: '序号', xtype: 'rownumberer', width: 30, align: 'center'},
		    			{text: '参数名称', dataIndex: 'methodParamsName', flex: 1, align: 'center'},
		    			{text: '参数代码', dataIndex: 'methodParamsCode', flex: 1, align: 'center'},
		    			{text: '参数类型', dataIndex: 'methodParamsType', flex: 1, align: 'center'},
		    			{text: '参数缺省值', dataIndex: 'methodParamsDefault', flex: 1, align: 'center'}
		    		],
		    		viewConfig: {
		    			trackOver: false,
			            plugins: {
			                ptype: 'gridviewdragdrop',
			                dragText: '1 selected row'
			            }
		    		}
	            }
			]
       	},
       	renderTo: Ext.getBody()	 
	}).show();
	
}

/**
 * 表单动作设置方法参数表单
 */
MB.form.methodParamsForm = function(config) {
	
	Ext.create("Ext.window.Window",{
		id: 'formActionParamsWin',
		title: config.title,
		width: Math.ceil(Ext.getBody().getWidth() * 0.55),
		height: 180,
		layout: 'fit',
		modal: true,
       	maximizable: true,
       	tbar: {
       		xtype: 'toolbar',
       		id: 'tbarFormActionParams',
       		items: [{
				iconCls:'icon-saveBus', 
				text:'保存',
				handler: function(){
					if(Ext.getCmp('formActionParamsForm').getForm().isValid()){
						Ext.getCmp('formActionParamsForm').getForm().submit({
							url: 'custom/BAutoFormMtdParams_save.action',
							waitMsg: '正在保存，请稍后...',
							success: function(form, action) {
								var result = Ext.decode(action.response.responseText);
		                       	Ext.Msg.alert('成功', result.data, function(){
		                       		Ext.getCmp('mtdParamsGrid').getStore().load();
									Ext.getCmp('formActionParamsWin').close();
		                       });
		                    },
		                    failure: function(form, action) {
		                    	var result = Ext.decode(action.response.responseText);
		                        Ext.Msg.alert('失败', result.data);
		                    }
						});
					}
				}
       		},{
       			iconCls:'icon-returnBig', 
				text:'取消',
				handler: function(){
					Ext.getCmp('formActionParamsWin').close();
				}
       		}]
				
       	},
       	items: {
       		xtype: 'form',
       		id: 'formActionParamsForm',
			border: 1,
			layout: {
				type: 'vbox',
				align: 'stretch'
			},
			autoScroll: true,
			bodyPadding: '10 30 10 30',
			fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', labelWidth: 80 },
			items:[
				{ xtype: 'hiddenfield', id: 'methodParamsId', name: 'autoFormMtdParams.methodParamsId', value: config.data.methodParamsId, hidden: true },
				{ xtype: 'hiddenfield', id: 'mtdParamsActionId', name: 'autoFormMtdParams.actionId', value: config.actionId, hidden: true },
				{ xtype: 'hiddenfield', id: 'methodParamsSortNo', name: 'autoFormMtdParams.methodParamsSortNo', value: config.sortNo, hidden: true },
				{
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                	{ 
	                		xtype: 'combo',
							id: 'methodParamsCode',
							name: 'autoFormMtdParams.methodParamsCode',
							fieldLabel: '参数编码',
							allowBlank: false,
							blankText: '参数编码必填！',
							queryMode: 'local',
							displayField: 'META_NUM',
							valueField: 'META_NUM',
							//editable: true,
							//forceSelection: true,
							emptyText: '请选择',
							value: config.data.methodParamsCode,
							store: {
		                    	xtype: 'store',
		                        fields: ['META_NUM', 'META_NAME'],
		                        proxy: {
		                        	type: 'ajax',
		                        	url: 'custom/BAutoFormAction_queryAutoFormList.action',
		                        	extraParams: {metaId: config.metaId},
		                        	render: {
		                        		type: 'json',
		                        		root: 'data'
		                        	}
		                        },
								autoLoad: true,
								listeners: {
									load: function(store, records, successful) {
										if(!successful) {
											Ext.Msg.alert('提示', '参数编码列表数据加载失败！');
										}else {
			                                var field = Ext.getCmp('methodParamsCode');
			                                if (field) {
			                                    field.select(config.data.methodParamsCode);
			                                    
			                                }
			                            }
									}
								}
							},
							listeners: {
								select: function(combo, records, opts) {
									Ext.getCmp('methodParamsName').setValue(records[0].data.META_NAME);
								}
							}
	                	},
	                    { xtype: 'splitter', width: 40 },
	                    { xtype: 'textfield', id: 'methodParamsName', name: 'autoFormMtdParams.methodParamsName', fieldLabel: '参数名称', allowBlank: false, blankText: '参数名称必填', value: config.data.methodParamsName}
	                ]
	            },{
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                	{
	                		xtype: 'combo',
							id: 'methodParamsType',
							name: 'autoFormMtdParams.methodParamsType',
							fieldLabel: '参数类型',
							allowBlank: false,
							blankText: '参数类型必填！',
							queryMode: 'local',
							displayField: 'TYPE_NAME',
							valueField: 'TYPE_CODE',
							editable: false,
							forceSelection: true,
							emptyText: '请选择',
							value: config.data.methodParamsType,
							store: {
		                    	xtype: 'store',
		                        fields: ['TYPE_CODE', 'TYPE_NAME'],
		                        proxy: {
		                        	type: 'ajax',
		                        	url: 'custom/BAutoFormMtdParams_queryParamsTypeList.action',
		                        	render: {
		                        		type: 'json',
		                        		root: 'data'
		                        	}
		                        },
								autoLoad: true,
								listeners: {
									load: function(store, records, successful) {
										if(!successful) {
											Ext.Msg.alert('提示', '参数类型加载失败！');
										}else {
			                                var field = Ext.getCmp('methodParamsType');
			                                if (field) {
			                                    field.select(config.data.methodParamsType);
			                                    
			                                }
			                            }
									}
								}
							}
	                	},
	                    { xtype: 'splitter', width: 40 },
	                    { xtype: 'textfield', id: 'methodParamsDefault', name: 'autoFormMtdParams.methodParamsDefault', fieldLabel: '参数缺省值', value: config.data.methodParamsDefault}
	                ]
	            }
			]
       	},
       	renderTo: Ext.getBody()	
	}).show();
}


/**
 * 获取工具条
 * @param {} tbarId 工具条ID
 * @return {}
 */
function getToolBar(tbarId, gridId, modelName, metaId){
	var gridTbar = Ext.create('Ext.toolbar.Toolbar', {
		id: tbarId
	});
	
	gridTbar.add([{
		iconCls:'icon-addBig', 
		tooltip:'新增',
		handler: function(){
			if(modelName =='B_REPORT_FILTER_CRITERIA') {	//筛选条件设置
				var criteria = Ext.getCmp(gridId).getSelectionModel().getSelection();
            	if (criteria.length > 0) {
            		var filterCriteriaType = criteria[0].data.FILTER_CRITERIA_TYPE;
            		var filterCriteriaPcode = criteria[0].data.FILTER_CRITERIA_CODE;
            		if(filterCriteriaPcode.length >= 32) {	//子节点超过八级不再创建子节点
            			Ext.MessageBox.alert("提示","子节点已超过八级不能再创建子节点");
						return;
					}
					//检查选择的父节点是否是且/或
					 Ext.Ajax.request({
                          url: 'dreport/BReportFilterCriteria_checkCriteriaData.action',
                          params: {
                              templateId: metaId,
                              filterCriteriaType: filterCriteriaType,
                              filterCriteriaPcode : filterCriteriaPcode
                          },
                          scope: this,
                          success: function (response, opts) {
                          	var result = Ext.JSON.decode(response.responseText);
                          	if(result.opt == "error") {
                          		Ext.MessageBox.alert("提示","请选择筛选条件关系（且/或）！");
                          	}else {
		                    	new MB.form.searchParamsForm({
									metaId: metaId,
									modelName: modelName,
									filterCriteriaType: filterCriteriaType,
									filterCriteriaPcode : filterCriteriaPcode,
									data: '',
									gridId: gridId
								});
		                    }
                          },
                          failure: function (response) {
                              Ext.Msg.alert(result.message);
                          }
                    });
            		
            	} else {
            		Ext.MessageBox.alert("提示","请选择筛选条件关系（且/或）！");
            		return;
            	}
			} else {
				new MB.form.searchParamsForm({
					metaId: metaId,
					modelName: modelName,
					data: '',
					gridId: gridId
				});
			}
		}
	},{
		iconCls:'icon-editBig', 
		tooltip:'编辑',
		handler: function(){
			if (Ext.getCmp(gridId).getSelectionModel().selected.length > 0) {
				new MB.form.searchParamsForm({
					metaId: metaId,
					modelName: modelName,
					data: Ext.getCmp(gridId).getSelectionModel().selected.items[0].data,
					gridId: gridId
				});
			
			} else {
				Ext.Msg.alert('提示', '请先选择一条数据！');
			}
		}
	},{
		iconCls:'icon-deleteBig', 
		tooltip:'删除',
		handler: function(){
			var criteria = Ext.getCmp(gridId).getSelectionModel().getSelection();
			if (criteria.length > 0) {
				Ext.Msg.show({
				     title:'删除',
				     msg: '确定要删除吗？',
				     buttons: Ext.Msg.OKCANCEL,
				     icon: Ext.Msg.QUESTION,
				     fn: function(buttonId, text, opt){
				     	if(buttonId == 'ok') {
				     		//删除提交地址
							var deleteUrl;
							//提交参数modelSource
							var submitParams;
							if(modelName =='B_REPORT_VARIABLE') {	//变量设置
								deleteUrl = "dreport/BReportVariable_doDelete.action";
								submitParams = {modelSource: 'AUTO_FORM', variableIds: criteria[0].data.VARIABLE_ID};
									
							}else if(modelName =='B_REPORT_FILTER_CRITERIA') {	//筛选条件设置
				            	//当前选中节点的根节点，如果为根节点，不能删除
		            			var fCriteriaPcode = criteria[0].data.FILTER_CRITERIA_PCODE;
		            			if(fCriteriaPcode == -1) {
		            				Ext.Msg.alert("提示", "不能删除此记录！");
		            				return;
		            			}
		            			//当前选中节点值
								var filterCriteriaPcode = criteria[0].data.FILTER_CRITERIA_CODE;
								//筛选条件类型
								var filterCriteriaType = criteria[0].data.FILTER_CRITERIA_TYPE;
								
								deleteUrl = "dreport/BReportFilterCriteria_doDelete.action";
									
								submitParams = {modelSource: 'AUTO_FORM', templateId: metaId, filterCriteriaType: filterCriteriaType, 
									filterCriteriaPcode: filterCriteriaPcode};
		            			
							}else if(modelName =='B_REPORT_GROUP_FIELD') {	//分组设置
								deleteUrl = "dreport/BReportGroupField_doDelete.action";
								submitParams = {modelSource: 'AUTO_FORM', groupFieldIds: criteria[0].data.GROUP_FIELD_ID};
									
							}else if(modelName =='B_REPORT_OUTPUT_FIELD') {	//输出字段设置
								deleteUrl = "dreport/BReportOutputField_doDelete.action";
								submitParams = {modelSource: 'AUTO_FORM', outputFieldIds: criteria[0].data.OUTPUT_FIELD_ID};
									
							}else if(modelName =='B_REPORT_SORT_FIELD') {	//排序设置
								deleteUrl = "dreport/BReportSortField_doDelete.action";
								submitParams = {modelSource: 'AUTO_FORM', sortFieldIds: criteria[0].data.SORT_FIELD_ID};
							}
							
							if(null != deleteUrl && '' != deleteUrl) {
								Ext.Ajax.request({
									url: deleteUrl,
									params: submitParams,
									success: function(response) {
										var result = Ext.decode(response.responseText);
										Ext.Msg.alert('提示', result.data, function(){
											Ext.getCmp(gridId).getStore().load();
										});
									},
									failure: function(response) {
										Ext.Msg.alert('提示', result.data);
									}
								});
							}
				     	}
				     }
				});
			} else {
				Ext.Msg.alert('提示', '请选择一条数据！',null );
			}
		}
	}]);
	
	//筛选条件设置
	if(modelName =='B_REPORT_FILTER_CRITERIA'){
		gridTbar.add([
			{
	        	text: "且",
	        	handler: function(){
	        		onSaveForAndOR('AND', gridId);
	        	}
	        },
	        "-",
	         {
	        	text: "或",
	        	handler: function(){
	        		onSaveForAndOR('OR', gridId);
	        	}
	        }
		]);
		
	}
	
	return gridTbar;
}

/**
 * 查询参数表单
 * @param {} config
 */
MB.form.searchParamsForm = function(config){
	//表单对象
	var sparamsForm =Ext.create('Ext.form.Panel', {
		id: 'searchParamsForm',
		border: 1,
		layout: {
			type: 'vbox',
			align: 'stretch'
		},
		autoScroll: true,
		bodyPadding: '10 30 10 30',
		fieldDefaults: { labelAlign: 'left', msgTarget: 'none', anchor: '100%', labelWidth: 80 },
		items:[{ xtype: 'hiddenfield', id: 'templateId', name: 'templateId', value: config.metaId, hidden: true }]
	});
	//保存提交地址
	var saveUrl;
	//窗体标题
	var title = '新增';
	if(config.data){
		title = '编辑';
	}
	//模块名称
	var modelName = config.modelName;
	if(modelName =='B_REPORT_VARIABLE') {	//变量设置
		saveUrl = "dreport/BReportVariable_doAdd.action?modelSource=AUTO_FORM";
		if(config.data.VARIABLE_ID){
			saveUrl = "dreport/BReportVariable_doEdit.action?modelSource=AUTO_FORM";
		}
		
		title += '变量';
		sparamsForm.add([
			{ xtype: 'hiddenfield', name: 'breportVariable.variableId', value: config.data.VARIABLE_ID, hidden: true },
			{
                xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: { flex: 1, labelWidth: 80 },
                items: [
                    { xtype: 'textfield', name: 'breportVariable.variableName', fieldLabel: '变量名称', allowBlank: false, blankText: '变量名称必填', value: config.data.VARIABLE_NAME},
                    { xtype: 'splitter', width: 40 },
                    { xtype: 'textfield', name: 'breportVariable.variableCode', fieldLabel: '变量编码', allowBlank: false, blankText: '变量编码必填', value: config.data.VARIABLE_CODE }
                ]
            },
            {
                xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: { flex: 1, labelWidth: 80 },
                items: [
                    { xtype: 'textfield', name: 'breportVariable.variableExpress', fieldLabel: '变量表达式',  value: config.data.VARIABLE_EXPRESS},
                    { xtype: 'splitter', width: 40 },
                    { 	xtype: 'combo',
	                    id: 'dataItemTypeId',
	                    name: 'breportVariable.dataItemTypeId',
	                    fieldLabel: '变量类型',
	                    queryMode: 'local',
	                    displayField: 'META_TYPE_NAME',
	                    valueField: 'META_TYPE_ID',
	                    editable: false,
	                    allowBlank: false,
	                    blankText: '类型必填',
	                    forceSelection: true,
	                    emptyText: '请选择',
	                    value: config.data.DATA_ITEM_TYPE_ID,
	                    store: Ext.create('Ext.data.Store', {
	                        fields: ['META_TYPE_ID', 'META_TYPE_NAME', 'META_TYPE_NUM'],
	                        proxy: {
	                            type: 'ajax',
	                            url: 'custom/BAutoForm_getMetaTypeList.action',
	                            //extraParams: {optType: config.optType},
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
	                                    var field = Ext.getCmp('dataItemTypeId');
	                                    if (field) {
	                                        field.select(config.data.DATA_ITEM_TYPE_ID);
	                                        
	                                    }
	                                }
	                            }
	                        }
	                    }) 
	               	}
                ]
            },
            {
                xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: { flex: 1, labelWidth: 80 },
                items: [
                    { xtype: 'textfield', name: 'breportVariable.variableDefaultValue', fieldLabel: '缺省值', value: config.data.VARIABLE_DEFAULT_VALUE},
                    { xtype: 'splitter', width: 40 },
                    { 	xtype: 'combo',
						name: 'breportVariable.variableIsDisplay',
						fieldLabel: '是否显示',
						queryMode: 'local',
						displayField: 'name',
						valueField: 'id',
						store: {
							fields:['id', 'name'],
							data: [{id: '1', name: '是'}, {id: '2', name: '否'}]
						},
						value: config.data.VARIABLE_IS_DISPLAY,
						allowBlank: false,
						blankText: '是否显示不能为空'
                    }
                ]
            },
            { xtype: 'textareafield', name: 'breportVariable.variableParams', fieldLabel: '变量参数', value: config.data.VARIABLE_PARAMS },
            { xtype: 'textareafield', name: 'breportVariable.remark', fieldLabel: '备注', value: config.data.REMARK }
		]);
	}else if(modelName == 'B_REPORT_FILTER_CRITERIA'){	//筛选条件设置
		saveUrl = "dreport/BReportFilterCriteria_doAdd.action?modelSource=AUTO_FORM&filterCriteriaPcode=" + 
			config.filterCriteriaPcode + "&filterCriteriaType=" + config.filterCriteriaType;
		if(config.data.FILTER_CRITERIA_ID){
			saveUrl = "dreport/BReportFilterCriteria_doEdit.action?modelSource=AUTO_FORM";
		}
		
		title += '筛选条件';
		
		sparamsForm.add({ xtype: 'hiddenfield', name: 'breportFilterCriteria.filterCriteriaId', value: config.data.FILTER_CRITERIA_ID, hidden: true });
         
         if(config.data.FILTER_CRITERIA_OPERATOR == 'AND ' || config.data.FILTER_CRITERIA_OPERATOR == 'OR  '){
         	sparamsForm.add([
         		{ 	xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: { flex: 1, labelWidth: 80 },
	                items: [
	                    { xtype: 'textfield', name: 'breportFilterCriteria.filterCriteriaName', fieldLabel: '名称', allowBlank: false, blankText: '名称必填', value: config.data.FILTER_CRITERIA_NAME},
	                    { xtype: 'splitter', width: 40 },
	                    { xtype: 'combo', 
	                    	name: 'breportFilterCriteria.filterCriteriaOperator', 
	                    	fieldLabel: '运算符', 
	                    	allowBlank: false, blankText: '运算符必填',
	                    	queryMode: 'local',
							displayField: 'text',
							valueField: 'id',
							store: {
								fields:['id', 'text'],
								data: [{ id: 'AND ', text: '且' }, { id: 'OR  ', text: '或'}]
							},
	                    	value: config.data.FILTER_CRITERIA_OPERATOR 
	                    }
	                ]
                }
             ]);
         }else {
         	sparamsForm.add([
         		{ xtype: 'textfield', name: 'breportFilterCriteria.filterCriteriaName', fieldLabel: '名称', value: config.data.FILTER_CRITERIA_NAME, allowBlank: false, blankText: '名称必填'},
				{
	                xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: {labelWidth: 80 },
	                items: [
	                    { 	xtype: 'combo',
	                    	id: 'dataItemId',
	                    	name: 'breportFilterCriteria.dataItemId', 
	                    	fieldLabel: '表达式', 
	                    	allowBlank: false, blankText: '左表达式必填',
	                    	queryMode: 'local',
		                    displayField: 'META_NAME',
		                    valueField: 'META_ID',
		                    editable: false,
		                    forceSelection: true,
		                    emptyText: '请选择',
		                    value: config.data.DATA_ITEM_ID,
		                    store: Ext.create('Ext.data.Store', {
		                        fields: ['META_ID', 'META_NAME', 'META_NUM'],
		                        proxy: {
		                            type: 'ajax',
		                            url: 'custom/BAutoForm_queryAutoFormMetaList.action',
		                            extraParams: {templateId: config.metaId},
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
		                                    var field = Ext.getCmp('dataItemId');
		                                    if (field) {
		                                        field.select(config.data.DATA_ITEM_ID);
		                                        
		                                    }
		                                }
		                            }
		                        }
		                    }),
	                    	value: config.data.DATA_ITEM_ID,
	                    	flex: 1.5
	                    },
	                    { xtype: 'splitter', width: 10 },
	                    { xtype: 'combo', 
	                    	name: 'breportFilterCriteria.filterCriteriaOperator', 
	                    	allowBlank: false, blankText: '操作符必填', 
	                    	queryMode: 'local',
							displayField: 'text',
							valueField: 'id',
							store: {
								fields:['id', 'text'],
								data: [{ id: '>   ', text: '大于' }, { id: '>=  ', text: '大于等于' },{ id: '<   ', text: '小于' }, 
		                  			   { id: '<=  ', text: '小于等于' }, { id: '=   ', text: '等于' }, { id: '<>  ', text: '不等于' }, 
		                  			   { id: 'IN  ', text: '在...中' }, { id: 'LIKE', text: '相似等' }]
							},
	                    	value: config.data.FILTER_CRITERIA_OPERATOR?config.data.FILTER_CRITERIA_OPERATOR:'>   ',
	                    	flex: 0.5
	                    },
	                    { xtype: 'splitter', width: 10 },
	                    { 	xtype: 'combo', 
	                    	id: 'filterCriteriaRexpress',
	                    	name: 'breportFilterCriteria.filterCriteriaRexpress', 
	                    	allowBlank: false, blankText: '右表达式必填', 
	                    	queryMode: 'local',
		                    displayField: 'VARIABLE_NAME',
		                    valueField: 'VARIABLE_ID',
		                    editable: false,
		                    forceSelection: true,
		                    emptyText: '请选择',
		                    value: config.data.DATA_ITEM_ID,
		                    store: Ext.create('Ext.data.Store', {
		                        fields: ['VARIABLE_ID', 'VARIABLE_NAME'],
		                        proxy: {
		                            type: 'ajax',
		                            url: 'custom/BAutoForm_queryVariableList.action',
		                            extraParams: {templateId: config.metaId},
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
		                                    var field = Ext.getCmp('filterCriteriaRexpress');
		                                    if (field) {
		                                        field.select(config.data.FILTER_CRITERIA_REXPRESS);
		                                        
		                                    }
		                                }
		                            }
		                        }
		                    }),
		                    value: config.data.FILTER_CRITERIA_REXPRESS,
	                    	flex: 1.2
	                    }
	                ]
	            }
         	]);
         }
		
         sparamsForm.add({ xtype: 'textareafield', name: 'breportFilterCriteria.remark', fieldLabel: '备注', value: config.data.REMARK });
         
	}else if(modelName == 'B_REPORT_GROUP_FIELD'){	//分组设置
		saveUrl = "dreport/BReportGroupField_doAdd.action?modelSource=AUTO_FORM";
		if(config.data.GROUP_FIELD_ID){
			saveUrl = "dreport/BReportGroupField_doEdit.action?modelSource=AUTO_FORM";
		}
		
		title += '分组设置';
		
		sparamsForm.add(
			{xtype: 'hiddenfield', name: 'breportGroupField.groupFieldId', value: config.data.GROUP_FIELD_ID, hidden: true},
			{xtype: 'fieldcontainer',
	                layout: 'hbox',
	                fieldDefaults: {labelWidth: 80, flex: 1 },
	                items: [
	                	{ xtype: 'combo',
	                    	id: 'breportGroupField.dataItemId',
	                    	name: 'breportGroupField.dataItemId', 
	                    	fieldLabel: '分组字段', 
	                    	allowBlank: false, blankText: '分组字段必填',
	                    	queryMode: 'local',
		                    displayField: 'metaName',
		                    valueField: 'metaId',
		                    editable: false,
		                    forceSelection: true,
		                    emptyText: '请选择',
		                    value: config.data.DATA_ITEM_ID,
		                    store: Ext.create('Ext.data.Store', {
		                        fields: ['metaId', 'metaName', 'metaNum'],
		                        proxy: {
		                            type: 'ajax',
		                            url: 'custom/BAutoForm_queryAutoFormMetaList.action',
		                            extraParams: {templateId: config.metaId},
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
		                                    var field = Ext.getCmp('breportGroupField.dataItemId');
		                                    if (field) {
		                                        field.select(config.data.DATA_ITEM_ID);
		                                        
		                                    }
		                                }
		                            }
		                        }
		                    }),
	                    	value: config.data.DATA_ITEM_ID
	                    },
	                    { xtype: 'splitter', width: 40 },
	                    {xtype: 'textfield', name: 'breportGroupField.groupFieldName', fieldLabel: '分组名称', allowBlank: false, blankText: '分组名称必填', value: config.data.GROUP_FIELD_NAME}
	                ]
			},
			{xtype: 'textareafield', name: 'breportGroupField.remark', fieldLabel: '备注', value: config.data.REMARK }
		);
		
	}else if(modelName == 'B_REPORT_OUTPUT_FIELD'){	//输出字段设置
		saveUrl = "dreport/BReportOutputField_doAdd.action?modelSource=AUTO_FORM";
		if(config.data.OUTPUT_FIELD_ID){
			saveUrl = "dreport/BReportOutputField_doEdit.action?modelSource=AUTO_FORM";
		}
		
		title += '输出字段';
		
		sparamsForm.add([
			{xtype: 'hiddenfield', name: 'breportOutputField.outputFieldId', value: config.data.OUTPUT_FIELD_ID},
			{
				xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: {labelWidth: 80, flex: 1 },
                items: [
                	{ 	xtype: 'combo',
                    	id: 'breportOutputField.dataItemId',
                    	name: 'breportOutputField.dataItemId', 
                    	fieldLabel: '输出字段', 
                    	allowBlank: false, blankText: '输出字段必填',
                    	queryMode: 'local',
	                    displayField: 'META_NAME',
		                valueField: 'META_ID',
	                    editable: false,
	                    forceSelection: true,
	                    emptyText: '请选择',
	                    value: config.data.DATA_ITEM_ID,
	                    store: {
	                    	xtype: 'store',
	                        fields: ['META_ID', 'META_NAME', 'META_NUM'],
	                        proxy: {
	                            type: 'ajax',
	                            url: 'custom/BAutoForm_queryAutoFormMetaList.action',
	                            extraParams: {templateId: config.metaId},
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
	                                    var field = Ext.getCmp('breportOutputField.dataItemId');
	                                    if (field) {
	                                        field.select(config.data.DATA_ITEM_ID);
	                                        
	                                    }
	                                }
	                            }
	                        }
	                    },
						listeners: {
							select: function(combo, records, opts) {
								Ext.getCmp('breportOutputField.outputFieldName').setValue(records[0].data.META_NAME);
							}
						}
                    },
                    { xtype: 'splitter', width: 10 },
                    {xtype: 'textfield', id: 'breportOutputField.outputFieldName', name: 'breportOutputField.outputFieldName', 
                    	fieldLabel: '输出字段名称', allowBlank: false, blankText: '输出字段名称必填', value: config.data.OUTPUT_FIELD_NAME}
                ]
			},
			{xtype: 'numberfield', name: 'breportOutputField.outputFieldSortNo', fieldLabel: '排序号',minValue: 0, value: config.data.OUTPUT_FIELD_SORT_NO},
			{xtype: 'textarea', name: 'breportOutputField.outputFieldParams', fieldLabel: '输出字段参数', value: config.data.OUTPUT_FIELD_PARAMS},
			{xtype: 'textarea', name: 'breportOutputField.remark', fieldLabel: '备注', value: config.data.REMARK}
			
		]);
		
	}else if(modelName == 'B_REPORT_SORT_FIELD'){	//排序设置
		saveUrl = "dreport/BReportSortField_doAdd.action?modelSource=AUTO_FORM";
		if(config.data.SORT_FIELD_ID){
			saveUrl = "dreport/BReportSortField_doEdit.action?modelSource=AUTO_FORM";
		}
		
		title += '排序设置';
		
		sparamsForm.add([
			{xtype: 'hiddenfield', name: 'breportSortField.sortFieldId', value: config.data.SORT_FIELD_ID},
			{
				xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: {labelWidth: 80, flex: 1 },
                items: [
                	{
                		xtype: 'combo',
						id: 'breportSortField.dataItemId',
						name: 'breportSortField.dataItemId',
						fieldLabel: '排序字段',
						allowBlank: false,
						blankText: '排序字段必填！',
						queryMode: 'local',
						displayField: 'metaName',
						valueField: 'metaId',
						editable: false,
						forceSelection: true,
						emptyText: '请选择',
						value: config.data.DATA_ITEM_ID,
						store: {
	                    	xtype: 'store',
	                        fields: ['metaId', 'metaName', 'metaNum'],
	                        proxy: {
	                            type: 'ajax',
	                            url: 'custom/BAutoForm_queryAutoFormMetaList.action',
	                            extraParams: {templateId: config.metaId},
	                            reader: {
	                                type: 'json',
	                                root: 'data'
	                            }
	                        },
							autoLoad: true,
							listeners: {
								load: function(store, records, successful) {
									if(!successful) {
										Ext.Msg.alert('提示', '数据加载失败！');
									}else {
		                                var field = Ext.getCmp('breportSortField.dataItemId');
		                                if (field) {
		                                    field.select(config.data.DATA_ITEM_ID);
		                                    
		                                }
		                            }
								}
							}
						},
						listeners: {
							select: function(combo, records, opts) {
								Ext.getCmp('breportSortField.sortFieldName').setValue(records[0].data.metaName);
							}
						}
						
                	},
                	{ xtype: 'splitter', width: 40 },
                	{xtype: 'textfield', id: 'breportSortField.sortFieldName', name: 'breportSortField.sortFieldName', fieldLabel: '排序字段名称', 
                		allowBlank: false, blankText: '排序字段名称不能为空！', value: config.data.SORT_FIELD_NAME}
                ]
			},
			{
				xtype: 'fieldcontainer',
                layout: 'hbox',
                fieldDefaults: {labelWidth: 80, flex: 1 },
                items: [
                	{
                		xtype: 'combo',
						id: 'breportSortField.sortFieldWay',
						name: 'breportSortField.sortFieldWay',
						fieldLabel: '排序方式',
						allowBlank: false,
						blankText: '排序方式必填！',
						queryMode: 'local',
						displayField: 'text',
						valueField: 'id',
						editable: false,
						forceSelection: true,
						emptyText: '请选择',
						value: config.data.SORT_FIELD_WAY,
						store: {
							xtype: 'store',
							fields: ['id', 'text'],
							data: [{ id: 'ASC ', text: '升序' }, { id: 'DESC', text: '降序' }]
						}
                	},
                	{ xtype: 'splitter', width: 40 },
                	{xtype: 'numberfield', name: 'breportSortField.sortFieldSortNo', fieldLabel: '排序号', allowBlank: false, blankText: '排序号不能为空！', value: config.data.SORT_FIELD_SORT_NO}
                ]
			},
			{xtype: 'textarea', name: 'breportSortField.remark', fieldLabel: '备注', value: config.data.REMARK, maxLength: 1000, maxLengthText: '最大长度不能超过1000个字符！'}
		]);
	}
	
	Ext.create("Ext.window.Window",{
		id: 'autoParamsWin',
		title: title,
		width: Math.ceil(Ext.getBody().getWidth() * 0.6),
		height: Math.ceil(Ext.getBody().getHeight()* 0.7),
		layout: 'fit',
		modal: true,
       	maximizable: true,
       	renderTo: Ext.getBody(),
       	items: sparamsForm,
       	 buttons: [{
			text: '保存',
			handler: function(){
				if(Ext.getCmp('searchParamsForm').getForm().isValid()){
					Ext.getCmp('searchParamsForm').getForm().submit({
						url: saveUrl,
						params: {modelName: modelName},
						waitMsg: '正在保存，请稍后...',
						success: function(form, action) {
							var result = Ext.decode(action.response.responseText);
	                       	Ext.Msg.alert('成功', result.data, function(){
	                       		//清空选择
					 			Ext.getCmp(config.gridId).getSelectionModel().clearSelections(true);
	                       		Ext.getCmp(config.gridId).getStore().load();
								Ext.getCmp('autoParamsWin').close();
	                       });
	                    },
	                    failure: function(form, action) {
	                    	var result = Ext.decode(action.response.responseText);
	                        Ext.Msg.alert('失败', result.data);
	                    }
					});
				}
			}
		}, {
			text: '取消',
			handler: function(){
				//清空选择
				Ext.getCmp(config.gridId).getSelectionModel().clearSelections(true);
				Ext.getCmp('autoParamsWin').close();
			}
		}]
	}).show();
	
}

/**
 * 工具栏按钮(AND/OR)操作
 * @param {} criteriaOperator 操作符标识
 */
function onSaveForAndOR(criteriaOperator, gridId){
	var criteria = Ext.getCmp(gridId).getSelectionModel().getSelection();
	if (criteria.length > 0) {
		//当前选中节点值
		var filterCriteriaPcode = criteria[0].data.FILTER_CRITERIA_CODE;
		if(filterCriteriaPcode.length >= 32) {	//子节点超过八级不再创建子节点
			Ext.Msg.alert("提示","子节点已超过八级不能再创建子节点");
			return;
		}
		//检查选择的父节点是否是且/或
		var fcOperator = criteria[0].data.FILTER_CRITERIA_OPERATOR;
		if(fcOperator == '且' || fcOperator == '或') {
			//当前选中节点值
			var filterCriteriaPcode = criteria[0].data.FILTER_CRITERIA_CODE;
			//新增数据
			Ext.Ajax.request({
				url: "dreport/BReportFilterCriteria_doAdd.action",
				params: {
					templateId: criteria[0].data.TEMPLATE_ID,
                  	filterCriteriaType: criteria[0].data.FILTER_CRITERIA_TYPE,
                  	criteriaOperator : criteriaOperator,
                  	filterCriteriaPcode : filterCriteriaPcode
				},
				success: function(response) {
					 Ext.Msg.alert("提示", "操作成功！", function(){
					 	//清空选择
					 	Ext.getCmp(gridId).getSelectionModel().clearSelections(true);
					 	Ext.getCmp(gridId).getStore().load();
					 });
				},
				failure: function (response) {
					//清空选择
					Ext.getCmp(gridId).getSelectionModel().clearSelections(true);
                  Ext.Msg.alert("提示", "操作失败！");
              	}
			});
		} else {
			Ext.Msg.alert("提示","请选择筛选条件关系（且/或）！");
			return;
		}
	} else {
		Ext.Msg.alert("提示","请选择筛选条件关系（且/或）！");
	}

}
