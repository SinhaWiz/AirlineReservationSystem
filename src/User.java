import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class User {
    static String[][] adminUserNameAndPassword = new String[10][2];
    private static List<Customer> customersCollection = new ArrayList<>();
    public static void main(String[] args) {
        int countNumOfUsers = 1;
        RolesAndPermissions r1 = new RolesAndPermissions();
        Flight f1 = new Flight();
        FlightReservation bookingAndReserving = new FlightReservation();
        Customer c1 = new Customer();
        f1.flightScheduler();
        Scanner read = new Scanner(System.in);
        Scanner read1 = new Scanner(System.in);
        MessageDisplays.displayWelcomeMessage();
        displayMainMenu();
        int desiredOption ;
        do {
            desiredOption = read.nextInt();
            while (desiredOption < 0 || desiredOption > 8) {
                System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
                desiredOption = read.nextInt();
            }
            switch (desiredOption) {
                case 1: loginAsAdmin(r1, c1, f1, bookingAndReserving); break;
                case 2: countNumOfUsers = registerAsAdmin(r1, countNumOfUsers); break;
                case 3: loginAsPassenger(r1, c1, f1, bookingAndReserving); break;
                case 4: registerAsPassenger(c1); break;
                case 5: manualInstructions(); break;
            }
            displayMainMenu();
            desiredOption = read1.nextInt();
            while (desiredOption < 0 || desiredOption > 8) {
                System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
                desiredOption = read1.nextInt();
            }
        } while (desiredOption != 0);

    }

    static void displayMainMenu() {
       MessageDisplays.displayMainMenu();
    }
    static void manualInstructions() {
        Scanner read = new Scanner(System.in);
        MessageDisplays.displayManualInstructions();
        int choice = read.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.print("ERROR!!! Invalid entry...Please enter a value either 1 or 2....Enter again....");
            choice = read.nextInt();
        }
        if (choice == 1) {
            MessageDisplays.displayAdminInstructions();
        } else {
            MessageDisplays.displayLocalUserInstructions();
        }
    }
    private static void loginAsAdmin(RolesAndPermissions r1, Customer c1, Flight f1, FlightReservation bookingAndReserving) {
        Scanner read = new Scanner(System.in);
        Scanner read1 = new Scanner(System.in);
        adminUserNameAndPassword[0][0] = "root";
        adminUserNameAndPassword[0][1] = "root";
        System.out.print("\nEnter the UserName to login to the Management System :     ");
        String username = read1.nextLine();
        System.out.print("Enter the Password to login to the Management System :    ");
        String password = read1.nextLine();
        System.out.println();
        int adminIndex = r1.isPrivilegedUserOrNot(username, password);
        if (adminIndex == -1) {
            System.out.printf(
                    "\n%20sERROR!!! Unable to login Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 4....\n",
                    "");
        } else if (adminIndex == 0) {
            System.out.println(
                    "You've standard/default privileges to access the data... You can just view customers data..."
                            + "Can't perform any actions on them....");
            c1.displayCustomersData(true);
        } else {
            System.out.printf(
                    "%-20sLogged in Successfully as \"%s\"..... For further Proceedings, enter a value from below....",
                    "", username);
        }
        int desiredOption;
        do {
            System.out.printf("\n\n%-60s+++++++++ 2nd Layer Menu +++++++++%50sLogged in as \"%s\"\n", "",
                    "", username);
            MessageDisplays.display2ndLayerMenu();
            desiredOption = read.nextInt();
            if (desiredOption == 1) {
                c1.addNewCustomer();
            } else if (desiredOption == 2) {
                c1.displayCustomersData(false);
                System.out.print("Enter the CustomerID to Search :\t");
                String customerID = read1.nextLine();
                System.out.println();
                c1.searchUser(customerID);
            } else if (desiredOption == 3) {
                c1.displayCustomersData(false);
                System.out.print("Enter the CustomerID to Update its Data :\t");
                String customerID = read1.nextLine();
                if (customersCollection.size() > 0) {
                    c1.editUserInfo(customerID);
                } else {
                    System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                }
            } else if (desiredOption == 4) {
                c1.displayCustomersData(false);
                System.out.print("Enter the CustomerID to Delete its Data :\t");
                String customerID = read1.nextLine();
                if (customersCollection.size() > 0) {
                    c1.deleteUser(customerID);
                } else {
                    System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                }
            } else if (desiredOption == 5) {
                c1.displayCustomersData(false);
            } else if (desiredOption == 6) {
                c1.displayCustomersData(false);
                System.out.print(
                        "\n\nEnter the ID of the user to display all flights registered by that user...");
                String id = read1.nextLine();
                bookingAndReserving.displayFlightsRegisteredByOneUser(id);
            } else if (desiredOption == 7) {
                System.out.print(
                        "Do you want to display Passengers of all flights or a specific flight.... 'Y/y' for displaying all flights and 'N/n' to look for a"
                                +
                                " specific flight.... ");
                char choice = read1.nextLine().charAt(0);
                if ('y' == choice || 'Y' == choice) {
                    bookingAndReserving.displayRegisteredUsersForAllFlight();
                } else if ('n' == choice || 'N' == choice) {
                    f1.displayFlightSchedule();
                    System.out.print(
                            "Enter the Flight Number to display the list of passengers registered in that flight... ");
                    String flightNum = read1.nextLine();
                    bookingAndReserving.displayRegisteredUsersForASpecificFlight(flightNum);
                } else {
                    System.out.println("Invalid Choice...No Response...!");
                }
            } else if (desiredOption == 8) {
                f1.displayFlightSchedule();
                System.out.print("Enter the Flight Number to delete the flight : ");
                String flightNum = read1.nextLine();
                f1.deleteFlight(flightNum);
            } else if (desiredOption == 0) {
                System.out.println("Thanks for Using BAV Airlines Ticketing System...!!!");
            } else {
                System.out.println(
                        "Invalid Choice...Looks like you're Robot...Entering values randomly...You've Have to login again...");
                desiredOption = 0;
            }
        } while (desiredOption != 0);
    }
    private static  void loginAsPassenger(RolesAndPermissions r1 , Customer c1 ,Flight f1 , FlightReservation bookingAndReserving ){
        Scanner read1 = new Scanner(System.in);
        Scanner read = new Scanner(System.in);
        System.out.print("\n\nEnter the Email to Login : \t");
        String userName = read1.nextLine();
        System.out.print("Enter the Password : \t");
        String password = read1.nextLine();
        String[] result = r1.isPassengerRegistered(userName, password).split("-");
        if (Integer.parseInt(result[0]) == 1) {
            int desiredChoice;
            System.out.printf(
                    "\n\n%-20sLogged in Successfully as \"%s\"..... For further Proceedings, enter a value from below....",
                    "", userName);
            do {
                System.out.printf("\n\n%-60s+++++++++ 3rd Layer Menu +++++++++%50sLogged in as \"%s\"\n", "",
                        "", userName);
                MessageDisplays.display3rdLayerMenu();
                System.out.printf("%-40s (f) Enter 6 to Display all flights registered by \"%s\"....\n", "",
                        userName);
                System.out.printf("%-40s (g) Enter 0 to Go back to the Main Menu/Logout....\n", "");
                System.out.print("Enter the desired Choice :   ");
                desiredChoice = read.nextInt();
                if (desiredChoice == 1) {
                    f1.displayFlightSchedule();
                    System.out.print("\nEnter the desired flight number to book :\t ");
                    String flightToBeBooked = read1.nextLine();
                    System.out.print("Enter the Number of tickets for " + flightToBeBooked + " flight :   ");
                    int numOfTickets = read.nextInt();
                    while (numOfTickets > 10) {
                        System.out.print(
                                "ERROR!! You can't book more than 10 tickets at a time for single flight....Enter number of tickets again : ");
                        numOfTickets = read.nextInt();
                    }
                    bookingAndReserving.bookFlight(flightToBeBooked, numOfTickets, result[1]);
                } else if (desiredChoice == 2) {

                    c1.editUserInfo(result[1]);
                } else if (desiredChoice == 3) {
                    System.out.print(
                            "Are you sure to delete your account...It's an irreversible action...Enter Y/y to confirm...");
                    char confirmationChar = read1.nextLine().charAt(0);
                    if (confirmationChar == 'Y' || confirmationChar == 'y') {
                        c1.deleteUser(result[1]);
                        System.out.printf("User %s's account deleted Successfully...!!!", userName);
                        desiredChoice = 0;
                    } else {
                        System.out.println("Action has been cancelled...");
                    }
                } else if (desiredChoice == 4) {
                    f1.displayFlightSchedule();
                    f1.displayMeasurementInstructions();
                } else if (desiredChoice == 5) {
                    bookingAndReserving.cancelFlight(result[1]);
                } else if (desiredChoice == 6) {
                    bookingAndReserving.displayFlightsRegisteredByOneUser(result[1]);
                } else {
                    if (desiredChoice != 0) {
                        System.out.println(
                                "Invalid Choice...Looks like you're Robot...Entering values randomly...You've Have to login again...");
                    }
                    desiredChoice = 0;
                }
            } while (desiredChoice != 0);
        } else {
            System.out.printf(
                    "\n%20sERROR!!! Unable to login Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 4....\n",
                    "");
        }
    }
    private static  void registerAsPassenger(Customer c1){
        Scanner read = new Scanner(System.in);
        System.out.print("\nEnter your name :\t");
        String name = read.nextLine();
        System.out.print("Enter your email address :\t");
        String email = read.nextLine();
        System.out.print("Enter your Password :\t");
        String password = read.nextLine();
        System.out.print("Enter your Phone number :\t");
        String phone = read.nextLine();
        System.out.print("Enter your address :\t");
        String address = read.nextLine();
        System.out.print("Enter your age :\t");
        int age = read.nextInt();
        CustomerManager.addCustomer(new CustomerData(name, email, password, phone, address, age));
    }
    private static  int registerAsAdmin( RolesAndPermissions r1,int countNumOfUsers){
        Scanner read = new Scanner(System.in);
        System.out.print("\nEnter the UserName to Register :    ");
        String username = read.nextLine();
        System.out.print("Enter the Password to Register :     ");
        String password = read.nextLine();
        while (r1.isPrivilegedUserOrNot(username, password) != -1) {
            System.out.print("ERROR!!! Admin with same UserName already exist. Enter new UserName:   ");
            username = read.nextLine();
            System.out.print("Enter the Password Again:   ");
            password = read.nextLine();
        }
        adminUserNameAndPassword[countNumOfUsers][0] = username;
        adminUserNameAndPassword[countNumOfUsers][1] = password;
        return countNumOfUsers + 1;
    }
    public static List<Customer> getCustomersCollection() {
        return customersCollection;
    }
}