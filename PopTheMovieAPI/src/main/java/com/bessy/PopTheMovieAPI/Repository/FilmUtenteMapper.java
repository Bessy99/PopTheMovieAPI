package com.bessy.PopTheMovieAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bessy.PopTheMovieAPI.Model.FilmUtente;


public class FilmUtenteMapper implements RowMapper<FilmUtente>  {
	
	public FilmUtente mapRow(ResultSet row, int rowNum)throws SQLException{
		FilmUtente res = new FilmUtente();
		
		try {
			res.setFilm_id(row.getString("Film_id").trim());
			res.setEmail(row.getString("Utente_email").trim());
		}
		catch (Exception ex)
		 {
			 System.out.println("Errore in Film_UtenteMapper.mapRow: " + ex);
		 }
		
		return res;
	}

}
