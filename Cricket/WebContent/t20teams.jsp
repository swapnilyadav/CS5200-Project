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
<body>
<%
		T20TeamsDao teamDao = new T20TeamsDao();
		teamDao.createT20Team();
		List<T20Teams> teams = teamDao.retrieveAllT20TeamsRecords();
		
		for(T20Teams team : teams){
	%>
		<p>
			<%= team.getT20teamranking()%>
			<%= team.getTeams().getTeamname() %>		
			<%= team.getT20capfname() %>
			<%= team.getT20caplname() %>
			<%= team.getT20coachfname() %>
			<%= team.getT20coachlname() %>
			
		</p>
	<% } %>
</body>
</html>