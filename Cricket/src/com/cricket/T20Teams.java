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
@NamedQuery(name="T20Teams.findAll", query="select t from T20Teams t")
public class T20Teams {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int t20teamid;
	private int t20teamranking;
	private String t20capfname;
	private String t20caplname;
	private String t20coachfname;
	private String t20coachlname;
	
	@OneToOne
	@JoinColumn(name="Teams_teamid")
	private Teams teams;
	

	@OneToOne(mappedBy="t20Team")
	private Player t20Player;
	
	

	public Player getT20Player() {
		return t20Player;
	}

	public void setT20Player(Player t20Player) {
		this.t20Player = t20Player;
	}

	



	public T20Teams(int t20teamranking, String t20capfname, String t20caplname,
			String t20coachfname, String t20coachlname, Teams teams) {
		super();
		this.t20teamranking = t20teamranking;
		this.t20capfname = t20capfname;
		this.t20caplname = t20caplname;
		this.t20coachfname = t20coachfname;
		this.t20coachlname = t20coachlname;
		this.teams = teams;
		
	}

	public T20Teams(){
		
	}

	public int getT20teamid() {
		return t20teamid;
	}

	public void setT20teamid(int t20teamid) {
		this.t20teamid = t20teamid;
	}

	public int getT20teamranking() {
		return t20teamranking;
	}

	public void setT20teamranking(int t20teamranking) {
		this.t20teamranking = t20teamranking;
	}

	public String getT20capfname() {
		return t20capfname;
	}

	public void setT20capfname(String t20capfname) {
		this.t20capfname = t20capfname;
	}

	public String getT20caplname() {
		return t20caplname;
	}

	public void setT20caplname(String t20caplname) {
		this.t20caplname = t20caplname;
	}

	public String getT20coachfname() {
		return t20coachfname;
	}

	public void setT20coachfname(String t20coachfname) {
		this.t20coachfname = t20coachfname;
	}

	public String getT20coachlname() {
		return t20coachlname;
	}

	public void setT20coachlname(String t20coachlname) {
		this.t20coachlname = t20coachlname;
	}

	public Teams getTeams() {
		return teams;
	}

	public void setTeams(Teams teams) {
		this.teams = teams;
	}
	
	

}
