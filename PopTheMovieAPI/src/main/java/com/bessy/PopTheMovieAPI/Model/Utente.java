package com.bessy.PopTheMovieAPI.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {
	@Id
	private String email;
	private String password;
	private String nome;
	private String cognome;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="filmdavedere", 
                joinColumns={@JoinColumn(name="Utente_email")}, 
                inverseJoinColumns={@JoinColumn(name="Film_id")})
	private Set<Film> filmDaVedere = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="filmvisti", 
                joinColumns={@JoinColumn(name="Utente_email")}, 
                inverseJoinColumns={@JoinColumn(name="Film_id")})
	private Set<Film> filmVisti = new HashSet<>();
	
	
	//costruttori, getter e setter
	
	public Utente() {
		super();
	}

	public Utente(String email, String password, String nome, String cognome, Set<Film> filmVisti, Set<Film> filmDaVedere) {
		super();
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.filmVisti = filmVisti;
		this.filmDaVedere = filmDaVedere;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Film> getFilmVisti() {
		return filmVisti;
	}

	public Set<Film> getFilmDaVedere() {
		return filmDaVedere;
	}
	
	public void setFilmVisti(Set<Film> filmVisti) {
		this.filmVisti = filmVisti;
	}

	public void setFilmDaVedere(Set<Film> filmDaVedere) {
		this.filmDaVedere = filmDaVedere;
	}
	
	

}
