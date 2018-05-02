package ro.siit.mytripsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.siit.mytripsapp.entity.Trip;

import java.util.List;
import java.util.Optional;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    public Trip findByTripName(String tripName);
    public List<Trip> findAllByUserId(Long userId);
}
