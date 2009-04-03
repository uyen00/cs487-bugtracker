<%@page import="com.gheewhiz.util.JspUtil"%>

<jsp:include page="header.jsp" />

<h2>Login Page</h2>
<h4>Please enter your login information</h4>
<form action="login.html" method="post">
<table>
	<tr>
		<td>Account Name:</td>
		<td><input type="text" name="screenName" /></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type="password" name="password"></td>
	</tr>
</table>
<% if(Boolean.TRUE.equals(JspUtil.getAttribute(request, "authFail", Boolean.class))) { %>
	<h5>Invalid Username or Password!</h5>
<% } %>
<input type="submit" value="submit" />
</form>

<jsp:include page="footer.jsp" />