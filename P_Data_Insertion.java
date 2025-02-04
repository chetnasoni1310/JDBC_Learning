import java.sql.*;

public class P_Data_Insertion {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password="2121912";
        String query="INSERT into employee Values(?,?,?,?);";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully !! ");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");
            PreparedStatement preparedStatement=connection.prepareStatement(query);

            preparedStatement.setInt(1,5);
            preparedStatement.setString(2,"Poonam");
            preparedStatement.setString(3,"Artist");
            preparedStatement.setDouble(4,8.0);

            int rowsAffected=preparedStatement.executeUpdate();

            if(rowsAffected>0)
            {
                System.out.println("Successfull Insertion");
            }else{
                System.out.println("Failed Insertion");
            }

            preparedStatement.close();
            connection.close();
            System.out.println();
            System.out.println("I am a responsible developer");

        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
