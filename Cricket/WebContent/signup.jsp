<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cricket.*"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type='text/javascript' src='http://code.jquery.com/jquery-1.4.2.js'></script>

<link href='http://fonts.googleapis.com/css?family=Magra:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/main-style.v6.css">
<link rel="stylesheet" type="text/css" href="css/newsPanel.v5.css" />
<link rel="stylesheet" type="text/css" href="css/menu-style.css" />
<style>
	.displayuser
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
	
	.signuptable
	{
		margin-left: auto;
		margin-right: auto;
	}
	
</style>

</head>
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

	<form action="signup.jsp" method="post" action = "j_security_check">
		<%
			String action = request.getParameter("action");
			if("createuser".equals(action)){
				
				String firstName = request.getParameter("j_username");
				String lastName = request.getParameter("lastname");
				String userName = request.getParameter("username");
				String password = request.getParameter("j_password");
				String date = request.getParameter("dob");
				String email = request.getParameter("email");
				if(!date.equals("")){
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date date1 = sdf1.parse(date);
					Date  date2 = new java.sql.Date(date1.getTime());
					
					Userinfo user = new Userinfo(firstName,lastName,userName,password,date2,email,"No");
					UserinfoDao userdao = new UserinfoDao();
					userdao.createUser(user);
					
					
				}
				session.setAttribute("login", true);
				session.setAttribute("username", userName);
				String redirectURL = "crickethome.jsp"; 
			    response.sendRedirect(redirectURL);
			}
			
			
		%>
		
			<div class= "displayuser">
				<table class = "signuptable">
				<tr>
					<td> <label for="firstname">Firstname</label> </td>
					<td><input type="text" name="j_username" id="firstname" alt="Please enter your username."><br><br></td>
				</tr>
				<tr>
					<td><label for="lastname">Lastname</label></td>
					<td><input type="text" name="lastname" id="lastname" alt="Please enter your lastname."><br><br></td>
				</tr>
				<tr>
					<td><label for="username">Username</label></td>
					<td><input type="text" name="username" id="username" alt="Please enter your username."><br><br></td>
				</tr>
				
				<tr>
					<td><label for="password">Password</label></td>
					<td><input type="password" name="j_password" id="pswd" alt="Please enter a password."><br><br></td>
				</tr>
				
				<tr>
					<td><label for="dateofbirth">Date Of Birth</label></td>
					<td><input type="text" name="dob" id="dob" alt="Please enter your date of birth."><br><br></td>
				</tr>
				
				<tr>
					<td><label for="email address">Email Address</label></td>
					<td><input type="text" name="email" id="email" alt="Please enter your Email Address."><br><br></td>
				</tr>
				<tr>
					<td colspan="2"><center> <button name = "action" value="createuser">Sign-Up</button></center></td>
				</tr>
				</table>
			</div>
		
		
			
           
             
       
		
	</form>
	
</body>
</html>