import java.util.*;

public class FlightManager {
    private static final List<Flight> flights = new ArrayList<>();


    public static void deleteFlight(String flightNumber) {
        Iterator<Flight> iterator = flights.iterator();
        while (iterator.hasNext()) {
            Flight flight = iterator.next();
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                iterator.remove();
                displayFlightSchedule();
                return;
            }
        }
        System.out.println("Flight with given Number not found...");
        displayFlightSchedule();
    }
    public static void displayFlightSchedule() {
        Iterator<Flight> flightIterator = getFlights().iterator();
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO | Available Seats  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |   DISTANCE(MILES/KMS)  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        int i = 0;
        while (flightIterator.hasNext()) {
            i++;
            Flight f1 = flightIterator.next();
            System.out.println(f1.toString(i));
            System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        }
    }
    public static List<Flight> getFlights() {
        return Collections.unmodifiableList(flights);
    }
}