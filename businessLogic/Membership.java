package businessLogic;

import java.util.List;

import database.DatabaseHandlers.UserDBhandler;

import java.util.ArrayList;

public class Membership
{
	private String CNIC;
	private List<String> plans;
	private int currentPlan;
	
	public Membership(String cnic)
	{
		this.CNIC = cnic;
		plans = new ArrayList<String>();
		plans.add("Basic");
		plans.add("Premium");
		currentPlan = 0;  // Points to basic plan
	}
	
	public boolean upgradeToPremium()
	{
		if(currentPlan == 0)
		{
			currentPlan = 1;
			UserDBhandler.upgradeToPremium(CNIC);
			return true;
		}
		
		return false;
	}
	
	public boolean isPremium()
	{
		if(currentPlan == 0)
			return false;
		else
			return true;
	}
}
