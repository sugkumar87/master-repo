package com.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customExceptions.MovieAppException;
import com.models.User;
import com.service.UserService;

@RestController
@RequestMapping(value="/user", consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)

public class RegistrationController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@Valid @RequestBody User user){
		
		try {
			return userService.registerUser(user);
		} catch (MovieAppException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public User getUsers(@PathVariable("id") long userId){
		return userService.getUser(userId);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public User updateUser(@Valid @RequestBody User user){
		return userService.updateUser(user);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") long userId){
		return userService.deleteUser(userId);
	}

}
