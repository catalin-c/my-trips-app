package ro.siit.mytripsapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.siit.mytripsapp.entity.Photo;
import ro.siit.mytripsapp.model.PhotoModel;

import java.util.List;


@Repository
//Crud Repository<Photo - DAO>
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    public List<Photo> findAllByTripId(Long tripId);
}
