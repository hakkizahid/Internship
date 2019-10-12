package obss.movietracker.repository;

import obss.movietracker.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>, PagingAndSortingRepository<RoleEntity, Long> {
    boolean existsByName(String name);
    RoleEntity findByName(String name);
}

