import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Data_Updation {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password="2121912";
        String query="UPDATE employee SET title='Product Manager', " +
                "                         name='Pinky',id=4 where id=5;";

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
                System.out.println("Updation is successfull."+rowsAffected+" Row(s) Affected");
            }
            else{
                System.out.println("Updation Failed");
            }
            stmt.close();
            con.close();
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
