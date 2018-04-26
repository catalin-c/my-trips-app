package ro.siit.mytripsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.siit.mytripsapp.entity.Trip;
import ro.siit.mytripsapp.model.TripModel;
import ro.siit.mytripsapp.repository.TripRepository;

@RestController
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    private static TripModel mapToModel(Trip entity) {
        TripModel to = new TripModel();
        to.setId(entity.getId());
        to.setTripName(entity.getTripName());
        to.setImpression(entity.getImpression());
        to.setDateFrom(entity.getDateFrom());
        to.setDateTo(entity.getDateTo());
        return to;
    }

    @RequestMapping(value="/trip", method = RequestMethod.GET)
    public @ResponseBody TripModel getTrip(@RequestParam String tripName)
    {
        return mapToModel(tripRepository.findByTripName(tripName));
    }
}
