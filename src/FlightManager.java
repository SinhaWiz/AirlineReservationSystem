import java.util.*;

public class FlightManager {
    private static final List<Flight> flights = new ArrayList<>();

    public static void generateFlightSchedule(int numOfFlights) {
        RandomGenerator r1 = new RandomGenerator();
        for (int i = 0; i < numOfFlights; i++) {
            String[][] chosenDestinations = r1.randomDestinations();
            String[] distanceBetweenTheCities = new Flight().calculateDistance(
                    Double.parseDouble(chosenDestinations[0][1]), Double.parseDouble(chosenDestinations[0][2]),
                    Double.parseDouble(chosenDestinations[1][1]), Double.parseDouble(chosenDestinations[1][2]));
            String flightSchedule = new Flight().createNewFlightsAndTime();
            String flightNumber = r1.randomFlightNumbGen(2, 1).toUpperCase();
            int numOfSeatsInTheFlight = r1.randomNumOfSeats();
            String gate = r1.randomFlightNumbGen(1, 30).toUpperCase();
            flights.add(new Flight(flightSchedule, flightNumber, numOfSeatsInTheFlight, chosenDestinations, distanceBetweenTheCities, gate));
        }
    }
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
        int i = 0;
        MessageDisplays.flightScheduleHead();
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