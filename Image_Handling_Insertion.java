import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Image_Handling_Insertion {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password="2121912";
        String image_path="D:\\ARMY\\Documentations\\profilepic.jpg";
        String query="INSERT INTO image_table(image_data) VALUES (?) ;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully !! ");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");


            FileInputStream fileInputStream=new FileInputStream(image_path);
            byte[] imageData=new byte[fileInputStream.available()];
            fileInputStream.read(imageData);


            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);

            int affectedRows=preparedStatement.executeUpdate();

            if(affectedRows>0)
            {
                System.out.println("Image inserted succesfully !");
            }
            else{
                System.out.println("Image insertion failed !!");
            }

            preparedStatement.close();
            connection.close();
            System.out.println();
            System.out.println("I am a responsible developer");

        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
