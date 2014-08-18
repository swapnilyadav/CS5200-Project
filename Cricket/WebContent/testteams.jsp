<%@page import="com.cricket.TestTeams"%>
<%@page import="java.util.List"%>
<%@page import="com.cricket.TestTeamsDao"%>
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
		TestTeamsDao teamDao = new TestTeamsDao();
		teamDao.createTestTeam();
		List<TestTeams> teams = teamDao.retrieveAllTestTeamsRecords();
		
		for(TestTeams team : teams){
	%>
		<p>
			<%= team.getTestteamranking() %>
			<%= team.getTeamIdentifier().getTeamname() %>		
			<%= team.getTestcapfname() %>
			<%= team.getTestcaplname() %>
			<%= team.getTestcoachfname() %>
			<%= team.getTestcoachlname() %>
			
		</p>
	<% } %>
</body>
</html>