<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>My Project</title>
<script src="js/prototype.js" type="text/javascript"></script>
<script src="js/scriptaculous.js" type="text/javascript"></script>
<script src="js/simple-slide-show.js" type="text/javascript"></script>
<script src="js/effects.js" type="text/javascript"></script>
<script src="js/display-match-data.js" type="text/javascript"></script>

<link rel="shortcut icon" href="http://3.bp.blogspot.com/-sl_Lg2Yt1JE/TZd7FIafc-I/AAAAAAAABgE/GY3F7EG1tYs/s1600/Cricket+World+Cup.png">
<link href='http://fonts.googleapis.com/css?family=Magra:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/main-style.v6.css">
<link rel="stylesheet" type="text/css" href="css/newsPanel.v5.css" />
<link rel="stylesheet" type="text/css" href="css/menu-style.css" />
<style>
    #slide-images{
    position:relative;
    display:block;
    margin-top:-79px;
    padding:0px;
    width:400px;
    height:550px;
    overflow:hidden;
}

#slide-images li{
    position:absolute;
    display:block;
    list-style-type:none;
    margin:0px;
    padding:0px;
    background-color:#FFFFFF;
}

#slide-images li img{
    display:block;
    background-color:#FFFFFF;
}

p {

	text-align: right;
	color : #191970;
	margin-right: 15%;
	font-size: medium;
	font-weight: bold;
	
}
</style>

</head>
<body onload="displayFixtures()">
    <div class="logo">
        <img width="300" height="130" src="images/Logo.gif" />
    </div>
 <p>Welcome :- 
<% if(session.getAttribute("login")!= null) {
%>
<%= session.getAttribute("username") %> </p>
<%}else{ %>
Guest</p>
<%} %>
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
    <!--Main Nagigation ends-->
<!-- Navigation Starts -->    
    <div class="bodyContentWrapper">
        <div class="mainBodyContainer">
            <!--News Section starts-->
            <section class="newsPanel">
                <!--News Carousel -->
                <div class="newsCarouselWrapper">
                    <ul id="slide-images">
                        <li>
                            <div class="imageInfo">
                                    Sachin Tendulkar - Indian Cricketer, he is considered as the best batsman to have ever graced the game.                     
                            </div>
                                <img src="http://static.guim.co.uk/sys-images/Sport/Pix/pictures/2010/12/19/1292778133565/Sachin-Tendulkar-India-So-007.jpg" title="Sachin- The best batsman" alt="Best Batsman!" width="430" height="550"/> 
                        </li>
                        <li>
                            <div class="imageInfo">
                                    Rahul Dravid - Indian Cricketer, ranked 3rd greatest batsman of all time by Wisden                              
                            </div>
                                <img src="http://im.rediff.com/cricket/2011/dec/14dravid1.jpg" title="Dravid - Mr Dependable" alt="Dravid - Mr Dependable" width="430" height="550"/>   
                        </li>
                        <li>
                            <div class="imageInfo">
                                    Ricky Ponting - Nicknamed Punter, Ricky Ponting is an Australian Cricketer who is well known for his aggressive leadership skills                               
                                    </div>
                                <img src="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQF06lUUo5Vg6eaFF9zBgZsquF0YuJV6CpDRTM0WWAUVQWQrONhKQ" title="Ponting - The most aggressive captain" alt="Ponting - The most aggressive captain" width="430" height="550"/>
                         </li>
                        <li>
                            <div class="imageInfo">
                                    Shane Warne -   Fondly called "Warnie" by his team-mates, Shane is a living legend and is considered the King of Spin Bowling                           
                            </div>
                                <img src="http://images.smh.com.au/2011/01/23/2147569/shane%20warne%20diary-420x0.jpg" title="Warnie- King of Spin" alt="Warnie - King 0f Spin" width="430" height="550"/>  
                        </li>
                        <li>
                            <div class="imageInfo">
                                    Mutthiah Muralitharan - A bowling legend, this "Smiling Assassin" has 800 test wickets to his name!
                            </div>
                                <img src="http://2.bp.blogspot.com/_vK6dvnjubGU/SxNMAZRjmxI/AAAAAAAAAFM/JxWMlUS7z5A/s1600/muttiah_muralitharan.jpg" title="Murali - Smiling Assassin" alt="Murali - Smiling Assassin" width="430" height="550"/>    
                        </li>
                        <li>
                            <div class="imageInfo">
                                    Anil Kumble - Called "Jumbo" by his team mates, Kumble is the third highest test wicket taker of all time.
                            </div>
                                <img src="http://l.yimg.com/bt/api/res/1.2/lzfV0IRv1GXDyod4Q.U4vg--/YXBwaWQ9eW5ld3M7Zmk9aW5zZXQ7aD02MzA7cT04NTt3PTQyMA--/http://l.yimg.com/os/153/2012/02/07/kumble-broken-jaw-630-jpg_074251.jpg" title="Kumble - Great Leg Spinner" alt="Kumble - Great Leg Spinner" width="430" height="550"/>   
                        </li>
                        <li>
                            <div class="imageInfo">
                                    M.S Dhoni - One of the finest wicketkeepers in World cricket, he is also the captain of Team India.
                            </div>
                                <img src="http://2.bp.blogspot.com/-HPfidhIo7CY/T_fMyb5HMXI/AAAAAAAABIE/LJ0pisNlFYk/s1600/Mahendra-Singh-Dhoni_Cricket_India_Captain.jpg" title="Dhoni - WC winning captain" alt="Dhoni - WC winning captain" width="430" height="550"/>    
                        </li>
                    </ul>
                </div>
                
                <!--News Listing-->
                <ul class="headlineContent">
                        <li>
                            <div class="headlineNews">
                                <p>It is said that cricket originated in England. <br>
                                Since they used to raise sheep, the grass was so short that it was possible to roll a lump of wool on it which they used as a ball.
                                </p>
                            </div>
                        </li>
                         <li>
                            <div class="headlineNews">
                                <p>The first recorded game was played in 1646, and later fines were handed out for those who missed the church to play.
                                </p>
                            </div>
                        </li>
                        <li>
                            <div class="headlineNews">
                                <p>In 1760, pitching a ball through the air was considered normal, thus the bat shape changed from a curve to a straight one.
                                </p>
                            </div>
                        </li>
                        <li>
                            <div class="headlineNews">
                                <p>The longest cricket match took place in 1939 between England and South Africa, after 14 days it ended with a tie.
                                </p>
                            </div>
                        </li>
                        <li>

                            <div class="headlineNews">
                                <p>
                                A cricket game was once stopped because a pig ran across the field. It is even considered legal to suspend the game if an animal entered the field.
                                </p>
                            </div>
                        </li>
                        
                        <li>

                            <div class="headlineNews">
                                <p>
                                Shahid Afridi used a bat borrowed from Waqar Younis to score the fastest century in a ODI match.
                                </p>
                            </div>
                        </li>
                </ul>
            <!-- jslidernews1ENDS --> 
            </section>
            <!--News Section end-->
            <!-- Cricket for dummies Video Section starts-->
                    
        <div class="matchDayWrapper" > 
            <iframe width="300" height="250" src="http://www.youtube-nocookie.com/embed/yPXAzgwwo0A" frameborder="0" allowfullscreen></iframe>
        </div>
        <div class="tweetWrapper" >
        	<h3><i>Latest Tweets from ESPN Crickinfo</i></h3> 
            <iframe width="300" height="250" src="tweet.jsp" frameborder="0" allowfullscreen></iframe>
        </div>
            <div class="clear"></div>  
        </div>
    </div>
   
    
</body>
</html>