package obss.movietracker.controller;


import obss.movietracker.model.entity.DirectorEntity;
import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.security.model.JwtUser;
import obss.movietracker.security.utilities.JwtValidator;
import obss.movietracker.service.AdminService;
import obss.movietracker.service.LoginService;
import obss.movietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
    private JwtValidator jwtValidator;

    @GetMapping
    public List<DirectorEntity> getAllDirectors() { return adminService.getAllDirectors(); }

    @GetMapping(value = "/{name}")
    public List<DirectorEntity> searchDirectorsByName(@PathVariable String name){
        return adminService.searchDirectorsByName(name);
    }

    @PostMapping
    public DirectorEntity addDirector(@RequestBody DirectorEntity directorEntity){
        return adminService.addDirector(directorEntity);
    }

    @PutMapping("/{directorId}")
    public DirectorEntity updateDirector(@PathVariable Long directorId, @RequestBody DirectorEntity directorEntity){
        return adminService.updateDirector(directorId, directorEntity);
    }

    @DeleteMapping("/{directorId}")
    public Iterable<DirectorEntity> removeDirector(@PathVariable Long directorId){
        return adminService.removeDirector(directorId);
    }
}
