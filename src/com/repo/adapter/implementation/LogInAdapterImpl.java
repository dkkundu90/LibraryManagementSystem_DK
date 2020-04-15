package com.repo.adapter.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.gen.exception.DBException;
import com.gen.util.AppLogger;
import com.gen.util.DataBaseConnection;
import com.gen.util.LoadProperties;
import com.repo.adapter.LogInAdapter;
import com.repo.dao.AdminDao;

public class LogInAdapterImpl implements LogInAdapter {
	private Logger logger = null;
	private LoadProperties properties;
	
	private PreparedStatement preparedStatement;
	private DataBaseConnection dataBaseConnection;
	
	public LogInAdapterImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public void read(AdminDao adminDao) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + LogInAdapterImpl.class);
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();  
			
			String sqlQuery = properties.getPropertyForValue("logInSelect");
			
			preparedStatement = con.prepareStatement(sqlQuery); 
			if (adminDao != null) {
				preparedStatement.setString(1, adminDao.getAdminName());
				preparedStatement.setString(2, adminDao.getPassword());
			}
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				logger.info("User exits. " + LogInAdapterImpl.class);
				adminDao.setAdminId(rs.getInt(1));
				adminDao.setAdminName(rs.getString(2));
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User does not exits. " + properties.getPropertyForValue("adapterExit") + LogInAdapterImpl.class);
	}
}
