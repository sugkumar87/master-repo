package com.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.customExceptions.MovieAppException;
import com.models.User;

public interface UserService {
	
	public String registerUser(User user) throws MovieAppException;

	public List<User> getUsers();
	
	public User getUser(long userId);
	
	public User updateUser(User user);
	
	public String deleteUser(long userId);
}
