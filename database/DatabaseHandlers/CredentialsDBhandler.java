package database.DatabaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CredentialsDBhandler 
{
	// Method to get all credentials from the database
    public static HashMap<String, String> getAllCredentials() 
    {
        HashMap<String, String> credentialsMap = new HashMap<>();

        String query = "SELECT * FROM CREDENTIALS";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) 
        {
            
            while (resultSet.next()) 
            {
                String cnic = resultSet.getString("CNIC");
                String password = resultSet.getString("PASSWORD");

                credentialsMap.put(cnic, password);
            }
            
            System.out.println(credentialsMap.size() + " row(s) returned: Get Credentials");
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        
        return credentialsMap;
    }
    
    // Method to add credentials to the database
    public static void addCredentials(String cnic, String password) 
    {
        String query = "INSERT INTO CREDENTIALS (CNIC, PASSWORD) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            preparedStatement.setString(2, password);
            int rows = preparedStatement.executeUpdate();
            
            System.out.println(rows + " row(s) affected: Add Credentials");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to delete credentials for a given CNIC
    public static void deleteCredentials(String cnic) 
    {
        String query = "DELETE FROM CREDENTIALS WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            int rows = preparedStatement.executeUpdate();
            
            System.out.println(rows + " row(s) affected: Delete Credentials");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to update the password for a given CNIC
    public static void updatePassword(String cnic, String newPassword) 
    {
        String query = "UPDATE CREDENTIALS SET PASSWORD = ? WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, cnic);
            int rows = preparedStatement.executeUpdate();
            
            System.out.println(rows + " row(s) affected: Update password");
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
