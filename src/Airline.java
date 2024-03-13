import java.util.ArrayList;
import java.util.Random;

public class Airline {
	public static final  String NAME = "Airline Reservation Simulator";
	public static final String[] CITIES = {"New York City", "Albany", "Buffalo", "Syracuse"};
	public static final String[] AIRPORTS = {"LGA", "ALB", "BUF", "SYR"};	
	private final ArrayList<Flight> flights;
	
	public Airline() {
		flights = new ArrayList<>();
	}
	
	void createFlight(double time, int numberOfSeats, String from, String to) {
		flights.add(new Flight(this, numberOfSeats, time, from, to));
	}
	
	ArrayList<Flight> findFlights(String date, double time, String origin, String to) {
		ArrayList<Flight> myFlights = new ArrayList<Flight>();
		for(Flight f: flights)
			if(f.matches(date, time, origin, to))
				myFlights.add(f);
		return myFlights;
	}

	public Flight getRandomFlight() {
		return flights.get((new Random().nextInt(flights.size())));
	}
	
	public ArrayList<Flight> getFlights() {
		return flights;
	}
	
	public void setFlightDates(String day) {
        for (Flight flight : flights) 
			flight.setDate(day);
	}
	
	Ticket book(Passenger p, Flight f) {
		return p.bookFlight(f);	
	}
	
	public void cancel(Ticket t) {
		issueRefund(t);
	}
	
	void issueRefund(Ticket t) {
		System.out.println("Passenger " + t.getMyPassenger().getName() + " has been refunded $" + t.getPrice() + ".");
	}

	public double cost(Flight f) {	
		double cost = 300.00;
		if(f.hasSpace()) {
			for(int i = 0; i < 150; i+=25)
				if(f.getFilledSeats() > i)
					cost+=50.0;
		}
		return cost;
	}
	
	public String toString() {
		return NAME;
	}
}