
package service;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class LaundryService {
    public void saveUser(User user) {
        // Save user to the database or perform other operations
    }

    public String displayAvailableLaundryDays() {
        return "Available locations in each day:\n" +
                "1. Devereux Hall (Mondays)\n" +
                "2. Doyle Hall (Tuesdays)\n" +
                "3. Shay Hall (Wednesdays)\n" +
                "4. Loughlen Hall (Thursdays)\n" +
                "5. Robinson & Falconio Halls (Fridays)\n" +
                "6. Francis Hall, Garden Apartments, Townhouse Apartments, Off campus (Weekends)";
    }

    public String getDayOfWeek(int locationChoice) {
        switch (locationChoice) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            default: return "Unknown";
        }
    }
}

