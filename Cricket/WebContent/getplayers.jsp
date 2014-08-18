<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.cricket.*, java.util.*, org.json.simple.*,java.io.PrintWriter"%>

	
	<%
		
		
		PrintWriter outt = response.getWriter();
		String team = request.getParameter("team");
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		PlayerDao playerDao = new PlayerDao();
		List<Player> players = null;
		System.out.println("name" + name);
		System.out.println("team " + team);
		System.out.println("category" + category);
		if(!name.equals(""))
		{
			System.out.println(name);
			players = playerDao.findPlayer(name);
		}
		else
		{
			if("Test".equals(category))
			{
				players = playerDao.findTestPlayers(team);
			}
			if("Odi".equals(category))
			{
				players = playerDao.findOdiPlayers(team);
			}
			if("T20".equals(category))
			{
				players = playerDao.findT20Players(team);
			}
		}
		
		
		//response.setContentType("text/json; charset=utf-8");
		JSONObject json = new JSONObject();
		JSONArray  playersArray = new JSONArray();
		JSONObject obj;
		for(Player player : players)
		{
			obj = new JSONObject();
			obj.put("name", player.getPlayerName());
			obj.put("dob", player.getPlayersDob().toString());
			obj.put("birthcity", player.getBirthCity());
			obj.put("battingstyle", player.getPlayerBattingStyle());
			obj.put("bowlingstyle", player.getPlayerBowlingStyle());
			obj.put("gender", player.getPlayerGender());
			obj.put("image", player.getPlayerImage());
			playersArray.add(obj);
			
		}
		json.put("Players", playersArray);
		//outt.write(json.toJSONString());
		//JSONParser parser = new JSONParser();
		//System.out.println(parser.parse(json.toJSONString()));
		request.setCharacterEncoding("utf8");
		response.setContentType("application/text");
		outt.write(json.toJSONString());
		outt.flush();
	%>
