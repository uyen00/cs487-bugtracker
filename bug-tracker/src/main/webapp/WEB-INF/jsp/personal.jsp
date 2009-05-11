<jsp:include page="header.jsp" />
<%@page import="com.gheewhiz.Account"%>
<%@page import="com.gheewhiz.util.*"%>
<%@page import="java.util.List"%>


<h4>Account Information:</h4>
<table>
	<%
		Account account = (Account) (request.getSession()
				.getAttribute("account"));
	%>
	
	<tr>
		<td><span>Screen name: </td>
		<td><%=account.getScreenName()%></span></td>
	</tr>
</table>

<form method="post" action="update-password.html">
<h4>Change password:</h4>
<table border="0">
	<tr>
		<td>Old Password:</td>
		<td><input type="password" name="oldPassword" /></td>
	</tr>
	<tr>
		<td>New Password:</td>
		<td><input type="password" name="newPassword" /></td>
	</tr>
	<tr>
		<td>Verify New Password:</td>
		<td><input type="password
		ls" name="verifyNewPass" /></td>
	</tr>
</table>
<input type="submit" value="Update" /> 
<%
 	String message = JspUtil.getAttribute(request, "message",
 			String.class);
 	if (message != null) {
 %>
<h5><%=message%></h5>
<%
	}
%>
</form>

<jsp:include page="footer.jsp" />