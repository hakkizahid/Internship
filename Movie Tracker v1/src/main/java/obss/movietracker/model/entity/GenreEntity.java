package obss.movietracker.model.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    public GenreEntity(String name) {
        this.name = name;
    }
}

