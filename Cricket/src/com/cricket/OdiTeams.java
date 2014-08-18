package com.cricket;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name="OdiTeams.findAll", query="select o from OdiTeams o")
public class OdiTeams {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int oditeamid;
	private int oditeamranking;
	private String odicapfname;
	private String odicaplname;
	private String odicoachfname;
	private String odicoachlname;
	
	@OneToOne
	@JoinColumn(name="Teams_teamid")
	private Teams teams;


	@OneToOne(mappedBy="OdiTeam")
	private Player odiPlayer;


	
	public Player getOdiPlayer() {
		return odiPlayer;
	}

	public void setOdiPlayer(Player odiPlayer) {
		this.odiPlayer = odiPlayer;
	}
	
	


	public OdiTeams(int oditeamranking, String odicapfname, String odicaplname,
			String odicoachfname, String odicoachlname, Teams teams) {
		super();
		this.oditeamranking = oditeamranking;
		this.odicapfname = odicapfname;
		this.odicaplname = odicaplname;
		this.odicoachfname = odicoachfname;
		this.odicoachlname = odicoachlname;
		this.teams = teams;
		
	}

	public OdiTeams(){
		
	}

	public int getOditeamid() {
		return oditeamid;
	}

	public void setOditeamid(int oditeamid) {
		this.oditeamid = oditeamid;
	}

	public int getOditeamranking() {
		return oditeamranking;
	}

	public void setOditeamranking(int oditeamranking) {
		this.oditeamranking = oditeamranking;
	}

	public String getOdicapfname() {
		return odicapfname;
	}

	public void setOdicapfname(String odicapfname) {
		this.odicapfname = odicapfname;
	}

	public String getOdicaplname() {
		return odicaplname;
	}

	public void setOdicaplname(String odicaplname) {
		this.odicaplname = odicaplname;
	}

	public String getOdicoachfname() {
		return odicoachfname;
	}

	public void setOdicoachfname(String odicoachfname) {
		this.odicoachfname = odicoachfname;
	}

	public String getOdicoachlname() {
		return odicoachlname;
	}

	public void setOdicoachlname(String odicoachlname) {
		this.odicoachlname = odicoachlname;
	}

	public Teams getTeams() {
		return teams;
	}

	public void setTeams(Teams teams) {
		this.teams = teams;
	}
	
	

}
