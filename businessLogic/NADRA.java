package businessLogic;

import java.util.LinkedList;

public class NADRA
{
	private LinkedList<String> CNIC_database;
	
	public NADRA()
	{
		setCNIC_database(new LinkedList<String>());
	}
	
	public boolean verifyUser(String CNIC)
	{
		// To be verified using a NADRA Verification API
		return true;
	}

	public LinkedList<String> getCNIC_database()
	{
		return CNIC_database;
	}

	public void setCNIC_database(LinkedList<String> cNIC_database)
	{
		CNIC_database = cNIC_database;
	}
}
