package com.cricket;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
@NamedQuery(name="News.findAll", query="select n from News n")
public class News implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY.AUTO)
	@Column(name= "ArchivedNewsId")
	private int ArchivedNewsId;
	private String ArchivedNewsLink;

	private Date ArchivedNewsDateAndTime;
	private String ArchivedNewsTitle;
	
	@OneToMany(mappedBy="news")
	private List<Comments> comments;
	
	public News(String ArchivedNewsLink, Date date, String ArchivedNewsTitle)
	{
		super();
		this.ArchivedNewsLink=ArchivedNewsLink;
		this.ArchivedNewsDateAndTime=date;
		this.ArchivedNewsTitle=ArchivedNewsTitle;
		
	}
	
	public News(){}
	public void setArchivedNewsId(int Id)
	{
		this.ArchivedNewsId=Id;
	}
	
	public int getArchivedNewsId()
	{
		return ArchivedNewsId;
	}
	
	public void setArchivedNewsLink(String link)
	{
		this.ArchivedNewsLink=link;
	}
	
	public String getArchivedNewslink()
	{
		return ArchivedNewsLink;
	}
	
	public void setArchivedNewsDateAndTime(Date sqlTime)
	{
		this.ArchivedNewsDateAndTime=sqlTime;
	}
	
	public Date getArchivedNewsDateAndTime()
	{
		return ArchivedNewsDateAndTime;
	}
	
	public void setArchivedNewsTitle(String title)
	{
		this.ArchivedNewsTitle=title;
	}
	
	public String getArchivedNewsTitle()
	{
		return ArchivedNewsTitle;
	}
}

