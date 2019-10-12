package obss.movietracker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "movie")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "imdbId")
    private String imdbId;

    @Column(name = "rating")
    private float rating;

    @Column(name = "image")
    private String image;

    @Column(name = "duration")
    private String duration;

    @Column(name = "releaseDate")
    private Date releaseDate;

    @JsonIgnore
    @ManyToMany( cascade =  CascadeType.ALL)
    private Set<GenreEntity> genres;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany()
    private List<DirectorEntity> directors = new ArrayList<DirectorEntity>();

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<UserMovieList> userMovieListSet = new HashSet<>();

}
