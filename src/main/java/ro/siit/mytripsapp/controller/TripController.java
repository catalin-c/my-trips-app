package ro.siit.mytripsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ro.siit.mytripsapp.entity.Trip;
import ro.siit.mytripsapp.model.TripModel;
import ro.siit.mytripsapp.repository.TripRepository;

@RestController
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static TripModel mapToModel(Trip entity) {
        TripModel to = new TripModel();
        to.setId(entity.getId());
        to.setTripName(entity.getTripName());
        to.setImpression(entity.getImpression());
        to.setDateFrom(entity.getDateFrom());
        to.setDateTo(entity.getDateTo());
        to.setLatitude(entity.getLatitude());
        to.setLongitude(entity.getLongitude());
        return to;
    }

    @RequestMapping(value="/trip", method = RequestMethod.GET)
    public @ResponseBody TripModel getTrip(@RequestParam String tripName)
    {
        return mapToModel(tripRepository.findByTripName(tripName));
    }


    @PostMapping("/delete/trip/{id}")
    public void solveNeed(@PathVariable(value = "id") Long id) {
        jdbcTemplate.update(
                "DELETE FROM mytripapp.photo WHERE trip_id = ?",
                id
        );
        jdbcTemplate.update(
                "DELETE FROM mytripapp.trip WHERE id = ?",
                id
        );
    }
}
