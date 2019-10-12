package obss.movietracker.service;

import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public UserEntity login(String username, String password){
        if(userRepository.existsByUsername(username)){
            return userRepository.findByUsernameAndPassword(username, password);
        }
        return null;
    }
}
