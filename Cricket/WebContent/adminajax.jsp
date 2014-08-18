<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "com.cricket.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String action = request.getParameter("action");
		if("createplayers".equals(action))
		{
			PlayerDao playerdao = new PlayerDao();
			playerdao.createPlayers("create");
		}
		if("updateplayers".equals(action))
		{
			PlayerDao playerdao = new PlayerDao();
			playerdao.createPlayers("update");
		}
		if("createteams".equals(action))
		{
			TeamsDao teamDao = new TeamsDao();
			teamDao.createTeam();
			T20TeamsDao t20TeamDao = new T20TeamsDao();
			t20TeamDao.createT20Team();
			TestTeamsDao testTeamDao = new TestTeamsDao();
			testTeamDao.createTestTeam();
			OdiTeamsDao odiTeamDao = new OdiTeamsDao();
			odiTeamDao.createOdiTeam();
		}
		if("addarchivedmatches".equals(action))
		{
			ArchivedMatchDao matchDao = new ArchivedMatchDao();
			matchDao.createArchivedMatch();
		}
		if("addupcomingmatches".equals(action))
		{
			UpcomingMatchDao matchDao = new UpcomingMatchDao();
			matchDao.createUpcomingMatch();
		}
		if("worldcuphistory".equals(action))
		{
			WorldCupHistoryDao wchdao = new WorldCupHistoryDao();
			wchdao.parseCSVFileLineByLine();
		}
		if("addnews".equals(action))
		{
			NewsDao nd = new NewsDao();
			nd.createNews();
		}
	%>
</body>
</html>