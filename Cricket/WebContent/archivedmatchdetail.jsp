<%@page import="com.cricket.ArchivedMatch"%>
<%@page import="com.cricket.ArchivedMatchDao"%>
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
 .table1Id
 {
 	background: -moz-linear-gradient(0% 100% 90deg, #E3BA33, #F1A62C) repeat scroll 0% 0% transparent;
 	background: -webkit-gradient(linear, 0 0, 0 100%, from(#f1a62c),to(#e3ba33) );
 	margin-left: auto;
 	margin-right: auto;
 }
 .table2Id
 {
 background: -moz-linear-gradient(0% 100% 90deg, #E3BA33, #F1A62C) repeat scroll 0% 0% transparent;
 }
    </style>
<body>
   <div class="logo">
        <img width="300" height="130" src="images/Logo.gif" />
   </div>
   <div id="menu">
        <ul class="menu">
			<li class="current"><a href="crickethome.jsp"><span>Home</span></a></li>
	        <li><a href="upcomingmatch.jsp" class="parent" target="_blank"><span>Upcoming</span></a></li>
	        <li><a href="archivedmatch.jsp" class="parent" target="_blank"><span>Archived</span></a></li>
	        <li><a href="teams.jsp" class="parent" target="_blank"><span>Teams</span></a></li>
	        <li><a href="crickethome.jsp" class="parent" target="_blank"><span>News</span></a></li>
	        <li><a href="crickethome.jsp" class="parent" target="_blank"><span>Players</span></a></li>
	        <li><a href="crickethome.jsp" class="parent" target="_blank" ><span>World Cup!</span></a></li>
    	</ul>
    </div>
    <form action="archivedmatchdetail.jsp?id=<%=request.getParameter("id") %>" method="post">
    <%
    	String matchId = request.getParameter("id");
    	ArchivedMatchDao matchDao = new ArchivedMatchDao();
    	ArchivedMatch matchObj = matchDao.retrieveArchivedMatchForId(matchId);
    	
    %>
    <table border="1" class="table1Id">
    
    <tr>
    <td><b>Match Type</b></td>
    <td colspan="3"><%=matchObj.getArchivedmatchtype() %></td>
    </tr>
    
    <tr>
    <td><b>Match Series</b></td>
    <td colspan="3"><%=matchObj.getArchivedmatchseries() %></td>
    </tr>
    
    <tr>
    <td><b>Match Name</b></td>
    <td colspan="3"><%=matchObj.getArchivedmatchname() %></td>
    </tr>
    
    <tr>
    <td><b>Match No</b></td>
    <td colspan="3"><%=matchObj.getArchivedmatchno() %></td>
    </tr>
    
     <tr>
    <td><b>Match City</b></td>
    <td colspan="3"><%=matchObj.getArchivedmcity() %></td>
    </tr>
    
     <tr>
    <td><b>Match Country</b></td>
    <td colspan="3"><%=matchObj.getArchivedmcountry() %></td>
    </tr>
    
    <tr>
    <td><b>Match Stadium</b></td>
    <td colspan="3"><%=matchObj.getArchivedmstadium() %></td>
    </tr>
    
    <tr>
    <td><b>Match Result</b></td>
    <td colspan="3"><%=matchObj.getArchivedmstatus()%></td>
    </tr>
    
    <tr>
    <td><b>Match Toss</b></td>
    <td colspan="3"><%=matchObj.getArchivedmtoss()%></td>
    </tr>
    
    <tr>
    <td><b>Toss Decision</b></td>
    <td colspan="3"><%=matchObj.getArchivedmtossdecision()%></td>
    </tr>
    
    <tr>
    <td><b>Match Start Date</b></td>
    <td colspan="3"><%=matchObj.getArchivedmstartdate()%></td>
    </tr>
    
    <tr>
    <td><b>Match End Date</b></td>
    <td colspan="3"><%=matchObj.getArchivedmenddate()%></td>
    </tr>
    
    <tr>
    <td><b>Match Toss</b></td>
    <td colspan="3"><%=matchObj.getArchivedmtoss()%></td>
    </tr>
    
    
    <tr>
      <td><b>Team 1</b></td>
      <td><%=matchObj.getArchivedmteam1() %></td>
      <td>
          <table class="table2Id">
              <tr>
                  <td>Inngs1 Runs - <%=matchObj.getArchivedmteam1Innings1runs() %></td>
              </tr>
              <tr>
                  <td>Inngs1 Wickets - <%=matchObj.getArchivedmteam1Innings1wickets() %></td>
              </tr>
              <tr>
                 <td>Inngs1 Overs - <%=matchObj.getArchivedmteam1Innings1overs() %></td>
              </tr>
          </table>
      </td>
      <td>
          <table class="table2Id">
              <tr>
                  <td>Inngs2 Runs- <%=matchObj.getArchivedmteam1Innings2runs() %></td>
              </tr>
              <tr>
                  <td>Inngs2 Wickets-<%=matchObj.getArchivedmteam1Innings2wickets() %></td>
              </tr>
              <tr>
                 <td>Inngs2 Overs-<%=matchObj.getArchivedmteam1Innings2overs() %></td>
              </tr>
          </table>
      </td>
   </tr>
   
   <tr>
      <td><b>Team 2</b></td>
      <td><%=matchObj.getArchivedmteam2() %></td>
      <td>
          <table class="table2Id">
              <tr>
                  <td>Inngs1 Runs- <%=matchObj.getArchivedmteam2Innings1runs() %></td>
              </tr>
              <tr>
                  <td>Inngs1 Wickets- <%=matchObj.getArchivedmteam2Innings1wickets() %></td>
              </tr>
              <tr>
                 <td>Inngs1 Overs- <%=matchObj.getArchivedmteam2Innings1overs() %></td>
              </tr>
          </table>
      </td>
      <td>
          <table class="table2Id">
              <tr>
                  <td>Inngs2 Runs- <%=matchObj.getArchivedmteam2Innings2runs() %></td>
              </tr>
              <tr>
                  <td>Inngs2 Wickets- <%=matchObj.getArchivedmteam2Innings2wickets() %></td>
              </tr>
              <tr>
                 <td>Inngs2 Overs- <%=matchObj.getArchivedmteam2Innings2overs() %></td>
              </tr>
          </table>
      </td>
   </tr>
   
    
    
    </table>
    </form>
    </body>
    </html>
