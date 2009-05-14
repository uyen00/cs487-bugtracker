<%@page import="com.gheewhiz.util.JspUtil"%>


<%@page import="com.gheewhiz.Bug"%>
<%@page import="java.util.Set"%>
<%@page import="com.gheewhiz.ProductCategory"%><jsp:include page="header.jsp" />
<% 
	ProductCategory product = JspUtil.getAttribute(request, "product", ProductCategory.class);
	Set<Bug> bugs = JspUtil.getAttribute(request, "bugs", Set.class);
%>
<h3><%=product.getName() %>[<%=product.getVersion() %>]</h3>
<h4>Bugs:</h4>
<table>
<tr>
	<td>ID</td>
	<td>Description</td>
	<td>State</td>
	<td>Resolution</td>
</tr>
<% for (Bug bug : bugs) { %>
	<tr>
		<td><a href="bug.html?bugId=<%=bug.getBugId() %>"><%=bug.getBugId() %></a></td>
		<td><%=bug.getShortdesc() %></td>
		<td><%=bug.getState() %></td>
		<td><%=bug.getResolution() %></td>
	</tr>
<% } %>
</table>

<h4>Create Bug:</h4>
<jsp:include page="footer.jsp" />