package com.cricket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
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

public class TestTeamsDao {
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public TestTeamsDao(){
	}
	
	public void createTestTeam(){
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		// Parse the URL
		URLConnection conn = getConnection("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.teams&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0");
		Document doc = null;
		String xmlrecords = "";
		
		try{

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) 
				xmlrecords += inputLine;

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
		
		
		NodeList testteams = doc.getElementsByTagName("Team");
		String testRankings, captainFName = "", captainLName = "",  coachFName = "", coachLName = "", teamName = "";
		int testRankingsInt;

		
		for (int i = 0; i < testteams.getLength(); i++){
			Node testteam = testteams.item(i);
			if (testteam.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) testteam;
				
				teamName = eElement.getElementsByTagName("TeamName").item(0).getTextContent();
				
				testRankings = eElement.getElementsByTagName("Ranking").item(0).getTextContent();
				testRankingsInt = Integer.parseInt(testRankings);
				
				Node captainTag = eElement.getElementsByTagName("Captain").item(0);
				if (captainTag.getNodeType() == Node.ELEMENT_NODE){
					Element captainElement = (Element) captainTag;
					
					captainFName = captainElement.getElementsByTagName("FirstName").item(0).getTextContent();
					
					captainLName = captainElement.getElementsByTagName("LastName").item(0).getTextContent();
				}
				
				Node coachTag = eElement.getElementsByTagName("Coach").item(0);
				if (coachTag.getNodeType() == Node.ELEMENT_NODE){
					Element coachElement = (Element) coachTag;
					
					coachFName = coachElement.getElementsByTagName("FirstName").item(0).getTextContent();
					
					coachLName = coachElement.getElementsByTagName("LastName").item(0).getTextContent();
				}
				
				List<TestTeams> testTeamsResultSet = retrieveAllTestTeamsRecords();
				TeamsDao teamDaoObject = new TeamsDao();
				List<Teams> teams= teamDaoObject.retrieveAllTeamsRecords();
				Teams teamObject = teamDaoObject.getTeam(teams, teamName);
				TestTeams updateObject = new TestTeams();
				//write an else which updates on admin
				if( getTestTeam (testTeamsResultSet, teamName) == null){
					TestTeams testTeamObject = new TestTeams(testRankingsInt, captainFName, captainLName, coachFName, coachLName,teamObject);
					em.persist(testTeamObject);		
				}
				else{
					List <TestTeams> testTeams = retrieveAllTestTeamsRecords();
					for (TestTeams team : testTeams)
					{
						if(team.getTeamIdentifier().getTeamname().equals(teamName)){
							
							updateObject = team;
							break;
						}
					}
					//set all values to update obj
					updateObject.setTestteamranking(testRankingsInt);
					updateObject.setTestcapfname(captainFName);
					updateObject.setTestcaplname(captainLName);
					updateObject.setTestcoachfname(coachFName);
					updateObject.setTestcoachlname(coachLName);
					em.merge(updateObject);
				}//end of else for merge

				
			}
		}
		em.getTransaction().commit();
		em.close();
		
	}
	
	private URLConnection getConnection(String urlToParse) {
		URLConnection connection = null;
		try {
			URL website = new URL(urlToParse);
			connection = website.openConnection();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return connection;

	}
	
	public TestTeams getTestTeam(List<TestTeams> teams, String teamName) {
		TestTeams resultForTeams = null;
			
			
		for(TestTeams team : teams)
		{
				
			if(team.getTeamIdentifier().getTeamname().equals(teamName)){
					
				resultForTeams = team;
				break;
			}
		}
			
		return resultForTeams;
	}
	
	public TestTeams getTestTeam(int teamId)
	{
		TestTeams returnTeam = new TestTeams();
		List<TestTeams> testTeams = retrieveAllTestTeamsRecords();
		
		for(TestTeams team : testTeams)
		{
			if(team.getTeamIdentifier().getTeamid()== teamId)
			{
				returnTeam = team;
				break;
			}
		}
		return returnTeam;
	}
	
	public List<TestTeams> retrieveAllTestTeamsRecords() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select t from TestTeams t order by t.testteamranking");
		List<TestTeams> testTeams = (List<TestTeams>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return testTeams;
	}
	
   
}
