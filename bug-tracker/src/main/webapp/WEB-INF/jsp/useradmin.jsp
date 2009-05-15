
<%@page import="java.util.Map"%>

<%@page import="com.gheewhiz.Entitlement"%>
<%@page import="com.gheewhiz.Account"%>
<%@page import="com.gheewhiz.util.JspUtil"%>
<jsp:include page="header.jsp" />
 <% 
 	Account newUser = JspUtil.getAttribute(request, "newUser", Account.class);
 %>
<hr />
<form method="post">
	<h4>Create an Account</h4>
	<table>
		<tr><td>Screen Name</td><td><input type="text" name="screenName" /></td></tr>
		<tr><td>Password</td><td><input type="password" name="password" /></td></tr>
		<tr>
			<td>Entitlement</td>
			<td>
				<select name="entitlement">
				<option value="<%= Entitlement.ADMIN.getType() %>">Admin</option>
				<option value="<%= Entitlement.DEVELOPER.getType() %>">Developer</option>
				<option value="<%= Entitlement.QA.getType() %>">QA</option>
				<option value="<%= Entitlement.MANAGER.getType() %>">Manager</option>
				</select>
			</td>
		</tr>
	</table>
	<input type="submit" value="Create" />
	<% if (newUser != null) { %>
		User <%= newUser.getScreenName() %> successfully created.
	<% } %>
</form>
<hr />
<jsp:include page="footer.jsp" />