package ro.siit.mytripsapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.siit.mytripsapp.entity.Trip;


@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
    public Trip findByTripName(String tripName);
}
