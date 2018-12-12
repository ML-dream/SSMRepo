/**
 * 项目名称: work
 * 创建日期：2016-6-22
 * 修改历史：
 *		1.[2016-6-22]创建文件 by Flair
 */
package com.wl.testaction.utils;

import java.io.OutputStream;  
import java.util.List;  
  
public class ExcelEntity<T> {  
      
    private String title = "报表标题";  
    private String[] headers;  
    private List<T> dataset;  
    private OutputStream os;  
    private boolean hasComment = false;  
    private String commentContent = "report";  
    private String commentAuthor = "Galo";  
    public String getTitle() {  
        return title;  
    }  
    public void setTitle(String title) {  
        this.title = title;  
    }  
    public String[] getHeaders() {  
        return headers;  
    }  
    public void setHeaders(String[] headers) {  
        this.headers = headers;  
    }  
    public List<T> getDataset() {  
        return dataset;  
    }  
    public void setDataset(List<T> dataset) {  
        this.dataset = dataset;  
    }  
    public OutputStream getOs() {  
        return os;  
    }  
    public void setOs(OutputStream os) {  
        this.os = os;  
    }  
    public boolean isHasComment() {  
        return hasComment;  
    }  
    public void setHasComment(boolean hasComment) {  
        this.hasComment = hasComment;  
    }  
    public String getCommentContent() {  
        return commentContent;  
    }  
    public void setCommentContent(String commentContent) {  
        this.commentContent = commentContent;  
    }  
    public String getCommentAuthor() {  
        return commentAuthor;  
    }  
    public void setCommentAuthor(String commentAuthor) {  
        this.commentAuthor = commentAuthor;  
    }  
} 