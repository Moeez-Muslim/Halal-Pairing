package businessLogic;

import java.util.HashMap;
import java.util.LinkedList;

import javafx.util.Pair;


public class Admin extends Person
{
	private LinkedList<Pair<String, String>> complainsToReview;
	
	public Admin()
	{
		complainsToReview = new LinkedList<Pair<String,String>>();
		// Load from database
	}
	
	public void restrictUser(HashMap<String, User> userList, String CNIC)
	{
		userList.get(CNIC).restrict();
	}
	
	public LinkedList<Pair<String, String>> getComplainsToReview()
	{
		return complainsToReview;
	}
	
	public void reviewComplaint(String CNIC, boolean isValid, HashMap<String, User> userList)
	{
		for(int i = 0;i < complainsToReview.size(); i++)
		{
			if (complainsToReview.get(i).getKey().equals(CNIC))
			{
				if(isValid)
				{
					userList.get(CNIC).addWarning();
					if(userList.get(CNIC).getWarningCount() >= 2)
					{
						this.restrictUser(userList, CNIC);
					}
				}
			}
		}
	}
	
	// Function to view all transactions but not their details
}
