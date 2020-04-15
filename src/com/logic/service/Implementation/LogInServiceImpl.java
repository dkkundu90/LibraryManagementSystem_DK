package com.logic.service.Implementation;

import org.apache.log4j.Logger;

import com.data.bean.AdminBean;
import com.gen.exception.DBException;
import com.gen.exception.ServiceException;
import com.gen.util.AppLogger;
import com.gen.util.LoadProperties;
import com.logic.mapper.LogInDataMapper;
import com.logic.service.LogInService;
import com.repo.adapter.LogInAdapter;
import com.repo.adapter.implementation.LogInAdapterImpl;
import com.repo.dao.AdminDao;

public class LogInServiceImpl implements  LogInService {
	private Logger logger = null;
	private LoadProperties properties;
	
	private LogInDataMapper logInDataMapper;
	private AdminDao adminDao;
	private LogInAdapter logInAdapter;
	
	public LogInServiceImpl() {
		logger = AppLogger.getLogger();
    	properties = new LoadProperties();
	}

	@Override
	public AdminBean readLogInInfo(AdminBean adminBean) throws ServiceException {
		logger.info(properties.getPropertyForValue("serviceEntry") + LogInServiceImpl.class);
		logInDataMapper = new LogInDataMapper();
		logInAdapter = new LogInAdapterImpl();
		
		adminDao = logInDataMapper.mapBeanDataToDao(adminBean);
		
		try {
			logInAdapter.read(adminDao);
			adminBean = logInDataMapper.mapDaoDataToBean(adminDao);
		} catch (DBException dbException) {
			logger.error((dbException.toString() + "\n" + dbException.getMessage()));
			throw new ServiceException(dbException);
		}
		logger.info(properties.getPropertyForValue("serviceExit") + LogInServiceImpl.class);
		return adminBean;
	}
}
