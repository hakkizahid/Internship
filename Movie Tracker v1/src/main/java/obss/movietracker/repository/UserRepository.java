package obss.movietracker.repository;

import obss.movietracker.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    UserEntity findById(long id);

}
