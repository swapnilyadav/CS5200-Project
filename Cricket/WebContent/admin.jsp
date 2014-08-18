<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/menu-style.css" />
<link rel="stylesheet" type="text/css" href="css/main-style.v6.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/admin.js" type="text/javascript"></script>
<script type="text/javascript">
    
 
</script>
<style>
	.adminstuff
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
	.admintable
	{
		margin-left: auto;
		margin-right: auto;
	}
	tr
	{
		padding-bottom: 1em;
	}
	div#spinner
{
    display: none;
    width:200px;
    height: 200px;
    position: fixed;
    top: 50%;
    left: 50%;
    text-align:center;
    margin-left: -50px;
    margin-top: -100px;
    z-index:2;
    overflow: auto;
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

        <div class = "adminstuff">
    	<center><label>Please perform all maintenance task here :- </label></center><br><br>
    	
    	<table class = "admintable">
    		<tr>
    			<th>Tasks</th>
    			<th>Action</th>
    		</tr>
    		<tr>
    			<td><label>Create Teams :- </label></td>
    			<td><input type = "button" id = "createteams" value = "Create Team"></td>
    		</tr>
    		
    		<tr>
    			<td><label>Add Players :- </label></td>
    			<td><input type = "button" id = "addplayers" value = "Add Players"></td>
    		</tr>
    		<tr>
    			<td><label>Update Players :- </label></td>
    			<td><input type = "button" id = "updateplayers" value = "Update Players"></td>
    		</tr>
    		<tr>
    			<td><label>Add Upcoming Matches :- </label></td>
    			<td><input type = "button" id = "addupcomingmatches" value = "Add Upcoming Matches"></td>
    		</tr>
    		<tr>
    			<td><label>Add Archived Matches :- </label></td>
    			<td><input type = "button" id = "addarchivedmatches" value = "Add Archived Matches"></td>
    		</tr>
    		<tr>
    			<td><label>Add News :- </label></td>
    			<td><input type = "button" id = "addnews" value = "Add News"></td>
    		</tr>
    		<tr>
    			<td><label>Add World Cup History :- </label></td>
    			<td><input type = "button" id = "worldcuphistory" value = "Add World Cup History"></td>
    		</tr>
    	</table>
    </div>
<div id="spinner">
    <img src="images/ajax-loader.gif" alt="Loading..."/>
</div>
</body>
</html>