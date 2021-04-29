package com.bessy.PopTheMovieAPI.Controller;

import java.util.Optional;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bessy.PopTheMovieAPI.Model.Film;
import com.bessy.PopTheMovieAPI.Model.Utente;
import com.bessy.PopTheMovieAPI.Repository.FilmCRUDRepository;
import com.bessy.PopTheMovieAPI.Repository.UserCRUDRepository;


@RestController
@RequestMapping("PopTheMovieAPI/user")
public class UserController {

    @Autowired
    private UserCRUDRepository userRepository;
    
    @Autowired
    private FilmCRUDRepository filmRepository;
    
    @GetMapping("/allUsers")
    public Iterable<Utente> findAllUsers() {
    	return userRepository.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<Utente> findUserById(@RequestParam(value = "id") String id, @RequestParam(value = "password") String password) {
    	
    	Optional<Utente> user = userRepository.findById(id);

        if(user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/saveUser")
    public Utente saveUser(@Validated @RequestBody Utente user) {
		return userRepository.save(user);
    }
    
    @PostMapping("/addFilm")
    public ResponseEntity<Utente> addFilm(@Validated @RequestBody Film film, @RequestBody Utente utente, @PathVariable(value = "modalita") String modalita) {
    	Optional<Utente> optionalUser = userRepository.findById(utente.getEmail());
    	if(optionalUser.isPresent()) {
    		Utente u = optionalUser.get();
	    	if(modalita == "visti") {
	    		Optional<Film> f = filmRepository.findById(film.getId());
	    		if(f.isPresent()) {
	    			//? necessario?
	    			userRepository.delete(u);
	    			u.getFilmVisti().add(f.get());
					userRepository.save(u);
	    		}
				else { 
					Film newFilm = filmRepository.save(film);
					//? necessario?
					userRepository.delete(u);
					u.getFilmVisti().add(newFilm);
					userRepository.save(u);
				}
			}
			else if(modalita == "daVedere") {
				Optional<Film> f = filmRepository.findById(film.getId());
	    		if(f.isPresent()) {
	    			//? necessario?
	    			userRepository.delete(u);
	    			u.getFilmDaVedere().add(f.get());
					userRepository.save(u);
	    		}
				else { 
					Film newFilm = filmRepository.save(film);
					//? necessario?
					userRepository.delete(u);
					u.getFilmDaVedere().add(newFilm);
					userRepository.save(u);
				}
				
			}
	    
	    	return ResponseEntity.ok().body(u);
    	}
    	
    	return ResponseEntity.notFound().build();
			
    }
    
}