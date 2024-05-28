package test;

import menu.Menu;
import model.User;
import service.LaundryService;
import service.DatabaseService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MenuTest {

    public static void main(String[] args) {
        MenuTest test = new MenuTest();
        test.testDisplayMainMenu();
        test.testRegisterForLaundry();
        test.testGetPhoneNumber_ValidInput();
        test.testGetPhoneNumber_Skip();
        test.testDisplaySubMenuAfterViewingConditions();
        test.testDisplaySubMenuAfterViewingInfo();
    }

    public void testDisplayMainMenu() {
        // Set up user input and output capture
        String input = "6\n"; // Simulate user input for exiting the application
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create Menu instance and call displayMainMenu
        Menu menu = new Menu();
        menu.displayMainMenu();

        // Verify the output
        String expectedOutput = "Main Menu:\n" +
                "1. Accept Conditions and Register for Laundry\n" +
                "2. See Conditions\n" +
                "3. How do we work?\n" +
                "4. Information about us\n" +
                "5. Payment Information\n" +
                "6. Exit\n" +
                "Enter your choice: \n" +
                "Thank you for using our application!\n";
        assert outContent.toString().contains(expectedOutput) : "Test displayMainMenu failed!";
    }

    public void testRegisterForLaundry() {
        // Set up user input and output capture
        String input = "1\n1\n101\nJohn\nDoe\n1234567890\njohn.doe@example.com\n2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Mock LaundryService methods
        LaundryService laundryService = new LaundryService() {
            @Override
            public String displayAvailableLaundryDays() {
                return "Available laundry days: Monday, Wednesday, Friday";
            }

            @Override
            public String getDayOfWeek(int locationChoice) {
                return "Wednesday";
            }

            @Override
            public void saveUser(User user) {
                // Mock save user method
            }
        };

        // Inject mock service into Menu instance
        Menu menu = new Menu();
        menu.laundryService = laundryService;

        // Call registerForLaundry
        menu.registerForLaundry();

        // Verify the output
        assert outContent.toString().contains("Thank you for registering for laundry.") : "Test registerForLaundry failed!";
        assert outContent.toString().contains("Your signup data:") : "Test registerForLaundry failed!";
    }

    public void testGetPhoneNumber_ValidInput() {
        // Set up user input
        String input = "1234567890\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Create Menu instance and call getPhoneNumber
        Menu menu = new Menu();
        String phone = menu.getPhoneNumber();

        // Verify the output
        assert phone.equals("1234567890") : "Test getPhoneNumber_ValidInput failed!";
    }

    public void testGetPhoneNumber_Skip() {
        // Set up user input
        String input = "skip\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Create Menu instance and call getPhoneNumber
        Menu menu = new Menu();
        String phone = menu.getPhoneNumber();

        // Verify the output
        assert phone.equals("N/A") : "Test getPhoneNumber_Skip failed!";
    }

    public void testDisplaySubMenuAfterViewingConditions() {
        // Set up user input and output capture
        String input = "2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create Menu instance and call displaySubMenuAfterViewingConditions
        Menu menu = new Menu();
        menu.displaySubMenuAfterViewingConditions();

        // Verify the output
        String expectedOutput = "Sub-Menu:\n" +
                "1. Accept Conditions and Register for Laundry\n" +
                "2. Return to Main Menu\n" +
                "3. Exit\n" +
                "Enter your choice: \n";
        assert outContent.toString().contains(expectedOutput) : "Test displaySubMenuAfterViewingConditions failed!";
    }

    public void testDisplaySubMenuAfterViewingInfo() {
        // Set up user input and output capture
        String input = "1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create Menu instance and call displaySubMenuAfterViewingInfo
        Menu menu = new Menu();
        menu.displaySubMenuAfterViewingInfo();

        // Verify the output
        String expectedOutput = "Sub-Menu:\n" +
                "1. Return to Main Menu\n" +
                "2. Exit\n" +
                "Enter your choice: \n";
        assert outContent.toString().contains(expectedOutput) : "Test displaySubMenuAfterViewingInfo failed!";
    }
}




