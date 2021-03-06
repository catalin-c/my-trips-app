package ro.siit.mytripsapp.model;

import ro.siit.mytripsapp.entity.Photo;

import java.util.Date;


public class TripModel {

    private Long id;
    private String tripName;
    private Long userId;
    private String dateFrom;
    private String dateTo;
    private String impression;
    private String latitude;
    private String longitude;
    private String city;
    private String country;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
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
}
