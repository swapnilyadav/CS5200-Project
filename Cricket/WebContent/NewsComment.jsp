<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "com.cricket.*, java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href='http://fonts.googleapis.com/css?family=Magra:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/main-style.v6.css">
<link rel="stylesheet" type="text/css" href="css/newsPanel.v5.css" />
<link rel="stylesheet" type="text/css" href="css/menu-style.css" />
<style>
	.addcomments
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
	
	.commenttable
	{
		margin-left: auto;
		margin-right: auto;
	}
	
</style>
<title>Insert title here</title>
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

<form action="NewsComment.jsp?id=<%=request.getParameter("id") %>" method="post">
	<%if("comment".equals(request.getParameter("action")))
	{
		String comment = request.getParameter("comments");
		String userName = (String)session.getAttribute("username");
		String newsid = request.getParameter("id");
		System.out.println(newsid);
		UserinfoDao userDao = new UserinfoDao();
		NewsDao newsDao = new NewsDao(); 
		Userinfo user = userDao.getUser(userName);
		News news = newsDao.getNews(newsid);
		CommentsDao commentDoa = new CommentsDao();
		Date date = new Date(System.currentTimeMillis());
		Comments addComment = new Comments(comment,date,user,news);
		commentDoa.addComment(addComment);
		String redirectURL = "crickethome.jsp"; 
	    response.sendRedirect(redirectURL);
		
	}%>
	
	<div class = "addcomments">
	<% if(session.getAttribute("login")!=null ){ %>
		<table class = "commenttable">
			<tr>
				<td><label>Insert Comment</label></td>
				<td><textarea rows="10" cols="50" name="comments"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><center><button name = "action" value="comment">Comment</button></center></td>
			</tr>
		
       </table>
	<%}else{ %>
		<h1>Please login to comment!</h1>
	<%} %>
	</div>
</form>
</body>
</html>