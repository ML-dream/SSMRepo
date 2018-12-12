<%@ page language="java" contentType="text/html; charset=utf-8" import="com.wl.forms.User"%>
<jsp:useBean id="login" scope="page" class="com.wl.tools.login"/>
<% String user_id = "";
    if(session.getAttribute("user")!=null){
       //System.out.println("userid"+((User)session.getAttribute("user")).getUser_id());
       user_id = ((User)session.getAttribute("user")).getUserId();
       //session.invalidate();
    }
    //login.setUserid(((User)session.getAttribute("user")).getUser_id());
    //login.query();bgcolor="lightblue"
%>
 
<HTML>
<HEAD><TITLE>更改密码</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="mb/zcstyle.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.5730.13" name=GENERATOR></HEAD>
<BODY >
<FORM name=form1 action=Modify method=post>
<DIV class=clear id=wp>

<INPUT type=hidden value=regok name=dopost> 
<INPUT type=hidden value=个人 name=mtype> 
<DIV id=hd><img src="images/logo.gif"></DIV>
<H5>用户密码更改</H5>
<DIV class=reg>
<P><LABEL for=name>*用户名：</LABEL><SPAN class=sfl>
<INPUT class="input w_240" id=userid onblur=checkuser() value="<%if(!user_id.equals("")){%><%=user_id%><%}%>" maxLength=20 name=userid> 
<EM id=feifa style="DISPLAY: none"></EM></SPAN></P>

<P><LABEL for=pw>*密码：</LABEL><SPAN class=sfl> 
<INPUT class="input w_240" id=userpwd onblur=unfocususerpwd() type=password maxLength=20 name=userpwd> 
<EM id=checkuserpwd style="DISPLAY: none"></EM></SPAN></P>

<P class=bline></P>

<P><LABEL>*机密级别：</LABEL> <SPAN class=sfl>
<INPUT type=radio value=10 name=secret checked onclick="onfocusTopLevel()"> 
<LABEL style="FLOAT: none; WIDTH: auto" for=s1>机密</LABEL>&nbsp;&nbsp; 
<INPUT type=radio value=8 name=secret onclick="onfocusLevel()"> 
<LABEL style="FLOAT: none; WIDTH: auto" for=s2>秘密</LABEL>
<EM id=level style="DISPLAY: inline;color: Gray">机密级--密码长度要求≥10位</EM></SPAN></P>
<P class=bline></P>

<P><LABEL for=pw>*新密码：</LABEL><SPAN class=sfl> 
<INPUT class="input w_240" id=userpwdnew onkeyup="unfocususerpwdnew()" onblur="unfocususerpwdnew()" type=password maxLength=20 name=userpwdnew > 
<EM id=checkuserpwdnew style="DISPLAY:none;"></EM></SPAN></P>

<P><LABEL for=pw></LABEL><SPAN class=sfl> <EM id=checkuserpwdnew style="DISPLAY: inline">
<font color="#c0c0c0">可以是大小写英文字母、数字、特殊字符（&ldquo;_&rdquo;、&ldquo;.&rdquo;、&ldquo;-&rdquo;、&ldquo;/&rdquo;、&ldquo;*&rdquo;）。至少2种字符组合</font>
</EM></SPAN></P>

<P><LABEL>*密码强度：</LABEL> <SPAN class=sfl>
<STRONG class="pw_power pw_ebb" id=pwdstrong></STRONG></SPAN></P>

<P><LABEL for=pw1>*新密码确认：</LABEL> <SPAN class=sfl>
<INPUT class="input w_240" id=userpwdok onkeyup="keyupuserpwdok()" onblur="unfocususerpwdok()" type=password maxLength=20 name=userpwdok> 
<EM id=checkuserpwdok style="DISPLAY: none"></EM></SPAN></P>

<P><LABEL for=pw1>*验证码：</LABEL> <SPAN class=sfl style="MARGIN-LEFT: -5px"> <INPUT type=text name=certCode
style="BORDER-RIGHT: #ccc 1px solid; PADDING-RIGHT: 0px; BORDER-TOP: #ccc 1px solid; PADDING-LEFT: 0px; FONT-WEIGHT: bold; bgcolor:#ffffff; PADDING-BOTTOM: 0px; BORDER-LEFT: #ccc 1px solid; WIDTH: 48px; COLOR: #000; TEXT-INDENT: 5px; PADDING-TOP: 0px; BORDER-BOTTOM: #ccc 1px solid; HEIGHT: 20px" 
 maxLength=4 size=4 >
