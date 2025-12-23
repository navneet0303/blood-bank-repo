import java.util.*;
import java.util.regex.Pattern;

/*
 * Blood Donor Management System
 * Review - 2 Final Submission
 * Author: Navneet Yadav
 * Description:
 * Java-based system for managing blood donor records with validation,
 * error handling, search, availability, and logging features.
 */

// ===================== DONOR ENTITY =====================
class Donor {
    int id;
    String name;
    String bloodGroup;
    String phone;
    String city;
    boolean available;
    String lastDonationDate;

    Donor(int id, String name, String bloodGroup, String phone,
          String city, boolean available, String lastDonationDate) {
        this.id = id;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.city = city;
        this.available = available;
        this.lastDonationDate = lastDonationDate;
    }
}

// ===================== MAIN CLASS =====================
public class main {

    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Donor> donorDB = new HashMap<>();

    static final List<String> BLOOD_GROUPS = Arrays.asList(
            "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    );

    static final String ADMIN_USER = "admin";
    static final String ADMIN_PASS = "1234";

    // ===================== MAIN =====================
    public static void main(String[] args) {

        if (!login()) {
            System.out.println("Authentication Failed. Exiting...");
            return;
        }

        while (true) {
            try {
                showMenu();
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> registerDonor();
                    case 2 -> viewAllDonors();
                    case 3 -> searchDonor();
                    case 4 -> updateDonor();
                    case 5 -> deleteDonor();
                    case 6 -> {
                        System.out.println("System Closed Successfully.");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid Input!");
            }
        }
    }

    // ===================== LOGIN =====================
    static boolean login() {
        System.out.println("===== Admin Login =====");
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        return user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS);
    }

    // ===================== MENU =====================
    static void showMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Register Donor");
        System.out.println("2. View All Donors");
        System.out.println("3. Search Donor");
        System.out.println("4. Update Donor");
        System.out.println("5. Delete Donor");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }

    // ===================== REGISTER =====================
    static void registerDonor() {
        try {
            System.out.print("Donor ID: ");
            int id = Integer.parseInt(sc.nextLine());
            if (donorDB.containsKey(id)) {
                System.out.println("Donor already exists!");
                return;
            }

            System.out.print("Name: ");
            String name = sc.nextLine();
            if (name.length() < 3) {
                System.out.println("Invalid Name!");
                return;
            }

            System.out.print("Blood Group: ");
            String bg = sc.nextLine().toUpperCase();
            if (!BLOOD_GROUPS.contains(bg)) {
                System.out.println("Invalid Blood Group!");
                return;
            }

            System.out.print("Phone (10 digits): ");
            String phone = sc.nextLine();
            if (!Pattern.matches("\\d{10}", phone)) {
                System.out.println("Invalid Phone!");
                return;
            }

            System.out.print("City: ");
            String city = sc.nextLine();

            System.out.print("Available (true/false): ");
            boolean available = Boolean.parseBoolean(sc.nextLine());

            System.out.print("Last Donation Date (DD-MM-YYYY): ");
            String date = sc.nextLine();

            donorDB.put(id, new Donor(id, name, bg, phone, city, available, date));
            log("Donor Registered");

        } catch (Exception e) {
            System.out.println("Registration Failed!");
        }
    }

    // ===================== VIEW =====================
    static void viewAllDonors() {
        if (donorDB.isEmpty()) {
            System.out.println("No Donors Found.");
            return;
        }

        for (Donor d : donorDB.values()) {
            System.out.println("--------------------------------");
            System.out.println("ID        : " + d.id);
            System.out.println("Name      : " + d.name);
            System.out.println("Blood     : " + d.bloodGroup);
            System.out.println("Phone     : " + d.phone);
            System.out.println("City      : " + d.city);
            System.out.println("Available : " + d.available);
            System.out.println("Last Donate: " + d.lastDonationDate);
        }
    }

    // ===================== SEARCH =====================
    static void searchDonor() {
        System.out.print("Enter Blood Group: ");
        String bg = sc.nextLine().toUpperCase();

        System.out.print("Enter City: ");
        String city = sc.nextLine();

        boolean found = false;
        for (Donor d : donorDB.values()) {
            if (d.bloodGroup.equals(bg) && d.city.equalsIgnoreCase(city) && d.available) {
                System.out.println("ID: " + d.id + " | Name: " + d.name + " | Phone: " + d.phone);
                found = true;
            }
        }

        if (!found)
            System.out.println("No Available Donor Found.");
    }

    // ===================== UPDATE =====================
    static void updateDonor() {
        try {
            System.out.print("Enter Donor ID: ");
            int id = Integer.parseInt(sc.nextLine());
            Donor d = donorDB.get(id);

            if (d == null) {
                System.out.println("Donor Not Found!");
                return;
            }

            System.out.print("New Phone: ");
            d.phone = sc.nextLine();

            System.out.print("Availability (true/false): ");
            d.available = Boolean.parseBoolean(sc.nextLine());

            log("Donor Updated");

        } catch (Exception e) {
            System.out.println("Update Error!");
        }
    }

    // ===================== DELETE =====================
    static void deleteDonor() {
        System.out.print("Enter Donor ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (donorDB.remove(id) != null)
            log("Donor Deleted");
        else
            System.out.println("Donor Not Found!");
    }

    // ===================== LOGGING =====================
    static void log(String msg) {
        System.out.println("[LOG] " + msg + " | " + new Date());
    }
}
