package obss.movietracker.service;

import obss.movietracker.error.exception.ConflictError;
import obss.movietracker.error.exception.BadRequestError;
import obss.movietracker.model.entity.DirectorEntity;
import obss.movietracker.model.payload.ApiResponse;
import obss.movietracker.repository.DirectorRepository;
import obss.movietracker.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DirectorService {

    @Autowired DirectorRepository directorRepository;

    public DirectorEntity getDirector(String director) {
        String[] directorNames = director.split(" ", 2);
        if(directorNames.length==1){
            directorNames = new String[]{directorNames[0], directorNames[0]};
        }
        String name = directorNames[0];
        String surname = directorNames[1];
        if(directorRepository.existsByNameAndSurname(name,surname)){
            return directorRepository.findByNameAndSurname(name,surname);
        }else{
            return directorRepository.save(new DirectorEntity(name,surname,new Date(1000), null));
        }
    }

    public List<DirectorEntity> getDirectors(String directorFullName) {
        String[] directors = directorFullName.split(",");
        List<DirectorEntity> directorList = new ArrayList<>();
        for (String s:directors){
            if(s.startsWith(" ")){
                s = s.replaceAll("\\s+","");
            }
            directorList.add(getDirector(s));
        }
        return directorList;
    }

    /*DIRECTOR OPERATIONS*/
    public Iterable<DirectorEntity> getAllDirectors() { return directorRepository.findAll(); }

    public ApiResponse addDirector(DirectorEntity directorEntity){
        if(directorEntity.getName() == null || directorEntity.getId() == null){
            throw new BadRequestError("Id or Name is empty");
        }
        if (directorRepository.existsById(directorEntity.getId())){
            throw new ConflictError("Your addition is already exist");
        }
        directorRepository.save(directorEntity);
        return new ApiResponse(true,"Director saved.");
    }

    public ApiResponse updateDirector(DirectorEntity directorEntity){
        if (!directorRepository.existsById(directorEntity.getId())){
            return new ApiResponse(false,"No director found with this id.");
        }
        directorRepository.save(directorEntity);
        return new ApiResponse(true,"Director updated.");
    }

    public ApiResponse removeDirector(Long id){
        if (!directorRepository.existsById(id)){
            return new ApiResponse(false,"No director found with this id.");
        }
        directorRepository.deleteById(id);
        return new ApiResponse(true,"Director deleted.");
    }
}
