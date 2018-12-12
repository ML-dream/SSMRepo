/*     */ package com.jspsmart.upload;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public class Request
/*     */ {
/*  26 */   private Hashtable m_parameters = new Hashtable();
/*  27 */   private int m_counter = 0;
/*     */ 
/*     */   protected void putParameter(String paramString1, String paramString2)
/*     */   {
/*  46 */     if (paramString1 == null)
/*  47 */       throw new IllegalArgumentException("The name of an element cannot be null.");
/*     */     Hashtable localHashtable;
/*  52 */     if (this.m_parameters.containsKey(paramString1)) {
/*  53 */       localHashtable = (Hashtable)this.m_parameters.get(paramString1);
/*  54 */       localHashtable.put(new Integer(localHashtable.size()), paramString2);
/*     */     } else {
/*  56 */       localHashtable = new Hashtable();
/*  57 */       localHashtable.put(new Integer(0), paramString2);
/*  58 */       this.m_parameters.put(paramString1, localHashtable);
/*  59 */       this.m_counter += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getParameter(String paramString)
/*     */   {
/*  95 */     if (paramString == null) {
/*  96 */       throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
/*     */     }
/*     */ 
/*  99 */     Hashtable localHashtable = (Hashtable)this.m_parameters.get(paramString);
/* 100 */     if (localHashtable == null)
/* 101 */       return null;
/* 102 */     return (String)localHashtable.get(new Integer(0));
/*     */   }
/*     */ 
/*     */   public Enumeration getParameterNames()
/*     */   {
/* 122 */     return this.m_parameters.keys();
/*     */   }
/*     */ 
/*     */   public String[] getParameterValues(String paramString)
/*     */   {
/* 147 */     if (paramString == null) {
/* 148 */       throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
/*     */     }
/*     */ 
/* 151 */     Hashtable localHashtable = (Hashtable)this.m_parameters.get(paramString);
/* 152 */     if (localHashtable == null)
/* 153 */       return null;
/* 154 */     String[] arrayOfString = new String[localHashtable.size()];
/* 155 */     for (int i = 0; i < localHashtable.size(); i++)
/* 156 */       arrayOfString[i] = ((String)localHashtable.get(new Integer(i)));
/* 157 */     return arrayOfString;
/*     */   }
/*     */ }

/* Location:           G:\helper\java操作\jspsmartupload.jar
 * Qualified Name:     com.jspsmart.upload.Request
 * JD-Core Version:    0.6.1
 */