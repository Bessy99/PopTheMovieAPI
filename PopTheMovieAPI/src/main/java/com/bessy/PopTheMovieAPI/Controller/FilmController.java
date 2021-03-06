package com.bessy.PopTheMovieAPI.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bessy.PopTheMovieAPI.Model.Film;
import com.bessy.PopTheMovieAPI.Model.FilmUtente;
import com.bessy.PopTheMovieAPI.Model.ResultAffinity;
import com.bessy.PopTheMovieAPI.Repository.FilmCRUDRepository;
import com.bessy.PopTheMovieAPI.Repository.HintFilmRepository;




@RestController
@RequestMapping("PopTheMovieAPI/film")
public class FilmController {

    @Autowired
    private FilmCRUDRepository filmRepository;
    @Autowired
    private HintFilmRepository hintFilmRepository;
    
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
    
    /*
    @GetMapping("/classificaAffinita")
    public ResponseEntity<List<ResultAffinity>> findAffinita(@RequestParam(value = "id") String id) {
    	List<ResultAffinity> listaAffinita = hintFilmRepository.findAffinity(id);
        return ResponseEntity.ok().body(listaAffinita);
        
    }
    
    @GetMapping("/filmMaiVisti")
    public ResponseEntity<List<FilmUtente>> findMaiVisti(@RequestParam(value = "id") String id) {
    	List<FilmUtente> listaFilmUtenti = hintFilmRepository.findAltriFilm(id);
        return ResponseEntity.ok().body(listaFilmUtenti);
        
    }
    */
    
    @GetMapping("/filmInClassifica")
    public ResponseEntity<List<Film>> findFilmInClassifica(@RequestParam(value = "id") String id) {
    	List<Film> listaClassificaFilm = hintFilmRepository.findClassificaFilm(id);
        return ResponseEntity.ok().body(listaClassificaFilm);
        
    }
    
    @GetMapping("/filmInClassificaInversa")
    public ResponseEntity<List<Film>> findFilmInClassificaInversa(@RequestParam(value = "id") String id) {
    	List<Film> listaClassificaFilm = hintFilmRepository.findClassificaInversaFilm(id);
        return ResponseEntity.ok().body(listaClassificaFilm);
        
    }
    
    
    
}