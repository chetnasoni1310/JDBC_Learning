import java.sql.*;

public class Prepared_Statements {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password="2121912";
        String query="SELECT * FROM employee where name = ? ;";

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
            preparedStatement.setString(1,"Chetna");
            ResultSet rs=preparedStatement.executeQuery();

            while(rs.next())
            {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String title=rs.getString("title");
                Double salary=rs.getDouble("salary");

                System.out.println();
                System.out.println("********************************");

                System.out.println("ID: "+id);
                System.out.println("Name: "+name);
                System.out.println("Title: "+title);
                System.out.println("Salary: "+salary);

            }
            rs.close();
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
