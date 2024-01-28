package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import businessLogic.HalalPairing;
import businessLogic.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

public class ShowProfileController implements Initializable
{
    @FXML
    private Label ProfileName;

    @FXML
    private Label age;

    @FXML
    private Label CNIC;
    @FXML
    private ImageView imgProfile;
    @FXML
    private org.controlsfx.control.Rating feedback;
    @FXML
    public void feedback(MouseEvent me)
    {
    	HalalPairing.addUserReview(CNIC.getText(), HalalPairing.getCurrentUser().getName(), String.valueOf((int)feedback.getRating()) );
    	System.out.print("Rating"+ feedback.getRating());
    }
    
    public void setData(User a) {

        ProfileName.setText(a.getName());
        CNIC.setText(a.getCNIC());
        try {
            if (a.getProfilePictureName() != null) 
            {
                Image profileImage = new Image(new FileInputStream(a.getProfilePictureName()));
                imgProfile.setImage(profileImage);
            } else {
                imgProfile.setVisible(false);
                imgProfile.setManaged(false);
            }
            age.setText(String.valueOf(a.getAge()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //HalalPairing.getUserReviews(null)
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//setData(getAccount());	
	}
    
}
