<%@page import="com.cricket.T20Teams"%>
<%@page import="java.util.List"%>
<%@page import="com.cricket.T20TeamsDao"%>
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
		T20TeamsDao teamDao = new T20TeamsDao();
		//teamDao.createT20Team();
		List<T20Teams> teams = teamDao.retrieveAllT20TeamsRecords();
		
		for(T20Teams team : teams){
	%>
		<tr>
		<td><%= team.getT20teamranking() %></td>
		<td><%= team.getTeams().getTeamname() %></td>	
		<td><%= team.getT20capfname() %>&nbsp;<%= team.getT20caplname()%></td>
		<td><%= team.getT20coachfname() %>&nbsp;<%= team.getT20coachlname()%></td>
		</tr>

	<% } %>
			</table>
</body>
</html>