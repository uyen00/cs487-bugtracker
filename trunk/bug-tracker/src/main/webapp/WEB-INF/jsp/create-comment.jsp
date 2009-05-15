<%@page import="com.gheewhiz.util.JspUtil"%>

<jsp:include page="header.jsp" />
<form method="post">
<table>
<tr>
	<td>Comment</td>
	<td>
		<textarea name="comment"></textarea>
	</td>
</tr>
</table>
<input type="submit" value="Submit" />
</form>
<jsp:include page="footer.jsp" />