<%--  ;BACKGROUND: url(img/zc/input_bg.gif) no-repeat--%>
<IMG src="makeCertPic.jsp" title = "看不清？请点击图片更换！" onclick="this.src='makeCertPic.jsp?'+new Date().getTime()";> </SPAN></P>

<P class=bline></P>
<DIV class=action><INPUT class=confirm_btn type=button value=更改密码 name=Submit onclick="return toservlet()"> 
</DIV>
</DIV>
<DIV id=ft>&copy;---南京航空航天大学---&copy;</DIV>
</DIV>
</FORM>
</BODY></HTML>
<SCRIPT>
function toservlet(){
     //unfocususerpwdnew();
     if ($("userid").value=="" ){alert("请输入用户号！");$("userid").focus();return ;}	
     if ($("userpwd").value=="" ){alert("请输入旧密码！");$("userpwd").focus();return ;}	
     if (!document.getElementsByName("secret")[0].checked && !document.getElementsByName("secret")[1].checked){alert("请选择密码等级！");return ;}
     
     if ($("userpwdnew").value=="" ){alert("请输入新密码！");$("userpwdnew").focus();return ;}	
     if ($("userpwdok").value=="" ){alert("请输入确认新密码！");$("userpwdok").focus();return ;}	
     
     if ($("certCode").value=="" ){alert("请输入验证码！");$("certCode").focus();return ;}	
		document.all.form1.action = "Modify";
		document.all.form1.submit();
}
$ = function(eid){
	return document.getElementById(eid);
}
function init(){
	var num = "";
	var keys= "";
	var myURL = window.location.href;
	if(myURL.indexOf("?")!=0){
		myURL=myURL.substring(myURL.indexOf("?")+1, myURL.length);
		var params=myURL.split("&");
		for(i=0;i<params.length;i++){
			var attrPair=params[i].split("=");
			if(attrPair.length==2 && attrPair[0]=="redirecturl"){
				num=unescape(attrPair[1]);
			}
			if(attrPair.length==2 && attrPair[0]=="key"){
				keys=unescape(attrPair[1]);
			}
		}
	}
	$('redirecturl').value=escape(num);
	$('key').value=escape(keys);
}

