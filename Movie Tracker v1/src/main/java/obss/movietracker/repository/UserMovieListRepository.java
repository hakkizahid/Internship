package obss.movietracker.repository;

import obss.movietracker.model.entity.UserEntity;
import obss.movietracker.model.entity.UserMovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMovieListRepository extends JpaRepository<UserMovieList, Long>, PagingAndSortingRepository<UserMovieList, Long> {
//        UserMovieList findByUserNameAndType(String username, String type)
        List<UserMovieList> findAllByUserAndType(UserEntity userEntity, String type);
        Boolean existsByTypeAndUserAndMovie(UserMovieList userMovieList);
//    void deleteById(Long userMovieListId)
}
