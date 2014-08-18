package com.cricket;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="UpcomingMatch.findAll", query="select u from UpcomingMatch u")
public class UpcomingMatch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int matchid;
	private String matchtype;
	private String matchseries;
	private Date matchdate;
	private String matchvenue;
	private String matchteam1;
	private String matchteam2;
	
	public UpcomingMatch(){}
	
	public UpcomingMatch(Date date, String matchVenue, String matchTeam1, String matchTeam2, String matchType, String matchSeries){
		this.matchdate = date;
		this.matchvenue = matchVenue;
		this.matchteam1 = matchTeam1;
		this.matchteam2 = matchTeam2;
		this.matchtype = matchType;
		this.matchseries = matchSeries;
	}

	public int getMatchid() {
		return matchid;
	}

	public void setMatchid(int matchid) {
		this.matchid = matchid;
	}

	public Date getMatchdate() {
		return matchdate;
	}

	public void setMatchdate(Date matchdate) {
		this.matchdate = matchdate;
	}

	public String getMatchvenue() {
		return matchvenue;
	}

	public void setMatchvenue(String matchvenue) {
		this.matchvenue = matchvenue;
	}

	public String getMatchteam1() {
		return matchteam1;
	}

	public void setMatchteam1(String matchteam1) {
		this.matchteam1 = matchteam1;
	}

	public String getMatchteam2() {
		return matchteam2;
	}

	public void setMatchteam2(String matchteam2) {
		this.matchteam2 = matchteam2;
	}

	public String getMatchtype() {
		return matchtype;
	}

	public void setMatchtype(String matchtype) {
		this.matchtype = matchtype;
	}

	public String getMatchseries() {
		return matchseries;
	}

	public void setMatchseries(String matchseries) {
		this.matchseries = matchseries;
	}
	
	

}
