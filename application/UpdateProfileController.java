package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import businessLogic.HalalPairing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UpdateProfileController implements Initializable{
	
	 @FXML
	    private TextField EmailAddress;

	    @FXML
	    private TextField NewPassword;

	    @FXML
	    private TextField confirmPassword;

	    @FXML
	    private ImageView profileImg;
	    @FXML
	    void Submit(ActionEvent event) {
	    	if(!NewPassword.getText().isEmpty())
	    	{
	    		if(NewPassword.getText().equals(confirmPassword.getText()))
	    			HalalPairing.changePassword(HalalPairing.getCurrentUser().getCNIC(),NewPassword.getText());
	    	}
	    }
	    
	    
	    
	    @FXML
		private void deleteUser(ActionEvent event) throws IOException
		{
			HalalPairing.deleteUser();
			Main m = new Main();
			m.changeScene("Login.fxml");
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

    @FXML
    private Label profileName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image s;
		try {
			s = new Image(new FileInputStream(HalalPairing.getCurrentUser().getProfilePictureName()));
			profileImg.setImage(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		profileName.setText(HalalPairing.getCurrentUser().getName());
	}
}
