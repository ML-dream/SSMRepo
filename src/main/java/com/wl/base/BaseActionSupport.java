//package com.wl.base;
//
//public class BaseActionSupport extends ActionSupport {
//    /**
//     * 注释内容
//     */
//    private static final long serialVersionUID = -6857445263440739570L;
//
//
//    /**
//     * 封装从Request中获取请求参数值
//     *
//     * @param param_name 请求参数
//     * @return 参数值
//     */
//    public String getParameter(String param_name) {
//        if (null == ServletActionContext.getRequest()) {
//            return null;
//        }
//
//        return ServletActionContext.getRequest().getParameter(param_name);
//    }
//
//    public HttpSession getSession() {
//        HttpServletRequest request = this.getRequest();
//
//        if (null == request) {
//            return null;
//        }
//
//        return request.getSession();
//    }
//
//    /**
//     * AJAX输出，返回null
//     */
//    public String ajax(String content) {
//        try {
//            HttpServletResponse response = this.getResponse();
//            response.getWriter().write(content);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * 输出JSON成功消息
//     *
//     * @param message 提示信息，null 则默认为"操作成功"
//     */
//    public String ajaxJsonSuccessMessage(String message) {
//        if (StringUtil.isNullOrEmpty(message)) {
//            message = "保存成功";
//        }
//
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        result.put("message", message);
//        result.put("opt", "success");
//        String json = PluSoft.Utils.JSON.Encode(result);
//
//        return ajax(json, "text/json");
//    }
//    
//    
//    /**
//     * 前台用输出JSON状态消息
//     *
//     * @param message 提示信息，null 则默认为"操作成功"
//     * @param opt success 还是 error
//     * @param HasMap 存储可能需要的参数
//     */
//    public String ajaxJsonOptMessage(String message , String opt  , HashMap<String , String >  map) {
//        if (StringUtil.isNullOrEmpty(message)) {
//            message = "操作成功！";
//        }
//        if (StringUtil.isNullOrEmpty(opt)) {
//        	opt = "success";
//        }
//
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        result.put("message", message);
//        result.put("opt", opt);
//        //带入可能需要的参数
//        if(null != map && !map.isEmpty()){
//        	 for (Map.Entry<String, String> entry : map.entrySet()) {
//             	result.put(entry.getKey(),  entry.getValue());
//             }
//        }
//       
//        
//        String json = PluSoft.Utils.JSON.Encode(result);
//        return ajax(json, "text/json");
//    }
//
//    /**
//     * 输出JSON成功消息
//     *
//     * @param message 提示信息，null 则默认为"操作成功"
//     */
//    public String ajaxJsonSuccessMessage(String message, Object obj) {
//        if (StringUtil.isNullOrEmpty(message)) {
//            message = "操作成功";
//        }
//
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        result.put("message", message);
//        result.put("opt", "success");
//        result.put("data", obj);
//        String json = PluSoft.Utils.JSON.Encode(result);
//
//        return ajax(json, "text/json");
//    }
//
//    /**
//     * 输出JSON成功消息
//     *
//     * @param message 提示信息，null 则默认为"操作成功"
//     */
//    public String ajaxJsonSuccessMessage(String message, String declareId) {
//        if (StringUtil.isNullOrEmpty(message)) {
//            message = "操作成功";
//        }
//
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        result.put("message", message);
//        result.put("opt", "success");
//        result.put("declareId", declareId);
//        String json = PluSoft.Utils.JSON.Encode(result);
//
//        return ajax(json, "text/json");
//    }
//    
//    /**
//     * 输出JSON错误消息
//     *
//     * @param message 提示信息，null 则默认为"操作失败"
//     */
//    public String ajaxJsonErrorMessage(String message) {
//        if (StringUtil.isNullOrEmpty(message)) {
//            message = "操作失败";
//        }
//
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        result.put("message", message);
//        result.put("opt", "error");
//        String json = PluSoft.Utils.JSON.Encode(result);
//
//        return ajax(json, "text/json");
//    }
//
//    /**
//     * 输出JSON错误消息
//     *
//     * @param message 提示信息，null 则默认为"操作失败"
//     * @param opt     操作结果
//     */
//    public String ajaxJsonErrorMessage(String message, String opt) {
//        if (StringUtil.isNullOrEmpty(message)) {
//            message = "操作失败";
//        }
//
//        if (StringUtil.isNullOrEmpty(opt)) {
//            opt = "error";
//        }
//
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        result.put("message", message);
//        result.put("opt", opt);
//        String json = PluSoft.Utils.JSON.Encode(result);
//
//        return ajax(json, "text/json");
//    }
//
//    /**
//     * 输出JSON错误消息
//     *
//     * @param message 提示信息，null 则默认为"操作失败"
//     * @param obj     操作结果
//     */
//    public String ajaxJsonErrorMessage(String message, Object obj) {
//        if (StringUtil.isNullOrEmpty(message)) {
//            message = "操作失败";
//        }
//
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        result.put("message", message);
//        result.put("opt", "error");
//        result.put("data", obj);
//        String json = PluSoft.Utils.JSON.Encode(result);
//
//        return ajax(json, "text/json");
//    }
//
//    /**
//     * AJAX输出，返回null
//     */
//    public String ajax(String content, String type) {
//        try {
//            HttpServletResponse response = this.getResponse();
//            response.setContentType(type + ";charset=UTF-8");
//            response.setHeader("Pragma", "No-cache");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expires", 0);
//            response.getWriter().write(content);
//            response.getWriter().flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public String ajaxDataTable(List data, int total) {
//        try {
//            String json = PluSoft.Utils.JSON.Encode(data);
//            JSONArray jsonArray = new JSONArray(json);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("data", jsonArray);
//            jsonObject.put("total", total);
//            
//            System.out.println("Flair jsonObject的数据是：===="+jsonObject.toString());
//            
//            return this.ajax(jsonObject.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public String ajaxDataTable(List data, long total) {
//        try {
//            String json = PluSoft.Utils.JSON.Encode(data);
//            JSONArray jsonArray = new JSONArray(json);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("data", jsonArray);
//            jsonObject.put("total", total);
//            
//            return this.ajax(jsonObject.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    
//    /**
//     * 中文解码
//     *
//     * @param value
//     * @return
//     * @throws Exception
//     */
//    public static String setDecodeURIComponent(String value) throws Exception {
//    	if(null == value || "".equals(value)) {
//    		return value;
//    	}
//        return URLDecoder.decode(URLDecoder.decode(value, "UTF-8"), "UTF-8");
//    }
//
//    public void downExl(String fileName ,InputStream inputStream) throws UnsupportedEncodingException {
//
//        String name = java.net.URLEncoder.encode(fileName + ".xls","UTF-8");
//        this.getResponse().setContentType("application/x-excel");
//        this.getResponse().setHeader("Content-Disposition", "attachment; filename="+name);
//
//
//
//        try {
//            byte[] bytes = new byte[1024];
//            while(inputStream.read(bytes) != -1 ) {
//                this.getResponse().getOutputStream().write(bytes);
//            }
//            this.getResponse().getOutputStream().flush();
//            this.getResponse().getOutputStream().close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
//
//
//    public boolean isAdmin(){
//        if("admin".equals(this.getRequest().getSession().getAttribute(MyProperties.WEB_LOGINUSER_LOGIN_NAME))){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//}
