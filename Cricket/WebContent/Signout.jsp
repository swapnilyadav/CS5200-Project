<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "com.cricket.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
<form action="Signout.jsp" method="post">
<%

if(session!=null)
{
	session.invalidate();
	String redirectURL = "crickethome.jsp"; 
    response.sendRedirect(redirectURL);
}

%>

</form>


</body>
</html>