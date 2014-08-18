package com.cricket;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetInfoDao {
	
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public void createTweets()
	{
		EntityManager em = factory.createEntityManager();
		Twitter twitter = new TwitterFactory().getInstance();
		 
		 twitter.setOAuthConsumer("Zkj2O1mFotaxs2hCxxIqEwPal", "JNw1TlTNt1N5O4tZvRwuhwMbYwPTLJ995o0FRFCHBA3TR6HibN");
		 twitter.setOAuthAccessToken(new AccessToken("2192674526-Avpfp26pLs5vNSmLHAOHyeUbjxpx1BnuFJcsMwB", "ieZyVlwYwFJxyWoPUPHOpV1ZNaPBzMXJrHsFAEDmgBaDM"));
		 List<Status> statusList = null;
		
		em.getTransaction().begin();
		
		try{
			
			statusList = twitter.getUserTimeline("@ESPNcricinfo");
			String check = "RT";
			for(Status status : statusList){
				if(!status.getText().contains(check)){
					java.util.Date utilDate = status.getCreatedAt();
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					String tweetName = status.getUser().getName();
					String tweetText = status.getText();
					String tweetUserName = "ESPNcricinfo";
					List<TweetInfo> tweets = readAllTweets();
					if(getTweet(tweets,tweetText) == null){
						System.out.println("inserting tweets");
						TweetInfo tweet = new TweetInfo(tweetName,tweetUserName,sqlDate,tweetText);
						em.persist(tweet);
						
					}
					
						
					
					
				}
				
				
			}
			
			
		}
		catch(TwitterException te){
			te.printStackTrace();
	         System.out.println("Failed to search tweets: " + te.getMessage());
	         
		}
		finally{
			em.getTransaction().commit();
			em.close();
		}
		
	}
	
	public List<TweetInfo> readTweets()
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		Query query = em.createQuery("select t from TweetInfo t where t.tweetdate = '" +date+ "'");
		List<TweetInfo> tweets = (List<TweetInfo>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return tweets;
	}
	
	public List<TweetInfo> readAllTweets()
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		Query query = em.createQuery("select t from TweetInfo t");
		List<TweetInfo> tweets = (List<TweetInfo>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return tweets;
	}
	
	public TweetInfo getTweet(List<TweetInfo> tweets, String tweetText)
	{
		TweetInfo returnTweet = null;
		
		
		for(TweetInfo tweet : tweets)
		{
			
			if(tweet.getTweettext().equals(tweetText)){
				
				returnTweet = tweet;
				break;
			}
		}
		
		return returnTweet;
	}
	
	public TweetInfo getTweet(String tweetid)
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		TweetInfo tweetInfo = em.find(TweetInfo.class, Integer.parseInt(tweetid));

		em.getTransaction().commit();
		em.close();

		return tweetInfo;
	}
/*	
	public static void main(String[] args){
		
		TweetInfoDao dao = new TweetInfoDao();
		dao.createTweets();
	}
	
*/
}
