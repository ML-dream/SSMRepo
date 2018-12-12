	//业务处理表单对象
	var handleMasterForm;
	
	
	 /**
	 * 获取业务处理列表对象
	 * @param {} title 标题
	 * @param {} gridId 列表ID
	 * @param {} storeId 数据源ID
	 * @param {} result 列表数据对象
	 * @param {} source 业务源：0/未处理，1/已处理
	 * @return {}
	 */
	function getHandleGrid(title, gridId, storeId, result, source){
		
		var handleGrid = Ext.create('Ext.grid.Panel',{
				title: title,
		        id: gridId,
		        columnLines: true,
	    		border: 1,
	    		rowexpander: 30,
	    		tbar: getGridToolBar(title, 'gridTbar' + source, gridId, result, source),
	    		store:getStore(storeId, result.dataItemList, source),
	    		columns: getGridColumn(result.dataItemList, title, gridId, source), 
				bbar: getPagingtoolbar(storeId)
		});
		return handleGrid;
	}
	
	/**
	 * 获取工具条
	 * @param {} criteria 查询条件对象
	 * @param {} source 业务源：0/未处理，1/已处理
	 * @return {}
	 */
	function getGridToolBar(title, tbarId, gridId, criteria, source){
		
		
		var gridTbar = Ext.create('Ext.toolbar.Toolbar', {
			id: tbarId,
			enableOverflow: true
		});
		
		if(source == 0 && displayAddBtn == true){	//未处理,添加“新增”按钮
			gridTbar.add({
					iconCls:'icon-addBig', 
					tooltip:'新增',
					text: '新增',
					handler: function(){
						//不加当前选项卡列表数据
						reloadListTab = false;
						//更新当前选项卡内容为表单页
						Ext.getCmp('handleTab').remove(gridId);
						getHandleMasterForm(title, source, templateId, firstHandleNodeId, instanceId, instanceNodeId, 'HANDLE');
						Ext.getCmp('handleTab').insert(source, handleMasterForm);
						Ext.getCmp('handleTab').setActiveTab(source);
						
						//选项卡改变时：重新设置当前选项卡为列表页面标识
						resetListTab = true;
						resetListTabTitle = title;
						resetListTabSource = source;
						reloadListTab = true;
						//window.location.href="custom/BWorkFlowMenu_handle.action?pageSource=WF_MENU&templateId=" + templateId + 
						//	"&nodeId=" + firstHandleNodeId + "&menuId=" + menuId;
					}
				});

		} 
		
		gridTbar.add('->');
		
		//查询条件列表
		var criteriaList = criteria.criteria;
		if(0 < criteriaList.length) {
			var metaNum;
			Ext.Array.forEach(criteriaList, function(criteria){
				metaNum = criteria.META_NUM;
				criteria.META_NUM = metaNum + source;
				Ext.getCmp(tbarId).add(getFormField(criteria));
				criteria.META_NUM = metaNum;
			});
			
			gridTbar.add({
				xtype: 'button', 
				text: '查   询 ',
				icon: 'resources/custom/images/search.gif',
				margin: '0 30 0 30',
				handler: function(){
					var params = {templateId: templateId, busizModelId: busizModelId, busizTableName: busizTableName, source: source, menuId: menuId};
			
					Ext.Array.forEach(criteriaList, function(criteria){
						metaNum = criteria.META_PNUM + '.' + criteria.META_NUM + source;
						var metaNumValue = Ext.getCmp(metaNum).getValue();
						if(criteria.META_TYPE == 'DATE'){
							metaNumValue = dateFormat(metaNumValue, criteria.META_FIELD_TYPE);
						} else {
							metaNumValue = encodeURIComponent(encodeURIComponent(metaNumValue));
						}
						params[metaNum] = metaNumValue;
					});
					Ext.getCmp(gridId).getStore().load({params: params});
				}
			});
		}
		
		return gridTbar;
	}
	
	/**
	 * 获取列表列对象
	 * @param {} dataItemList 数据项列表
	 * @param {} source 业务源：0/未处理，1/已处理
	 */
	function getGridColumn(dataItemList, title, gridId, source){
		//未处理按钮图标
		var handleIcon = "approval.png";
		//发起流程状态
		if(displayAddBtn){
			var handleIcon = "declare.png";
		}
		var columns = [{ text: '序号', xtype: 'rownumberer', width: 40, sortable: false, height:25}];
		if(source == 0) {
			
			columns.push({
	      		text: '操作' , xtype: 'actioncolumn',width:120, sortable: false, align: "center", 
				items: [{
			 	  		icon: 'resources/custom/images/' + handleIcon, tooltip: "处理",
					 	handler: function(grid, rowIndex, colIndex){
				 	  	   var rec = grid.getStore().getAt(rowIndex);
				 	  	   var templateId = rec.get('TEMPLATE_ID');
				 	  	   var nodeId = rec.get("NODE_ID");
					  	   var instanceId = rec.get("INSTANCE_ID");
					  	   var instanceNodeId = rec.get("INSTANCE_NODE_ID");
					  	   
					  	   //不加当前选项卡列表数据
							reloadListTab = false;
					  	   	//更新当前选项卡内容为表单页
					  	   	Ext.getCmp('handleTab').remove(gridId);
							getHandleMasterForm(title, source, templateId, nodeId, instanceId, instanceNodeId, 'HANDLE');
							Ext.getCmp('handleTab').insert(source, handleMasterForm);
							Ext.getCmp('handleTab').setActiveTab(source);
							
							//选项卡改变时：重新设置当前选项卡为列表页面标识
							resetListTab = true;
							resetListTabTitle = title;
							resetListTabSource = source;
							reloadListTab = true;
					  	   	//window.location.href="custom/BWorkFlowMenu_handle.action?pageSource=WF_MENU&templateId=" + templateId + 
					  	   	//	"&nodeId=" + nodeId + "&instanceId=" + instanceId + "&instanceNodeId=" +　instanceNodeId + "&menuId=" + menuId;
					  	  
					 	}
				 	  },{
			 	  		icon: 'resources/custom/images/flow.png', tooltip: "视图",iconCls: "margin15",
						 	handler: function(grid, rowIndex, colIndex){
								   var rec = grid.getStore().getAt(rowIndex);
						 	  	   var templateId = rec.get('TEMPLATE_ID');
							  	   var instanceId = rec.get("INSTANCE_ID");
							  	   
							  	   showInstanceDetail(instanceId, templateId);
						 	}
				 	  },{
			 	  		icon: 'resources/custom/images/query.png', tooltip: "查看",iconCls: "margin15",
					 	handler: function(grid, rowIndex, colIndex){
							   var rec = grid.getStore().getAt(rowIndex);
					 	  	   var templateId = rec.get('TEMPLATE_ID');
						  	   var instanceId = rec.get("INSTANCE_ID");
						  	   var nodeId = rec.get("NODE_ID");
						  	   //var templateName = rec.get("TEMPLATE_NAME");
						  	   var instanceNodeId = rec.get("INSTANCE_NODE_ID");
						  	   
						  	   //不加当前选项卡列表数据
								reloadListTab = false;
						  	   	//更新当前选项卡内容为表单页
						  	   	Ext.getCmp('handleTab').remove(gridId);
								getHandleMasterForm(title, source, templateId, nodeId, instanceId, instanceNodeId, 'VIEW');
								Ext.getCmp('handleTab').insert(source, handleMasterForm);
								Ext.getCmp('handleTab').setActiveTab(source);
								
								//选项卡改变时：重新设置当前选项卡为列表页面标识
								resetListTab = true;
								resetListTabTitle = title;
								resetListTabSource = source;
								reloadListTab = true;
						  	   //showFormView(instanceId, busizModelId, busizTableName, nodeId, formDataSource, templateName, 'READ');
					 	}
				 	  }] 
			})
		}else {
			columns.push({
	      		text: '操作' , xtype: 'actioncolumn',width:100, sortable: false, align: "center", 
				items: [{
			 	  		icon: 'resources/custom/images/flow.png', tooltip: "视图",
						 	handler: function(grid, rowIndex, colIndex){
								   var rec = grid.getStore().getAt(rowIndex);
						 	  	   var templateId = rec.get('TEMPLATE_ID');
							  	   var instanceId = rec.get("INSTANCE_ID");
							  	   
							  	   showInstanceDetail(instanceId, templateId);
						 	}
				 	  },{
			 	  		icon: 'resources/custom/images/query.png', tooltip: "查看", iconCls: "margin15",
					 	handler: function(grid, rowIndex, colIndex){
				 	  	   	   var rec = grid.getStore().getAt(rowIndex);
					 	  	   var templateId = rec.get('TEMPLATE_ID');
						  	   var instanceId = rec.get("INSTANCE_ID");
						  	   var nodeId = rec.get("NODE_ID");
						  	   //var templateName = rec.get("TEMPLATE_NAME");
						  	   var instanceNodeId = rec.get("INSTANCE_NODE_ID");
						  	   
						  	   //不加当前选项卡列表数据
								reloadListTab = false;
						  	   //更新当前选项卡内容为表单页
						  	   	Ext.getCmp('handleTab').remove(gridId);
								getHandleMasterForm(title, source, templateId, nodeId, instanceId, instanceNodeId, 'VIEW');
								Ext.getCmp('handleTab').insert(source, handleMasterForm);
								Ext.getCmp('handleTab').setActiveTab(source);
								
								//选项卡改变时：重新设置当前选项卡为列表页面标识
								resetListTab = true;
								resetListTabTitle = title;
								resetListTabSource = source;
								reloadListTab = true;
						  	   //showFormView(instanceId, busizModelId, busizTableName, nodeId, formDataSource, templateName, 'READ');
					 	}
				 	  }] 
			})
		}
		
		Ext.Array.forEach(dataItemList, function(dataItem) {
			//如果是附件，提供附件下载功能
			if(dataItem.META_TYPE == 'ATTACH') {
				columns.push({text: dataItem.META_NAME, dataIndex: dataItem.META_NUM, flex: 1,
					renderer: function(value, cellmeta, record) {
						if(value){
							var filePath = encodeURIComponent(encodeURIComponent(value));
							var fileName = value.substring(0, value.indexOf('_')) + value.substring(value.lastIndexOf('.')); 
			           		var str = "<a href=\"custom/BWorkFlowInstance_download.action?filePath=" + 
			           			filePath + "\" title=\"下载\">" + fileName  + "</a>" ;
			          		return str;
						}
						
		          	}
				});
			}else if(dataItem.META_PRIMARY_KEY != 1){
				columns.push({text: dataItem.META_NAME, dataIndex: dataItem.META_NUM, flex: 1});
			}
			
		},this);
		
		columns.push({text: '流程状态', dataIndex: 'STATE', flex: 1});
		columns.push({text: '业务状态', dataIndex: 'STATUS', flex: 1});
		
		return columns;
	}
	
	/**
	 * 获取数据源
	 * @param {} storeId 数据源ID
	 * @param {} dataItemList 数据项列表
	 * @param {} source 业务源：0/未处理，1/已处理
	 * @return {}
	 */
	function getStore(storeId, dataItemList, source){
		
		//数据源字段对象
		var fields = ['ID','INSTANCE_ID', 'STATE', 'STATUS', 'INSTANCE_CODE', 'INSTANCE_SUMMARY', 'TEMPLATE_ID', 
			'INSTANCE_NODE_ID', 'NODE_ID', 'VIEW_FORM_CODE', 'TEMPLATE_NAME'];
			
		
		Ext.Array.forEach(dataItemList, function(dataItem) {
			fields.push(dataItem.META_NUM);
		},this);
		
		//流程实例数据
  		var store = Ext.create("Ext.data.Store",{
  			storeId: storeId,
	    	fields: fields,
  			pageSize: pageSize,
  			proxy: {
  				type: "ajax",
  				url: "custom/BWorkFlowMenu_busizJsonList.action",
  				extraParams: {templateId: templateId, busizModelId: busizModelId, busizTableName: busizTableName, source: source, menuId: menuId},
  				reader: {
  					type: "json",
  					root: "data",
  					totalProperty: "total"
  				}
  			},
  			listeners: {
  				load: function (store, records, successful) {
                     if (!successful) {
                         Ext.Msg.alert("提示", '数据加载失败！');
                     }
                 }
  			}
  		});
  		return store;
	}
	
	/**
	 * 获取业务处理表单对象
	 * @param {} title	选项卡标题
	 * @param {} source	选项卡索引
	 * @param {} templateId		模版主键
	 * @param {} nodeId			节点主键
	 * @param {} instanceId		实例主键
	 * @param {} instanceNodeId	实例节点主键
	 * @param {} formShowType	展示类型：HANDLE/处理表单，VIEW/查看表单
	 */
	function getHandleMasterForm(title, source, templateId, nodeId, instanceId, instanceNodeId, formShowType){
		
		handleMasterForm = Ext.create('Ext.panel.Panel', {
			id: 'handlePanel',
    		border: 0,
    		title: title,
    		autoScroll: true,
    		items: [{
	    		xtype: "form",
	    		id: "instanceForm",
	    		border: 0,
	    		layout: {
		            type: 'vbox',
		            align: 'stretch'
		        },
		        tbar: Ext.create('Ext.toolbar.Toolbar', {
					id: 'handleTbar',
					enableOverflow: true
				}),
		        bodyPadding: "5 0 10 10",	       		
		        fieldDefaults: { labelAlign: 'left', margin: "5 20 0 10", labelWidth: 70},
		        items: [{
			        	layout: {
				            type: 'hbox',
				            align: 'stretch'
			        	},
			        	border: 0,
			        	items: [
			        		{
			        			//模版主键
					    		xtype: 'hiddenfield', name: 'workFlowInstance.templateId', id: "templateId", value: templateId
					    	}, {
					    		//节点主键
					    		xtype: 'hiddenfield', name: 'workFlowInstance.nodeId', id: "nodeId", value: nodeId
					    	}, {
					    		//流程实例主键
					    		xtype: 'hiddenfield', name: 'workFlowInstance.instanceId', id: "instanceId", value: instanceId
					    	}, {
					    		//流程实例节点主键
					    		xtype: 'hiddenfield', name: 'instanceNodeId', id: "instanceNodeId", value: instanceNodeId
					    	}, {
					    		//模版业务编码
					    		xtype: 'hiddenfield', name: 'dataSourceNum', id: "dataSourceNum"
					    	}, {
					    		//模版业务主键
					    		xtype: 'hiddenfield', name: 'busizModelId', id: "busizModelId"
					    	}
			        	]
		        }]
	    	}]
			
		});
		
		/**
		 * 请求数据，填充到表单中
		 */
		Ext.Ajax.request({
			url: "custom/BWorkFlowInstance_getInstHandleData.action",
			params: {
				templateId: templateId,
				nodeId: nodeId,
				instanceId: instanceId
			},
			 success: function (response) {
		         var result = Ext.JSON.decode(response.responseText).data;
		         //处理表单对象
		         var instForm = Ext.getCmp("instanceForm");
		         //处理节点对象
		         var instNode = result.nodes;
		        instForm.setTitle(instNode.TEMPLATE_NAME);
				
	             //模版业务源编码
	             Ext.getCmp("dataSourceNum").setValue(instNode.DATA_SOURCE_NUM);
	             //模版业务源主键
	             Ext.getCmp("busizModelId").setValue(instNode.TEMPLATE_BUSIZ_ID);
	             
	             //主表数据项列表数据
		         var WFDataItemList = result.WF_MASTER_DATA_ITEM;
	            //子表数据项列表
		         var childDataItemList = result.WF_DETAIL_DATA_ITEM;
		         //子表表名列表
		         var childTableList = result.WF_DETAIL_DATA_TABLE;
		        
		         //设置表单控件
		         setFormField(instForm, instanceId, WFDataItemList, childDataItemList, childTableList);
	             
				//只读状态为“可写”，设置表单按钮
		        var WFToolbar = Ext.getCmp("handleTbar");
				if(result.readOnly == 'WRITER' && formShowType == 'HANDLE') { //表单项可写：添加保存按钮
						
					WFToolbar.add([{ 
		                    xtype:'button',
		                    id: 'saveButton' + source,
		                    text:'保存',
		                    icon: 'resources/custom/images/saveBus.png',
		                    handler:function(){ 
		                    	if(instForm.getForm().isValid()){
		                    		instForm.getForm().submit({
		                        		url: "custom/BWorkFlowInstance_saveMasterForm.action",
		                        		waitMsg: '正在保存，请稍后...',
		                        		success: function(form, action) {
		                        			instanceId = action.result.data.instanceId;
		                        			instanceNodeId = action.result.data.instanceNodeId;
		                        			Ext.getCmp("instanceId").setValue(instanceId);
		                        			Ext.getCmp("instanceNodeId").setValue(instanceNodeId);
		                        			Ext.Msg.alert('成功', "保存成功！");
					                       
					                    },
					                    failure: function(form, action) {
					                        Ext.Msg.alert('失败', action.result ? action.result.data : '操作失败！');
					                    }
		                        	});
		                    	}
		                    }
		                }]);
					
				} 
				
				//流程处理操作按钮
				if(formShowType == 'HANDLE'){
					var nodeLines = result.lines;
					if(null != nodeLines) {
						Ext.Array.forEach(nodeLines, function(nodeLine) {
							WFToolbar.add(getFormHandleButton(nodeLine, result.readOnly, source, title));
						}, this);
						
					}
				}
				
				
				//添加返回按钮
				WFToolbar.add({
					xtype:'button',
		                    text:'返回',
		                    id: 'returnButton' + source,
		                    icon: 'resources/custom/images/return.png',
		                    handler:function(){ 
		                    	//选项卡改变时：重新设置当前选项卡为列表页面标识
								resetListTab = false;
								reloadListTab = false;
								//更新当前选项卡内容为列表页面
		                    	Ext.getCmp('handleTab').remove('handlePanel');
		                    	var handleGrid = getHandleGrid(title, 'handleGrid' + source, 'handleStore' + source, resultMenu, source);
								Ext.getCmp('handleTab').insert(source, handleGrid);
								
								reloadListTab = true;
								Ext.getCmp('handleTab').setActiveTab(source);
								
		                    }
				});
				
				//添加查看历史按钮
				WFToolbar.add(['->', {
					xtype:'button',
					id: 'queryHistory' + source,
                    text:'查看历史',
                    icon: 'resources/custom/images/query.png',
                    handler:function(){ 
                    	showInstanceHistory(instanceId);
                    }
				}, '']);
				
		     },
		     failure: function (response) {
		         Ext.Msg.alert("提示", response.responseText);
		     }
			
		});
	}
	
	
	/**
 * 获取处理按钮
 */
