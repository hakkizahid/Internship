package obss.movietracker.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserMovieListId implements Serializable {
    Long user;
    Long movie;
}
