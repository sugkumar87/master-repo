package com.dao;

import java.util.List;

import com.customExceptions.MovieAppException;
import com.models.User;

public interface UserDao {

	public String registerUser(User user) throws MovieAppException;
	
	public List<User> getUsers();
	
	public User getUser(long userId);
	
	public User updateUser(User user);
	
	public String deleteUser(long userId);
	
}
