
<%@page import="edu.iit.cs.util.JspUtil"%>
<%@page import="java.util.Map"%>
<%@page import="edu.iit.cs.Member"%>
<%@page import="edu.iit.cs.Account"%><jsp:include page="header.jsp" />
 <% 
 	Map<Integer, String> screenNameMap = JspUtil.getAttribute(request, "screenNameMap", Map.class);
	Map<Integer, String> emailMap = JspUtil.getAttribute(request, "emailMap", Map.class);
 %>
 <hr />
<form action="createmember.html" method="post">
	<h4>Create a Member</h4>
	<table>
		<tr><td>Email Address</td><td><input type="text" name="email" /></td></tr>
		<tr><td>First Name</td><td><input type="text" name="firstName" /></td></tr>
		<tr><td>Last Name</td><td><input type="text" name="lastName" /></td></tr>
		<tr>
			<td>Date of Birth</td>
			<td>
				Month:
				<select name="month">
					<option value="JAN">JAN</option>
					<option value="FEB">FEB</option>
					<option value="MAR">MAR</option>
					<option value="APR">APR</option>
					<option value="JUN">JUN</option>
					<option value="JUL">JUL</option>
					<option value="AUG">AUG</option>
					<option value="SEP">SEP</option>
					<option value="OCT">OCT</option>
					<option value="NOV">NOV</option>
					<option value="DEC">DEC</option>
				</select>
				Day:
				<input type="text" name="day" size="2" />
				Year:
				<input type="text" name="year" size="4" />
			</td>
		</tr>
		<tr><td>Zip</td><td><input type="text" name="zip" /></td></tr>
		<tr>
			<td>EmailAddress</td>
			<td>
				<input type="radio" name="gender" value="M" checked="checked" /> Male
				<input type="radio" name="gender" value="F" /> Female
			</td>
		</tr>
	</table>
	<input type="submit" value="Create" />
</form>
<hr />
<form action="createaccount.html" method="post">
	<h4>Create an Account</h4>
	<table>
		<tr><td>Screen Name</td><td><input type="text" name="screenName" /></td></tr>
		<tr><td>Password</td><td><input type="password" name="password" /></td></tr>
		<tr>
			<td>Member Type</td>
			<td>
				<select name="memberType">
				<option value="<%= Account.GUEST %>">Guest</option>
				<option value="<%= Account.GENERAL %>">General</option>
				<option value="<%= Account.MODERATOR %>">Moderator</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Member Email</td>
			<td>
				<select name="memberId">
				<% for (Map.Entry<Integer, String> entry : emailMap.entrySet()) {  %>
					<option value="<%= entry.getKey() %>"><%=entry.getValue() %></option>
				<% } %>
				</select>
			</td>
		</tr>
	</table>
	<input type="submit" value="Create" />
</form>
<hr />
<jsp:include page="footer.jsp" />