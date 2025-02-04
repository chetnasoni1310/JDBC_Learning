import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Batch_Processing_Without_Prepared_Statements {
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "2121912";

        String query1="INSERT INTO employee VALUES" +
                "(8,'Rooller','Artist',5600)," +
                "(9,'coaster','Youtuber',7800);";

        String query2="UPDATE employee SET salary=8000 WHERE id=7 ;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully ");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

            Statement statement=connection.createStatement();
            try {
                statement.addBatch(query1);
                statement.addBatch(query2);
                int[] batchResult = statement.executeBatch();
                connection.commit();
                System.out.println("Success");
            } catch (Exception e) {
                connection.rollback();
                System.out.println("Failure");
                System.out.println(e.getMessage());
            }
            sc.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}