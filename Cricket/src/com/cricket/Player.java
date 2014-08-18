package com.cricket;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


@Entity
@NamedQuery(name="Player.findAll", query="select u from Userinfo u")
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int playerid;
	
	private String playerName;
	private Date playersDob;
	private String birthCity;
	private String playerGender;
	private String playerBattingStyle;
	public String getPlayerImage() {
		return playerImage;
	}
	public void setPlayerImage(String playerImage) {
		this.playerImage = playerImage;
	}
	private String playerBowlingStyle;
	private String playerImage;
	
	@OneToOne
	@JoinColumn(name="TestTeams_testteamid")
	private TestTeams testTeam;


	@OneToOne
	@JoinColumn(name="T20Teams_t20teamid")
	private T20Teams t20Team;


	@OneToOne
	@JoinColumn(name="OdiTeams_oditeamid")
	private OdiTeams OdiTeam;
	
	
	public TestTeams getTestTeam() {
		return testTeam;
	}
	public void setTestTeam(TestTeams testTeam) {
		this.testTeam = testTeam;
	}
	
	public T20Teams getT20Team() {
		return t20Team;
	}
	public void setT20Team(T20Teams t20Team) {
		this.t20Team = t20Team;
	}
	
	public OdiTeams getOdiTeam() {
		return OdiTeam;
	}
	public void setOdiTeam(OdiTeams odiTeam) {
		OdiTeam = odiTeam;
	}


	public int getPlayerid() {
		return playerid;
	}
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public Date getPlayersDob() {
		return playersDob;
	}
	public void setPlayersDob(Date playersDob) {
		this.playersDob = playersDob;
	}
	public String getBirthCity() {
		return birthCity;
	}
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}
	public String getPlayerGender() {
		return playerGender;
	}
	public void setPlayerGender(String playerGender) {
		this.playerGender = playerGender;
	}
	public String getPlayerBattingStyle() {
		return playerBattingStyle;
	}
	public void setPlayerBattingStyle(String playerBattingStyle) {
		this.playerBattingStyle = playerBattingStyle;
	}
	public String getPlayerBowlingStyle() {
		return playerBowlingStyle;
	}
	public void setPlayerBowlingStyle(String playerBowlingStyle) {
		this.playerBowlingStyle = playerBowlingStyle;
	}
	
	
	


	
	public Player(String playerName, Date playersDob, String birthCity,
			String playerGender, String playerBattingStyle,
			String playerBowlingStyle, String playerImage, TestTeams testTeam,
			T20Teams t20Team, OdiTeams odiTeam) {
		super();
		this.playerName = playerName;
		this.playersDob = playersDob;
		this.birthCity = birthCity;
		this.playerGender = playerGender;
		this.playerBattingStyle = playerBattingStyle;
		this.playerBowlingStyle = playerBowlingStyle;
		this.playerImage = playerImage;
		this.testTeam = testTeam;
		this.t20Team = t20Team;
		this.OdiTeam = odiTeam;
	}
	public Player(){}

}
