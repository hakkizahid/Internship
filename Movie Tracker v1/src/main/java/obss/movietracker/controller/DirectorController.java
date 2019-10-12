package obss.movietracker.controller;


import obss.movietracker.model.entity.DirectorEntity;
import obss.movietracker.model.payload.ApiResponse;
import obss.movietracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    DirectorService directorService;

    @Autowired
    MovieService movieService;

    @GetMapping
    public Iterable<DirectorEntity> getAllDirectors() { return directorService.getAllDirectors(); }

    @GetMapping(value = "/{name}")
    public List<DirectorEntity> searchDirectorsByName(@PathVariable String name){
        return adminService.searchDirectorsByName(name);
    }

    @PostMapping
    public ResponseEntity<DirectorEntity> addDirector(@RequestBody DirectorEntity directorEntity){
        ApiResponse apiResponse = directorService.addDirector(directorEntity);
        return new ResponseEntity(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{directorId}")
    public ResponseEntity<DirectorEntity> updateDirector(@PathVariable Long directorId, @RequestBody DirectorEntity director){
        director.setId(directorId);
        ApiResponse apiResponse = directorService.updateDirector(director);
        if(apiResponse.getSuccess()){
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity(apiResponse,HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{directorId}")
    public ResponseEntity<DirectorEntity> removeDirector(@PathVariable Long directorId){
        ApiResponse apiResponse = directorService.removeDirector(directorId);
        if(apiResponse.getSuccess()){
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
