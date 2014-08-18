<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.cricket.UpcomingMatchDao"%>
<%@page import="com.cricket.UpcomingMatch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <title></title>
    <script src="javascript/display-live-scores.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="css/main-style.v6.css">
    <link rel="shortcut icon" href="http://3.bp.blogspot.com/-sl_Lg2Yt1JE/TZd7FIafc-I/AAAAAAAABgE/GY3F7EG1tYs/s1600/Cricket+World+Cup.png">
    <link rel="stylesheet" type="text/css" href="css/menu-style.css" />
</head>
<style>
 body 
 {
     font-family: 'Arial';
     color:black;
     font-size:15px;
     
 }
 table
 {
 		border: 2px solid #313D9A;
	    border-top: 0!important;
	    background-color: #EAC535;
	    color: #49370A;
	    float: inherit;
	    margin-top:0px;
	    width:50%;
	    margin-left:25%;
	    margin-right:25%;
 }
    </style>
<body>
   <div class="logo">
        <img width="300" height="130" src="images/Logo.gif" />
   </div>
   <div id="menu">

    <ul class="menu">
        <li class="current"><a href="crickethome.jsp"><span>Home</span></a></li>
        <li><a href="crickethome.jsp" class="parent" target="_blank"><span>ManageAccount</span></a>
        	<ul class="dropDown">
        		<li><a href = "login.jsp" target = "_blank"><span>Login</span></a></li>
        		<li><a href = "signup.jsp" target = "_blank"><span>SignUp</span></a></li>
        		<%if(session.getAttribute("login")!=null){ %>
        			<li><a href = "Signout.jsp" target = "_self"><span>Signout</span></a></li>
        		<%} %>
        		<%if(session.getAttribute("admin")!=null){ %>
        			<li><a href="admin.jsp" class="parent" target="_blank" ><span>Admin</span></a></li>
        		<%} %>
        		
        	</ul>
        </li>
        <li><a href="crickethome.jsp" class="parent" target="_blank"><span>Matches</span></a>
        	<ul class="dropDown">
        		<li><a href = "upcomingmatch.jsp" target = "_blank"><span>Upcoming</span></a></li>
        		<li><a href = "archivedmatch.jsp" target = "_blank"><span>Archived</span></a></li>
        		
        	</ul>
        </li>
         <li><a href="crickethome.jsp" class="parent" target="_blank"><span>Statistics</span></a>
        	<ul class="dropDown">
        		<li><a href="teams.jsp" class="parent" target="_blank"><span>Teams</span></a></li>
        		 <li><a href="player.jsp" class="parent" target="_blank"><span>Players</span></a></li>
        		
        	</ul>
        </li>
        
        <li><a href="NEWS.jsp" class="parent" target="_blank"><span>News</span></a></li>
        <%if(session.getAttribute("login")!=null){ %>
        			<li><a href = "usercomments.jsp" target = "_self"><span>Comments</span></a></li>
        <%} %>
        <li><a href="WorldCupHistory.jsp" class="parent" target="_blank" ><span>World Cup!</span></a></li>
        
     </ul>
    </div>

    <table border="1" width="50%">
<tr>
		<th><b>Match Date</b></th>
		<th><b>Match Type</b></th>
		<th><b>Match Series</b></th>
		<th><b>Match Venue</b></th>
		<th><b>Team 1</b></th>
		<th><b>Team 2</b></th>
		</tr>
<%
		UpcomingMatchDao matchDao = new UpcomingMatchDao();
		//matchDao.createUpcomingMatch();
		List<UpcomingMatch> matches = matchDao.retrieveAllUpcomingMatches();
		
		for(UpcomingMatch match : matches){
			Date dateobj = new Date();
			if (match.getMatchdate().after(dateobj)){
	%>
		<tr>
		<td><%= match.getMatchdate()%></td>
		<td><%= match.getMatchtype() %></td>	
		<td><%= match.getMatchseries() %></td>
		<td><%= match.getMatchvenue()%></td>
		<td><%= match.getMatchteam1() %></td>
		<td><%= match.getMatchteam2()%></td>
		</tr>

	<%} 
	} %>
			</table>
			</body>
			</html>