package models;
//this class is used to store a renter subscription
public class Subscription {
    private String username;
    private int noOfBedrooms;
    private int noOfBathrooms;
    private Boolean isFurnished;
    private String cityQuadrant;
    private String propertyType;


    public Subscription(String username, int noOfBedrooms, int noOfBathrooms, Boolean isFurnished, String cityQuadrant, String propertyType) {
        this.username = username;
        this.noOfBedrooms = noOfBedrooms;
        this.noOfBathrooms = noOfBathrooms;
        this.isFurnished = isFurnished;
        this.cityQuadrant = cityQuadrant;
        this.propertyType = propertyType;
    }
    public Subscription(){
        this.username = "";
        this.noOfBedrooms = -1;
        this.noOfBathrooms = -1;
        this.isFurnished = false;
        this.cityQuadrant = "";
        this.propertyType = "";
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNoOfBedrooms() {
        return this.noOfBedrooms;
    }

    public void setNoOfBedrooms(int noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public int getNoOfBathrooms() {
        return this.noOfBathrooms;
    }

    public void setNoOfBathrooms(int noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public Boolean getIsFurnished() {
        return this.isFurnished;
    }

    public void setIsFurnished(Boolean isFurnished) {
        this.isFurnished = isFurnished;
    }

    public String getCityQuadrant() {
        return this.cityQuadrant;
    }

    public void setCityQuadrant(String cityQuadrant) {
        this.cityQuadrant = cityQuadrant;
    }

    public String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }


}
