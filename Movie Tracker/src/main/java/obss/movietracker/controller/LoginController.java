package obss.movietracker.controller;


import obss.movietracker.model.cache.UserLogin;
import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.repository.UserRepository;
import obss.movietracker.security.model.JwtUser;
import obss.movietracker.security.utilities.JwtGenerator;
import obss.movietracker.service.AdminService;
import obss.movietracker.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    JwtGenerator jwtGenerator;

    @Autowired
    UserRepository userRepository;


    @PostMapping
    public ResponseEntity login(@RequestBody JwtUser jwtUser){
        UserEntity userEntity = userRepository.findByUsername(jwtUser.getUsername());
        if(jwtUser.getUsername().equals("admin") && jwtUser.getPassword().equals("1234")){
            jwtUser.setRole("ROLE_ADMIN");
            jwtUser.setId(1l);
            return ResponseEntity.ok().header("Authorization",jwtGenerator.generate(jwtUser)).body(null);
        } else if (!jwtUser.getUsername().equals("admin") && jwtUser.getPassword().equals(userEntity.getPassword())){
            jwtUser.setRole("ROLE_USER");
            jwtUser.setId(userEntity.getId());
            return ResponseEntity.ok().header("Authorization",jwtGenerator.generate(jwtUser)).body(null);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
