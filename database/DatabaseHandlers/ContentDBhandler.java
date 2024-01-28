package database.DatabaseHandlers;

import businessLogic.Content;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ContentDBhandler 
{
    // Method to add content to the database
    public static void addContent(Content content) 
    {
        String query = "INSERT INTO CONTENT (CNIC, PICTURE_PATH, TEXT, UPLOADED) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {

            preparedStatement.setString(1, content.getCNIC());
            preparedStatement.setString(2, content.getPicturePath());
            preparedStatement.setString(3, content.getText());
            preparedStatement.setString(4, getCurrentDate());

            int rows = preparedStatement.executeUpdate();
            
            System.out.println(rows + " rows(s) affected: Add content");

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to get all content from the database
    public static LinkedList<Content> getAllContent() 
    {
        LinkedList<Content> contentList = new LinkedList<>();

        String query = "SELECT * FROM CONTENT";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) 
        {

            while (resultSet.next()) 
            {
                String cnic = resultSet.getString("CNIC");
                String picturePath = resultSet.getString("PICTURE_PATH");
                String text = resultSet.getString("TEXT");
                String uploadDate = resultSet.getString("UPLOADED");

                Content content = new Content(cnic, picturePath, text, convertStringToDate(uploadDate));
                contentList.add(content);
            }
            
            System.out.println(contentList.size() + " rows(s) returned: Get content");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return contentList;
    }

    // Helper method to get the current date in string format
    private static String getCurrentDate() 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    // Helper method to convert string to date
    private static Date convertStringToDate(String dateString) 
    {
        try 
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private static Connection getConnection() throws SQLException 
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/HALAL_PAIRING";
        String user = "root";
        String password = Password.DBpassword;
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}

