//import java.util.Iterator;
//import java.util.Scanner;
//public class FlightBookingService {
//    private FlightManager flightManager;
//    private CustomerManager customerManager;
//    public FlightReservation flightReservation = new FlightReservation();
//    public FlightBookingService(FlightManager fm, CustomerManager cm) {
//        this.flightManager = fm;
//        this.customerManager = cm;
//    }
//    void bookFlight(String flightNo, int numOfTickets, String userID) {
//        Flight selectedFlight = findFlight(flightNo);
//        if(selectedFlight == null) {
//            System.out.println("Invalid Flight Number...! No flight with the  ID \"" + flightNo + "\" was found...");
//            return;
//        }
//        Customer customer = CustomerManager.getCustomerById(userID);
//        if(customer == null) {
//            System.out.println("Customer Not Found...");
//            return;
//        }
//        selectedFlight.setNoOfSeatsInTheFlight(selectedFlight.getNoOfSeats()-numOfTickets);
//
//        if(!selectedFlight.isCustomerAlreadyAdded(selectedFlight.getListOfRegisteredCustomersInAFlight() ,customer))
//        {
//            selectedFlight.addNewCustomerToFlight(customer);
//        }
//        if(flightReservation.isFlightAlreadyAddedToCustomerList(customer.flightsRegisteredByUser,selectedFlight) && flightReservation. flightIndex(flight.getFlightList(), flight) != -1) {
//            addNumberOfTicketsToAlreadyBookedFlight(customer, numOfTickets);
//            customer.addExistingFlightToCustomerList(flightIndex(flight.getFlightList(), flight), numOfTickets);
//        }
//        if(isFlightAlreadyAddedToCustomerList(customer.flightsRegisteredByUser,selectedFlight) && flightIndex(flight.getFlightList(), flight) == -1) {
//            addNumberOfTicketsForNewFlight(customer, numOfTickets);
//        }
//        else {
//            customer.addNewFlightToCustomerList(selectedFlight);
//            addNumberOfTicketsForNewFlight(customer, numOfTickets);
//        }
//        System.out.printf("\n %50s You've booked %d tickets for Flight \"%5s\"...", "", numOfTickets, flightNo.toUpperCase());
//
//    }
//    void cancelFlight(String userID) {
//        String flightNum = "";
//        Scanner read = new Scanner(System.in);
//        int index = 0, ticketsToBeReturned;
//        boolean isFound = false;
//        Customer customer = CustomerManager.getCustomerById(userID);
//        if (customer != null) {
//            if (customer.getFlightsRegisteredByUser().size() != 0) {
//                System.out.printf("%50s %s Here is the list of all the Flights registered by you %s", " ", "++++++++++++++", "++++++++++++++");
//                flightReservation.displayFlightsRegisteredByOneUser(userID);
//                System.out.print("Enter the Flight Number of the Flight you want to cancel : ");
//                flightNum = read.nextLine();
//                System.out.print("Enter the number of tickets to cancel : ");
//                int numOfTickets = read.nextInt();
//                Iterator<Flight> flightIterator = customer.getFlightsRegisteredByUser().iterator();
//                while (flightIterator.hasNext()) {
//                    Flight flight = flightIterator.next();
//                    if (flightNum.equalsIgnoreCase(flight.getFlightNumber())) {
//                        isFound = true;
//                        int numOfTicketsForFlight = customer.getNumOfTicketsBookedByUser().get(index);
//                        while (numOfTickets > numOfTicketsForFlight) {
//                            System.out.print("ERROR!!! Number of tickets cannot be greater than " + numOfTicketsForFlight + " for this flight. Please enter the number of tickets again : ");
//                            numOfTickets = read.nextInt();
//                        }
//                        if (numOfTicketsForFlight == numOfTickets) {
//                            ticketsToBeReturned = flight.getNoOfSeats() + numOfTicketsForFlight;
//                            customer.numOfTicketsBookedByUser.remove(index);
//                            flightIterator.remove();
//                        } else {
//                            ticketsToBeReturned = numOfTickets + flight.getNoOfSeats();
//                            customer.numOfTicketsBookedByUser.set(index, (numOfTicketsForFlight - numOfTickets));
//                        }
//                        flight.setNoOfSeatsInTheFlight(ticketsToBeReturned);
//                        break;
//                    }
//                    index++;
//                }
//
//            }else{
//                System.out.println("No Flight Has been Registered by you with ID \"\"" + flightNum.toUpperCase() +"\"\".....");
//            }
////                index++;
//            if (!isFound) {
//                System.out.println("ERROR!!! Couldn't find Flight with ID \"" + flightNum.toUpperCase() + "\".....");
//            }
//        }
//
//    }
//    }
//}