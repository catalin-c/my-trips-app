package ro.siit.mytripsapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.siit.mytripsapp.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
