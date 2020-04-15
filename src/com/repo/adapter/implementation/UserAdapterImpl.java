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
import com.repo.adapter.UserAdapter;
import com.repo.dao.UserDao;

public class UserAdapterImpl implements UserAdapter{
	private Logger logger = null;
	private LoadProperties properties;
	
	private PreparedStatement preparedStatement;
	private DataBaseConnection dataBaseConnection;
	
	public UserAdapterImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}
	
	@Override
	public Integer save(UserDao userDao) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		Integer insertionCompletionStatus;
		Integer newUserId;
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			newUserId = null;
			insertionCompletionStatus = null;
			String sqlQuery = properties.getPropertyForValue("getUserSequence");
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.executeQuery();
			ResultSet userSequenceResultSet = preparedStatement.getResultSet();
			if (userSequenceResultSet.next()) {
				newUserId = userSequenceResultSet.getInt(1);
			}
			
			String sqlInsertQuery = properties.getPropertyForValue("userInsert");
			preparedStatement = con.prepareStatement(sqlInsertQuery);
			
			preparedStatement.setInt(1, newUserId);
			if (userDao != null) {
				preparedStatement.setString(2, userDao.getFirstName());
				preparedStatement.setString(3, userDao.getLastName());
				preparedStatement.setString(4, userDao.getAddress());
				preparedStatement.setString(5, userDao.getMobile());
				preparedStatement.setString(6, userDao.getEmail());
				preparedStatement.setInt(7, userDao.getAge());
				preparedStatement.setString(8, userDao.getGender().toString());
			}
			
			insertionCompletionStatus = preparedStatement.executeUpdate();
			if (insertionCompletionStatus.equals(ApplicationConstants.VALUE_ZERO)) {
				logger.info("User Creation Failed." + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
				throw new DBException();
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User Successfully Created. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return newUserId;
	}

	@Override
	public Integer update(UserDao userDao) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		Integer updationCompletionStatus;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			updationCompletionStatus = null;
			String sqlUpdateQuery = properties.getPropertyForValue("userUpdate");
			preparedStatement = con.prepareStatement(sqlUpdateQuery);
			
			if (userDao != null) {
				preparedStatement.setString(1, userDao.getFirstName());
				preparedStatement.setString(2, userDao.getLastName());
				preparedStatement.setString(3, userDao.getAddress());
				preparedStatement.setString(4, userDao.getMobile());
				preparedStatement.setString(5, userDao.getEmail());
				preparedStatement.setInt(6, userDao.getAge());
				preparedStatement.setString(7, userDao.getGender().toString());
				preparedStatement.setInt(8, userDao.getUserId());
			}
			
			updationCompletionStatus = preparedStatement.executeUpdate();
			
			if ( updationCompletionStatus.equals(ApplicationConstants.VALUE_ZERO)) {
				logger.info("User Updation Failed." + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
				throw new DBException();
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User Successfully Updated. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return updationCompletionStatus;
	}
	
	@Override
	public Integer delete(Integer userId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		Integer deletionCompletionStatus;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			deletionCompletionStatus = null;
			String sqlDeleteQuery = properties.getPropertyForValue("userDelete");
			preparedStatement = con.prepareStatement(sqlDeleteQuery);
			preparedStatement.setInt(1, userId);
			
			deletionCompletionStatus = preparedStatement.executeUpdate();
			
			if (deletionCompletionStatus.equals(ApplicationConstants.VALUE_ZERO)) {
				logger.info("User Deletion Failed." + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
				throw new DBException();
			}
			
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		
		logger.info("User Successfully Deleted. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return deletionCompletionStatus;
	}

	@Override
	public List<UserDao> readAllUsers() throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		List<UserDao> userDaos = new ArrayList<UserDao>();
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			UserDao userDao;
			
			String sqlQuery = properties.getPropertyForValue("userSelect");
			preparedStatement = con.prepareStatement(sqlQuery); 
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				userDao = new UserDao();
				
				userDao.setUserId(rs.getInt(1));
				userDao.setFirstName(rs.getString(2));
				userDao.setLastName(rs.getString(3));
				userDao.setAddress(rs.getString(4));
				userDao.setMobile(rs.getString(5));
				userDao.setEmail(rs.getString(6));
				userDao.setAge(rs.getInt(7));
				userDao.setGender(rs.getString(8).charAt(ApplicationConstants.VALUE_ZERO));
				
				userDaos.add(userDao);
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User data fatched. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return userDaos;
	}
	
	@Override
	public UserDao readUserById(Integer userId) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		UserDao userDao = null;
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			
			String sqlQuery = properties.getPropertyForValue("userLookupSelect");
			preparedStatement = con.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, userId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				userDao = new UserDao();
				
				userDao.setUserId(rs.getInt(1));
				userDao.setFirstName(rs.getString(2));
				userDao.setLastName(rs.getString(3));
				userDao.setAddress(rs.getString(4));
				userDao.setMobile(rs.getString(5));
				userDao.setEmail(rs.getString(6));
				userDao.setAge(rs.getInt(7));
				userDao.setGender(rs.getString(8).charAt(ApplicationConstants.VALUE_ZERO));
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User dropdown data fatched. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return userDao;
	}
	
	@Override
	public List<UserDao> readAllUserNamesWithIds() throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + UserAdapterImpl.class);
		List<UserDao> userDaos = new ArrayList<UserDao>();
		
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();
			UserDao userDao;
			
			String sqlQuery = properties.getPropertyForValue("userDropDownSelect");
			preparedStatement = con.prepareStatement(sqlQuery); 
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				userDao = new UserDao();
				
				userDao.setUserId(rs.getInt(1));
				userDao.setFirstName(rs.getString(2));
				userDao.setLastName(rs.getString(3));
				
				userDaos.add(userDao);
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User dropdown data fatched. " + properties.getPropertyForValue("adapterExit") + UserAdapterImpl.class);
		return userDaos;
	}
}
