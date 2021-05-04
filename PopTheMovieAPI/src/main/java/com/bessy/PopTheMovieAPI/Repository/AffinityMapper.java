package com.bessy.PopTheMovieAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bessy.PopTheMovieAPI.Model.ResultAffinity;

public class AffinityMapper implements RowMapper<ResultAffinity>  {
	
	public ResultAffinity mapRow(ResultSet row, int rowNum)throws SQLException{
		ResultAffinity res = new ResultAffinity();
		
		try {
			res.setNumFilmInComune(row.getInt("numFilm"));
			res.setEmail(row.getString("Utente_email").trim());
		}
		catch (Exception ex)
		 {
			 System.out.println("Errore in AffinityMapper.mapRow: " + ex);
		 }
		
		return res;
	}

}
