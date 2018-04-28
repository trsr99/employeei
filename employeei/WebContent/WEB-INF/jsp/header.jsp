<ul>
	<li><a href="index">Dashboard</a></li>
	<li><a href="employee">Employee</a></li>
	<li><a href="department">Department</a></li>
	<li><a href="mapemployee">Map Employee</a></li>
	<li><a href="miscellaneous">Miscellaneous</a></li>
	<%HttpSession session1 = request.getSession(true);
	String email = (String)session1.getAttribute("userid");
	if (email.equals("admin")) {
	out.print("<li><a href='profile'>User Profile</a></li>");
	} %>
</ul>