/*      */ package com.jspsmart.upload;
/*      */ 
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
import java.nio.charset.Charset;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Vector;
/*      */ import javax.servlet.ServletConfig;
/*      */ import javax.servlet.ServletContext;
/*      */ import javax.servlet.ServletException;
/*      */ import javax.servlet.ServletRequest;
/*      */ import javax.servlet.ServletResponse;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import javax.servlet.http.HttpSession;
/*      */ import javax.servlet.jsp.JspWriter;
		   import javax.servlet.jsp.PageContext;
/*      */ 
/*      */ public class SmartUpload
/*      */ {
/*      */   protected byte[] m_binArray;
/*      */   protected HttpServletRequest m_request;
/*      */   protected HttpServletResponse m_response;
/*      */   protected ServletContext m_application;
/*   52 */   private int m_totalBytes = 0;
/*   53 */   private int m_currentIndex = 0;
/*   54 */   private int m_startData = 0;
/*   55 */   private int m_endData = 0;
/*   56 */   private String m_boundary = new String();
/*   57 */   private long m_totalMaxFileSize = 0L;
/*   58 */   private long m_maxFileSize = 0L;
/*   59 */   private Vector m_deniedFilesList = new Vector();
/*   60 */   private Vector m_allowedFilesList = new Vector();
/*   61 */   private boolean m_denyPhysicalPath = false;
/*   62 */   private boolean m_forcePhysicalPath = false;
/*   63 */   private String m_contentDisposition = new String();
/*      */   public static final int SAVE_AUTO = 0;
/*      */   public static final int SAVE_VIRTUAL = 1;
/*      */   public static final int SAVE_PHYSICAL = 2;
/*   71 */   private Files m_files = new Files();
/*      */ 
/*   74 */   private Request m_formRequest = new Request();
/*      */ 
/*      */   /** @deprecated */
/*      */   public final void init(ServletConfig paramServletConfig)
/*      */     throws ServletException
/*      */   {
/*   95 */     this.m_application = paramServletConfig.getServletContext();
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   public void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
/*      */     throws ServletException, IOException
/*      */   {
/*  115 */     this.m_request = paramHttpServletRequest;
/*  116 */     this.m_response = paramHttpServletResponse;
/*      */   }
/*      */ 
/*      */   public final void initialize(ServletConfig paramServletConfig, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
/*      */     throws ServletException
/*      */   {
/*  136 */     this.m_application = paramServletConfig.getServletContext();
/*  137 */     this.m_request = paramHttpServletRequest;
/*  138 */     this.m_response = paramHttpServletResponse;
/*      */   }
/*      */ 
/*      */   public final void initialize(PageContext paramPageContext)
/*      */     throws ServletException
/*      */   {
/*  153 */     this.m_application = paramPageContext.getServletContext();
/*  154 */     this.m_request = ((HttpServletRequest)paramPageContext.getRequest());
/*  155 */     this.m_response = ((HttpServletResponse)paramPageContext.getResponse());
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   public final void initialize(ServletContext paramServletContext, HttpSession paramHttpSession, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, JspWriter paramJspWriter)
/*      */     throws ServletException
/*      */   {
/*  180 */     this.m_application = paramServletContext;
/*  181 */     this.m_request = paramHttpServletRequest;
/*  182 */     this.m_response = paramHttpServletResponse;
/*      */   }
/*      */ 
/*      */   public void upload()
/*      */     throws ServletException, IOException, SmartUploadException
/*      */   {
/*  195 */     int i = 0;
/*  196 */     int j = 0;
/*  197 */     long l = 0L;
/*  198 */     int k = 0;
/*  199 */     String str1 = new String();
/*  200 */     String str2 = new String();
/*  201 */     String str3 = new String();
/*  202 */     String str4 = new String();
/*  203 */     String str5 = new String();
/*  204 */     String str6 = new String();
/*  205 */     String str7 = new String();
/*  206 */     String str8 = new String();
/*  207 */     String str9 = new String();
/*  208 */     int m = 0;
/*      */ 
/*  215 */     this.m_totalBytes = this.m_request.getContentLength();
/*      */ 
/*  218 */     this.m_binArray = new byte[this.m_totalBytes];
/*      */ 
/*  221 */     while (i < this.m_totalBytes) {
/*      */       try {
/*  223 */         this.m_request.getInputStream();
/*  224 */         j = this.m_request.getInputStream().read(this.m_binArray, i, this.m_totalBytes - i);
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*  228 */         throw new SmartUploadException("Unable to upload.");
/*      */       }
/*  230 */       i += j;
/*      */     }
/*      */ 
/*  237 */     while ((k == 0) && (this.m_currentIndex < this.m_totalBytes)) {
/*  238 */       if (this.m_binArray[this.m_currentIndex] == 13)
/*  239 */         k = 1;
/*      */       else
/*  241 */         this.m_boundary += (char)this.m_binArray[this.m_currentIndex];
/*  242 */       this.m_currentIndex += 1;
/*      */     }
/*      */ 
/*  246 */     if (this.m_currentIndex == 1) {
/*  247 */       return;
/*      */     }
/*      */ 
/*  254 */     this.m_currentIndex += 1;
/*      */ 
/*  256 */     while (this.m_currentIndex < this.m_totalBytes)
/*      */     {
/*  259 */       str1 = getDataHeader();
/*      */ 
/*  262 */       this.m_currentIndex += 2;
/*      */ 
/*  265 */       m = str1.indexOf("filename") > 0 ? 1 : 0;
/*      */ 
/*  268 */       str2 = getDataFieldValue(str1, "name");
/*      */ 
/*  271 */       if (m != 0) {
/*  272 */         str5 = getDataFieldValue(str1, "filename");
/*  273 */         str3 = getFileName(str5);
/*  274 */         str4 = getFileExt(str3);
/*  275 */         str6 = getContentType(str1);
/*  276 */         str7 = getContentDisp(str1);
/*  277 */         str8 = getTypeMIME(str6);
/*  278 */         str9 = getSubTypeMIME(str6);
/*      */       }
/*      */ 
/*  282 */       getDataSection();
/*      */ 
/*  285 */       if (m != 0)
/*      */       {
/*  287 */         if (str3.length() > 0)
/*      */         {
/*  290 */           if (this.m_deniedFilesList.contains(str4) == true) {
/*  291 */             throw new SecurityException("The extension of the file is denied to be uploaded (1015).");
/*      */           }
/*      */ 
/*  295 */           if ((!this.m_allowedFilesList.isEmpty()) && (!this.m_allowedFilesList.contains(str4)))
/*      */           {
/*  297 */             throw new SecurityException("The extension of the file is not allowed to be uploaded (1010).");
/*      */           }
/*      */ 
/*  301 */           if ((this.m_maxFileSize > 0L) && (this.m_endData - this.m_startData + 1 > this.m_maxFileSize))
/*      */           {
/*  303 */             throw new SecurityException("Size exceeded for this file : " + str3 + " (1105).");
/*      */           }
/*      */ 
/*  307 */           l += this.m_endData - this.m_startData + 1;
/*  308 */           if ((this.m_totalMaxFileSize > 0L) && (l > this.m_totalMaxFileSize))
/*      */           {
/*  310 */             throw new SecurityException("Total File Size exceeded (1110).");
/*      */           }
/*      */         }
/*      */       }
/*      */       Object localObject;
/*  317 */       if (m != 0)
/*      */       {
/*  320 */         localObject = new File();
/*      */ 
/*  323 */         ((File)localObject).setParent(this);
/*  324 */         ((File)localObject).setFieldName(str2);
/*  325 */         ((File)localObject).setFileName(str3);
/*  326 */         ((File)localObject).setFileExt(str4);
/*  327 */         ((File)localObject).setFilePathName(str5);
/*  328 */         ((File)localObject).setIsMissing(str5.length() == 0);
/*  329 */         ((File)localObject).setContentType(str6);
/*  330 */         ((File)localObject).setContentDisp(str7);
/*  331 */         ((File)localObject).setTypeMIME(str8);
/*  332 */         ((File)localObject).setSubTypeMIME(str9);
/*      */ 
/*  335 */         if (str6.indexOf("application/x-macbinary") > 0) {
/*  336 */           this.m_startData += 128;
/*      */         }
/*  338 */         ((File)localObject).setSize(this.m_endData - this.m_startData + 1);
/*  339 */         ((File)localObject).setStartData(this.m_startData);
/*  340 */         ((File)localObject).setEndData(this.m_endData);
/*      */ 
/*  343 */         this.m_files.addFile((File)localObject);
/*      */       }
/*      */       else
/*      */       {
/*  350 */         localObject = new String(this.m_binArray, this.m_startData, this.m_endData - this.m_startData + 1);
/*      */ 
/*  354 */         this.m_formRequest.putParameter(str2, (String)localObject);
/*      */       }
/*      */ 
/*  358 */       if ((char)this.m_binArray[(this.m_currentIndex + 1)] == '-') {
/*      */         break;
/*      */       }
/*  361 */       this.m_currentIndex += 2;
/*      */     }
/*      */   }
/*      */ 
/*      */   public int save(String paramString)
/*      */     throws ServletException, IOException, SmartUploadException
/*      */   {
/*  376 */     return save(paramString, 0);
/*      */   }
/*      */ 
/*      */   public int save(String paramString, int paramInt)
/*      */     throws ServletException, IOException, SmartUploadException
/*      */   {
/*  389 */     int i = 0;
/*      */ 
/*  392 */     if (paramString == null) {
/*  393 */       paramString = this.m_application.getRealPath("/");
/*      */     }
/*      */ 
/*  396 */     if (paramString.indexOf("/") != -1) {
/*  397 */       if (paramString.charAt(paramString.length() - 1) != '/')
/*  398 */         paramString = paramString + "/";
/*      */     }
/*  400 */     else if (paramString.charAt(paramString.length() - 1) != '\\') {
/*  401 */       paramString = paramString + "\\";
/*      */     }
/*  403 */     for (int j = 0; j < this.m_files.getCount(); j++) {
/*  404 */       if (!this.m_files.getFile(j).isMissing()) {
/*  405 */         this.m_files.getFile(j).saveAs(paramString + this.m_files.getFile(j).getFileName(), paramInt);
/*      */ 
/*  407 */         i++;
/*      */       }
/*      */     }
/*  410 */     return i;
/*      */   }
/*      */ 
/*      */   public int getSize()
/*      */   {
/*  419 */     return this.m_totalBytes;
/*      */   }
/*      */ 
/*      */   public byte getBinaryData(int paramInt)
/*      */   {
/*      */     byte b;
/*      */     try
/*      */     {
/*  434 */       b = this.m_binArray[paramInt];
/*      */     } catch (Exception localException) {
/*  436 */       throw new ArrayIndexOutOfBoundsException("Index out of range (1005).");
/*      */     }
/*      */ 
/*  439 */     return b;
/*      */   }
/*      */ 
/*      */   public Files getFiles()
/*      */   {
/*  450 */     return this.m_files;
/*      */   }
/*      */ 
/*      */   public Request getRequest()
/*      */   {
/*  463 */     return this.m_formRequest;
/*      */   }
/*      */ 
/*      */   public void downloadFile(String paramString)
/*      */     throws ServletException, IOException, SmartUploadException
/*      */   {
/*  476 */     downloadFile(paramString, null, null);
/*      */   }
/*      */ 
/*      */   public void downloadFile(String paramString1, String paramString2)
/*      */     throws ServletException, IOException, SmartUploadException, SmartUploadException
/*      */   {
/*  490 */     downloadFile(paramString1, paramString2, null);
/*      */   }
/*      */ 
/*      */   public void downloadFile(String paramString1, String paramString2, String paramString3)
/*      */     throws ServletException, IOException, SmartUploadException
/*      */   {
/*  508 */     downloadFile(paramString1, paramString2, paramString3, 65000);
/*      */   }
/*      */ 
/*      */   public void downloadFile(String paramString1, String paramString2, String paramString3, int paramInt)
/*      */     throws ServletException, IOException, SmartUploadException
/*      */   {
/*  528 */     if (paramString1 == null) throw new IllegalArgumentException("File '" + paramString1 + "' not found (1040).");
/*      */ 
/*  531 */     if (paramString1.equals("")) throw new IllegalArgumentException("File '" + paramString1 + "' not found (1040).");
/*      */ 
/*  534 */     if ((!isVirtual(paramString1)) && (this.m_denyPhysicalPath)) {
/*  535 */       throw new SecurityException("Physical path is denied (1035).");
/*      */     }
/*      */ 
/*  539 */     if (isVirtual(paramString1)) paramString1 = this.m_application.getRealPath(paramString1);
/*      */ 
/*  544 */     java.io.File localFile = new java.io.File(paramString1);
/*  545 */     FileInputStream localFileInputStream = new FileInputStream(localFile);
/*      */ 
/*  547 */     long l = localFile.length();
/*  548 */     int i = 0;
/*  549 */     int j = 0;
/*  550 */     byte[] arrayOfByte = new byte[paramInt];
/*      */ 
/*  553 */     if (paramString2 == null)
/*  554 */       this.m_response.setContentType("application/x-msdownload");
/*  555 */     else if (paramString2.length() == 0)
/*  556 */       this.m_response.setContentType("application/x-msdownload");
/*      */     else {
/*  558 */       this.m_response.setContentType(paramString2);
/*      */     }
/*      */ 
/*  561 */     this.m_response.setContentLength((int)l);
/*      */ 
/*  563 */     this.m_contentDisposition = (this.m_contentDisposition == null ? "attachment;" : this.m_contentDisposition);
/*      */ 
/*  567 */     if (paramString3 == null) this.m_response.setHeader("Content-Disposition", this.m_contentDisposition + " filename=" + getFileName(paramString1));
/*  569 */     else if (paramString3.length() == 0)
/*  570 */       this.m_response.setHeader("Content-Disposition", this.m_contentDisposition);
/*      */     else {
/*  572 */       this.m_response.setHeader("Content-Disposition", this.m_contentDisposition + " filename=" + paramString3);
/*      */     }
/*      */ 
/*  576 */     while (j < l) {
/*  577 */       i = localFileInputStream.read(arrayOfByte, 0, paramInt);
/*  578 */       j += i;
/*  579 */       this.m_response.getOutputStream().write(arrayOfByte, 0, i);
/*      */     }
/*  581 */     localFileInputStream.close();
/*      */   }
/*      */ 
/*      */   public void downloadField(ResultSet paramResultSet, String paramString1, String paramString2, String paramString3)
/*      */     throws ServletException, IOException, SQLException
/*      */   {
/*  605 */     if (paramResultSet == null) throw new IllegalArgumentException("The RecordSet cannot be null (1045).");
/*      */ 
/*  608 */     if (paramString1 == null) throw new IllegalArgumentException("The columnName cannot be null (1050).");
/*      */ 
/*  611 */     if (paramString1.length() == 0) throw new IllegalArgumentException("The columnName cannot be empty (1055).");
/*      */ 
/*  614 */     byte[] arrayOfByte = paramResultSet.getBytes(paramString1);
/*      */ 
/*  617 */     if (paramString2 == null)
/*  618 */       this.m_response.setContentType("application/x-msdownload");
/*  619 */     else if (paramString2.length() == 0)
/*  620 */       this.m_response.setContentType("application/x-msdownload");
/*      */     else {
/*  622 */       this.m_response.setContentType(paramString2);
/*      */     }
/*      */ 
/*  625 */     this.m_response.setContentLength(arrayOfByte.length);
/*      */ 
/*  628 */     if (paramString3 == null)
/*  629 */       this.m_response.setHeader("Content-Disposition", "attachment;");
/*  630 */     else if (paramString3.length() == 0)
/*  631 */       this.m_response.setHeader("Content-Disposition", "attachment;");
/*      */     else {
/*  633 */       this.m_response.setHeader("Content-Disposition", "attachment; filename=" + paramString3);
/*      */     }
/*      */ 
/*  637 */     this.m_response.getOutputStream().write(arrayOfByte, 0, arrayOfByte.length);
/*      */   }
/*      */ 
/*      */   public void fieldToFile(ResultSet paramResultSet, String paramString1, String paramString2)
/*      */     throws ServletException, IOException, SmartUploadException, SQLException
/*      */   {
/*      */     try
/*      */     {
/*  659 */       if (this.m_application.getRealPath(paramString2) != null) {
/*  660 */         paramString2 = this.m_application.getRealPath(paramString2);
/*      */       }
/*      */ 
/*  664 */       InputStream localInputStream = paramResultSet.getBinaryStream(paramString1);
/*      */ 
/*  667 */       FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
/*      */       int i;
/*  671 */       while ((i = localInputStream.read()) != -1)
/*  672 */         localFileOutputStream.write(i);
/*  673 */       localFileOutputStream.close();
/*      */     } catch (Exception localException) {
/*  675 */       throw new SmartUploadException("Unable to save file from the DataBase (1020).");
/*      */     }
/*      */   }
/*      */ 
/*      */   private String getDataFieldValue(String paramString1, String paramString2)
/*      */   {
/*  689 */     String str1 = new String();
/*  690 */     String str2 = new String();
/*  691 */     int i = 0;
/*  692 */     int j = 0;
/*  693 */     int k = 0;
/*  694 */     int m = 0;
/*      */ 
/*  696 */     str1 = paramString2 + "=" + '"';
/*  697 */     i = paramString1.indexOf(str1);
/*      */ 
/*  699 */     if (i > 0) {
/*  700 */       j = i + str1.length();
/*  701 */       k = j;
/*  702 */       str1 = "\"";
/*  703 */       m = paramString1.indexOf(str1, j);
/*  704 */       if ((k > 0) && (m > 0)) str2 = paramString1.substring(k, m);
/*      */     }
/*  706 */     return str2;
/*      */   }
/*      */ 
/*      */   private String getFileExt(String paramString)
/*      */   {
/*  716 */     String str = new String();
/*  717 */     int i = 0;
/*  718 */     int j = 0;
/*      */ 
/*  720 */     if (paramString == null) {
/*  721 */       return null;
/*      */     }
/*      */ 
/*  724 */     i = paramString.lastIndexOf('.') + 1;
/*  725 */     j = paramString.length();
/*  726 */     str = paramString.substring(i, j);
/*  727 */     if (paramString.lastIndexOf('.') > 0) {
/*  728 */       return str;
/*      */     }
/*  730 */     return "";
/*      */   }
/*      */ 
/*      */   private String getContentType(String paramString)
/*      */   {
/*  742 */     String str1 = new String();
/*  743 */     String str2 = new String();
/*  744 */     int i = 0;
/*  745 */     int j = 0;
/*      */ 
/*  747 */     str1 = "Content-Type:";
/*  748 */     i = paramString.indexOf(str1) + str1.length();
/*      */ 
/*  750 */     if (i != -1) {
/*  751 */       j = paramString.length();
/*  752 */       str2 = paramString.substring(i, j);
/*      */     }
/*  754 */     return str2;
/*      */   }
/*      */ 
/*      */   private String getTypeMIME(String paramString)
/*      */   {
/*  765 */     String str = new String();
/*  766 */     int i = 0;
/*      */ 
/*  768 */     i = paramString.indexOf("/");
/*      */ 
/*  770 */     if (i != -1) {
/*  771 */       return paramString.substring(1, i);
/*      */     }
/*  773 */     return paramString;
/*      */   }
/*      */ 
/*      */   private String getSubTypeMIME(String paramString)
/*      */   {
/*  781 */     String str = new String();
/*  782 */     int i = 0;
/*  783 */     int j = 0;
/*      */ 
/*  785 */     i = paramString.indexOf("/") + 1;
/*  786 */     if (i != -1) {
/*  787 */       j = paramString.length();
/*  788 */       return paramString.substring(i, j);
/*      */     }
/*  790 */     return paramString;
/*      */   }
/*      */ 
/*      */   private String getContentDisp(String paramString)
/*      */   {
/*  798 */     String str = new String();
/*  799 */     int i = 0;
/*  800 */     int j = 0;
/*      */ 
/*  802 */     i = paramString.indexOf(":") + 1;
/*  803 */     j = paramString.indexOf(";");
/*  804 */     str = paramString.substring(i, j);
/*  805 */     return str;
/*      */   }
/*      */ 
/*      */   private void getDataSection()
/*      */   {
/*  815 */     int i = 0;
/*  816 */     String str = new String();
/*  817 */     int j = this.m_currentIndex;
/*  818 */     int k = 0;
/*  819 */     int m = this.m_boundary.length();
/*  820 */     this.m_startData = this.m_currentIndex;
/*  821 */     this.m_endData = 0;
/*      */ 
/*  823 */     while (j < this.m_totalBytes)
/*      */     {
/*  825 */       if (this.m_binArray[j] == (byte)this.m_boundary.charAt(k))
/*      */       {
/*  827 */         if (k == m - 1) {
/*  828 */           this.m_endData = (j - m + 1 - 3);
/*  829 */           break;
/*      */         }
/*  831 */         j++;
/*  832 */         k++;
/*      */       }
/*      */       else {
/*  835 */         j++;
/*  836 */         k = 0;
/*      */       }
/*      */     }
/*      */ 
/*  840 */     this.m_currentIndex = (this.m_endData + m + 3);
/*      */   }
/*      */ 
/*      */   private String getDataHeader()
/*      */   {
/*  849 */     int i = this.m_currentIndex;
/*  850 */     int j = 0;
/*  851 */     int k = 0;
/*  852 */     int m = 0;
/*      */ 
/*  855 */     while (m == 0)
/*      */     {
/*  857 */       if ((this.m_binArray[this.m_currentIndex] == 13) && (this.m_binArray[(this.m_currentIndex + 2)] == 13))
/*      */       {
/*  859 */         m = 1;
/*  860 */         j = this.m_currentIndex - 1;
/*  861 */         this.m_currentIndex += 2;
/*      */       }
/*      */       else {
/*  864 */         this.m_currentIndex += 1;
/*      */       }
/*      */     }
/*  866 */     String str = new String(this.m_binArray, i, j - i + 1,Charset.forName("UTF-8"));
/*  867 */     return str;
/*      */   }
/*      */ 
/*      */   private String getFileName(String paramString)
/*      */   {
/*  877 */     String str1 = new String();
/*  878 */     String str2 = new String();
/*  879 */     int i = 0;
/*  880 */     int j = 0;
/*  881 */     int k = 0;
/*  882 */     int m = 0;
/*      */ 
/*  884 */     i = paramString.lastIndexOf('/');
/*  885 */     if (i != -1)
/*  886 */       return paramString.substring(i + 1, paramString.length());
/*  887 */     i = paramString.lastIndexOf('\\');
/*  888 */     if (i != -1) {
/*  889 */       return paramString.substring(i + 1, paramString.length());
/*      */     }
/*  891 */     return paramString;
/*      */   }
/*      */ 
/*      */   public void setDeniedFilesList(String paramString)
/*      */     throws ServletException, IOException, SQLException
/*      */   {
/*  910 */     String str = "";
/*      */ 
/*  912 */     if (paramString != null) {
/*  913 */       str = "";
/*  914 */       for (int i = 0; i < paramString.length(); i++) {
/*  915 */         if (paramString.charAt(i) == ',') {
/*  916 */           if (!this.m_deniedFilesList.contains(str))
/*  917 */             this.m_deniedFilesList.addElement(str);
/*  918 */           str = "";
/*      */         } else {
/*  920 */           str = str + paramString.charAt(i);
/*      */         }
/*      */       }
/*  923 */       if (str != "") this.m_deniedFilesList.addElement(str); 
/*      */     } else { this.m_deniedFilesList = null; }
/*      */ 
/*      */   }
/*      */ 
/*      */   public void setAllowedFilesList(String paramString)
/*      */   {
/*  942 */     String str = "";
/*      */ 
/*  944 */     if (paramString != null) {
/*  945 */       str = "";
/*  946 */       for (int i = 0; i < paramString.length(); i++) {
/*  947 */         if (paramString.charAt(i) == ',') {
/*  948 */           if (!this.m_allowedFilesList.contains(str))
/*  949 */             this.m_allowedFilesList.addElement(str);
/*  950 */           str = "";
/*      */         } else {
/*  952 */           str = str + paramString.charAt(i);
/*      */         }
/*      */       }
/*  955 */       if (str != "") this.m_allowedFilesList.addElement(str); 
/*      */     } else { this.m_allowedFilesList = null; }
/*      */ 
/*      */   }
/*      */ 
/*      */   public void setDenyPhysicalPath(boolean paramBoolean)
/*      */   {
/*  969 */     this.m_denyPhysicalPath = paramBoolean;
/*      */   }
/*      */ 
/*      */   public void setForcePhysicalPath(boolean paramBoolean)
/*      */   {
/*  983 */     this.m_forcePhysicalPath = paramBoolean;
/*      */   }
/*      */ 
/*      */   public void setContentDisposition(String paramString)
/*      */   {
/*  996 */     this.m_contentDisposition = paramString;
/*      */   }
/*      */ 
/*      */   public void setTotalMaxFileSize(long paramLong)
/*      */   {
/* 1010 */     this.m_totalMaxFileSize = paramLong;
/*      */   }
/*      */ 
/*      */   public void setMaxFileSize(long paramLong)
/*      */   {
/* 1024 */     this.m_maxFileSize = paramLong;
/*      */   }
/*      */ 
/*      */   protected String getPhysicalPath(String paramString, int paramInt)
/*      */     throws IOException
/*      */   {
/* 1040 */     String str1 = new String();
/* 1041 */     String str2 = new String();
/* 1042 */     String str3 = new String();
/* 1043 */     int i = 0;
/* 1044 */     str3 = System.getProperty("file.separator");
/*      */ 
/* 1047 */     if (paramString == null) throw new IllegalArgumentException("There is no specified destination file (1140).");
/*      */ 
/* 1050 */     if (paramString.equals("")) throw new IllegalArgumentException("There is no specified destination file (1140).");
/*      */ 
/* 1055 */     if (paramString.lastIndexOf("\\") >= 0) {
/* 1056 */       str1 = paramString.substring(0, paramString.lastIndexOf("\\"));
/* 1057 */       str2 = paramString.substring(paramString.lastIndexOf("\\") + 1);
/*      */     }
/* 1059 */     if (paramString.lastIndexOf("/") >= 0) {
/* 1060 */       str1 = paramString.substring(0, paramString.lastIndexOf("/"));
/* 1061 */       str2 = paramString.substring(paramString.lastIndexOf("/") + 1);
/*      */     }
/* 1063 */     str1 = str1.length() == 0 ? "/" : str1;
/*      */ 
/* 1066 */     java.io.File localFile = new java.io.File(str1);
/* 1067 */     if (localFile.exists()) i = 1;
/*      */ 
/* 1070 */     if (paramInt == 0)
/*      */     {
/* 1072 */       if (isVirtual(str1)) {
/* 1073 */         str1 = this.m_application.getRealPath(str1);
/* 1074 */         if (str1.endsWith(str3))
/* 1075 */           str1 = str1 + str2;
/*      */         else {
/* 1077 */           str1 = str1 + str3 + str2;
/*      */         }
/* 1079 */         return str1;
/*      */       }
/* 1081 */       if (i != 0) {
/* 1082 */         if (this.m_denyPhysicalPath) {
/* 1083 */           throw new IllegalArgumentException("Physical path is denied (1125).");
/*      */         }
/*      */ 
/* 1086 */         return paramString;
/*      */       }
/*      */ 
/* 1089 */       throw new IllegalArgumentException("This path does not exist (1135).");
/*      */     }
/*      */ 
/* 1095 */     if (paramInt == 1) {
/* 1096 */       if (isVirtual(str1)) {
/* 1097 */         str1 = this.m_application.getRealPath(str1);
/* 1098 */         if (str1.endsWith(str3))
/* 1099 */           str1 = str1 + str2;
/*      */         else {
/* 1101 */           str1 = str1 + str3 + str2;
/*      */         }
/* 1103 */         return str1;
/*      */       }
/* 1105 */       if (i != 0) {
/* 1106 */         throw new IllegalArgumentException("The path is not a virtual path.");
/*      */       }
/*      */ 
/* 1109 */       throw new IllegalArgumentException("This path does not exist (1135).");
/*      */     }
/*      */ 
/* 1115 */     if (paramInt == 2) {
/* 1116 */       if (i != 0) {
/* 1117 */         if (this.m_denyPhysicalPath) {
/* 1118 */           throw new IllegalArgumentException("Physical path is denied (1125).");
/*      */         }
/*      */ 
/* 1121 */         return paramString;
/*      */       }
/*      */ 
/* 1124 */       if (isVirtual(str1)) {
/* 1125 */         throw new IllegalArgumentException("The path is not a physical path.");
/*      */       }
/*      */ 
/* 1128 */       throw new IllegalArgumentException("This path does not exist (1135).");
/*      */     }
/*      */ 
/* 1133 */     return null;
/*      */   }
/*      */ 
/*      */   public void uploadInFile(String paramString)
/*      */     throws IOException, SmartUploadException
/*      */   {
/* 1153 */     int i = 0;
/* 1154 */     int j = 0;
/* 1155 */     int k = 0;
/*      */ 
/* 1157 */     if (paramString == null) throw new IllegalArgumentException("There is no specified destination file (1025).");
/*      */ 
/* 1160 */     if (paramString.length() == 0) throw new IllegalArgumentException("There is no specified destination file (1025).");
/*      */ 
/* 1163 */     if ((!isVirtual(paramString)) && (this.m_denyPhysicalPath)) {
/* 1164 */       throw new SecurityException("Physical path is denied (1035).");
/*      */     }
/*      */ 
/* 1167 */     i = this.m_request.getContentLength();
/*      */ 
/* 1170 */     this.m_binArray = new byte[i];
/*      */ 
/* 1173 */     while (j < i) {
/*      */       try {
/* 1175 */         k = this.m_request.getInputStream().read(this.m_binArray, j, i - j);
/*      */       }
/*      */       catch (Exception localException1) {
/* 1178 */         throw new SmartUploadException("Unable to upload.");
/*      */       }
/* 1180 */       j += k;
/*      */     }
/*      */ 
/* 1184 */     if (isVirtual(paramString)) paramString = this.m_application.getRealPath(paramString);
/*      */ 
/*      */     try
/*      */     {
/* 1189 */       java.io.File localFile = new java.io.File(paramString);
/*      */ 
/* 1192 */       FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
/* 1193 */       localFileOutputStream.write(this.m_binArray);
/* 1194 */       localFileOutputStream.close();
/*      */     }
/*      */     catch (Exception localException2) {
/* 1197 */       throw new SmartUploadException("The Form cannot be saved in the specified file (1030).");
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean isVirtual(String paramString)
/*      */   {
/* 1213 */     if (this.m_application.getRealPath(paramString) != null)
/*      */     {
/* 1215 */       java.io.File localFile = new java.io.File(this.m_application.getRealPath(paramString));
/*      */ 
/* 1217 */       return localFile.exists();
/*      */     }
/* 1219 */     return false;
/*      */   }
/*      */ }

/* Location:           G:\helper\java操作\jspsmartupload.jar
 * Qualified Name:     com.jspsmart.upload.SmartUpload
 * JD-Core Version:    0.6.1
 */