<%@page import="java.util.List"%>
<%@page import="com.cricket.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main-style.v6.css">
<link rel="stylesheet" type="text/css" href="css/newsPanel.v5.css" />
<link rel="stylesheet" type="text/css" href="css/rankings.css" />
<link rel="stylesheet" type="text/css" href="css/menu-style.css" />
<script src="javascript/rankings.js" type="text/javascript"></script>
<script src="javascript/display-match-data.js" type="text/javascript"></script>
<title>Insert title here</title>
<style>
 table, body 
 {
     font-family: 'Arial';
     color:black;
     font-size:15px;
     background: -moz-linear-gradient(0% 100% 90deg, #E3BA33, #F1A62C) repeat scroll 0% 0% transparent;
     background: -webkit-gradient(linear, 0 0, 0 100%, from(#f1a62c),to(#e3ba33) );
 }
 img {
    float: right;
}


    </style>
</head>
<body>
There are 10 international teams recognized by ICC. Follow the link on each team to learn more! <br/>
<%
		
		TeamsDao teamDao = new TeamsDao();
		//teamDao.createTeam();
		List<Teams> teams = teamDao.retrieveAllTeamsRecords();
		
		for(Teams team : teams){
			String teamName = team.getTeamname();
			String newTeamName= teamName.replaceAll(" ", "_");
			String url = "http://en.wikipedia.org/wiki/"+newTeamName+"_national_cricket_team";
			String flagName = "images/"+newTeamName+".png";
	%>
		<table align="center">
		<tr>
		<td><a href=<%=url%> target="_blank"><%=teamName %></a></td>
		<td><img src=<%=flagName %> alt="flag" width="20" height="20"/></td>
		</tr>
			</table>
	<% } %>
</body>
</html>