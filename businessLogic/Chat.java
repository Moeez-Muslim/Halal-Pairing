package businessLogic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import database.DatabaseHandlers.ChatsHandler;

public class Chat 
{
    private User user1;
    private User user2;
    private String basePath = database.DatabaseHandlers.Path.chatsPath;
    private Integer chatNumber;
    private String filePath;

    LinkedList<Message> chats;

    public Chat()
	{
		user1 = null;
		user2 = null;
		filePath = "";
	}
    
    public void Initialize(User user1, User user2) throws SQLException 
    {
        this.user1 = user1;
        this.user2 = user2;
        this.chatNumber = ChatsHandler.getMaxFileNumber() + 1;

        if (user1.getChatNumber(user2.getCNIC()) == 0 || user2.getChatNumber(user1.getCNIC()) == 0)  // if chat file doesn't exist, create a new one
        {
            String fileName = chatNumber.toString() + ".txt";
            filePath = basePath + fileName;

            ChatsHandler.createChatFile(chatNumber);

            user1.addChat(user2.getCNIC(), chatNumber);
            user2.addChat(user1.getCNIC(), chatNumber);

            chatNumber++;
        } 
        else 
        {
            // Open existing chat file
            String fileName = user1.getChatNumber(user2.getCNIC()) + ".txt";
            filePath = basePath + fileName;
        }
    }

    public void user1SendsMessage(String content) 
    {
        sendMessage(user1, user2, content);
    }

    public void user2SendsMessage(String content) 
    {
        sendMessage(user2, user1, content);
    }

    private void sendMessage(User sender, User receiver, String content) 
    {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardOpenOption.APPEND)) 
        {
            // Format the current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy, h:mm a");
            String formattedDate = dateFormat.format(new Date());

            // Write the message to the file
            String message = String.format("%s - %s: %s%n", formattedDate, sender.getName(), content);
            writer.write(message);

            // Update the in-memory chat
            if (chats == null) 
            {
                chats = new LinkedList<>();
            }
            chats.add(new Message(sender.getName(), receiver.getName(), content, formattedDate));

            //System.out.println(sender.getName() + " sent a message: " + content);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public LinkedList<Message> getChatHistory() 
    {
        return ChatsHandler.getChatHistory(user1, user2, filePath);
    }
    
    public void closeChat()
    {
    	user1 = null;
    	user2 = null;
    	filePath = "";
    }
}

