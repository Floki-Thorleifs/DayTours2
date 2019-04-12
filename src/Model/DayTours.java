package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class DayTours {

    public ArrayList<Trip> trips = new ArrayList<Trip>();
    private ArrayList<Trip> filtered = trips;
    private ArrayList<Booking> books = new ArrayList<>();

    public DayTours(){

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/JSON/gogn.json"));
            JSONArray jsonArray = (JSONArray) obj;
            Object bookings = parser.parse(new FileReader("src/JSON/bookings.json"));
            JSONArray bookingsArray = (JSONArray) bookings;

            for(int j = 0; j < bookingsArray.size(); j++){
                JSONObject bookObject = (JSONObject) bookingsArray.get(j);

                int tripId = (int) ((long)bookObject.get("tripID"));
                String fName = (String) bookObject.get("firstName");
                String lName = (String) bookObject.get("lastName");
                String email = (String) bookObject.get("email");
                String phone = (String) bookObject.get("phonenumber");
                int seats = (int)((long) bookObject.get("seats"));
                String date = (String) bookObject.get("date");
                int id = (int)((long) bookObject.get("id"));
                Booking temp = new Booking(tripId, fName, lName, email, phone, seats, date,id);
                books.add(temp);
            }


            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id =  (int)((long)jsonObject.get("id"));
                String name = (String) jsonObject.get("Name");
                String interests = (String) jsonObject.get("Interests");
                String location = (String) jsonObject.get("Location");
                String tourGuide = (String) jsonObject.get("Tour Guide");
                String duration = (String) jsonObject.get("Duration");
                String date = (String) jsonObject.get("Date");
                String introduction = (String) jsonObject.get("Introduction");
                int seats = (int)((long)jsonObject.get("Seats"));
                String description = (String) jsonObject.get("Description");
                int price = (int)((long)jsonObject.get("Price"));
                ArrayList<Booking> tripBookings = new ArrayList<>();
                for(int j = 0; j < books.size(); j++){
                    if(books.get(j).getID() == id){
                        System.out.println(books.get(j).getID());
                        System.out.println(id);
                        tripBookings.add(books.get(j));
                    }
                }
                trips.add(new Trip(
                        id,
                        name,
                        interests,
                        description,
                        duration,
                        introduction,
                        seats,
                        tourGuide,
                        date,
                        location,
                        price,
                        tripBookings

                ));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void searchDates(LocalDate start, LocalDate end) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Trip> matches = new ArrayList<Trip>();
        Date inputStart = sdf.parse(start.toString());
        Date inputEnd = sdf.parse(end.toString());

        for (int i = 0; i < filtered.size(); i++) {
            String date = filtered.get(i).getDate();
            Date tripDate = sdf.parse(date);
            if(inputStart.before(tripDate) && inputEnd.after(tripDate) || inputStart.equals(tripDate) || inputEnd.equals(tripDate)){
                matches.add(filtered.get(i));
            }
        }
        filtered = matches;
    }

    public void searchName(String s) {
        ArrayList<Trip> matches = new ArrayList<Trip>();

        for (int i = 0; i < filtered.size(); i++) {
            String name = filtered.get(i).getName();
            String descr = filtered.get(i).getDescription();
            if (name.toLowerCase().contains(s.toLowerCase()) || descr.toLowerCase().contains(s.toLowerCase())) {
                matches.add(filtered.get(i));
            }
        }

        filtered = matches;
    }

    public void searchLocations(String s) {
        ArrayList<Trip> matches = new ArrayList<Trip>();

        for (int i = 0; i < filtered.size(); i++) {
            if (filtered.get(i).getLocation().equals(s)) {
                matches.add(filtered.get(i));
            }
        }
        filtered = matches;
    }

    public void searchPrice(int low, int high ) {
        ArrayList<Trip> matches = new ArrayList<Trip>();

        for(int i = 0; i < filtered.size(); i++) {
            if(filtered.get(i).getPrice() >= low && filtered.get(i).getPrice() <= high ) {
                matches.add(filtered.get(i));
            }
        }
        filtered = matches;
    }

    public void searchSeats(int number) {
        ArrayList<Trip> matches = new ArrayList<>();

        for(int i = 0; i < filtered.size(); i++){
            if(filtered.get(i).getSeatCount() >= number){
                matches.add(filtered.get(i));
            }
        }
        filtered = matches;
    }

    public void searchInterests(String input){
        ArrayList<Trip> matches = new ArrayList<>();

        for(int i = 0; i < filtered.size(); i++){
            String interests = filtered.get(i).getInterests();
            if(input.toLowerCase().equals(interests.toLowerCase())){
                matches.add(filtered.get(i));
            }
        }
        filtered = matches;
    }

    public Trip getTripById(String id) {
        for(int i = 0; i < filtered.size(); i++){
            if(filtered.get(i).getId() == Integer.parseInt(id)){
                return filtered.get(i);
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getInfo() {
        ArrayList<String> interests = new ArrayList<>();
        ArrayList<String> places = new ArrayList<>();
        for(int i = 0; i < filtered.size(); i++){
            if(!interests.contains(filtered.get(i).getInterests())){
                interests.add(filtered.get(i).getInterests());
            }
            if(!places.contains(filtered.get(i).getLocation())){
                places.add(filtered.get(i).getLocation());
            }
        }
        ArrayList<ArrayList<String>> stuff = new ArrayList<>();
        stuff.add(interests);
        stuff.add(places);
        return stuff;
    }

    public ArrayList<Trip> getTrips(){
        return filtered;
    }

    public ArrayList<Trip> getBookings(){
        ArrayList<Trip> matches = new ArrayList<>();
        for(int i = 0; i < trips.size(); i++){
            if(trips.get(i).getBookings().size() != 0){
                matches.add(trips.get(i));
            }
        }
        return matches;
    }



    public Booking getBookingById(int number){
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getrealID() == number){
                return books.get(i);
            }
        }
        return null;
    }


    public void freshStart(){
        filtered = trips;
    }
}
