<%@page import="com.gheewhiz.util.JspUtil"%>
<%@page import="java.util.List"%>

<% 
	List<Integer> productids = JspUtil.getAttribute(request, "productids", List.class);
	List<Integer> devids = JspUtil.getAttribute(request, "devids", List.class);
%>

<jsp:include page="header.jsp" />

<b>Enter Developer and Product Information</b>
<form method="POST" action="add-devprod.html">
<table>
<tr>
	<td>Product: </td>
	<td>
		<select name="productId">
		<% for (Integer productId : productids) { %>
			<option value="<%=productId %>"><%=productId %></option>
		<% }  %>
		</select>
	</td>	
</tr>
<tr>
	<td>Developer: </td>
	<td>
		<select name="devId">
		<% for (Integer devId : devids) { %>
			<option value="<%=devId %>"><%=devId %></option>
		<% }  %>
		</select>
	</td>	
</tr>
</table>
<input type="submit" value="Submit" />
</form>
<jsp:include page="footer.jsp" />