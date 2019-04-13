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
    private double rating;
    private int price;


    public Trip(int ID,
                String NAME,
                String INTERESTS,
                String DESCRIPTION,
                String DURATION,
                String INTRODUCTION,
                int SEATS,
                String TOURGUIDE,
                String DATE,
                String LOCATION,
                int PRICE,
                ArrayList<Booking> BOOKINGS,
                double RATING
    ){
        this.id = ID;
        this.name = NAME;
        this.interests = INTERESTS;
        this.description = DESCRIPTION;
        this.duration = DURATION;
        this.introduction = INTRODUCTION;
        this.tourGuide = TOURGUIDE;
        this.date = DATE;
        this.location = LOCATION;
        this.price = PRICE;
        this.bookings = BOOKINGS;
        double tempRating = 0;

        this.rating = RATING;

        if(BOOKINGS.size() > 0){
            for(int i = 0; i < BOOKINGS.size(); i++){
                this.seats = SEATS - BOOKINGS.get(i).getSeats();
            }
        } else {
            this.seats = SEATS;
        }

    }


    public double getRating(){
        return this.rating;
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

    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }

    public void createBooking(Trip trip){

    }
}
