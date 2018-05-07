package ro.siit.mytripsapp.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.siit.mytripsapp.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;


@RestController
public class ValidationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/email-validator", method = RequestMethod.POST, produces = "application/json")
    public JsonResponse validateEmail(@RequestParam String email) {

        int count = jdbcTemplate.queryForObject("SELECT COUNT(email) from mytripapp.user WHERE email=?", Integer.class, email);

        if (count == 1) {
            return new JsonResponse(false, "This email is already taken.");
        }

        return new JsonResponse(true, "This email is available.");
    }

    @RequestMapping(value = "/trip-validator", method = RequestMethod.POST, produces = "application/json")
    public JsonResponse validateTrip(@RequestParam String tripName) {

        int count = jdbcTemplate.queryForObject("SELECT COUNT(trip_name) from mytripapp.trip WHERE trip_name=?", Integer.class, tripName);

        if (count == 1) {
            return new JsonResponse(false, "This trip name is already taken.");
        }

        return new JsonResponse(true, "This trip name is available.");
    }


}
