
/*d = new dTree('d','','dtree');
		function buildtree(){	
			d.add('top','x','EBOM结构树',null,null,null,'','','');
	        d.add('jsq','top','jsq【部件】','..','部件','list','','','','ec');
	        d.add('xt','jsq','xt[组件]','..','组件','list','');
	        d.add('z','xt','z【轴】','....','零件','','','','','ec');
			d.draw();
		}
 * */

// Node object 节点对象！！！
/**
 * @param id         当前节点的ID号
 *        pid        父节点的ID号
 *        name       当前节点的名称
 *        url        当前节点指向的链接地址
 *        title      选择是部件，组件和零件三者之一
 *        target     指定新窗口target
 *        icon       图标？？？？
 *        iconOpen   节点展开后的图标
 *        open
 *        ec
 */
function Node(id, pid, name, url, title, target, icon, iconOpen, open,ec) { 
	this.id = id; 
	this.pid = pid; 
	this.name = name; 
	this.url = url; 
	this.title = title; 
	this.target = target; 
	this.icon = icon; 
	this.iconOpen = iconOpen; 
	this._io = open || false;                             // 节点是否已经展开
    this._is = false;                                     /* is = is selected, 标记该节点是否是被选中的节点 */
    this._ls = false;                                     /* ls = last slibling, 是否最后一个节点 */
    this._hc = false;                                     /* hc = has children, 标记是否具有子节点 */
    this._ai = 0;                                         /* ai = array index, 节点在数组中的下标 */
    this._p;                                              /* 节点的父节点 */
	this._ec  = ec;
}; 
// Tree object 树结构对象！！！
function dTree(objName,path,htmlContainer) { 
    this.path = path;
	this.config = { 
		target				: null, 
		folderLinks			: true,                       /* 文件夹图表上是否具有连接 */
		useSelection		: true,                       /* 选中部分是否高亮显示 */
		useCookies			: true,                       /* 是否使用cookie */
		useLines			: true,                       /* 显示连接线 */
		useIcons			: true,                       /* 显示图标 */
		useStatusText		: false,                      /* 在状态栏显示提示信息 */
		closeSameLevel	    : false,                      /* 关闭同级其他节点 */
		inOrder				: false 
	} 
	this.icon = { /* 指定各个图标 */
		root				: path+'img/base.jpg',        /* 根节点图标 */
		folder			    : path+'img/folder.gif',      /* 未展开节点图标 */
		folderOpen	        : path+'img/folderopen.gif',  /* 展开后节点图标 */
		node				: path+'img/page.gif',        /* 叶子节点(无节点的节点)图标 */
		empty				: path+'img/empty.gif',       /* 空白图标 */
		line				: path+'img/line.gif',        /* 竖向连接线图标 */
		join				: path+'img/join.gif',        /* 兼具水平、竖向连接线的图标 */
		joinBottom	        : path+'img/joinbottom.gif',  /* 底端连接线图标 */
		plus				: path+'img/plus.gif',        /* 带连接线的+图标 */
		plusBottom	        : path+'img/plusbottom.gif',  /* 带连接线的底端+图标 */
		minus				: path+'img/minus.gif',       /* 带连接线的-图标 */
		minusBottom	        : path+'img/minusbottom.gif', /* 带连接线的底端-图标 */
		nlPlus			    : path+'img/nolines_plus.gif',/* 不带连接线的+图标 */
		nlMinus			    : path+'img/nolines_minus.gif'/* 不带连接线的-图标 */
	}; 
	this.obj = objName;                                   /* 树对象名称 */
	this.aNodes = [];                                     /* 节点数组 */
	this.aNodesData = []; 
	this.container=htmlContainer||'dtree';                // 树所在的容器???
	this.aIndent = [];                                    /* 缩进数组 */
	this.root = new Node('x');                            /* 根节点 */
	this.selectedNode = null;                             /* 选定节点 */
	this.selectedFound = false;                           /* 是否已经选定节点 */
	this.completed = false;                               /* 结束标志 */
}; 
 
