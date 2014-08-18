package com.cricket;






import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Worldcuphistory implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY.AUTO)
	@Column(name= "worldcuphistoryid")
	private int worldcuphistoryid;
	private String worldcuphistoryhost;
	private Date worldcuphistorydate;
	private String worldcuphistorystatus;
	private String worlcuphistoryteam1;
	private String worldcuphistoryteam2;
	private String worldcuphistorymom;
	private String worldcuphistorymos;
	
	public Worldcuphistory(){}
	//
	
	public Worldcuphistory(String worldcuphistoryhost,Date worldcuphistorydate, 
			String worldcuphistorystatus,
			String worlcuphistoryteam1, String worldcuphistoryteam2,
			String worldcuphistorymom, String worldcuphistorymos) throws ParseException 
	{
		this.worldcuphistoryhost=worldcuphistoryhost;
		this.worldcuphistorydate=worldcuphistorydate;
		this.worldcuphistorystatus=worldcuphistorystatus;
		this.worlcuphistoryteam1=worlcuphistoryteam1;
		this.worldcuphistoryteam2=worldcuphistoryteam2;
		this.worldcuphistorymom=worldcuphistorymom;
		this.worldcuphistorymos=worldcuphistorymos;		
	}
	public int getWorldcuphistoryid() {
		return worldcuphistoryid;
	}
	public void setWorldcuphistoryid(int worldcuphistoryid) {
		this.worldcuphistoryid = worldcuphistoryid;
	}
	public String getWorldcuphistoryhost() {
		return worldcuphistoryhost;
	}
	public void setWorldcuphistoryhost(String worldcuphistoryhost) {
		this.worldcuphistoryhost = worldcuphistoryhost;
	}
	//public Date getWorldcuphistorydate() {
		//return worldcuphistorydate;
//	}
	public void setWorldcuphistorydate(Date date) {
		this.worldcuphistorydate = date;
	}
	public Date getWorldcuphistorydate() {
		return worldcuphistorydate;
	}
	//public void setWorldcuphistorydate(Date date) {
	//	this.worldcuphistorydate = date;
	//}
	public String getWorldcuphistorystatus() {
		return worldcuphistorystatus;
	}
	public void setWorldcuphistorystatus(String worldcuphistorystatus) {
		this.worldcuphistorystatus = worldcuphistorystatus;
	}
	public String getWorlcuphistoryteam1() {
		return worlcuphistoryteam1;
	}
	public void setWorlcuphistoryteam1(String worlcuphistoryteam1) {
		this.worlcuphistoryteam1 = worlcuphistoryteam1;
	}
	public String getWorldcuphistoryteam2() {
		return worldcuphistoryteam2;
	}
	public void setWorldcuphistoryteam2(String worldcuphistoryteam2) {
		this.worldcuphistoryteam2 = worldcuphistoryteam2;
	}
	public String getWorldcuphistorymom() {
		return worldcuphistorymom;
	}
	public void setWorldcuphistorymom(String worldcuphistorymom) {
		this.worldcuphistorymom = worldcuphistorymom;
	}
	public String getWorldcuphistorymos() {
		return worldcuphistorymos;
	}
	public void setWorldcuphistorymos(String worldcuphistorymos) {
		this.worldcuphistorymos = worldcuphistorymos;
	}

	
}
