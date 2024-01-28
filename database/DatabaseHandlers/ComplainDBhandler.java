package database.DatabaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ComplainDBhandler 
{
    // Method to add a complaint to the database
    public static void addComplaint(String cnic, String complaineeCnic, String reason) 
    {
        String query = "INSERT INTO COMPLAINS (CNIC, COMPLAINEE_CNIC, REASON) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            preparedStatement.setString(2, complaineeCnic);
            preparedStatement.setString(3, reason);
            preparedStatement.executeUpdate();
            
            System.out.println("1 row(s) affected: Add Complaint");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to get the complaints for a given CNIC
    public static HashMap<String, String> getComplaintsByCNIC(String cnic) 
    {
        HashMap<String, String> complaints = new HashMap<>();
        String query = "SELECT COMPLAINEE_CNIC, REASON FROM COMPLAINS WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    complaints.put(resultSet.getString("COMPLAINEE_CNIC"), resultSet.getString("REASON"));
                }
                
                System.out.println(complaints.size() + " row(s) returned: Get Complaints for " + cnic);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return complaints;
    }

    private static Connection getConnection() throws SQLException 
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/HALAL_PAIRING";
        String user = "root";
        String password = Password.DBpassword;
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}
