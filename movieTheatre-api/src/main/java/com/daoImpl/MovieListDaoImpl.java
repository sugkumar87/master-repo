package com.daoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.MovieListDao;
import com.model.Movie;

@Repository
public class MovieListDaoImpl implements MovieListDao {
	
	@Autowired
	EntityManager entityManager;
	
/*	private List<Movie> movies = new ArrayList<>(
			Arrays.asList(
					new Movie("URI", "A Surgical Attack 121312", 9.0),
					new Movie("Accidental PM", "Accidental PM", 7.0),
					new Movie("Simba", "Simba", 8.0),
					new Movie("Hum Chaar", "A story about friends", 3.0)
					)); 
*/	
	
	public List<Movie> getMovieList() {
		Query createQuery = entityManager.createQuery("from Movie", Movie.class);
		return createQuery.getResultList();
	}
	
	public Movie getMovie(String name) {
		
		Query query = entityManager.createNativeQuery("select * from movie where name ='"+ name +"'", Movie.class);
		
		return (Movie) query.getSingleResult();
		
	}

	public String addMovie(Movie movie) {
		entityManager.persist(movie);
		return "Movie added successfully!!!";
	}

	public String updateMovie(Movie movie) {
		
		StringBuffer sqlQuery = new StringBuffer("update movie set description = '")
								.append(movie.getDescription()).append("', rating = '")
								.append(movie.getRating()).append("' ")
								.append("where name='").append(movie.getName()).append("'");
		
		Query query = entityManager.createNativeQuery(sqlQuery.toString());
		
		int result = query.executeUpdate();
		
		
		if(result == 1) {
			return "Movie Details updated.";
		}
		
		return "No Movie Found.";
	}

	public String deleteMovie(String name) {
		
		Query query = entityManager.createNativeQuery("select * from movie where name ='"+ name +"'", Movie.class);
		
		Movie movie = (Movie) query.getSingleResult();
		
		if(movie != null) {
			entityManager.remove(movie);
			return "Movie " + name + "deleted.";
		}
		
		return "No Movie Found.";
	}

}
