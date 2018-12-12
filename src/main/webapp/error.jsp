<%@ page contentType="text/html;charset=utf-8"%>
<%@ page isErrorPage="true"%>
<html><title>Error occured!</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="expires" content="fri,30 dec 1999 00:00:00 gmt">
		<meta name="author" content="fredwebs@sina.com">
	
	<body bgcolor="#999999" style="margin:0;">
		<table border="0" cellspacing="0" cellpadding="0" width="778" height="47%" align=center style="border-right:1 solid black;border-left:1 solid black;">
			<tr>
				<td valign=top bgcolor="#D6EAFF">
					<table border="0" cellspacing="0" cellpadding="0" width="100%" height="80%">
						<tr><td height="11" bgcolor="#ffffff"></td></tr>
						<tr>
							<td style="padding-top:20;" valign=top>
								<div ></div>
								<br><br><br><br><br>
									<table border="0" cellspacing="1" cellpadding="5" width="500" align=center bgcolor="#3A6EA5">
										<tr>
											<td style="color:white;">
												对不起，系统运行出错！<br>
												<%=exception.toString()%><BR><BR>
												Please <A HREF="javascript:history.back(-1);" style="text-decoration:underline;"><FONT COLOR="#333333">go back</FONT></A>.
											</td>
										</tr>
									</table>
								<BR><BR>
							</td>
						</tr>


					</table>
			  </td>
				<td width="11" bgcolor="#ffffff"></td>
				<td width="132" background=""></td>
			</tr>
	</table>
	</body>
</html>