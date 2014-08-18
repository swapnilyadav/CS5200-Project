package com.cricket;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.sun.org.apache.xml.internal.security.utils.Base64;

@Entity
@NamedQuery(name="User.findAll", query="select u from Userinfo u")
public class Userinfo implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;
	private String userfirstname;
	private String userlastname;
	private String userloginname;
	private String userpassword;
	private Date userdob;
	private String useremail;
	private String isuseradmin;
	
	@OneToMany(mappedBy="user")
	private List<Comments> comments;
	
	public Userinfo(){}
	
	public Userinfo(String userfirstname, String userlastname,
			String userloginname, String userpassword, Date userdob,
			String useremail, String isuseradmin) {
		super();
		this.userfirstname = userfirstname;
		this.userlastname = userlastname;
		this.userloginname = userloginname;
		try
	    {
	      MessageDigest md = MessageDigest.getInstance("SHA-256");
	      md.update(userpassword.getBytes());

	      byte byteData[] = md.digest();
	      this.userpassword=new String(Base64.encode(byteData));
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	      throw new RuntimeException(e);
	    }
		
		this.userdob = userdob;
		this.useremail = useremail;
		this.isuseradmin = isuseradmin;
	}
	
	public Date getUserdob() {
		return userdob;
	}

	public void setUserdob(Date userdob) {
		this.userdob = userdob;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserfirstname() {
		return userfirstname;
	}
	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}
	public String getUserlastname() {
		return userlastname;
	}
	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}
	public String getUserloginname() {
		return userloginname;
	}
	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}
	public String getUserpassword(){
		
		return userpassword;	
	     
	}
	public void setUserpassword(String userpassword) {
		try
	    {
	      MessageDigest md = MessageDigest.getInstance("SHA-256");
	      md.update(userpassword.getBytes());

	      byte byteData[] = md.digest();

	      
	      this.userpassword=new String(Base64.encode(byteData));
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	      throw new RuntimeException(e);
	    }
	}
	
	public boolean verifypassword(String username,String enteredusername,String password, String enteredpassword) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(enteredpassword.getBytes());
	    byte byteData[] = md.digest();
 	    String pswdhash=new String(Base64.encode(byteData));
 	    boolean value = (username.equals(enteredusername) && password.equals(pswdhash));
 	    return value;
	}
	
	public Date getDob() {
		return userdob;
	}
	public void setDob(Date dob) {
		this.userdob = dob;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getIsuseradmin() {
		return isuseradmin;
	}
	public void setIsuseradmin(String isuseradmin) {
		this.isuseradmin = isuseradmin;
	}
	
	
	

}
