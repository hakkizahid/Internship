package obss.movietracker.service;


import obss.movietracker.error.exception.ConflictError;
import obss.movietracker.error.exception.FieldEmptyError;
import obss.movietracker.model.entity.DirectorEntity;
import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.repository.DirectorRepository;
import obss.movietracker.repository.MovieRepository;
import obss.movietracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;


    public UserEntity searchUsersByUsername(String name){ return userRepository.findByUsername(name); }

    public List<DirectorEntity> searchDirectorsByName(String name){ return directorRepository.findDirectorsByName(name); }

    /*MOVIE OPERATIONS*/

    public MovieEntity addMovie(MovieEntity movieEntity){
        if(movieEntity.getName() == null){
            throw new FieldEmptyError("Id or Name  ");
        }
        if (movieRepository.existsById(movieEntity.getId())){
            throw new ConflictError("Your addition is already exist");
        }
        movieRepository.save(movieEntity);
        return movieEntity;
    }

    public MovieEntity updateMovie(Long id, MovieEntity movieEntity){
        if (!movieRepository.findById(id).isPresent()){
            throw new FieldEmptyError("Your Id does  ");
        }
        else{
            if (!id.equals(movieEntity.getId())){
                throw new FieldEmptyError("Your Id does not match");
            }
            else {
                movieRepository.save(movieEntity);
            }
        }
        return movieEntity;
    }

    public Iterable<MovieEntity> removeMovie(Long id){
        if (!movieRepository.findById(id).isPresent()){
            throw new FieldEmptyError("Your Id does not exist");
        }
        movieRepository.deleteById(id);
        return movieRepository.findAll();
    }


    /*USER OPERATIONS*/
    public List<UserEntity> getAllUsers() { return userRepository.findAll(); }

    public UserEntity addUser(UserEntity userEntity){
        if(userEntity.getUsername() == null){
            throw new FieldEmptyError("Id or Name is ");
        }
        if (userRepository.existsById(userEntity.getId())){
            throw new ConflictError("Your addition is already exist");
        }
        userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity updateUser(Long id, UserEntity userEntity){
        if (!userRepository.findById(id).isPresent()){
            throw new FieldEmptyError("Your Id does not ");
        }
        else{
            if (!id.equals(userEntity.getId())){
                throw new FieldEmptyError("Your Id does not match");
            }
            else {
                userRepository.save(userEntity);
            }
        }
        return userEntity;
    }

    public Iterable<UserEntity> removeUser(Long id){
        if (!userRepository.findById(id).isPresent()){
            throw new FieldEmptyError("Your Id does not exist");
        }
        userRepository.deleteById(id);
        return userRepository.findAll();
    }

    /*DIRECTOR OPERATIONS*/

    public List<DirectorEntity> getAllDirectors() { return directorRepository.findAll(); }

    public DirectorEntity addDirector(DirectorEntity directorEntity){
        if(directorEntity.getName() == null || directorEntity.getId() == null){
            throw new FieldEmptyError("Id or Name is empty");
        }
        if (directorRepository.existsById(directorEntity.getId())){
            throw new ConflictError("Your addition is already exist");
        }
        directorRepository.save(directorEntity);
        return directorEntity;
    }

    public DirectorEntity updateDirector(Long id, DirectorEntity directorEntity){
        if (!directorRepository.findById(id).isPresent()){
            throw new FieldEmptyError("Your Id does not exist");
        }
        else{
            if (!id.equals(directorEntity.getId())){
                throw new FieldEmptyError("Your Id does not match");
            }
            else {
                directorRepository.save(directorEntity);
            }
        }
        return directorEntity;
    }

    public Iterable<DirectorEntity> removeDirector(Long id){
        if (!directorRepository.findById(id).isPresent()){
            throw new FieldEmptyError("Your Id does not exist");
        }
        directorRepository.deleteById(id);
        return directorRepository.findAll();
    }

}
