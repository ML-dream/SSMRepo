
<%@page import="org.omg.CORBA.Request"%>
<%@page import="Test.TreeUtil"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*,Test.*,java.lang.reflect.*,java.io.File,java.io.FileInputStream,java.io.FileOutputStream,java.io.IOException,net.sf.json.JSONArray,net.sf.json.JSONObject,org.codehaus.jackson.JsonFactory"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="com.wl.tools.Sqlhelper"%>
<%@page import="java.sql.ResultSet"%>
<% 	
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");	
	 
    String methodName = request.getParameter("method");
          
    Class[] argsClass = new Class[2];  
    argsClass[0] = HttpServletRequest.class;
    argsClass[1] = HttpServletResponse.class;
     
    Class cls = this.getClass();   
    Method method = cls.getMethod(methodName, argsClass);   
    
    Object[] args = new Object[2];
   // System.out.println("request="+request);
   // System.out.println("response="+response);
    args[0] = request;
    args[1] = response;   
    method.invoke(this, args);      	
%>
<%!
public void LoadTree(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    
    String sql = "select * from plus_file order by num";
    ArrayList folders = new Test.TestDB().DBSelect(sql);
    
    String json = Test.JSON.Encode(folders);
    response.getWriter().write(json);    
}

/**
*	加载工序计划里面的左侧树结构
*/
public void LoadTreeprocess(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    System.out.println("已经进去LoadTree_process函数体了！！！");
    
    String sql2="select distinct item_id,order_id,product_id from partplan ";       //从partplan表中查询出所有零件
    System.out.println("sql2==="+sql2);
    ArrayList partplan = new Test.TestDB().DBSelect(sql2);
    
	String inData = "(";
    
    ResultSet rs = null ;
    try{
    	rs = Sqlhelper.executeQuery(sql2,null);
    	while(rs.next()){
    		inData += "'"+rs.getString(1)+"',";
    	}
    }catch (Exception e){
    	e.printStackTrace();
    }finally{
    	try{
    		rs.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    //str.substring(0,str.length()-1);
    inData = inData.substring(0,inData.length()-1)+")";
    System.out.println("inData======"+inData);
    
    //String sql = "select equipment_drawid,oper_id from process";
    String sql = "select item_id , fo_operid from fo_detail where item_id in "+inData;              //从FO表中查询出所有的物料号（也就是零件号）和工序号
    System.out.println("sql==="+sql);
    
    ArrayList folders = new Test.TestDB().DBSelect(sql);

    
    String partplanjson = Test.JSON.Encode(partplan);
    System.out.println("partplanjson=="+partplanjson);
    //String processjson = Test.JSON.Encode(folders);
    String processjson = Test.JSON.Encode(folders).replaceAll("\"ITEM_ID\"","pid");
    System.out.println("processjson="+processjson);
    //String json = (partplanjson+processjson).replace("][",",").replaceAll("\"PRODUCT_ID\"","id")
    //.replaceAll("\"OPER_ID\"","id").replaceAll("\"EQUIPMENT_DRAWID\"","pid");
    String json = (partplanjson+processjson).replace("][",",").replaceAll("\"ITEM_ID\"","id")
    .replaceAll("\"FO_OPERID\"","id");
    System.out.println("输出json是："+json);
    response.getWriter().write(json);    
}





/**
*	加载完工统计里面的左侧树结构
*/
public void LoadTreeFinish(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    System.out.println("已经进去LoadTreeFinishs函数体了！！！");
    String sql2="select distinct item_id,orderid,product_id from process_temp ";       //从partplan表中查询出所有零件
    System.out.println("sql2==="+sql2);
    ArrayList partplan = new Test.TestDB().DBSelect(sql2);
    
    String sql = "select item_id , oper_id, orderid from process_temp order by to_number(oper_id)";       //从FO表中查询出所有的物料号（也就是零件号）和工序号
    System.out.println("sql==="+sql);
    
    ArrayList folders = new Test.TestDB().DBSelect(sql);

    
    String partplanjson = Test.JSON.Encode(partplan);
    System.out.println("partplanjson=="+partplanjson);
    //String processjson = Test.JSON.Encode(folders);
    String processjson = Test.JSON.Encode(folders).replaceAll("\"ITEM_ID\"","pid");
    System.out.println("processjson="+processjson);
    //String json = (partplanjson+processjson).replace("][",",").replaceAll("\"PRODUCT_ID\"","id")
    //.replaceAll("\"OPER_ID\"","id").replaceAll("\"EQUIPMENT_DRAWID\"","pid");
    String json = (partplanjson+processjson).replace("][",",").replaceAll("\"ITEM_ID\"","id")
    .replaceAll("\"OPER_ID\"","id");
    System.out.println("输出json是："+json);
    response.getWriter().write(json);    
}






/**
*	加载派工里面的左侧树结构
*/
public void LoadTreePaigong(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    System.out.println("已经进去LoadTreeFinishs函数体了！！！");
    String sql2="select distinct item_id,orderid,product_id from process_plan ";       //从partplan表中查询出所有零件
    System.out.println("sql2==="+sql2);
    ArrayList partplan = new Test.TestDB().DBSelect(sql2);
    
    String sql = "select item_id , oper_id, orderid from process_plan order by to_number(oper_id)";       //从FO表中查询出所有的物料号（也就是零件号）和工序号
    System.out.println("sql==="+sql);
    
    ArrayList folders = new Test.TestDB().DBSelect(sql);

    
    String partplanjson = Test.JSON.Encode(partplan);
    System.out.println("partplanjson=="+partplanjson);
    //String processjson = Test.JSON.Encode(folders);
    String processjson = Test.JSON.Encode(folders).replaceAll("\"ITEM_ID\"","pid");
    System.out.println("processjson="+processjson);
    //String json = (partplanjson+processjson).replace("][",",").replaceAll("\"PRODUCT_ID\"","id")
    //.replaceAll("\"OPER_ID\"","id").replaceAll("\"EQUIPMENT_DRAWID\"","pid");
    String json = (partplanjson+processjson).replace("][",",").replaceAll("\"ITEM_ID\"","id")
    .replaceAll("\"OPER_ID\"","id");
    System.out.println("输出json是："+json);
    response.getWriter().write(json);    
}





/**
*	加载实时刷新里面的左侧树结构
*/
public void LoadTreeShuaxin(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    System.out.println("已经进去LoadTreeShuaxin函数体了！！！");
    String sql2="select distinct orderid,product_id from departuresheet ";       //从partplan表中查询出所有零件
    System.out.println("sql2==="+sql2);
    ArrayList partplan = new Test.TestDB().DBSelect(sql2);
    
    String sql = "select item_id , order_id,product_id from part_temp order by item_id";       //从FO表中查询出所有的物料号（也就是零件号）和工序号
    System.out.println("sql==="+sql);
    
    ArrayList folders = new Test.TestDB().DBSelect(sql);

    
    String partplanjson = Test.JSON.Encode(partplan);
    System.out.println("partplanjson=="+partplanjson);
    //String processjson = Test.JSON.Encode(folders);
    String processjson = Test.JSON.Encode(folders).replaceAll("\"ORDER_ID\"","pid");
    System.out.println("processjson="+processjson);
    String json = (partplanjson+processjson).replace("][",",").replaceAll("\"ITEM_ID\"","id")
    .replaceAll("\"ORDERID\"","id");
    System.out.println("输出json是："+json);
    response.getWriter().write(json);    
}




/**
*	加载工位计划里面的左侧树结构
*/
public void LoadTreeAo(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    System.out.println("已经进去LoadTreeAo函数体了！！！");
    
    String productSql="select product_id apro from ao_plan where order_id = '' or 1=1";       //从Item表中查询出所有产品,此处差了一个订单号的条件
    System.out.println("productSql==="+productSql);
    
    String aonoSql = "select ao_no aono, productid apro from aodetail";              //从FO表中查询出所有的物料号（也就是零件号）和工序号
   
    ArrayList product = new Test.TestDB().DBSelect(productSql);
    ArrayList aono = new Test.TestDB().DBSelect(aonoSql);
    for(int i=0;i<product.size();i++){
    	System.out.println(product.get(i));
    }
    
    for(int i=0;i<aono.size();i++){
    	System.out.println(aono.get(i));
    }
    String productjson = JSONArray.fromObject(product).toString();
    String aonojson = JSONArray.fromObject(aono).toString().replaceAll("\"APRO\"","pid");
    
    //String productjson = Test.JSON.Encode(product);
    System.out.println("productjson=="+productjson);

    //String aonojson = Test.JSON.Encode(aono).replaceAll("\"PRODUCTID\"","pid");
    System.out.println("aonojson="+aonojson);

    String json = (productjson+aonojson).replace("][",",").replaceAll("\"APRO\"","id")
    .replaceAll("\"AONO\"","id");
    System.out.println("输出json是："+json);
    response.getWriter().write(json);    
}


public void LoadNodes(HttpServletRequest request, HttpServletResponse response) throws Exception
{ 	
    //获取提交的数据
    String id = request.getParameter("id");
    if(StringUtil.isNullOrEmpty(id)) id = "-1";

    //获取下一级节点
    String sql = "select * from plus_file where pid = '" + id + "' order by num";
    ArrayList folders = new Test.TestDB().DBSelect(sql);
    
    //判断节点，是否有子节点。如果有，则处理isLeaf和expanded。
    for (int i = 0, l = folders.size(); i < l; i++)
    {
        HashMap node = (HashMap)folders.get(i);
        String nodeId = node.get("id").toString();

        String sql2 = "select * from plus_file where pid = '" + nodeId + "' order by num";
        ArrayList nodes = new Test.TestDB().DBSelect(sql2);
		
        if (nodes.size() > 0)
        {
            node.put("isLeaf", false);
            node.put("expanded", false);
        }
    }
    
    //返回处理结果
    String json = Test.JSON.Encode(folders);
    response.getWriter().write(json);    
}

public void SaveTreeTotxt(HttpServletRequest request, HttpServletResponse response)throws Exception{

    System.out.println("进入保存的函数体了已经！！！！");
    String dataJSON = request.getParameter("data");
    FileOutputStream fos= null;
    try{
			String path=request.getRealPath("");
			System.out.println(path);
            path=path+"/demo/data/copywork.txt";
			fos= new FileOutputStream(path);
			fos.write(dataJSON.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFileFromJson转化出现异常！！！");
		} finally{
		    if(fos!=null){
		       fos.close();
		    }
		}
		response.getWriter().write(dataJSON);
}

public void SaveTree(HttpServletRequest request, HttpServletResponse response)throws Exception
{
    String dataJSON = request.getParameter("data");
    /*
    String finaldataJSON = dataJSON.replaceAll("\"id\"","id")
    .replaceAll("\"text\"","text").replaceAll("\"_id\"","_id")
    .replaceAll("\"_pid\"","_pid").replaceAll("\"_level\"","_level")
    .replaceAll("\"expanded\"","expanded").replaceAll("\"pid\"","pid")
    .replaceAll("\"isLeaf\"","idLeaf").replaceAll("\"asyncLoad\"","asyncLoad")
    .replaceAll("\"children\"","children").replaceAll("\"checked\"","checked")
    .replaceAll("\"_indeterminate\"","_indeterminate");
    */
    System.out.println("dataJSON="+dataJSON);
    String removedJSON = request.getParameter("removed");
    ArrayList tree=new ArrayList();
    ArrayList removed = new ArrayList();
  
   tree = (ArrayList)Test.JSON.Decode(dataJSON);
   // removed = (ArrayList)Test.JSON.Decode(removedJSON);
   /* try{
        
    	tree =(ArrayList<Object>)Test.JSON.Decode(dataJSON);
    	removed = (ArrayList<Object>)Test.JSON.Decode(removedJSON);
        System.out.println("已经进入TreeService.jsp的树形转换！！！");
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("TreeUtil.ToList转化出错异常！！！！");
    }
    */
    
    System.out.println("执行到这里了已经！！！");

    //树形转换为列表
    ArrayList list= TreeUtil.ToList(tree, "-1", "children", "id", "pid");
    
    //生成id和num
    for(int i = 0,l = list.size();i<l;i++){
        HashMap node = (HashMap)list.get(i);
        if(node.get("id") == null || node.get("id").toString().equals("")){
            String UID = UUID.randomUUID().toString();
            node.put("id",UID);
        }
        node.put("num",i);
    }
    
    //生成pid
    list = TreeUtil.ToList(tree, "-1", "children", "id", "pid");
    
    // Add/Update/Move Node
    for(int i=0,l=list.size();i<l;i++){
           HashMap node = (HashMap)list.get(i);           
           String state = node.get("_state") == null ? "" : node.get("_state").toString();           
           if(state .equals("added"))
           {
                new Test.TestDB().InsertNode(node);
           }else{
                new Test.TestDB().UpdateTreeNode(node);   
           }
    }
    // Remove Node
    if(removed != null)
    {
           for(int j =0 ,len = removed.size();j<len;j++)
           {
                HashMap removedNode = (HashMap)removed.get(j);
                new Test.TestDB().RemoveNode(removedNode);
           }
    }
}



public void FilterTree(HttpServletRequest request, HttpServletResponse response)throws Exception
{
	ArrayList data = new ArrayList();
    
	//获取查询参数
    String text = request.getParameter("name").toLowerCase();
    
    //获取整个树数据
    String sql = "select * from plus_file";
    ArrayList nodes = new Test.TestDB().DBSelect(sql);
    
    //找出符合查询条件的节点
    for(int i=0;i<nodes.size();i++){
        HashMap node = (HashMap)nodes.get(i);
	
	String name = node.get("name") == null ? "" : node.get("name").toString().toLowerCase();

        if(name.indexOf(text) > -1){
        	data.add(node);
            
        	//加入父级所有节点
            String pid = node.get("pid").toString();
            if(!pid.equals("-1")){
                ArrayList data2 = SearchParentNode(pid,nodes);
                data.addAll(data2);
            }
        }
    }
    
    //去除重复节点
    HashMap idMaps = new HashMap();
    for(int i= data.size()-1;i>=0;i--){
        HashMap node = (HashMap)data.get(i);  
        String id = node.get("id").toString();
        if(idMaps.get(id) == null){
            idMaps.put(id,node);
        }else{
        	data.remove(i);
        }
    }
    
  //返回JSON
    String dataJson = Test.JSON.Encode(data);
    response.getWriter().write(dataJson);    
}

public ArrayList SearchParentNode(String pid,ArrayList nodes)throws Exception
{
    ArrayList data = new ArrayList();
    for(int i=0;i<nodes.size();i++){
        HashMap node = (HashMap)nodes.get(i);
        if(node.get("id").toString().equals(pid)){
            data.add(node);
            if(!node.get("pid").toString().equals( "-1")){
                ArrayList data2 = SearchParentNode(node.get("pid").toString(),nodes);
                data.addAll(data2);
            }
        }
    }
    return data;
}
%> 