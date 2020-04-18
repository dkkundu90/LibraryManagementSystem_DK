package com.logic.service;

import com.gen.exception.ServiceException;

public interface BookService {
	
	public Boolean bookIssuedForUserCheck(Integer userId) throws ServiceException;
}
