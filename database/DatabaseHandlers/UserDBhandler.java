package database.DatabaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import businessLogic.*;

public class UserDBhandler 
{
	// Method to get all users
    public static HashMap<String, User> getAllUsers() 
    {
        HashMap<String, User> usersMap = new HashMap<>();

        String query = "SELECT * FROM USER";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) 
        {

            while (resultSet.next()) 
            {
                User user = extractUserFromResultSet(resultSet);
                usersMap.put(user.getCNIC(), user);
            }

            System.out.println(usersMap.size() + " row(s) returned: Get all Users");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return usersMap;
    }
    
    
    // Method to get user by CNIC
    public static User getUserByCNIC(String cnic) 
    {
        String query = "SELECT * FROM USER WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                if (resultSet.next()) 
                {
                    return extractUserFromResultSet(resultSet);
                }
            }
            
            System.out.println("1 row(s) returned: Get User " + cnic);
            preparedStatement.close();
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to add a new user
    public static void addUser(User user) 
    {
        String query = "INSERT INTO USER (CNIC, NAME, AGE, GENDER, PREMIUM, WARNING_COUNT, PROFILE_PICTURE) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, user.getCNIC());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, String.valueOf(user.getGender()));
            preparedStatement.setBoolean(5, false);
            preparedStatement.setInt(6, user.getWarningCount());
            preparedStatement.setString(7, user.getProfilePictureName());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected: Add User");

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to update an existing user
    public static void updateUser(User user) 
    {
        String query = "UPDATE USER SET NAME = ?, AGE = ?, GENDER = ?, PREMIUM = ?, WARNING_COUNT = ?, PROFILE_PICTURE = ? WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, String.valueOf(user.getGender()));
            preparedStatement.setBoolean(4, user.isPremium());
            preparedStatement.setInt(5, user.getWarningCount());
            preparedStatement.setString(6, user.getProfilePictureName());
            preparedStatement.setString(7, user.getCNIC());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected: Update User " + user.getCNIC());

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    // Method to delete a user
    public static void deleteUser(String cnic) 
    {
        String query = "DELETE FROM USER WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + "row(s) affected: Delete User");
            
            preparedStatement.close();
            connection.close();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

 // Method to extract user information from a ResultSet
    private static User extractUserFromResultSet(ResultSet resultSet) throws SQLException 
    {
        String cnic = resultSet.getString("CNIC");
        String name = resultSet.getString("NAME");
        int age = resultSet.getInt("AGE");
        char gender = resultSet.getString("GENDER").charAt(0);
        boolean premium = resultSet.getBoolean("PREMIUM");
        int warningCount = resultSet.getInt("WARNING_COUNT");
        String profilePictureName = resultSet.getString("PROFILE_PICTURE");

        User user = new User(name, cnic, age, gender, profilePictureName);

        if (premium) 
        {
            user.upgradeToPremium();
        }

        user.setWarningCount(warningCount);
        //resultSet.close();

        return user;
    }


    // Method to add warning count for a given CNIC
    public static void addWarningCount(String cnic) 
    {
        String query = "UPDATE USER SET WARNING_COUNT = WARNING_COUNT + 1 WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + "row(s) affected: Add Warning for " + cnic);
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    // Method to upgrade user to premium by setting premium to true
    public static void upgradeToPremium(String cnic) 
    {
        String query = "UPDATE USER SET PREMIUM = true WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            int rowsAffected = preparedStatement.executeUpdate();
            
            System.out.println(rowsAffected + "row(s) affected: Upgrade to Premium");
            
            preparedStatement.close();
            connection.close();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
 // Method to get chat information by CNIC
    public static HashMap<String, Integer> getChatsByCNIC(String cnic) 
    {
        HashMap<String, Integer> chatsMap = new HashMap<>();

        String query = "SELECT * FROM CHATS WHERE CNIC = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, cnic);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    String partnerCNIC = resultSet.getString("PARTNER_CNIC");
                    int fileNumber = resultSet.getInt("FILE_NUMBER");

                    chatsMap.put(partnerCNIC, fileNumber);
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return chatsMap;
    }

    // Method to add a chat for a given CNIC and partnerCNIC
    public static void addChat(String cnic, String partnerCNIC, int fileNumber) 
    {
        String query = "INSERT INTO CHATS (CNIC, PARTNER_CNIC, FILE_NUMBER) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {

            preparedStatement.setString(1, cnic);
            preparedStatement.setString(2, partnerCNIC);
            preparedStatement.setInt(3, fileNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

        } catch (SQLException e) 
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

