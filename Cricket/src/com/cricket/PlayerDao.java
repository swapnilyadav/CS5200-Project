package com.cricket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class PlayerDao {
	
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public List<Player> findTestPlayers(String teamName)
	{
		TeamsDao teamDao = new TeamsDao();
		Teams team = teamDao.getTeam(teamName);
		
		int teamId = team.getTeamid();
		
		TestTeamsDao testTeamDao = new TestTeamsDao();
		TestTeams testTeam = testTeamDao.getTestTeam(teamId);
		//System.out.println("The test team id is : - " + testTeam.getTestteamid());
		List<Player> players = getAllPlayers();
		List<Player> testPlayers = new ArrayList<Player>();
		for(Player player : players)
		{
			if(player.getTestTeam()!=null)
				if(player.getTestTeam().getTestteamid() == testTeam.getTestteamid())
					testPlayers.add(player);
		}
		//System.out.println(testPlayers.size());
		return testPlayers;
		
		
	}
	
	public List<Player> findOdiPlayers(String teamName)
	{
		TeamsDao teamDao = new TeamsDao();
		Teams team = teamDao.getTeam(teamName);
		
		int teamId = team.getTeamid();
		
		OdiTeamsDao odiTeamDao = new OdiTeamsDao();
		OdiTeams odiTeam = odiTeamDao.getOdiTeam(teamId);
		//System.out.println("The test team id is : - " + odiTeam.getOditeamid());
		List<Player> players = getAllPlayers();
		List<Player> odiPlayers = new ArrayList<Player>();
		for(Player player : players)
		{
			if(player.getOdiTeam()!=null)
				if(player.getOdiTeam().getOditeamid() == odiTeam.getOditeamid())
					odiPlayers.add(player);
		}
		//System.out.println(odiPlayers.size());
		return odiPlayers;
	}
	
	public List<Player> findT20Players(String teamName)
	{
		TeamsDao teamDao = new TeamsDao();
		Teams team = teamDao.getTeam(teamName);
		
		int teamId = team.getTeamid();
		
		T20TeamsDao t20TeamDao = new T20TeamsDao();
		T20Teams t20Team = t20TeamDao.getT20Team(teamId);
		//System.out.println("The test team id is : - " + t20Team.getT20teamid());
		List<Player> players = getAllPlayers();
		List<Player> t20Players = new ArrayList<Player>();
		for(Player player : players)
		{
			if(player.getT20Team()!=null)
				if(player.getT20Team().getT20teamid() == t20Team.getT20teamid())
					t20Players.add(player);
		}
		//System.out.println(t20Players.size());
		return t20Players;
	}
	
	/*What if teams have the same rank*/
	public void createPlayers(String type)
	{
		EntityManager em = factory.createEntityManager();
		TeamsDao teamDao = new TeamsDao();
		List<Teams> teams = teamDao.retrieveAllTeamsRecords();
		URLConnection connection = null;
		Document doc = null;
		Date date = null;
		int count = 0;
		String firstName = "";
		String lastName = "";
		String gender = "";
		String dateOfBirth = "";
		String placeOfBirth = "";
		String batting = "";
		String bowling = "";
		String playerImage = "";
		boolean value = false;
		boolean repeat = false;
		try{
			/* The below for loop iterates through the teams from the teams table*/
			for(int i = 1 ; i<= teams.size() ; i++)
			{
				Teams team = teams.get(i-1);
				int teamId = team.getYahooteamid();
				connection = getConnection("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.team.profile%20where%20team_id%3D" + teamId +"&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0");
				doc = xmlRecords(connection);
				NodeList nodes = doc.getElementsByTagName("Players");
				Node currentNode = nodes.item(0);
				
				/*Get the ith team from the teams*/
				
				System.out.println(team.getTeamname());
				
				
				
				
				
				NodeList childNodes = currentNode.getChildNodes();
				//System.out.println(childNodes.getLength());
				for (int j = 0 ; j < childNodes.getLength(); j++)
				{
					TestTeams testTeam = new TestTeams();
					OdiTeams odiTeam = new OdiTeams();
					T20Teams t20Team = new T20Teams();
					Node childNodeItem = childNodes.item(j);
					if (childNodeItem instanceof Element)
					{

						String personId = ((Element) childNodeItem).getAttribute("personid");
						//System.out.println("The person Id is :- " + personId);
						String playerURL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.player.profile%20where%20player_id%3D" + personId + "&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0";
						//System.out.println(playerURL);
						connection = getConnection(playerURL);
						doc = xmlRecords(connection);
						NodeList nodes1 = doc.getElementsByTagName("PersonalDetails");
						Node currentNode1 = nodes1.item(0);

						NodeList childNodes1 = currentNode1.getChildNodes();
						for (int k = 0; k < childNodes1.getLength(); k++)
						{
							Node childNodes2 = childNodes1.item(k);
							if(childNodes2 instanceof Element)
							{
								if(childNodes2.getNodeName().equalsIgnoreCase("firstname"))
								{
									firstName = childNodes2.getTextContent();
									//System.out.println(childNodes2.getTextContent());
								}
								if(childNodes2.getNodeName().equalsIgnoreCase("lastname"))
								{
									lastName = childNodes2.getTextContent();
									//System.out.println(childNodes2.getTextContent());
								}
								
								
								
								if(childNodes2.getNodeName().equalsIgnoreCase("gender"))
								{
									gender = childNodes2.getTextContent();
									//System.out.println(childNodes2.getTextContent());
								}
								if(childNodes2.getNodeName().equalsIgnoreCase("dateofbirth"))
								{
									dateOfBirth = childNodes2.getTextContent();
									date = new Date(new SimpleDateFormat("yyyy-mm-dd").parse(dateOfBirth).getTime());
									//System.out.println(childNodes2.getTextContent());
								}
								if(childNodes2.getNodeName().equalsIgnoreCase("PlaceOfBirth"))
								{
									if (childNodes2.getChildNodes().item(1).getNodeName().equalsIgnoreCase("City"))
									{
										placeOfBirth = childNodes2.getTextContent();
										//System.out.println(childNodes2.getChildNodes().item(1).getTextContent());
									}
								}
								if(childNodes2.getNodeName().equalsIgnoreCase("PlayerThumbImgName"))
								{
									playerImage = childNodes2.getTextContent();
									//System.out.println(childNodes2.getTextContent());
								}




							}
						}
						
						NodeList nodeCareerList = doc.getElementsByTagName("CareerDetails");
						Node nodeCareer = nodeCareerList.item(0);
						

						NodeList nodeCareerChild = nodeCareer.getChildNodes();
						for (int m = 0 ; m < nodeCareerChild.getLength(); m++)
						{
							
							Node nodeCareerChild1 = nodeCareerChild.item(m);
							if(nodeCareerChild1.getNodeName().equalsIgnoreCase("Style"))
							{

								if (nodeCareerChild1.getChildNodes().item(1).getNodeName().equalsIgnoreCase("Batting"))
								{
									batting = nodeCareerChild1.getTextContent();
									//System.out.println(nodeCareerChild1.getChildNodes().item(1).getTextContent());
								}
								if (nodeCareerChild1.getChildNodes().item(3).getNodeName().equalsIgnoreCase("Bowling"))
								{
									bowling = nodeCareerChild1.getTextContent();
									//System.out.println(nodeCareerChild1.getChildNodes().item(3).getTextContent());
								}
							}
							if(nodeCareerChild1.getNodeName().equalsIgnoreCase("MatchStats"))
							{
								
								if(((Element)nodeCareerChild1).getAttribute("mtype").equals("Test"))
								{
									/* Code to get the test team object to the team where the player belongs*/
									TestTeamsDao testTeamDao = new TestTeamsDao();
									testTeam = testTeamDao.getTestTeam(team.getTeamid());
									//System.out.println("got the test team id");
									
								}
								if(((Element)nodeCareerChild1).getAttribute("mtype").equals("ODI"))
								{
									/* Code to get the odi team object to the team where the player belongs*/
									OdiTeamsDao odiTeamDao = new OdiTeamsDao();
									odiTeam = odiTeamDao.getOdiTeam(team.getTeamid());
									//System.out.println("got the odi team id");
								}
								if(((Element)nodeCareerChild1).getAttribute("mtype").equals("T20"))
								{
									/* Code to get the t20 team object to the team where the player belongs*/
									T20TeamsDao t20TeamDao = new T20TeamsDao();
									t20Team = t20TeamDao.getT20Team(team.getTeamid());
									//System.out.println("got the t20 team id");
								}
							}
						}
						
						
						//System.out.println(j + " Player Name is :- " + firstName + lastName +" Player test team id :- " + testTeam.getTestteamid());
						//System.out.println(j + " Player Name is :- " + firstName + lastName +" Player test team id :- " + t20Team.getT20teamid());
						//System.out.println(j + " Player Name is :- " + firstName + lastName +" Player test team id :- " + odiTeam.getOditeamid());
						
						
						
						if(testTeam.getTestteamid()==0)
							testTeam = null;
						if(t20Team.getT20teamid()==0)
							t20Team = null;
						if(odiTeam.getOditeamid()==0)
							odiTeam = null;
						Player player = new Player(firstName+lastName, date, placeOfBirth, gender, batting, bowling,playerImage, testTeam, t20Team ,odiTeam);
						Player updatePlayer = new  Player();
						if(type.equals("update"))
						{
							
							List<Player> players = getAllPlayers();
							for(Player player1 : players)
							{
								if(player1.getPlayerName().equals(firstName+lastName))
								{
									updatePlayer = player1;
									value = true;
									break;
								}
							}
						}
						em.getTransaction().begin();
						if(value)
						{
							updatePlayer.setPlayerBattingStyle(batting);
							updatePlayer.setPlayerBowlingStyle(bowling);
							updatePlayer.setPlayerImage(playerImage);
							em.merge(updatePlayer);
							System.out.println(updatePlayer.getPlayerid());
						}
						else
						{
							System.out.println("inserting");
							List<Player> players = getAllPlayers();
							for(Player player1 : players)
							{
								
								if(player1.getPlayerName().equals(firstName+lastName))
								{
									repeat = true;
									break;
								}
							}
							if(!repeat)
								em.persist(player);
						}
						em.getTransaction().commit();
						
						
						
						


					}
					//
					//System.out.println("Creating player record");
					
					
					//em.persist(player);
					//System.out.println("After adding the player record");
					//em.getTransaction().commit();
					//em.close();
					
					
				}
			}
			
			em.close();
			
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public URLConnection getConnection(String url)
	{
		URLConnection connection = null;
		try {
			URL website = new URL(url);
			connection = website.openConnection();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return connection;

	}
	
	public Document xmlRecords(URLConnection connection)
	{
		Document doc = null;
		String xmlrecords = "";
		try{

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));


			String inputLine;

			while ((inputLine = in.readLine()) != null) 
				xmlrecords += inputLine;

			//System.out.println(xmlrecords);

			in.close();
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlrecords));
			doc = db.parse(is);


		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		return doc;
	}
	
	public List<Player> getAllPlayers()
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		
		
		Query query = em.createQuery("select p from Player p");
		List<Player> players = (List<Player>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		//System.out.println(players.size());
		
		return players;
	}
	
	public List<Player> findPlayer(String name)
	{
		URLConnection connection = null;
		//Player player = new Player();
		Document doc = null;
		String firstName = "N/A";
		String lastName = "N/A";
		String gender = "N/A";
		String dateOfBirth = "N/A";
		String placeOfBirth = "N/A";
		String batting = "N/A";
		String bowling = "N/A";
		Date date = null;
		String playerImage = "";
		boolean value = false;
		List<Player> playerList = new ArrayList<Player>();
		try
		{
			List<Player> players = getAllPlayers();
			for(Player player : players)
			{
				
				if(player.getPlayerName().contains(name))
				{
					playerList.add(player);
					value = true;
				}
			}
			
			if(!value)
			{
				System.out.println("player not present in db");
				EntityManager em = factory.createEntityManager();
				connection = getConnection("https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20cricket.players%20WHERE%20player_name%3D%22" + name + "%22&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0");
				doc = xmlRecords(connection);
				NodeList nodes = doc.getElementsByTagName("Player");
				
				
				
				for (int j = 0 ; j < nodes.getLength(); j++)
				{
					firstName = nodes.item(j).getChildNodes().item(1).getChildNodes().item(1).getTextContent();
					lastName = nodes.item(j).getChildNodes().item(1).getChildNodes().item(3).getTextContent();
					gender = nodes.item(j).getChildNodes().item(1).getChildNodes().item(5).getTextContent();
					dateOfBirth = nodes.item(j).getChildNodes().item(1).getChildNodes().item(7).getTextContent();
					date = new Date(new SimpleDateFormat("yyyy-mm-dd").parse(dateOfBirth).getTime());
					placeOfBirth = nodes.item(j).getChildNodes().item(1).getChildNodes().item(9).getChildNodes().item(1).getTextContent();	
					playerImage =  nodes.item(j).getChildNodes().item(1).getChildNodes().item(13).getTextContent();
					System.out.println(playerImage);
					batting = nodes.item(j).getChildNodes().item(5).getChildNodes().item(1).getTextContent();
					bowling = nodes.item(j).getChildNodes().item(5).getChildNodes().item(3).getTextContent();
					Player player = new Player(firstName+lastName, date, placeOfBirth, gender, batting, bowling,playerImage, null, null ,null);
					playerList.add(player);
					em.getTransaction().begin();
					em.persist(player);
					em.getTransaction().commit();
				}
				
				em.close();
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return playerList;
	}
	
	
	
	public static void main(String[] args)
	{
		PlayerDao playerDao = new PlayerDao();
		//playerDao.createPlayers("create");
		//playerDao.getAllPlayers();
		//playerDao.findOdiPlayers("India");
		playerDao.findPlayer("sachin");
	}
	
	

}
