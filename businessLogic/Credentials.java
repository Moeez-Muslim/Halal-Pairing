package businessLogic;

import java.util.HashMap;

import database.DatabaseHandlers.CredentialsDBhandler;

public class Credentials
{
	private HashMap<String, String> logInInfo;
	
	public Credentials()
	{
		logInInfo = CredentialsDBhandler.getAllCredentials();
	}
	
	public boolean addCredentials(String CNIC, String password)
	{
		if(!logInInfo.containsKey(CNIC))
		{
			logInInfo.put(CNIC, password);
			return true;
		}
		
		return false;
	}
	
	public boolean checkCNIC(String CNIC)
	{
		return logInInfo.containsKey(CNIC);	
	}
	
	public boolean verifyPassword(String CNIC, String password)
	{
		return (logInInfo.get(CNIC).equals(password));
	}
	
	public void removeCredentials(String CNIC)
	{
		logInInfo.remove(CNIC);
		CredentialsDBhandler.deleteCredentials(CNIC);
	}
	
	public void changePassword(String CNIC, String password)
	{
		logInInfo.remove(CNIC);
		logInInfo.put(CNIC, password);
		CredentialsDBhandler.updatePassword(CNIC, password);
	}
}
