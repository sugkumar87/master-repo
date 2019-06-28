package com.model;

import org.springframework.stereotype.Component;

@Component
public class Movie {
	
	private int id;
	 
	private String name;
	
	private String description;

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
