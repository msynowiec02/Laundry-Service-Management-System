package service;

import java.sql.*;

public class DatabaseService {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/laundry";
    private static final String USER = "root";
    private static final String PASSWORD = "Szymek2911!23";

    public void displayTermsFromDatabase(String tableName) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            boolean found = false;

            while (resultSet.next()) {
                if (tableName.equals("howweworks")) {
                    System.out.println(resultSet.getString("description"));
                } else if (tableName.equals("information")) {
                    System.out.println(resultSet.getString("content"));
                } else if (tableName.equals("paymentinfo")) {
                    System.out.println(resultSet.getString("description"));
                } else if (tableName.equals("Terms")) {
                    System.out.println(resultSet.getString("content"));
                }
                found = true;
            }

            if (!found) {
                System.out.println("No information found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayHowWeWorkInfo() {
        displayTermsFromDatabase("howweworks");
    }

    public void displayInformationAboutUs() {
        displayTermsFromDatabase("information");
    }

    public void displayPaymentInfo() {
        displayTermsFromDatabase("paymantinfo");
    }
}
