<%@page import="com.gheewhiz.util.JspUtil"%>
<%@page import="java.util.List"%>

<% 
	List<Integer> managerids = JspUtil.getAttribute(request, "managerids", List.class);
%>

<jsp:include page="header.jsp" />

<b>Enter Product Information</b>
<form method="POST" action="create-product.html">
<table>
<tr><td>Name: </td></tr><tr><td><input type="text" name="name" /></td></tr>
<tr><td>Version: </td></tr><tr><td><input type="text" name="version" /></td></tr>
<tr>
	<td>Manager: </td>
	<td>
		<select name="managerId">
		<% for (Integer managerId : managerids) { %>
			<option value="<%=managerId %>"><%=managerId %></option>
		<% }  %>
		</select>
	</td>	
</tr>
</table>
<input type="submit" value="Submit" />
</form>
<jsp:include page="footer.jsp" />