package ro.siit.mytripsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ro.siit.mytripsapp.entity.Trip;
import ro.siit.mytripsapp.model.TripModel;
import ro.siit.mytripsapp.repository.TripRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static TripModel mapToModel(Trip entity) {
        TripModel to = new TripModel();
        to.setId(entity.getId());
        to.setUserId(entity.getUserId());
        to.setTripName(entity.getTripName());
        to.setImpression(entity.getImpression());
        to.setDateFrom(entity.getDateFrom());
        to.setDateTo(entity.getDateTo());
        to.setLatitude(entity.getLatitude());
        to.setLongitude(entity.getLongitude());
        to.setCity(entity.getCity());
        to.setCountry(entity.getCountry());
        return to;
    }

    @RequestMapping(value="/trip", method = RequestMethod.GET)
    public @ResponseBody TripModel getTrip(@RequestParam String tripName)
    {
        return mapToModel(tripRepository.findByTripName(tripName));
    }


    @PostMapping("/delete/trip/{id}")
    public void deleteTrip(@PathVariable(value = "id") Long id) {
        jdbcTemplate.update(
                "DELETE FROM mytripapp.photo WHERE trip_id = ?",
                id
        );
        jdbcTemplate.update(
                "DELETE FROM mytripapp.trip WHERE id = ?",
                id
        );
    }


    @PostMapping("/addTrip")
    public Trip addPhoto(@Valid @RequestBody Trip trip) {
        return tripRepository.save(trip);
    }

    @GetMapping("/getTrips/{id}")
    public List<String> getTripById(@PathVariable(value = "id") Long userId) {
        List<TripModel> trips = new ArrayList<>();
        trips.addAll(tripRepository.findAllByUserId(userId).stream().map(TripController::mapToModel).collect(Collectors.toList()));

        List<String> tripNames = new ArrayList<>();
        for (TripModel t : trips) {
            tripNames.add(t.getTripName());
        }
        return tripNames;
    }

    @PatchMapping("/updateTrip")
    public Trip updateTrip(@RequestParam String tripName,
                           @Valid @RequestBody Trip editDetails) {

        Trip trip = tripRepository.findByTripName(tripName);

        
        if(editDetails.getCity() != null) {
            trip.setCity(editDetails.getCity());
        }

        if(editDetails.getCountry() != null) {
            trip.setCountry(editDetails.getCountry());
        }

        if(editDetails.getDateFrom() != null) {
            trip.setDateFrom(editDetails.getDateFrom());
        }

        if(editDetails.getDateTo() != null) {
            trip.setDateTo(editDetails.getDateTo());
        }

        if(editDetails.getImpression() != null) {
            trip.setImpression(editDetails.getImpression());
        }

        if(editDetails.getTripName() != null) {
            trip.setTripName(editDetails.getTripName());
        }

        if(editDetails.getLatitude() != null) {
            trip.setLatitude(editDetails.getLatitude());
        }

        if(editDetails.getLongitude() != null) {
            trip.setLongitude(editDetails.getLongitude());
        }

        Trip updatedNote = tripRepository.save(trip);
        return updatedNote;
    }

}
