package obss.movietracker.repository;

import obss.movietracker.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, PagingAndSortingRepository<MovieEntity, Long> {
    List<MovieEntity> findMovieEntitiesByName(String name);
    MovieEntity findById(long id);
    MovieEntity findByName(String name);
    boolean existsByImdbId(String id);
}
