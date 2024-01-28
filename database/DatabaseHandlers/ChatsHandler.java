package database.DatabaseHandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import businessLogic.Message;
import businessLogic.User;

public class ChatsHandler
{
	private static String basePath = database.DatabaseHandlers.Path.chatsPath;
	
	public static void createChatFile(Integer chatNumber)
	{
		String fileName = chatNumber.toString() + ".txt";
        String filePath = basePath + fileName;
        
        // Create the file
        Path path = Path.of(filePath);
        try
		{
			Files.write(path, new byte[0], StandardOpenOption.CREATE);
		} 
        catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("New chat file created at: " + path.toAbsolutePath());
	}
	
	public static int getMaxFileNumber() throws SQLException 
	{
        int maxFileNumber = 0;
        Connection connection = getConnection();
        String query = "SELECT MAX(FILE_NUMBER) AS MAX_FILE_NUMBER FROM CHATS";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) 
        {
            if (resultSet.next()) 
            {
                maxFileNumber = resultSet.getInt("MAX_FILE_NUMBER");
            }
        }

        return maxFileNumber;
    }

	
	public static LinkedList<Message> getChatHistory(User user1, User user2, String filePath) 
    {
        LinkedList<Message> chatHistory = new LinkedList<>();
        System.out.println(filePath);
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                // Assuming each line follows the format: "30/11/23, 9:29 am - Sender Name: Message content"
                String[] parts = line.split(" - ", 2);
                String[] senderInfo = parts[1].split(": ", 2);

                String senderName = senderInfo[0];
                String receiverName = senderName.equals(user1.getName()) ? user2.getName() : user1.getName();

                Message message = new Message(senderName, receiverName, senderInfo[1], parts[0]);
                chatHistory.add(message);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return chatHistory;
    }
	
	private static Connection getConnection() throws SQLException 
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/HALAL_PAIRING";
        String user = "root";
        String password = Password.DBpassword;
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}
