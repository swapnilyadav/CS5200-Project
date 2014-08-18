package com.cricket;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="ArchivedMatch.findAll", query="select a from ArchivedMatch a")
public class ArchivedMatch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int archivedmatchid;
	private String archivedmatchtype;
	private String archivedmatchseries;
	private String archivedmatchname;
	private String archivedmatchno ;
	private String archivedmcity;
	private String archivedmcountry;
	private String archivedmstadium;
	private String archivedmstatus;
	private String archivedmtoss;
	private String archivedmtossdecision;
	private String archivedmteam1;
	private String archivedmteam1Innings1runs;
	private String archivedmteam1Innings1wickets;
	private String archivedmteam1Innings1overs ;
	private String archivedmteam1Innings2runs ;
	private String archivedmteam1Innings2wickets ;
	private String archivedmteam1Innings2overs;
	private String archivedmteam2;
	private String archivedmteam2Innings1runs ;
	private String archivedmteam2Innings1wickets ;
	private String archivedmteam2Innings1overs ;
	private String archivedmteam2Innings2runs;
	private String archivedmteam2Innings2wickets;
	private String archivedmteam2Innings2overs;
	private String archivedmstartdate;
	private String archivedmenddate;
	
	public ArchivedMatch(){}
	
	public ArchivedMatch(String archivedMatchType, String archivedMatchSeries, String archivedMatchName, String archivedMatchNo,
						 String archivedMCity, String archivedMCountry, String archivedMStadium, String archivedMStatus, 
						 String archivedMToss, String archivedMTossDecision, String archivedMTeam1, String archivedMTeam1Innings1Runs,
						 String archivedMTeam1Innings1Wickets, String archivedMTeam1Innings1Overs, String archivedMTeam1Innings2Runs,
						 String archivedMTeam1Innings2Wickets, String archivedMTeam1Innings2Overs, String archivedMTeam2, String archivedMTeam2Innings1Runs ,
						 String archivedMTeam2Innings1Wickets, String archivedMTeam2Innings1Overs, String archivedMTeam2Innings2Runs,
						 String archivedMTeam2Innings2Wickets, String archivedMTeam2Innings2Overs, String archivedMStartDate, String archivedMEndDate){
		this.archivedmatchtype = archivedMatchType;
		this.archivedmatchseries = archivedMatchSeries;
		this.archivedmatchname = archivedMatchName;
		this.archivedmatchno = archivedMatchNo;
		this.archivedmcity = archivedMCity;
		this.archivedmcountry = archivedMCountry;
		this.archivedmstadium = archivedMStadium;
		this.archivedmstatus = archivedMStatus;
		this.archivedmtoss = archivedMToss;
		this.archivedmtossdecision = archivedMTossDecision;
		this.archivedmteam1 = archivedMTeam1;
		this.archivedmteam1Innings1runs = archivedMTeam1Innings1Runs;
		this.archivedmteam1Innings1overs = archivedMTeam1Innings1Overs;
		this.archivedmteam1Innings1wickets = archivedMTeam1Innings1Wickets;
		this.archivedmteam1Innings2runs = archivedMTeam1Innings2Runs;
		this.archivedmteam1Innings2overs = archivedMTeam1Innings2Overs;
		this.archivedmteam1Innings2wickets = archivedMTeam1Innings2Wickets;
		this.archivedmteam2 = archivedMTeam2;
		this.archivedmteam2Innings1runs = archivedMTeam2Innings1Runs;
		this.archivedmteam2Innings1overs = archivedMTeam2Innings1Overs;
		this.archivedmteam2Innings1wickets = archivedMTeam2Innings1Wickets;
		this.archivedmteam2Innings2runs = archivedMTeam2Innings2Runs;
		this.archivedmteam2Innings2overs = archivedMTeam2Innings2Overs;
		this.archivedmteam2Innings2wickets = archivedMTeam2Innings2Wickets;
		this.archivedmstartdate = archivedMStartDate;
		this.archivedmenddate = archivedMEndDate;
	}

	public int getArchivedmatchid() {
		return archivedmatchid;
	}

	public void setArchivedmatchid(int archivedmatchid) {
		this.archivedmatchid = archivedmatchid;
	}

	public String getArchivedmatchtype() {
		return archivedmatchtype;
	}

	public void setArchivedmatchtype(String archivedmatchtype) {
		this.archivedmatchtype = archivedmatchtype;
	}

	public String getArchivedmatchseries() {
		return archivedmatchseries;
	}

	public void setArchivedmatchseries(String archivedmatchseries) {
		this.archivedmatchseries = archivedmatchseries;
	}

	public String getArchivedmatchname() {
		return archivedmatchname;
	}

	public void setArchivedmatchname(String archivedmatchname) {
		this.archivedmatchname = archivedmatchname;
	}

	public String getArchivedmatchno() {
		return archivedmatchno;
	}

	public void setArchivedmatchno(String archivedmatchno) {
		this.archivedmatchno = archivedmatchno;
	}

	public String getArchivedmcity() {
		return archivedmcity;
	}

	public void setArchivedmcity(String archivedmcity) {
		this.archivedmcity = archivedmcity;
	}

	public String getArchivedmcountry() {
		return archivedmcountry;
	}

	public void setArchivedmcountry(String archivedmcountry) {
		this.archivedmcountry = archivedmcountry;
	}

	public String getArchivedmstadium() {
		return archivedmstadium;
	}

	public void setArchivedmstadium(String archivedmstadium) {
		this.archivedmstadium = archivedmstadium;
	}

	public String getArchivedmstatus() {
		return archivedmstatus;
	}

	public void setArchivedmstatus(String archivedmstatus) {
		this.archivedmstatus = archivedmstatus;
	}

	public String getArchivedmtoss() {
		return archivedmtoss;
	}

	public void setArchivedmtoss(String archivedmtoss) {
		this.archivedmtoss = archivedmtoss;
	}

	public String getArchivedmtossdecision() {
		return archivedmtossdecision;
	}

	public void setArchivedmtossdecision(String archivedmtossdecision) {
		this.archivedmtossdecision = archivedmtossdecision;
	}

	public String getArchivedmteam1() {
		return archivedmteam1;
	}

	public void setArchivedmteam1(String archivedmteam1) {
		this.archivedmteam1 = archivedmteam1;
	}

	public String getArchivedmteam1Innings1runs() {
		return archivedmteam1Innings1runs;
	}

	public void setArchivedmteam1Innings1runs(String archivedmteam1Innings1runs) {
		this.archivedmteam1Innings1runs = archivedmteam1Innings1runs;
	}

	public String getArchivedmteam1Innings1wickets() {
		return archivedmteam1Innings1wickets;
	}

	public void setArchivedmteam1Innings1wickets(
			String archivedmteam1Innings1wickets) {
		this.archivedmteam1Innings1wickets = archivedmteam1Innings1wickets;
	}

	public String getArchivedmteam1Innings1overs() {
		return archivedmteam1Innings1overs;
	}

	public void setArchivedmteam1Innings1overs(String archivedmteam1Innings1overs) {
		this.archivedmteam1Innings1overs = archivedmteam1Innings1overs;
	}

	public String getArchivedmteam1Innings2runs() {
		return archivedmteam1Innings2runs;
	}

	public void setArchivedmteam1Innings2runs(String archivedmteam1Innings2runs) {
		this.archivedmteam1Innings2runs = archivedmteam1Innings2runs;
	}

	public String getArchivedmteam1Innings2wickets() {
		return archivedmteam1Innings2wickets;
	}

	public void setArchivedmteam1Innings2wickets(
			String archivedmteam1Innings2wickets) {
		this.archivedmteam1Innings2wickets = archivedmteam1Innings2wickets;
	}

	public String getArchivedmteam1Innings2overs() {
		return archivedmteam1Innings2overs;
	}

	public void setArchivedmteam1Innings2overs(String archivedmteam1Innings2overs) {
		this.archivedmteam1Innings2overs = archivedmteam1Innings2overs;
	}

	public String getArchivedmteam2() {
		return archivedmteam2;
	}

	public void setArchivedmteam2(String archivedmteam2) {
		this.archivedmteam2 = archivedmteam2;
	}

	public String getArchivedmteam2Innings1runs() {
		return archivedmteam2Innings1runs;
	}

	public void setArchivedmteam2Innings1runs(String archivedmteam2Innings1runs) {
		this.archivedmteam2Innings1runs = archivedmteam2Innings1runs;
	}

	public String getArchivedmteam2Innings1wickets() {
		return archivedmteam2Innings1wickets;
	}

	public void setArchivedmteam2Innings1wickets(
			String archivedmteam2Innings1wickets) {
		this.archivedmteam2Innings1wickets = archivedmteam2Innings1wickets;
	}

	public String getArchivedmteam2Innings1overs() {
		return archivedmteam2Innings1overs;
	}

	public void setArchivedmteam2Innings1overs(String archivedmteam2Innings1overs) {
		this.archivedmteam2Innings1overs = archivedmteam2Innings1overs;
	}

	public String getArchivedmteam2Innings2runs() {
		return archivedmteam2Innings2runs;
	}

	public void setArchivedmteam2Innings2runs(String archivedmteam2Innings2runs) {
		this.archivedmteam2Innings2runs = archivedmteam2Innings2runs;
	}

	public String getArchivedmteam2Innings2wickets() {
		return archivedmteam2Innings2wickets;
	}

	public void setArchivedmteam2Innings2wickets(
			String archivedmteam2Innings2wickets) {
		this.archivedmteam2Innings2wickets = archivedmteam2Innings2wickets;
	}

	public String getArchivedmteam2Innings2overs() {
		return archivedmteam2Innings2overs;
	}

	public void setArchivedmteam2Innings2overs(String archivedmteam2Innings2overs) {
		this.archivedmteam2Innings2overs = archivedmteam2Innings2overs;
	}

	public String getArchivedmstartdate() {
		return archivedmstartdate;
	}

	public void setArchivedmstartdate(String archivedmstartdate) {
		this.archivedmstartdate = archivedmstartdate;
	}

	public String getArchivedmenddate() {
		return archivedmenddate;
	}

	public void setArchivedmenddate(String archivedmenddate) {
		this.archivedmenddate = archivedmenddate;
	}

	

}
