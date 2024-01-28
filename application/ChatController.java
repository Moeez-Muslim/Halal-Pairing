package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import businessLogic.HalalPairing;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ChatController implements Initializable {
    @FXML
    private Text content;

    @FXML
    private Label date;

    @FXML
    private ImageView sentProfile;
    @FXML
    private ImageView recieveProfile;
    
    public void setData(businessLogic.Message m) 
    {
    	content.setText(m.getContent());
    	date.setText(m.getTimeSent());
        try 
        {
        	/*
            if (m. != null && !m.receiverProfile.isEmpty()) {
                Image profileImage = new Image(new FileInputStream(m.receiverProfile));
                recieveProfile.setImage(profileImage);
            } else {
            	recieveProfile.setVisible(false);
            	recieveProfile.setManaged(false);
            }
            
            if (m.senderProfile != null && !m.senderProfile.isEmpty()) {
                Image profileImage = new Image(new FileInputStream(m.senderProfile));
                sentProfile.setImage(profileImage);
            } else {
            	sentProfile.setVisible(false);
            	sentProfile.setManaged(false);
            }*/
        	Image profileImage = new Image(new FileInputStream(HalalPairing.getCurrentUser().getProfilePictureName()));
            recieveProfile.setImage(profileImage);
            profileImage = new Image(new FileInputStream(HalalPairing.getCurrentChat().getProfilePictureName()));
            sentProfile.setImage(profileImage);
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//(getMessage());
		
	}
}
