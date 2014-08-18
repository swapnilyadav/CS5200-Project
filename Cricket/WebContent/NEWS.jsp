<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "com.cricket.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/menu-style.css" />
<link rel="stylesheet" type="text/css" href="css/main-style.v6.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
/*
$(document).ready(function(){
  $("button").click(function(){
    $.post("getplayers.jsp",
    {
      team:$( "#team option:selected" ).text(),
      category:$( "#category option:selected" ).text()
    },
    function(data,status){
      var jsondata = $.trim(data);
      alert(jsondata);
      var obj = $.parseJSON(jsondata);
      alert(obj);
    });
  });
});
*/

$(document).ready(function(){
	  $("button").click(function(){
		  $("div[id*=newslist]").remove();
		  $("div[id*=news]").find('br').remove();
		  $.ajax({
			    url : 'GetNews.jsp',
			    data : {     
			        date:$( "#date" ).val()
			        },
			    dataType: 'text',
			    type: "POST",
			    success : function(json) {
			    	var obj = $.parseJSON(json);
			    	var a = obj.news;
			    	alert(a.length);
			    	var i =0;
			    	$.each (a, function (bb) {
			    		
			    		i++;
			    		$("div[id*=today]").remove();
			    		$("#news").append("<br><div id = 'newslist" + i + "'>");
			    		$("#newslist"+i).append("<b> Date :- </b>" + a[bb].date + "<br>");
			    		$("#newslist"+i).append("<b> News :- </b>" + a[bb].title + "<br>");
			    		$("#newslist"+i).append("<a href='" + a[bb].link +"'" + "target='_blank'>Get Detailed news</a><br>");
			    		$("#newslist"+i).append("<a href= 'NewsComment.jsp?id=" + a[bb].id +"'" + "target = '_blank'>Add Comment </a>");
			    		$("#newslist"+i).append("</div>");
			    		$("#newslist"+i).append("<hr>");
			    		$("#newslist"+i).css('font-size', '14px');
			    		$("#newslist"+i).css('width', '70%');
			    		$("#newslist"+i).css('PaddingLeft', '12px');
			    		$("#newslist"+i).css('MarginLeft', 'auto');
			    		$("#newslist"+i).css('MarginRight', 'auto');
			    	    
			    	});
			    },
			        error: function (jqXHR, textStatus, errorThrown)
			        {
			     		alert(textStatus+" "+errorThrown);
			        }
			});
	  });
	});

</script>

<style>
.display
{font-size:14px;
width:70%;
PaddingLeft:12px;
MarginLeft:auto;
MarginRight:auto;
}</style>
<style>
	.displayNews
	{
		border: 2px solid #313D9A;
	    border-top: 0!important;
	    background-color: #EAC535;
	    color: #49370A;
	    float: inherit;
	    font-size:14px;
	    margin-top:0px;
	    width:50%;
	    margin-left:25%;
	    margin-right:25%;
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

    
   	<div class="displayNews">
   	
    	<br>
    	<br>
    	<label>Want a specific day's news? Enter the date here in "yyyy-mm-dd" format:</label>
    	<input type = "text" id= "date" name = "date"></input><br><br>
    	<center><button>Search News</button></center>
    	<div id = "news">
    	</div>
    </div>	
   
   
   <div  id="today" class="displayNews">
   <%
   
		
		NewsDao nd = new NewsDao();
		//nd.createNews();
		//List<News> news = nd.readNews();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd");
		Date td = new Date();
		java.sql.Date sqlDate = new java.sql.Date(td.getTime());
		List<News> news = nd.getNews(sqlDate);

		for(News archivednews : news){
	%>
		
		    <br>
		    <b>Date:-</b><%= archivednews.getArchivedNewsDateAndTime() %>
		    <br>
			<b>News:-</b><%= archivednews.getArchivedNewsTitle() %>
			<br>
			<a href=<%= archivednews.getArchivedNewslink() %> target="_blank">Get detailed news</a>
			
		<br>
		
		<a href="NewsComment.jsp?id=<%=archivednews.getArchivedNewsId() %>" target="_blank">Add Comment</a><hr>
	<% }%>
   	 </div>
    
</body>
</html>