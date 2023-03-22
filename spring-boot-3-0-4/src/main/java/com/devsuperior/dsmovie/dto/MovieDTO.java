package com.devsuperior.dsmovie.dto;

import org.hibernate.validator.constraints.URL;

import com.devsuperior.dsmovie.entities.MovieEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class MovieDTO {

	private Long id;
	
	@NotBlank(message = "Required field")
	@Size(min = 5, max = 80, message = "Title must be between 5 and 80 characters")
	private String title;
	
	@PositiveOrZero(message = "Score should be greater than or equal to zero")
	private Double score;
	
	@PositiveOrZero(message = "Count should be greater than or equal to zero")
	private Integer count;
	
	@NotBlank(message = "Required field")
	@URL(message = "Field must be a valid url")
	private String image;

	public MovieDTO() {
	}

	public MovieDTO(Long id, String title, Double score, Integer count, String image) {
		this.id = id;
		this.title = title;
		this.score = score;
		this.count = count;
		this.image = image;
	}

	public MovieDTO(MovieEntity movie) {
		id = movie.getId();
		title = movie.getTitle();
		score = movie.getScore();
		count = movie.getCount();
		image = movie.getImage();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public MovieEntity toEntity() {
		return new MovieEntity(id, title, score, count, image);
	}
}