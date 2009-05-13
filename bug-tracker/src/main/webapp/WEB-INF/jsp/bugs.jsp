<%@page import="com.gheewhiz.util.JspUtil"%>


<%@page import="com.gheewhiz.Bug"%>
<%@page import="java.util.Set"%>
<%@page import="com.gheewhiz.ProductCategory"%><jsp:include page="header.jsp" />
<% 
	ProductCategory product = JspUtil.getAttribute(request, "product", ProductCategory.class);
	Set<Bug> bugs = JspUtil.getAttribute(request, "bugs", Set.class);
%>
<b><%=product.getName() %>[<%=product.getVersion() %>]</b>
Bugs:

Create Bug:
<jsp:include page="footer.jsp" />