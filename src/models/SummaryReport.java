package models;
import java.util.ArrayList;
//used to store info about a periodical summary report(in our program this is defined as a listing period defined by the manager)
public class SummaryReport {
    private int totalNoHouses;
    private int totalHousesRented;
    private int totalHousesActive;
    ArrayList<Property> rentedHouses;

    public SummaryReport(int totalNoHouses, int totalHousesRented, int totalHousesActive, ArrayList<Property> rentedHouses){
        this.totalNoHouses = totalHousesActive;
        this.totalHousesRented = totalHousesRented;
        this.totalHousesActive = totalHousesActive;
        this.rentedHouses = rentedHouses;
    }

    public int getTotalNoHouses() {
        return this.totalNoHouses;
    }

    public void setTotalNoHouses(int totalNoHouses) {
        this.totalNoHouses = totalNoHouses;
    }

    public int getTotalHousesRented() {
        return this.totalHousesRented;
    }

    public void setTotalHousesRented(int totalHousesRented) {
        this.totalHousesRented = totalHousesRented;
    }

    public int getTotalHousesActive() {
        return this.totalHousesActive;
    }

    public void setTotalHousesActive(int totalHousesActive) {
        this.totalHousesActive = totalHousesActive;
    }

    public ArrayList<Property> getRentedHouses() {
        return this.rentedHouses;
    }

    public void setRentedHouses(ArrayList<Property> rentedHouses) {
        this.rentedHouses = rentedHouses;
    }
}