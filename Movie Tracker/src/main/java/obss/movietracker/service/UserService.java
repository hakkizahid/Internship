package obss.movietracker.service;


import obss.movietracker.error.exception.FieldEmptyError;
import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.model.entity.UserMovieList;
import obss.movietracker.repository.DirectorRepository;
import obss.movietracker.repository.MovieRepository;
import obss.movietracker.repository.UserMovieListRepository;
import obss.movietracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    UserMovieListRepository userMovieListRepository;

    public List<MovieEntity> getAllMovies() { return movieRepository.findAll(); }

    public List<UserMovieList> getAllMovieLists() {
        return userMovieListRepository.findAll();
    }

    public List<MovieEntity> searchMoviesByName(String name){ return movieRepository.findMoviesByName(name); }

    public UserMovieList addMovieToList(Long userId, Long movieId, String type){
        if(userId == null || movieId == null || type == null){
            throw new FieldEmptyError("Id or Name is empty");
        }
//        if (userMovieListRepository.getOne(userMovieList.getId())){
//            throw new ConflictError("Your addition is already exist");
//        }
        UserMovieList userMovieList = new UserMovieList();
        UserEntity userEntity = userRepository.findById((long) userId);
        MovieEntity movieEntity = movieRepository.findById((long) movieId);
        userMovieList.setUser(userEntity);
        userMovieList.setMovie(movieEntity);
        userMovieList.setType(type);
        userMovieListRepository.save(userMovieList);
        return userMovieList;
    }

    public List<MovieEntity> getMovieList(String username, String type){
        if(username == null || type == null){
            throw new FieldEmptyError("Id or Type is empty");
        }
        UserEntity userEntity = userRepository.findByUsername(username);
        List<UserMovieList> userMovieLists = userMovieListRepository.findAllByUserAndType(userEntity,type);
        List<MovieEntity> movieList = new ArrayList<>();
        userMovieLists.forEach(userMovieList -> movieList.add(userMovieList.getMovie()));
        return movieList;
    }

}
