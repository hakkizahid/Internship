package obss.movietracker.controller;

import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.model.payload.ApiResponse;
import obss.movietracker.model.payload.ImdbRequestModel;
import obss.movietracker.service.AdminService;
import obss.movietracker.service.LoginService;
import obss.movietracker.service.MovieService;
import obss.movietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController                 //OMDB key = 38e228d
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<MovieEntity> getAllMovies() {
        return new ResponseEntity(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<MovieEntity> searchMoviesByName(@PathVariable String name){
        if(userService.searchMoviesByName(name)!= null){
            return new ResponseEntity(userService.searchMoviesByName(name), HttpStatus.OK);
        }else{
            return new ResponseEntity("Bu id numarasına sahip movie bulunamadı.",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieEntity> getMovie(@PathVariable Long id){
        if(movieService.getMovieById(id)!= null){
            return new ResponseEntity(movieService.getMovieById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity("Bu id numarasına sahip movie bulunamadı.",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<MovieEntity> addMovie(@RequestBody MovieEntity movie){
//        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        movie.setUser(userService.getUserByName(username));
        movieService.addMovie(movie);
        return new ResponseEntity("Kayıt edildi.", HttpStatus.CREATED);
    }

    @PostMapping("/imdb")
    public ResponseEntity<MovieEntity> addImdbMovie(@RequestBody ImdbRequestModel model) throws IOException, ParseException {
        ApiResponse apiResponse = movieService.addImdbMovie(model.getName(),model.getUsername());
        if(apiResponse.getSuccess()){
            return new ResponseEntity(apiResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieEntity> updateMovie(@PathVariable Long movieId,@RequestBody MovieEntity movie){
        movie.setId(movieId);
//        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        movie.setUser(userService.getUserByName(username));
        ApiResponse apiResponse = movieService.updateMovie(movie);
        if(apiResponse.getSuccess()){
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity(apiResponse,HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<MovieEntity> removeMovie(@PathVariable long movieId){
        ApiResponse apiResponse = movieService.removeMovie(movieId);
        if(apiResponse.getSuccess()){
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
