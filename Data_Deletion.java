import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Data_Deletion {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password="2121912";
        String query="DELETE FROM employee where id=7;";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        try{
            Connection con= DriverManager.getConnection(url,username,password);
            Statement stmt=con.createStatement();
            int rowsAffected=stmt.executeUpdate(query);

            if(rowsAffected>0)
            {
                System.out.println("Deletion is successfull."+rowsAffected+" Row(s) Affected");
            }
            else{
                System.out.println("Deletion Failed");
            }
            stmt.close();
            con.close();
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
