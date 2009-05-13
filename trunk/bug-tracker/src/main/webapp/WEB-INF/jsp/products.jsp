<%@page import="com.gheewhiz.util.JspUtil"%>


<%@page import="com.gheewhiz.ProductCategory"%>
<%@page import="java.util.Set"%>

<jsp:include page="header.jsp" />
<%
	Set<ProductCategory> products = JspUtil.getAttribute(request, "products", Set.class);
%>
	
<b>Choose Product:</b>
<table>
<% for (ProductCategory product : products) { %>
	<tr><td><a href="bugs.html?productId=<%=product.getProductCategoryId() %>">
		<%= product.getName() + "[" + product.getVersion() + "]" %>
	</a></td></tr>
<% } %>
</table>
<jsp:include page="footer.jsp" />