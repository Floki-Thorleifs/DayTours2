package Model;

public class Booking {

    private int tripID;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private int seats;
    private String date;
    private int id;


    public Booking(int TRIPID, String NAME, String LASTNAME, String EMAIL, String PHONE, int SEATS, String DATE, int ID) {
        this.tripID = TRIPID;
        this.name = NAME;
        this.lastName = LASTNAME;
        this.email = EMAIL;
        this.phone = PHONE;
        this.seats = SEATS;
        this.date = DATE;
        this.id = ID;
    }

    public int getID() {
        return this.tripID;
    }

    public String getFullName() {
        return this.name + " " + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getSeats() {
        return this.seats;
    }

    public String getDate() { return this.date; }

    public int getrealID(){
        return this.id;
    }


}
