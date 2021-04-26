package com.bessy.PopTheMovieAPI.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bessy.PopTheMovieAPI.Model.Film;
import com.bessy.PopTheMovieAPI.Repository.FilmCRUDRepository;




@RestController
@RequestMapping("PopTheMovieAPI/film")
public class FilmController {

    @Autowired
    private FilmCRUDRepository filmRepository;
    
    @GetMapping
    public Iterable<Film> findAllFilms() {
    	return filmRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> findFilmById(@PathVariable(value = "id") String id) {
    	Optional<Film> film = filmRepository.findById(id);

        if(film.isPresent()) {
            return ResponseEntity.ok().body(film.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
}