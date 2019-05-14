package com.dao;

import java.util.List;

import com.model.Movie;

public interface MovieListDao {

	public List<Movie> getMovieList();
	
	public Movie getMovie(String name);

	public String addMovie(Movie movie);

	public String updateMovie(Movie movie);

	public String deleteMovie(String name);
}
