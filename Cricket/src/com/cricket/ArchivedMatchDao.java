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

public class ArchivedMatchDao {

	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public ArchivedMatchDao(){}

	public void createArchivedMatch(){
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		URLConnection conn = getConnection("http://synd.cricbuzz.com/j2me/1.0/livematches.xml");
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

		NodeList matches = doc.getElementsByTagName("match");
		String matchType ="", matchSeries="", matchName="", matchCity="", matchNo="", matchCountry="", matchStadium="", matchStatus="", matchToss="", matchTossDecision="", matchTeam1="",
				matchTeam1Inng1Runs="", matchTeam1Inng1Wickets="", matchTeam1Inng1Overs="", matchTeam1Inng2Runs="", matchTeam1Inng2Wickets="", matchTeam1Inng2Overs="",
				matchTeam2="", matchTeam2Inng1Runs="", matchTeam2Inng1Wickets="", matchTeam2Inng1Overs="", matchTeam2Inng2Runs="", matchTeam2Inng2Wickets="", matchTeam2Inng2Overs="", matchStartDate="",
				matchEndDate="";
		String team1Id = "", team2Id="";
		String matchProgress = "";

		for (int i = 0; i < matches.getLength(); i++){
			Node match = matches.item(i);
			if (match.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) match;

				if (Integer.parseInt(eElement.getAttribute("id"))<=matches.getLength()){
				matchType = eElement.getAttribute("type");
				matchSeries = eElement.getAttribute("srs");
				matchName = eElement.getAttribute("mchDesc");
				matchNo = eElement.getAttribute("mnum");

				if(eElement.getAttribute("vcity")== null || eElement.getAttribute("vcity")==""){
					matchCity = "Not Available";
				}
				else {
					matchCity = eElement.getAttribute("vcity");
				}

				if(eElement.getAttribute("vcountry")== null || eElement.getAttribute("vcountry")== ""){
					matchCountry = "Not Available";
				}
				else{
					matchCountry = eElement.getAttribute("vcountry");
				}

				if (eElement.getAttribute("grnd")== null ||eElement.getAttribute("grnd")== ""){
					matchStadium = "Not Available";
				}
				else{
					matchStadium = eElement.getAttribute("grnd");
				}
				
			
				Node stateTag = eElement.getElementsByTagName("state").item(0);
				if (stateTag.getNodeType() == Node.ELEMENT_NODE){
					Element stateElement = (Element) stateTag;
				
					if(stateElement.getAttribute("status")== null || stateElement.getAttribute("status")==""){
						matchStatus = "Not Available";
					}
					else {
						matchStatus = stateElement.getAttribute("status");
					}
					
					if(stateElement.getAttribute("mchState")== null || stateElement.getAttribute("mchState")==""){
						matchProgress = "Not Available";
					}
					else {
						matchProgress = stateElement.getAttribute("mchState");
					}
					
					if(stateElement.getAttribute("TW")== null || stateElement.getAttribute("TW")==""){
						matchToss = "Not Available";
					}
					else {
						matchToss = stateElement.getAttribute("TW");
					}

					if(stateElement.getAttribute("decisn")== null || stateElement.getAttribute("decisn")==""){
						matchTossDecision = "Not Available";
					}
					else {
						matchTossDecision = stateElement.getAttribute("decisn");
					}
				}
				
				if(matchProgress.equals("complete")|| matchProgress.equals("Result"))
				{

				Node team1Tag = eElement.getElementsByTagName("Tm").item(0);

				if (team1Tag.getNodeType() == Node.ELEMENT_NODE){
					Element team1Element = (Element) team1Tag;

					if(team1Element.getAttribute("sName")== null || team1Element.getAttribute("sName")==""){
						matchTeam1 = "Not Available";
					}
					else {
						matchTeam1 = team1Element.getAttribute("sName");
					}

				}

				Node team2Tag = eElement.getElementsByTagName("Tm").item(1);

				if (team2Tag.getNodeType() == Node.ELEMENT_NODE){
					Element team2Element = (Element) team2Tag;

					if(team2Element.getAttribute("sName")== null || team2Element.getAttribute("sName")==""){
						matchTeam2 = "Not Available";
					}
					else {
						matchTeam2 = team2Element.getAttribute("sName");
					}
				}


				Node matchDateTag = eElement.getElementsByTagName("Tme").item(0);
				if(matchDateTag.getNodeType() == Node.ELEMENT_NODE){
					Element dateElement = (Element) matchDateTag;

					if(dateElement.getAttribute("Dt")== null || dateElement.getAttribute("Dt")==""){
						matchStartDate= null;
					}
					else {
						matchStartDate = dateElement.getAttribute("Dt");
					}

					if(dateElement.getAttribute("enddt")== null || dateElement.getAttribute("enddt")==""){
						matchEndDate= null;
					}
					else {
						matchEndDate = dateElement.getAttribute("enddt");
					}


				}
				
				Node mscrTag = eElement.getElementsByTagName("mscr").item(0);
				if (mscrTag == null){
					matchTeam1Inng1Runs = "Not Available";
					matchTeam1Inng1Wickets= "Not Available";
					matchTeam1Inng1Overs= "Not Available";
					matchTeam1Inng2Runs= "Not Available";
					matchTeam1Inng2Wickets= "Not Available";
					matchTeam1Inng2Overs= "Not Available";

					matchTeam2Inng1Runs = "Not Available";
					matchTeam2Inng1Wickets= "Not Available";
					matchTeam2Inng1Overs= "Not Available";
					matchTeam2Inng2Runs= "Not Available";
					matchTeam2Inng2Wickets= "Not Available";
					matchTeam2Inng2Overs= "Not Available";
				}
				else{
					if(mscrTag.getNodeType() == Node.ELEMENT_NODE){
						Element mscrElement = (Element) mscrTag;
						
						//Batting Team Data
						
						Node battingTeamTag = mscrElement.getElementsByTagName("btTm").item(0);
						
						if (battingTeamTag.getNodeType() == Node.ELEMENT_NODE){
							Element btTmElement = (Element)battingTeamTag;
							
							if(btTmElement.getAttribute("sName")== null || btTmElement.getAttribute("sName")==""){
								matchTeam1= "Not Available";
							}
							else {
								matchTeam1 = btTmElement.getAttribute("sName");
							}
							
							// First "Inngs " tag encountered - item(0)
							if (btTmElement.getElementsByTagName("Inngs").item(0)!=null){
							Node Innings1Tag = btTmElement.getElementsByTagName("Inngs").item(0);
							if (Innings1Tag.getNodeType() == Node.ELEMENT_NODE){
								Element inn1Element = (Element)Innings1Tag;
								
								if(inn1Element.getAttribute("desc").equals("2nd Inns")){
									matchTeam1Inng2Runs= inn1Element.getAttribute("r");
									matchTeam1Inng2Wickets= inn1Element.getAttribute("wkts");
									matchTeam1Inng2Overs= inn1Element.getAttribute("ovrs");
								}
								else if((inn1Element.getAttribute("desc").equals("1st Inns"))||inn1Element.getAttribute("desc").equals("Inns")){
									matchTeam1Inng1Runs = inn1Element.getAttribute("r");
									//System.out.println("Runs"+matchT);
									matchTeam1Inng1Wickets= inn1Element.getAttribute("wkts");
									matchTeam1Inng1Overs= inn1Element.getAttribute("ovrs");
									matchTeam1Inng2Runs= "Not Available";
									matchTeam1Inng2Wickets= "Not Available";
									matchTeam1Inng2Overs= "Not Available";
								}
								
							}
							} //end of new if cond
							
							// Second "Inngs" tag encountered - item(1)
							if (btTmElement.getElementsByTagName("Inngs").item(1)!=null){
							Node Innings2Tag = btTmElement.getElementsByTagName("Inngs").item(1);
							if (Innings2Tag.getNodeType() == Node.ELEMENT_NODE){
								Element inn2Element = (Element)Innings2Tag;
								
								if(inn2Element.getAttribute("desc").equals("1st Inns")){
									matchTeam1Inng1Runs= inn2Element.getAttribute("r");
									matchTeam1Inng1Wickets= inn2Element.getAttribute("wkts");
									matchTeam1Inng1Overs= inn2Element.getAttribute("ovrs");
								}							
							 }
							} //End of new if check
						} //End of batting tag if
							
							//Bowling Team Data
							Node bowlingTeamTag = mscrElement.getElementsByTagName("blgTm").item(0);
							
							if (bowlingTeamTag.getNodeType() == Node.ELEMENT_NODE){
								Element blgTmElement = (Element)bowlingTeamTag;
								
								if(blgTmElement.getAttribute("sName")== null || blgTmElement.getAttribute("sName")==""){
									matchTeam2= "Not Available";
								}
								else {
									matchTeam2 = blgTmElement.getAttribute("sName");
								}
								
								// First "Inngs " tag encountered - item(0)
								if (blgTmElement.getElementsByTagName("Inngs").item(0)!=null){
								Node BlgInnings1Tag = blgTmElement.getElementsByTagName("Inngs").item(0);
								if (BlgInnings1Tag.getNodeType() == Node.ELEMENT_NODE){
									Element blgInn1Element = (Element)BlgInnings1Tag;
									
									if(blgInn1Element.getAttribute("desc").equals("2nd Inns")){
										matchTeam2Inng2Runs= blgInn1Element.getAttribute("r");
										matchTeam2Inng2Wickets= blgInn1Element.getAttribute("wkts");
										matchTeam2Inng2Overs= blgInn1Element.getAttribute("ovrs");
									}
									else if((blgInn1Element.getAttribute("desc").equals("1st Inns"))||blgInn1Element.getAttribute("desc").equals("Inns")){
										matchTeam2Inng1Runs = blgInn1Element.getAttribute("r");
										matchTeam2Inng1Wickets= blgInn1Element.getAttribute("wkts");
										matchTeam2Inng1Overs= blgInn1Element.getAttribute("ovrs");
										matchTeam2Inng2Runs= "Not Available";
										matchTeam2Inng2Wickets= "Not Available";
										matchTeam2Inng2Overs= "Not Available";
									}
									
								}
								} //end of new if check
								
								// Second "Inngs" tag encountered - item(1)
								if (blgTmElement.getElementsByTagName("Inngs").item(1)!=null){
								Node BlgInnings2Tag = blgTmElement.getElementsByTagName("Inngs").item(1);
								if (BlgInnings2Tag.getNodeType() == Node.ELEMENT_NODE){
									Element blginn2Element = (Element)BlgInnings2Tag;
									
									if(blginn2Element.getAttribute("desc").equals("1st Inns")){
										matchTeam2Inng1Runs= blginn2Element.getAttribute("r");
										matchTeam2Inng1Wickets= blginn2Element.getAttribute("wkts");
										matchTeam2Inng1Overs= blginn2Element.getAttribute("ovrs");
									}							
								}
							} //end of new if check
						} //End of bowling team tag
					} //end of mscr tag check
				} //else of mscr tag
				
				List<ArchivedMatch> archivedMatchResultSet = retrieveAllArchivedMatches();
				
				ArchivedMatch updateObject = new ArchivedMatch();
				
				//add else for update on admin
				if( getArchivedMatch (archivedMatchResultSet, matchType, matchName, matchNo, matchSeries,
										matchTeam1, matchTeam2, matchStartDate, matchEndDate) == null){
					ArchivedMatch archivedMatchObj = new ArchivedMatch(matchType , matchSeries, matchName, matchNo, 
							matchCity, matchCountry, matchStadium, matchStatus, matchToss, matchTossDecision, 
							matchTeam1, matchTeam1Inng1Runs, matchTeam1Inng1Wickets, matchTeam1Inng1Overs, 
							matchTeam1Inng2Runs, matchTeam1Inng2Wickets, matchTeam1Inng2Overs, matchTeam2, 
							matchTeam2Inng1Runs, matchTeam2Inng1Wickets, matchTeam2Inng1Overs, matchTeam2Inng2Runs, 
							matchTeam2Inng2Wickets, matchTeam2Inng2Overs, matchStartDate, matchEndDate);
					em.persist(archivedMatchObj);
				}		
				
				} //end of matchProgress check
				} //end of length check of matches
			}	//End of match tag
		} //end of for loop of matches
		em.getTransaction().commit();
		em.close();	
	} //end of func


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
	
	public List<ArchivedMatch> retrieveAllArchivedMatches() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from ArchivedMatch a");
		List<ArchivedMatch> matches = (List<ArchivedMatch>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return matches;
	}
	
	public ArchivedMatch retrieveArchivedMatchForId(String matchId)
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		ArchivedMatch matchInfo = em.find(ArchivedMatch.class, Integer.parseInt(matchId));

		em.getTransaction().commit();
		em.close();

		return matchInfo;
	}
	
	public ArchivedMatch getArchivedMatch(List<ArchivedMatch> matches, String matchType, String matchName, String matchNo, String matchSeries, 
											String matchTeam1, String matchTeam2, String matchStartDate, String matchEndDate) {
		ArchivedMatch resultForMatch = null;
		
		
		for(ArchivedMatch match : matches)
		{
			if(match.getArchivedmteam1().equals(matchTeam1)&&match.getArchivedmteam2().equals(matchTeam2)&& match.getArchivedmstartdate().equals(matchStartDate)
			 &&match.getArchivedmenddate().equals(matchEndDate) && match.getArchivedmatchtype().equals(matchType) && match.getArchivedmatchname().equals(matchName)
			 &&match.getArchivedmatchno().equals(matchNo)&&match.getArchivedmatchseries().equals(matchSeries)){
			
				resultForMatch = match;
				break;
			}
		}
		
		return resultForMatch;
	}


	public static void main (String args[]){

		ArchivedMatchDao a = new ArchivedMatchDao();
		a.createArchivedMatch();
	}
}

