package Model;

import java.util.ArrayList;

public class Trip {

    private String name;
    private String interests;
    private String description;
    private String duration;
    private String introduction;
    private int seats;
    private int id;
    //TODO Import Booking
    private String tourGuide;
    private String date;
    private String location;
    private int price;


    public Trip(String NAME, String INTERESTS, String DESCRIPTION, String DURATION, String INTRODUCTION, int SEATS, int ID, String TOURGUIDE, String DATE, String LOCATION, int PRICE){
        this.name = NAME;
        this.interests = INTERESTS;
        this.description = DESCRIPTION;
        this.duration = DURATION;
        this.introduction = INTRODUCTION;
        this.seats = SEATS;
        this.id = ID;
        this.tourGuide = TOURGUIDE;
        this.date = DATE;
        this.location = LOCATION;
        this.price = PRICE;
    }

    public void updateSeats(int seatsBought){
        this.seats -= seatsBought;
    }

    public String getLocation(){
        return  this.location;
    }

    public ArrayList<String> getDates(){
        return this.date;
    }

    public String getName(){
        return this.name;
    }

    public int getSeatCount(){
        return this.seats;
    }

}
