package obss.movietracker.controller;

import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.service.AdminService;
import obss.movietracker.service.LoginService;
import obss.movietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @GetMapping
    public List<MovieEntity> getAllMovies() { return userService.getAllMovies(); }

    @GetMapping(value = "/{name}")
    public List<MovieEntity> searchMoviesByName(@PathVariable String name){ return userService.searchMoviesByName(name); }

    @PostMapping
    public MovieEntity addMovie(@RequestBody MovieEntity movieEntity){
        return adminService.addMovie(movieEntity);
    }

    @PutMapping("/{movieId}")
    public MovieEntity updateMovie(@PathVariable Long movieId, @RequestBody MovieEntity movieEntity){
        return adminService.updateMovie(movieId,movieEntity);
    }

    @DeleteMapping("/{movieId}")
    public Iterable<MovieEntity> removeMovie(@PathVariable long movieId){
        return adminService.removeMovie(movieId);
    }

}
