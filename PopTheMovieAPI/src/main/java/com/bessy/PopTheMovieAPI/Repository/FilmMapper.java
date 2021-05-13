package com.bessy.PopTheMovieAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bessy.PopTheMovieAPI.Model.Film;


public class FilmMapper implements RowMapper<Film>  {
	
	public Film mapRow(ResultSet row, int rowNum)throws SQLException{
		Film res = new Film();
		
		try {
			res.setId(row.getString("id").trim());
			res.setTitolo(row.getString("titolo").trim());
			res.setGenere(row.getString("genere").trim());
			res.setPoster(row.getString("poster").trim());
			res.setDurata(row.getString("durata").trim());
		}
		catch (Exception ex)
		 {
			 System.out.println("Errore in FilmMapper.mapRow: " + ex);
		 }
		
		return res;
	}

}
