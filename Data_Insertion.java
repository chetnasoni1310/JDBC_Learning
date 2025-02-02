import java.sql.*;

public class Data_Insertion {
    public static void main(String[] args) throws ClassNotFoundException{

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password="2121912";
        String query="INSERT into employee (id,name,title,salary) " +
                " Values (7,'Mridu','SWE',3.0);";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        try{
            Connection con=DriverManager.getConnection(url,username,password);
            Statement stmt=con.createStatement();
            int rowsAffected=stmt.executeUpdate(query);

            if(rowsAffected>0)
            {
             System.out.println("Insertion is successfull."+rowsAffected+" Row(s) Affected");
            }
            else{
                System.out.println("Insertion Failed");
            }
            stmt.close();
            con.close();

            System.out.println();
            System.out.println("I am a responsible developer");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
