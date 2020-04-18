package com.logic.mapper;

import java.sql.Timestamp;

import com.data.bean.BookBean;
import com.repo.dao.BookDao;

public class BookDataMapper {
	private BookDao bookDao;
	
	public BookDao mapBeanDataToDao(BookBean bookBean, Boolean updateFlag) {
		bookDao = new BookDao();
		
		if (updateFlag) {
			bookDao.setBookId(bookBean.getBookId());
		}
		
		bookDao.setBookName(bookBean.getBookName());
		bookDao.setAuthorName(bookBean.getAuthorName());
		bookDao.setIssueDate(new Timestamp(bookBean.getIssueDate().getTime()));
		bookDao.setSubmissionDate(new Timestamp(bookBean.getSubmissionDate().getTime()));
		bookDao.setAvailaibilityDate(new Timestamp(bookBean.getAvailaibilityDate().getTime()));
		bookDao.setReadytoreIssue(bookBean.getReadytoreIssue());
		bookDao.setCurrentUserIssued(bookBean.getCurrentUserIssued());
		bookDao.setGenreId(bookBean.getGenreId());
		
		return bookDao;
	}
}
