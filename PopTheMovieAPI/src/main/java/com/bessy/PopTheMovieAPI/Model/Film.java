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
	private String categoria;
	private String poster;
	
	public Film() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Film(String id, String titolo, String categoria, String poster) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.categoria = categoria;
		this.poster = poster;
	}

	
	
	
}