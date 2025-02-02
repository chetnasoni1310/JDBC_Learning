import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainTester {

        public static void main(String[] args) {
            // URL of the database
            String url = "jdbc:mysql://localhost:3306/students";

            // Database credentials
            String user = "root";
            String password = "2121912";

            // Establishing the connection
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                if (connection != null) {
                    System.out.println("Successfully connected to the database.");
                    System.out.println(connection);
                } else {
                    System.out.println("Failed to connect to the database.");
                }
            } catch (SQLException e) {
                System.out.println("An error occurred while connecting to the database: " + e.getMessage());
            }
        }
    }


