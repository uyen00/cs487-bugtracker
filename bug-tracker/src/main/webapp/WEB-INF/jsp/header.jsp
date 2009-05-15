
<%@page import="com.gheewhiz.util.JspUtil"%>
<%@page import="com.gheewhiz.Account"%>

<html>
<link rel="stylesheet" media="screen,projection" type="text/css" href="./css/main.css" />
<body>
<script language="JavaScript">
function onlyNumbers(evt)
{
	var e = event || evt;
	var charCode = e.which || e.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;
	return true;
}
</script>
<div id="main" class="box">
<div id="header">
<h1 id="logo"><a href="./bug-tracker.html"
	title="Welcome to the GheeWhiz Bug Tracker [Go to homepage]">GheeWhiz Bug Tracking System<span /></a></h1>
<hr class="noscreen" />
</div>

<div id="tabs" class="noprint">

<h3 class="noscreen">Navigation</h3>
<ul class="box">
	<li><a href="./bug-tracker.html">Home<span class="tab-l"></span><span
		class="tab-r"></span></a></li>
	<%
		Account account = JspUtil.getAccount(request);
		if (account != null) { 
			if(account.isEntitledWIthAdmin()) {
	%>
	<li><a href="./moderate.html">Moderate<span class="tab-l"></span><span
		class="tab-r"></span></a></li>
	<% } %>
	<li><a href="./list-users.html">Reports<span class="tab-l"></span><span
		class="tab-r"></span></a></li>	
	<li><a href="./products.html">Products<span class="tab-l"></span><span
		class="tab-r"></span></a></li>
	<li><a href="./personal.html">Account Info<span class="tab-l"></span><span
		class="tab-r"></span></a></li>
	<li><a href="./logout.html">Logout <%= account.getScreenName() %><span class="tab-l"></span><span
		class="tab-r"></span></a></li>
	<% } else { %>
	<li><a href="./login.html">Login<span class="tab-l"></span><span
		class="tab-r"></span></a></li>
	<% } %>
</ul>

<hr class="noscreen" />
</div>
<div id="page-in" class="box">
<div id="content">