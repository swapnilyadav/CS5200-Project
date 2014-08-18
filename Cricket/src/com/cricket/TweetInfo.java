package com.cricket;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="TweetInfo.findAll", query="select t from TweetInfo t")
public class TweetInfo implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tweetid;
	private String tweetname;
	private String username;
	private Date tweetdate;
	private String tweettext;
	
	@OneToMany(mappedBy="tweet")
	private List<Comments> comments;
	
	public int getTweetid() {
		return tweetid;
	}
	public void setTweetid(int tweetid) {
		this.tweetid = tweetid;
	}
	public String getTweetname() {
		return tweetname;
	}
	public void setTweetname(String tweetname) {
		this.tweetname = tweetname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getTweetdate() {
		return tweetdate;
	}
	public void setTweetdate(Date tweetdate) {
		this.tweetdate = tweetdate;
	}
	public String getTweettext() {
		return tweettext;
	}
	public void setTweettext(String tweettext) {
		this.tweettext = tweettext;
	}
	public TweetInfo(String tweetname, String username, Date tweetdate,
			String tweettext) {
		super();
		this.tweetname = tweetname;
		this.username = username;
		this.tweetdate = tweetdate;
		this.tweettext = tweettext;
	}
	
	public TweetInfo (){} 
	
	
}
