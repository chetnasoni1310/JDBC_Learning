import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class Image_Handling_From_database_to_Folder {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password="2121912";
        String folder_path="D:\\ARMY\\Documentations\\Resumes\\";
        String query="select image_data from image_table where image_id=(?);";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully !! ");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");

           PreparedStatement preparedStatement= connection.prepareStatement(query);
           preparedStatement.setInt(1,1);

            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next())
            {
                byte[] image_data=resultSet.getBytes("image_data");
                String image_path=folder_path+"JDBC_image_retreival.jpg";
                FileOutputStream outputStream=new FileOutputStream(image_path);
                outputStream.write(image_data);
                System.out.println("Image get retreived ");
            }else{
                System.out.println("Image not found");
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
