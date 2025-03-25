import java.util.*;

public class CustomerManager {
    private static final List<Customer> customers = new ArrayList<>();

    public static void addCustomer(String name, String email, String password, String phone, String address, int age) {
        Scanner read = new Scanner(System.in);
        while (!isEmailUnique(email)) {
            System.out.println("ERROR!!! User with the same email already exists... Use new email or login using the previous credentials....");
            System.out.print("Enter your email address :\t");
            email = read.nextLine();
        }
        Customer newCustomer = new Customer(name, email, password, phone, address, age);
        customers.add(newCustomer);
    }

    public static Customer getCustomerById(String userID) {
        for (Customer c : getCustomers()) {
            if (c.getUserID().equals(userID)) {
                return c;
            }
        }
        return null;
    }

    public static void searchCustomer(String id) {
        Customer customer = getCustomerById(id);
        if (customer != null) {
            System.out.printf("%-50sCustomer Found...!!!Here is the Full Record...!!!\n\n\n", " ");
            customer.displayHeader();
            System.out.println(customer.toString(1));
            System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", id);
        }
    }

    public static void editCustomer(String id) {
        Customer customer = getCustomerById(id);
        if (customer != null) {
            Scanner read = new Scanner(System.in);
            System.out.print("\nEnter the new name of the Passenger:\t");
            customer.setName(read.nextLine());
            System.out.print("Enter the new email address of Passenger " + customer.getName() + ":\t");
            customer.setEmail(read.nextLine());
            System.out.print("Enter the new Phone number of Passenger " + customer.getName() + ":\t");
            customer.setPhone(read.nextLine());
            System.out.print("Enter the new address of Passenger " + customer.getName() + ":\t");
            customer.setAddress(read.nextLine());
            System.out.print("Enter the new age of Passenger " + customer.getName() + ":\t");
            customer.setAge(read.nextInt());
            displayAllCustomers(false);
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", id);
        }
    }

    public static void deleteCustomer(String id) {
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (id.equals(customer.getUserID())) {
                iterator.remove();
                System.out.printf("\n%-50sPrinting all Customer's Data after deleting Customer with the ID %s.....!!!!\n", "", id);
                displayAllCustomers(false);
                return;
            }
        }
        System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", id);
    }

    public static void displayAllCustomers(boolean showHeader) {
        if (showHeader) {
            new Customer().displayHeader(); // Use a dummy instance for header
        }
        Iterator<Customer> iterator = customers.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            Customer c = iterator.next();
            System.out.println(c.toString(i));
            System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
        }
    }

    private static boolean isEmailUnique(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
    public static List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }
}
