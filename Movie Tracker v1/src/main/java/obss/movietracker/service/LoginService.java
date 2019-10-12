package obss.movietracker.service;

import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.model.payload.LoginModel;
import obss.movietracker.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService{

    @Autowired
    private UserRepository userRepository;

    public Boolean login(LoginModel loginModel) {
        UserEntity tempUser = userRepository.findByUsername(loginModel.getUsername());
        String sha256hex = DigestUtils.sha256Hex(loginModel.getPassword());
        if(tempUser == null || !tempUser.getPassword().equals(sha256hex)){
            return false;
        }
        return true;
    }
}
