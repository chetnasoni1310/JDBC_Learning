import java.sql.*;
import java.util.Scanner;

public class Batch_Processing_With_Prepared_statements {
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "2121912";

        String query1="INSERT INTO batch_learning(name,title,salary)  VALUES" +
                "(? ,? ,?);";


        String query2="UPDATE batch_learning SET salary=? WHERE id=? ;";
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


            PreparedStatement preparedStatement=connection.prepareStatement(query1);

            while(true)
            {
                System.out.print("Enter name : ");
                String name= sc.nextLine();
                System.out.print("Enter title : ");
                String title= sc.nextLine();

                System.out.print("Enter salary : ");
                double salary= sc.nextDouble();
                sc.nextLine();


                preparedStatement.setString(1,name);
                preparedStatement.setString(2,title);
                preparedStatement.setDouble(3,salary);

                preparedStatement.addBatch();

                boolean isRerun=true;
                String choice="";
                do {
                    System.out.print("DO you want to continue insertion(Yes/No) : ");
                     choice = sc.nextLine();
                    if (choice.equalsIgnoreCase("YES")) {
                        isRerun=false;
                        continue;
                    } else if (choice.equalsIgnoreCase("NO")) {
                        isRerun=false;
                        break;
                    } else {
                        System.out.println("Invalid choice.Try again ");
                        isRerun=true;
                    }
                }while(isRerun);

                if (choice.equalsIgnoreCase("NO")) {
                    break; // Exit the outer while loop
                }
            }

            int[] batch= preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Successful Insertion");


            System.out.println("Do you want to update salary(YES/NO) : ");
            String choice=sc.nextLine();
            boolean isUpdate=false;
            if(choice.equalsIgnoreCase("YES"))
            {
                isUpdate=true;
            }
            PreparedStatement preparedStatement2= connection.prepareStatement(query2);
            while(isUpdate)
            {

                System.out.print("Enter id : ");
                int id= sc.nextInt();
                sc.nextLine();

                System.out.print("Enter new Salary : ");
                double salary=sc.nextDouble();
                sc.nextLine();

                preparedStatement2.setDouble(1,salary);
                preparedStatement2.setInt(2,id);

                preparedStatement2.addBatch();

                System.out.println("Do you want to update more(Yes/No) : ");
                choice=sc.nextLine();

                if(!choice.equalsIgnoreCase("YES"))
                {
                    isUpdate=false;
                    break;
                }
            }
            int[]batch2= preparedStatement2.executeBatch();
            connection.commit();
            System.out.println("Successful Updation");


            preparedStatement.close();
            sc.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
