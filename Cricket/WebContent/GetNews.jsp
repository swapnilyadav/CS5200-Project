<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cricket.*, java.util.*, org.json.simple.*,java.io.PrintWriter"%>

	
	<%
		
		
		PrintWriter outt = response.getWriter();
		String date = request.getParameter("date");
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		Date parsed = formatter.parse(date);
		java.sql.Date newsdate = new java.sql.Date(parsed.getTime());
		NewsDao newsdao = new NewsDao();
		newsdao.createNews();
		List<News> news = newsdao.getNews(newsdate);
		
			
		
		//response.setContentType("text/json; charset=utf-8");
		JSONObject json = new JSONObject();
		JSONArray  newsArray = new JSONArray();
		JSONObject obj;
		for(News archivednews : news)
		{
			
			obj = new JSONObject();			
			obj.put("date", archivednews.getArchivedNewsDateAndTime().toString());
			obj.put("title", archivednews.getArchivedNewsTitle());
			obj.put("link", archivednews.getArchivedNewslink());
			obj.put("id",String.valueOf(archivednews.getArchivedNewsId()));
			
			newsArray.add(obj);
			
		}
		json.put("news", newsArray);
		//outt.write(json.toJSONString());
		//JSONParser parser = new JSONParser();
		//System.out.println(parser.parse(json.toJSONString()));
		request.setCharacterEncoding("utf8");
		response.setContentType("application/text");
		//System.out.println(json.toJSONString());
		outt.write(json.toJSONString());
		outt.flush();
	%>
