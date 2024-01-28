package businessLogic;

import java.util.HashMap;
import java.util.Set;

import database.DatabaseHandlers.UserDBhandler;

public class User extends Person
{
    private Membership membership;
    private Complain complains;
    private boolean restricted;
    private Feedback feedback; 
    private Profile profile;
    private Match matches;
    private String profilePictureName;
    private HashMap<String, Integer> chats; 
    
    public User(String name, String cnic, int age, char gender, String pFpName)
	{
    	super(name, cnic, age, gender);
    	restricted = false;
    	setProfilePictureName(pFpName);
    	
    	membership = new Membership(cnic);
    	complains = new Complain(cnic);
    	matches = new Match(cnic);
    	feedback = new Feedback(cnic);
    	profile = new Profile(cnic);
    	chats = UserDBhandler.getChatsByCNIC(cnic);
	}
    
    public boolean upgradeToPremium()
    {
    	return membership.upgradeToPremium();
    }
    
    public void restrict()
    {
    	restricted = true;
    }
    
    public void setWarningCount(int C)
    {
    	this.complains.setWarningCount(C);
    }
    
    public boolean isRestricted()
    {
    	return this.restricted;
    }
    
    public void addComplain(String complaineeCNIC, String complain)
	{
		this.complains.addComplain(complaineeCNIC ,complain);
	}
	
	public int getWarningCount()
	{
		if(complains != null)
			return complains.getWarningCount();
		else 
			return 0;
	}
	
	public void addWarning()
	{
		this.complains.addWarningCount();
	}
	
    public void addReview(String giverName, String review) 
    {
        feedback.addReview(giverName, review);
    }

    public HashMap<String, String> getReviews() 
    {
        return feedback.getReviews();
    }
    
    public void addInterest(String interest) 
    {
        profile.addInterest(interest);
    }
    
    public void removeInterest(String interest)
    {
    	profile.removeInterest(interest);
    }
    
    // Function to add a match to the map
    public void addMatch(String partnerCNIC, double similarityIndex) 
    {
        matches.addMatch(partnerCNIC, similarityIndex);
    }


    // Getter for matchMap
    public HashMap<String, Double> getMatchMap() 
    {
        return matches.getMatchMap();
    }

	public boolean isPremium()
	{
		if(membership != null)
			return membership.isPremium();
		else 
			return false;
	}
	
	public void addChat(String partnerCNIC, int chatNumber)
	{
		chats.put(partnerCNIC, chatNumber);
		UserDBhandler.addChat(this.cnic, partnerCNIC, chatNumber);
	}
	
	public int getChatNumber(String partnerCNIC)
	{
		if(chats.containsKey(partnerCNIC))
		{
			return chats.get(partnerCNIC);
		}
		else 
		{
			return 0;
		}
	}

	public String getProfilePictureName()
	{
		return profilePictureName;
	}

	public void setProfilePictureName(String profilePictureName)
	{
		this.profilePictureName = profilePictureName;
		UserDBhandler.updateUser(this);
	}
	
	public Set<String> getAllInterests()
    {
    	return profile.getAllInterests();
    }
}
