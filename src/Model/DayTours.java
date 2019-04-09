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

    private ArrayList<Trip> trips = new ArrayList<Trip>();

    public DayTours(){

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("/Users/egill/Dropbox/University/Þroun/DayTours2/src/JSON/gogn.json"));
            JSONArray jsonArray = (JSONArray) obj;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                int id = (int) jsonObject.get("id");
                String name = (String) jsonObject.get("Name");
                String interests = (String) jsonObject.get("Interests");
                String location = (String) jsonObject.get("Location");
                String tourGuide = (String) jsonObject.get("Tour Guide");
                String duration = (String) jsonObject.get("Duration");
                String date = (String) jsonObject.get("Date");
                String introduction = (String) jsonObject.get("Introduction");
                int seats = (int) jsonObject.get("Seats");
                String description = (String) jsonObject.get("Description");
                int price = (int) jsonObject.get("Price");

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
            ArrayList<String> dates = trips.get(i).getDate();
            for (int j = 0; j < dates.size(); j++) {
                Date tripDate = sdf.parse(dates.get(j));
                if(inputStart.before(tripDate) && inputEnd.after(tripDate) || inputStart.equals(tripDate) || inputEnd.equals(tripDate)){
                    matches.add(trips.get(j));
                    break;
                }
            }
        }
        trips = matches;
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

        trips = matches;
    }

    public void searchLocations(String s) {
        ArrayList<Trip> matches = new ArrayList<Trip>();

        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getLocation() == s) {
                matches.add(trips.get(i));
            }
        }
        trips = matches;
    }

    public void searchPrice(int low, int high ) {
        ArrayList<Trip> matches = new ArrayList<Trip>();

        for(int i = 0; i < trips.size(); i++) {
            if(trips.get(i).getPrice() >= low && trips.get(i).getPrice() <= high ) {
                matches.add(trips.get(i));
            }
        }
        trips = matches;
    }

    public void searchSeats(int number) {
        ArrayList<Trip> matches = new ArrayList<>();

        for(int i = 0; i < trips.size(); i++){
            if(trips.get(i).getSeatCount() >= number){
                matches.add(trips.get(i));
            }
        }
        trips = matches;
    }

    public void searchInterests(String input){
        ArrayList<Trip> matches = new ArrayList<>();

        for(int i = 0; i < trips.size(); i++){
            ArrayList<String> interests = trips.get(i).getInterests();
            for(int j = 0; j < interests.size(); j++){
                if(input.toLowerCase().equals(interests.get(j).toLowerCase())){
                    matches.add(trips.get(i));
                }
            }
        }
        trips = matches;
    }

    public ArrayList<Trip> getTrips(){
        return trips;
    }
}
