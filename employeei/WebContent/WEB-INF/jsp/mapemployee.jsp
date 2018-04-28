<%@ page
	import="java.lang.*,java.io.*,java.util.*,java.sql.*,employeei.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Map Employee</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="css/paging.css" rel="stylesheet" type="text/css" />
<link href="css/header.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/jquery.table.hpaging.min.js"></script>
<script>
  function setapply(i)
  {
	  var objHidden = document.getElementById("apply"+i);
	      objHidden.value = 1; 
  }
  
</script>
<style type="text/css">
 .topcorner{
   position:absolute;
   top:0;
   right:0;
  }
</style>
</head>
<body>
	<B>EMPLOYEE DB</B><div class="topcorner"><a href="login">Logout</a>&nbsp;&nbsp;&nbsp;</div>
    <jsp:include page="header.jsp" />
	<BR>

	<%
	    HttpSession session1 = request.getSession(true);
	    String pg = request.getParameter("pglmt");
	    String status = (String)session1.getAttribute("status");
		String strack = (String)session1.getAttribute("strack");
	    if (strack == null) {session1.setAttribute("loginstatus","Invalid Session...!");
		                     //New location to be redirected
	                         String site = new String("login");
	                         response.setStatus(response.SC_MOVED_TEMPORARILY);
	                         response.setHeader("Location", site);}
	    else {
	    if (strack.equals(session1.getId())) {String ignore=null;}
	    else { session1.setAttribute("loginstatus","Invalid Session...!");
	    	//New location to be redirected
	        String site = new String("login");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);}
	    }
	    
	    if (status != null) {
		out.println("<B><U>Map Employee</U></B>" + 
				"<center><span style='color:blue;'>" + status + "</span></center><BR><BR>");
		      session1.removeAttribute("status"); }
	    else {
	    out.println("<B><U>Map Employee</U></B>" + 
					"<BR><BR>");	
	    }
	%>
	
	<sql:setDataSource var="xesrc" driver="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@(DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = EPINHYDW0404.princeton.epam.com)(PORT = 1521))
    (CONNECT_DATA =
      (SERVER = DEDICATED)
      (SERVICE_NAME = XE)
    )
    )"
	user="sample" password="sample" />
	
	<sql:query dataSource="${xesrc}" var="emp"> 
    select a.empid,a.empname from employee a order by a.empid
    </sql:query>
    
    <sql:query dataSource="${xesrc}" var="dept"> 
    select a.deptid,a.deptname from department a order by a.deptid
    </sql:query>

	<form action="AddMapemployee" method="post" name="form1"
		style="border: solid 1px #000000; width: 600px; background-color: #f48941;">
		<table style="width:100%">
		    <thead>
			<tr>
				<th align="right" ><input type="submit" value="Add" /></th>
			</tr>
			</thead>
			<tbody>
			<tr><td align="left">
			<table>
			<tr>
				<td align="right"><Span
					style="font-weight: bold; color: black; padding: 12px 20px;">
						Employee Name : </Span></td>
				<td><select name="empid">
				<c:forEach var="e" items="${emp.rows}">
				<option value="${e.empid}"><c:out value="${e.empname}" /></option>
				</c:forEach></select></td><td></td>
			</tr>
			<tr>
				<td align="right"><Span
					style="font-weight: bold; color: black; padding: 12px 20px;">
						Department Name : </Span></td>
				<td><select name="deptid">
				<c:forEach var="d" items="${dept.rows}">
				<option value="${d.deptid}"><c:out value="${d.deptname}" /></option>
				</c:forEach></select></td><td></td>
			</tr>
			</table>
			</td></tr>
			</tbody>
		</table>
	</form>

	<form name="pg" method="post">
	<table>
            <tr>
                <td>
                    <input name="pglmt" id="pglmt" placeholder="Page Limit" title="Page Limit" value="<%if (pg != null) {out.print(pg);} %>" class="form-control">
                </td>
                <td>
                    <button id="btnApply" class="btn btn-danger">Go</button>
                </td>
                <td>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Search: <input type="text" id="myInput" placeholder="Search String" onkeyup="doSearch()" size=50/>
                </td>
            </tr>
    </table>
    </form>
        
	<form action="MapemployeeGrid" method="post" name="form2">
		<table id="table2" class="table table-bordered table-striped" border="1" cellpadding="0" cellspacing="0"
			width=900px height=100px
			style="border-collapse: collapse; overflow: scroll;">
			<thead>
			<tr>
				<th colspan="5" align="right"
					style="padding: 3px 3px; background-color: #111;"><input
					type="submit" name="submit" Value="Delete" />&nbsp;&nbsp; <input
					type="submit" name="submit" Value="Apply Changes" /></th>
			</tr>
			<tr style="background-color: #f48941;">
				<th width=5%></th>
				<th width=10%>Emp ID</th>
				<th width=30%>Employee Name</th>
				<th width=15%>Dept ID</th>
				<th width=30%>Department Name</th>
			</tr>
			</thead>
			<%int i = 0; %>
            <tbody>
			<c:forEach var="m" items="${mapemployeelist}">
			<%
				i++;
			%>
			<tr>
				<td valign="top"><input type="checkbox"
					name="check<%out.print(i);%>" value="1" /> <input type="hidden"
					id="apply<%out.print(i);%>" name="apply<%out.print(i);%>" value="0" /></td>
				<td valign="top"><input type="hidden"
					name="erid<%out.print(i);%>" value="${m.empdeptid}" />
					<c:out value="${m.empid}" /></td>
				<td valign="top"><select name="empid<%out.print(i);%>" onchange="setapply(<%out.print(i);%>);" >
				<c:forEach var="e" items="${emp.rows}">
				<option value="${e.empid}" <c:if test = "${m.empid == e.empid }"><c:out value="selected" /></c:if>><c:out value="${e.empname}" /></option>
				</c:forEach></select></td>
				<td valign="top">
				 	<c:out value="${m.deptid}" />
				</td>
				<td valign="top"><select name="deptid<%out.print(i);%>" onchange="setapply(<%out.print(i);%>);" >
				<c:forEach var="d" items="${dept.rows}">
				<option value="${d.deptid}" <c:if test = "${m.deptid == d.deptid }"><c:out value="selected" /></c:if>><c:out value="${d.deptname}" /></option>
				</c:forEach></select></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<input type="hidden" name="nrows" value="<%out.print(i);%>" />
	</form>
<script>
//Pagination
 $(function () {
    if ($("#pglmt").val() == null || $("#pglmt").val() == "") {
    $("#table2").hpaging({"limit" : 10});}
    else {$("#table2").hpaging({"limit" : document.pg.pglmt.value});}
});

 function doSearch() {
	  // Declare variables 
	  var input, filter, table, tr, td, i,k;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("table2");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
		k=0;
		for (j=1;j < 4;j++) {
	    td = tr[i].getElementsByTagName("td")[j];
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        k=1;
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	      if (k==1) {tr[i].style.display = "";}
		}
	  }
	}

</script>
</body>
</html>