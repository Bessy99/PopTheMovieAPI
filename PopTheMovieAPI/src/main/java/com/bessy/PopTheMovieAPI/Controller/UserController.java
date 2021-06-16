package com.bessy.PopTheMovieAPI.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.bessy.PopTheMovieAPI.Model.BodyAddFilmAnswer;
import com.bessy.PopTheMovieAPI.Model.BodyRemoveFilmAnswer;
import com.bessy.PopTheMovieAPI.Model.Film;
import com.bessy.PopTheMovieAPI.Model.FilmUtente;
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
    
    @GetMapping("/exists")
    public ResponseEntity<Boolean> userExists(@RequestParam(value = "id") String id, @RequestParam (value = "password") String password){
    	Optional<Utente> user = userRepository.findById(id);

        if(user.isPresent() && user.get().getPassword().equals(password)) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.ok().body(false);
        }
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
    public ResponseEntity<String> addFilm(@Validated @RequestBody BodyAddFilmAnswer body) {
    	String email = body.getEmail();
    	String modalita = body.getModalita();
    	Film film = body.getFilm();
    	
    	Optional<Utente> optionalUser = userRepository.findById(email);
    	if(optionalUser.isPresent()) {
    		Utente u = optionalUser.get();
    		Utente newU = new Utente(u.getEmail(),u.getPassword(), u.getNome(), u.getCognome(), u.getFilmVisti(), u.getFilmDaVedere());
	    	if(modalita.equals("visti")) {
	    			Film filmPersistent = filmChange(film);
	    			if(filmPersistent!=null) {
		    			userRepository.delete(u);
		    			newU.getFilmVisti().add(filmPersistent);
						userRepository.save(newU);
						}
					else { 
						Film newFilm = filmRepository.save(film);
						userRepository.delete(u);
						newU.getFilmVisti().add(newFilm);
						userRepository.save(newU);
					}
			}
			else if(modalita.equals("daVedere")) {
				Film filmPersistent = filmChange(film);
	    		if(filmPersistent!=null) {
	    			userRepository.delete(u);
	    			newU.getFilmDaVedere().add(filmPersistent);
					userRepository.save(newU);
	    		}
				else { 
					Film newFilm = filmRepository.save(film);
					userRepository.delete(u);
					newU.getFilmDaVedere().add(newFilm);
					userRepository.save(newU);
				}
			}
			
	     return ResponseEntity.ok().body("lista aggiornata");
    	}
    	
    	return ResponseEntity.ok().body("utente non trovato");
			
    }
    
    @PostMapping("/removeFilm")
    public ResponseEntity<String> removeFilm(@Validated @RequestBody BodyRemoveFilmAnswer body) {
    	String email = body.getEmail();
    	String modalita = body.getModalita();
    	String filmId = body.getFilmId();
    	
    	Optional<Utente> optionalUser = userRepository.findById(email);
    	if(optionalUser.isPresent()) {
    		Utente u = optionalUser.get();
    		Utente newU = new Utente(u.getEmail(),u.getPassword(), u.getNome(), u.getCognome(), u.getFilmVisti(), u.getFilmDaVedere());
    		if(modalita.equals("visti")) {
	    		Film filmToRemove = null;
	    		for(Film f : u.getFilmVisti()) {
	    			if(f.getId().equals(filmId)) {
	    				filmToRemove = f;
	    				break;
	    			}
	    		 }
	    		if(filmToRemove != null) {
	    			Set<Film> filmList = u.getFilmVisti();
	    			filmList.remove(filmToRemove);
	    			newU.setFilmVisti(filmList);
	    			userRepository.delete(u);
    				userRepository.save(newU);
	    		}
    		}
    		else if(modalita.equals("daVedere")) {
    			Film filmToRemove = null;
    			for(Film f : u.getFilmDaVedere()) {
        			if(f.getId().equals(filmId)) {
        				filmToRemove = f;
        				break;
        			}
    			}
    			if(filmToRemove != null) {
    				Set<Film> filmList = u.getFilmDaVedere();
	    			filmList.remove(filmToRemove);
	    			newU.setFilmDaVedere(filmList);
	    			userRepository.delete(u);
    				userRepository.save(newU);
	    		}
        	}
    		String s = "lista aggiornata";
    		return ResponseEntity.ok().body(s);
    	}
    	String s = "utente non trovato";
    	return ResponseEntity.ok().body(s);
			
    }
    
    private Film filmChange(Film film) {
    	Optional<Film> f = filmRepository.findById(film.getId());
    	if(f.isPresent()) {
    		Film filmPersistent = f.get();
    		if(!filmPersistent.getDurata().equals(film.getDurata()))
    			filmPersistent.setDurata(film.getDurata());
    		if(!filmPersistent.getTitolo().equals(film.getTitolo()))
    			filmPersistent.setTitolo(film.getTitolo());
    		if(!filmPersistent.getGenere().equals(film.getGenere()))
    			filmPersistent.setGenere(film.getGenere());
    		if(!filmPersistent.getPoster().equals(film.getPoster()))
    			filmPersistent.setPoster(film.getPoster());
    		return filmPersistent;
    	}
    	return null;
    }
    
}