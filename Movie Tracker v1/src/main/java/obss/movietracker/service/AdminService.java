package obss.movietracker.service;


import obss.movietracker.error.exception.BadRequestError;
import obss.movietracker.model.entity.*;
import obss.movietracker.model.payload.ApiResponse;
import obss.movietracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class AdminService {

    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired MovieRepository movieRepository;
    @Autowired DirectorRepository directorRepository;
    @Autowired GenreRepository genreRepository;

//    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public UserEntity searchUsersByUsername(String name){ return userRepository.findByUsername(name); }

    public List<DirectorEntity> searchDirectorsByName(String name){ return directorRepository.findDirectorsByName(name); }

    /*USER OPERATIONS*/
    public List<UserEntity> getAllUsers() { return userRepository.findAll(); }

    public UserEntity getUserById(long id) { return userRepository.findById(id); }

    public UserEntity getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    public ApiResponse addUser(UserEntity user) {
        if(userRepository.existsByUsername(user.getUsername())){
            return new ApiResponse(false,"There is a User with the same username");
        }
        Set<RoleEntity> roles =  new HashSet<>();
        roles.add(roleRepository.findByName("User"));
        user.setRoles(roles);
        userRepository.save(user);
        return new ApiResponse(true,"User saved.");
    }

    public ApiResponse updateUser(UserEntity user){
        if (!userRepository.existsById(user.getId())){
            throw new BadRequestError("Your Id does not ");
        }
        userRepository.save(user);
        return new ApiResponse(true,"User updated.");
    }

    public ApiResponse removeUser(long id){
        if (!userRepository.existsById(id)){
            throw new BadRequestError("Your Id does not exist");
        }
        userRepository.deleteById(id);
        return new ApiResponse(true,"User deleted.");
    }



}
