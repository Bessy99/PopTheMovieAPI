package com.bessy.PopTheMovieAPI.Model;

public class BodyAddFilmAnswer {
	private Film film;
	private String email;
	private String modalita;
	
	public BodyAddFilmAnswer() {}
	
	public BodyAddFilmAnswer(Film film, String email, String modalita) {
		super();
		this.film = film;
		this.email = email;
		this.modalita = modalita;
	}
	public Film getFilm() {
		return film;
	}
	public String getEmail() {
		return email;
	}
	public String getModalita() {
		return modalita;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setModalita(String modalita) {
		this.modalita = modalita;
	}
	
	

}
