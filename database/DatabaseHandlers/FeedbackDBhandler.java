package database.DatabaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class FeedbackDBhandler 
{
    // Method to add review for a given CNIC
    public static void addReview(String cnic, String giverName, String review) 
    {
        String query = "INSERT INTO FEEDBACK (CNIC, GIVER_NAME, REVIEW) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            preparedStatement.setString(2, giverName);
            preparedStatement.setString(3, review);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected: Add Review");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to get reviews by CNIC
    public static HashMap<String, String> getReviewsByCNIC(String cnic) 
    {
        HashMap<String, String> reviews = new HashMap<>();
        String query = "SELECT GIVER_NAME, REVIEW FROM FEEDBACK WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    String giverName = resultSet.getString("GIVER_NAME");
                    String review = resultSet.getString("REVIEW");
                    reviews.put(giverName, review);
                }
                
                System.out.println(reviews.size() + " row(s) returned: Get Reviews for " + cnic);
                
                resultSet.close();
            }
            
            preparedStatement.close();
            connection.close();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return reviews;
    }

    // Method to update an existing review
    public static void updateReview(String cnic, String giverName, String newReview) 
    {
        String query = "UPDATE FEEDBACK SET REVIEW = ? WHERE CNIC = ? AND GIVER_NAME = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, newReview);
            preparedStatement.setString(2, cnic);
            preparedStatement.setString(3, giverName);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected: Update Review");
            
            preparedStatement.close();
            connection.close();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to delete a review
    public static void deleteReview(String cnic, String giverName) 
    {
        String query = "DELETE FROM FEEDBACK WHERE CNIC = ? AND GIVER_NAME = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            preparedStatement.setString(2, giverName);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected: Delete Review");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
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
