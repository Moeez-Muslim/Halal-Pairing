package application;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import businessLogic.HalalPairing;
import businessLogic.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class PaymentController implements Initializable 
{
	@FXML
    private CheckBox Bank;

    @FXML
    private CheckBox microfinance;
    @FXML
    private Label profileName;
    
    @FXML
    void submit(ActionEvent event) 
    {
    	User u = HalalPairing.getCurrentUser();
		HalalPairing.addPayment(u.getCNIC(), 19.99, "Credit Card", new Date());
		HalalPairing.getCurrentUser().upgradeToPremium();
		Main m = new Main();
		try 
		{
			m.changeScene("MainPage.fxml");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
    }
    @FXML
    void handleBank(ActionEvent event) 
    {
    	if(Bank.isSelected())
    		microfinance.setSelected(false);
    }

    @FXML
    void handleMicro(ActionEvent event) 
    {
    	if(microfinance.isSelected())
    		Bank.setSelected(false);
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
	public void initialize(URL arg0, ResourceBundle arg1) 
	{	
		profileName.setText(HalalPairing.getCurrentUser().getName());
	}
}
