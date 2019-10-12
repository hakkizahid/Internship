package obss.movietracker.model.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "name",nullable = false, unique = true)
    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }

}
