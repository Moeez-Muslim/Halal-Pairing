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

public class InviteFriendController implements Initializable
{
	 @FXML
	    private TextField email;
	 @FXML
	    private ImageView ProfileImg;
	 @FXML
	    private Label profileName;
	    @FXML
	    private Label referrralLink;
	    
	    @FXML
	    void generateLink(ActionEvent event) {
	    	String s = email.getText();
	    	referrralLink.setText("https://www.HalalPairing.com/referral/"+s);
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
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			profileName.setText(HalalPairing.getCurrentUser().getName());
			Image img;
			try {
				img = new Image(new FileInputStream(HalalPairing.getCurrentUser().getProfilePictureName()));
				
				this.ProfileImg.setImage(img);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
}
