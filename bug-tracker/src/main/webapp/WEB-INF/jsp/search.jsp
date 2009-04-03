
<jsp:include page="header.jsp" />


<h4>Find a producer for a given video Q5<h4>
<table>
<tr>
<form action="findproducer.html" method="POST">
Video ID: <input type = text name = "videoId"
onkeypress = return onlyNumbers();>
<br><input type = "submit" value = "Find" />
</form>
</tr>
</table>
<hr />

<b>Find the rating of a video from its title Q6</b>
<table>
<tr>
<form action="findrating.html" method="POST">
Title: <input type = text name = "title"
onkeypress = return onlyNumbers();>
<br><input type = "submit" value = "Find" />
</form>
</tr>
</table>
<hr />

<b>Find the average review of a video given its rating and/or producer Q7</b>
<table>
<tr>
<form action="findaveragereview.html" method="POST">
<td><b>Rating:</b></td><td> <input type = text name = "rating"
onkeypress = return onlyNumbers();></td>

<tr><td><b>Producer ID: </b></td><td> <input type = text name = "producerId"
onkeypress = return onlyNumbers();></td></tr>
<br><input type = "submit" value = "Find" />
</form>
</tr>
</table>
<hr />

<b>Find amount of each type of videos for each type of rating Q10</b>
<table>
<tr>
<form action="previousproducer.html" method="POST">
Producer ID: <input type ="text" name ="producerId"
onkeypress = return onlyStrings(); />
<br><input type = "submit" value = "Find" />
</form>
</tr>
</table>
<hr />

<b>Which videos have never been reviewed for a given producer and all producers Q11</b>
<table>
<tr>
<form action="neverreviewed.html" method="POST">
Producer ID: <input type = text name = "producerId"
onkeypress = return onlyNumbers();>
<br><input type = "submit" value = "Find" />
</form>
</tr>
</table>
<hr />

<b>Videos viewed by all accounts associated with email. Q13</b>
<table>
<tr>
<form action="neverreviedemail.html" method="POST">
Email Address: <input type ="text" name ="emailAddress"
onkeypress = return onlyStrings(); />
<br><input type = "submit" value = "Find" />
</form>
</tr>
</table>
<hr />

<b>Find Average Review Of Videos (Produer Id Optional). Q15</b>
<form action="findaveragereview.html" method="POST">
Producer ID: <input type ="text" name ="producerId"
onkeypress = return onlyStrings(); />
<br><input type = "submit" value = "Find" />
</form>
<hr />

<b>Previous rating flag data. Q16</b>
<form action="moderatorclose.html" method="POST">
<table>
<tr><td><b>Start Day</b></td><td><input type="text" name="startDay" /></td></tr>
<tr><td><b>Start Month</b></td><td>
<select name="startMonth">
	<option value="JAN">JAN</option>
	<option value="FEB">FEB</option>
	<option value="MAR">MAR</option>
	<option value="APR">APR</option>
	<option value="JUN">JUN</option>
	<option value="JUL">JUL</option>
	<option value="AUG">AUG</option>
	<option value="SEP">SEP</option>
	<option value="OCT">OCT</option>
	<option value="NOV">NOV</option>
	<option value="DEC">DEC</option>
</select>
</td></tr>
<tr><td><b>Start Year</b></td><td><input type="text" name="startYear" /></td></tr>
<tr><td><b>End Day</b></td><td><input type="text" name="endDay" /></td></tr>
<tr><td><b>End Month</b></td><td>
<select name="endMonth">
	<option value="JAN">JAN</option>
	<option value="FEB">FEB</option>
	<option value="MAR">MAR</option>
	<option value="APR">APR</option>
	<option value="JUN">JUN</option>
	<option value="JUL">JUL</option>
	<option value="AUG">AUG</option>
	<option value="SEP">SEP</option>
	<option value="OCT">OCT</option>
	<option value="NOV">NOV</option>
	<option value="DEC">DEC</option>
</select>
</td></tr>
<tr><td><b>End Year</b></td><td><input type="text" name="endYear" /></td></tr>
</table>
<br><input type = "submit" value = "Find" />
</form>

<hr />

<b>Previous rating flag data. Q17</b>
<form action="previousratings.html" method="POST">
Video ID: <input type ="text" name ="videoId"
onkeypress = return onlyStrings(); />
<br><input type = "submit" value = "Find" />
</form>

<jsp:include page="footer.jsp" />