package com.repo.dao;

import java.sql.Timestamp;

public class BookDao {
	private Integer bookid;
	private String bookname;
	private String authorname;
	private Timestamp issuedate;
	private Timestamp submissiondate;
	private Timestamp availaibilitydate;
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
	public Timestamp getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(Timestamp issuedate) {
		this.issuedate = issuedate;
	}
	public Timestamp getSubmissiondate() {
		return submissiondate;
	}
	public void setSubmissiondate(Timestamp submissiondate) {
		this.submissiondate = submissiondate;
	}
	public Timestamp getAvailaibilitydate() {
		return availaibilitydate;
	}
	public void setAvailaibilitydate(Timestamp availaibilitydate) {
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
