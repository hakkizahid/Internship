package obss.movietracker.repository;

import obss.movietracker.model.entity.DirectorEntity;
import obss.movietracker.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
    List<DirectorEntity> findDirectorsByName(String name);
}
