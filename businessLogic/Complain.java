package businessLogic;

import java.util.HashMap;

import database.DatabaseHandlers.ComplainDBhandler;
import database.DatabaseHandlers.UserDBhandler;

public class Complain
{
	private String CNIC;
	private int warningCount;
	HashMap<String, String> complains;
	
	public Complain(String cnic)
	{
		this.CNIC = cnic;
		
		complains = ComplainDBhandler.getComplaintsByCNIC(cnic);
	}
	
	public void addComplain(String complain, String giverCNIC)
	{
		complains.put(giverCNIC, complain);
		warningCount++;
		
		ComplainDBhandler.addComplaint(this.CNIC, giverCNIC, complain);
		UserDBhandler.addWarningCount(CNIC);
	}
	
	public int getWarningCount()
	{
		return warningCount;
	}
	
	public void addWarningCount()
	{
		this.warningCount++;
		UserDBhandler.addWarningCount(CNIC);
	}
	
	public void setWarningCount(int count)
	{
		this.warningCount = count;
	}
}
