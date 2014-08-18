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

public class OdiTeamsDao {
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public OdiTeamsDao(){
	}
	
	public void createOdiTeam(){
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
		
		
		NodeList oditeams = doc.getElementsByTagName("Team");
		String odiRankings, captainFName = "", captainLName = "",  coachFName = "", coachLName = "", teamName = "";
		int odiRankingsInt;

		
		for (int i = 0; i < oditeams.getLength(); i++){
			Node oditeam = oditeams.item(i);
			if (oditeam.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) oditeam;
				
				teamName = eElement.getElementsByTagName("TeamName").item(0).getTextContent();
				
				odiRankings = eElement.getElementsByTagName("Ranking").item(1).getTextContent();
				odiRankingsInt = Integer.parseInt(odiRankings);
				
				Node captainTag = eElement.getElementsByTagName("Captain").item(1);
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
				
				List<OdiTeams> odiTeamsResultSet = retrieveAllOdiTeamsRecords();
				TeamsDao teamDaoObject = new TeamsDao();
				List<Teams> teams= teamDaoObject.retrieveAllTeamsRecords();
				Teams teamObject = teamDaoObject.getTeam(teams, teamName);
				OdiTeams updateObject = new OdiTeams();
				//write an else for admin func
				if( getOdiTeam (odiTeamsResultSet, teamName) == null){
				OdiTeams odiTeamObject = new OdiTeams(odiRankingsInt, captainFName, captainLName, coachFName, coachLName,teamObject);
				em.persist(odiTeamObject);
				
				}
				else{
					List <OdiTeams> odiTeams = retrieveAllOdiTeamsRecords();
					for (OdiTeams team : odiTeams)
					{
						if(team.getTeams().getTeamname().equals(teamName)){
							
							updateObject = team;
							break;
						}
					}
					//set all values to update obj
					updateObject.setOditeamranking(odiRankingsInt);
					updateObject.setOdicapfname(captainFName);
					updateObject.setOdicaplname(captainLName);
					updateObject.setOdicoachfname(coachFName);
					updateObject.setOdicoachlname(coachLName);
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
	
	public List<OdiTeams> retrieveAllOdiTeamsRecords() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select o from OdiTeams o order by o.oditeamranking");
		List<OdiTeams> odiTeams = (List<OdiTeams>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return odiTeams;
	}
	
	public OdiTeams getOdiTeam(List<OdiTeams> teams, String teamName) {
		OdiTeams resultForTeams = null;
			
			
		for(OdiTeams team : teams)
		{
				
			if(team.getTeams().getTeamname().equals(teamName)){
					
				resultForTeams = team;
				break;
			}
		}
			
		return resultForTeams;
	}
	
	public OdiTeams getOdiTeam(int teamId)
	{
		OdiTeams returnTeam = new OdiTeams();
		List<OdiTeams> odiTeams = retrieveAllOdiTeamsRecords();
		
		for(OdiTeams team : odiTeams)
		{
			if(team.getTeams().getTeamid()== teamId)
			{
				returnTeam = team;
				break;
			}
		}
		return returnTeam;
	}
	
     public static void main (String args[]){
		
		OdiTeamsDao t = new OdiTeamsDao();
		t.createOdiTeam();
     }

}
