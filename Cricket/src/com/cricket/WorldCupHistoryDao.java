package com.cricket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class WorldCupHistoryDao {
	
	public WorldCupHistoryDao()
	{
		/*EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("delete from Worldcuphistory");
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();*/
	}
	
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	 public void parseCSVFileLineByLine() throws Exception {
		 
		
	        
	        	String csvFile = "C:/Users/Swapnil/Desktop/worldcups (1).csv";
	        	System.out.println(System.getProperty("user.dir"));
	        	BufferedReader br = null;
	        	String line = "";
	        	String cvsSplitBy = ",";
	         
	        	try {
	         
	        		br = new BufferedReader(new FileReader(csvFile));
	        		while ((line = br.readLine()) != null) {
	         
	        		        
	        			String[] data = line.split(cvsSplitBy);
	        			
	
	    	            String Worldcuphistoryhost=data[1];
	    	            String strdate=(data[2]);
	    	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    				Date parsed = formatter.parse(strdate);
	    				java.sql.Date worldcuphistorydate = new java.sql.Date(parsed.getTime());
	    	            String Worldcuphistorystatus=data[3];
	    				String Worlcuphistoryteam1=data[4];
	    				String Worldcuphistoryteam2=data[5];
	    				String Worldcuphistorymom=data[6];
	    				String Worldcuphistorymos=data[7];
	    				
	    				
	    				savewch(Worldcuphistoryhost,worldcuphistorydate,Worldcuphistorystatus,
	    	            		Worlcuphistoryteam1,Worldcuphistoryteam2,Worldcuphistorymom,Worldcuphistorymos);
	    				
	    				
	        		}	
	        		
	        		
	        		}
	        		
	        		catch(Exception e)
	    			{
	    				e.printStackTrace();
	    			}
	        	}
	         
	 
	 public void savewch(String worldcuphistoryhost,Date worldcuphistorydate,String worldcuphistorystatus,String worlcuphistoryteam1, String worldcuphistoryteam2,
			String worldcuphistorymom, String worldcuphistorymos)
			{
		 EntityManager em = factory.createEntityManager();
		 try
			{
		 Worldcuphistory wch = new Worldcuphistory(worldcuphistoryhost,(java.sql.Date) worldcuphistorydate,worldcuphistorystatus,
				 worlcuphistoryteam1,worldcuphistoryteam2,worldcuphistorymom,worldcuphistorymos);
		
				System.out.println(wch.toString());
			
				em.getTransaction().begin();
				em.persist(wch);
				em.getTransaction().commit();
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			em.close();
		 
			}
	        
	 public List<Worldcuphistory> readData()
 	{
 		EntityManager em = factory.createEntityManager();
 		em.getTransaction().begin();
 		
 		Query query = em.createQuery("select w from Worldcuphistory w order by w.worldcuphistorydate");
 		List<Worldcuphistory> wch = (List<Worldcuphistory>)query.getResultList();
 		
 		
 		em.getTransaction().commit();
 		em.close();
 		
 		return wch;
 	}
	 
	public static void main(String[] args)
	{
		WorldCupHistoryDao wchdao = new WorldCupHistoryDao();
		try {
			wchdao.parseCSVFileLineByLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
	}


