package obss.movietracker.controller;


import obss.movietracker.model.payload.AddMovieToListModel;
import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.model.entity.UserMovieList;
import obss.movietracker.model.payload.ApiResponse;
import obss.movietracker.service.AdminService;
import obss.movietracker.service.LoginService;
import obss.movietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<MovieEntity> getMoviesFromList(@PathVariable String username, @PathVariable String listName){
        return userService.getMoviesFromList(username, listName);
    }

    @PostMapping
    public ApiResponse addMovieToList(@RequestBody AddMovieToListModel AddMovieToListModel){
        return userService.addMovieToList(AddMovieToListModel.getUserId(), AddMovieToListModel.getMovieId(), AddMovieToListModel.getType());
    }

    @DeleteMapping ApiResponse removeMovieFromList(@PathVariable String username, @PathVariable String movieName,@PathVariable String listName){
        return userService.removeMovieFromList(username, movieName, listName);
    }

}
