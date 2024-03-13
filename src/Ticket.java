import java.util.ArrayList;

public class Ticket {
	private final int ticketNumber; 
	private final Airline myAirline;
	private final Passenger myPassenger;
	private final Flight myFlight;
	private final double price;
	private static int counter = 0;	
	
	public Ticket() {
		 ticketNumber = 100 + counter;
		 counter++;
		 myAirline = new Airline();
		 myPassenger = new Passenger();
		 myFlight = new Flight();
		 price = myFlight.getCost();
	}
	
	public Ticket(Airline a, Passenger p, Flight f) {
		ticketNumber = 100 + counter;
		counter++;
		myAirline = a;
		myPassenger = p;
		myFlight = f;
		price = myFlight.getCost();
	}	

	public void cancel() {
		counter--;
		myFlight.removeTicket(this);
		myAirline.cancel(this);
	}
	
	public int getTicketNumber() {
		return ticketNumber;
	}

	public Airline getMyAirline() {
		return myAirline;
	}

	public Passenger getMyPassenger() {
		return myPassenger;
	}

	public Flight getMyFlight() {
		return myFlight;
	}

	public double getPrice() {
		return price;
	}
	
	public static int getIndexOfTicketNumber(ArrayList<Ticket> t, int ticketNumber) {
		for(int i = 0; i < t.size(); i++) 
			if(t.get(i).getTicketNumber() == ticketNumber)
				return i;
		return -1;
	}
	
	public String toString() {
        return "\nTicket #: " + ticketNumber +
				"\n" + myAirline +				 
				myFlight + 
				"Price: $" + price + "\n";
	}
}