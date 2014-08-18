package com.cricket;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserinfoDao {
	
	private static final String PERSISTENCE_UNIT_NAME = "Cricket";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public void createUser(Userinfo user){
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(user);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<Userinfo> getAllUsers(){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select u from Userinfo u");
		List<Userinfo> users = (List<Userinfo>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return users;
	}
	
	public boolean getUser(String userName, String password) throws Exception 
	{
		List<Userinfo> users = getAllUsers();
		boolean value=false;
		
		for(Userinfo user : users)
		{
			value = user.getUserloginname().equals(userName) && user.getUserpassword().equals(password);
			if(value==true) return value;
		}
		
			return value;
	}
	
	public boolean checkUserAdmin(String userName)
	{
		boolean value = false;
		List<Userinfo> users = getAllUsers();
		for(Userinfo user : users)
		{
			if(user.getUserloginname().equals(userName) && user.getIsuseradmin().equalsIgnoreCase("yes"))
			{
				value = true;
				break;
			}
		}
		return value;
	}
	
	public Userinfo getUser(String userName)
	{
		List<Userinfo> users = getAllUsers();
		Userinfo returnUser = new Userinfo();
		for(Userinfo user : users)
		{
			 if(user.getUserloginname().equals(userName)){
				 returnUser = user;
				 break;
			 }
		}
		return returnUser;
	}

}
