<%@page import="edu.iit.cs.util.JspUtil"%>

<%@page import="java.util.List"%><jsp:include page="header.jsp" />

<h4>Moderator that closed the most tickets in a given date range:</h4>
<b>Moderator(s): </b><br />
<% for (Object o : JspUtil.getAttribute(request, "moderatorticket", List.class)) { 
		String result = (String)o;
%>
	
		<%= o %><br />
	
<%} %>

<jsp:include page="footer.jsp"/>
