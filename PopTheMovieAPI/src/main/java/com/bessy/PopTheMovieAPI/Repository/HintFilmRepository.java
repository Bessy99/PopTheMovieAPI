package com.bessy.PopTheMovieAPI.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bessy.PopTheMovieAPI.Model.Film;
import com.bessy.PopTheMovieAPI.Model.FilmUtente;
import com.bessy.PopTheMovieAPI.Model.ResultAffinity;


@Repository
public class HintFilmRepository {
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	/*
	
	public List<ResultAffinity> findAffinity(String email){
		String sql = "CALL classificaAffinita('"+email+"');";
		List<ResultAffinity> risposta = jdbcTemplate.query(sql,new AffinityMapper());
		return risposta;
	}
	
	public List<FilmUtente> findAltriFilm(String email){
		String sql = "CALL filmVistiDaAltri('"+email+"');";
		List<FilmUtente> risposta = jdbcTemplate.query(sql,new FilmUtenteMapper());
		return risposta;
	}
	
	*/
	
	public List<Film> findClassificaFilm(String email){
		String sql = "CALL filmInClassifica('"+email+"');";
		List<Film> risposta = jdbcTemplate.query(sql,new FilmMapper());
		return risposta;
	}

	public List<Film> findClassificaInversaFilm(String email) {
		String sql = "CALL filmInClassificaInversa('"+email+"');";
		List<Film> risposta = jdbcTemplate.query(sql,new FilmMapper());
		return risposta;
	}

}
