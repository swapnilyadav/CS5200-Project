package com.cricket;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Comments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentId;
	private String commentsText;
	
	
	private Date commentsDate;
	
	@ManyToOne
	@JoinColumn(name="Userinfo_userid")
	private Userinfo user;
	
	public Comments(String commentsText, Date commentsDate,
			Userinfo user, TweetInfo tweet) {
		super();
		this.commentsText = commentsText;
		this.commentsDate = commentsDate;
		this.user = user;
		this.tweet = tweet;
	}
	
	public Comments(String commentsText, Date commentsDate,
			Userinfo user, News news) {
		super();
		this.commentsText = commentsText;
		this.commentsDate = commentsDate;
		this.user = user;
		this.news = news;
	}
	
	public Comments(){};

	@ManyToOne
	@JoinColumn(name="TweetInfo_tweetid")
	private TweetInfo tweet;
	
	@ManyToOne
	@JoinColumn(name="News_newsid")
	private News news;
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentsText() {
		return commentsText;
	}

	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}

	public Date getCommentsDate() {
		return commentsDate;
	}

	public void setCommentsDate(Date commentsDate) {
		this.commentsDate = commentsDate;
	}

	public TweetInfo getTweet() {
		return tweet;
	}

	public void setTweet(TweetInfo tweet) {
		this.tweet = tweet;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Userinfo getUser() {
		return user;
	}

	public void setUser(Userinfo user) {
		this.user = user;
	}

	

}
