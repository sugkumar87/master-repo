package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daoImpl.MovieListDaoImpl;
import com.model.Movie;
import com.services.MovieListService;

@Service
@Transactional
public class MovieListServiceImpl implements MovieListService {
	
	@Autowired
	private MovieListDaoImpl movieListDao;
	
	public List<Movie> getMovieList() {
		return movieListDao.getMovieList();
	}
	
	public Movie getMovie(String name) {
		return movieListDao.getMovie(name);
	}

	public String addMovie(Movie movie) {
		return movieListDao.addMovie(movie);
	}

	public String updateMovie(Movie movie) {
		return movieListDao.updateMovie(movie);
	}

	public String deleteMovie(String name) {
		return movieListDao.deleteMovie(name);
	}
}
