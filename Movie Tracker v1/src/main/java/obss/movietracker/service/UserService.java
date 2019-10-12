package obss.movietracker.service;


import obss.movietracker.error.exception.BadRequestError;
import obss.movietracker.error.exception.ConflictError;
import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.model.entity.UserMovieList;
import obss.movietracker.model.payload.ApiResponse;
import obss.movietracker.model.payload.MovieListModel;
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

    @Autowired UserRepository userRepository;
    @Autowired MovieRepository movieRepository;
    @Autowired DirectorRepository directorRepository;
    @Autowired UserMovieListRepository userMovieListRepository;

    public List<UserMovieList> getAllMovieLists() {
        return userMovieListRepository.findAll();
    }

    public List<MovieEntity> searchMoviesByName(String name){ return movieRepository.findMovieEntitiesByName(name); }

    public List<MovieEntity> getMoviesFromList(String username, String type){
        if(username == null || type == null){
            throw new BadRequestError("Id or Type is empty");
        }
        UserEntity userEntity = userRepository.findByUsername(username);
        List<UserMovieList> userMovieLists = userMovieListRepository.findAllByUserAndType(userEntity,type);
        List<MovieEntity> movieList = new ArrayList<>();
        userMovieLists.forEach(userMovieList -> movieList.add(userMovieList.getMovie()));
        return movieList;
    }

    public ApiResponse addMovieToList(MovieListModel movieListModel){
        if(movieListModel.getUserId() == null || movieListModel.getMovieId() == null || movieListModel.getType() == null){
            throw new BadRequestError("Your input is deficient. User Id or Movie Id or Type is missing.");
        }
        UserMovieList userMovieList = new UserMovieList();
        UserEntity userEntity = userRepository.findById((long) movieListModel.getUserId());
        MovieEntity movieEntity = movieRepository.findById((long) movieListModel.getMovieId());
        userMovieList.setUser(userEntity);
        userMovieList.setMovie(movieEntity);
        userMovieList.setType(movieListModel.getType());

        if (userMovieListRepository.existsByTypeAndUserAndMovie(userMovieList)){
            throw new ConflictError("Your addition is already exist");
        }
        userMovieListRepository.save(userMovieList);
        return new ApiResponse(true,"Movie saved to List");
    }

    public ApiResponse removeMovieFromList(String username, String movieName, String type) {
        if(username == null || type == null){
            throw new BadRequestError("Your input is deficient. Username or Type is missing.");
        }
        UserMovieList userMovieList = new UserMovieList();
        UserEntity userEntity = userRepository.findByUsername(username);
        MovieEntity movieEntity = movieRepository.findByName(movieName);
        userMovieList.setUser(userEntity);
        userMovieList.setMovie(movieEntity);
        userMovieList.setType(type);
        userMovieListRepository.delete(userMovieList);
        return new ApiResponse(true,"Movie deleted from List");
    }
}
