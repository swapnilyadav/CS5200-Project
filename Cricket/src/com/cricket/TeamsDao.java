package com.cricket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLConnection;
import java.net.URL;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Element;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class TeamsDao {
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public TeamsDao(){
	}
	
	public void createTeam()
	{
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		// Parse the URL
		URLConnection conn = getConnection("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.teams&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0");
		Document doc = null;
		String xmlrecords = "";
		String teamName = "";
		int teamId = 0;
		
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
		
		
		NodeList teams = doc.getElementsByTagName("Team");

		
		for (int i = 0; i < teams.getLength(); i++){
			Node team = teams.item(i);
			if (team.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) team;
	
				teamName = eElement.getElementsByTagName("TeamName").item(0).getTextContent();
				
				
				
				}
			if (team.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) team;
	
				teamId = Integer.parseInt(eElement.getElementsByTagName("TeamId").item(0).getTextContent());
				
				
				
				}
			List<Teams> teamsResultSet = retrieveAllTeamsRecords();
			if(getTeam(teamsResultSet,teamName) == null){
				Teams teamObject = new Teams(teamName,teamId);
				em.persist(teamObject);
				
			}
		}
		em.getTransaction().commit();
		em.close();

	}

	public Teams getTeam(List<Teams> teams, String teamName) {
		Teams resultForTeams = null;
		
		
		for(Teams team : teams)
		{
			
			if(team.getTeamname().equals(teamName)){
				
				resultForTeams = team;
				break;
			}
		}
		
		return resultForTeams;
	}
	
	public Teams getTeam(String teamName) {
		Teams resultForTeams = null;
		List<Teams> teams = retrieveAllTeamsRecords();
		
		for(Teams team : teams)
		{
			
			if(team.getTeamname().equals(teamName)){
				
				resultForTeams = team;
				break;
			}
		}
		
		return resultForTeams;
	}
	

	public List<Teams> retrieveAllTeamsRecords() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select t from Teams t");
		List<Teams> teams = (List<Teams>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return teams;
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
	
	//public static void main (String args[]){
		
		//TeamsDao t = new TeamsDao();
		//t.createTeam();
//	}
}

