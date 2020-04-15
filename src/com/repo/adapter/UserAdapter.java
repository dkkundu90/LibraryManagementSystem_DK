package com.repo.adapter;

import java.util.List;

import com.gen.exception.DBException;
import com.repo.dao.UserDao;

public interface UserAdapter {
	public Integer save(UserDao userDao) throws DBException;
	
	public Integer update(UserDao userDao) throws DBException;
	
	public Integer delete(Integer userId) throws DBException;
	
	public List<UserDao> readAllUsers() throws DBException;
	
	public UserDao readUserById(Integer userId) throws DBException;
	
	public List<UserDao> readAllUserNamesWithIds() throws DBException;
}
