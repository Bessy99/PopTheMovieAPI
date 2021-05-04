package com.bessy.PopTheMovieAPI.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bessy.PopTheMovieAPI.Model.Film_Utente;
import com.bessy.PopTheMovieAPI.Model.ResultAffinity;


@Repository
public class HintFilmRepository {
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	public List<ResultAffinity> findAffinity(String email){
		String sql = "CALL classificaAffinita('"+email+"');";
		List<ResultAffinity> risposta = jdbcTemplate.query(sql,new AffinityMapper());
		return risposta;
	}
	
	public List<Film_Utente> findAltriFilm(String email){
		String sql = "CALL filmVistiDaAltri('"+email+"');";
		List<Film_Utente> risposta = jdbcTemplate.query(sql,new Film_UtenteMapper());
		return risposta;
	}
	

}
