package ro.siit.mytripsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.siit.mytripsapp.entity.Trip;

import java.util.List;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    public Trip findByTripName(String tripName);
    public List<Trip> findAllByUserId(Long userId);
}
