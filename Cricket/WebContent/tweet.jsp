<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "com.cricket.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/tweet.css" />
<title>Insert title here</title>
</head>
<body>
	
		<ul class="tweetContent">
	<%
		TweetInfoDao tweetDao = new TweetInfoDao();
		tweetDao.createTweets();
		List<TweetInfo> tweets = tweetDao.readTweets();
		System.out.println(tweets.size());
		for(TweetInfo tweet : tweets){
	%>
		<li>
			<div class="tweet">
				<p>
					<%= tweet.getTweettext() %>
					
				</p>
			</div>
		<a href="comment.jsp?id=<%=tweet.getTweetid() %>" target="_blank">Add Comment</a>
		</li>
	<% } %>
		</ul>
	

</body>
</html>