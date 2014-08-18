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
<script>
function getContent(str) {

    var xmlhttp;
    var fileName = str + ".jsp";
    if (str == "test") {
        document.getElementById(str).src = "images/" + str + "-change.jpg";
        document.getElementById("t20").src = "images/T20.jpg";
        document.getElementById("odi").src = "images/ODI.jpg";
    }
    if (str == "odi") {
        document.getElementById(str).src = "images/" + str + "-change.jpg";
        document.getElementById("test").src = "images/Test.jpg";
        document.getElementById("t20").src = "images/T20.jpg";
    }
    if (str == "t20") {
        document.getElementById(str).src = "images/" + str + "-change.jpg";
        document.getElementById("test").src = "images/Test.jpg";
        document.getElementById("odi").src = "images/ODI.jpg";
    }
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("content").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", fileName, true);
    xmlhttp.send();
}
</script>
</head>
<body>
<body onload="getContent('test')">
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

        <table class="mainContainer" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="displayRankings">
                    <iframe width="308" height="315" src="display-teams.jsp" frameborder="0" overflow:auto></iframe>
                </td>
                <td>
                    <div class ="mainRankingInfo">
                        <div class="navigation">
                            <ul>
                                <li><img id ="test" src="images/Test.jpg" alt="Test Data" onclick="getContent('test')"> </li>
                                <li><img id="odi" src="images/ODI.jpg" alt="ODI Data" onclick="getContent('odi')"></li>
                                <li><img id="t20" src="images/T20.jpg" alt="T20 Data" onclick="getContent('t20')"></li>
                            </ul>
                        </div>
                        <div id= "content" class="displayRankingInfo">       
                        </div>
                    </div>
                </td>
            </tr>
        </table> 
        <br />
        
</body>
</html>