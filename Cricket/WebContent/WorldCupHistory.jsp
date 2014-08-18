<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%@ page import="com.cricket.*, java.util.*;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main-style.v6.css">
<link rel="stylesheet" type="text/css" href="css/rankings.css" />
<link rel="stylesheet" type="text/css" href="css/newsPanel.v5.css" />
<link rel="stylesheet" type="text/css" href="css/menu-style.css" />
<title>WorldCup History</title>
<link rel="shortcut icon" href="http://3.bp.blogspot.com/-sl_Lg2Yt1JE/TZd7FIafc-I/AAAAAAAABgE/GY3F7EG1tYs/s1600/Cricket+World+Cup.png">
<link rel="stylesheet" type="text/css" href="../css/documentation.css">
</head>
<style>

 
 table
 {
 		border: 2px solid #313D9A;
	    border-top: 0!important;
	    background-color: #EAC535;
	    color: #49370A;
	    float: inherit;
	    margin-top:-50px;
	    width:50%;
	    margin-left:25%;
	    margin-right:25%;
 }
</style>


</head>

<body onload="getContent('WorldCupHistory')">
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

    
    <br><br><br>
<table border="1" width="100%" align=center>
<tr>
		<th><b>Host</b></th>
		<th><b>Date</b></th>
		<th><b>Status</b></th>
		<th><b>Team1</b></th>
	    <th><b>Team2</b></th>
	    <th><b>Man Of The Match</b></th>
	    <th><b>Man Of The Series</b></th>
		</tr>
<%
		WorldCupHistoryDao wchdao = new WorldCupHistoryDao();
		//wchdao.parseCSVFileLineByLine();
		List<Worldcuphistory> wch = wchdao.readData();
		
		for(Worldcuphistory worldch : wch){
	%>
		<tr>
		<td><%= worldch.getWorldcuphistoryhost() %></td>
		<td width = "10%"><%= worldch.getWorldcuphistorydate() %></td>	
		<td><%= worldch.getWorldcuphistorystatus() %></td>
		<td><%= worldch.getWorlcuphistoryteam1()%></td>
		<td><%= worldch.getWorldcuphistoryteam2() %></td>
		<td><%= worldch.getWorldcuphistorymom()%></td>
		<td><%= worldch.getWorldcuphistorymos()%></td>
		</tr>

	<% } %>
			</table>



      
         </body>	


</html>