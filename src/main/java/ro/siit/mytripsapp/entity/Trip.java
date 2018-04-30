package ro.siit.mytripsapp.entity;

import ro.siit.mytripsapp.entity.user.User;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Column(name="trip_name")
    private String tripName;

    @Column(name="user_id")
    private Long userId;

//    @Temporal(TemporalType.DATE)
//    @Column(name="date_from")
//    private Date dateFrom;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name="date_to")
//    private Date dateTo;

//    @Temporal(TemporalType.DATE)
    @Column(name="date_from")
    private String dateFrom;

//    @Temporal(TemporalType.DATE)
    @Column(name="date_to")
    private String dateTo;

    @Column(name="impression")
    private String impression;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "trip")
//    private Set<Photo> photos = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "trip")
    private Location location;

//    protected Trip() {}
//
//    public Trip(String tripName, Date dateFrom, Date dateTo, String impression) {
//        this.tripName = tripName;
//        this.dateFrom = dateFrom;
//        this.dateTo = dateTo;
//        this.impression = impression;
//    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Trip))
            return false;
        if (obj == this)
            return true;
        return this.tripName.equals(((Trip) obj).tripName);
    }

//    public int hashCode(){
//        return tripName.length();//for simplicity reason
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //    public Date getDateFrom() {
//        return dateFrom;
//    }
//
//    public void setDateFrom(Date dateFrom) {
//        this.dateFrom = dateFrom;
//    }
//
//    public Date getDateTo() {
//        return dateTo;
//    }
//
//    public void setDateTo(Date dateTo) {
//        this.dateTo = dateTo;
//    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

//    public Set<Photo> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(Set<Photo> photos) {
//        this.photos = photos;
//    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
