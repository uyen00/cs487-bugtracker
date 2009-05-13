<%@page import="com.gheewhiz.util.JspUtil"%>


<%@page import="com.gheewhiz.ProductCategory"%>
<%@page import="java.util.Set"%>

<jsp:include page="header.jsp" />
<%
	Set<ProductCategory> products = JspUtil.getAccount(request);
	if (account != null) { 
		if(account.isEntitledWIthAdmin()) {
%>
	
<b>Choose Product:</b>
<% for (ProductCategory product :  %>
<jsp:include page="footer.jsp" />