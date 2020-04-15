package com.logic.service.Implementation;

import org.apache.log4j.Logger;

import com.gen.exception.DBException;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.LoadProperties;
import com.logic.service.BookService;
import com.repo.adapter.BookAdapter;
import com.repo.adapter.implementation.BookAdapterImpl;

public class BookServiceImpl implements  BookService {
	private Logger logger = null;
	private LoadProperties properties;
	
	private BookAdapter bookAdapter;
	
	public BookServiceImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public Boolean bookIssuedForUserCheck(Integer userId) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + BookServiceImpl.class);
		Boolean IsBookIssuedForUser = ApplicationConstants.VALUE_FALSE;
		bookAdapter = new BookAdapterImpl();
		
		try {
			IsBookIssuedForUser = bookAdapter.getAllBookIdsByUserId(userId);
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + BookServiceImpl.class);
		return IsBookIssuedForUser;
	}
}
