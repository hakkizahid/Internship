package obss.movietracker.controller;


import obss.movietracker.model.payload.LoginModel;
import obss.movietracker.repository.UserRepository;
import obss.movietracker.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginModel> login(@RequestBody LoginModel loginModel){
        if(loginService.login(loginModel)){
            return new ResponseEntity("Giris yapıldı.", HttpStatus.OK);
        }else{
            return new ResponseEntity("Giriş yapılamadı.",HttpStatus.CONFLICT);
        }
    }


//    @PostMapping
//    public ResponseEntity login(@RequestBody JwtUser jwtUser){
//        UserEntity userEntity = userRepository.findByUsername(jwtUser.getUsername());
//        if(jwtUser.getUsername().equals("admin") && jwtUser.getPassword().equals("1234")){
//            jwtUser.setRole("ROLE_ADMIN");
//            jwtUser.setId(1l);
//            return ResponseEntity.ok().header("Authorization",jwtGenerator.generate(jwtUser)).body(null);
//        } else if (!jwtUser.getUsername().equals("admin") && jwtUser.getPassword().equals(userEntity.getPassword())){
//            jwtUser.setRole("ROLE_USER");
//            jwtUser.setId(userEntity.getId());
//            return ResponseEntity.ok().header("Authorization",jwtGenerator.generate(jwtUser)).body(null);
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

}
