<%@page import="com.gheewhiz.util.JspUtil"%>
<%@page import="com.gheewhiz.Bug"%>
<%@page import="com.gheewhiz.Comment"%>

<%
	Bug bug = JspUtil.getAttribute(request, "bug", Bug.class);
%>

<jsp:include page="header.jsp" />

<table>
	<tr>
		<td>ID</td><td>State</td><td>Resolution</td><td>Opened</td><td>QA ID</td><td>Dev ID</td>
	</tr>
	<tr>
		<td><%= bug.getBugId() %></td>
		<td><%= bug.getState() %></td>
		<td><%= bug.getResolution() %></td>
		<td><%= bug.getOpened() %></td>
		<td><%= bug.getQAId() %></td>
		<td><%= bug.getDevId() %></td>
	</tr>
</table>

<h5>Short Description: </h5>
<%= bug.getShortdesc() %>

<h5>Steps to Reproduce: </h5>
<%= bug.getSteps() %>

<h4>Comments: </h4>
<%for (Comment comment : bug.getComments()) { %> 
	<hr />
	Comment [<%=comment.getCommentId()%>] By: <%= comment.getCommenter().getScreenName() %>	
	<hr />
	<%=comment.getComment()%>

<% } %>
<br />
<a href="create-comment.html?bugId=<%=bug.getBugId() %>"><b>New Comment</b></a>
<jsp:include page="footer.jsp" />