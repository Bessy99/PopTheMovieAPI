package com.bessy.PopTheMovieAPI.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bessy.PopTheMovieAPI.Model.Film;


@Repository
public interface FilmCRUDRepository extends CrudRepository<Film, String>{
	
}
