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

    public DayTours(){

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/JSON/gogn.json"));
            JSONArray jsonArray = (JSONArray) obj;


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
                        price
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

        for (int i = 0; i < trips.size(); i++) {
            String date = trips.get(i).getDate();
            Date tripDate = sdf.parse(date);
            if(inputStart.before(tripDate) && inputEnd.after(tripDate) || inputStart.equals(tripDate) || inputEnd.equals(tripDate)){
                matches.add(trips.get(i));
            }
        }
        filtered = matches;
    }

    public void searchName(String s) {
        ArrayList<Trip> matches = new ArrayList<Trip>();

        for (int i = 0; i < trips.size(); i++) {
            String name = trips.get(i).getName();
            String descr = trips.get(i).getDescription();
            if (name.toLowerCase().contains(s.toLowerCase()) || descr.toLowerCase().contains(s.toLowerCase())) {
                matches.add(trips.get(i));
            }
        }

        filtered = matches;
    }

    public void searchLocations(String s) {
        ArrayList<Trip> matches = new ArrayList<Trip>();

        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getLocation().equals(s)) {
                matches.add(trips.get(i));
            }
        }
        filtered = matches;
    }

    public void searchPrice(int low, int high ) {
        ArrayList<Trip> matches = new ArrayList<Trip>();

        for(int i = 0; i < trips.size(); i++) {
            if(trips.get(i).getPrice() >= low && trips.get(i).getPrice() <= high ) {
                matches.add(trips.get(i));
            }
        }
        filtered = matches;
    }

    public void searchSeats(int number) {
        ArrayList<Trip> matches = new ArrayList<>();

        for(int i = 0; i < trips.size(); i++){
            if(trips.get(i).getSeatCount() >= number){
                matches.add(trips.get(i));
            }
        }
        filtered = matches;
    }

    public void searchInterests(String input){
        ArrayList<Trip> matches = new ArrayList<>();

        for(int i = 0; i < trips.size(); i++){
            String interests = trips.get(i).getInterests();
            if(input.toLowerCase().equals(interests.toLowerCase())){
                matches.add(trips.get(i));
            }
        }
        filtered = matches;
    }

    public Trip getTripById(String id) {
        for(int i = 0; i < trips.size(); i++){
            if(trips.get(i).getId() == Integer.parseInt(id)){
                return trips.get(i);
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getInfo() {
        ArrayList<String> interests = new ArrayList<>();
        ArrayList<String> places = new ArrayList<>();
        for(int i = 0; i < trips.size(); i++){
            if(!interests.contains(trips.get(i).getInterests())){
                interests.add(trips.get(i).getInterests());
            }
            if(!places.contains(trips.get(i).getLocation())){
                places.add(trips.get(i).getLocation());
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

    public void freshStart(){
        System.out.println(filtered.get(1).getName());
        System.out.println(trips.get(1).getName());
        filtered = trips;
    }
}
