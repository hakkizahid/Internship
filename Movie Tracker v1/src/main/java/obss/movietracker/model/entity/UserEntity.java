package obss.movietracker.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String  username;
    @Column(name = "email", nullable = false, unique = true)
    private String  email;
    @Column(name = "password", nullable = false)
    private String  password;
    @Column(name = "name")
    private String  name;
    @Column(name = "surname")
    private String  surname;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserMovieList> userMovieListSet;

    @JsonIgnore
    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RoleEntity> roles = new HashSet<>();

}
