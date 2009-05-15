<%@page import="com.gheewhiz.util.JspUtil"%>
<%@page import="java.util.List"%>

<% 
	List<Integer> productids = JspUtil.getAttribute(request, "productids", List.class);
	List<Integer> qaids = JspUtil.getAttribute(request, "qaids", List.class);
%>

<jsp:include page="header.jsp" />

<b>Enter Developer and Product Information</b>
<form method="POST" action="add-qaprod.html">
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
		<select name="qaId">
		<% for (Integer qaId : qaids) { %>
			<option value="<%=qaId %>"><%=qaId %></option>
		<% }  %>
		</select>
	</td>	
</tr>
</table>
<input type="submit" value="Submit" />
</form>
<jsp:include page="footer.jsp" />