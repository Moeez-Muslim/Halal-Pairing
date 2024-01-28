package application;

import java.net.URL;
import java.util.ResourceBundle;

import businessLogic.HalalEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class EventProfileController implements Initializable
{
    @FXML
    private Label EventName;
    @FXML
    private Label Location;
    @FXML
    private Text desp;
    
    public void setData(HalalEvent a) 
    {
    	EventName.setText(a.getTitle());
    	Location.setText(a.getVenue());
    	desp.setText(a.getDescription());
    }
   
    
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//setData(getEvent());
	}
}
