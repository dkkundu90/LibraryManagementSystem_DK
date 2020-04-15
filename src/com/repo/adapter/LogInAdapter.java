package com.repo.adapter;

import com.gen.exception.DBException;
import com.repo.dao.AdminDao;

public interface LogInAdapter {

	public void read(AdminDao logInDao) throws DBException;
	
}
