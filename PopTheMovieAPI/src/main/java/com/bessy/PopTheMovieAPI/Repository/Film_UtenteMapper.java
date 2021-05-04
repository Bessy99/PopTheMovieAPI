package com.bessy.PopTheMovieAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bessy.PopTheMovieAPI.Model.Film_Utente;


public class Film_UtenteMapper implements RowMapper<Film_Utente>  {
	
	public Film_Utente mapRow(ResultSet row, int rowNum)throws SQLException{
		Film_Utente res = new Film_Utente();
		
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
