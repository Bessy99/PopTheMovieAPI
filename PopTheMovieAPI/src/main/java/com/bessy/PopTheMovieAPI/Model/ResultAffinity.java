package com.bessy.PopTheMovieAPI.Model;

public class ResultAffinity {
	private int numFilmInComune;
	private String email;
	public int getNumFilmInComune() {
		return numFilmInComune;
	}
	public String getEmail() {
		return email;
	}
	public void setNumFilmInComune(int numFilmInComune) {
		this.numFilmInComune = numFilmInComune;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ResultAffinity() {
		super();
	}
	
	
}
