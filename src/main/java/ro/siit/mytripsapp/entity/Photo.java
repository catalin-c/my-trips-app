package ro.siit.mytripsapp.entity;

import javax.persistence.*;
import ro.siit.mytripsapp.entity.Trip;

@Entity
@Table(name="photo")
public class Photo {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "photo_link")
    private String photoLink;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name="trip_id")
    private Long tripId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

}



//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "location")
//    private String location;
//
//    @Column(name = "title")
//    private String title;
//
//    @Column(name = "description")
//    private String description;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "trip_id", nullable = false)
//    private Trip trip;
//
//    protected Photo(){}
//
//    public Photo(String location, String title, String description) {
//        this.location = location;
//        this.title = title;
//        this.description = description;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Trip getTrip() {
//        return trip;
//    }
//
//    public void setTrip(Trip trip) {
//        this.trip = trip;
//    }
