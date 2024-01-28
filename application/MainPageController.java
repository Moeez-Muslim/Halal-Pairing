package application;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import businessLogic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainPageController implements Initializable 
{   
    @FXML
    private VBox eventsContainer;
    
    @FXML
    private Label profileName;
   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
    	LinkedList<HalalEvent> events;
    	if(HalalPairing.getCurrentUser().isPremium())
    	{
    		if(HalalPairing.getCurrentUser().getGender() == 'm')
    			events = HalalPairing.getAllMaleEvents();
    		else
    			events = HalalPairing.getAllFemaleEvents();
    		try {
	           for (HalalEvent Events : events) {
	                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("EventProfile.fxml"));
	              
	                    AnchorPane anchorPane = fxmlloader.load();
	                    EventProfileController postController = fxmlloader.getController();
	                    postController.setData(Events);
	
	                        eventsContainer.getChildren().add(anchorPane);
	
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
    		
    	profileName.setText(HalalPairing.getCurrentUser().getName());
		
    }
    
   
    
    @FXML
	private void userSignUp(ActionEvent event) throws IOException
	{
		System.out.print("Hello");
		Main m = new Main();
		m.changeScene("createAcc.fxml");
	}
	@FXML
	private void userLogOut(ActionEvent event) throws IOException
	{
		System.out.print("Hello");
		Main m = new Main();
		m.changeScene("Login.fxml");
	}
	@FXML
	private void home(ActionEvent event) throws IOException
	{
		System.out.print("MainPage");
		Main m = new Main();
		m.changeScene("MainPage.fxml");
	}
	@FXML
	private void findPartner(ActionEvent event) throws IOException
	{
		System.out.print("FindPartner");
		Main m = new Main();
		m.changeScene("Find.fxml");
	}
	@FXML
	private void Request(ActionEvent event) throws IOException
	{
		System.out.print("FindPartner");
		Main m = new Main();
		m.changeScene("Request.fxml");
	}
	@FXML
	private void halalEvent(ActionEvent event) throws IOException
	{
		System.out.print("HalalEvents");
		Main m = new Main();
		m.changeScene("HalalEvents.fxml");
	}
	@FXML
	private void inviteFriend(ActionEvent event) throws IOException
	{
		System.out.print("inviteFriend");
		Main m = new Main();
		m.changeScene("InviteFriend.fxml");
	}
	@FXML
	private void Membership(ActionEvent event) throws IOException
	{
		System.out.print("Membership");
		Main m = new Main();
		m.changeScene("Membership.fxml");
	}
	@FXML
	private void Messages(ActionEvent event) throws IOException
	{
		System.out.print("Messages");
		Main m = new Main();
		m.changeScene("Messages.fxml");
	}
	@FXML
	private void updateProfile(ActionEvent event) throws IOException
	{
		System.out.print("updateProfile");
		Main m = new Main();
		m.changeScene("UpdateProfile.fxml");
	}
	@FXML
	private void editInterests(ActionEvent event) throws IOException
	{
		System.out.print("updateProfile");
		Main m = new Main();
		m.changeScene("EditInterests.fxml");
	}
}