function getFormHandleButton(nodeLine, readOnly, source, title){
	
	var formButton = Ext.create("Ext.Button", {
		id: 'ddd' + source,
		text: nodeLine.ACTION_NAME,
		icon: 'resources/custom/images/declare.png',
		handler: function(){
			Ext.getCmp("instanceForm").getForm().submit({
				waitMsg: '正在处理，请稍后...',
				url: "custom/BWorkFlowInstance_executeInstanceHandle.action",
				params: {
					nextNodeId: nodeLine.ACTION_END_NODE_ID,
					actionName: nodeLine.ACTION_NAME,
					readOnly: readOnly
				},
				success: function(form, action) {
					var pageSource = action.result.data;
				    Ext.Msg.alert('成功', "操作成功！", function(){
				    	
				    	//选项卡改变时：重新设置当前选项卡为列表页面标识
						resetListTab = false;
						reloadListTab = false;
						//更新当前选项卡内容为列表页
				    	Ext.getCmp('handleTab').remove('handlePanel');
                    	var handleGrid = getHandleGrid(title, 'handleGrid' + source, 'handleStore' + source, resultMenu, source);
						Ext.getCmp('handleTab').insert(source, handleGrid);
						
						reloadListTab = true;
						Ext.getCmp('handleTab').setActiveTab(source);
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
	 * 日期格式转换
	 * @param {} value
	 * @return {}
	 */
	function dateFormat(value, format){ 
	    if(null != value){ 
	        return Ext.Date.format(new Date(value),format); 
	    }
	    return value;
	} 
