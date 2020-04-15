package com.repo.adapter.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gen.exception.DBException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.DataBaseConnection;
import com.gen.util.LoadProperties;
import com.repo.adapter.BookAdapter;
import com.repo.dao.BookDao;

public class BookAdapterImpl implements BookAdapter {
	private Logger logger = null;
	private LoadProperties properties;
	
	private PreparedStatement preparedStatement;
	private DataBaseConnection dataBaseConnection;
	
	public BookAdapterImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public List<BookDao> getAllBooksByUserId(Integer userId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + BookAdapterImpl.class);
		List<BookDao> bookDaos = new ArrayList<BookDao>();
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();  
			BookDao bookDao;
			
			String sqlQuery = properties.getPropertyForValue("bookSelect");
			preparedStatement = con.prepareStatement(sqlQuery); 
			preparedStatement.setInt(1, userId);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bookDao = new BookDao();
				
				bookDao.setBookid(rs.getInt(1));
				bookDao.setBookname(rs.getString(2));
				bookDao.setAuthorname(rs.getString(3));
				bookDao.setCurrentuserissued(rs.getInt(4));
				bookDao.setIssuedate(rs.getTimestamp(5));
				bookDao.setSubmissiondate(rs.getTimestamp(6));
				bookDao.setAvailaibilitydate(rs.getTimestamp(7));
				bookDao.setReadytoreissue(rs.getString(8).charAt(ApplicationConstants.VALUE_ZERO));
				bookDao.setGenreid(rs.getInt(9));
				
				bookDaos.add(bookDao);
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		
		logger.info("Book Data Populated. " + properties.getPropertyForValue("adapterExit") + BookAdapterImpl.class);
		return bookDaos;
	}

	@Override
	public Boolean getAllBookIdsByUserId(Integer userId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + BookAdapterImpl.class);
		Boolean IsBookIssuedForUser = ApplicationConstants.VALUE_FALSE;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();  
			
			String sqlQuery = properties.getPropertyForValue("bookForAUserLookup");
			preparedStatement = con.prepareStatement(sqlQuery); 
			preparedStatement.setInt(1, userId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				logger.info("Atleast one Book is ussed to this users." + LogInAdapterImpl.class);
				IsBookIssuedForUser = ApplicationConstants.VALUE_TRUE;
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		
		logger.info("User does not exits. " + properties.getPropertyForValue("adapterExit") + BookAdapterImpl.class);
		return IsBookIssuedForUser;
	}
}
