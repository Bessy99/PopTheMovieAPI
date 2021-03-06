package com.bessy.PopTheMovieAPI.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "film")
public class Film {
	@Id
	private String id;
	private String titolo;
	private String genere;
	private String poster;
	private String durata;
	
	public Film() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public Film(String id, String titolo, String genere, String poster, String durata) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.genere = genere;
		this.poster = poster;
		this.durata = durata;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", titolo=" + titolo + ", genere=" + genere + ", poster=" + poster + ", durata="
				+ durata + "]";
	}
	
	

	
	
	
}