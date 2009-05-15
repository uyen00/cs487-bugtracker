<%@page import="com.gheewhiz.util.JspUtil"%>


<%@page import="com.gheewhiz.ProductCategory"%>
<%@page import="com.gheewhiz.Account"%>
<%@page import="java.util.Set"%>

<jsp:include page="header.jsp" />
<%
	Set<ProductCategory> products = JspUtil.getAttribute(request, "products", Set.class);
%>
	
<b>Choose Product:</b>
<table>
<tr>
	<td>ID</td>
	<td>Product</td>
</tr>
<% for (ProductCategory product : products) { %>
	<tr>
	    <td><%=product.getProductCategoryId() %></td>
	    <td>
	    	<a href="bugs.html?productId=<%=product.getProductCategoryId() %>">
			<%= product.getName() + "[" + product.getVersion() + "]" %>
			</a>
			<td>
			Dev ID
			<% Set<Account> accounts = product.getDevelopers(); %>
			<% for (Account dev : accounts) { %>
			<%= dev.getAccountId() %>
			<% } %>
			</td>
			<td>
			QA ID
			<% Set<Account> accountsa = product.getQA(); %>
			<% for (Account qa : accountsa) { %>
			<%= qa.getAccountId() %>
			<% } %>
			</td>
			<td>
			Manager ID
			<%= product.getManager().getAccountId() %>
		    </td>
		</td>
	</tr>
<% } %>
</table>
<a href="create-product.html"><b>New Product</b></a><br>
<a href="add-devprod.html"><b>Add Developer to Product</b></a><br>
<a href="add-qaprod.html"><b>Add QA to Product</b></a><br>
<jsp:include page="footer.jsp" />