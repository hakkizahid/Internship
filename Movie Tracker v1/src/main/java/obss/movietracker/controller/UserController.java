package obss.movietracker.controller;

import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.model.payload.ApiResponse;
import obss.movietracker.model.payload.LoginModel;
import obss.movietracker.security.model.JwtAuthenticationResponse;
import obss.movietracker.security.utilities.JwtTokenProvider;
import obss.movietracker.service.AdminService;
import obss.movietracker.service.LoginService;
import obss.movietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    LoginService loginService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginModel loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        System.out.println(jwt);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }



    @GetMapping
    public List<UserEntity> getAllUsers() { return adminService.getAllUsers(); }

    @GetMapping("/{name}")
    public UserEntity searchUsersByUsername(@PathVariable String name){ return adminService.searchUsersByUsername(name); }

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ApiResponse apiResponse = adminService.addUser(user);
        if (apiResponse.getSuccess()) {
            return new ResponseEntity(apiResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(apiResponse, HttpStatus.CONFLICT);
        }
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