// Adds a new node to the node array alert  给节点队列增加一个新节点！！！
dTree.prototype.add = function(id, pid, name, url, title, target, icon, iconOpen, open,ec) { 
	this.aNodesData[this.aNodesData.length] = 
		new Node(id, pid, name, url, title, target, icon, iconOpen, open,ec); 
}; 
 
// Open/close all nodes 打开和关闭所有节点！！！
dTree.prototype.openAll = function() { 
	this.oAll(true); 
}; 
dTree.prototype.closeAll = function() { 
	this.oAll(false); 
}; 
 
// Outputs the tree to the page 
dTree.prototype.toString = function() { 
	var str = '<div class="dtree">\n'; 
	if (document.getElementById) { 
		if (this.config.useCookies) this.selectedNode = this.getSelected(); 
		//alert(this.root);
		str += this.addNode(this.root); 
	} else str += 'Browser not supported.'; 
	str += '</div>'; 
	if (!this.selectedFound) this.selectedNode = null; 
	this.completed = true; 
	return str; 
}; 
 
// Creates the tree structure 创建树结构！！！好像是创建子节点？？？
dTree.prototype.addNode = function(pNode) { 
	var str = ''; 
	var n=0; 
	if (this.config.inOrder) n = pNode._ai; 
	for (n; n<this.aNodes.length; n++) { 
		if (this.aNodes[n].pid == pNode.id) { 
			var cn = this.aNodes[n]; 
			cn._p = pNode; 
			cn._ai = n; 
			this.setCS(cn); 
			if (!cn.target && this.config.target) cn.target = this.config.target; 
			if (cn._hc && !cn._io && this.config.useCookies) cn._io = this.isOpen(cn.id); 
			if (!this.config.folderLinks && cn._hc) cn.url = null; 
			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) { 
					cn._is = true; 
					this.selectedNode = n; 
					this.selectedFound = true; 
			} 
			str += this.node(cn, n); 
			if (cn._ls) break; 
		} 
	} 
	return str; 
}; 
 
// Creates the node icon, url and text 创建节点的图标，url地址和文本！！！
dTree.prototype.node = function(node, nodeId) { 
	
	var str = '<div class="dTreeNode">' + this.indent(node, nodeId); 
	if (this.config.useIcons) { 
		if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node); 
		if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node; 
		if (this.root.id == node.pid) { 
			node.icon = this.icon.root; 
			node.iconOpen = this.icon.root; 
		} 
		str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io) ? node.iconOpen : node.icon) + '" alt="" />'; 
	} 
	if (node.url) { 
		if(node._ec=='ec'){str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"'; }else{
		str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"'; 
		}
		if (node.title) str += ' title="' + node.title + '"'; 
		if (node.target) str += ' target="' + node.target + '"'; 
		if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" '; 
		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc)) 
			str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"'; 
		str += '>'; 
	} 
	else if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id) 
		str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');" class="node">'; 
	str += node.name; 
	if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) str += '</a>'; 
	str += '</div>'; 
	if (node._hc) { 
		str += '<div id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">'; 
		str += this.addNode(node); 
		str += '</div>'; 
	} 
	this.aIndent.pop(); 
	return str; 
}; 
 
// Adds the empty and line icons 增加空的以及线图标！！！
dTree.prototype.indent = function(node, nodeId) { 
	var str = ''; 
	if (this.root.id != node.pid) { 
		for (var n=0; n<this.aIndent.length; n++) 
			str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" />'; 
		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1); 
		if (node._hc) { 
			str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');"><img id="j' + this.obj + nodeId + '" src="'; 
			if (!this.config.useLines) str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus; 
			else str += ( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) ); 
			str += '" alt="" /></a>'; 
		} else str += '<img src="' + ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) + '" alt="" />'; 
	} 
	return str; 
}; 
 
// Checks if a node has any children and if it is the last sibling 检查节点是否有子节点以及是否是最后一个节点！！！
dTree.prototype.setCS = function(node) { 
	var lastId; 
	for (var n=0; n<this.aNodes.length; n++) { 
		if (this.aNodes[n].pid == node.id) node._hc = true; 
		if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id; 
	} 
	if (lastId==node.id) node._ls = true; 
}; 
 
