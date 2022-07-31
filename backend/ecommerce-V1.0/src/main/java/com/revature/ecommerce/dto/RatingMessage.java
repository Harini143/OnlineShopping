package com.revature.ecommerce.dto;

public class RatingMessage {

	private double overallRating;

	public RatingMessage(double overallRating) {
		super();
		this.overallRating = overallRating;
	}

	public double getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	
	
}
