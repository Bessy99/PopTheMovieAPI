package com.bessy.PopTheMovieAPI.Model;

public class BodyRemoveFilmAnswer {
	private String filmId;
	private String email;
	private String modalita;
	
	public BodyRemoveFilmAnswer() {}
	
	public BodyRemoveFilmAnswer(String film, String email, String modalita) {
		super();
		this.filmId = film;
		this.email = email;
		this.modalita = modalita;
	}
	public String getFilmId() {
		return filmId;
	}
	public String getEmail() {
		return email;
	}
	public String getModalita() {
		return modalita;
	}
	public void setFilmId(String film) {
		this.filmId = film;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setModalita(String modalita) {
		this.modalita = modalita;
	}
}