// Returns the selected node 选定节点！！！
dTree.prototype.getSelected = function() { 
	var sn = this.getCookie('cs' + this.obj); 
	return (sn) ? sn : null; 
}; 
 
// Highlights the selected node 高亮被选的节点！！！
dTree.prototype.s = function(id) { 
     //this.delegate(id);
	if (!this.config.useSelection) return; 
	var cn = this.aNodes[id]; 
	if (cn._hc && !this.config.folderLinks) return; 
	if (this.selectedNode != id) { 
		if (this.selectedNode || this.selectedNode==0) { 
			eOld = document.getElementById("s" + this.obj + this.selectedNode); 
			eOld.className = "node"; 
		} 
		eNew = document.getElementById("s" + this.obj + id); 
		eNew.className = "nodeSel"; 
		this.selectedNode = id; 
		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id); 
	} 
}; 
 
// Toggle Open or close 
dTree.prototype.o = function(id) { 
	var cn = this.aNodes[id]; 
	this.nodeStatus(!cn._io, id, cn._ls); 
	cn._io = !cn._io; 
	if (this.config.closeSameLevel) this.closeLevel(cn); 
	if (this.config.useCookies) this.updateCookie(); 
	//this.delegate(id);
}; 
 
// Open or close all nodes 打开和关闭所有节点！！！
dTree.prototype.oAll = function(status) { 
	for (var n=0; n<this.aNodes.length; n++) { 
		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) { 
			this.nodeStatus(status, n, this.aNodes[n]._ls) 
			this.aNodes[n]._io = status; 
		} 
	} 
	if (this.config.useCookies) this.updateCookie(); 
}; 
 
// Opens the tree to a specific node 
dTree.prototype.openTo = function(nId, bSelect, bFirst) { 
	if (!bFirst) { 
		for (var n=0; n<this.aNodes.length; n++) { 
			if (this.aNodes[n].id == nId) { 
				nId=n; 
				break; 
			} 
		} 
	} 
	var cn=this.aNodes[nId]; 
	if (cn.pid==this.root.id || !cn._p) return; 
	cn._io = true; 
	cn._is = bSelect; 
	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls); 
	if (this.completed && bSelect) this.s(cn._ai); 
	else if (bSelect) this._sn=cn._ai; 
	this.openTo(cn._p._ai, false, true); 
}; 
 
// Closes all nodes on the same level as certain node 
dTree.prototype.closeLevel = function(node) { 
	for (var n=0; n<this.aNodes.length; n++) { 
		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) { 
			this.nodeStatus(false, n, this.aNodes[n]._ls); 
			this.aNodes[n]._io = false; 
			this.closeAllChildren(this.aNodes[n]); 
		} 
	} 
} 
 
// Closes all children of a node 关闭当前节点下的所有子节点！！！
dTree.prototype.closeAllChildren = function(node) { 
	for (var n=0; n<this.aNodes.length; n++) { 
		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) { 
			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls); 
			this.aNodes[n]._io = false; 
			this.closeAllChildren(this.aNodes[n]);		 
		} 
	} 
} 
 
// Change the status of a node(open or closed) 
dTree.prototype.nodeStatus = function(status, id, bottom) { 
	eDiv	= document.getElementById('d' + this.obj + id); 
	eJoin	= document.getElementById('j' + this.obj + id); 
	if (this.config.useIcons) { 
		eIcon	= document.getElementById('i' + this.obj + id); 
		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon; 
	} 
	eJoin.src = (this.config.useLines)? 
	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)): 
	((status)?this.icon.nlMinus:this.icon.nlPlus); 
	eDiv.style.display = (status) ? 'block': 'none'; 
}; 
 
// [Cookie] Clears a cookie 清空cookie缓存！！！
dTree.prototype.clearCookie = function() { 
	var now = new Date(); 
	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24); 
	this.setCookie('co'+this.obj, 'cookieValue', yesterday); 
	this.setCookie('cs'+this.obj, 'cookieValue', yesterday); 
}; 
 
