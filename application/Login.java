package application;

import businessLogic.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

import java.io.IOException;
public class Login 
{
	@FXML
	private Button button;
	@FXML
	private PasswordField password;
	@FXML
	private TextField username;
	@FXML
	private TextField CNIC;
	@FXML
    private TextField wrongLogIn;
    @FXML
    private ImageView wrongCNIC;

    @FXML
    private ImageView wrongPassword;

	public Login()
	{
		
	}
	@FXML
	private void userLogIn(ActionEvent event) throws IOException
	{
		checkCredentials(); 
		
	}
	@FXML
	private void checkCredentials() throws IOException {
	    Main m = new Main();
	    
	    if (CNIC != null && CNIC.getText() != null && HalalPairing.checkCNIC(CNIC.getText())) {
	    	
	    	if(HalalPairing.verifyPassword(CNIC.getText(), password.getText())) {
	    		if(!HalalPairing.isUserRestricted(CNIC.getText()))
	    		{
	    			HalalPairing.LogIN(CNIC.getText());
	    			System.out.print(HalalPairing.getCurrentUser().getName());
	    			m.changeScene("MainPage.fxml");
	    		}
	    		
	    	}
	    	else
	    		wrongPassword.setVisible(true);
	        
	    } else if (CNIC == null || CNIC.getText().isEmpty()) {
	        wrongCNIC.setVisible(true);
	    } else {
	    	wrongCNIC.setVisible(true);wrongPassword.setVisible(true);
	    }
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
		System.out.print("FindPartner");
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
}
