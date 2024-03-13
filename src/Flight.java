import java.util.ArrayList;
import java.util.Random;

public class Flight {
	private final Airline airline;
	private final int flightNumber;
	private final int seats;
	private int filledSeats;
	private final String origin;
	private final String destination;
	private String date;
	private double departureTime;
	private double arrivalTime;
	private double flightLength;	
	private final ArrayList<Ticket> tickets = new ArrayList<>();
	private static int counter = 0;	
	
	public Flight() {
		Random r = new Random();
		airline = new Airline();
		flightNumber = 1000+counter;
		seats = 150;
		filledSeats = 0;
		int o = r.nextInt(Airline.AIRPORTS.length), d = r.nextInt(Airline.AIRPORTS.length);
		while(o == d) {
			d = r.nextInt(Airline.AIRPORTS.length);
		}
		origin = Airline.AIRPORTS[o];
		destination = Airline.AIRPORTS[d];
		generateFlightLength();
		arrivalTime = departureTime+flightLength;
		if(arrivalTime > 24)
			arrivalTime = (departureTime+flightLength)%24;
		counter++;
	}
	
	public Flight(Airline a, int numberOfSeats, double time, String from, String to) {
		seats = 150;
		flightNumber = 1000+counter;		
		airline = a;
		filledSeats += numberOfSeats;
		departureTime = time;
		origin = from;
		destination = to;
		generateFlightLength();
		arrivalTime = departureTime+flightLength;
		if(arrivalTime > 24)
			arrivalTime = (departureTime+flightLength)%24;
		counter++;
	}
	
	private void generateFlightLength() {
		if((origin.equals("LGA") && destination.equals("ALB")) || (origin.equals("ALB") && destination.equals("LGA"))) 
			flightLength = 3.00;
		if((origin.equals("LGA") && destination.equals("BUF")) || (origin.equals("BUF") && destination.equals("LGA"))) 
			flightLength = 3.00;
		if((origin.equals("LGA") && destination.equals("SYR")) || (origin.equals("SYR") && destination.equals("LGA")))
			flightLength = 2.00;
		if((origin.equals("ALB") && destination.equals("BUF")) || (origin.equals("BUF") && destination.equals("ALB")))
			flightLength = 3.00;
		if((origin.equals("ALB") && destination.equals("SYR")) || (origin.equals("SYR") && destination.equals("ALB")))
			flightLength = 3.00;
		if((origin.equals("BUF") && destination.equals("SYR")) || (origin.equals("SYR") && destination.equals("BUF")))
			flightLength = 3.00;	
	}
	
	boolean hasSpace() {
		return seats - filledSeats > 0;
	}	
	
	void addTicket(Ticket ticket) {
		tickets.add(ticket);
		filledSeats++;
	}
	
	void removeTicket(Ticket ticket) {
		tickets.remove(ticket);
		filledSeats--;
	}
	
	boolean holdsTicket(Ticket ticket) {
		return tickets.contains(ticket);
	}
			
	double getCost() {
		return airline.cost(this);
	}
	
	boolean matches(String d, double t, String from, String to)  {
		return d.equalsIgnoreCase(date) && from.equalsIgnoreCase(origin) && to.equalsIgnoreCase(destination) &&	(t <= departureTime+4 && t >= departureTime-4);
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public int getFilledSeats() {
		return filledSeats;
	}

	public double getFlightLength() {
		return flightLength;
	}

	public Airline getAirline() {
		return airline;
	}

	public String getDate() {
		return date;
	}

	public String getOriginAirport() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}
	
	public double getDepartureTime() {
		return departureTime;
	}

	public void setDate(String d) {
		date = d;		
	}	
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	
	public static int getIndexOfFlightNumber(ArrayList<Flight> f, int flightNumber) {
		for(int i = 0; i < f.size(); i++) 
			if(f.get(i).getFlightNumber() == flightNumber)
				return i;
		return -1;
	}

	public String toString() {
		return "\nDate:" +
				"\nFlight #: " + flightNumber + 
				"\nDeparting airport: " + origin +
				"\nArriving airport: " + destination + 
				"\nDeparture time:" + departureTime +
				"\nArrival time:" + arrivalTime + 
				"\nTotal flight length: " + flightLength + " hours.\n";
	}
}