package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import businessLogic.*;
import businessLogic.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MessageProfile implements Initializable
{

    @FXML
    private Label ProfileName;

    @FXML
    private Label CNIC;
    @FXML
    private ImageView imgProfile;
    
    @FXML
    void openChatBox(ActionEvent event) {
    	HalalPairing.setCurrentChat(HalalPairing.getUser(CNIC.getText()));
    	System.out.println('\n'+CNIC.getText());
    	Main m = new Main();
		try {
			m.changeScene("ChatBox.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void setData(User a,String s) 
    {
        ProfileName.setText(a.getName());
        System.out.println('\n'+s);
        if(this.CNIC != null)
        	this.CNIC.setText(s);
        try {
            if (a.getProfilePictureName() != null) {
                Image profileImage = new Image(new FileInputStream(a.getProfilePictureName()));
                imgProfile.setImage(profileImage);
            } else {
                imgProfile.setVisible(false);
                imgProfile.setManaged(false);
            }
            
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		
	}

}
