package businessLogic;

import java.util.Set;

import database.DatabaseHandlers.ProfileDBhandler;

public class Profile
{
	private String CNIC;
	private Set<String> interests;
	
	public Profile(String cnic)
	{
		this.CNIC = cnic;
		this.interests = ProfileDBhandler.getInterestsByCNIC(cnic);
		
	}
	
	// Add interest to the user's set of interests
    public void addInterest(String interest) 
    {
        interests.add(interest);
        ProfileDBhandler.addInterest(this.CNIC, interest);
    }
    
    public void removeInterest(String interest)
    {
    	interests.remove(interest);
    	ProfileDBhandler.removeInterest(CNIC, interest);
    }
	
    public Set<String> getAllInterests()
    {
    	return interests;
    }
    
}
