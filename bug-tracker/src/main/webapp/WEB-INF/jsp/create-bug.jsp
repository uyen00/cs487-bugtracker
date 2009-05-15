<%@page import="com.gheewhiz.util.JspUtil"%>
<%@page import="java.util.List"%>

<% 
	List<String> resolutions = JspUtil.getAttribute(request, "resolutions", List.class);
	List<String> states = JspUtil.getAttribute(request, "states", List.class);
%>

<jsp:include page="header.jsp" />
<form method="post">
<table>
<tr>
	<td>Description</td>
	<td>
		<textarea name="shortdesc"></textarea>
	</td>
</tr>
<tr>
	<td>Steps to Repeat</td>
	<td><textarea name="steps"></textarea></td>
</tr>
<tr>
	<td>State: </td>
	<td>
		<select name="state">
		<% for (String state : states) { %>
			<option value="<%=state %>"><%=state %></option>
		<% }  %>
		</select>
	</td>
</tr>
<tr>
	<td>Resolution: </td>
	<td>	
		<select name="resolution">
		<% for (String resolution : resolutions) { %>
			<option value="<%=resolution %>"><%=resolution %></option>
		<% }  %>
		</select>
	</td>
</tr>
</table>
<input type="submit" value="Submit" />
</form>
<jsp:include page="footer.jsp" />