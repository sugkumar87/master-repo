package com.feignClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Movie;



@RestController
public class FeignClientClass {
	
	@Autowired
	FeignClientInterface feignClientInterface;
	
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping("/getMoviesList")
	public List<Movie> getMoviesList() {
		return feignClientInterface.getMovieList();
	}
	
	@RequestMapping(value="/deleteMovie/{name}", method= RequestMethod.DELETE)
	public ResponseEntity<String> deleteMovieFromList(@PathVariable("name") String name) {
		return feignClientInterface.deleteMovie(name);
	}
	
	
	
}