// [Cookie] Sets value in a cookie 设置缓存的值！！！
dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) { 
	document.cookie = 
		escape(cookieName) + '=' + escape(cookieValue) 
		+ (expires ? '; expires=' + expires.toGMTString() : '') 
		+ (path ? '; path=' + path : '') 
		+ (domain ? '; domain=' + domain : '') 
		+ (secure ? '; secure' : ''); 
}; 
 
// [Cookie] Gets a value from a cookie 从cookie缓冲中取值
dTree.prototype.getCookie = function(cookieName) { 
	var cookieValue = ''; 
	var posName = document.cookie.indexOf(escape(cookieName) + '='); 
	if (posName != -1) { 
		var posValue = posName + (escape(cookieName) + '=').length; 
		var endPos = document.cookie.indexOf(';', posValue); 
		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos)); 
		else cookieValue = unescape(document.cookie.substring(posValue)); 
	} 
	return (cookieValue); 
}; 
 
// [Cookie] Returns ids of open nodes as a string 把当前打开节点的ID号以字符串返回！！！
dTree.prototype.updateCookie = function() { 
	var str = ''; 
	for (var n=0; n<this.aNodes.length; n++) { 
		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) { 
			if (str) str += '.'; 
			str += this.aNodes[n].id; 
		} 
	} 
	this.setCookie('co' + this.obj, str); 
}; 
 
// [Cookie] Checks if a node id is in a cookie 检查在cookie中是否存在某一节点的ID值！！！
dTree.prototype.isOpen = function(id) { 
	var aOpen = this.getCookie('co' + this.obj).split('.'); 
	for (var n=0; n<aOpen.length; n++) 
		if (aOpen[n] == id) return true; 
	return false; 
}; 
 
// If Push and pop is not implemented by the browser 
if (!Array.prototype.push) { 
	Array.prototype.push = function array_push() { 
		for(var i=0;i<arguments.length;i++) 
			this[this.length]=arguments[i]; 
		return this.length; 
	} 
}; 
if (!Array.prototype.pop) { 
	Array.prototype.pop = function array_pop() { 
		lastElement = this[this.length-1]; 
		this.length = Math.max(this.length-1,0); 
		return lastElement; 
	} 
};
//show the tree  显示整棵树！！！
dTree.prototype.draw = function(){	
	// renew the two array to save original data.
	this.aNodes=new Array();
	//this.aIndent=new Array();
	// dump original data to aNode array.
	for(var i=0 ; i<this.aNodesData.length ; i++){		
		var oneNode=this.aNodesData[i];
		this.aNodes[i]=new Node(oneNode.id,oneNode.pid,oneNode.name,oneNode.url,oneNode.title,oneNode.target,oneNode.icon,oneNode.iconOpen,oneNode.open,oneNode._ec);	
		}	
	this.rewriteHTML();
}

// outputs the tree to the page , callled by show() 由显示函数调用！！！
dTree.prototype.rewriteHTML = function() {
	var str = '';	
	var container;
	container=document.getElementById(this.container);	
	if(!container){		
		alert('dTree can\'t find your specified container to show your tree.\n\n Please check your code!');
		return;
	}
	if (this.config.useCookies) this.selectedNode = this.getSelected();
	str += this.addNode(this.root);
	if (!this.selectedFound) this.selectedNode = null;
	this.completed = true;	
	container.innerHTML=str;
};

// Checks if a node has children  检查一个节点是否有子节点！！！
dTree.prototype.hasChildren=function(id){
    for(var i=0 ; i<this.aNodesData.length ; i++){		
		var oneNode=this.aNodesData[i];
		if(oneNode.pid==id)
		    return true;
	}
	return false;
}

//define a remove method for Array
Array.prototype.remove=function(dx) {
  if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
           this[n++]=this[i]
        }
    }
    this.length-=1
}

//remove a node  根据ID号删除当前节点！！！传入节点ID号
dTree.prototype.remove=function(id){
    if(!this.hasChildren(id)){
        for(var i=0 ; i<this.aNodesData.length ; i++){
            if(this.aNodesData[i].id==id){
                this.aNodesData.remove(i);
            }
        }
    }
}

