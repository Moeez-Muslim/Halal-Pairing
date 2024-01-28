package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import businessLogic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ChatboxController implements Initializable 
{   
    @FXML
    private VBox eventsContainer;
    
    @FXML
    private TextField content;
    
    
    @FXML
    private ImageView ProfilePic;
    @FXML
    private ImageView recieverProfile;
    @FXML
    private Label RecieverName;

    @FXML
    private Label profileName;
    
   
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
    	System.out.println("------------------------------------------------");
    	try 
    	{
			HalalPairing.startChat(HalalPairing.getCurrentUser(), HalalPairing.getCurrentChat());
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	LinkedList<businessLogic.Message> m = HalalPairing.getChatHistory();
    	
    	RecieverName.setText(HalalPairing.getCurrentChat().getName());
    	this.profileName.setText(HalalPairing.getCurrentUser().getName());
    	
        try 
        {
           for (businessLogic.Message a : m) 
           {
        	   FXMLLoader fxmlloader;
        	   if(HalalPairing.getCurrentUser().getName() == a.getReceiverName()) 
        	   {
        		   
        		   fxmlloader = new FXMLLoader(getClass().getResource("RecieveMessage.fxml"));
        	   }
        	   else
        		   fxmlloader = new FXMLLoader(getClass().getResource("SendMessage.fxml"));
        	   
                AnchorPane anchorPane= fxmlloader.load();
                ChatController postController = fxmlloader.getController();
                postController.setData(a);
                
                eventsContainer.getChildren().add(anchorPane);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        Image img;
        try 
        {
        	
			img = new Image(new FileInputStream(HalalPairing.getCurrentChat().getProfilePictureName()));
			
	        recieverProfile.setImage(img);
		} 
        catch (FileNotFoundException e) 
        {
			e.printStackTrace();
		}
        
    }
    
    @FXML
    void saveMessage(ActionEvent event) 
    {
    	System.out.print(content.getText());
    	if(!content.getText().isEmpty())
    		HalalPairing.user1SendsMessage(content.getText());
    	content.clear();
    	Main m = new Main();
		try 
		{
			m.changeScene("ChatBox.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    
    @FXML
	private void userSignUp(ActionEvent event) throws IOException
	{
    	HalalPairing.closeChat();
		System.out.print("Hello");
		Main m = new Main();
		m.changeScene("createAcc.fxml");
	}
	@FXML
	private void userLogOut(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("Hello");
		Main m = new Main();
		m.changeScene("Login.fxml");
	}
	@FXML
	private void home(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("MainPage");
		Main m = new Main();
		m.changeScene("MainPage.fxml");
	}
	@FXML
	private void findPartner(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("FindPartner");
		Main m = new Main();
		m.changeScene("Find.fxml");
	}
	@FXML
	private void Request(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("FindPartner");
		Main m = new Main();
		m.changeScene("Request.fxml");
	}
	@FXML
	private void halalEvent(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("HalalEvents");
		Main m = new Main();
		m.changeScene("HalalEvents.fxml");
	}
	@FXML
	private void inviteFriend(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("inviteFriend");
		Main m = new Main();
		m.changeScene("InviteFriend.fxml");
	}
	@FXML
	private void Membership(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("Membership");
		Main m = new Main();
		m.changeScene("Membership.fxml");
	}
	@FXML
	private void Messages(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("Messages");
		Main m = new Main();
		m.changeScene("Messages.fxml");
	}
	@FXML
	private void updateProfile(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("updateProfile");
		Main m = new Main();
		m.changeScene("UpdateProfile.fxml");
	}
	@FXML
	private void editInterests(ActionEvent event) throws IOException
	{
		HalalPairing.closeChat();
		System.out.print("updateProfile");
		Main m = new Main();
		m.changeScene("EditInterests.fxml");
	}
}
