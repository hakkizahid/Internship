package obss.movietracker.controller;



import com.fasterxml.jackson.annotation.JsonView;
import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.security.model.JwtUser;
import obss.movietracker.security.utilities.JwtValidator;
import obss.movietracker.service.AdminService;
import obss.movietracker.service.LoginService;
import obss.movietracker.service.UserService;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    AdminService adminService;

    @Autowired
    JwtValidator jwtValidator;

    @GetMapping
    public List<UserEntity> getAllUsers() { return adminService.getAllUsers(); }

    @GetMapping("/{name}")
    public UserEntity searchUsersByUsername(@PathVariable String name){ return adminService.searchUsersByUsername(name); }

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity userEntity){
            return adminService.addUser(userEntity);
    }

    @PutMapping("/{userId}")
    public UserEntity updateUser(@PathVariable Long userId, @RequestBody UserEntity userEntity){
        return adminService.updateUser(userId, userEntity);
    }

    @DeleteMapping("/{userId}")
    public Iterable<UserEntity> removeUser(@PathVariable long userId){
        return adminService.removeUser(userId);
    }
}
