package menu;

import model.User;
import service.DatabaseService;
import service.LaundryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    public LaundryService laundryService = new LaundryService();
    private DatabaseService databaseService = new DatabaseService();

    public void displayMainMenu() {
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Accept Conditions and Register for Laundry");
            System.out.println("2. See Conditions");
            System.out.println("3. How do we work?");
            System.out.println("4. Information about us");
            System.out.println("5. Payment Information");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int mainMenuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (mainMenuChoice) {
                case 1:
                    registerForLaundry();
                    break;
                case 2:
                    databaseService.displayTermsFromDatabase("Terms");
                    displaySubMenuAfterViewingConditions();
                    break;
                case 3:
                    databaseService.displayTermsFromDatabase("howweworks");
                    displaySubMenuAfterViewingInfo();
                    break;
                case 4:
                    databaseService.displayTermsFromDatabase("information");
                    displaySubMenuAfterViewingInfo();
                    break;
                case 5:
                    databaseService.displayTermsFromDatabase("paymentinfo");
                    displaySubMenuAfterViewingInfo();
                    break;
                case 6:
                    System.out.println("Thank you for using our application!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displaySubMenuAfterViewingConditions() {
        databaseService.displayTermsFromDatabase("Terms"); // Wyświetl wszystkie warunki z tabeli "Terms"
        System.out.println("Sub-Menu:");
        System.out.println("1. Accept Conditions and Register for Laundry");
        System.out.println("2. Return to Main Menu");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choiceAfterConditions = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        switch (choiceAfterConditions) {
            case 1:
                registerForLaundry();
                break;
            case 2:
                return;
            case 3:
                System.out.println("Thank you for using our application!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void displaySubMenuAfterViewingInfo() {
        System.out.println("Sub-Menu:");
        System.out.println("1. Return to Main Menu");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choiceAfterInfo = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        switch (choiceAfterInfo) {
            case 1:
                return;
            case 2:
                System.out.println("Thank you for using our application!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void registerForLaundry() {
        System.out.println(laundryService.displayAvailableLaundryDays());

        System.out.println("Choose location:");
        System.out.print("Enter location number: ");
        int locationChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        String locationName = "";
        String time = "";
        boolean isOffCampus = false;

        if (locationChoice >= 1 && locationChoice <= 5) {
            Map<Integer, String> locationMap = new HashMap<>();
            locationMap.put(1, "Devereux Hall");
            locationMap.put(2, "Doyle Hall");
            locationMap.put(3, "Shay Hall");
            locationMap.put(4, "Loughlen Hall");
            locationMap.put(5, "Robinson & Falconio Halls");
            locationName = locationMap.get(locationChoice);

            System.out.println("Choose room:");
            System.out.print("Enter room number: ");
            String room = scanner.nextLine().trim();
            locationName += ", Room " + room;
        } else if (locationChoice == 6) {
            System.out.println("Choose sub-location:");
            System.out.println("1. Francis Hall");
            System.out.println("2. Garden Apartments");
            System.out.println("3. Townhouse Apartments");
            System.out.println("4. Off campus");
            System.out.print("Enter sub-location number: ");
            int subLocationChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            Map<Integer, String> subLocationMap = new HashMap<>();
            subLocationMap.put(1, "Francis Hall");
            subLocationMap.put(2, "Garden Apartments");
            subLocationMap.put(3, "Townhouse Apartments");
            subLocationMap.put(4, "Off campus");
            locationName = subLocationMap.get(subLocationChoice);

            if (subLocationChoice == 1) {
                System.out.print("Enter room number: ");
                String room = scanner.nextLine().trim();
                locationName += ", Room " + room;
            } else if (subLocationChoice == 2 || subLocationChoice == 3) {
                System.out.print("Enter apartment number: ");
                String apartment = scanner.nextLine().trim();
                locationName += ", Apartment " + apartment;
            } else if (subLocationChoice == 4) {
                System.out.println("Choose city:");
                System.out.println("1. Allegany");
                System.out.println("2. St Bonaventure");
                System.out.print("Enter city number: ");
                int cityChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                String city = (cityChoice == 1) ? "Allegany" : "St Bonaventure";

                System.out.print("Enter street: ");
                String street = scanner.nextLine().trim();

                System.out.print("Enter postal code: ");
                String postalCode = scanner.nextLine().trim();

                locationName += ", Address: " + city + ", " + street + ", " + postalCode;
                isOffCampus = true;
            }
        }

        if (time.equals("") && !isOffCampus) {
            System.out.println("Choose time (3pm, 5pm, 7pm):");
            System.out.println("1. 3pm");
            System.out.println("2. 5pm");
            System.out.println("3. 7pm");
            System.out.print("Enter time number: ");
            int timeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            Map<Integer, String> timeMap = new HashMap<>();
            timeMap.put(1, "3pm");
            timeMap.put(2, "5pm");
            timeMap.put(3, "7pm");
            time = timeMap.get(timeChoice);
        } else if (isOffCampus) {
            System.out.println("Choose delivery time:");
            System.out.println("1. 12pm-2pm");
            System.out.println("2. 3pm-5pm");
            System.out.print("Enter delivery time number: ");
            int deliveryTimeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            Map<Integer, String> deliveryTimeMap = new HashMap<>();
            deliveryTimeMap.put(1, "12pm-2pm");
            deliveryTimeMap.put(2, "3pm-5pm");
            time = deliveryTimeMap.get(deliveryTimeChoice);
        }

        System.out.println("Enter your details:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine().trim();
        String phone = getPhoneNumber();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        User user = new User(firstName, lastName, phone, email);
        laundryService.saveUser(user);

        if (isOffCampus) {
            System.out.println("Thank you for registering for laundry. Your laundry will be picked up from " + locationName + " at " + time + ". We will contact you soon to schedule the exact pickup time.");
        } else {
            System.out.println("Thank you for registering for laundry. Your laundry will be picked up from " + locationName + " at " + time + " on " + laundryService.getDayOfWeek(locationChoice) + ".");
        }

        System.out.println("Your signup data:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Location Choice: " + locationName);
        System.out.println("Time Choice: " + time);

        System.out.println("Would you like to make a payment now?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter your choice: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        if (paymentChoice == 1) {
            String venmoLink = "https://venmo.com/synek";
            System.out.println("Please make a payment using the following link: " + venmoLink);
        }

        System.out.println("Thank you for using our application!");
        System.exit(0);
    }

    public String getPhoneNumber() {
        while (true) {
            System.out.print("Phone (only American phone number, 10 digits) or type 'skip' if you don't have American phone number: ");
            String phone = scanner.nextLine().trim();

            if (phone.equalsIgnoreCase("skip")) {
                return "N/A";
            } else if (phone.matches("\\d{10}")) {
                return phone;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit American phone number or type 'skip'.");
            }
        }
    }
}




