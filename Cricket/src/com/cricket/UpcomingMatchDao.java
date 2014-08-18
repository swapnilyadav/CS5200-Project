package com.cricket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Element;

import sun.nio.cs.ext.MacHebrew;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class UpcomingMatchDao {
	
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public UpcomingMatchDao(){}
	
	public void createUpcomingMatch(){
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		// Parse the URL
				URLConnection conn = getConnection("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.upcoming_matches&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0");
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
					
				NodeList matches = doc.getElementsByTagName("Match");
				String matchType = "", matchSeries = "", matchDate = "",  matchVenue = "", matchTeam1 = "", matchTeam2 = "";
				Date matchDateFormatted = null;

				
				for (int i = 0; i < matches.getLength(); i++){
					Node match = matches.item(i);
					if (match.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) match;
						
						matchType = eElement.getAttribute("mtype");
						matchSeries = eElement.getAttribute("series_name");
						
						matchDate = eElement.getElementsByTagName("StartDate").item(0).getTextContent();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date = null;
						try {
							date = formatter.parse(matchDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						matchDateFormatted =new Date(date.getTime());
						
						matchVenue = eElement.getElementsByTagName("Venue").item(0).getTextContent();
						
						Node team1Tag = eElement.getElementsByTagName("Team").item(0);
						
						if (team1Tag.getNodeType() == Node.ELEMENT_NODE){
							
							Element team1Element = (Element) team1Tag;
							
							matchTeam1 = team1Element.getAttribute("Team");
							
						}
						
						Node team2Tag = eElement.getElementsByTagName("Team").item(1);
						
						if (team2Tag.getNodeType() == Node.ELEMENT_NODE){
							
							Element team2Element = (Element) team2Tag;
							
							matchTeam2 = team2Element.getAttribute("Team");				
							
						}
						
						List<UpcomingMatch> upcomingMatchResultSet = retrieveAllUpcomingMatches();
						
						UpcomingMatch updateObject = new UpcomingMatch();
						
						//add else for update on admin
						if( getUpcomingMatch (upcomingMatchResultSet, matchType, matchVenue, matchSeries,
												matchTeam1, matchTeam2, matchDateFormatted) == null){
							UpcomingMatch upcomingMatchObj = new UpcomingMatch(matchDateFormatted, matchVenue, matchTeam1, matchTeam2, matchType, matchSeries);
							em.persist(upcomingMatchObj);
						}			
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
	
	public List<UpcomingMatch> retrieveAllUpcomingMatches() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select u from UpcomingMatch u order by u.matchdate");
		List<UpcomingMatch> matches = (List<UpcomingMatch>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return matches;
	}
	
	public UpcomingMatch getUpcomingMatch(List<UpcomingMatch> matches, String matchType, String matchVenue, String matchSeries,
											String matchTeam1, String matchTeam2, Date matchDate) {
		UpcomingMatch resultForMatch = null;
			
			
		for(UpcomingMatch match : matches)
		{
			
			
			if(match.getMatchteam1().equals(matchTeam1)&&match.getMatchteam2().equals(matchTeam2)&& (match.getMatchdate().compareTo(matchDate) == 0)
					&&match.getMatchtype().equals(matchType) && match.getMatchseries().equals(matchSeries)&& match.getMatchvenue().equals(matchVenue)){
				
				resultForMatch = match;
				break;
			}
		}
			
		return resultForMatch;
	}
	
public static void main (String args[]){
		
		UpcomingMatchDao u = new UpcomingMatchDao();
		u.createUpcomingMatch();
     }

}
