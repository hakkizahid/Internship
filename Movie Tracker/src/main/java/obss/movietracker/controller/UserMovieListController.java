package obss.movietracker.controller;


import obss.movietracker.model.cache.MovieList;
import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.model.entity.UserMovieList;
import obss.movietracker.service.AdminService;
import obss.movietracker.service.LoginService;
import obss.movietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/lists")
public class UserMovieListController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    AdminService adminService;

    @GetMapping
    public List<UserMovieList> getAllMovieListTypes() {
        return userService.getAllMovieLists();
    }

    //TODO: tokenla alıcaz
    @GetMapping("/{username}/{listName}")
    public List<MovieEntity> getMovieList(@PathVariable String username, @PathVariable String listName){
        return userService.getMovieList(username, listName);
    }

    @PostMapping
    public UserMovieList addMovieToList(@RequestBody MovieList MovieList){
        return userService.addMovieToList(MovieList.getUserId(), MovieList.getMovieId(), MovieList.getType());
    }

}
