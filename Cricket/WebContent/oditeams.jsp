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
<body>
<%
		OdiTeamsDao teamDao = new OdiTeamsDao();
		teamDao.createOdiTeam();
		List<OdiTeams> teams = teamDao.retrieveAllOdiTeamsRecords();
		
		for(OdiTeams team : teams){
	%>
		<p>
			<%= team.getOditeamranking() %>
			<%= team.getTeams().getTeamname() %>		
			<%= team.getOdicapfname() %>
			<%= team.getOdicaplname() %>
			<%= team.getOdicoachfname() %>
			<%= team.getOdicoachlname() %>
			
		</p>
	<% } %>
</body>
</html>