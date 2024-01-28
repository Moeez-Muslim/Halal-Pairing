package database.DatabaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ProfileDBhandler 
{
    // Method to get interests by CNIC
    public static Set<String> getInterestsByCNIC(String cnic) 
    {
        Set<String> interests = new HashSet<>();
        String query = "SELECT INTEREST FROM PROFILE WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    interests.add(resultSet.getString("INTEREST"));
                }
                
                resultSet.close();
            }
            
            System.out.println(interests.size() + " row(s) returned: Get Interests for " + cnic);
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
     
        return interests;
    }
    
    
    // Method to add interest for a given CNIC
    public static void addInterest(String cnic, String interest) 
    {
        String query = "INSERT INTO PROFILE (CNIC, INTEREST) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            preparedStatement.setString(2, interest);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected: Add Interest");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to remove interest for a given CNIC
    public static void removeInterest(String cnic, String interest) 
    {
        String query = "DELETE FROM PROFILE WHERE CNIC = ? AND INTEREST = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            preparedStatement.setString(2, interest);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected: Remove Interest");
            
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
    
    // Other methods related to interests can be added as needed
}
