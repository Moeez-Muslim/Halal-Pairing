package database.DatabaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MatchDBhandler 
{
    // Method to add a match for a given CNIC
    public static void addMatch(String userCNIC, String partnerCNIC, double similarityIndex) 
    {
        String query = "INSERT INTO MATCHES (CNIC, PARTNER_CNIC, SIMILARITY_INDEX) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, userCNIC);
            preparedStatement.setString(2, partnerCNIC);
            preparedStatement.setDouble(3, similarityIndex);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + "row(s) affected: Add Match");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to remove a match for a given CNIC and partner CNIC
    public static void removeMatch(String userCNIC, String partnerCNIC) 
    {
        String query = "DELETE FROM MATCHES WHERE CNIC = ? AND PARTNER_CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, userCNIC);
            preparedStatement.setString(2, partnerCNIC);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + "row(s) affected: Remove Match");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to get all matches for a given CNIC
    public static HashMap<String, Double> getMatchesByCNIC(String userCNIC) 
    {
        HashMap<String, Double> matchMap = new HashMap<>();
        String query = "SELECT PARTNER_CNIC, SIMILARITY_INDEX FROM MATCHES WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, userCNIC);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    String partnerCNIC = resultSet.getString("PARTNER_CNIC");
                    double similarityIndex = resultSet.getDouble("SIMILARITY_INDEX");
                    matchMap.put(partnerCNIC, similarityIndex);
                    
                }
                
                resultSet.close();
                
                System.out.println(matchMap.size() + " row(s) returned: Get Matches for " + userCNIC);
            }
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return matchMap;
    }

    private static Connection getConnection() throws SQLException 
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/HALAL_PAIRING";
        String user = "root";
        String password = Password.DBpassword;
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}
