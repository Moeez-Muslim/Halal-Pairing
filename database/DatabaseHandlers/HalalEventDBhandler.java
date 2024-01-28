package database.DatabaseHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import businessLogic.HalalEvent;
import businessLogic.User;

public class HalalEventDBhandler 
{
    // Method to add a new HalalEvent
    public static void addHalalEvent(HalalEvent halalEvent) 
    {
        String query = "INSERT INTO HALAL_EVENTS (TITLE, DESCRIPTION, ORGANIZER_CNIC, VENUE, GENDER) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, halalEvent.getTitle());
            preparedStatement.setString(2, halalEvent.getDescription());
            preparedStatement.setString(3, halalEvent.getOrganizer().getCNIC());
            preparedStatement.setString(4, halalEvent.getVenue());
            preparedStatement.setString(5, String.valueOf(halalEvent.getGender()));

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + "row(s) affected: Add Halal Event");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to get HalalEvent by title
    public static HalalEvent getHalalEventByTitle(String title) 
    {
        String query = "SELECT * FROM HALAL_EVENTS WHERE TITLE = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                if (resultSet.next()) 
                {
                    return extractHalalEventFromResultSet(resultSet);
                }
            }

            System.out.println("1 row(s) returned: Get Halal Event");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    // Method to update an existing HalalEvent
    public static void updateHalalEvent(HalalEvent halalEvent) 
    {
        String query = "UPDATE HALAL_EVENTS SET DESCRIPTION = ?, ORGANIZER_CNIC = ?, VENUE = ?, GENDER = ? WHERE TITLE = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, halalEvent.getDescription());
            preparedStatement.setString(2, halalEvent.getOrganizer().getCNIC());
            preparedStatement.setString(3, halalEvent.getVenue());
            preparedStatement.setString(4, String.valueOf(halalEvent.getGender()));
            preparedStatement.setString(5, halalEvent.getTitle());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected: Update Halal Event");
            
            preparedStatement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to delete a HalalEvent by title
    public static void deleteHalalEvent(String title) 
    {
        String query = "DELETE FROM HALAL_EVENTS WHERE TITLE = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, title);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + "row(s) affected: Delete Halal Event");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to extract HalalEvent information from a ResultSet
    private static HalalEvent extractHalalEventFromResultSet(ResultSet resultSet) throws SQLException 
    {
        String title = resultSet.getString("TITLE");
        String description = resultSet.getString("DESCRIPTION");
        String organizerCNIC = resultSet.getString("ORGANIZER_CNIC");
        String venue = resultSet.getString("VENUE");
        char gender = resultSet.getString("GENDER").charAt(0);

        User organizer = UserDBhandler.getUserByCNIC(organizerCNIC);

        //resultSet.close();

        return new HalalEvent(title, description, gender, venue, null, 0, organizer);
    }
    
    // Method to get all male events
    public static LinkedList<HalalEvent> getAllMaleEvents() 
    {
        return getAllEventsByGender('M');
    }

    // Method to get all female events
    public static LinkedList<HalalEvent> getAllFemaleEvents() 
    {
        return getAllEventsByGender('F');
    }

    // Helper method to get all events by gender
    private static LinkedList<HalalEvent> getAllEventsByGender(char gender) 
    {
    	LinkedList<HalalEvent> events = new LinkedList<HalalEvent>();
    	
        String query = "SELECT * FROM HALAL_EVENTS WHERE GENDER = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {
            preparedStatement.setString(1, String.valueOf(gender));

            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    HalalEvent event = extractHalalEventFromResultSet(resultSet);
                    events.add(event);
                }
                
                System.out.println(events.size() + " row(s) returned: Get Events by Gender for " + gender);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return events;
    }

    private static Connection getConnection() throws SQLException 
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/HALAL_PAIRING";
        String user = "root";
        String password = Password.DBpassword;
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}
