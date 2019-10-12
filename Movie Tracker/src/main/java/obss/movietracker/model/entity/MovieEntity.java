package obss.movietracker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movie")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "releaseDate")
    private String releaseDate;

    @Column(name = "imdbRating")
    private String imdbRating;

    @Column(name = "duration")
    private String duration;

    @Column(name = "genre")
    private String genre;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private DirectorEntity director;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<UserMovieList> userMovieListSet = new HashSet<>();

}
