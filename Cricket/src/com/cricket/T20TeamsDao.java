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


public class T20TeamsDao {
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public T20TeamsDao(){
	}
	
	public void createT20Team(){
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
		
		
		NodeList t20teams = doc.getElementsByTagName("Team");
		String t20Rankings, captainFName = "", captainLName = "",  coachFName = "", coachLName = "", teamName = "";
		int t20RankingsInt;

		
		for (int i = 0; i < t20teams.getLength(); i++){
			Node t20team = t20teams.item(i);
			if (t20team.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) t20team;
				
				teamName = eElement.getElementsByTagName("TeamName").item(0).getTextContent();
				
				t20Rankings = eElement.getElementsByTagName("Ranking").item(2).getTextContent();
				t20RankingsInt = Integer.parseInt(t20Rankings);
				
				Node captainTag = eElement.getElementsByTagName("Captain").item(2);
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
				
				List<T20Teams> t20TeamsResultSet = retrieveAllT20TeamsRecords();
				TeamsDao teamDaoObject = new TeamsDao();
				List<Teams> teams= teamDaoObject.retrieveAllTeamsRecords();
				Teams teamObject = teamDaoObject.getTeam(teams, teamName);
				T20Teams updateObject = new T20Teams();
				
				//add else for update on admin
				if( getT20Team (t20TeamsResultSet, teamName) == null){
					T20Teams t20TeamObject = new T20Teams(t20RankingsInt, captainFName, captainLName, coachFName, coachLName,teamObject);
					em.persist(t20TeamObject);
				}
				else{
					List <T20Teams> t20Teams = retrieveAllT20TeamsRecords();
					for (T20Teams team : t20Teams)
					{
						if(team.getTeams().getTeamname().equals(teamName)){
							
							updateObject = team;
							break;
						}
					}
					//set all values to update obj
					updateObject.setT20teamranking(t20RankingsInt);
					updateObject.setT20capfname(captainFName);
					updateObject.setT20caplname(captainLName);
					updateObject.setT20coachfname(coachFName);
					updateObject.setT20coachlname(coachLName);
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
	
	public List<T20Teams> retrieveAllT20TeamsRecords() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select t from T20Teams t order by t.t20teamranking");
		List<T20Teams> t20Teams = (List<T20Teams>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return t20Teams;
	}
	
	public T20Teams getT20Team(List<T20Teams> teams, String teamName) {
		T20Teams resultForTeams = null;
			
			
		for(T20Teams team : teams)
		{
				
			if(team.getTeams().getTeamname().equals(teamName)){
					
				resultForTeams = team;
				break;
			}
		}
			
		return resultForTeams;
	}
	
	
	public T20Teams getT20Team(int teamId)
	{
		T20Teams returnTeam = new T20Teams();
		List<T20Teams> t20Teams = retrieveAllT20TeamsRecords();
		
		for(T20Teams team : t20Teams)
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
		
		T20TeamsDao t = new T20TeamsDao();
		t.createT20Team();
     }
}
