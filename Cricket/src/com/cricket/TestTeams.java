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
@NamedQuery(name="TestTeams.findAll", query="select t from TestTeams t")
public class TestTeams implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int testteamid;
	private int testteamranking;
	private String testcapfname;
	private String testcaplname;
	private String testcoachfname;
	private String testcoachlname;
	
	@OneToOne
	@JoinColumn(name="Teams_teamid")
	private Teams teams;
	
	@OneToOne(mappedBy="testTeam")
	private Player testPlayer;
	
	
	
	
	
	
	public Teams getTeams() {
		return teams;
	}

	public void setTeams(Teams teams) {
		this.teams = teams;
	}

	public Player getTestPlayer() {
		return testPlayer;
	}

	public void setTestPlayer(Player testPlayer) {
		this.testPlayer = testPlayer;
	}

	

	


	public TestTeams(int testteamranking, String testcapfname,
			String testcaplname, String testcoachfname, String testcoachlname,
			Teams teams) {
		super();
		this.testteamranking = testteamranking;
		this.testcapfname = testcapfname;
		this.testcaplname = testcaplname;
		this.testcoachfname = testcoachfname;
		this.testcoachlname = testcoachlname;
		this.teams = teams;
		
	}

	public TestTeams(){
		
	}
	
	public int getTestteamid() {
		return testteamid;
	}
	public void setTestteamid(int testteamid) {
		this.testteamid = testteamid;
	}
	public int getTestteamranking() {
		return testteamranking;
	}
	public void setTestteamranking(int testteamranking) {
		this.testteamranking = testteamranking;
	}
	public String getTestcapfname() {
		return testcapfname;
	}
	public void setTestcapfname(String testcapfname) {
		this.testcapfname = testcapfname;
	}
	public String getTestcaplname() {
		return testcaplname;
	}
	public void setTestcaplname(String testcaplname) {
		this.testcaplname = testcaplname;
	}
	public String getTestcoachfname() {
		return testcoachfname;
	}
	public void setTestcoachfname(String testcoachfname) {
		this.testcoachfname = testcoachfname;
	}
	public String getTestcoachlname() {
		return testcoachlname;
	}
	public void setTestcoachlname(String testcoachlname) {
		this.testcoachlname = testcoachlname;
	}
	public Teams getTeamIdentifier() {
		return teams;
	}
	public void setTeamIdentifier(Teams teamIdentifier) {
		this.teams = teamIdentifier;
	}
	
	

}
