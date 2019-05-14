package com.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Movie;
import com.serviceImpl.MovieListServiceImpl;

@RestController
public class MovieListController {
	
	@Autowired
	private MovieListServiceImpl movieListService;

	@RequestMapping(value="/movies", method = RequestMethod.GET)
	public List<Movie> getMoviesList() {
		
		return movieListService.getMovieList();
	}
	
//	@RequestMapping("/movies/{name}")
//	public Movie getMovie(@PathVariable("name") String name) {
//		
//		return movieListService.getMovie(name);
//	}
	
	
	@RequestMapping(value="/movies/{name}", method = RequestMethod.GET)
	public Movie getMovie(@PathVariable("name") String name, HttpServletResponse response) {
		
		Movie movie = movieListService.getMovie(name);
//		return ResponseEntity.ok()
//				.header("Set-Cookie","testCookie="+"testValue; secure")
//				.body(movie);
		/*
		Cookie cookie = new Cookie("testCookie", "Value1");
		cookie.setMaxAge(-1);
		cookie.setSecure(true);
		
		response.addCookie(cookie);*/
		return movie;
		
		//return new ResponseEntity<Movie>(movie, HttpStatus.OK);
				
				
				
	}
	
	@RequestMapping(value= "/movies", method= RequestMethod.POST)
	public String addMovie(@RequestBody Movie movie) {
		
		return movieListService.addMovie(movie);
	}
	
	@RequestMapping(value= "/movies/{name}", method= RequestMethod.PUT)
	public String updateMovie(@PathVariable("name") String name, @RequestBody Movie movie) {
		
		return movieListService.updateMovie(movie);
	}
	
	@RequestMapping(value= "/movies/{name}", method= RequestMethod.DELETE)
	public ResponseEntity<String> deleteMovie(@PathVariable("name") String name, HttpServletResponse response) {
		
		
		//response.addCookie(new Cookie("test2", "456"));
		
		String msg=  movieListService.deleteMovie(name);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
