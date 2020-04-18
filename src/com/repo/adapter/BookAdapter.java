package com.repo.adapter;

import java.util.List;

import com.gen.exception.DBException;
import com.repo.dao.BookDao;

public interface BookAdapter {
	
	public List<BookDao> getAllBooksByUserId(Integer userId) throws DBException;
	
	public Boolean getBookLookUpByUserId(Integer userId) throws DBException;
}
