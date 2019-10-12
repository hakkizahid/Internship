package obss.movietracker.model.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_movie_list")
@IdClass(UserMovieListId.class)
public class UserMovieList implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn
    private MovieEntity movie;

    @Column(name = "type", nullable = false)
    private String type;

}
