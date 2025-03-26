/*
 * FlightReservation class allows the user to book, cancel and check the status of the registered flights.
 *
 *
 * */
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class FlightReservation implements DisplayClass {
    Flight flight = new Flight();
    int flightIndexInFlightList;
    void bookFlight(String flightNo, int numOfTickets, String userID) {
        Flight selectedFlight = findFlight(flightNo);
        if(selectedFlight == null) {
            System.out.println("Invalid Flight Number...! No flight with the  ID \"" + flightNo + "\" was found...");
            return;
        }
        Customer customer = CustomerManager.getCustomerById(userID);
        if(customer == null) {
            System.out.println("Customer Not Found...");
            return;
        }
        selectedFlight.setNoOfSeatsInTheFlight(selectedFlight.getNoOfSeats()-numOfTickets);

        if(!selectedFlight.isCustomerAlreadyAdded(selectedFlight.getListOfRegisteredCustomersInAFlight() ,customer))
        {
            selectedFlight.addNewCustomerToFlight(customer);
        }
        if(isFlightAlreadyAddedToCustomerList(customer.flightsRegisteredByUser,selectedFlight) && flightIndex(flight.getFlightList(), flight) != -1) {
            addNumberOfTicketsToAlreadyBookedFlight(customer, numOfTickets);
            customer.addExistingFlightToCustomerList(flightIndex(flight.getFlightList(), flight), numOfTickets);
        }
        if(isFlightAlreadyAddedToCustomerList(customer.flightsRegisteredByUser,selectedFlight) && flightIndex(flight.getFlightList(), flight) == -1) {
                addNumberOfTicketsForNewFlight(customer, numOfTickets);
            }
        else {
            customer.addNewFlightToCustomerList(selectedFlight);
            addNumberOfTicketsForNewFlight(customer, numOfTickets);
        }
            System.out.printf("\n %50s You've booked %d tickets for Flight \"%5s\"...", "", numOfTickets, flightNo.toUpperCase());
    }
    void cancelFlight(String userID) {
        String flightNum = "";
        Scanner read = new Scanner(System.in);
        int index = 0, ticketsToBeReturned;
        boolean isFound = false;
        Customer customer = CustomerManager.getCustomerById(userID);
            if (customer != null) {
                if (customer.getFlightsRegisteredByUser().size() != 0) {
                    System.out.printf("%50s %s Here is the list of all the Flights registered by you %s", " ", "++++++++++++++", "++++++++++++++");
                    displayFlightsRegisteredByOneUser(userID);
                    System.out.print("Enter the Flight Number of the Flight you want to cancel : ");
                    flightNum = read.nextLine();
                    System.out.print("Enter the number of tickets to cancel : ");
                    int numOfTickets = read.nextInt();
                    Iterator<Flight> flightIterator = customer.getFlightsRegisteredByUser().iterator();
                    while (flightIterator.hasNext()) {
                        Flight flight = flightIterator.next();
                        if (flightNum.equalsIgnoreCase(flight.getFlightNumber())) {
                            isFound = true;
                            int numOfTicketsForFlight = customer.getNumOfTicketsBookedByUser().get(index);
                            while (numOfTickets > numOfTicketsForFlight) {
                                System.out.print("ERROR!!! Number of tickets cannot be greater than " + numOfTicketsForFlight + " for this flight. Please enter the number of tickets again : ");
                                numOfTickets = read.nextInt();
                            }
                            if (numOfTicketsForFlight == numOfTickets) {
                                ticketsToBeReturned = flight.getNoOfSeats() + numOfTicketsForFlight;
                                customer.numOfTicketsBookedByUser.remove(index);
                                flightIterator.remove();
                            } else {
                                ticketsToBeReturned = numOfTickets + flight.getNoOfSeats();
                                customer.numOfTicketsBookedByUser.set(index, (numOfTicketsForFlight - numOfTickets));
                            }
                            flight.setNoOfSeatsInTheFlight(ticketsToBeReturned);
                            break;
                        }
                        index++;
                    }

                }else{
                    System.out.println("No Flight Has been Registered by you with ID \"\"" + flightNum.toUpperCase() +"\"\".....");
                }
//                index++;
                if (!isFound) {
                    System.out.println("ERROR!!! Couldn't find Flight with ID \"" + flightNum.toUpperCase() + "\".....");
                }
            }

    }
    private Flight findFlight(String flightNo) {
        for (Flight flight : flight.getFlightList()) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNo)) {
                return flight;
            }
        }
        return null;
    }
    void addNumberOfTicketsToAlreadyBookedFlight(Customer customer, int numOfTickets) {
        int newNumOfTickets = customer.numOfTicketsBookedByUser.get(flightIndexInFlightList) + numOfTickets;
        customer.numOfTicketsBookedByUser.set(flightIndexInFlightList, newNumOfTickets);
    }

    void addNumberOfTicketsForNewFlight(Customer customer, int numOfTickets) {
        customer.numOfTicketsBookedByUser.add(numOfTickets);
    }

    boolean isFlightAlreadyAddedToCustomerList(List<Flight> flightList, Flight flight) {
        boolean addedOrNot = false;
        for (Flight flight1 : flightList) {
            if (flight1.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                this.flightIndexInFlightList = flightList.indexOf(flight1);
                addedOrNot = true;
                break;
            }
        }
        return addedOrNot;
    }

    String flightStatus(Flight flight) {
        boolean isFlightAvailable = false;
        for (Flight list : flight.getFlightList()) {
            if (list.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                isFlightAvailable = true;
                break;
            }
        }
        if (isFlightAvailable) {
            return "As Per Schedule";
        } else {
            return "   Cancelled   ";
        }
    }

    public String toString(int serialNum, Flight flights, Customer customer) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  | %-10s |", serialNum, flights.getFlightSchedule(), flights.getFlightNumber(), customer.numOfTicketsBookedByUser.get(serialNum - 1), flights.getFromWhichCity(), flights.getToWhichCity(), flights.fetchArrivalTime(), flights.getFlightTime(), flights.getGate(), flightStatus(flights));
    }

    @Override
    public void displayFlightsRegisteredByOneUser(String userID) {
        MessageDisplays.displayFlightRegisteredHeader();
        for (Customer customer : Customer.customerCollection) {
            List<Flight> f = customer.getFlightsRegisteredByUser();
            int size = customer.getFlightsRegisteredByUser().size();
            if (userID.equals(customer.getUserID())) {
                for (int i = 0; i < size; i++) {
                    System.out.println(toString((i + 1), f.get(i), customer));
                    System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
                }
            }
        }
    }

    /*overloaded toString() method for displaying all users in a flight....*/

    public String toString(int serialNum, Customer customer, int index) {
        return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |       %-7s  |", "", (serialNum + 1), customer.randomIDDisplay(customer.getUserID()), customer.getName(),
                customer.getAge(), customer.getEmail(), customer.getAddress(), customer.getPhone(), customer.numOfTicketsBookedByUser.get(index));
    }

    @Override
    public void displayHeaderForUsers(Flight flight, List<Customer> c) {
        System.out.printf("\n%65s Displaying Registered Customers for Flight No. \"%-6s\" %s \n\n", "+++++++++++++", flight.getFlightNumber(), "+++++++++++++");
        MessageDisplays.displayUserHeader();
        int size = flight.getListOfRegisteredCustomersInAFlight().size();
        for (int i = 0; i < size; i++) {
            System.out.println(toString(i, c.get(i), flightIndex(c.get(i).flightsRegisteredByUser, flight)));
            System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");
        }
    }

    @Override
    public void displayRegisteredUsersForAllFlight() {
        System.out.println();
        for (Flight flight : flight.getFlightList()) {
            List<Customer> c = flight.getListOfRegisteredCustomersInAFlight();
            int size = flight.getListOfRegisteredCustomersInAFlight().size();
            if (size != 0) {
                displayHeaderForUsers(flight, c);
            }
        }
    }

    int flightIndex(List<Flight> flightList, Flight flight) {
        int i = -1;
        for (Flight flight1 : flightList) {
            if (flight1.equals(flight)) {
                i = flightList.indexOf(flight1);
            }
        }
        return i;
    }

    @Override
    public void displayRegisteredUsersForASpecificFlight(String flightNum) {
        System.out.println();
        for (Flight flight : flight.getFlightList()) {
            List<Customer> c = flight.getListOfRegisteredCustomersInAFlight();
            if (flight.getFlightNumber().equalsIgnoreCase(flightNum)) {
                displayHeaderForUsers(flight, c);
            }
        }
    }


}
