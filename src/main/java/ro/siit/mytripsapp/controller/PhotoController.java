package ro.siit.mytripsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.siit.mytripsapp.entity.Photo;
import ro.siit.mytripsapp.entity.Trip;
import ro.siit.mytripsapp.model.PhotoModel;
import ro.siit.mytripsapp.model.TripModel;
import ro.siit.mytripsapp.repository.PhotoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    private static PhotoModel mapToModel(Photo entity) {
        PhotoModel to = new PhotoModel();
        to.setId(entity.getId());
        to.setTitle(entity.getTitle());
        to.setDescription(entity.getDescription());
        to.setPhotoLink(entity.getPhotoLink());
        to.setTripId(entity.getTripId());
        return to;
    }

    @RequestMapping("/photos")
    public @ResponseBody List<PhotoModel> getPhotos(@RequestParam Long tripId) {
        List<PhotoModel> photos = new ArrayList<>();
        photos.addAll(photoRepository.findAllByTripId(tripId).stream().map(PhotoController::mapToModel).collect(Collectors.toList()));
        return photos;
    }

    @PostMapping("/addPhoto")
    public Photo createNote(@Valid @RequestBody Photo photo) {
        return photoRepository.save(photo);
    }
}
