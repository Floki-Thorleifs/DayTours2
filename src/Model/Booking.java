package Model;

public class Booking {

    private int tripID;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private int ticketAmount;


    public Booking(int TRIPID, String NAME, String LASTNAME, String EMAIL, String PHONE, int TICKETAMOUNT) {
        this.tripID = TRIPID;
        this.name = NAME;
        this.lastName = LASTNAME;
        this.email = EMAIL;
        this.phone = PHONE;
        this.ticketAmount = TICKETAMOUNT;
    }

    public int getID() {
        return this.tripID;
    }

    public String getFullName() {
        return this.name + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getTicketAmount() {
        return this.ticketAmount;
    }

}