function  bytelength(szString){
	return szString.replace(new RegExp("[^\x00-\xff]", "g"), "  ").length;
}
function checkPassword(e){
    var ok = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_.-/*";
	var ok1 = "1234567890";
	var ok2 = "qwertyuiopasdfghjklzxcvbnm";
	var ok3 = "QWERTYUIOPASDFGHJKLZXCVBNM";
	var ok4 = "_.-/*";
	var j=0;var k=0;var m=0;var n=0;
	for(var i=0; i<e.length; i++){
		if (ok.indexOf(e.charAt(i))<0) {
			return false;
		}
	}
	for(var i=0; i<e.length; i++){
		if (ok1.indexOf(e.charAt(i))>0) {
			j=1;
		}
		if (ok2.indexOf(e.charAt(i))>0) {
			k=1;
		}
		if (ok3.indexOf(e.charAt(i))>0) {
			m=1;
		}
		if (ok4.indexOf(e.charAt(i))>0) {
			n=1;
		}
	}
	if((j+k+m+n)<2){
	    return false;
	}
	return true;
}
function checkPassword2(e){
	var ok = "1234567890";
	for(var i=0; i<e.length; i++){
		if (ok.indexOf(e.charAt(i))<0) {
			return true;
		}
	}
	return false;
}
function checkPassword3(e){
	var  pos= e.charAt(0);
	for(var i=0; i<e.length; i++){
              pos=e.charAt(i)+e.charAt(i)+e.charAt(i);
		if (e.indexOf(pos,i)>=0) {
			return false;
		}
	}
	return true;
}

function gotoAdvance(){
	$("proB").className="a1";
	$("proA").className="xz";
	$("proc2").style.display="inline";
	$("procAnser2").style.display="inline";
}
function gotoBase(){
	$("proA").className="a1";
	$("proB").className="xz";
	$("proc2").style.display="none";
	$("procAnser2").style.display="none";
}
function checkuser(){
	var szUsername=$("userid").value;
	if(szUsername==""){	
		$("feifa").innerHTML=" 用户名不能为空!";
		if($("feifa").style.display=="none")
		$("feifa").style.display="inline";
		$("submit").disabled= true;
		return false;
	}
	
	$("feifa").style.display="none";
	$("submit").disabled= false;
	return true;
}

function unfocususerpwd(){
	//alert($("userpwd").value);
	$("checkuserpwd").style.display="none";
	if($("userpwd").value == ""){
		$("checkuserpwd").style.display="inline";
		$("checkuserpwd").innerHTML=" 请输入旧密码!";
		$("submit").disabled= true;
		return false;
	}
	$("submit").disabled= false;
	return true;
}
function unfocususerpwdnew(){
	//alert($("userpwd").value);
	$("checkuserpwdnew").style.display="none";
	$("pwdstrong").className="pw_power pw_mid";
	if($("userpwdok").value!=""){
		unfocususerpwdok();
	}
	if(bytelength($("userpwdnew").value)<=6||(bytelength($("userpwdnew").value)<=8
			 && (checkPassword2($("userpwdnew").value)==false )
			 || checkPassword3($("userpwdnew").value)==false))
	{
		$("pwdstrong").className="pw_power pw_ebb";
	}
	if($("userpwdnew").value == ""){
		$("checkuserpwdnew").style.display="inline";
		$("checkuserpwdnew").innerHTML=" 密码不能为空!";
		$("submit").disabled= true;
		$("userpwdok").disabled= true;
		return false;
	}
		
	if(bytelength($("userpwdnew").value)< secret()){
		$("checkuserpwdnew").style.display="inline";
		$("checkuserpwdnew").innerHTML=" 密码应≥"+secret()+"位字符";
		$("userpwdok").disabled= true;
		$("submit").disabled= true;
		return false;
	}
	
	if(bytelength($("userpwdnew").value)>20){
		$("checkuserpwdnew").style.display="inline";
		$("checkuserpwdnew").innerHTML=" 您输入的密码不合法";
		$("submit").disabled= true;
		$("userpwdok").disabled= true;
		return false;
	}
  if(checkPassword($("userpwdnew").value)==false){
		$("checkuserpwdnew").style.display="inline";
		$("checkuserpwdnew").innerHTML=" 您输入的密码不符合以下规则";
		$("submit").disabled= true;
		$("userpwdok").disabled= true;
		return false;
	}
  if($("userpwdnew").value!=$("userpwdok").value && $("userpwdnew").value!=""){

		$("submit").disabled= true;
		$("userpwdok").disabled= false;
		return false;
	}	   
	if(bytelength($("userpwdnew").value)>10){
		$("pwdstrong").className="pw_power pw_strong";
	}
	$("submit").disabled= false;
	$("userpwdok").disabled= false;
	return true;
}
function unfocususerpwdok(){
	$("checkuserpwdok").style.display="none";
	if(document.form1.userpwdok.value == ""){
		$("checkuserpwdok").style.display="inline";
		$("checkuserpwdok").innerHTML=" 确认密码不能为空!";
		$("submit").disabled= true;
		return false;
	}
	if($("userpwdnew").value!=$("userpwdok").value){
		$("checkuserpwdok").style.display="inline";
		$("checkuserpwdok").innerHTML=" 您输入的确认密码与新密码不符";
		$("submit").disabled= true;
		return false;
	}
	$("submit").disabled= false;
	return true;
}
function keyupuserpwdok(){
	$("checkuserpwdok").style.display="none";
	if($("userpwdnew").value!=$("userpwdok").value){
		$("checkuserpwdok").style.display="inline";
		$("checkuserpwdok").innerHTML=" 您输入的确认密码与新密码不符";
		$("submit").disabled= true;
		return false;
	}
	$("submit").disabled= false;
	return true;
}
function onfocusTopLevel(){
	$("level").style.display="inline";
	$("level").innerHTML=" 机密级--密码长度要求≥10位";
}
function onfocusLevel(){
	$("level").style.display="inline";
	$("level").innerHTML=" 秘密级--密码长度要求≥8位，有效期为30天";
}
function secret() 
{ 
  var temp=document.getElementsByName("secret"); 
  for (i=0;i<temp.length;i++){ 
  //遍历Radio 
    if(temp[i].checked){
      return temp[i].value; 
    } 
  } 
} 


</SCRIPT>
