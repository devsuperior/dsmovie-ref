package com.devsuperior.dsmovie.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class ScoreDTO {

	@NotNull(message = "Required field")
	private Long movieId;

	@NotBlank(message = "Required field")
	@Email(message = "Email should be valid")
	private String email;
	
	@PositiveOrZero(message = "Score should be greater than or equal to zero")
	private Double score;

	public ScoreDTO() {
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}