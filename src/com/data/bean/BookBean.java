package com.data.bean;

import java.util.Date;

public class BookBean {
	private Integer bookid;
	private String bookname;
	private String authorname;
	private Date issuedate;
	private Date submissiondate;
	private Date availaibilitydate;
	private Character readytoreissue;
	private Integer currentuserissued;
	private Integer genreid;
	
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}
	public Date getSubmissiondate() {
		return submissiondate;
	}
	public void setSubmissiondate(Date submissiondate) {
		this.submissiondate = submissiondate;
	}
	public Date getAvailaibilitydate() {
		return availaibilitydate;
	}
	public void setAvailaibilitydate(Date availaibilitydate) {
		this.availaibilitydate = availaibilitydate;
	}
	public Character getReadytoreissue() {
		return readytoreissue;
	}
	public void setReadytoreissue(Character readytoreissue) {
		this.readytoreissue = readytoreissue;
	}
	public Integer getCurrentuserissued() {
		return currentuserissued;
	}
	public void setCurrentuserissued(Integer currentuserissued) {
		this.currentuserissued = currentuserissued;
	}
	public Integer getGenreid() {
		return genreid;
	}
	public void setGenreid(Integer genreid) {
		this.genreid = genreid;
	}
}
