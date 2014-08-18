package com.cricket;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CommentsDao {
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public void addComment(Comments comment){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(comment);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<Comments> getAllComments()
	{
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select c from Comments c");
		List<Comments> comments = (List<Comments>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return comments;
	}
	
	public List<Comments> getComments(String userName)
	{
		UserinfoDao userDao = new UserinfoDao();
		Userinfo user = userDao.getUser(userName);
		
		List<Comments> comments = getAllComments();
		List<Comments> returnComments = new ArrayList<Comments>();
		for(Comments comment : comments)
		{
			if(comment.getUser().getUserid() == user.getUserid())
			{
				returnComments.add(comment);
			}
		}
		return returnComments;
	}
	

}
