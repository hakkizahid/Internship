package obss.movietracker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import obss.movietracker.error.exception.ConflictError;
import obss.movietracker.error.exception.BadRequestError;
import obss.movietracker.model.entity.GenreEntity;
import obss.movietracker.model.entity.MovieEntity;
import obss.movietracker.model.payload.ApiResponse;
import obss.movietracker.repository.GenreRepository;
import obss.movietracker.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieService {

    @Autowired MovieRepository movieRepository;
    @Autowired GenreRepository genreRepository;
    @Autowired DirectorService directorService;

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    /* OMDB DATA COLLECTER */
    public ApiResponse addImdbMovie(String name, String username) {
        String uri;
        if(name.startsWith("tt")){
            uri = "http://www.omdbapi.com/?i=";
            uri = uri.concat(name).concat("&apikey=38e228d");
            System.out.println("idd girdi");
        }else{
            uri = "http://www.omdbapi.com/?t=";
            uri = uri.concat(name).concat("&apikey=38e228d");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class,name);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(result);
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(result);
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }

        if(jsonNode.get("Response").asText().equals("True") && !movieRepository.existsByImdbId(jsonNode.get("imdbID").asText())){
            MovieEntity movie = new MovieEntity();
            movie.setName(jsonNode.get("Title").asText());
//            movie.setUser(userRepository.findByUsername(username))
            String directorFullName  = jsonNode.get("Director").asText();
            movie.setDirectors(directorService.getDirectors(directorFullName));
            String genreFull  = jsonNode.get("Genre").asText();
            movie.setGenres(getGenres(genreFull));
            movie.setImage(jsonNode.get("Poster").asText());
            movie.setImdbId(jsonNode.get("imdbID").asText());
            movie.setDuration(jsonNode.get("Runtime").asText());
            String rating =  jsonNode.get("imdbRating").asText();
            if(rating.equals("N/A")){
                movie.setRating(0);
            }else{
                movie.setRating(Float.parseFloat(rating));
            }
            String date = jsonNode.get("Released").asText();
            System.out.println("date" + date);
            Date releaseDate = new Date(1000);
            try {
                releaseDate = formatter.parse(date);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.getStackTrace();
                System.out.println("hatata");
            }
            System.out.println("date2" + releaseDate);
            movie.setReleaseDate(releaseDate);
            System.out.println(movie);
            movieRepository.save(movie);

            return new ApiResponse(true, "Movie saved from IMDB.");
        }
        if(jsonNode.get("Response").asText().equals("False")){
            return new ApiResponse(false, "Movie not found from IMDB.");
        }
        else{
            return new ApiResponse(false,"There is a movie with the same IMDBID");
        }
    }


    /*MOVIE OPERATIONS*/
    public List<MovieEntity> getAllMovies() { return movieRepository.findAll(); }

    public MovieEntity getMovieById(long id) {
        if(!movieRepository.existsById(id)){
            return null;
        }
        return movieRepository.findById(id);
    }

    public ApiResponse addMovie(MovieEntity movie) {
        if(movie.getName() == null){
            throw new BadRequestError("Id or Name  ");
        }
        if (movieRepository.existsById(movie.getId())){
            throw new ConflictError("Your addition is already exist");
        }
        movieRepository.save(movie);
        return new ApiResponse(true,"Movie saved.");
    }

    public ApiResponse updateMovie(MovieEntity movie){
        if(!movieRepository.existsById(movie.getId())){
            return  new ApiResponse(false,"No movie found with this id.");
        }
        movieRepository.save(movie);
        return new ApiResponse(true,"Movie updated.");
    }

    public ApiResponse removeMovie(long id){
        if(!movieRepository.existsById(id)){
            return new ApiResponse(false,"No movie found with this id.");
        }
        movieRepository.deleteById(id);
        return new ApiResponse(true,"Movie deleted.");
    }

    /* GENRE */
    public Set<GenreEntity> getGenres(String genreFull) {
        String[] genres = genreFull.split(",");
        Set<GenreEntity> genreList = new HashSet<>();
        for (String s:genres){
            s = s.replaceAll("\\s+","");
            if(!genreRepository.existsByName(s)){
                GenreEntity genre = new GenreEntity(s);
                genreRepository.save(genre);
                genreList.add(genre);
            }
            genreList.add(genreRepository.findByName(s));
        }
        return genreList;
    }
}
