<%@page import="com.cricket.OdiTeams"%>
<%@page import="java.util.List"%>
<%@page import="com.cricket.OdiTeamsDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
 table, body 
 {
     font-family: 'Arial';
     color:black;
     font-size:15px;
     left:20px;
 }
    </style>
<body>
<table border="1" width="50%">
<tr>
		<th><b>Rank</b></th>
		<th><b>Team</b></th>
		<th><b>Captain</b></th>
		<th><b>Coach</b></th>
		</tr>
<%
		OdiTeamsDao teamDao = new OdiTeamsDao();
		//teamDao.createOdiTeam();
		List<OdiTeams> teams = teamDao.retrieveAllOdiTeamsRecords();
		
		for(OdiTeams team : teams){
	%>
		<tr>
		<td><%= team.getOditeamranking() %></td>
		<td><%= team.getTeams().getTeamname()%></td>	
		<td><%= team.getOdicapfname() %>&nbsp;<%= team.getOdicaplname()%></td>
		<td><%= team.getOdicoachfname() %>&nbsp;<%= team.getOdicoachlname() %></td>
		</tr>

	<% } %>
			</table>
</body>
</html>