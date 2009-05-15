<%@page import="com.gheewhiz.util.JspUtil"%>
<%@page import="com.gheewhiz.Account"%>
<%@page import="java.util.Set"%>

<%
	Set<Account> accounts = JspUtil.getAttribute(request, "accounts", Set.class);
%>

<jsp:include page="header.jsp" />

<table>
	<tr>
		<td>ID</td><td>screenname</td>
	</tr>
	<% for (Account account : accounts) { %>
	<tr>
		<td><%=account.getAccountId() %></td>
		<td><%=account.getScreenName() %></td>
	</tr>
<% } %>
</table>
<jsp:include page="footer.jsp" />