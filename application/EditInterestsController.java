package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import businessLogic.HalalPairing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EditInterestsController implements Initializable
{
	  @FXML
	    private CheckBox Anime;

	    @FXML
	    private CheckBox Cars;

	    @FXML
	    private CheckBox Cooking;

	    @FXML
	    private CheckBox Fitness;

	    @FXML
	    private CheckBox Football;

	    @FXML
	    private Label profileName;
	    @FXML
	    private ImageView profilepic;
	    
	    @FXML
	    private CheckBox Movies;

	    @FXML
	    private CheckBox Painting;

	    @FXML
	    private CheckBox Pets;

	    @FXML
	    private CheckBox Photography;

	    @FXML
	    private CheckBox Programming;

	    @FXML
	    private CheckBox Reading;

	    @FXML
	    private CheckBox Traveling;
	    
	    private Set<String> interests = new HashSet<>();
	    
	    private void updateInterests(CheckBox checkBox, String interest) 
	    {
	        if (checkBox.isSelected()) 
	        {
	            HalalPairing.addUserInterest(HalalPairing.getCurrentUser().getCNIC(), interest);
	        } 
	        else 
	        {
	            HalalPairing.removeUserInterest(HalalPairing.getCurrentUser().getCNIC(), interest);
	        }
	    }

	    @FXML
	    void Submit(ActionEvent event) 
	    {
	    	try 
	    	{
				home(null);
			} 
	    	catch (IOException e) 
	    	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	updateInterests(Anime, "Anime");
	        updateInterests(Cars, "Cars");
	        updateInterests(Cooking, "Cooking");
	        updateInterests(Fitness, "Fitness");
	        updateInterests(Football, "Football");
	        updateInterests(Movies, "Movies");
	        updateInterests(Painting, "Painting");
	        updateInterests(Pets, "Pets");
	        updateInterests(Photography, "Photography");
	        updateInterests(Programming, "Programming");
	        updateInterests(Reading, "Reading");
	        updateInterests(Traveling, "Traveling");

	    }
	  
	    private void selectCheckBoxIfPresent(CheckBox checkBox, String interest) 
	    {
	        if (interests.contains(interest)) 
	        {
	            checkBox.setSelected(true);
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
		Image s;
		try {
			s = new Image(new FileInputStream(HalalPairing.getCurrentUser().getProfilePictureName()));
			profilepic.setImage(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		profileName.setText(HalalPairing.getCurrentUser().getName());
		interests = HalalPairing.getAllInterests(HalalPairing.getCurrentUser().getCNIC());
		System.out.println("Current Interests: " + interests);
		selectCheckBoxIfPresent(Anime, "Anime");
        selectCheckBoxIfPresent(Cars, "Cars");
        selectCheckBoxIfPresent(Cooking, "Cooking");
        selectCheckBoxIfPresent(Fitness, "Fitness");
        selectCheckBoxIfPresent(Football, "Football");
        selectCheckBoxIfPresent(Movies, "Movies");
        selectCheckBoxIfPresent(Painting, "Painting");
        selectCheckBoxIfPresent(Pets, "Pets");
        selectCheckBoxIfPresent(Photography, "Photography");
        selectCheckBoxIfPresent(Programming, "Programming");
        selectCheckBoxIfPresent(Reading, "Reading");
        selectCheckBoxIfPresent(Traveling, "Traveling");
		
	}
}
