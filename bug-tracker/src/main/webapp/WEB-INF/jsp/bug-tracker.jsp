<%@page import="java.util.List"%>
<%@page import="com.gheewhiz.util.JspUtil"%>
<%@page import="java.util.Map"%>

<%@page import="com.gheewhiz.Account"%><jsp:include page="header.jsp" />

<%
	Account account = (Account)(request.getSession().getAttribute("account"));  
%>
<jsp:include page="footer.jsp" />