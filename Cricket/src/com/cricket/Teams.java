package com.cricket;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name="Teams.findAll", query="select t from Teams t")
public class Teams implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int teamid;
	private String teamname;
	private int yahooteamid;
	
	@OneToOne(mappedBy="teams")
	private TestTeams testTeamIdentifier;
	
	public int getYahooteamid() {
		return yahooteamid;
	}

	public void setYahooteamid(int yahooteamid) {
		this.yahooteamid = yahooteamid;
	}
	@OneToOne(mappedBy="teams")
	private OdiTeams odiTeamIdentifier;
	
	
	public Teams(String teamName, int yahooTeamId) {
		this.teamname = teamName;
		this.yahooteamid = yahooTeamId;
	}
	
	public Teams (){
	}
	
	public int getTeamid() {
		return teamid;
	}
	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
		
}
