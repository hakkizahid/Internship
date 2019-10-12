package obss.movietracker.repository;

import obss.movietracker.model.entity.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity,Long>, PagingAndSortingRepository<GenreEntity, Long> {
    boolean existsByName(String name);
    GenreEntity findByName(String name);
    Page<GenreEntity> findAll(Pageable pageable);
}
