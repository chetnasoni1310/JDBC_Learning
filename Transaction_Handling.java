import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Transaction_Handling {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password="2121912";

        String withdrawlquery="UPDATE accounts SET balance=balance -(?) where account_number=(?) ;";
        String depositquery="UPDATE accounts SET balance=balance +(?) where account_number=(?) ;";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully ");
        }catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        Scanner sc=new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);


            System.out.print("Enter the withdrawl account number : ");
            String Withdrawl_account_number = sc.nextLine();
            System.out.print("Enter the amount to be transfer : ");
            Double Transfer_amount = sc.nextDouble();

            sc.nextLine();

            System.out.print("Enter the depositing account number : ");
            String Deposit_account_number = sc.nextLine();

            PreparedStatement withdrawlStatement = connection.prepareStatement(withdrawlquery);
            PreparedStatement depositStatement = connection.prepareStatement(depositquery);
        try {
            withdrawlStatement.setDouble(1, Transfer_amount);
            withdrawlStatement.setString(2, Withdrawl_account_number);

            depositStatement.setDouble(1, Transfer_amount);
            depositStatement.setString(2, Deposit_account_number);

            int withdrawlAffectedRows = withdrawlStatement.executeUpdate();
            int depositAffectedRows = depositStatement.executeUpdate();

            if (withdrawlAffectedRows > 0 && depositAffectedRows > 0) {
                connection.commit();
                System.out.println("Transaction Successfull");
            } else {
                connection.rollback();
                System.out.println("Transaction Failed");
            }

        }catch(SQLException e)
        {
            connection.rollback();
            System.out.println(e.getMessage());
        }
        } catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
