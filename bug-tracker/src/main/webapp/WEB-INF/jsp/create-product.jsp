<%@page import="com.gheewhiz.util.JspUtil"%>

<jsp:include page="header.jsp" />

<b>Enter Product Information</b>
<form method="POST" action="create-product.html">
<table>
<tr><td>Name: </td></tr><tr><td><input type="text" name="name" /></td></tr>
<tr><td>Version: </td></tr><tr><td><input type="text" name="version" /></td></tr>
<tr><td>Manager: </td></tr><tr><td></td></tr>
</table>
</form>
<jsp:include page="footer.jsp" />