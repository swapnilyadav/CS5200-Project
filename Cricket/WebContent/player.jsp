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
		  $("div[id*=playerlist]").remove();
		  $("div[id*=players]").find('br').remove();
		  $.ajax({
			    url : 'getplayers.jsp',
			    data : {      team:$( "#team option:selected" ).text(),
			        category:$( "#category option:selected" ).text(),
			        name:$( "#playername" ).val()
			        },
			    dataType: 'text',
			    type: "POST",
			    success : function(json) {
			    	var obj = $.parseJSON(json);
			    	var a = obj.Players;
			    	var i =0;
			    	$.each (a, function (bb) {
			    		
			    		i++;
			    		$("#players").append("<br><div id = 'playerlist" + i + "'>");
			    		$("#playerlist"+i).append("&nbsp;<img src = '" + a[bb].image + "'>");
			    		$("#playerlist"+i).append("<b> &nbsp;Name :- </b>" + a[bb].name + "<br>");
			    		$("#playerlist"+i).append("<b> &nbsp;Date Of Birth :- </b>" + a[bb].dob + "<br>");
			    		$("#playerlist"+i).append("<b> &nbsp;Birth City :- </b>" + a[bb].birthcity + "<br>");
			    		$("#playerlist"+i).append("<b> &nbsp;Batting Style :- </b>" + a[bb].battingstyle + "<br>");
			    		$("#playerlist"+i).append("<b> &nbsp;Bowling Style :- </b>" + a[bb].bowlingstyle + "<br>");
			    		$("#playerlist"+i).append("<b> &nbsp;Gender :- </b>" + a[bb].gender + "<br>");
			    		$("#playerlist"+i).append("</div>");
			    		$("#playerlist"+i).append("<hr>");
			    		$("#playerlist"+i).css('font-size', '14px');
			    		$("#playerlist"+i).css('width', '70%');
			    		$("#playerlist"+i).css('PaddingLeft', '12px');
			    		$("#playerlist"+i).css('MarginLeft', 'auto');
			    		$("#playerlist"+i).css('MarginRight', 'auto');
			    		 
			    		
			    	    
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
	.displayPlayer
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

    
   	<div class = "displayPlayer">
    	<label>Select a team:- </label>
    	<select id = "team">
    	<option value = "" selected>N/A</option>
    		<% 	TeamsDao teamsDao = new TeamsDao();
    			List<Teams> teams = teamsDao.retrieveAllTeamsRecords();
    			for(Teams team : teams){
    		%>
    		<option value = "<%=team.getTeamname()%>"><%=team.getTeamname()%></option>
    		<%}%>
    	</select>
    	
    	<label >Select a format :- </label>
    	<select id="category">
    		<option value = "" selected>N/A</option>
    		<option value = "Test">Test</option>
    		<option value = "Odi">Odi</option>
    		<option value = "T20">T20</option>
    	</select>
    	 <b>OR</b>
    	<br>
    	<br>
    	<label>If you don't find a player please enter his first name :- </label>
    	<input type = "text" id="playername" name = "playername"></input><br><br>
    	<center><button>Search Players</button></center>
    	<div id = "players">
    	</div>
   	</div>
  
    
</body>
</html>