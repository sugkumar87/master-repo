package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "movieId_generator")
	@SequenceGenerator(name="movieId_generator", sequenceName = "movieId_seq")
	@Column(name="movie_id")
	private int id;
	 
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	@Column(name="rating")
	private Double rating;

	public Movie() {
		super();
	}

	public Movie(String name, String description, Double rating) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	

}
