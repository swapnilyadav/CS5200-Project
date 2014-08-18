package com.cricket;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
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



public class NewsDao
{
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	URL url;
	
	public NewsDao()
	{
		/*EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("delete from News");
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();*/
	}

	public NewsDao(URL url2) {
		this.url=url2;
	}

	
	
	public void createNews()
	{
		URL url;
		try {
			url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20cricket.news(0%2C50)%20%20where%20region%3D%22in%22%20%20or%20region%3D%22aus%22%20or%20region%3D%22sa%22%20or%20region%3D%22pak%22%20or%20region%3D%22eng%22%20or%20region%3D%22sl%22%20or%20region%3D%22nz%22%20or%20region%3D%22wi%22%20or%20region%3D%22zim%22%20or%20region%3D%22ban%22&diagnostics=true&env=store%3A%2F%2F0TxIGQMQbObzvU4Apia0V0");
			NewsDao ex = new NewsDao(url);
			String res=ex.getData();
			InputStream result = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(result);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");
						
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				 
				Node nNode = nList.item(temp);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					String link = eElement.getElementsByTagName("link").item(0).getTextContent();
					String pubDate = eElement.getElementsByTagName("pubDate").item(0).getTextContent();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SS:SS");
					java.util.Date dd = formatter.parse(pubDate);
					
					Date date=new Date(dd.getTime());
					//Timestamp sqlTime=new Timestamp(date.getTime());
					String title = eElement.getElementsByTagName("title").item(0).getTextContent();
					//ex.saveNews(link, sqlTime, title);
					ex.saveNews(link, date, title);
				}
				
			}
			
					
					
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getData() throws Exception
	{
		
		StringBuilder buffer;
		InputStream input;
		String line;
		int responsecode;
		BufferedReader reader;
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		responsecode = connection.getResponseCode();
		if(responsecode != HttpURLConnection.HTTP_OK)
		{
			throw new Exception("Http Response is" + String.valueOf(responsecode));
		}
		
		try
		{
			buffer = new StringBuilder();
			input = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
			while((line=reader.readLine())!=null)
			{
				buffer.append(line);
				buffer.append("\r \n");
			}
			input.close();
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
		return null;
		}
	//System.out.println(buffer);
	return buffer.toString();	
	}
	
	public News getNews(String newsid)
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		News news = em.find(News.class, Integer.parseInt(newsid));

		em.getTransaction().commit();
		em.close();

		return news;
	}
	
	private void saveNews(String link, Date date, String title)
	{
		List<News> news = readNews();
		if(getNews(news,title) == null)
		{
			
			News newNews = new News(link, (java.sql.Date) date, title);
	
		EntityManager em = factory.createEntityManager();
		try
		{
			em.getTransaction().begin();
			em.persist(newNews);
			em.getTransaction().commit();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		em.close();
		
	}
		
	}
	
		public News getNews(List<News> news, String title)
		{
			News returnNews = null;
			
			for(News archivednews : news)
			{
				
				if(archivednews.getArchivedNewsTitle().equals(title)){
					
					returnNews = archivednews;
					break;
				}
			}
			
			return returnNews;
		}
		
	public List<News> readNews()
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select news from News news order by news.ArchivedNewsDateAndTime desc");
		List<News> news = (List<News>)query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		
		return news;
	}
	
	public List<News> getNews(Date date)
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select news from News news where news.ArchivedNewsDateAndTime=:date");
		query.setParameter("date", date);
		List<News> news = (List<News>)query.getResultList();
		System.out.println(news);
		
		em.getTransaction().commit();
		em.close();
		
		return news;
	}
	

	public Date returnDate(Timestamp datetime)
	{
		
		 Date date = new Date(datetime.getTime());
		return date;
	}
	
	
}
