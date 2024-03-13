import java.util.ArrayList;
import java.util.Random;

public class Passenger {
	private final String firstName;
	private final String lastName;
	private String address;
	private String phoneNumber;
	private final ArrayList<Ticket> myTickets = new ArrayList<>();

    public Passenger(String f, String l, String a, String p) {
		firstName = f;
		lastName = l;
		address = a;
		phoneNumber = p;
	}
	
	public Passenger() {
		Random r = new Random();
        String[] firstNames = {"Aragorn", "Gandalf", "Frodo", "Legolas", "Samwise", "Mitchell", "Gustavo", "Nash", "Ayla",
                "Shea", "Ariel", "Jorge", "Parker", "Rebekah", "Campbell", "Angela", "Adelaide", "Randall", "George", "Dean"};
        firstName = firstNames[r.nextInt(firstNames.length)];
        String[] lastNames = {"Thomas", "Lynn", "Mack", "Blackwell", "Fowler", "Harper", "Adams", "Wallace", "Pierce", "Walters",
                "Chung", "Petty", "Rice", "Velez", "Shields", "Hahn", "Simpson", "Daniels", "Gillespie", "Chandler"};
        lastName = lastNames[r.nextInt(lastNames.length)];
		address = r.nextInt(100)+1 + " " + r.nextInt(100)+1 + "th Street.";
		phoneNumber = Integer.toString(r.nextInt(99999) + 10000)+Integer.toString(r.nextInt(99999) + 10000); //10 digits.
	}

	public ArrayList<Ticket> getMyTickets() {
		return myTickets;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phoneNumber;
	}
	
	public void setAddress(String a) {
		address = a;
	}
	
	public void setPhone(String p) {
		phoneNumber = p;
	}

	ArrayList<Flight> findFlights(Airline a, String date, double time, String from, String to) {
		return a.findFlights(date, time, from, to);
	}
	
	public Ticket bookFlight(Flight f) {
		Ticket t = new Ticket(f.getAirline(), this, f);
		if(f.hasSpace()) {
			f.addTicket(t);
			myTickets.add(t);
		}
		return t;
	}
	
	public boolean holdsFlight(int flightNumber) {
		for(Ticket t : myTickets)
			if(t.getMyFlight().getFlightNumber() == flightNumber)
				return true;
		return false;		
	}
	
	boolean holdsTicket(Ticket t) {
		return myTickets.contains(t);		
	}	
	
	void cancel(Ticket t) {
		if(holdsTicket(t)) {
			t.cancel();
			myTickets.remove(t);
		}
	}
	
	public String toString() {
		return "\nName: " + firstName + " " + lastName +
				"\nAddress: " + address + 
				"\nPhone #: " + phoneNumber;
	}	
}