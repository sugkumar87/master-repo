package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customExceptions.MovieAppException;
import com.dao.UserDao;
import com.models.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userdao;
	
	@Override
	@Transactional
	public String registerUser(User user) throws MovieAppException {
		return userdao.registerUser(user);
	}

	@Override
	public List<User> getUsers() {
		return userdao.getUsers();
	}

	@Override
	public User getUser(long userId) {
		return userdao.getUser(userId);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return userdao.updateUser(user);
	}

	@Override
	@Transactional
	public String deleteUser(long userId) {
		return userdao.deleteUser(userId);
	}

}
