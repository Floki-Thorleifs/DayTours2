package Model;

import java.util.ArrayList;

public class Trip {

    private int id;
    private String name;
    private String interests;
    private String description;
    private String duration;
    private String introduction;
    private int seats;
    private ArrayList<Booking> bookings;
    private String tourGuide;
    private String date;
    private String location;
    private int price;


    public Trip(int ID, String NAME, String INTERESTS, String DESCRIPTION, String DURATION, String INTRODUCTION, int SEATS, String TOURGUIDE, String DATE, String LOCATION, int PRICE){
        this.id = ID;
        this.name = NAME;
        this.interests = INTERESTS;
        this.description = DESCRIPTION;
        this.duration = DURATION;
        this.introduction = INTRODUCTION;
        this.seats = SEATS;
        this.tourGuide = TOURGUIDE;
        this.date = DATE;
        this.location = LOCATION;
        this.price = PRICE;
        this.bookings = new ArrayList<>();
    }

    public void updateSeats(int seatsBought){
        this.seats -= seatsBought;
    }

    public String getLocation(){
        return  this.location;
    }

    public String getDate(){
        return this.date;
    }

    public String getName(){
        return this.name;
    }

    public int getSeatCount(){
        return this.seats;
    }

    public String getDescription() { return this.description; }

    public int getPrice() {
        return this.price;
    }

    public String getInterests(){ return this.interests; }

    public String getIntroduction(){ return this.introduction; }

    public String getDuration(){ return this.duration;}

    public int getId(){ return this.id; }

    public String getTourGuide() { return this.tourGuide; }

    public void createBooking(Trip trip){

    }
}
