package com.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Movie;

//@FeignClient(name="movieAPI", url="http://localhost:8080/")
@FeignClient(name="movieAPI")
public interface FeignClientInterface {

	@RequestMapping("/movies")
	List<Movie> getMovieList();
	
	@RequestMapping(value="/movies/{name}", method= RequestMethod.DELETE)
	ResponseEntity<String> deleteMovie(@PathVariable("name") String name);
}
