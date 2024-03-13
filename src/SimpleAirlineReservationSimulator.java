import java.util.Scanner;
import java.util.ArrayList;

public class SimpleAirlineReservationSimulator {
	public static void main(String args[]) {	
		Scanner input = new Scanner(System.in);
		Airline passengerAirline;
		Passenger passenger;
		String name, firstName, lastName;
		String address;
		String phoneNumber;
		String origin;
		String destination;
		String day; 
		double time = 0.0;	
		boolean valid = false;
		
		passengerAirline = new Airline();		
		generateFlights(passengerAirline);		
		
		Passenger passengers[] = new Passenger[10000];
		for(int i = 0; i < passengers.length; i++) {
			passengers[i] = new Passenger();
			passengers[i].bookFlight(passengerAirline.getRandomFlight());
		}
		
		System.out.println("Welcome to " + Airline.NAME + "!");
		System.out.println();
		
		System.out.println("Enter your full name: ");		
		name = input.nextLine().trim();
		while(!valid) {
			if(!isValidName(name)) {
				System.out.println("Invalid full name. Enter your full name: ");
				name = input.nextLine().trim();
			}
			else
				valid = true;
		}
		firstName = name.split(" ")[0];
		lastName = name.split(" ")[1];
		valid = false;
		
		System.out.println("Enter your address: ");
		address = input.nextLine().trim();		
		while(!valid) {
			if(address == null || address.isEmpty()) {
				System.out.println("Invalid address. Enter your address: ");
				address = input.nextLine().trim();		
			}
			else
				valid = true;
		}
		valid = false;
		
		System.out.println("Enter your phone #(10 digits, XXXXXXXXXX): ");
		phoneNumber = input.nextLine().trim();	
		while(!valid) {
			if(phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.length() == 10) 
				if(isStringNumeric(phoneNumber))
						valid = true;
			if(!valid) {
				System.out.println("Invalid phone #. Enter a 10 digit phone #: ");
				phoneNumber = input.nextLine().trim();
			}
		}
		valid = false;
		
		passenger = new Passenger(firstName, lastName, address, phoneNumber);
		
		System.out.println();
		System.out.println("Hi, " + name + "!");
		System.out.println("Do you want to book or cancel a flight? Yes or no?");
		String yesNo = input.nextLine().trim();		
		while(yesNo.equalsIgnoreCase("yes")) {			
			System.out.println("Enter C to cancel or anything else to book: ");
			if(input.nextLine().trim().equalsIgnoreCase("C")) {
				if(passenger.getMyTickets() == null || passenger.getMyTickets().isEmpty()) {
					System.out.println("You do not have any tickets to cancel.");
					System.out.println("Do you want to book or cancel a flight? Yes or no?");
					yesNo = input.nextLine(); 
				}
				else {
					System.out.println("Here are your tickets: " + passenger.getMyTickets());
					System.out.println("Enter the ticket # to cancel: ");
					int ticketNumber = input.nextInt();				
					if(Ticket.getIndexOfTicketNumber(passenger.getMyTickets(), ticketNumber) < 0) { 
						System.out.println("Sorry that is not a valid ticket Number.");
						input.nextLine();
						System.out.println("Do you want to book or cancel a flight? Yes or no?");
						yesNo = input.nextLine();
					}
					else {
						passenger.cancel(passenger.getMyTickets().get(Ticket.getIndexOfTicketNumber(passenger.getMyTickets(), ticketNumber)));
						System.out.println("Ticket canceled.");
						input.nextLine();
						System.out.println("Do you want to book or cancel a flight? Yes or no?");
						yesNo = input.nextLine();
					}
				}
			}
			else {
				System.out.println(Airline.NAME + " travels between: ");
				for(int i = 0, j = 0; i < Airline.CITIES.length && j < Airline.AIRPORTS.length; i++, j++) {
					System.out.print(Airline.CITIES[i] + "(" + Airline.AIRPORTS[j] + ")");
					if(i < Airline.CITIES.length-1 || j < Airline.AIRPORTS.length-1)
						System.out.print(", ");
					else
						System.out.print(".\n");
				}
				System.out.println("Where are you flying from? ");
				origin = input.nextLine();	
				while(!valid) {	
					for(String ports : Airline.AIRPORTS) {
						if(origin.equalsIgnoreCase(ports))
							valid = true;
					}
					if(!valid) {
						System.out.println("Invalid airport name. Where are you flying from? ");
						origin = input.nextLine();
					}			
				}
				valid = false;
				
				System.out.println(Airline.NAME + " travels between: ");
				for(int i = 0, j = 0; i < Airline.CITIES.length && j < Airline.AIRPORTS.length; i++, j++) {
					System.out.print(Airline.CITIES[i] + "(" + Airline.AIRPORTS[j] + ")");
					if(i < Airline.CITIES.length-1 || j < Airline.AIRPORTS.length-1)
						System.out.print(", ");
					else
						System.out.print(".\n");
				}
				System.out.println("Where are you flying to? ");		
				destination = input.nextLine();	
				while(!valid) {	
					for(String ports : Airline.AIRPORTS) 
						if(destination.equalsIgnoreCase(ports)) 
							if(!origin.equalsIgnoreCase(destination)) 
								valid = true;
					if(origin.equalsIgnoreCase(destination)) {
						System.out.println("You cannot have the same origin and destination. Where are you flying to? ");
						destination = input.nextLine();	
					}
					if(!origin.equalsIgnoreCase(destination) && !valid) {
						System.out.println("That is not a valid airport. Where are you flying to? ");
						destination = input.nextLine();	
					}				
				}
				valid = false;
				
				System.out.println("Which day in August (1-31) do you want to fly? ");
				day = input.nextLine();
				while(!valid) {
					if(isStringNumeric(day)) {
						if(Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31)
							valid = true;					
						else {
							System.out.println("Invalid day. Which day in August(1-31) do you want to fly? ");
							day = input.nextLine();
						}
					}
					else {
						System.out.println("Invalid day. Which day in August(1-31) do you want to fly? ");
						day = input.nextLine();
					}
				}
				passengerAirline.setFlightDates(day);
				valid = false;
				
				System.out.println("Enter your desired departure hour (1-24): "); 
				String stime = input.nextLine();
				while(!valid) {
					if(isStringNumeric(stime)) {
						if(Double.parseDouble(stime) >= 1 && Double.parseDouble(stime) <= 24) {
							time = Double.parseDouble(stime);
							valid = true;
						} 
						else {
							System.out.println("Invalid hour. Enter your desired departure hour(1-24): ");
							stime = input.nextLine();
						}
					}
					else {
						System.out.println("Invalid hour. Enter your desired departure hour(1-24): ");
						stime = input.nextLine();
					}
				}
				valid = false; 
				
				ArrayList<Flight> passengerflights = passengerAirline.findFlights(day, time, origin, destination);
				if(passengerflights.isEmpty()) {
					System.out.println("There are no available flights for those parameters.");
					input.nextLine();
					System.out.println("Do you want to book or cancel a flight? Yes or no?");							
					yesNo = input.nextLine();
				} 
				else {
					System.out.print("Available flights: " + passengerflights);	
					int flightNumber = 0;
					System.out.println("Which flight do you want to book? Enter the flight #:");
					String sfn = input.nextLine();
					if(isStringNumeric(sfn)) {
						flightNumber = Integer.parseInt(sfn);				
						if(Flight.getIndexOfFlightNumber(passengerflights, flightNumber) < 0) {
							System.out.println("That is not a valid flight number.");
							System.out.println("Do you want to book or cancel a flight? Yes or no?");					
							yesNo = input.nextLine();
						}
						else {
							if(passenger.holdsFlight(flightNumber)) {
								System.out.println("You are already booked for that flight!");
								System.out.println("Do you want to book or cancel a flight? Yes or no?");					
								yesNo = input.nextLine();
							}
							else {
								passenger.bookFlight(passengerflights.get(Flight.getIndexOfFlightNumber(passengerflights, flightNumber)));
								System.out.println("Booked!");
								System.out.println("Do you want to book or cancel a flight? Yes or no?");					
								yesNo = input.nextLine();
							}
						}
					}
					else {
						System.out.println("That is not a valid flight number.");
						System.out.println("Do you want to book or cancel a flight? Yes or no?");							
						yesNo = input.nextLine();
					}
				}
			}
		}		
		System.out.println("Thank you for flying with " + Airline.NAME + "!");
		System.out.print("Here is a list of your bookings: " + passenger.getMyTickets());
		input.close();		
	}
	
