package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import businessLogic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SignUpController implements Initializable
{
	@FXML
    private TextField CNIC;

    @FXML
    private TextField Email;

    @FXML
    private TextField Fname;

    @FXML
    private TextField Lname;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private ComboBox<Integer> day;

    @FXML
    private CheckBox female;

    @FXML
    private CheckBox male;
    
    @FXML 
    private ImageView wrongpass;

    @FXML
    private ComboBox<Integer> month;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<Integer> year;
    
   
    
    @FXML
    void handleFemaleBox(ActionEvent event) 
    {
    	if(female.isSelected())
			male.setSelected(false);
    }

    @FXML
    void handleMaleBox(ActionEvent event) 
    {
    	if(male.isSelected())
			female.setSelected(false);
    }
    
    @FXML
    void selectProfile(ActionEvent event) throws IOException
    {
    	User user;
    	
    	LocalDate currentDate = LocalDate.now();

        LocalDate birthDate = LocalDate.of(year.getValue(),month.getValue(),day.getValue() );

        Period age = Period.between(birthDate, currentDate);
        int year = age.getYears();
        
    	
    	
    	if(password.getText().equals(confirmPassword.getText()))
    	{
    		HalalPairing.addCredentials(CNIC.getText(), password.getText());
    		if(male.isSelected())
    			user = UserFactory.createUser(Fname.getText()+" "+Lname.getText(),CNIC.getText(),year,'m',"");
        	else
        		user = UserFactory.createUser(Fname.getText()+" "+Lname.getText(),CNIC.getText(),year,'f',"");
    		HalalPairing.addUser(user);
    		Main m = new Main();
    		m.changeScene("SelectProfile.fxml");
    	}
    	else
    	{
    		wrongpass.setVisible(true);
    	}
    	
		
    }

    @FXML
    void userLogOut(ActionEvent event) throws IOException{
    	Main m = new Main();
		m.changeScene("Login.fxml");
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<Integer> daylist = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30);
        ObservableList<Integer> monthlist = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        ObservableList<Integer> yearlist = FXCollections.observableArrayList();
        
    	
	    for(int i=1910;i<2004;i++)
	    {
			yearlist.add(i);
	    }
	    if (day != null) 
	    day.setItems(daylist);
	    if (month != null) 
		month.setItems(monthlist);
	    if (year != null) 
		year.setItems(yearlist);
		
		
	}
}
