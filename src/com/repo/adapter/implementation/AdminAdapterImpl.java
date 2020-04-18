package com.repo.adapter.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.gen.exception.DBException;
import com.gen.util.AppLogger;
import com.gen.util.ApplicationConstants;
import com.gen.util.DataBaseConnection;
import com.gen.util.LoadProperties;
import com.repo.adapter.AdminAdapter;
import com.repo.dao.AdminDao;

public class AdminAdapterImpl implements AdminAdapter {
	private Logger logger = null;
	private LoadProperties properties;
	
	private PreparedStatement preparedStatement;
	private DataBaseConnection dataBaseConnection;
	
	public AdminAdapterImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public void getAdminLookupById(AdminDao adminDao) throws DBException {
		logger.info(properties.getPropertyForValue("adapterEntry") + AdminAdapterImpl.class);
		try {
			dataBaseConnection = new DataBaseConnection();
			Connection con = dataBaseConnection.newConnection();  
			
			String sqlQuery = properties.getPropertyForValue("logInSelect");
			
			preparedStatement = con.prepareStatement(sqlQuery); 
			if (adminDao != null) {
				preparedStatement.setString(ApplicationConstants.VALUE_ONE, adminDao.getAdminName());
				preparedStatement.setString(ApplicationConstants.VALUE_TWO, adminDao.getPassword());
			}
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				logger.info("User exits. " + AdminAdapterImpl.class);
				adminDao.setAdminId(rs.getInt(ApplicationConstants.VALUE_ONE));
				adminDao.setAdminName(rs.getString(ApplicationConstants.VALUE_TWO));
			}
		} catch(SQLException sqlException) {
			logger.error((sqlException.getMessage()));
			throw new DBException(sqlException);
		} finally {
			dataBaseConnection.closeConnection();
		}
		logger.info("User does not exits. " + properties.getPropertyForValue("adapterExit") + AdminAdapterImpl.class);
	}
}