	private static void generateFlights(Airline a) {
		for(int time = 6; time <= 22; time++) {			
			a.createFlight(time, 0, "LGA", "ALB");
			a.createFlight(time, 0, "LGA", "BUF");
			a.createFlight(time, 0, "LGA", "SYR");
			a.createFlight(time, 0, "ALB", "LGA");
			a.createFlight(time, 0, "ALB", "BUF");
			a.createFlight(time, 0, "ALB", "SYR");
			a.createFlight(time, 0, "BUF", "LGA");
			a.createFlight(time, 0, "BUF", "ALB");
			a.createFlight(time, 0, "BUF", "SYR");
			a.createFlight(time, 0, "SYR", "LGA");
			a.createFlight(time, 0, "SYR", "ALB");
			a.createFlight(time, 0, "SYR", "BUF");	
		}
	}	
	
	private static boolean isStringNumeric(String s) {
		for(char ch : s.toCharArray())
			if(!Character.isDigit(ch))
				return false;
		return true;
	}
	
	private static boolean isValidName(String s) {
		if(s == null || s.isEmpty())
			return false;
		if(s.contains(" ")) {
			if(s.split(" ").length > 2) 
				return false;
			else {
				String b = s.split(" ")[0];
				String c = s.split(" ")[1];
				for(int i = 0; i < b.length(); i++) 
					if(!Character.isLetter(b.charAt(i)))
						return false;
				for(int j = 0; j < c.length(); j++)
					if(!Character.isLetter(c.charAt(j)))
						return false;
			}
		}	
		else
			return false;
		return true;
	}
}
