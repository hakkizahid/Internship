package obss.movietracker.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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

    @Column
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMovieList)) return false;
        UserMovieList that = (UserMovieList) o;
        return Objects.equals(user.getName(), that.user.getName()) &&
                Objects.equals(movie.getName(), that.movie.getName());
    }




}
